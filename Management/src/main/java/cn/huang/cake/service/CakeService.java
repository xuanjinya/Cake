package cn.huang.cake.service;

import cn.huang.cake.common.MybatisUtils;
import cn.huang.cake.entity.Cake;
import cn.huang.cake.mapper.CakeMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;
import java.util.List;

/**
 * @Author: Yaking
 * @Date: 2019/6/15 14:24
 * @Describe: 蛋糕
 */
public class CakeService {
    /*
     * 通过分类编号，分页查询蛋糕信息
     * categoryId: 蛋糕分类id
     * page: 要查询的页数
     * size: 要查询的记录数
     * @return：蛋糕集合
     * */
    public List<Cake> getCakeByCategoryId(Long categoryId, Integer page, Integer size) {
        SqlSession sqlSession = MybatisUtils.openSession();
        try {
            CakeMapper mapper = sqlSession.getMapper(CakeMapper.class);
            return mapper.getCakesByCategoryId(categoryId, (page - 1) * size, size);
        } finally {
            sqlSession.close();
        }
    }

    /*
     * 新增蛋糕
     * cake：蛋糕信息
     * */
    public void addCake(Cake cake) {
        Date now = new Date();
        cake.setCreateTime(now);
        cake.setUpdateTime(now);
        SqlSession sqlSession = MybatisUtils.openSession();
        try {
            CakeMapper mapper = sqlSession.getMapper(CakeMapper.class);
            mapper.addCake(cake);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }

    }
}
