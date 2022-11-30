package kr.gjai.hwabun.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String getAllProduct(Model model) {
		List<CosmeticsDTO> list = rankService.getAllProduct();
		model.addAttribute("list", list);
		return "rank/rank";
	}
	
	// 판매 랭킹
	@GetMapping("rank_sales")
	public String getRankSales(Model model){
		List<CosmeticsDTO> list = rankService.getRankSales();
		model.addAttribute("list", list);
		return "rank/rank";
	}
	
	// 좋아요 랭킹
	@GetMapping("rank_likes")
	public String getRankLikes(Model model){
		List<CosmeticsDTO> list = rankService.getRankLikes();
		model.addAttribute("list", list);
		return "rank/rank";
	}
	
	// 카테고리별 판매랭킹
	@GetMapping("rank_cate_sales")
	public String getCateRankSales(@RequestParam(value="cate") String cate, Model model) {
		List<CosmeticsDTO> list = rankService.getCateRankSales(cate);
		model.addAttribute("list", list);
		return "rank/rank";
	}
	
	// 카테고리별 좋아요 랭킹
	@GetMapping("rank_cate_likes")
	public String getCateRankLikes(@RequestParam(value="cate") String cate, Model model) {
		List<CosmeticsDTO> list = rankService.getCateRankLikes(cate);
		model.addAttribute("list", list);
		return "rank/rank";
	}
	
	
}
