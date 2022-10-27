package dto;

//import commons.TimeUtils;
//import dao.MembersDAO;

import java.sql.ResultSet;

public class FreeBoardDTO {
    private int postNum;
    private String title;
    private String id;
    private String content;
    private String writer;
    private String writeDate;
    private int viewCount;
    private String displayNew;


//    public FreeBoardDTO(ResultSet resultSet) throws Exception {
//        this.title = resultSet.getString("title");
//        this.content = resultSet.getString("content");
//        this.id = resultSet.getString("writer");
//        try {
//            this.writer = MembersDAO.getInstance().selectMember(resultSet.getString("writer")).getName();
//        }catch (Exception e){
//            this.writer = "NO NAME";
//        }
//        this.writeDate = TimeUtils.toStringDate(resultSet.getTimestamp("write_date"));
//        this.displayNew = (this.writeDate.length() < 10) ?  "inline" : "none";
//        this.postNum = resultSet.getInt("freeBoard_seq");
//        this.viewCount = resultSet.getInt("view_count");
//    }

    public FreeBoardDTO(String writer, String title, String content, int postNum) {
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.postNum = postNum;
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

    public String getDisplayNew() {
        return displayNew;
    }

    public void setDisplayNew(String displayNew) {
        this.displayNew = displayNew;
    }

    int recordTotalCount;
    int recordCountPerPage;
    int naviCountPerPage;

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

    public FreeBoardDTO(int recordCountPerPage, int naviCountPerPage) {
        this.recordCountPerPage = recordCountPerPage;
        this.naviCountPerPage = naviCountPerPage;
    }
}
