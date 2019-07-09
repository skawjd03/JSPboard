package com.mvc.board.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.board.dao.UDao;

public class ULogincommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String userID = request.getParameter("userID");
		String userPass = request.getParameter("userPass");
		String check = request.getParameter("check");
		
		
		
		HttpSession session = request.getSession();
		
		int result = new UDao().loginProc(userID, userPass);
		
		String viewPage = "Main.jsp";
		
		if(result != 1) {
			try {
				response.setContentType("text/html;charset=UTF-8"); 
	            PrintWriter out = response.getWriter(); 
	            out.println("<script>"); 
	            out.println("alert('로그인 성공');"); 
	            out.println("history.back();"); 
	            out.println("</script>"); 
	            out.close();

			} catch (IOException e) {
				
			}
		}else {
			session.setAttribute("userID", userID);
			if(check!=null) {
				Cookie cookie = new Cookie("userID",userID);
				cookie.setMaxAge(1*60);
				response.addCookie(cookie);
			}
		}
		try {
			response.sendRedirect(viewPage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
