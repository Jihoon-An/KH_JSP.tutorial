package dao;

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
            return result;
        }
    }

    public List<FreeBoardDTO> loadPostingList() throws Exception {
        String sql = "select * from freeBoard order by write_date";
        try (
                Connection con = this.getConnection();
                PreparedStatement pstat = con.prepareStatement(sql);
        ) {
            List<FreeBoardDTO> result = new ArrayList<>();
            ResultSet resultSet = pstat.executeQuery();
            while (resultSet.next()) {
                result.add(0, new FreeBoardDTO(resultSet));
            }
            return result;
        }
    }

    public FreeBoardDTO searchLoadPost(int postedNum) throws Exception {
        String sql = "select * from freeBoard where freeBoard_seq = ?";
        try (
                Connection con = this.getConnection();
                PreparedStatement pstat = con.prepareStatement(sql);
        ) {
            pstat.setInt(1, postedNum);
            ResultSet resultSet = pstat.executeQuery();
            if (resultSet.next()) {
                return new FreeBoardDTO(resultSet);
            } else {
                return null;
            }
        }
    }

    public void veiwCountUp(int postNum) throws Exception {
        String sql = "UPDATE freeBoard SET view_count = view_count + 1 WHERE freeBoard_seq=?";
        try (
                Connection con = this.getConnection();
                PreparedStatement pstat = con.prepareStatement(sql);
        ) {
            pstat.setInt(1, postNum);
            pstat.executeUpdate();
            con.commit();
        }
    }

    public void deletePost(int postNum) throws Exception {
        String sql = "delete from freeBoard where freeBoard_seq=?";
        try (
                Connection con = this.getConnection();
                PreparedStatement pstat = con.prepareStatement(sql);
        ) {
            pstat.setInt(1, postNum);
            pstat.executeUpdate();
            con.commit();
        }
    }

    public void modify(FreeBoardDTO dto) throws Exception {
        String sql = "UPDATE freeBoard SET title=?,content=?,write_date=sysdate WHERE freeBoard_seq=?";
        try (
                Connection con = this.getConnection();
                PreparedStatement pstat = con.prepareStatement(sql);
        ) {
            pstat.setString(1, dto.getTitle());
            pstat.setString(2, dto.getContent());
            pstat.setInt(3, dto.getPostNum());
            pstat.executeUpdate();
            System.out.println("수정 완료");
            con.commit();
        }
    }

    public static String getPageNavi() {
        int recordTotalCount = 144; //테이블에 144개의 글이 있다고 가정.
        int recordCountPerPage = 10; //게시판 한 페이지당 10개의 글씩 보여주기로 설정.
        int naviCountPerPage = 10; //게시판 하단의 Pae Navigator 가 한번에 몇 개씩 보여질지 설정.
        int pageTotalCount = (recordTotalCount + recordCountPerPage - 1) / recordCountPerPage; //전체 필요한 페이지 수
        int currentPage = 7; // 현재 페이지 가정.
        int startNavi = ((currentPage - 1) / naviCountPerPage * naviCountPerPage) + 1;
        int endNavi = startNavi + naviCountPerPage - 1;

        return "";
    }
}
