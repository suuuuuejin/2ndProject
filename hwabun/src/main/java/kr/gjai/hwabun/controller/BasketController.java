package kr.gjai.hwabun.controller;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import kr.gjai.hwabun.entity.EventDTO;
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
								HttpSession session,EventDTO edo
								)
	{
		
		
		if(session.getAttribute("mvo") != null) {
		
		MemberDTO mvo = (MemberDTO)session.getAttribute("mvo");
		
		String nickname = mvo.getMb_id();
		
	
		
		basketService.saveBasket(cos_seq,nickname,cnt);
		int updatedcnt=basketService.countBasket(nickname);
		
		
		// 행동 이벤트 양식
					edo.setCos_seq(cos_seq);
					edo.setUser_id(mvo.getMb_id());
					edo.setUser_session(session.getId());
					basketService.registerEvent(edo);
		
		return updatedcnt;
		}
		
		
		
		else {
			
			return 0;
			
		}
		
		
			
	}
	
	
	
	
	
	
	@RequestMapping("/goBasket")
	public String showBasket(Model model,HttpSession session){
		
		
		if(session.getAttribute("mvo") != null) {
		MemberDTO mb=(MemberDTO)session.getAttribute("mvo");		
		
	
		List<TempBasketDTO> tlist=basketService.showBasket(mb.getMb_id());
		
		model.addAttribute("Basket",tlist);
		}
		return "cart/cart";
		
	}
	
	
	
	
	@ResponseBody
	@RequestMapping("/plusCnt")
	public String plusCnt(@RequestParam(value="cos_seq") int cos_seq,
			HttpSession session)
	{
		if(session.getAttribute("mvo") != null) {
		MemberDTO mb=(MemberDTO)session.getAttribute("mvo");
		
		basketService.plusCnt(cos_seq,mb.getMb_id());
		
		
		}
		
		return "cart/cart";
	}
	
	@ResponseBody
	@RequestMapping("/minusCnt")
	public String minusCnt(@RequestParam(value="cos_seq") int cos_seq,HttpSession session
							)
	{
		if(session.getAttribute("mvo") != null) {
		MemberDTO mb=(MemberDTO)session.getAttribute("mvo");
		
		
		basketService.minusCnt(cos_seq,mb.getMb_id());
		
		}
	
		
		return "cart/cart";
	}
	
	
	
	
	@ResponseBody
	@RequestMapping("/throwSeq")
	public String throwSeq(@RequestParam(value="cos_seq") int cos_seq,
							HttpSession session, EventDTO edo)
	{
		if(session.getAttribute("mvo") != null) {
		MemberDTO mb=(MemberDTO)session.getAttribute("mvo");
		
		basketService.throwSeq(cos_seq,mb.getMb_id());
		
		edo.setCos_seq(cos_seq);
		edo.setUser_id(mb.getMb_id());
		edo.setUser_session(session.getId());
		basketService.dropEvent(edo);
		}
		
		return "cart/cart";
	}
	
	
	
	
	
	
	
	
	
	
	
	@ResponseBody
	@RequestMapping("/clearBasket")
	public String clearCart(HttpSession session)
	{
		
		if(session.getAttribute("mvo") != null) {
		MemberDTO mb=(MemberDTO)session.getAttribute("mvo");
		
		basketService.clearCart(mb.getMb_id());
		
		}
		
		return "cart/cart";
	}
	
	
	
	
	@ResponseBody
	@RequestMapping("/changeCnt")
	public String changeCnt(@RequestParam(value="cos_seq") int cos_seq,
							HttpSession session ,
							@RequestParam(value="cnt") int cnt)
	{
		if(session.getAttribute("mvo") != null) {
		MemberDTO mb=(MemberDTO)session.getAttribute("mvo");
		
		basketService.changeCnt(cos_seq,mb.getMb_id(),cnt);
		
		}
		
		return "cart/cart";
	}
	
	
	@ResponseBody
	@RequestMapping("/countBasket")
	public int countBasket(HttpSession session){
		
		
		
		if(session.getAttribute("mvo")!=null) {
		
		MemberDTO mb=(MemberDTO)session.getAttribute("mvo");
		return basketService.countBasket(mb.getMb_id());
		}else {
			
			return 0;
		}
		
		
	}
	
	
	@RequestMapping("/willPurchase")
	public String willPurchase(@RequestParam(value="pchase") String pchase,HttpSession session,Model model){
		
		
		
		MemberDTO mb=(MemberDTO)session.getAttribute("mvo");
		
		model.addAttribute("pitem",basketService.willPurchase(mb.getMb_id(),pchase, session.getId()));
		
		return "cart/purchase";
		
	}
	
}