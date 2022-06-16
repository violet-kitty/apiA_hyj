package jspstudy.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import jspstudy.domain.BoardVo;
import jspstudy.domain.Criteria;
import jspstudy.domain.PageMaker;
import jspstudy.domain.SearchCriteria;
import jspstudy.service.BoardDao;

@WebServlet("/BoardController")
public class BoardController extends HttpServlet {
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
		//실제 경로 지정. \ 하나만 쓰면 인식이 안될 수도 있어 \\사용
		String uploadPath = "E:\\openapi(A)\\dev\\jspstudy\\src\\main\\webapp\\";
		String saveFolder = "images";
		String saveFullPath = uploadPath+saveFolder;
		
		// 글쓰기 페이지로 이동
		if(command.equals("/board/boardWrite.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardWrite.jsp");
			rd.forward(request, response);
		}
		// 글쓰기 처리
		else if(command.equals("/board/boardWriteAction.do")) {
			int sizeLimit = 1024*1024*15;
			
			//MultipartRequest(requset, 저장 경로, 파일 사이즈 제한, 인코딩방법, 파일 이름이 같은때 처리방법(아래에선 rename 사용))
			//String과 바이너리 타입으로 넘어온 이미지 둘다 받을 수 있돋록 멀티 타입으로 받는것
			MultipartRequest multi = null;
			multi = new MultipartRequest(request, saveFullPath, sizeLimit, "UTF-8", new DefaultFileRenamePolicy());
			//write에서 multi 타입으로 넘겼으므로 request객체 대신 multi 타입으로 값을 받아옴
			String subject = multi.getParameter("subject");
			String writer = multi.getParameter("writer");
			String content = multi.getParameter("content");
			//write 페이지에서 업로드 된 파일 가져오기
			//열거자에 저장될 파일을 담는 객체를 생성
			//write 페이지에서 업로드한 파일의 이름을 받아옴
			//getFileNames()는 넘어온 폼 요소 중 타입이 file인 객체들을 Enumeration 형태로 반환해줌
			Enumeration<?> files = multi.getFileNames();
			//담긴 파일의 객체의 파일이름을 얻음
			String file = (String)files.nextElement();
			//서버상에 실제로 업로드된 파일 이름을 얻음
			//저장되는 파일이름
			String fileName = multi.getFilesystemName(file);
			//원래 파일이름
			//만약 저장되는 파일 이름이 같으면 DB에는 다른 이름으로 저장되므로 원래 파일이름을 따로 저장해줌
			String originFileName = multi.getOriginalFileName(file);
			
			//ip값 알아내기
			String ip = InetAddress.getLocalHost().getHostAddress();
			
			//세션에서 글작성자의 midx값 받아오기
			HttpSession session = request.getSession();
			int midx = (int)session.getAttribute("midx");
			
			BoardDao bd = new BoardDao();
			int value = bd.insertBoard(subject, content, writer, ip, midx, fileName);
			
			if(value==1) {
				response.sendRedirect(request.getContextPath()+"/board/boardList.do");
			}
			else if(value==0) {
				response.sendRedirect(request.getContextPath()+"/board/boardWrite.do");
			}
		}
		// 글 목록 페이지
		else if(command.equals("/board/boardList.do")) {
			
			//페이지 받기
			String page = request.getParameter("page");
			if(page == null) page = "1";
			int pagex = Integer.parseInt(page);
			
			//검색했을 시 키워드 받기
			String keyword = request.getParameter("keyword");
			String searchType = request.getParameter("searchType");
			//검색 기능을 사용하지 않았을 시
			if(keyword == null) keyword = "";
			if(searchType == null) searchType = "subject";
			
			SearchCriteria scri = new SearchCriteria();
			scri.setPage(pagex);
			scri.setKeyword(keyword);
			scri.setSearchType(searchType);
			
			//처리하는 부분
			BoardDao bd = new BoardDao();
			int cnt = bd.boardTotal(scri);
			
			PageMaker pm = new PageMaker();
			pm.setScri(scri);
			pm.setTotalCount(cnt);
			
			ArrayList<BoardVo> alist = bd.boardSelectAll(scri);
			
			// 데이터(자원) 공유
			request.setAttribute("alist", alist);
			request.setAttribute("pm", pm);
			
			//이동하는 부분
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardlist.jsp");
			rd.forward(request, response);
		}
		// 선택한 글 내용 보기
		else if(command.equals("/board/boardContent.do")) {
			
			// get방식으로 boardlist.jsp에서 넘긴 bidx 받기
			//1. 파라미터 넘어옴
			String bidx = request.getParameter("bidx");
			int bidx_ = Integer.parseInt(bidx);
			
			//2. 처리함
			BoardDao bd = new BoardDao();
			BoardVo bv = bd.boardSelectOne(bidx_);
			
			request.setAttribute("bv", bv);		// 내부적으로 자원 공유
			
			//3. 이동함
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardContent.jsp");
			rd.forward(request, response);
			
			
		}
		// 글 수정
		else if(command.equals("/board/boardModify.do")) {
			//파라미터 받기
			String bi = request.getParameter("bidx");
			
			int bidx = Integer.parseInt(bi);
			
			//처리
			BoardDao bd = new BoardDao();
			BoardVo bv = bd.boardSelectOne(bidx);
			
			request.setAttribute("bv", bv);
			
			//이동
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardModify.jsp");
			rd.forward(request, response);
		}
		// 수정 완료했을때
		else if(command.equals("/board/boardModifyAction.do")) {
			//파라미터 받기
			String bi = request.getParameter("bidx");
			int bidx = Integer.parseInt(bi);
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			String writer = request.getParameter("writer");
			
			//처리
			BoardDao bd = new BoardDao();
			int value = bd.boardModify(bidx, subject, content, writer);
			
			if(value == 1) {//글 수정 성공하면 다시 글 보는 페이지로 이동
				response.sendRedirect(request.getContextPath()+"/board/boardContent.do?bidx="+bidx);
			}
		}
		else if(command.equals("/board/boardDelete.do")) {
			String bidx = request.getParameter("bidx");
			
			request.setAttribute("bidx", bidx);
			
			//이동
			// 이동 후 값을 보낸 값을 사용해야 하는 경우 forward로 보내줘야함
			// sendRedirect로 보내면 보낸 값을 쓸 수 없음
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardDelete.jsp");
			rd.forward(request, response);
			
		
		}
		else if(command.equals("/board/boardDeleteAction.do")) {
			String bi = request.getParameter("bidx");
			int bidx = Integer.parseInt(bi);
			
			BoardDao bd = new BoardDao();
			int value = bd.boardDelete(bidx);
			
			//삭제처리에 성공하면 글 목록으로 이동
			if(value==1) {
				response.sendRedirect(request.getContextPath()+"/board/boardList.do");
			}
		}
		// 답변하기 페이지
		else if(command.equals("/board/boardReply.do")) {
			// 변수값들 받아오기
			String bi = request.getParameter("bidx");
			int bidx = Integer.parseInt(bi);
			String origin = request.getParameter("originbidx");
			int originbidx = Integer.parseInt(origin);
			String dep = request.getParameter("depth");
			int depth = Integer.parseInt(dep);
			String lev = request.getParameter("level_");
			int level_ = Integer.parseInt(lev);
			
			//변수 BoardVo에 담기
			BoardVo bv = new BoardVo();
			bv.setBidx(bidx);
			bv.setOriginbidx(originbidx);
			bv.setDepth(depth);
			bv.setLevel_(level_);
			
			//bv 넘겨주기
			request.setAttribute("bv", bv);
			
			//이동
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardReply.jsp");
			rd.forward(request, response);
		}
		//답변 페이지 확인 눌렀을때
		else if(command.equals("/board/boardReplyAction.do")) {
			//변수 넘겨받기
			String bidx = request.getParameter("bidx");
			String originbidx = request.getParameter("originbidx");
			String depth = request.getParameter("depth");
			String level_ = request.getParameter("level_");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			String writer = request.getParameter("writer");
			String ip = InetAddress.getLocalHost().getHostAddress();
			HttpSession session = request.getSession();
			int midx = (int)session.getAttribute("midx");
			
			BoardVo bv = new BoardVo();
			bv.setBidx(Integer.parseInt(bidx));
			bv.setOriginbidx(Integer.parseInt(originbidx));
			bv.setDepth(Integer.parseInt(depth));
			bv.setLevel_(Integer.parseInt(level_));
			bv.setSubject(subject);
			bv.setContent(content);
			bv.setWriter(writer);
			bv.setIp(ip);
			bv.setMidx(midx);
			
			BoardDao bd = new BoardDao();
			int value = bd.replyBoard(bv);
			
			if(value == 1) {
				response.sendRedirect(request.getContextPath()+"/board/boardList.do");
			}
			else {
				response.sendRedirect(request.getContextPath()+"/board/boardContent.do?bidx="+bidx);
			}
			
		}
		//파일 다운로드
		else if(command.equals("/board/fileDownload.do")) {
			//파일 이름 넘겨 받음
			String filename = request.getParameter("filename");
			//파일의 전체 경로
			String filePath = saveFullPath + File.separator + filename;
			byte[]b = new byte[4096];
			//해당 위치에 있는 파일을 읽음
			FileInputStream fileInputStream = new FileInputStream(filePath);
			//실제 소스 경로 지정
			Path source = Paths.get(filePath);
			//파일형식
			String mimeType = Files.probeContentType(source);
			//헤더정보에 추출한 파일 형식을 담음
			response.setContentType(mimeType);
			
			String sEncoding = new String(filename.getBytes("UTF-8"));
			//헤더정보에 파일 이름을 담음
			response.setHeader("Content-Disposition", "attachment;fileName="+sEncoding);
			
			//파일 쓰기
			ServletOutputStream servletOutStream = response.getOutputStream();
			
			int read = 0;
			while((read = fileInputStream.read(b, 0, b.length)) != -1) {
				servletOutStream.write(b, 0, read);
			}
			
			servletOutStream.flush();
			servletOutStream.close();
			fileInputStream.close();
		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}