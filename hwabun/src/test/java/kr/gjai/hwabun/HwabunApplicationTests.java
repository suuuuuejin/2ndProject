package kr.gjai.hwabun;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.gjai.hwabun.entity.MemberDTO;
import kr.gjai.hwabun.service.TestService;

@SpringBootTest
class HwabunApplicationTests {

	@Autowired
	TestService testService;
	
	@Test
	public void getList() {
		List<MemberDTO> list = testService.getList();
		System.out.println(list);
	}

}
