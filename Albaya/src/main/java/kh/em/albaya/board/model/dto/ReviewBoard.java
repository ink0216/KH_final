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
	
	private int reviewBoardNo;
	private int memberNo;
	private int reviewBoardCode;
	private String reviewTitle;
	private String reviewBoardContent;
	private String reviewBoardWriteDate;
	private int readCount;
	private String boardDelFl;
	private String reviewBoardUpdateDate;
	private int commentCount;
	// MEMBER 테이블 조인
	private String memberEmail;
	
	// 댓글 목록
	private List<Comment> commentList;
	
	
	

}
