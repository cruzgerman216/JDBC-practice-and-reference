package com.qa.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qa.utils.DBConfig;

public class DBConnection {

	private PreparedStatement ps;
	private Connection con;
	private ResultSet rs;
	
	public DBConnection() throws SQLException {
		con = DriverManager.getConnection(DBConfig.url, DBConfig.user, DBConfig.pw);
	}
	
	public void readAll() throws SQLException {
		String sql = "SELECT * FROM user";
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		if(!rs.next()) {
			System.out.println("Nothing is found");
		}else {
			do {
				System.out.println(String.format("ID: %d, name: %s", rs.getInt("id"), rs.getString("name") ));
			}while(rs.next());
		}
	}
	
	public void createUser(String name) throws SQLException{
		String sql = "INSERT INTO user (name) VALUES (?)";
		ps = con.prepareStatement(sql);
		ps.setString(1, name);
		ps.execute();
	}
	
	public void readOne(int id) throws SQLException{
		String sql = "SELECT * FROM user WHERE id =?";
		ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		rs = ps.executeQuery();
		if(!rs.next()) {
			System.out.println("Nothing is found");
		}else {
			do {
				System.out.println(String.format("ID: %d, name: %s", rs.getInt("id"), rs.getString("name") ));
			}while(rs.next());
		}
	}
	
	public void Update(int id, String name) throws SQLException{
		String sql = "UPDATE user SET name = ? WHERE id = ?";
		ps = con.prepareStatement(sql);
		ps.setInt(2, id);
		ps.setString(1,name);
		ps.execute();
	}
	
	public void delete(int id) throws SQLException{
		String sql = "DELETE FROM user WHERE id = ?";
		ps = con.prepareStatement(sql);
		ps.setInt(id, id);
		ps.execute();
	}
	public void tearDown() throws SQLException {
		System.out.println("Connection closed.");
		con.close();
	}
	
}
