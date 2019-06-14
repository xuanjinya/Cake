package cn.huang.cake.mapper;

import cn.huang.cake.entity.Cake;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author: Yaking
 * @Date: 2019/6/14 20:27
 * @Describe: 蛋糕
 */
public interface CakeMapper {
    /*
     * 分页查询蛋糕
     * skip：跳过的记录数，也就是从那条开始查询
     * size: 要查询的记录数（从skip之后）
     * @return：蛋糕集合
     * */
    @Select("select * from cake order by create_time desc limit #{skip}, #{size}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "name", property = "name"),
            @Result(column = "level", property = "level"),
            @Result(column = "price", property = "price"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    List<Cake> getCakes(Integer skip, Integer size);

    /*
     * 通过分类编号，分页查询蛋糕信息
     * categoryId: 蛋糕分类id
     * skip：跳过的记录数，也就是从那条开始查询
     * size: 要查询的记录数（从skip之后）
     * @return：蛋糕集合
     * @Param注解的作用是给参数命名，参数命名之后就能根据名字得到参数值，正确的将参数传入到sql语句中去
     * */
    @Select("select id, category_id categoryId, name, level, price,create_time createTime, update_time updateTime" +
            "from cake where category_id = #{categoryId} order by create_time desc limit #{skip}, #{size}")
    List<Cake> getCakesByCategoryId(@Param("categoryId") Long categoryId, Integer skip, Integer size);

    @Select("select count(*) from cake where category_id = #{categoryId} ")
    int countCakesByCategoryId(@Param("categoryId") Long categoryId);

    @Insert("insert into cake(category_id, name, level, price, small_img, create_time, update_time)" +
            "value(#{cake.categoryId}, #{cake.name}, #{cake.level}, #{cake.price}, #{cake.smallImg}" +
            "#{cake.createTime}, #{cake.updateTime})")
    void addCake(@Param("cake") Cake cake);

    @Select("select small_img smallImg from cake where id = #{id} for update")
    Cake getImg(@Param("id") Long id);
}
