package kh.em.albaya.board.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentDeclare {
	
	private int commentDeclareNo; 			// 댓글 신고 번호
	private int commentNo; 					// 댓글 번호
	private int memberNo; 					// 신고한 회원 번호
	private String commentDeclareContent; 	// 댓글 신고 사유
	private String commentDeclareCondition; // 댓글 신고 처리
	private String commentDeclareDate; 		// 댓글 신고일
	private int reportedMemberNo; 			// 신고 당한 회원번호
	private String declareBoardCode; 		// 댓글 신고 유형
	private String commentContent; 			// 댓글 내용
	

}
