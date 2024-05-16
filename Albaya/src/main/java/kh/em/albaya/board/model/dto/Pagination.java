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

}
