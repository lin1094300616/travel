package com.examp.travel.system.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
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
public class Scenery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 景点ID
     */
    private Long sceneryId;
    /**
     * 景点名称
     */
    private String name;

    /**
     * 所在地
     */
    private String location;
    /**
     * 级别
     */
    private String level;
    /**
     * 景点类型
     */
    private String type;
    /**
     * 介绍
     */
    private String introduce;
    /**
     * 门票价格
     */
    private Long ticketPrice;
    /**
     * 营业时间
     */
    private String openingTime;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 地址
     */
    private String address;
    /**
     * 交通
     */
    private String traffic;
    /**
     * 门票
     */
    private String ticket;

    /**
     * 收藏
     */
    private Integer stock = 0;

    private List<Picture> pictureList;

    public Long getSceneryId() {
        return sceneryId;
    }

    public void setSceneryId(Long sceneryId) {
        this.sceneryId = sceneryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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

    public Long getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Long ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTraffic() {
        return traffic;
    }

    public void setTraffic(String traffic) {
        this.traffic = traffic;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
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
}
