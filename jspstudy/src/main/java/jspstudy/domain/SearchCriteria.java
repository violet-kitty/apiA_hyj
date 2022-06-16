package jspstudy.domain;

public class SearchCriteria extends Criteria{
	private String searchType;	// 검색하는 타입(ex)제목으로 검색, 작성자로 검색)
	private String keyword;		// 검색 키워드
	
	public SearchCriteria() {
		this.searchType = "";
		this.keyword = "";
	}
	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
