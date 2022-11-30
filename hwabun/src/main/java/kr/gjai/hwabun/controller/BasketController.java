package kr.gjai.hwabun.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.gjai.hwabun.entity.MemberDTO;
import kr.gjai.hwabun.entity.TempBasketDTO;
import kr.gjai.hwabun.service.BasketService;

@Controller
public class BasketController {

	@Autowired
	BasketService basketService;
	
	
	@ResponseBody
	@RequestMapping("/saveBasket")
	public int cartCosmetics(@RequestParam(value="cos_seq") int cos_seq,
								@RequestParam(value="cnt") int cnt,
								HttpSession session
								)
	
	
	{
		MemberDTO mvo = (MemberDTO)session.getAttribute("mvo");
		
		String nickname = mvo.getMb_id();
		
		System.out.println(cos_seq);
		System.out.println(nickname);
		
		basketService.saveBasket(cos_seq,nickname,cnt);
		int updatedcnt=basketService.countBasket(nickname);
		
		return updatedcnt;
			
	}
	
	
	
	
	
	
	@RequestMapping("/shop-cart.html")
	public String showBasket(Model model,HttpSession session){
		
		
		
		MemberDTO mb=(MemberDTO)session.getAttribute("mvo");		
		
		System.out.println(mb);
	
		List<TempBasketDTO> tlist=basketService.showBasket(mb.getMb_id());
	
		System.out.println(tlist);
		
		model.addAttribute("Basket",tlist);
		
		return "cart/cart.html";
		
	}
	
	
	
	
	@ResponseBody
	@RequestMapping("/plusCnt")
	public String plusCnt(@RequestParam(value="cos_seq") int cos_seq,
			HttpSession session)
	{
		
		MemberDTO mb=(MemberDTO)session.getAttribute("mvo");
		
		basketService.plusCnt(cos_seq,mb.getMb_id());
		
		
	
		
		return "cart/cart";
	}
	
	@ResponseBody
	@RequestMapping("/minusCnt")
	public String minusCnt(@RequestParam(value="cos_seq") int cos_seq,HttpSession session
							)
	{
		MemberDTO mb=(MemberDTO)session.getAttribute("mvo");
		
		
		basketService.minusCnt(cos_seq,mb.getMb_id());
		
		
	
		
		return "cart/cart";
	}
	
	
	
	
	@ResponseBody
	@RequestMapping("/throwSeq")
	public String throwSeq(@RequestParam(value="cos_seq") int cos_seq,
							HttpSession session)
	{
		
		MemberDTO mb=(MemberDTO)session.getAttribute("mvo");
		
		basketService.throwSeq(cos_seq,mb.getMb_id());
		
		System.out.println(cos_seq);

		System.out.println(mb.getMb_id());
		
		return "cart/cart";
	}
	
	
	
	
	

	
	
	
	
	
	
	@ResponseBody
	@RequestMapping("/clearCart")
	public String clearCart(HttpSession session)
	{
		
		MemberDTO mb=(MemberDTO)session.getAttribute("mvo");
		
		basketService.clearCart(mb.getMb_id());
		
		
		
		return "cart/cart";
	}
	
	
	
	
	@ResponseBody
	@RequestMapping("/changeCnt")
	public String changeCnt(@RequestParam(value="cos_seq") int cos_seq,
							HttpSession session ,
							@RequestParam(value="cnt") int cnt)
	{
		
		MemberDTO mb=(MemberDTO)session.getAttribute("mvo");
		
		basketService.changeCnt(cos_seq,mb.getMb_id(),cnt);
		
		
		
		return "cart/cart";
	}
	
	
	
	
	@ResponseBody
	@RequestMapping("/countBasket")
	public int countBasket(@RequestParam(value="count") String count,HttpSession session){
		
		System.out.println(count);
		MemberDTO mb=(MemberDTO)session.getAttribute("mvo");
		return basketService.countBasket(mb.getMb_id());
		

		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
