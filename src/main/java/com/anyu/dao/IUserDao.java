package com.anyu.dao;


import com.anyu.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/***
 * 用户的持久层接口
 */
public interface IUserDao {
    /**
     *
     * @return
     */
    @Results(id = "userMap", value = {
            @Result(id = true ,column="id",property = "userid"),
            @Result(column = "username" , property = "username"),
            @Result(column = "sex",property = "usersex"),
            @Result(column = "address",property = "useraddress"),
            @Result(column = "birthday",property = "userbirthday"),
            @Result(property = "accounts" ,column = "id",many = @Many(select = "com.anyu.dao.IAccountDao.findMany",
                    fetchType = FetchType.LAZY))
    })
    @Select("select * from user ")
    public List<User> findAll();

    /**
     *
     * @param id
     * @return
     */
    @ResultMap(value = {"userMap"})
    @Select("select * from user where id=#{id}")
    public User findOne(int id);

//    /**
//     * 查询所有操作
//     * @return
//     */
//    @Select("select * from user")
//    List<User> findAll();
//
//    /**
//     * insert user data
//     * @param user
//     */
//    @Insert("insert into user (username,sex,birthday,address) values(#{username},#{sex},#{birthday},#{address})" )
//    public void insert(User user);
//
//    /**
//     * select one user
//     * @param id
//     * @return
//     */
//    @Select("select * from user where id = #{id}")
//    public User findUser(Integer id);
//
//    /**
//     *
//     * @param name
//     * @return
//     */
//    @Select("select * from user where username like #{name}")
//    public List<User> findMany(String name);
}
