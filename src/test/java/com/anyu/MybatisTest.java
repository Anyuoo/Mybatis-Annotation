package com.anyu;

import com.anyu.dao.IUserDao;
import com.anyu.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisTest {
    private InputStream in;
    private SqlSessionFactory sqlSessionFactory;
    private   SqlSession sqlSession;
    private IUserDao userDao;

    @Before
    public void init() throws IOException {
        //1.读取配置文件
        in= Resources.getResourceAsStream("sqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
        sqlSessionFactory=sqlSessionFactoryBuilder.build(in);
        //3.使用工厂产生SqlSession对象
        sqlSession=sqlSessionFactory.openSession(true);
        //4.使用session创建Dao接口的代理对象
        userDao=sqlSession.getMapper(IUserDao.class);
    }
    @After
    public void destroy() throws IOException {
        sqlSession.close();
        in.close();
    }

    @Test
    public void findAll(){
        List<User> all=userDao.findAll();
        for (User user:all
             ) {
            System.out.println(user);
        }
    }




//    @Test
//    public  void test(){
//
//        //5.使用代理对象执行方法
//        List<User> all=userDao.findAll();
//        for (User user:all) {
//            System.out.printf("name"+user.getUsername());
//            System.out.println(user.getAddress());
//            System.out.println(user.getBirthday());
//        }
//    }
//
//    @Test
//    public void insert(){
//        User user=new User();
//        user.setUsername("xiayule");
//        user.setAddress("shanghai");
//        user.setBirthday(new Date());
//        user.setSex("男");
//        userDao.insert(user);
//    }
//
//    @Test
//    public void findUser(){
//       User user= userDao.findUser(38);
//        System.out.println(user);
//    }
//    @Test
//    public void findMany(){
//        List<User> list=userDao.findMany("%三");
//        for (User u: list
//             ) {
//            System.out.println(u);
//        }
//    }



}
