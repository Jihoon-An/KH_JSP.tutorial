package dto;

import commons.TimeUtils;
import dao.MembersDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentDTO {
    private int commentNum;
    private String writerId;
    private String writerName;
    private String contents;
    private String writeTime;
    private int postNum;

    /**
     * for CommentDAO.insert
     * @param writerId
     * @param contents
     * @param postNum
     */
    public CommentDTO(String writerId, String contents, int postNum) throws Exception {
        this.writerId = writerId;
        this.contents = contents;
        this.postNum = postNum;
    }

    public CommentDTO(ResultSet resultSet) throws Exception {
        this.commentNum = resultSet.getInt("comment_num");
        this.writerId = resultSet.getString("writer");
        this.writerName = MembersDAO.getInstance().selectMember(this.writerId).getName();
        this.contents = resultSet.getString("contents");
        this.writeTime = TimeUtils.toStringDateTime(resultSet.getTimestamp("write_date"));
        this.postNum = resultSet.getInt("post_num");
    }

    public CommentDTO(){}

    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public String getWriterId() {
        return writerId;
    }

    public void setWriterId(String writerId) {
        this.writerId = writerId;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getWriteTime() {
        return writeTime;
    }

    public void setWriteTime(String writeTime) {
        this.writeTime = writeTime;
    }

    public int getPostNum() {
        return postNum;
    }

    public void setPostNum(int postNum) {
        this.postNum = postNum;
    }


    private int recordTotalCount;
    private int recordCountPerPage;
    private int naviCountPerPage;

    public int getRecordTotalCount() {
        return recordTotalCount;
    }

    public void setRecordTotalCount(int recordTotalCount) {
        this.recordTotalCount = recordTotalCount;
    }

    public int getRecordCountPerPage() {
        return recordCountPerPage;
    }

    public void setRecordCountPerPage(int recordCountPerPage) {
        this.recordCountPerPage = recordCountPerPage;
    }

    public int getNaviCountPerPage() {
        return naviCountPerPage;
    }

    public void setNaviCountPerPage(int naviCountPerPage) {
        this.naviCountPerPage = naviCountPerPage;
    }
}
