package com.mvc.board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mvc.board.dto.CDto;

public class CDao {
	private Connection con;
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet rs;
	
	public CDao() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "increpas";
			String password = "increpas";
			con = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			System.out.println("※※※※※※※※※※※※※※ [DB 연결 실패] ※※※※※※※※※※※※※※※");
		}
	}
	
	public void setReply(String userID,String commentParent,String commentContent) {
		String sql = "insert into sibarcomment values(comment_sq.nextval,?,?,?,1,sysdate,0,0,null)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(commentParent));
			pstmt.setString(2, userID);
			pstmt.setString(3, commentContent);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("댓글 인서트 sql 구문 오류");
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
			}
		}
	}
	
	public void setReReply(String userID,String commentContent,String commentParent,String boardNo) {
		String sql = "insert into sibarcomment values(comment_sq.nextval,?,?,?,1,sysdate,0,(select (commentindent+1) from sibarcomment where commentno = ?),?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(boardNo));
			pstmt.setString(2, userID);
			pstmt.setString(3, commentContent);
			pstmt.setInt(4, Integer.parseInt(commentParent));
			pstmt.setInt(5, Integer.parseInt(commentParent));
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("댓글 인서트 sql 구문 오류");
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
			}
		}
	}
	
	public ArrayList<CDto> getCommentList(int boardNo){
		 ArrayList<CDto> list = new ArrayList<CDto>();
		 String sql = "select * from (select rownum rno,commentNo,commentParent,commentuserID,commentContent,commentISSHOW,commentDate,commentThumb,commentIndent,commentMother from sibarcomment start with commentMother is null connect by prior commentNo = commentMother) where commentParent = ?";
		 try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CDto cdto = new CDto();
				cdto.setCommentNo(rs.getInt("commentNo"));
				cdto.setCommentParent(rs.getInt("commentParent"));
				cdto.setCommentUserID(rs.getString("commentuserID"));
				cdto.setCommentContent(rs.getString("commentContent"));
				cdto.setCommentISSHOW(rs.getInt("commentISSHOW"));
				cdto.setCommentDate(rs.getString("commentDate"));
				cdto.setCommentThumb(rs.getInt("commentThumb"));
				cdto.setCommentIndent(rs.getInt("commentIndent"));
				cdto.setCommentMother(rs.getInt("commentMother"));
				list.add(cdto);
			}
		
			return list;
			
		} catch (Exception e) {
			System.err.println("댓글 리스트 불러오는 구문 오류");
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				rs.close();
				con.close();
			} catch (SQLException e) {
			}
		}
		 
		 return null;
	}
}
