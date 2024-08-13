package com.yasyl.sailtotm.mapper;

import com.yasyl.sailtotm.entity.CartItem;
import com.yasyl.sailtotm.entity.CartUpdateQuantity;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;

@Mapper
public interface CartMapper {
    public Integer insert(String num_iid, LocalDateTime created_time, String user_id, int quantity, String properties, String propertiesName);

    public Page<CartItem> selectAllByUserIdPage(String user_id);

    public Page<CartItem> selectAllByUserSearchPage(String user_id, String q);

    @Delete("delete from SAILTOTM.CartItems where user_id = #{userId} and cart_id = #{cartId}")
    public Integer delete(String userId, Integer cartId);

    @Update("update SAILTOTM.CartItems set quantity = #{quantity} " +
            "where cart_id = #{cartId} and user_id = #{userId}")
    public Integer updateQuantityById(CartUpdateQuantity cart);

    public CartItem selectAllByUserId(String userId, String numIid,String propertiesName);
}
