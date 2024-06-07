package kh.em.albaya.board.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Declare {
	
	private int reviewBoardDeclareNo; 	 // 게시글 신고 번호
	private int reviewBoardNo; 			 // 신고당한 게시글 번호
	private int memberNo; 				 // 신고한 회원번호
	private String boardDeclareContent;  //게시글 신고 사유
	private String reviewBoardCondition; // 처리 상태
	private String boardDeclareDate;	 // 신고 날짜
	private int reportedMemberNo; 		 // 신고당한 회원 번호
	private int declareBoardCode; 		 
	private int declareDelFl;

}
