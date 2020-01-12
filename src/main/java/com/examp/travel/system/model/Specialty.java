package com.examp.travel.system.model;

/**
 * <p>
 * 
 * </p>
 *
 * @author msi
 * @since 2020-01-03
 */
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
     * 关联图片id
     */
    private Integer pictureId;

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

    public Integer getPictureId() {
        return pictureId;
    }

    public void setPictureId(Integer pictureId) {
        this.pictureId = pictureId;
    }
}
