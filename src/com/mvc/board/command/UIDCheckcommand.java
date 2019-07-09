package com.mvc.board.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.board.dao.UDao;

public class UIDCheckcommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String userID = request.getParameter("userID");
		
		int result = new UDao().iDCheck(userID);
		System.err.println(result);
		try {
			response.getWriter().print(result);
		} catch (IOException e) {
		}
	}

}
