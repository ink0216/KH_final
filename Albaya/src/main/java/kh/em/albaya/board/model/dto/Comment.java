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
	
	private int commentNo;
	private String commentContent;
	private String commentWriteDate;
	private String commentDelFl;
	private int reviewBoardNo;
	private int memberNo;
	private int parentCommentNo;

}
