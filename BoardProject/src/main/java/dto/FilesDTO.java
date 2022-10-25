package dto;

public class FilesDTO {
    private int outNum;
    private int fileSeq;
    private String originName;
    private String sysName;
    private int parentSeq;

    public FilesDTO(int outNum, int fileSeq, String originName, String sysName, int parentSeq) {
        this.outNum =outNum;
        this.fileSeq = fileSeq;
        this.originName = originName;
        this.sysName = sysName;
        this.parentSeq = parentSeq;
    }

    public FilesDTO() {}

    public int getOutNum() {
        return outNum;
    }

    public void setOutNum(int outNum) {
        this.outNum = outNum;
    }

    public int getFileSeq() {
        return fileSeq;
    }

    public void setFileSeq(int fileSeq) {
        this.fileSeq = fileSeq;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public int getParentSeq() {
        return parentSeq;
    }

    public void setParentSeq(int parentSeq) {
        this.parentSeq = parentSeq;
    }
}
