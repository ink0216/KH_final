package kh.em.albaya.resume.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Career {

	private int resumeNo;
	private String companyName;
	private String startDate; //근무 시작 날짜
	private String endDate; // 근무 종료 날짜
	private String chargeOf; //담당 업무
}
