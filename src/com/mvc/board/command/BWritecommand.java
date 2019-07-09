package com.mvc.board.command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.board.dao.BDao;
import com.mvc.board.dto.BDto;
import com.mvc.board.dto.FDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BWritecommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String directory = request.getServletContext().getRealPath("/upload/");
		System.out.println(directory);
		int maxSize = 1024*1024*100;
		String encoding = "UTF-8";
		
		try{
			MultipartRequest req = new MultipartRequest(request,directory,maxSize,encoding,new DefaultFileRenamePolicy());
			String fileName = req.getOriginalFileName("postFile");
			String realFileName = req.getFilesystemName("postFile");
			FDto fdto = new FDto();
			fdto.setFileName(fileName);
			fdto.setRealFileName(realFileName);
			new BDao().uploadProc(fdto);
			
			String boardTitle = req.getParameter("boardTitle");
			String boardUserID = (String)request.getSession().getAttribute("userID");
			String boardContent = req.getParameter("boardContent");
			
			BDto bdto = new BDto();
			bdto.setBoardTitle(boardTitle);
			bdto.setBoardUserID(boardUserID);
			bdto.setBoardContent(boardContent);
			bdto.setFileName(fileName);
			new BDao().writeProc(bdto);
			
			String viewPage = "list.bo?pageNum=1";
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			try {
				dispatcher.forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
			return;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}

}
