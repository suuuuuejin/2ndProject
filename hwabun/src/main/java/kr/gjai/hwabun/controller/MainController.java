package kr.gjai.hwabun.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.gjai.hwabun.entity.CosmeticsDTO;
import kr.gjai.hwabun.service.RankService;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class MainController {
	@Autowired
	RankService rankService;
	
	@RequestMapping({"/","/main"})
	public void main(Model model) {
		log.info("main 확인!!");
		//판매순으로 가져오기
		List<CosmeticsDTO> rp = rankService.getRankSales();
		model.addAttribute("rp", rp);
		//좋아요순으로 가져오기
		List<CosmeticsDTO> lp = rankService.getRankLikes();
		model.addAttribute("lp", lp);
		
		
		model.addAttribute("main","main");
		
	}
	
	
	
	
	
}
