package cn.huang.cake.service;

import cn.huang.cake.common.MybatisUtils;
import cn.huang.cake.entity.Category;
import cn.huang.cake.mapper.CategoryMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;
import java.util.List;

/**
 * @Author: Yaking
 * @Date: 2019/6/15 14:58
 * @Describe: 分类
 */
public class CategoryService {
    /*
     * 功能：查询全部的蛋糕分类
     * return: 全部蛋糕分类
     * */
    public List<Category> getCategories() {
        SqlSession sqlSession = MybatisUtils.openSession();
        try {
            CategoryMapper mapper = sqlSession.getMapper(CategoryMapper.class);
            return mapper.getCategories();
        } finally {
            sqlSession.close();
        }
    }

    /*
     * 插入蛋糕分类信息
     * category：蛋糕分类的实体
     * */
    public void addCategory(Category category) {
        SqlSession sqlSession = MybatisUtils.openSession();
        Date now = new Date();
        category.setCreateTime(now);
        category.setUpdateTime(now);
        try {
            CategoryMapper mapper = sqlSession.getMapper(CategoryMapper.class);
            mapper.addCategory(category);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }
}
