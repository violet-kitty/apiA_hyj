package jspstudy.domain;

// 페이지 번호를 담기 위한 클래스
public class Criteria {
	private int page; //페이지
	private int perPageNum; // 화면에 출력하는 리스트 갯수
	
	public Criteria() {
		this.page = 1;
		this.perPageNum = 15;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if(page <=1) this.page = 1;
		else this.page = page;
	}

	public int getPerPageNum() {
		return perPageNum;
	}

	public void setPerPageNum(int perPageNum) {
		if(perPageNum <=0 || perPageNum >100) this.perPageNum = 10;
		else this.perPageNum = perPageNum;
	}
}
