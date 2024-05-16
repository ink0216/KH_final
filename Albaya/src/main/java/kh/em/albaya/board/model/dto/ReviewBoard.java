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
	private String reviewTitle;
	private String reviewBoardContent;
	private String reviewBoardWriteDate;
	private int readCount;
	private String boardDelFl;
	private String reviewBoardUpdateDate;
	private int reviewBoardCode;
	
	// 특정 게시글에 작성된 댓글 목록 조회
	private List<Comment> commentList;

}
