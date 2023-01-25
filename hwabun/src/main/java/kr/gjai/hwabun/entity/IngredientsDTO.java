package kr.gjai.hwabun.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IngredientsDTO {
	
	
	private int ingredient_seq; // 성분 번호 PK
	private String ingredient_type; // 성분 유형 (ex 알러지 유해 유익 선호)
	private String ingredient_name; // 성분 이름
	private String ingredient_eng; // 성분 영어명
	
	
}
