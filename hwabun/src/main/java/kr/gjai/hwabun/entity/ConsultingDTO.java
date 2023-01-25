package kr.gjai.hwabun.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConsultingDTO {
	
	private int consulting_seq; // 1:1컨설팅 순번
	private String mb_id; // 회원 아이디
	private String consulting_date; // 컨설팅 예약날짜
	private String consulting_id; // 컨설팅 담당자 default BSJ

}
