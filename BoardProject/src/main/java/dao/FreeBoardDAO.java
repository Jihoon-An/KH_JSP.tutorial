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

    private FreeBoardDAO()  {
    }

    private Connection getConnection() throws Exception {
        Context ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
        return ds.getConnection();
    }

    public int posting(FreeBoardDTO dto) throws Exception {
        String sql = "insert into freeBoard values(?,?,?,?,sysdate,0)";

        try (Connection con = this.getConnection();
             PreparedStatement pstat = con.prepareStatement(sql);
        ) {
            pstat.setInt(1, dto.getPostNum());
            pstat.setString(2, dto.getWriter());
            pstat.setString(3, dto.getTitle());
            pstat.setString(4, dto.getContent());

            int result = pstat.executeUpdate();

            con.commit();

            return result;
        }
    }

    public List<FreeBoardDTO> searchAllPosting() throws Exception {
        String sql = "select * from freeBoard order by write_date";
        try (
                Connection con = this.getConnection();
                PreparedStatement pstat = con.prepareStatement(sql);
        ) {
            List<FreeBoardDTO> result = new ArrayList<>();

            try (ResultSet resultSet = pstat.executeQuery();) {
                while (resultSet.next()) {
                    result.add(0, new FreeBoardDTO(resultSet));
                }
                return result;
            }
        }
    }

    public FreeBoardDTO searchPosting(int postedNum) throws Exception {
        String sql = "select * from freeBoard where freeBoard_seq = ?";
        try (
                Connection con = this.getConnection();
                PreparedStatement pstat = con.prepareStatement(sql);
        ) {
            pstat.setInt(1, postedNum);

            try (ResultSet resultSet = pstat.executeQuery();) {
                if (resultSet.next()) {
                    return new FreeBoardDTO(resultSet);
                } else {
                    return null;
                }
            }
        }
    }

    public void viewCountUp(int postNum) throws Exception {
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

    public void deletePosting(int postNum) throws Exception {
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

    public void modifyPosting(FreeBoardDTO dto) throws Exception {
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

    public String getPageNavi(int currentPage, FreeBoardDTO dto) throws Exception {

        String sql = "select count(*) from freeBoard";
        try (
                Connection con = this.getConnection();
                PreparedStatement pstat = con.prepareStatement(sql);
                ResultSet rs = pstat.executeQuery();
        ) {
            rs.next();
            int recordTotalCount = rs.getInt("count(*)");
            dto.setRecordTotalCount(recordTotalCount);

            int recordCountPerPage = dto.getRecordCountPerPage();
            int naviCountPerPage = dto.getNaviCountPerPage();
            int pageTotalCount = (recordTotalCount + recordCountPerPage - 1) / recordCountPerPage;

            if (currentPage < 1) {
                currentPage = 1;
            } else if (currentPage > pageTotalCount) {
                currentPage = pageTotalCount;
            }

            int startNavi = ((currentPage - 1) / naviCountPerPage * naviCountPerPage) + 1;
            int endNavi = startNavi + naviCountPerPage - 1;
            if (endNavi > pageTotalCount) {
                endNavi = pageTotalCount;
            }

            StringBuilder sb = new StringBuilder();

            if (startNavi != 1) {
                sb.append("<a href='/freeBoard.board?cpage=" + (startNavi - 1) + "'>< </a>");
            }
            for (int i = startNavi; i <= endNavi; i++) {
                sb.append("<a href='/freeBoard.board?cpage=" + i + "'>" + i + " </a>");
            }
            if (endNavi != pageTotalCount) {
                sb.append("<a href='/freeBoard.board?cpage=" + (endNavi + 1) + "'>> </a>");
            }

            return sb.toString();
        }
    }

    public List<FreeBoardDTO> selectBySeqRange(int start, int end) throws Exception {
        String sql = "select * from (select freeBoard.*, row_number() over(order by write_date desc) rn from freeBoard) where rn between ? and ?";
        try (
                Connection con = this.getConnection();
                PreparedStatement pstat = con.prepareStatement(sql);
        ) {
            List<FreeBoardDTO> list = new ArrayList<>();

            pstat.setInt(1, start);
            pstat.setInt(2, end);

            try (ResultSet resultSet = pstat.executeQuery();) {
                while (resultSet.next()) {
                    list.add(new FreeBoardDTO(resultSet));
                }
                return list;
            }
        }
    }

    public int getPostSeq() throws Exception{
        String sql = "select freeBoard_seq.nextval freeBoard_seq from dual";
        try (
                Connection con = this.getConnection();
                PreparedStatement pstat = con.prepareStatement(sql);
        ) {
            try (ResultSet resultSet = pstat.executeQuery()) {
                resultSet.next();
                return resultSet.getInt("freeBoard_seq");
            }
        }
    }

}