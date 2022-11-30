package kr.gjai.hwabun.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.gjai.hwabun.entity.MemberDTO;
import kr.gjai.hwabun.service.MemberService;
import lombok.extern.log4j.Log4j2;

@Controller

@Log4j2
public class MemberController {
	
	@Autowired
	MemberService memberService;

	// 회원가입 폼 페이지로 
	@GetMapping("/join")
	public String join() {
		log.info("join..........!!!!!");
		
		return "member/join";
	}
	@RequestMapping("/idCheck")
	@ResponseBody
	public String idCheck(String mb_id) {
		log.info(mb_id);
		String idCheckResult = memberService.idCheck(mb_id);
		log.info(idCheckResult);
		return idCheckResult;
	}
	
	// 회원가입 폼 작성 후 
	@PostMapping("/join")
	public String join(MemberDTO mdo, HttpSession session) {

		log.info(mdo);
		memberService.register(mdo);	
		log.info("회원가입성공!!");
		MemberDTO mvo = memberService.getMemInfo(mdo);
		session.setAttribute("mvo", mvo);
		
		return "redirect:/main";
	}
	
	// 로그인 페이지로
	@RequestMapping("/login")
	public String goLogin() {
		return "/member/login";
	}
	
	// 로그인 
	@RequestMapping("/login.do")
	public String loginCheck(MemberDTO mdo, HttpSession session) {
		log.info(mdo.getMb_id());
		log.info(mdo.getMb_pw());

		MemberDTO mvo = memberService.getMemInfo(mdo);
		if(mvo != null) {
			session.setAttribute("mvo", mvo);
			log.info(session.getAttribute("mvo"));
			log.info(session.getId());
			log.info("로그인 완료");

		}
		return "redirect:/main";
	}
	
	// 로그아웃
	@RequestMapping("/logout.do")
	public String logOut(HttpSession session) {
		log.info("로그아웃~~~~~~~~");
		session.invalidate();
		return "redirect:/main";
	}
	
}
