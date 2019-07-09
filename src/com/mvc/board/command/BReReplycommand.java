package com.mvc.board.command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.board.dao.CDao;

public class BReReplycommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String userID = (String)request.getSession().getAttribute("userID");
		String boardNo = request.getParameter("boardNo");
		String commentParent = request.getParameter("commentParent");
		String commentContent = request.getParameter("commentContent");
		System.err.println(userID);
		System.err.println(boardNo);
		System.err.println(commentParent);
		System.err.println(commentContent);
		new CDao().setReReply(userID, commentContent, commentParent, boardNo);
		
		String viewPage = "view.do?boardNo="+request.getParameter("boardNo");
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		return;
	}

}
