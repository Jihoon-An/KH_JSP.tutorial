package dao;

import commons.Password;
import dto.FreeBoardDTO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FreeBoardDAO {
    private static FreeBoardDAO instance = null;

    synchronized public static FreeBoardDAO getInstance() throws Exception {
        if (instance == null) {
            instance = new FreeBoardDAO();
        }
        return instance;
    }

    private FreeBoardDAO() throws Exception {
    }

    private Connection getConnection() throws Exception {
        Context ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
        return ds.getConnection();
    }

    public int posting(FreeBoardDTO dto) throws Exception {
        String sql = "insert into freeBoard values(freeBoard_seq.nextval,?,?,?,sysdate,0)";

        try (Connection con = this.getConnection();
             PreparedStatement pstat = con.prepareStatement(sql);) {
            pstat.setString(1, dto.getWriter());
            pstat.setString(2, dto.getTitle());
            pstat.setString(3, dto.getContent());


            int result = pstat.executeUpdate();
            con.commit();
            con.close();
            pstat.close();

            return result;
        }
    }

    public List<FreeBoardDTO> roadPostingList() throws Exception{
        String sql = "select * from freeBoard order by 1";
        try (
                Connection con = this.getConnection();
                PreparedStatement pstat = con.prepareStatement(sql);
        ) {
            List<FreeBoardDTO> result = new ArrayList<>();
            ResultSet resultSet = pstat.executeQuery();
            while (resultSet.next()){
                result.add(0, new FreeBoardDTO(resultSet));
            }
            return result;
        }
    }
}
