package com.examp.travel.system.model;

/**
 * <p>
 * 
 * </p>
 *
 * @author msi
 * @since 2020-01-03
 */

public class Scenery  {

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
     * 关联图片id
     */
//    private Integer pictureId;

    /**
     * 关联图片id
     */
    private Long ticketPrice;

    /**
     * 关联图片id
     */
    private String openingTime;

    /**
     * 关联图片id
     */
    private String phone;

    /**
     * 关联图片id
     */
    private String address;


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
}
