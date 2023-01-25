package kr.gjai.hwabun.controller;

import java.io.IOException;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestPart;
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
	public String getProduct(Model model, @RequestParam("cos_seq") int cos_seq, HttpServletRequest request,
			EventDTO edo, HttpSession session) {
		// 제품 상세정보 가져오기
		CosmeticsDTO cdto = productDetailService.getProduct(cos_seq);
		model.addAttribute("cdto", cdto);
		// 세부상품을 view 했다는 이벤트 로그 저장
		session = request.getSession();
		if (session.getAttribute("mvo") != null) {
			MemberDTO mvo = (MemberDTO) session.getAttribute("mvo");

			edo.setCos_seq(cos_seq);
			edo.setUser_id(mvo.getMb_id());
			edo.setUser_session(session.getId());
			productDetailService.registerEvent(edo);
			
		}
		
		// 고객 평점 가져오기
		List<StarDTO> sdto = productDetailService.getStars(cos_seq);
		model.addAttribute("sdto", sdto);
		
			
		
		return "product/product-detail";
	}

	// 리뷰가져오기
	@GetMapping("/reviewList")
	@ResponseBody
	public List<ReviewDTO> getReviews(Model model, @RequestParam("cos_seq") int cos_seq) {
		List<ReviewDTO> rdto = productDetailService.getReviews(cos_seq);
//		 model.addAttribute("rdto", rdto);
		return rdto;
	}

	// 리뷰 등록
	@PostMapping("/commentWrite")
	@ResponseBody
	public void insertReview(ReviewDTO reviewDTO, @RequestPart(value = "files", required = false) MultipartFile files)
			throws IllegalStateException, IOException {
		System.out.println("comment실행");
		System.out.println(reviewDTO);
		System.out.println(files);
		log.info(reviewDTO);
		/* MultipartFile file = reviewDTO.getFile(); */
		if (files != null) {
			productDetailService.insertReview(reviewDTO, files);
		} else {
			productDetailService.cInsertReview(reviewDTO);
		}
	}

	@PutMapping("/commentUpdate")
	@ResponseBody
	public void updateReview(ReviewDTO reviewDTO) {

		productDetailService.updateReview(reviewDTO);

	}

	@DeleteMapping("/commentDelete")
	@ResponseBody
	public int deleteReview(ReviewDTO reviewDTO) {
		int cnt = productDetailService.deleteReview(reviewDTO);
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
	// 좋아요
	@GetMapping("/like")
	@ResponseBody
	public int like(@RequestParam("review_seq") int review_seq) {

		int cnt = productDetailService.like(review_seq);

		return cnt;

	}
	/*
	 * @GetMapping("/productLike")
	 * 
	 * @ResponseBody public HashMap<String, Object> ProductLike(ReviewDTO reviewDTO,
	 * HashMap<String, Object> map) {
	 * 
	 * 
	 * int result = productDetailService.likeCheck(reviewDTO); if(result==0) {
	 * productDetailService.likeUpdate(reviewDTO); }else {
	 * productDetailService.likeDelete(reviewDTO); } int cnt =
	 * productDetailService.ProductLike(reviewDTO);
	 * 
	 * int likes = productDetailService.likeCount(reviewDTO.); result =
	 * productDetailService.likeCheck(reviewDTO);
	 * 
	 * 
	 * map.put("likes", likes); map.put("result", result);
	 * 
	 * 
	 * return map;
	 * 
	 * }
	 */
	/*
	 * List<ReviewDTO> rdto = productDetailService.getReviews(cos_seq);
	 * 
	 * 
	 * HashMap<String, Object> map = HashMap<String, Object>(); map.put("userVO",
	 * userVO); return map; }
	 * 
	 * public HashMap<String, Object> init(@RequestBody UserVO userVO) {
	 * 
	 * HashMap<String, Object> map = HashMap<String, Object>(); 
	 * map.put("VO",VO);
	 * m.put("md_id",md_id);
	 * return map;
	 * 
	 * int zw_seq = Integer.parseInt(request.getParameter("zw_seq")); String
	 * login_id = request.getParameter("login_id"); HttpSession session =
	 * request.getSession(); Member mo = (Member)session.getAttribute("mvo"); String
	 * login_id =mo.getLogin_id();
	 * 
	 * Map<String,Object> m = new HashMap<>(); m.put("no",zw_seq);
	 * m.put("id",login_id);
	 * 
	 * BoardMyBatisDAO dao = new BoardMyBatisDAO(); int result = dao.likeCheck(m);
	 * if(result==0) { dao.likeUpdate(m); }else { dao.likeDelete(m); }
	 * 
	 * int likes = dao.likeCount(zw_seq); PrintWriter out = response.getWriter();
	 * result = dao.likeCheck(m); Map<String,Object> m2 = new HashMap<>();
	 * m2.put("likes", likes); m2.put("result", result);
	 * 
	 * List<Map> list = new ArrayList<Map>(); list.add(m2); Gson gson = new Gson();
	 * String json = gson.toJson(list);
	 * response.setContentType("text/json;charset=utf-8"); out.println(json);
	 */

}
