package kr.gjai.hwabun.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SkinMBTIDTO {
// 테이블 생성하지 않고 페이지에 뿌릴 정보 DTO
	
	
	private String mbti; // 피부 mbti 유형
	private String skin_simple; // mbti간단설명 
	private String skin_result; // mbti결론
	private String skin_strength; //mbti강점
	private String skin_weakness; //mbti약점
	private String skin_solution; // mbti해결책
	private String mbti_color1; // mbti색깔1 강한색
	private String mbti_color2; // mbti색깔2 연한색
	
	
}
