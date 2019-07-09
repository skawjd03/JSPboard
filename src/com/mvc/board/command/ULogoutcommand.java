package com.mvc.board.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ULogoutcommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("userID");
		System.err.println(request.getSession().getAttribute("userID"));
		String viewPage = "Main.jsp";
		try {
			response.sendRedirect(viewPage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
