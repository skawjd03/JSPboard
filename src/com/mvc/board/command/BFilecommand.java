package com.mvc.board.command;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.board.dao.BDao;

public class BFilecommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String directory = request.getServletContext().getRealPath("/upload/");
		String fileName = request.getParameter("FileName");
		System.err.println(fileName);
		try {
			 response.setContentType("application/octet-stream");
			 String realFileName = new BDao().fileDownProc(fileName);
				
				// 저장되어 있는 폴더경로/저장된 파일명 으로 풀 path를 만들어준다.
					// 자바에서는 \를 표시하기 위해서는 \를 한번 더 붙여주기 때문에 \\로 해준다.
				String sFilePath = directory + fileName;
				// 풀 path에 대한걸 파일 객체로 인식시킨다.
				File outputFile = new File(sFilePath);
				// 저장된 파일을 읽어와 저장할 버퍼를 임시로 만들고 버퍼의 용량은 이전에 한번에 업로드할 수 있는 파일크기로 지정한다.
				byte[] temp = new byte[1024*1024*10]; // 10M
				
				// 파일을 읽어와야 함으로 inputStream을 연다.(풀패스를 가지는 파일 객체를 이용해 input스트림을 형성한다.)
				FileInputStream in = new FileInputStream(outputFile);
				
				// 유형 확인 : 읽어올 경로의 파일의 유형 -> 페이지 생성할 때 타입을 설정해야 한다.
				String sMimeType = request.getServletContext().getMimeType(sFilePath);
				// 지정되지 않은 유형 예외처리
				if ( sMimeType == null ){
					// 관례적인 표현
					sMimeType = "application.octec-stream"; // 일련된 8bit 스트림 형식
					// 유형이 알려지지 않은 파일에 대한 읽기 형식 지정
				}
				
				// 파일 다운로드 시작
				// 유형을 지정해 준다.
				response.setContentType(sMimeType); // 응답할 페이지가 text/html;charset=utf-8을
				// 파일 mime 타입으로 지정해 준다.
				
				// 업로드 파일의 제목이 깨질 수 있으므로 인코딩을 해준다.
				String sEncoding = new String(fileName.getBytes("euc-kr"),"8859_1");
				//String B = "utf-8";
				//String sEncoding = URLEncoder.encode(A,B);
				
				// 기타 내용을 헤더에 올려야 한다.
				// 기타 내용을 보고 브라우저에서 다운로드 시 화면에 출력시켜 준다.
				String AA = "Content-Disposition";
				String BB = "attachment;filename="+sEncoding;
				response.setHeader(AA,BB);
				
				// 브라우저에 쓰기
				ServletOutputStream out2 = response.getOutputStream();
				
				int numRead = 0;
				
				// 바이트 배열 temp의 0번부터 numRead번까지 브라우저로 출력
					// 파일이 위치한 곳에 연결된 inputStream에서 읽되 끝(-1) 전까지 while을 돈다.
				while((numRead = in.read(temp,0,temp.length)) != -1){ // temp 배열에 읽어올건데 0번째 인덱스부터 한번에 최대 temp.length 만큼 읽어온다.
					// 읽어올게 더이상 없으면 -1을 리턴하면서 while문을 빠져나감
					
					// 브라우저에 출력 : 근대 header 정보를 attachment로 해놓았음으로 다운로드가 된다.
					out2.write(temp,0,numRead); // temp배열에 있는 데이터의 0번째부터 최대 numRead만큼 출력한다.
				}
				// 자원 해제
				out2.flush();
				out2.close();
				in.close();
			 
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
