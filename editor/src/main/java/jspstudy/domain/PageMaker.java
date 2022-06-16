package jspstudy.domain;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class PageMaker {
	private int totalCount;		// 전체 데이터 수
	private int startPage;		// 현재 보여줘야 할 페이지의 시작 페이지 번호
	private int endPage;		// 현재 보여줘야 할 페이지의 끝 페이지 번호
	private boolean prev;		// ◀ 있어야하는가
	private boolean next;		// ▶ 있어야하는가
	private int displayPageNum = 10;	// < 1 2 3 4 5 6 7 8 9 10 > 이러한 형식의 페이징 번호 계산을 위한 변수
	private SearchCriteria scri;
	
	public SearchCriteria getScri() {
		return scri;
	}
	public void setScri(SearchCriteria scri) {
		this.scri = scri;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public int getDisplayPageNum() {
		return displayPageNum;
	}
	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}
	//페이지 번호를 몇개까지 화면에 나타낼지 정하는 메소드
	public void calcData() {
		endPage = (int)(Math.ceil(scri.getPage()/(double)displayPageNum)*displayPageNum);
		startPage = (endPage - displayPageNum)+1;
		
		int tempEndPage = (int)(Math.ceil(totalCount/(double)scri.getPerPageNum()));
		
		if(endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		prev = startPage == 1 ? false : true;
		next = endPage*scri.getPerPageNum() >= totalCount? false : true;
	}
	//검색어로 한글이 들어왔을 때 깨지지 않도록 인코딩해주는 메소드
	public String encoding(String keyword) {
		String str = "";
		
		try {
			if(keyword != null) {
				str = URLEncoder.encode(keyword, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return str;
	}
	
}
