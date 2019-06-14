package cn.huang.cake.mapper;

import cn.huang.cake.entity.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: Yaking
 * @Date: 2019/6/14 20:28
 * @Describe: 分类
 */
public interface CategoryMapper {

    /*
     * 功能：查询全部的蛋糕分类
     * 这里的【create_time】【update_time】是数据库的名称，实体类的是【createTime】【updateTime】，形成对应关系
     * */
    @Select("select id, name, create_time createTime, update_time updateTime from category")
    List<Category> getCategories();

    /*功能：根据id删除某个分类*/
    @Delete("delete from category where id = #{id}")
    void deleteById(Long id);

    /*功能：插入分类数据信息，id设置为自增（不用插入）*/
    @Insert("insert into category(name, create_time, update_time) values (#{name}, #{createTime}, #{updateTime})")
    void addCategory(Category category);

}
