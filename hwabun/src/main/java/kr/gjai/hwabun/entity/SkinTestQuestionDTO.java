package kr.gjai.hwabun.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SkinTestQuestionDTO {
	

	private int que_seq; // 질문 번호 PK
	private int que_num; //질문 순서
	private String que_type; // 질문 유형 ex) 유분형, 수분형 등등 
	private String que_content; // 질문 내용
	private String que_img; // 질문 가운데 이미지
	
	
}
