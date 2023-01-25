
package kr.gjai.hwabun.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kr.gjai.hwabun.entity.BasketDTO;
import kr.gjai.hwabun.entity.CosmeticsDTO;
import kr.gjai.hwabun.entity.EventDTO;
import kr.gjai.hwabun.entity.TempBasketDTO;
import kr.gjai.hwabun.mapper.BasketMapper;
@Service
public class BasketService {
	
	@Autowired
	BasketMapper basketMapper;
	
	
	public CosmeticsDTO getCosmetics(int cos_seq) {
		
		
		return basketMapper.getCosmetics(cos_seq);
		
		
	}
	
	
	
	
	
	public List<CosmeticsDTO> showCosmetics(){
		
		
		return basketMapper.showCosmetics();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	public List<TempBasketDTO> showBasket(String nickname){
		
		
		return basketMapper.showBasket(nickname);
		
	}
	
	
	
	
	
	
	public void saveBasket(int cos_seq, String nickname, int cnt) {
		// TODO Auto-generated method stub
	
		
		int knn=-1;
		List<BasketDTO> tlist=basketMapper.showBasket1(nickname);
		int bn=basketMapper.countBasket(nickname);
		
		
		
		for(int i=0;i<bn;i++) {
		
			if( cos_seq==tlist.get(i).getCos_seq() ) {
				
				
				knn=tlist.get(i).getCos_seq();
				cos_seq=knn;
				break;
				
			}
			
		
		}
		
		
		if(knn==-1) {
			
			
			basketMapper.insertBasket(cos_seq,nickname,cnt);
			
			
		}else {
			
		
			basketMapper.updateBasket(cos_seq,nickname,cnt);
			
		}
		
		
		
		
	}
	public void plusCnt(int cos_seq, String nickname) {
		basketMapper.plusCnt(cos_seq,nickname);
		
	}
	public void minusCnt(int cos_seq, String nickname) {
		basketMapper.minusCnt(cos_seq,nickname);
		
	}
	public void throwSeq(int cos_seq, String nickname) {
		basketMapper.throwSeq(cos_seq,nickname);
		
	}
	public void clearCart(String nickname) {
		basketMapper.clearCart(nickname);
		
	}
	public void changeCnt(int cos_seq, String nickname, int cnt) {
		basketMapper.changeCnt(cos_seq, nickname,cnt);
		
	}
	public int countBasket(String nickname) {
		
		return basketMapper.countBasket(nickname);
		
	}
	public void registerEvent(EventDTO edo) {
		basketMapper.registerEvent(edo);	
	}
	
	public void dropEvent(EventDTO edo) {
		basketMapper.dropEvent(edo);
		
	}
	
	public List<TempBasketDTO> willPurchase(String mb_id, String pchase, String user_session) {
		
		List<TempBasketDTO> blist=new ArrayList<TempBasketDTO>();
		List<Integer> cnts=new ArrayList<Integer>();
		List<Integer> seqs=new ArrayList<Integer>();
		List<Integer> prices=new ArrayList<Integer>();
		int amount=0;
		int number=0;
		
		try {
		String[] pchase_seq=pchase.split(",");
		
		for(int i=0;i<pchase_seq.length;i++) {
			
			EventDTO edo = new EventDTO();
			edo.setCos_seq(Integer.parseInt(pchase_seq[i]));
			edo.setUser_id(mb_id);
			edo.setUser_session(user_session);
			basketMapper.payEvent(edo);
			
			blist.add(basketMapper.willPurchase(mb_id,Integer.parseInt(pchase_seq[i])).get(0));
			
			
			int seq=Integer.parseInt(pchase_seq[i]);
			seqs.add(seq);

			int cnt = basketMapper.getCnt(mb_id,Integer.parseInt(pchase_seq[i]));
			cnts.add(cnt);
						
			int price=basketMapper.getPrice(Integer.parseInt(pchase_seq[i]));
			prices.add(price);
			
			
			basketMapper.throwSeq(Integer.parseInt(pchase_seq[i]),mb_id);
			
			
			}
		
		for(int j=0;j<pchase_seq.length;j++) {
			
			amount+=prices.get(j)*cnts.get(j);
		
		
		
		}

		
		basketMapper.saveOrder(amount,mb_id);
	
		for(int j=0;j<pchase_seq.length;j++) {
			int order_seq=basketMapper.getNumber(mb_id);
			int seq=seqs.get(j);
			int cnt=cnts.get(j);
			basketMapper.saveDetail(order_seq,seq,cnt);
		}
		
		
		
		
		}
		catch(Exception e){
			
			EventDTO edo = new EventDTO();
			edo.setCos_seq(Integer.parseInt(pchase));
			edo.setUser_id(mb_id);
			edo.setUser_session(user_session);
			basketMapper.payEvent(edo);
			blist.add(basketMapper.willPurchase(mb_id,Integer.parseInt(pchase)).get(0));
			
			int cnt = basketMapper.getCnt(mb_id,Integer.parseInt(pchase));
			int seq=Integer.parseInt(pchase);
			int price=basketMapper.getPrice(Integer.parseInt(pchase));
			
			amount=cnt*price;
			
			basketMapper.throwSeq(Integer.parseInt(pchase), mb_id);
			
			basketMapper.saveOrder(amount,mb_id);
			int order_seq=basketMapper.getNumber(mb_id);
			basketMapper.saveDetail(order_seq,seq,cnt);
		}
		
		return blist;
		
		
		
		
	}
	
	
	
	
	
	
	

}