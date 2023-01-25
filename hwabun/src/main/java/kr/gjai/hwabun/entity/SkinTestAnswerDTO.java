package kr.gjai.hwabun.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SkinTestAnswerDTO {

	private int ans_seq; // 답변 번호 PK
	private String mb_id; //회원 아이디 FK
	private int que_seq; // 질문 번호 FK
	private String que_type; // 질문 유형
	private float ans_value; // 답변 값
	private String skintest_cnt; // 테스트 진행 횟수
	
	
	private List<SkinTestAnswerDTO> ansList; // 답변 리스트로 받기 위한 DTO안에 리스트 생성
	
}
