package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.MessagesDTO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MessagesDAO {


	private static MessagesDAO instance = null;

	synchronized public static MessagesDAO getInstance() throws Exception {
		if (instance == null) {
			instance = new MessagesDAO();
		}
		return instance;
	}

	private MessagesDAO() throws Exception {}

	private Connection getConnection() throws Exception {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}
	
	
	// 메세지 추가 ---------------------------------------------------------------------------------
	public int insert(String writer, String message) throws Exception {
		String sql = "insert into messages values(messages_seq.nextval, ?, ?)";
		
		try (Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);) {

			pstat.setString(1, writer);
			pstat.setString(2, message);

			int result = pstat.executeUpdate();

			con.commit();
			con.close();

			return result;
		}
	}
	
	
	// 메세지 출력 ---------------------------------------------------------------------------------
	public List<MessagesDTO> selectAll() throws Exception{
		String sql = "select * from messages order by 1";
		
		try (Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();) {
			
			List<MessagesDTO> list = new ArrayList<>();
			
			while(rs.next()) {
				MessagesDTO dto = new MessagesDTO();
				dto.setSeq(rs.getInt("write_num"));
				dto.setWriter(rs.getString("writer"));
				dto.setMessage(rs.getString("message"));
				list.add(dto);
			}
			
			return list;
		}
	}
	
	
	// 메세지 삭제 ---------------------------------------------------------------------------------
	public int delete(int seq) throws Exception {
		String sql = "delete from messages where write_num = ?";
		
		try (Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, seq);
			
			int result = pstat.executeUpdate();
			
			con.commit();
			
			return result;
		}
	}
	
	
	// 메세지 수정 ---------------------------------------------------------------------------------
	public int update(MessagesDTO dto) throws Exception {
		String sql = "update messages set writer = ?, message = ? where write_num = ?";

		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				){
			pstat.setString(1, dto.getWriter());
			pstat.setString(2, dto.getMessage());
			pstat.setInt(3, dto.getSeq());

			int result = pstat.executeUpdate();

			con.commit();

			return result;
		}
	}
	
	
	
	
	
}
