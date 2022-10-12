package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.MessagesDTO;

public class MessagesDAO {
	
	// Singleton Pattern ---------------------------------------------------------------------------------	
	private static MessagesDAO instance;
	
	synchronized public static MessagesDAO getInstance() throws Exception{
		if(instance == null ) {
			instance = new MessagesDAO();
		}
		return instance;
	}
	
	// Driver ---------------------------------------------------------------------------------
	private MessagesDAO() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");	
	}
	
	
	// Connection ---------------------------------------------------------------------------------
	private Connection getConnection() throws Exception {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "kh";
		String pw = "kh";
		return DriverManager.getConnection(url, id, pw);
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
