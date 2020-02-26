package com.anyu.dao;

import com.anyu.entity.Account;
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
import java.util.List;

import static org.junit.Assert.*;

public class IAccountDaoTest {

    private InputStream in;
    private SqlSessionFactory sqlSessionFactory;
    private SqlSession sqlSession;
    private IAccountDao accountDao;

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
        accountDao=  sqlSession.getMapper(IAccountDao.class);
    }
    @After
    public void destroy() throws IOException {
        sqlSession.close();
        in.close();
    }

    @Test
    public void findAll(){
        List<Account> all=accountDao.findAll();
        for (Account account:all
        ) {
            System.out.println(account);
        }
    }
}