package com.mvc.board.command;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.board.dao.BDao;
import com.mvc.board.dao.CDao;
import com.mvc.board.dto.BDto;
import com.mvc.board.dto.CDto;
import com.mvc.board.dto.FDto;

public class BViewcommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
			System.err.println(boardNo);
			BDto bdto = new BDao().getView(boardNo);
			ArrayList<CDto> cdto = new CDao().getCommentList(boardNo);
			request.setAttribute("boardInfo", bdto);
			request.setAttribute("FileName", bdto.getFileName());
			request.setAttribute("CommentList", cdto);
			
			String viewPage = "view.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			try {
				dispatcher.forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
			return;
	}

}
