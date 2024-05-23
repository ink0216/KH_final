package kh.em.albaya.board.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Comment {
	
	private int commentNo; 				// 댓글 번호
	private int reviewBoardNo;			// 게시글 번호
	private int memberNo;				// 회원 번호
	private int parentCommentNo;		// 부모댓글 번호
	private String commentContent;		// 댓글 내용
	private String commentWriteDate;	// 댓글 작성일
	private String commentDelFl;		// 댓글 삭제 여부
	
	// 댓글 조회시 작성자 이메일(MEMBER테이블 조인)
	private String memberEmail;

}
