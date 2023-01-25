package kr.gjai.hwabun;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import kr.gjai.hwabun.service.TestService;
import lombok.extern.log4j.Log4j2;


@SpringBootTest
@Log4j2
class HwabunApplicationTests {

	@Autowired
	TestService testService;

	
	

	@Test
	public void test() {
		
		
	}
	
}
