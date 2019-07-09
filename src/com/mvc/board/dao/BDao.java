package com.mvc.board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mvc.board.dto.BDto;
import com.mvc.board.dto.FDto;

public class BDao {
	private Connection con;
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet rs;
	
	public BDao() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "increpas";
			String password = "increpas";
			con = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			System.out.println("占쌔※※※※※※※※※※※※∽옙 [DB 占쏙옙占쏙옙 占쏙옙占쏙옙] 占쌔※※※※※※※※※※※※※∽옙");
		}
	}
	
	public void writeProc(BDto bdto) {
		String sql = "insert into sibarBoard values(boardNo_sq.nextval,?,?,?,sysdate,0,1,?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,bdto.getBoardTitle());
			pstmt.setString(2,bdto.getBoardContent());
			pstmt.setString(3,bdto.getBoardUserID());
			pstmt.setString(4,bdto.getFileName());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("占쌉쏙옙占쏙옙 占쏙옙 占싸쇽옙트 占쏙옙占쏙옙");
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
			}
		}
		
	}
	
	public void uploadProc(FDto fdto) {
		String sql = "insert into sibarFile values(?,?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,fdto.getFileName());
			pstmt.setString(2,fdto.getRealFileName());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("占쌉쏙옙占쏙옙 占쏙옙 占싸쇽옙트 占쏙옙占쏙옙");
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
			}
		}
	}
	
	public String fileDownProc(String FileName) {
		String sql = "select * from sibarFile where FileName = ?";
		FDto fdto = new FDto();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,FileName);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				fdto.setRealFileName(rs.getString("REALFILENAME"));
				return fdto.getRealFileName();
			}
		} catch (SQLException e) {
			System.err.println("占쌉쏙옙占쏙옙 占쏙옙 占싸쇽옙트 占쏙옙占쏙옙");
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
			}
		} return "";
	}
	
	public ArrayList<BDto> getList(int pageNum){
		ArrayList<BDto> list = new ArrayList<BDto>();
		String sql = "select * from (select rownum rno,boardno,boardtitle,boardcontent,boarduserid,boarddate,boardhit,boardisshow from sibarboard order by boardNo desc) where rno > ? and rno <= ?";
		try {
			pstmt = con.prepareStatement(sql);
			if(pageNum == 1) {
				pstmt.setInt(1, 0);
				pstmt.setInt(2, pageNum*10);
			} else {
				pstmt.setInt(1, (pageNum-1)*10); 
				pstmt.setInt(2, pageNum*10);
			}
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BDto bdto = new BDto();
				bdto.setBoardContent(rs.getString("boardContent"));
				bdto.setBoardDate(rs.getString("boardDate"));
				bdto.setBoardHit(rs.getInt("boardHit"));
				bdto.setBoardNo(rs.getInt("boardNo"));
				bdto.setBoardTitle(rs.getString("boardTitle"));
				bdto.setBoardUserID(rs.getString("boardUserID"));
				list.add(bdto);
			}
		} catch (SQLException e) {
			System.err.println("占쌉쏙옙占쏙옙 占쏙옙占쏙옙트 sql 占쏙옙占쏙옙 占쏙옙占쏙옙");
		} finally {
			try {
				pstmt.close();
				rs.close();
				con.close();
			} catch (SQLException e) {
			}
		}
		
		return list;
	}
	
	public void setHit(int boardNo) {
		String sql = "update sibarboard set boardHit = boardHit+1 where boardNo = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("占쏙옙회占쏙옙 占시몌옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙");
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
			}
		}
	}
	
	public BDto getView(int boardNo) {
		BDto bdto = new BDto();
		String sql = "select * from sibarboard where boardNo = ?";
		setHit(boardNo);
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bdto.setBoardContent(rs.getString("boardContent"));
				bdto.setBoardDate(rs.getString("boardDate"));
				bdto.setBoardHit(rs.getInt("boardHit"));
				bdto.setBoardNo(rs.getInt("boardNo"));
				bdto.setBoardTitle(rs.getString("boardTitle"));
				bdto.setBoardUserID(rs.getString("boardUserID"));
				bdto.setFileName(rs.getString("FileName"));
				return bdto;
			}
		} catch (Exception e) {
			System.err.println("占쌉시깍옙 占쌀뤄옙占쏙옙占쏙옙 sql 占쏙옙占쏙옙 占쏙옙占쏙옙");
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
