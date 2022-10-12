package dao;

import dto.MoviesDTO;

import javax.servlet.annotation.WebServlet;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MoviesDAO {
    private String dbID = "kh";
    private String dbPW = "kh";
    private String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";

    private static MoviesDAO instance = null;

    synchronized public static MoviesDAO getInstance() throws Exception {
        if (instance == null) {
            instance = new MoviesDAO();
        }
        return instance;
    }

    private MoviesDAO() throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
    }

    private Connection getConnection() throws Exception {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String id = "kh";
        String pw = "kh";
        return DriverManager.getConnection(url, id, pw);
    }

    //insert
    public int insert(String title, String genre) throws Exception {
        String sql = "insert into movies values(id.nextval, ?, ?, sysdate)";

        try (Connection con = this.getConnection();
             PreparedStatement pstat = con.prepareStatement(sql);) {

            pstat.setString(1, title);
            pstat.setString(2, genre);

            int result = pstat.executeUpdate();

            con.commit();

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
    public ArrayList<MoviesDTO> viewAll() throws Exception {
        String sql = "select * from movies where title like ? order by 1";
        ArrayList<MoviesDTO> outList = new ArrayList<>();
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
                    String launch_date = new SimpleDateFormat("yyyy.MM.dd.HH.mm").format(time);
                    outList.add(new MoviesDTO(id, title, genre, launch_date));
                }
                return outList;
            }
        }
    }

    //search
    public ArrayList<MoviesDTO> search(String searchTitle) throws Exception {
        String sql = "select * from movies where title like ? order by 1";
        ArrayList<MoviesDTO> outList = new ArrayList<>();
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
                    String launch_date = new SimpleDateFormat("yyyy.MM.dd.HH.mm").format(time);
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
