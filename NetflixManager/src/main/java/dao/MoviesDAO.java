package dao;

import dto.MoviesDTO;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

//import org.apache.commons.dbcp2.BasicDataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MoviesDAO {
//    private String dbID = "kh";
//    private String dbPW = "kh";
//    private String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";

    private static MoviesDAO instance = null;

    synchronized public static MoviesDAO getInstance() throws Exception {
        if (instance == null) {
            instance = new MoviesDAO();
        }
        return instance;
    }

    private MoviesDAO() throws Exception {
//        this.bds.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
//        this.bds.setUsername("kh");
//        this.bds.setPassword("kh");
//        this.bds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
//        this.bds.setInitialSize(30);
    }
//    private BasicDataSource bds = new BasicDataSource();
    private Connection getConnection() throws Exception {
        Context ctx = new InitialContext();
        DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
        return ds.getConnection();
    }

    //insert
    public int insert(String title, String genre, Timestamp launch_date) throws Exception {
        String sql = "insert into movies values(id_seq.nextval, ?, ?, ?)";

        try (Connection con = this.getConnection();
             PreparedStatement pstat = con.prepareStatement(sql);) {
            pstat.setString(1, title);
            pstat.setString(2, genre);
            pstat.setTimestamp(3, launch_date);

            int result = pstat.executeUpdate();
            return result;
        }
    }

    //delete
    public int delete(int id) throws Exception {
        String sql = "delete from movies where id = ?";

        try (Connection con = this.getConnection();
             PreparedStatement pstat = con.prepareStatement(sql);) {

            pstat.setInt(1, id);

            int result = pstat.executeUpdate();

            con.commit();

            return result;
        }
    }

    //view all
    public List<MoviesDTO> viewAll() throws Exception {
        String sql = "select * from movies order by 1";
        List<MoviesDTO> outList = new ArrayList<>();
        try (Connection con = this.getConnection();
             PreparedStatement pstat = con.prepareStatement(sql);) {
            try (
                    ResultSet sqlResult = pstat.executeQuery();
            ) {
                while (sqlResult.next()) {
                    int id = sqlResult.getInt("id");
                    String title = sqlResult.getString("title");
                    String genre = sqlResult.getString("genre");
                    Timestamp time = sqlResult.getTimestamp("launch_date");
                    String launch_date = new SimpleDateFormat("yyyy.MM.dd").format(time);
                    outList.add(new MoviesDTO(id, title, genre, launch_date));
                }
                return outList;
            }
        }
    }

    //search
    public List<MoviesDTO> search(String searchTitle) throws Exception {
        String sql = "select * from movies where title like ? order by 1";
        List<MoviesDTO> outList = new ArrayList<>();
        try (
                Connection con = getConnection();
                PreparedStatement statement = con.prepareStatement(sql);) {
            statement.setString(1, "%" + searchTitle + "%");
            try (
                    ResultSet sqlResult = statement.executeQuery();
            ) {
                while (sqlResult.next()) {
                    int id = sqlResult.getInt("id");
                    String title = sqlResult.getString("title");
                    String genre = sqlResult.getString("genre");
                    Timestamp time = sqlResult.getTimestamp("launch_date");
                    String launch_date = new SimpleDateFormat("yyyy.MM.dd").format(time);
                    outList.add(new MoviesDTO(id, title, genre, launch_date));
                }
                return outList;
            }
        }
    }

    //update
    public int update(String title, String genre, int id) throws Exception {
        String sql = "update movies set title = ?, genre = ? where id = ?";

        try (Connection con = this.getConnection();
             PreparedStatement pstat = con.prepareStatement(sql);
        ) {
            pstat.setString(1, title);
            pstat.setString(2, genre);
            pstat.setInt(3, id);

            int sqlResult = pstat.executeUpdate();

            con.commit();

            return sqlResult;
        }
    }


}
