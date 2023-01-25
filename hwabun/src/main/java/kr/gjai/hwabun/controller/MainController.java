package kr.gjai.hwabun.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.gjai.hwabun.entity.CosmeticsDTO;
import kr.gjai.hwabun.entity.MemberDTO;
import kr.gjai.hwabun.entity.UserSkinDTO;
import kr.gjai.hwabun.service.GraphService;
import kr.gjai.hwabun.service.RankService;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class MainController {
	@Autowired
	RankService rankService;
	@Autowired
	GraphService graphService;
	
	@RequestMapping({"/main"})
	public void main(Model model, HttpSession session) {
		log.info("main 확인!!");
		//판매순으로 가져오기
		List<CosmeticsDTO> sp = rankService.getRankSales();
		model.addAttribute("sp", sp);
		
		//좋아요순으로 가져오기
		List<CosmeticsDTO> lp = rankService.getRankLikes();
		model.addAttribute("lp", lp);
		
		//리뷰랭킹순으로 가져오기
		List<CosmeticsDTO> rp = rankService.getRankReviews();
		model.addAttribute("rp", rp);
		
		model.addAttribute("main","main");
		// mbti 횟수별 그래프
		MemberDTO mb=(MemberDTO)session.getAttribute("mvo");
		if(mb!=null) {
		List<UserSkinDTO> mlg = graphService.getMbtiGraph(mb.getMb_id());
		log.info(mlg);
		model.addAttribute("mlg", mlg);
		// 같은mbti사람들이 구매한 그래프
		List<CosmeticsDTO> smg = graphService.getSameMbtiGraph(mb.getMb_id());
		model.addAttribute("smg", smg);
		//같은 MBTI 사람들이 구매한 상품
		List<CosmeticsDTO> smp = graphService.getSameMbtiProduct(mb.getMb_id());
		System.out.println(smp);
		System.out.println(smp.size());
		model.addAttribute("smp", smp);
		}else {
			String id="mb_id 1";
			List<UserSkinDTO> mlg = graphService.getMbtiGraph(id);
			log.info(mlg);
			model.addAttribute("mlg", mlg);
			
			
		}
		
		//20대가 구매한 상품
		List<CosmeticsDTO> twp = graphService.getTwProduct();
		model.addAttribute("twp", twp);
		//30대가 구매한 상품
		List<CosmeticsDTO> thp = graphService.getThProduct();
		model.addAttribute("thp", thp);
		
		//로그인안했을때
		// OSPW가 구매한 그래프
				String id = "mb_id 1";
				List<CosmeticsDTO> mbtiG = graphService.getSameMbtiGraph(id);
				model.addAttribute("mbtiG", mbtiG);
				//같은 MBTI 사람들이 구매한 상품
				List<CosmeticsDTO> mbtiP = graphService.getSameMbtiProduct(id);
				System.out.println(mbtiG);
				System.out.println(mbtiP.size());
				model.addAttribute("mbtiP", mbtiP);
		
	}	
	
}
