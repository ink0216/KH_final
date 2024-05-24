package kh.em.albaya.board.model.dto;

public class Pagination {

	private int currentPage;		// 현재 페이지 번호
	private int listCount;			// 전체 게시글 수
	
	private int limit = 15;			// 한 페이지 목록에 보여지는 게시글 수 
	private int pageSize = 10;		// 보여질 페이지 번호 개수 
	
	
	private int maxPage;			// 마지막 페이지 번호 (첫페이지는 1)
	private int startPage;			// 보여지는 맨 앞 페이지 번호 
	private int endPage;			// 보여지는 맨 뒤 페이지 번호
	
	private int prevPage;			// 이전 페이지 모음의 마지막 번호 
	private int nextPage;			// 다음 페이지 모음의 시작 번호 
	
	
	public Pagination(int currentPage, int listCount) {
		super();
		this.currentPage = currentPage;
		this.listCount = listCount;
		calculate();
	}


	public Pagination(int currentPage, int listCount, int limit, int pageSize) {
		super();
		this.currentPage = currentPage;
		this.listCount = listCount;
		this.limit = limit;
		this.pageSize = pageSize;
		calculate();
	}


	public int getCurrentPage() {
		return currentPage;
	}


	public int getListCount() {
		return listCount;
	}


	public int getLimit() {
		return limit;
	}


	public int getPageSize() {
		return pageSize;
	}


	public int getMaxPage() {
		return maxPage;
	}


	public int getStartPage() {
		return startPage;
	}


	public int getEndPage() {
		return endPage;
	}


	public int getPrevPage() {
		return prevPage;
	}


	public int getNextPage() {
		return nextPage;
	}


	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		calculate();
	}


	public void setListCount(int listCount) {
		this.listCount = listCount;
		calculate();
	}


	public void setLimit(int limit) {
		this.limit = limit;
		calculate();
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		
		calculate();
	}


	//toString
	@Override
	public String toString() {
		return "Pagination [currentPage=" + currentPage + ", listCount=" + listCount + ", limit=" + limit
				+ ", pageSize=" + pageSize + ", maxPage=" + maxPage + ", startPage=" + startPage + ", endPage="
				+ endPage + ", prevPage=" + prevPage + ", nextPage=" + nextPage + "]";
	}
	
	
	private void calculate() {
		
		// 마지막 페이지
		maxPage = (int)Math.ceil((double)listCount / limit);
		
		// 페이지 시작번호
		startPage = (currentPage -1) / pageSize * pageSize +1;
		// 페이지 끝번호
		endPage = pageSize - 1 + startPage;
		
		// 페이지 끝 번호가 최대 페이지 수를 초과한 경우
		if(endPage > maxPage) endPage = maxPage;
		
		
		if(currentPage < pageSize) {
			prevPage = 1; 
		} else {
			prevPage = startPage - 1;
		}
		

		if(endPage == maxPage) {
			nextPage = maxPage;;
		}else {
			nextPage = endPage + 1;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
