package cn.huang.cake.common;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * @Author: Yaking
 * @Date: 2019/6/14 19:49
 * @Describe: MyBatis的工具类
 */
public class MybatisUtils {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;//读取配置文件

    static {
        try {
            //配置数据库文件，数据库的连接账户，密码等
            String resource = "config.xml";
            reader = Resources.getResourceAsReader(resource);
            //初始化sqlSessionFactory
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //打开sqlSession会话
    public static SqlSession openSession() {
        return sqlSessionFactory.openSession();
    }


}
