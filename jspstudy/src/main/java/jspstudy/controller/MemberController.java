package jspstudy.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.Out;

import jspstudy.domain.MemberVo;
import jspstudy.service.MemberDao;

/*
 * html은 브라우저가 해석함
 * jsp는 서버(톰캣)가 해석해서 html 코드로 변환해 브라우저한테 보내줌
 * 내부적으로 서버가 jsp 쓰는 법
 * jsp를 class로 바꿔서 사용
 * 그럼 처음부터 class 형태면 빠름
 * 데이터 접근 관련은 class에서 처리
 * 
 * Model 1
 * 데이터 처리 > 자바빈 사용
 * 메소드 따로 모아놓음(객체 생성 후 안에 정의된 메소드들 사용)
 * 서버 부하가 상당히 줄어듦
 * 
 * Model 2
 * 페이지 이동은 servlet에서 처리
 * servlet : java(class)로 만든 웹 페이지(class 형태)
 * 굳이 jsp 없어도 웹페이지 만들기 가능
 * Servlet이 HttpServlet를 상속받아서 웹페이지에서 뜨는 것
 * 
 * 그럼 jsp의 역할은? 화면에 보여주는 것
 * 
 * 최종적 디자인  MVC(Model View Controller)
 * Model  >  class가 담당
 * View  >  jsp가 담당
 * Controller  >  Servlet이 담당
 * */


// 이동하기 위한 서블릿
//.do로 넘어오는 것들을 받아서 처리하도록 함.(가상경로가 .do로 끝나므로)
//톰캣 대신 가상경로를 받아 처리하게 할것임. (서버는 jsp 밖에 처리 못함)
@WebServlet("/MemberController")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//넘어온 가상경로 풀경로를 추출하는 메소드
		String uri = request.getRequestURI();
		//프로젝트 이름을 추출
		String pj = request.getContextPath();
		//프로젝트 이름을 뺀 나머지 가상경로를 추출
		String command = uri.substring(pj.length());
		//System.out.println("command : "+command);
		
		
		//jsp에서 처리하던 기능을 servlet 페이지에서
		if(command.equals("/member/memberjoinAction.do")) {
			
			//input 객체의 이름을 담은 파라미터를 호출하면 그 객체의 값을 리턴한다.
		    String memberid = request.getParameter("memberid");
			String memberpwd = request.getParameter("memberpwd");
			String membername = request.getParameter("membername");
			String memberemail = request.getParameter("memberemail");
			String membergender = request.getParameter("membergender");
			String memberjumin = request.getParameter("memberjumin");
			String memberaddr = request.getParameter("memberaddr");
			String memberphone = request.getParameter("memberphone");
			//여러개의 값을 담은 객체의 이름을 호출하면 배열형태의 값을 리턴한다.
			String[] memberhobby = request.getParameterValues("memberhobby");
			
			String hobby = "";
			for(int i=0;i<memberhobby.length;i++){
				hobby = hobby + "," + memberhobby[i];
				//out.println(memberhobby[i]);
			}
			hobby = hobby.substring(1);
			//out.println(hobby);
			//out.println 은 화면에 출력
			// System.out.println 은 콘솔에 출력
			
			//IP 뽑아내는 메소드
			String ip = InetAddress.getLocalHost().getHostAddress();
			
			MemberDao md = new MemberDao();
			int value = md.insertMember(memberid, memberpwd, membername, memberemail, membergender, memberaddr, memberphone, memberjumin, hobby, ip);
			
			PrintWriter out = response.getWriter();
			
			/*
			 * forward는 요청을 하면 내부적으로 그 페이지가 나타남
			 * sendRedirect는 주소도 가상주소로 바뀌고 이동도 함
			 * */
			
			
		  	if(value==1){
		  		response.sendRedirect(request.getContextPath()+"/member/memberList.do");
				//out.println("<script>alert('회원가입 성공!');location.href='"+request.getContextPath()+"/index.jsp';</script>");
			}
			else{
				response.sendRedirect(request.getContextPath()+"/member/memberjoin.do");
				//out.println("<script>alert('회원가입 실패!');location.href='./memberjoin.html';</script>");
			}
		 	
			out.println();
			
		}
		
		else if(command.equals("/member/memberjoin.do")){
			//회원가입 페이지로 들어오면 처리를 함
			
			//페이지를 이동시키는 방법 중 하나
			//외부적으로는 가상 경로가 나타나지만 내부적으로는 실제 페이지를 지정해줘서 나타나게함
			//경로만 가상경로로 보이고 실제 페이지를 보여주게됨
			//getRequestDispatcher 실제로 나타내줘야할 페이지의 경로를 지정해주는 메소드 
			RequestDispatcher rd = request.getRequestDispatcher("/member/memberjoin.jsp");
			
			//forward 방식 주소만 가상주소고 내부적으로 이동하는 방식. 실제 페이지로 이동하므로 자원 공유 가능
			rd.forward(request, response);
		}
		
		else if(command.equals("/member/memberList.do")) {
			//memberSelectAll 메소드 호출
			MemberDao md = new MemberDao();
			ArrayList<MemberVo> alist = md.memberSelectAll();
			
			request.setAttribute("alist", alist);
			
			RequestDispatcher rd = request.getRequestDispatcher("/member/memberlist.jsp");
			rd.forward(request, response);
		}
		
		//로그인 페이지로
		else if(command.equals("/member/memberLogin.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("/member/memberLogin.jsp");
			rd.forward(request, response);
		}
		//로그인처리
		else if(command.equals("/member/memberLoginAction.do")) {
			//1. 넘어온 값을 받는다
			String memberid = request.getParameter("memberid");
			String memberpwd = request.getParameter("memberpwd");
			
			//2. 처리 (쿼리 실행하는 메소드 사용)
			MemberDao md = new MemberDao();
			MemberVo mv = md.memberLogin(memberid, memberpwd);
			
			//3. 이동, 세션에 정보 담기
			//브라우저를 끄지 않으면 연결성이 유지되기 때문에 세션 데이터는 계속 유지됨
			HttpSession session = request.getSession();
			
			if(mv != null) {
				session.setAttribute("midx", mv.getMidx());
				session.setAttribute("memberId", mv.getMemberid());
				session.setAttribute("memberName", mv.getMembername());
				
				//로그인 후 가야할 페이지가 있다면
				if(session.getAttribute("saveUrl") != null) {
					response.sendRedirect((String)session.getAttribute("saveUrl"));
				}
				else {
					response.sendRedirect(request.getContextPath()+"/member/memberList.do");
				}
			}
			else { //로그인에 실패하면 다시 로그인 화면으로
				response.sendRedirect(request.getContextPath()+"/member/memberLogin.do");
			}
		}
		//로그아웃
		else if(command.equals("/member/memberLogout.do")) {
			HttpSession session = request.getSession();
			session.invalidate(); //세션 만료
			//메인으로 가기
			response.sendRedirect(request.getContextPath()+"/");
		}
		
		else if(command.equals("/member/main.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
