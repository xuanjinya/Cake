package cn.huang.cake.entity;

import java.util.Date;

/**
 * @Author: Yaking
 * @Date: 2019/6/14 20:47
 * @Describe: 蛋糕实体
 */
public class Cake {

    private Long id;
    private Long categoryId;//所属分类的id
    private String name;//蛋糕的名字
    private Integer level;//级别（几颗星）
    private Integer price;//价格，整型以分为单位
    private byte[] smallImg;//图片
    private Date createTime;//创建时间
    private Date updateTime;//修改时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public byte[] getSmallImg() {
        return smallImg;
    }

    public void setSmallImg(byte[] smallImg) {
        this.smallImg = smallImg;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
