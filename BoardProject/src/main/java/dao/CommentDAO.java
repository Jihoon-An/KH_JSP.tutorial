package dao;

import dto.CommentDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {
    private static CommentDAO instance = null;

    synchronized public static CommentDAO getInstance() throws Exception {
        if (instance == null) {
            instance = new CommentDAO();
        }
        return instance;
    }

    private CommentDAO()  {}

    /**
     * delete comment from comments table.
     * @param commentNum Comment number
     */
    public void delete(int commentNum) throws Exception {
        String sql = "delete from comments where comment_num=?";
        try (
                Connection con = DaoInstance.getConnection();
                PreparedStatement pstat = con.prepareStatement(sql);
        ) {
            pstat.setInt(1, commentNum);

            pstat.executeUpdate();

            con.commit();
        }
    }

    /**
     * insert commentNum, writer, contents, writeTime, postNum in comments table.
     * @param dto for writerId, contents, writeTime
     */
    public void insert(CommentDTO dto) throws Exception {
        String sql = "insert into comments values(comment_num.nextval,?,?,sysdate,?)";

        try (Connection con = DaoInstance.getConnection();
             PreparedStatement pstat = con.prepareStatement(sql);
        ) {
            pstat.setString(1, dto.getWriterId());
            pstat.setString(2, dto.getContents());
            pstat.setInt(3, dto.getPostNum());

            pstat.executeUpdate();

            con.commit();
        }
    }

    /**
     * update comment in comments table.
     * @param dto to contents, commentNum.
     */
    public void modify(CommentDTO dto) throws Exception{
        String sql = "UPDATE comments SET contents = ? WHERE comment_num = ?";
        try (
                Connection con = DaoInstance.getConnection();
                PreparedStatement pstat = con.prepareStatement(sql);
        ) {
            pstat.setString(1, dto.getContents());
            pstat.setInt(2, dto.getCommentNum());

            pstat.executeUpdate();

            con.commit();
        }
    }

    /**
     * select all comment from commets table where postNum == (@param postNum).
     * @param postNum post number that comment is in.
     * @param start start index.
     * @param end end index.
     * @return type is List of CommentDTO.
     * All comment where postNum == (@param postNum).
     */
    public List<CommentDTO> select(int postNum, int start, int end) throws Exception{
        String sql = "select * from (select comments.*, row_number() over(order by write_date desc) rn from comments where post_num = ?)";
        // where rn between ? and ?
        try (
                Connection con = DaoInstance.getConnection();
                PreparedStatement pstat = con.prepareStatement(sql);
        ) {
            pstat.setInt(1, postNum);
//            pstat.setInt(2, start);
//            pstat.setInt(3, end);
            List<CommentDTO> comments = new ArrayList<>();
            try (ResultSet resultSet = pstat.executeQuery();) {
                while (resultSet.next()) {
                    comments.add(new CommentDTO(resultSet));
                }
                return comments;
            } catch (Exception e) {
                return null;
            }
        }
    }

    /**
     * select just one comment from comments table.
     * @param commentNum comment number in comments table.
     * @return a commentDTO where commentNum == (@param commentNum).
     */
    public CommentDTO select(int commentNum) throws Exception{
        String sql = "select * from comments where comment_num = ?";
        try (
                Connection con = DaoInstance.getConnection();
                PreparedStatement pstat = con.prepareStatement(sql);
        ) {
            pstat.setInt(1, commentNum);
            try (ResultSet resultSet = pstat.executeQuery();) {
                if (resultSet.next()) {
                    return new CommentDTO(resultSet);
                }
                else {
                    return null;
                }
            }
        }
    }


    public String getNavi(int currentPage, CommentDTO dto) throws Exception {

        String sql = "select count(*) from comments";
        try (
                Connection con = DaoInstance.getConnection();
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
}
