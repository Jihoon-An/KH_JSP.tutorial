package dao;

import dto.FilesDTO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FilesDAO {
    private static FilesDAO instance = null;

    synchronized public static FilesDAO getInstance() throws Exception {
        if (instance == null) {
            instance = new FilesDAO();
        }
        return instance;
    }

    private FilesDAO()  {}

    private static Connection getConnection() throws Exception {
        Context ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
        return ds.getConnection();
    }

    public void insert(FilesDTO dto) throws Exception {
        String sql = "insert into files values(file_seq.nextval, ?,?,0)";
        try (Connection con = this.getConnection();
             PreparedStatement pstat = con.prepareStatement(sql)) {
            pstat.setString(1, dto.getOriginName());
            pstat.setString(2, dto.getSysName());

            pstat.executeUpdate();

            con.commit();
        }
    }

    public List<FilesDTO> selectAll() throws Exception{
        String sql = "select * from files";
        try (
                Connection con = this.getConnection();
                PreparedStatement pstat = con.prepareStatement(sql)
                ){
            try(ResultSet resultSet = pstat.executeQuery();){
                List<FilesDTO> dto = new ArrayList<>();
                while (resultSet.next()){
                    int fileSeq = resultSet.getInt("file_seq");
                    String originName = resultSet.getString("origin_name");
                    String sysName = resultSet.getString("sys_name");
                    int parentSeq = resultSet.getInt("parent_seq");

                    dto.add(new FilesDTO(fileSeq, originName, sysName, parentSeq));
                }
                return dto;
            }
        }
    }
}
