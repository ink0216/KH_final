package kh.em.albaya.shop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shop {
	private int shopNo;
	private int dongNo;
	private String shopName;
	private String shopEmail;
	private String shopPw;
	private String shopTel;
	private String shopBrn;
	private String shopOwner;
	private String shopProfile;
	private String addressDetail;
}
