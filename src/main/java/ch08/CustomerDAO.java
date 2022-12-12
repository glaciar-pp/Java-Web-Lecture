package ch08;

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

public class CustomerDAO {
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
	
	public void deleteCustomer(String uid) {
		Connection conn = getConnection();
		String sql = "UPDATE customer SET isDeleted=1 WHERE uid=?;";
		try {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, uid);

			// Update 실행..Delete대신에 isDeleted 필드를 1로 변경함
			pStmt.executeUpdate();
			pStmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateCustomer(Customer c) {
		Connection conn = getConnection();
		String sql = "UPDATE customer SET name=? WHERE uid=?;";
		try {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, c.getUname());
			pStmt.setString(2, c.getUid());

			// Update 실행
			pStmt.executeUpdate();
			pStmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Customer getCustomer(String uid) {
		Connection conn = getConnection();
		String sql = "SELECT * FROM customer WHERE uid = ?;";
		Customer c = new Customer();
		try {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, uid);
			
			//Select 실행
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				c.setUid(rs.getString(1));
				c.setUname(rs.getString(2));
				c.setRegDate(LocalDate.parse(rs.getString(3)));
				c.setIsDeleted(rs.getInt(4));
			}
			rs.close();
			pStmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}
	
	public List<Customer> getCustomers() { //결과물 = Customer 리스트
		Connection conn = getConnection();
		List<Customer> list = new ArrayList<>();
		String sql = "SELECT * FROM customer WHERE isDeleted=0;";
		try {
			Statement stmt = conn.createStatement();
			
			// Select 실행
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Customer c = new Customer();
				c.setUid(rs.getString(1));
				c.setUname(rs.getString(2));
				c.setRegDate(LocalDate.parse(rs.getString(3)));
				c.setIsDeleted(rs.getInt(4));
				list.add(c);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}

	public void insertCustomer(Customer c) {
		Connection conn = getConnection();
		String sql = "INSERT INTO customer(uid, name) VALUES(?,?);";
		try {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, c.getUid());
			pStmt.setString(2, c.getUname());
			
			pStmt.executeUpdate();
			pStmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

