package kr.gjai.hwabun.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.gjai.hwabun.entity.CosmeticsDTO;
import kr.gjai.hwabun.service.RankService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class RankController {
	
	@Autowired
	RankService rankService;
	// 기본 랭킹 화면
	@GetMapping("rank")
	public String getAllProduct(Model model){
		
			List<CosmeticsDTO> list = rankService.getAllProduct();
			model.addAttribute("list", list);
			return "rank/rank";
	}
	@ResponseBody
	@PostMapping("rankCate")
	public List<CosmeticsDTO> getAllProduct(Model model, String nowAddr, String cateName) {
		log.info(cateName);
		
		nowAddr = nowAddr.substring(22, nowAddr.length());
		log.info(nowAddr);
		List<CosmeticsDTO> list = null;
		if(nowAddr.equals("rank_sales")) {
			list= rankService.rank_sales(cateName);
		}
		else if(nowAddr.equals("rank_reviews")) {
			list= rankService.rank_reviews(cateName);
		}
		
		else if(nowAddr.equals("rank_likes")){
			list= rankService.rank_likes(cateName);
		}
		else {
			log.info("예외발생!!");	
		}
		return list;
	}
	// 판매 랭킹
	@GetMapping("rank_sales")
	public String getRankSales(Model model, String cateName){
		List<CosmeticsDTO> list = rankService.getRankSales();
		model.addAttribute("list", list);
		log.info(list.get(0));
		return "rank/rank";
	}
	
	// 좋아요 랭킹
	@GetMapping("rank_likes")
	public String getRankLikes(Model model, String cateName){
		List<CosmeticsDTO> list = rankService.getRankLikes();
		model.addAttribute("list", list);
		return "rank/rank";
	}
	
	// 리뷰순 랭킹
	@GetMapping("rank_reviews")
	public String getRankReviews(Model model) {
		List<CosmeticsDTO> list = rankService.getRankReviews();
		model.addAttribute("list", list);
		return "rank/rank";
	}
	
//	@GetMapping("cateSalesRank")
//	public String cateSalesRank(String cateName, Model model) {
//		List<CosmeticsDTO> list = rankService.cateSalesRank(cateName);
//		log.info(list.get(0));
//		return "rank/rank";
//	}
	
	
}
