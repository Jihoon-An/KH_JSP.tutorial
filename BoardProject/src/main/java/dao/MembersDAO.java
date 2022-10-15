package dao;

import commons.Password;
import dto.MembersDTO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MembersDAO {
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

    public int signup(MembersDTO dto) throws Exception{
        String sql = "insert into members values(?, ?, ?, ?, ?, ?, ?, ?, sysdate)";

        try (Connection con = this.getConnection();
             PreparedStatement pstat = con.prepareStatement(sql);) {
            pstat.setString(1, dto.getId());
            pstat.setString(2, Password.getSHA256(dto.getPw()));
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
}
