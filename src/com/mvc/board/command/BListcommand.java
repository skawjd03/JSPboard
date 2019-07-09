package com.mvc.board.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.board.dao.BDao;
import com.mvc.board.dto.BDto;

public class BListcommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<BDto> bdto = new BDao().getList(Integer.parseInt(request.getParameter("pageNum")));
		
		HttpSession session = request.getSession();
		session.setAttribute("list", bdto);
		session.setAttribute("pageNum",Integer.parseInt(request.getParameter("pageNum")));
		String viewPage = "board.jsp";
		try {
			response.sendRedirect(viewPage);
		} catch (IOException e) {
			e.printStackTrace();
			
		}
	}

}
