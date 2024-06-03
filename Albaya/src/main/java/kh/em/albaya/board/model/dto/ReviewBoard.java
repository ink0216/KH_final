package kh.em.albaya.board.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ReviewBoard {
	
	private int reviewBoardNo;			  // 게시글 번호
	private int memberNo; 				  // 회원 번호
	private int reviewBoardCode; 		  // 게시글 코드(1:공지, 2:일반)
	private String reviewTitle; 		  // 게시글 제목
	private String reviewBoardContent; 	  // 게시글 내용
	private String reviewBoardWriteDate;  // 작성일
	private int readCount; 				  // 조회수
	private String boardDelFl; 			  // 게시글 삭제 여부
	private String reviewBoardUpdateDate; // 마지막 수정일
	private int commentCount;			  // 댓글 수
	// MEMBER 테이블 조인
	private String memberEmail;
	
	// 댓글 목록
	private List<Comment> commentList;
	
	
	

}
