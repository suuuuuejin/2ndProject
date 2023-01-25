package kr.gjai.hwabun.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.gjai.hwabun.entity.MemberDTO;
import kr.gjai.hwabun.service.TestService;

@Controller
public class TestController {
	
	@Autowired
	TestService testService;
	
	@GetMapping("/test")
	public String getList(Model model) {
		List<MemberDTO> list = testService.getList();
		model.addAttribute("list", list);
		return "sample/test2";
	}
	

	@GetMapping("/t")
	public String test() {
		return "/vmain";

	}
	
	@GetMapping("/hn")
	public String test_hn() {
		return "/customizing/test";

	}
	
	
}
