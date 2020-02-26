package com.anyu.dao;

import com.anyu.entity.Account;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface IAccountDao {
    @Results(id = "accountMap" ,value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "uid",property = "uid"),
            @Result(column = "money",property = "money"),
           @Result(property = "user",column = "uid",one =@One(select = "com.anyu.dao.IUserDao.findOne",
                   fetchType = FetchType.DEFAULT))
    })
    @Select("select * from acount ")
    public List<Account> findAll();


    @Select("select * from acount where uid=#{uid}")
    public List<Account> findMany(int uid);
}
