package com.examp.travel.system.model;

import com.examp.travel.system.model.Picture;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author msi
 * @since 2020-01-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Specialty  {

    /**
     * 特产Id
     */
    private Integer specialtyId;

    /**
     * 特产名称
     */
    private String name;

    /**
     * 特产类型
     */
    private String type;

    /**
     * 介绍
     */
    private String introduce;

    /**
     * 地址
     */
    private String address;

    /**
     * 特产电话
     */
    private String phone;

    /**
     * 价格
     */
    private String consumption;
    /**
     * 开放时间
     */
    private String openingTime;

    /**
     * 用户编号
     */
    private Long userId;

    /**
     * 收藏
     */
    private Integer stock = 0;

    /**
     * 评论数
     */
    private Integer commentStock = 0;

    /**
     * 关联图片集合
     */
    private List<Picture> pictureList;

    public Integer getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(Integer specialtyId) {
        this.specialtyId = specialtyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getConsumption() {
        return consumption;
    }

    public void setConsumption(String consumption) {
        this.consumption = consumption;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Picture> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<Picture> pictureList) {
        this.pictureList = pictureList;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getCommentStock() {
        return commentStock;
    }

    public void setCommentStock(Integer commentStock) {
        this.commentStock = commentStock;
    }
}
