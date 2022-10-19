package dao;

import commons.Password;
import commons.TimeUtils;
import dto.MembersDTO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MembersDAO {
//    private static Logger logger = Logger.getGlobal();

    private static MembersDAO instance = null;

    synchronized public static MembersDAO getInstance() throws Exception {
        if (instance == null) {
            instance = new MembersDAO();
        }
        return instance;
    }

    private MembersDAO() throws Exception {
    }

    private Connection getConnection() throws Exception {
        Context ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
        return ds.getConnection();
    }

    // ID 중복 체크
    public boolean isIdExist(String id) throws Exception {
        String sql = "select * from members where id = ?";

        try (
                Connection con = this.getConnection();
                PreparedStatement pstat = con.prepareStatement(sql);
        ) {
            pstat.setString(1, id);
            ResultSet resultSet = pstat.executeQuery();
            return resultSet.next();
        }
    }

    // 회원가입 - DB에 데이터 넣기.
    public int signup(MembersDTO dto) throws Exception {
        String sql = "insert into members values(?, ?, ?, ?, ?, ?, ?, ?, sysdate)";

        try (Connection con = this.getConnection();
             PreparedStatement pstat = con.prepareStatement(sql);) {
            pstat.setString(1, dto.getId());
            pstat.setString(2, Password.getSHA512(dto.getPw()));
            pstat.setString(3, dto.getName());
            pstat.setString(4, dto.getPhone());
            pstat.setString(5, dto.getEmail());
            pstat.setString(6, dto.getZipcode());
            pstat.setString(7, dto.getAddress1());
            pstat.setString(8, dto.getAddress2());

            int result = pstat.executeUpdate();
            con.commit();
            con.close();
            pstat.close();

            return result;
        }
    }

    /*
    로그인
     DB에서 ID를 기준으로 PW를 조회하고 select의 결과가 없거나 비밀번호가 일치하지 않으면 false를 반환하고
     일치하면 true를 반환.
     */
    public boolean login(String id, String pw) throws Exception {
        String sql = "select pw from members where id = ?";
        try (
                Connection con = this.getConnection();
                PreparedStatement pstat = con.prepareStatement(sql);
        ) {
            pstat.setString(1, id);
            ResultSet resultSet = pstat.executeQuery();
            if (resultSet.next()) {
                if (resultSet.getString("pw").equals(Password.getSHA512(pw))) {
//                    logger.info("Login Success");
                    System.out.println("Login Success");
                    return true;
                }
            }
            return false;
        }
    }

    public int memberout(String id) throws Exception {
        String sql = "delete from members where id = ?";

        try (Connection con = this.getConnection();
             PreparedStatement pstat = con.prepareStatement(sql);) {
            pstat.setString(1, id);

            int result = pstat.executeUpdate();
            con.commit();
            con.close();
            pstat.close();

            return result;
        }
    }

    public MembersDTO selectMember(String searchId) throws Exception{
        String sql = "select * from members where id = ?";
        try (
                Connection con = this.getConnection();
                PreparedStatement pstat = con.prepareStatement(sql);
        ) {
            pstat.setString(1, searchId);
            ResultSet resultSet = pstat.executeQuery();
            if (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                String zipcode = resultSet.getString("zipcode");
                String address1 = resultSet.getString("address1");
                String address2 = resultSet.getString("address2");
                String signupDate = TimeUtils.timestampToString(resultSet.getDate("signup_date"));
                return new MembersDTO(id, "", name, phone, email, zipcode, address1, address2, signupDate);
            }
            return null;
        }
    }

    public int modify(MembersDTO dto) throws Exception{
        String sql = "UPDATE members SET name=?, phone=?, email=?, zipcode=?, address1=?, address2=? WHERE id = ?";

        try (Connection con = this.getConnection();
             PreparedStatement pstat = con.prepareStatement(sql);) {
            pstat.setString(1, dto.getName());
            pstat.setString(2, dto.getPhone());
            pstat.setString(3, dto.getEmail());
            pstat.setString(4, dto.getZipcode());
            pstat.setString(5, dto.getAddress1());
            pstat.setString(6, dto.getAddress2());
            pstat.setString(7, dto.getId());

            int result = pstat.executeUpdate();
            con.commit();
            con.close();
            pstat.close();

            return result;
        }
    }
}
