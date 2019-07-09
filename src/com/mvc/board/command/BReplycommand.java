package com.mvc.board.command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.board.dao.CDao;

public class BReplycommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String userID = (String)request.getSession().getAttribute("userID");
		String commentParent = request.getParameter("boardNo");
		String commentContent = request.getParameter("commentContent");
		
		new CDao().setReply(userID, commentParent, commentContent);
		
		
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
