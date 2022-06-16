package jspstudy.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//서블릿은 java 코드안에 html 코드 작성 가능
@WebServlet("/ServletTest")
public class ServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	//get방식으로 넘길때 호출됨
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath()).append("first servlet");
		
		//글자 깨짐 방지
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//화면에 HTML 출력
		PrintWriter out = response.getWriter();
		out.println("<html>"
				+ "<head>"
				+ "<title>서블릿</title>"
				+ "</head>"
				+ "<body>"
				+ "<h1>안녕하세요</h1><hr>"
				+ "</body>"
				+ "</html>");
		
		
		
	}
	
	//post방식으로 넘길때 호출됨
	// but post방식도 get 호출되므로 결국 doGet에 코드 작성
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
