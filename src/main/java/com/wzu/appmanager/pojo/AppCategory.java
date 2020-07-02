package com.wzu.appmanager.pojo;

import java.util.Date;

public class AppCategory {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_category.id
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_category.categoryCode
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    private String categorycode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_category.categoryName
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    private String categoryname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_category.parentId
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    private Long parentid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_category.createdBy
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    private Long createdby;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_category.creationTime
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    private Date creationtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_category.modifyBy
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    private Long modifyby;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_category.modifyDate
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    private Date modifydate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_category.id
     *
     * @return the value of app_category.id
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_category.id
     *
     * @param id the value for app_category.id
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_category.categoryCode
     *
     * @return the value of app_category.categoryCode
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    public String getCategorycode() {
        return categorycode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_category.categoryCode
     *
     * @param categorycode the value for app_category.categoryCode
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    public void setCategorycode(String categorycode) {
        this.categorycode = categorycode == null ? null : categorycode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_category.categoryName
     *
     * @return the value of app_category.categoryName
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    public String getCategoryname() {
        return categoryname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_category.categoryName
     *
     * @param categoryname the value for app_category.categoryName
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname == null ? null : categoryname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_category.parentId
     *
     * @return the value of app_category.parentId
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    public Long getParentid() {
        return parentid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_category.parentId
     *
     * @param parentid the value for app_category.parentId
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_category.createdBy
     *
     * @return the value of app_category.createdBy
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    public Long getCreatedby() {
        return createdby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_category.createdBy
     *
     * @param createdby the value for app_category.createdBy
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    public void setCreatedby(Long createdby) {
        this.createdby = createdby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_category.creationTime
     *
     * @return the value of app_category.creationTime
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    public Date getCreationtime() {
        return creationtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_category.creationTime
     *
     * @param creationtime the value for app_category.creationTime
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    public void setCreationtime(Date creationtime) {
        this.creationtime = creationtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_category.modifyBy
     *
     * @return the value of app_category.modifyBy
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    public Long getModifyby() {
        return modifyby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_category.modifyBy
     *
     * @param modifyby the value for app_category.modifyBy
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    public void setModifyby(Long modifyby) {
        this.modifyby = modifyby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_category.modifyDate
     *
     * @return the value of app_category.modifyDate
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    public Date getModifydate() {
        return modifydate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_category.modifyDate
     *
     * @param modifydate the value for app_category.modifyDate
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }
}