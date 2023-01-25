package kr.gjai.hwabun.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import kr.gjai.hwabun.entity.BasketDTO;
import kr.gjai.hwabun.entity.CosmeticsDTO;
import kr.gjai.hwabun.entity.EventDTO;
import kr.gjai.hwabun.entity.TempBasketDTO;
@Mapper
public interface BasketMapper {
	
	
	
	
	@Select("select * from cosmetics where cos_seq=#{cos_seq}")
	public CosmeticsDTO getCosmetics(int cos_seq);
	
	@Select("select * from cosmetics where cos_seq>=1 and cos_seq<=10")
	public List<CosmeticsDTO> showCosmetics();
	
	@Select("select * from basket where mb_id=#{nickname} order by cos_seq")
	public List<BasketDTO> showBasket1(String nickname);
	
	@Select("select * from cosmetics where cos_seq in(select cos_seq from basket where mb_id=#{nickname}")
	public List<CosmeticsDTO> showBasket2(String nickname);
	
	
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
	@Insert("insert into event_table(event_time, event_type, cos_seq, user_id, user_session, admin_id)\r\n"
			+ "values (NOW(), 2, #{cos_seq}, #{user_id}, #{user_session}, 'op')")
	public void registerEvent(EventDTO edo);
	
	@Insert("insert into event_table(event_time, event_type, cos_seq, user_id, user_session, admin_id)\r\n"
			+ "values (NOW(), 3, #{cos_seq}, #{user_id}, #{user_session}, 'op')")
	public void dropEvent(EventDTO edo);
	
	
	@Select("select c.cos_seq, c.cos_photo1, c.cos_name, c.cos_price, t.cnt, (select cnt*cos_price from dual) as price from basket t left outer join cosmetics c on t.cos_seq=c.cos_seq where t.mb_id=#{param1} and t.cos_seq=#{param2}")
	public List<TempBasketDTO> willPurchase(String param1, int param2);
	@Select("select cos_seq from basket where mb_id = #{mb_id}")
	public List<String> getList(String mb_id);

	@Insert("insert into event_table(event_time, event_type, cos_seq, user_id, user_session, admin_id)\r\n"
			+ "		values (NOW(), 4, #{cos_seq}, #{user_id}, #{user_session}, 'op')")
	public void payEvent(EventDTO edo);
	
	@Select("select cnt from basket where mb_id=#{param1} and cos_seq=#{param2}")
	public int getCnt(String param1, int param2);

	@Insert("insert into orders(order_date,total_amount,discount_amount,pay_amount,pay_method,paid_amount,mb_id,order_status) values(now(),#{param1},0,#{param1},'credit_card',#{param1},#{param2},'o')")
	public void saveOrder(int amount, String mb_id);

	@Select("select cos_price from cosmetics where cos_seq=#{cos_seq}")
	public int getPrice(int cos_seq);

	
	@Select("select order_seq from orders where mb_id=#{mb_id} and order_date=(select max(order_date) from orders where mb_id=#{mb_id})")
	public int getNumber(String mb_id);

	@Insert("insert into order_detail(order_seq,cos_seq,cos_cnt) values(#{param1},#{param2},#{param3})")
	public void saveDetail(int param1, int param2, int param3);

	
	
	
}