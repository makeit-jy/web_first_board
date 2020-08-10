package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JoayoDao {

	private static JoayoDao joayoDao;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	JoayoDao(){}
	
	public static synchronized JoayoDao getInstance() {
		if(joayoDao==null) {
			joayoDao = new JoayoDao();
		}
		return joayoDao;
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
	
	public void joayo(int boardId,String loginId) {
		String sql = "select * from joayo where boardid = ? and userid = ?";
		String userid = null;
		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardId);
			pstmt.setString(2, loginId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				userid = rs.getString("userid");
			}
			if(userid==null) {// 좋아 아직 안눌렀으면 좋아요 해줌
				addJoayo(boardId,loginId);
				BoardDao.getInstance().joayoUp(boardId);
			}
			else {// 좋아요 눌렀으면 취소 해줌
				deleteJoayo(boardId,loginId);
				BoardDao.getInstance().joayoDown(boardId);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(con,pstmt,rs);
		}
	}
	
	public void addJoayo(int boardId,String loginId) {
		String sql = "insert into joayo values(?,?)";
		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, loginId);
			pstmt.setInt(2, boardId);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(con,pstmt,rs);
		}
	}
	
	public void deleteJoayo(int boardId,String loginId) {
		String sql = "delete from joayo where userid=? and boardid=?";
		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, loginId);
			pstmt.setInt(2, boardId);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(con,pstmt,rs);
		}
	}
}
