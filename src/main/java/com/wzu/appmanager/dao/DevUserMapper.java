package com.wzu.appmanager.dao;

import com.wzu.appmanager.pojo.DevUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DevUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dev_user
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dev_user
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    int insert(DevUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dev_user
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    DevUser selectByPrimaryKey(Long id);

    int activeUser(@Param("code") String code);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dev_user
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    List<DevUser> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dev_user
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    int updateByPrimaryKey(DevUser record);

    public DevUser getLoginUser(@Param("devCode")String devCode)throws Exception;

}