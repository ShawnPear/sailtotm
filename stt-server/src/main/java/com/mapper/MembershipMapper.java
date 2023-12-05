package com.mapper;

import com.entity.Membership;
import com.entity.MembershipHistory;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MembershipMapper {
    @Insert("insert into SailToTMDB.MembershipUsers (user_id, balance, password, created_date)" +
            " VALUES (#{userId},#{balance},#{password},#{createdDate})")
    public Boolean add(Membership membership);

    @Select("select * from SailToTMDB.MembershipUsers where user_id = #{userId}")
    public Membership selectByUserId(String userId);

    @Update("update SailToTMDB.MembershipUsers set balance = balance + #{change} where user_id = #{userId}")
    public Boolean topUpMoney(MembershipHistory history);

    public Boolean topUpMoneyAddHistory(MembershipHistory history);

    @Update("update SailToTMDB.MembershipUsers set password = #{password} where user_id = #{userId}")
    public Boolean updatePassword(Integer userId, String password);

    @Select("select * from SailToTMDB.MembershipUsersBalanceHistory where user_id = #{userId} order by created_date desc")
    public Page<MembershipHistory> selectHistoryByUserId(String userId);

    @Select("select * from SailToTMDB.MembershipUsersBalanceHistory where user_id = #{userId} and status_id = #{status} order by created_date desc")
    public Page<MembershipHistory> selectHistoryByUserIdAndStatus(String userId, String status);
}