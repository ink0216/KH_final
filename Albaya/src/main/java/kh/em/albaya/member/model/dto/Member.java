package kh.em.albaya.member.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Member {
	private int memberNo;
	private int authorityNo;
	private String memberEmail;
	private String memberPw;
	private String memberPhoneNumber;
	private String enrollDate;
	private String memberName;
	private int memberStatus;
	private int memberGender;
	private String memberAddress;
	private String 	lastModifiedDate;
	private String lastModifiedPwDate;
	private String suspendPeriod;
}
