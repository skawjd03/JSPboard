package com.mvc.board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mvc.board.dto.UDto;

public class UDao {
	
	private Connection con;
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet rs;
	
	public UDao() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "increpas";
			String password = "increpas";
			con = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			System.out.println("※※※※※※※※※※※※※※ [DB 연결 실패] ※※※※※※※※※※※※※※※");
			e.printStackTrace();
		}
	}
	
	public void userJoin(UDto udto) {
		String sql = "insert into sibarUser values(userNo_sq.nextval,?,?,?,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, udto.getUserName());
			pstmt.setString(2, udto.getUserID());
			pstmt.setString(3, udto.getUserPass());
			pstmt.setString(4, udto.getUserGender());
			pstmt.setString(5, udto.getUserPhone());
			pstmt.setString(6, udto.getUserBirth());
			pstmt.setString(7, udto.getUserEmail());
			pstmt.setString(8, udto.getUserAddress());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("회원정보 인서트 오류");
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
			}
		}
		
	}
	
	public int iDCheck(String userID) {
		String sql = "select userID from sibarUser where userID = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				try {
					pstmt.close();
					rs.close();
					con.close();
				} catch (SQLException e) {
				}
				return -1;
			}
		} catch(Exception e) {
			System.err.println("아이디 검색 오류");
		} finally {
			try {
				pstmt.close();
				rs.close();
				con.close();
			} catch (SQLException e) {
			}
		}
		
		return 1;
	}
	
	public int loginProc(String userID,String userPass) {
		String sql = "select userID,userPass from sibarUser where userID = ? and userPass = ? ";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userID);
			pstmt.setString(2, userPass);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				try {
					pstmt.close();
					rs.close();
					con.close();
				} catch (SQLException e) {
				}
			
				return 1;
			}
		} catch (SQLException e) {
			System.err.println("로그인 sql 오류");
		} finally {
			try {
				pstmt.close();
				rs.close();
				con.close();
			} catch (SQLException e) {
			}
		}
		
		return -1;
	}

}
