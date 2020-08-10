package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDao {

	private static MemberDao memberDao;
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private MemberDao() {
		
	}
	
	public static synchronized MemberDao getInstance() {
		if(memberDao==null) {
			memberDao = new MemberDao();
		}
		return memberDao;
	}
	
	public Connection getConnection() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mariadb://ddangyc.cafe24.com:3306/ddangyc","ddangyc","ekdwls92!");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		if(rs!=null) {
			try {
				rs.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		if(pstmt!=null) {
			try {
				pstmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		if(con!=null) {
			try {
				con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int join(MemberDto memberDto) {
		int joinResult = 1;
		con = this.getConnection();
		String sql = "insert into member values(?,?,?,?,'병아리')"; //마지막 '병아리'에서 변경함.
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberDto.getId());
			pstmt.setString(2, memberDto.getPw());
			pstmt.setString(3, memberDto.getName());
			pstmt.setString(4, memberDto.getEmail());
			//pstmt.setString(5, memberDto.getProfile());	//추가함.
			pstmt.executeUpdate();
		}catch(SQLException e) {
			joinResult = 0;
			e.printStackTrace();
		}finally {
			this.close(con,pstmt,rs);
		}
		return joinResult;
	}
	
	public int login(String id,String pw) {
		int loginResult =0 ;
		con = getConnection();
		String sql = "select pw from member where id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				if(pw.equals(rs.getString("pw"))) {
					loginResult=1;
				}
				else {
					loginResult=-1;
				}
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			this.close(con, pstmt, rs);
		}
		return loginResult;
	}
	
	public MemberDto getMemberDtoById(String id) { ///
		MemberDto memberDto = new MemberDto();
		String sql = "select * from member where id = ?";
		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				memberDto.setId(rs.getString("id"));
				memberDto.setName(rs.getString("name"));
				memberDto.setEmail(rs.getString("email"));
				memberDto.setProfile(rs.getString("profile"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(con,pstmt,rs);
		}
		
		return memberDto;
	}
	
	public String getWriterProfileById(String id) { ///
		String writerprofile="";
		String sql = "select profile from member where id = ?";
		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				writerprofile = rs.getString("profile");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(con,pstmt,rs);
		}
		
		return writerprofile;
	}
	
	public void updateWriterProfileById(String id, String animal) { // 내가 추가함.
	
		String sql = "update member set profile = ? where id = ?";
		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, animal);
			pstmt.setString(2, id);
			pstmt.executeUpdate();			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(con,pstmt,rs);
		}

	}
	
	public String checkIdExsist(String id) {
		String s="";
		String sql = "select id from member where id = ?";
		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				s = rs.getString("id");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(con,pstmt,rs);
		}
		
		return s;
	}

}
