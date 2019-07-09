package com.mvc.board.fcont;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.mvc.board.command.Command;


@WebServlet("*.bo")	
public class UController extends HttpServlet {
	
	public HashMap map;	
	public ArrayList<String> key;
	public ArrayList value;
	public void init() throws ServletException {
		System.err.println("init!!!!");
		try {
			// DOM Parser 사용해보기~~
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = doc = builder.parse("C:\\Data\\lesson\\HTML\\source\\BasicModel2\\src\\com\\si\\var\\Test.xml");
			
			NodeList list = doc.getElementsByTagName("Testxml");
			
			key = new ArrayList<String>();
			value = new ArrayList();
			for(Node node= list.item(0).getFirstChild() ; node !=null ; node=node.getNextSibling()) {
				if(node.getNodeName().equals("key")) {
					key.add(node.getTextContent());
				}else if(node.getNodeName().equals("value")) {
					value.add(node.getTextContent());
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		map = new HashMap();
		for(int i = 0; i < key.size(); i++) {
			String strClass = (String) value.get(i);
			try {
				Class tmp = Class.forName(strClass);
				Object o = tmp.newInstance();
				map.put(key.get(i), o);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
		public void service(HttpServletRequest req,HttpServletResponse res) {
			String viewPage = null;
			
			try {
				req.setCharacterEncoding("UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			String uri = req.getRequestURI();
			String conPath = req.getContextPath();
			String com = uri.substring(conPath.length());
			System.out.println(com);
			Command command = (Command)map.get(com);
			command.execute(req, res);
		}
	
}
