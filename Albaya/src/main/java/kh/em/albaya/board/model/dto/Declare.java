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
	
	private int reviewBoardDeclareNo;
	private int reviewBoardNo;
	private int memberNo;
	private String boardDeclareContent;
	private String reviewBoardCondition;
	private String boardDeclareDate;
	private int reportedMemberNo;

}
