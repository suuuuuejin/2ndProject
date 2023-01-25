package kr.gjai.hwabun.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.gjai.hwabun.entity.MemberDTO;
import kr.gjai.hwabun.entity.OrderDTO;
import kr.gjai.hwabun.entity.OrderDetailDTO;
import kr.gjai.hwabun.entity.ReviewDTO;
import kr.gjai.hwabun.service.MyPageService;

@Controller
public class MyPageController {
	
	@Autowired
	MyPageService myPageService;
	
	@GetMapping("/myaccount-info")
	public String AccountInfo(HttpSession session,Model model) {
		

		MemberDTO mb=(MemberDTO)session.getAttribute("mvo");		
	
		List<MemberDTO> ulist=myPageService.getInfo(mb.getMb_id());
		model.addAttribute("info",ulist.get(0));
		
		return "mypage/page-member";
	}
	
	@ResponseBody
	@RequestMapping("/changePwd")
	public void changePwd(@RequestParam(value="pwd") String pwd, HttpSession session) {
		
		MemberDTO mb=(MemberDTO)session.getAttribute("mvo");
		myPageService.changePwd(mb.getMb_id(),pwd);
		
	}

	
	
	
	@GetMapping("/myaccount-reviews")
	public String AccountReviews(HttpSession session,Model model){
		
		MemberDTO mb=(MemberDTO)session.getAttribute("mvo");
		List<ReviewDTO> rlist=myPageService.showReview(mb.getMb_id());
		model.addAttribute("rlist",rlist);
		return "mypage/page-reviews";
	}
	
	@GetMapping("/myaccount-order")
	public String AccountOrder(HttpSession session,Model model) {
		
		MemberDTO mb=(MemberDTO)session.getAttribute("mvo");	
		
		List<OrderDTO> orders=myPageService.showOrders(mb.getMb_id());
		
		model.addAttribute("orders",orders);
		
		return "mypage/page-orders-detail";
	
	
	}
	
	@GetMapping("/myaccount-recpurview")
	public String Recpurview(HttpSession session,Model model) {
		
		MemberDTO mb=(MemberDTO)session.getAttribute("mvo");	
		model.addAttribute("recreviews",myPageService.showRReview(mb.getMb_id()));
	
		return "mypage/page-recpurview";
	
	
	}
	
	@ResponseBody
	@RequestMapping("/showDetail")
	public String ShowDetail(@RequestParam(value="number") int number) throws JsonProcessingException {
		
		
		ObjectMapper mapper = new ObjectMapper();
		
		List<OrderDetailDTO> detail=new ArrayList<OrderDetailDTO>(myPageService.showDetail(number));
		
		String orderdetail=mapper.writeValueAsString(detail);
		
		return orderdetail;
		 
		
		
	}
	
	@ResponseBody
	@RequestMapping("/saveRReview")
	public void SaveRReview(@RequestParam(value="recoseq") int recoseq,@RequestParam(value="content") String content,HttpSession session) {
		
		MemberDTO mb=(MemberDTO)session.getAttribute("mvo");	
		
		myPageService.saveRReview(mb.getMb_id(),recoseq,content);
		
		
		
		
	
	
	}
	
	
	
}
