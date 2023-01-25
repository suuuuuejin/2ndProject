package kr.gjai.hwabun.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.gjai.hwabun.entity.CosmeticsDTO;
import kr.gjai.hwabun.service.CateService;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class CateController { // 대분류 상품 조회 컨트롤러
	
	@Autowired
	CateService cateService;
	
	// 중분류 카테고리로 가는 컨트롤러
	@GetMapping("/getProductList")
	// 클라이언트에게 중분류를 입력받는다
	public String getProductList(@RequestParam(value="cate") String cate,Model model) {
		// 중분류 상품 리스트 받아오는 구문
		List<CosmeticsDTO> list = cateService.getProductList(cate);
		// 소분류 카테고리 받아오는 구문
		List<CosmeticsDTO> cate2_list = cateService.getCate(cate);
		log.info(list.get(0));
		model.addAttribute("cos_list", list);
		model.addAttribute("cate_list", cate2_list);
		model.addAttribute("cate", cate);
		return "product/product-list";
	}
	
	// 소분류 카테고리로 가는 컨트롤러
	@GetMapping("/getProductList2")
	public String getProductList2(@RequestParam(value="cate1") String cate1,@RequestParam(value="cate2") String cate2, Model model) { // 대분류 입력받는다
		List<CosmeticsDTO> list = cateService.getProductList2(cate1, cate2);
		List cate2_list = cateService.getCate(cate1);
		model.addAttribute("cos_list", list);
		model.addAttribute("cate_list", cate2_list);
		model.addAttribute("cate", cate1);
		System.out.println(list.get(0));
		return "product/product-list2";
	}
	
	// 페이지 확인용 컨트롤러
	@GetMapping("/1")
	public String test1() {
		return("rank/rank");
	}
	
}
