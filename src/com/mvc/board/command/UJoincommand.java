package com.mvc.board.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.board.dao.UDao;
import com.mvc.board.dto.UDto;

public class UJoincommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String userName = request.getParameter("userName");
		String userID = request.getParameter("userID");
		String userPass = request.getParameter("userPass");
		String userGender = request.getParameter("userGender");
		String userPhone = request.getParameter("userPhone");
		String userBirth = request.getParameter("userBirth");
		String userEmail = request.getParameter("userEmail");
		String userAddress = request.getParameter("userAddress");
		
		UDto udto = new UDto();
		udto.setUserAddress(userAddress);
		udto.setUserBirth(userBirth);
		udto.setUserEmail(userEmail);
		udto.setUserGender(userGender);
		udto.setUserID(userID);
		udto.setUserName(userName);
		udto.setUserPass(userPass);
		udto.setUserPhone(userPhone);
		
		new UDao().userJoin(udto);
		
		String viewPage = "Login.jsp";
		try {
			response.sendRedirect(viewPage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
