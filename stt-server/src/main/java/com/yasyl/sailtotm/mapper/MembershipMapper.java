package com.yasyl.sailtotm.mapper;

import com.yasyl.sailtotm.entity.Membership;
import com.yasyl.sailtotm.entity.MembershipHistory;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MembershipMapper {
    @Insert("insert into SAILTOTM.MembershipUsers (user_id, balance, password, created_date)" +
            " VALUES (#{userId},#{balance},#{password},#{createdDate})")
    public Boolean add(Membership membership);

    @Select("select * from SAILTOTM.MembershipUsers where user_id = #{userId}")
    public Membership selectByUserId(String userId);

    @Update("update SAILTOTM.MembershipUsers set balance = balance + #{change} where user_id = #{userId}")
    public Boolean topUpMoney(MembershipHistory history);

    public Boolean topUpMoneyAddHistory(MembershipHistory history);

    @Update("update SAILTOTM.MembershipUsers set password = #{password} where user_id = #{userId}")
    public Boolean updatePassword(Integer userId, String password);

    @Select("select * from SAILTOTM.MembershipUsersBalanceHistory where user_id = #{userId} order by created_date desc")
    public Page<MembershipHistory> selectHistoryByUserId(String userId);

    @Select("select * from SAILTOTM.MembershipUsersBalanceHistory where user_id = #{userId} and status_id = #{status} order by created_date desc")
    public Page<MembershipHistory> selectHistoryByUserIdAndStatus(String userId, String status);
}