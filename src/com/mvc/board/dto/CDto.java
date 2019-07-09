package com.mvc.board.dto;

public class CDto {
	private String commentUserID, commentContent, commentDate;
	private int commentNo, commentParent, commentISSHOW, commentThumb, commentIndent, commentMother;
	
	
	public int getCommentMother() {
		return commentMother;
	}
	public void setCommentMother(int commentMother) {
		this.commentMother = commentMother;
	}
	public String getCommentUserID() {
		return commentUserID;
	}
	public void setCommentUserID(String commentUserID) {
		this.commentUserID = commentUserID;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public String getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public int getCommentParent() {
		return commentParent;
	}
	public void setCommentParent(int commentParent) {
		this.commentParent = commentParent;
	}
	public int getCommentISSHOW() {
		return commentISSHOW;
	}
	public void setCommentISSHOW(int commentISSHOW) {
		this.commentISSHOW = commentISSHOW;
	}
	public int getCommentThumb() {
		return commentThumb;
	}
	public void setCommentThumb(int commentThumb) {
		this.commentThumb = commentThumb;
	}
	public int getCommentIndent() {
		return commentIndent;
	}
	public void setCommentIndent(int commentIndent) {
		this.commentIndent = commentIndent;
	}
	
	
}
