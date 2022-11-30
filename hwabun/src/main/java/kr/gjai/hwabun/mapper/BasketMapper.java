package kr.gjai.hwabun.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.gjai.hwabun.entity.BasketDTO;
import kr.gjai.hwabun.entity.CosmeticDTO;
import kr.gjai.hwabun.entity.TempBasketDTO;

@Mapper
public interface BasketMapper {

	
	
	
	
	@Select("select * from cosmetics where cos_seq=#{cos_seq}")
	public CosmeticDTO getCosmetics(int cos_seq);
	
	@Select("select * from cosmetics where cos_seq>=1 and cos_seq<=10")
	public List<CosmeticDTO> showCosmetics();
	
	
	@Select("select * from cosmetics where cos_seq in(select cos_seq from basket where mb_id=#{nickname}")
	public List<CosmeticDTO> showBasket2(String nickname);
	
	
	
	@Select("select * from basket where mb_id=#{nickname} order by cos_seq")
	public List<BasketDTO> showBasket1(String nickname);
	
	
	
	@Select("select c.cos_seq, c.cos_photo1, c.cos_name, c.cos_price, t.cnt, (select cnt*cos_price from dual) as price from basket t left outer join cosmetics c on t.cos_seq=c.cos_seq where t.mb_id=#{nickname}")
	public List<TempBasketDTO> showBasket(String nickname);
	
	
	@Select("select count(*) from basket where mb_id=#{nickname}")
	public int countBasket(String nickiname);
	
	
	@Insert("insert into basket(cos_seq,cnt,mb_id,reg_date,basket_status) values(#{param1},#{param3},#{param2},now(),'o')")
	public void insertBasket(int param1, String param2,int param3);
	

	@Update("update basket set cnt=cnt+#{param3} where cos_seq=#{param1} and mb_id=#{param2}")
	public void updateBasket(int param1, String param2, int param3);

	
	
	
	@Update("update basket set cnt=cnt-1 where cos_seq=#{param1} and mb_id=#{param2} and (cnt-1)>0")
	public void minusCnt(int param1, String param2);
	
	
	@Select("update basket set cnt=cnt+1 where cos_seq=#{param1} and mb_id=#{param2}")
	public void plusCnt(int param1, String param2);

	@Delete("delete from basket where cos_seq=#{param1} and mb_id=#{param2}")
	public void throwSeq(int param1, String param2);
	
	@Delete("delete from basket where mb_id=#{nickname}")
	public void clearCart(String nickname);

	@Update("update basket set cnt=#{param3} where cos_seq=#{param1} and mb_id=#{param2}")
	public void changeCnt(int param1, String param2, int param3);



	
	
	
	
}
