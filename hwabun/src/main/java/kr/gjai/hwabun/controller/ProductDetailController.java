package kr.gjai.hwabun.controller;

import java.io.IOException;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import kr.gjai.hwabun.entity.CosmeticsDTO;
import kr.gjai.hwabun.entity.EventDTO;
import kr.gjai.hwabun.entity.MemberDTO;
import kr.gjai.hwabun.entity.ReviewDTO;
import kr.gjai.hwabun.entity.StarDTO;
import kr.gjai.hwabun.service.ProductDetailService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class ProductDetailController {
	
	@Autowired
	ProductDetailService productDetailService;
	
	@GetMapping("/productDetail")
	public String getProduct(Model model, @RequestParam("cos_seq") int cos_seq, HttpServletRequest request, EventDTO edo,HttpSession session) {
		CosmeticsDTO cdto = productDetailService.getProduct(cos_seq);

		
		model.addAttribute("cdto", cdto);
		System.out.println(cdto);
		// 세부상품을 view 했다는 이벤트 로그 저장
		session = request.getSession();
		if(session.getAttribute("mvo")!=null) {
		MemberDTO mvo = (MemberDTO) session.getAttribute("mvo");
		

		edo.setCos_seq(cos_seq);
		edo.setUser_id(mvo.getMb_id());
		edo.setUser_session(session.getId());
		productDetailService.registerEvent(edo);
		}

		/*
		 * List<ReviewDTO> rdto = productDetailService.getReviews(cos_seq);
		 * model.addAttribute("rdto", rdto);
		 */
		List<StarDTO> sdto = productDetailService.getStars(cos_seq);
		model.addAttribute("sdto", sdto);
		System.out.println(sdto);
		return "product/product-detail";
	}
	//리뷰가져오기
	@GetMapping("/reviewList")
	@ResponseBody
	public List<ReviewDTO> getReviews(Model model, @RequestParam("cos_seq") int cos_seq) {
		 System.out.println("/reviewList");
		 List<ReviewDTO> rdto = productDetailService.getReviews(cos_seq);
//		 model.addAttribute("rdto", rdto);
		 System.out.println(rdto);
		 return rdto;
	}
	@PostMapping("/commentWrite")
	@ResponseBody
	public void insertReview(ReviewDTO reviewDTO, MultipartFile file) throws IllegalStateException, IOException {
		System.out.println("/commentWrite");
		productDetailService.insertReview(reviewDTO,file);
		
	}
	@PutMapping("/commentUpdate")
	@ResponseBody
	public void updateReview(ReviewDTO reviewDTO,MultipartFile file) {
		System.out.println("/commentWrite");
		productDetailService.updateReview(reviewDTO,file);
		
	}
	@DeleteMapping("/commentDelete")
	@ResponseBody
	public int deleteReview(ReviewDTO reviewDTO) {
		System.out.println("/commentWrite");
		int cnt = productDetailService.deleteReview(reviewDTO);
		System.out.println(cnt);
		return cnt;
	}
	
	
//	리뷰등록
	/*
	 * @PostMapping("/product-review")
	 * 
	 * @ResponseBody public String getProductReview(Model model, @RequestParam ) {
	 * ReviewDTO result = new ReviewDTO(); if(paramMap.get("mb_id").toString()!=
	 * null) { result.setMb_id(paramMap.get("mb_id").toString());
	 * result.setReview_content(paramMap.get("review_content").toString());
	 * result.setReview_rating((float)paramMap.get("review_rating"));
	 * System.out.println(result); int rs =
	 * productDetailService.insertReview(result); model.addAttribute("rs", rs); } //
	 * 수정&삭제 버튼 게시를 위한 유저 정보 전달 Map<String, Object> userInform = new HashMap<String,
	 * Object>(); userInform.put("userEmail", paramMap.get("email"));
	 * model.addAttribute("userInform", userInform);
	 * 
	 * return "/product/product-detail :: #commentTable"; }
	 */

}
