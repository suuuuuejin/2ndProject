package kr.gjai.hwabun.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberIngredientsDTO {

	private int mi_seq; // 멤버 성분 번호 PK
	private String mb_id; // 멤버 아이디
	private int ingredient_seq; // 성분 번호 FK 
}
