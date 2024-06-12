package kh.em.albaya.location.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Dong {
	private int dongNo;
	private int sigunguNo;
	private String dongName;
	private String sigunguName;
	private String dosiName;
}
