package com.examp.travel.system.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author msi
 * @since 2020-03-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Guide implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "guide_id", type = IdType.AUTO)
    private Integer guideId;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 收藏数
     */
    private Integer stock = 0;

    /**
     * 评论数
     */
    private Integer commentStock = 0;

    /**
     * 用户编号
     */
    private Integer userId;

    /**
     * 用户名*
     */
    private String userName;

    /**
     * 地区*
     */
    private String location;

    /**
     * 简介*
     */
    private String introduce;

    /**
     * 时间*
     */
    private String time;

    /**
     * 图片
     */
    private List<Picture> pictureList;


    public Integer getGuideId() {
        return guideId;
    }

    public void setGuideId(Integer guideId) {
        this.guideId = guideId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<Picture> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<Picture> pictureList) {
        this.pictureList = pictureList;
    }
}
