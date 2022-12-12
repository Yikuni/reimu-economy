package com.yikuni.mc.reimueconomy.util;


import com.yikuni.mc.reimueconomy.ReimuEconomy;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtil {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            //使用Mybatis第一步: 获取sqlSessionFactory对象
            String resource = "mybatis.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            ReimuEconomy.INSTANCE.getPluginLoader().disablePlugin(ReimuEconomy.INSTANCE);
        }
    }


    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession(true);
    }
    public static SqlSession getSqlSession(Boolean tran){
        return sqlSessionFactory.openSession(tran);
    }
}