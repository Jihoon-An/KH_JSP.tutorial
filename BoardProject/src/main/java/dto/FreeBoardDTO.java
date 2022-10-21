package dto;

import commons.TimeUtils;
import dao.MembersDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FreeBoardDTO {
    private int postNum;
    private String title;
    private String id;
    private String content;
    private String writer;
    private String writeDate;
    private int viewCount;


    public FreeBoardDTO(ResultSet resultSet) throws Exception {
        this.title = resultSet.getString("title");
        this.content = resultSet.getString("content");
        this.id = resultSet.getString("writer");
        this.writer = MembersDAO.getInstance().selectMember(resultSet.getString("writer")).getName();
        this.writeDate = TimeUtils.timestampToString(resultSet.getTimestamp("write_date"));
        this.postNum = resultSet.getInt("freeBoard_seq");
        this.viewCount = resultSet.getInt("view_count");
    }

    public FreeBoardDTO(String writer, String title, String content) {
        this.writer = writer;
        this.title = title;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPostNum() {
        return postNum;
    }

    public void setPostNum(int postNum) {
        this.postNum = postNum;
    }

    public String getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(String writeDate) {
        this.writeDate = writeDate;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }
}