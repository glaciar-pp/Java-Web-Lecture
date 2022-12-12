package ch08.player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class PlayerDAO {
	//DB 연결
	public static Connection getConnection() {
		Connection conn;
		try {
			Context initContext = new InitialContext();
			DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/world");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return conn;
	}
	
	// 선수목록
	public List<Player> getPlayers() {
		Connection conn = getConnection();
		List<Player> list = new ArrayList<>();
		String sql = "SELECT * FROM baseballteam WHERE isDeleted=0;";
		try {
			Statement stmt = conn.createStatement();
			
			// Select 실행
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Player p = new Player();
				p.setBackNo(rs.getInt(1));
				p.setName(rs.getString(2));
				p.setPosition(rs.getString(3));
				p.setBirthday(LocalDate.parse(rs.getString(4)));
				p.setHeight(rs.getInt(5));
				p.setIsDeleted(rs.getInt(6));
				
				list.add(p);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	//등록에 사용
	public Player getPlayer(int backNo) {
		Connection conn = getConnection();
		String sql = "SELECT * FROM baseballTeam WHERE backNo=?;";
		Player p = new Player();
		try {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, backNo);
			
			// Select 실행
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				p.setBackNo(rs.getInt(1));
				p.setName(rs.getString(2));
				p.setPosition(rs.getString(3));
				p.setBirthday(LocalDate.parse(rs.getString(4)));
				p.setHeight(rs.getInt(5));
				p.setIsDeleted(rs.getInt(6));
			}
			rs.close();
			pStmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}
	
	// 선수등록
	public void insertPlayer(Player p) {
		Connection conn = getConnection();
		String sql = "INSERT INTO baseballteam VALUES (?,?,?,?,?, DEFAULT);";
		try {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, p.getBackNo());
			pStmt.setString(2, p.getName());
			pStmt.setString(3, p.getPosition());
			pStmt.setString(4, p.getBirthday().toString());
			pStmt.setInt(5, p.getHeight());
			
			pStmt.executeUpdate();
			pStmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//선수정보 수정
	public void updatePlayer(Player p) {
		Connection conn = getConnection();
		String sql = "UPDATE baseballteam SET name=?, position=?,  birthday=?, height=?, isDeleted=? WHERE backNo=?;";
		try {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, p.getName());
			pStmt.setString(2, p.getPosition());
			pStmt.setString(3, p.getBirthday().toString());
			pStmt.setInt(4, p.getHeight());
			pStmt.setInt(5, p.getIsDeleted());
			pStmt.setInt(6, p.getBackNo());
			
			// Update 실행
			pStmt.executeUpdate();
			pStmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 선수방출
	public void releasePlayer(int backNo) {
		Connection conn = getConnection();
		String sql = "UPDATE baseballteam SET isDeleted=1 WHERE backNo=?;";
		try {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, backNo);

			// Delete 사항 Update 실행
			// isDeleted 적용
			pStmt.executeUpdate();
			pStmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

