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
    private Integer sceneryId;

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
    private Integer level;

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
    private Integer pictureId;

    public Integer getSceneryId() {
        return sceneryId;
    }

    public void setSceneryId(Integer sceneryId) {
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
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

    public Integer getPictureId() {
        return pictureId;
    }

    public void setPictureId(Integer pictureId) {
        this.pictureId = pictureId;
    }
}
