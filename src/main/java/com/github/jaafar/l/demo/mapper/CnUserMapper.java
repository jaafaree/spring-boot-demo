package com.github.jaafar.l.demo.mapper;

import com.github.jaafar.l.demo.entity.CnUser;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface CnUserMapper extends Mapper<CnUser> {
    /*@Select("select IFNULL(count(1),0) from cn_user cu WHERE cu.username= #{username} AND cu.`status`='1'")
    @ResultType(Integer.class)*/
    Integer getUserExistByUsername(@Param("username") String username);
}