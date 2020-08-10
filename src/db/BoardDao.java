package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDao {
	private static BoardDao boardDao;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	BoardDao(){}
	
	public static synchronized BoardDao getInstance() {
		if(boardDao==null) {
			boardDao = new BoardDao();
		}
		return boardDao;
	}
	
	public Connection getConnection() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mariadb://ddangyc.cafe24.com:3306/ddangyc","ddangyc","ekdwls92!");
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		if(rs!=null) {try {rs.close();}catch(SQLException e){e.printStackTrace();}}
		if(pstmt!=null) {try {pstmt.close();}catch(SQLException e) {e.printStackTrace();}}
		if(con!=null) {try {con.close();}catch(SQLException e) {e.printStackTrace();}}
	}
	
	public List<BoardDto> getList(){
		List<BoardDto> list = new ArrayList<>();
		con = this.getConnection();
		String sql = "select * from board";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardDto boardDto = new BoardDto();
				boardDto.setId(rs.getInt("id"));
				boardDto.setTitle(rs.getString("title"));
				boardDto.setContent(rs.getString("content"));
				boardDto.setWdate(rs.getString("wdate"));
				boardDto.setHit(rs.getInt("hit"));
				boardDto.setCategory(rs.getString("category"));
				boardDto.setWriter(rs.getString("writer"));
				boardDto.setWriterprofile(rs.getString("writerprofile"));
				boardDto.setJoayo(rs.getInt("joayo"));
				list.add(boardDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(con,pstmt,rs);
		}
		
		return list;
	}
	
	
	public BoardDto getDto(int id) {
		BoardDto boardDto = new BoardDto();
		String sql = "select * from board where id = ?";
		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id+"");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				boardDto.setId(rs.getInt("id"));
				boardDto.setTitle(rs.getString("title"));
				boardDto.setContent(rs.getString("content").replace("\r\n", "<br>")); // 이거 추가함.
				boardDto.setWdate(rs.getString("wdate"));
				boardDto.setHit(rs.getInt("hit"));
				boardDto.setCategory(rs.getString("category"));
				boardDto.setWriter(rs.getString("writer"));
				boardDto.setWriterprofile(rs.getString("writerprofile"));
				boardDto.setJoayo(rs.getInt("joayo"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(con,pstmt,rs);
		}
		return boardDto;
	}
	
	public void write(BoardDto boardDto) {
		String sql = "insert into board values(?,?,?,now(),0,?,?,?,0)";
		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, boardDto.getId());
			pstmt.setString(2, boardDto.getTitle());
			pstmt.setString(3, boardDto.getContent());
			pstmt.setString(4, boardDto.getCategory());
			pstmt.setString(5, boardDto.getWriter());
			pstmt.setString(6, boardDto.getWriterprofile());
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(con,pstmt,rs);
		}
	}
	

	public int getCurrentNumber() {
		int maxNum = 0;
		String sql = "select max(id) from board";
		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				maxNum = rs.getInt("max(id)");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(con,pstmt,rs);
		}
		return maxNum;
	}
	
	public void hitUp(int id) {
		int hit=0;
		String sql = "select hit from board where id = ?";
		String sql2 = "update board set hit = ? where id = ?";
		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				hit = rs.getInt("hit");
			}
			pstmt = con.prepareStatement(sql2);
			pstmt.setInt(1, hit+1);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(con,pstmt,rs);
		}
	}
	
	public void delete(int id) {
		String sql = "delete from board where id = ?";
		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,id);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(con,pstmt,rs);
		}
	}
	
	public void joayoUp(int boardId) {
		String sql = "update board set joayo = joayo+1 where id = ?";
		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardId);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(con,pstmt,rs);
		}
	}
	
	public void joayoDown(int boardId) {
		String sql = "update board set joayo = joayo-1 where id = ?";
		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardId);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(con,pstmt,rs);
		}
	}
}
