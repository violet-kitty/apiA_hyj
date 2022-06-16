package jspstudy.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//.do로 끝나는 페이지를 여기서 처리하고 싶다면 아래 코드를 바꿔도 되지만 web.xml(서버에서 가지고 있는 웹 설정 파일) 파일을 변경해도 됨
@WebServlet("/FrontController")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String pj = request.getContextPath();
		String command = uri.substring(pj.length());
		//ex) /member/memberList.do
		
		String[] subpath = command.split("/");
		String location = subpath[1];	//2번째 문자열 추출  ex) member
		
		if(location.equals("member")) {
			MemberController mc = new MemberController();
			mc.doGet(request, response);
		}
		else if(location.equals("board")) {
			BoardController bc = new BoardController();
			bc.doGet(request, response);
		}
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}