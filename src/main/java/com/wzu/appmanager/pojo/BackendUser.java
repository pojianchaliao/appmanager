package com.wzu.appmanager.pojo;

import java.util.Date;

public class BackendUser {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column backend_user.id
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column backend_user.userCode
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    private String usercode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column backend_user.userName
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column backend_user.userType
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    private Long usertype;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column backend_user.createdBy
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    private Long createdby;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column backend_user.creationDate
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    private Date creationdate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column backend_user.modifyBy
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    private Long modifyby;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column backend_user.modifyDate
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    private Date modifydate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column backend_user.userPassword
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    private String userpassword;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column backend_user.id
     *
     * @return the value of backend_user.id
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column backend_user.id
     *
     * @param id the value for backend_user.id
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column backend_user.userCode
     *
     * @return the value of backend_user.userCode
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    public String getUsercode() {
        return usercode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column backend_user.userCode
     *
     * @param usercode the value for backend_user.userCode
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    public void setUsercode(String usercode) {
        this.usercode = usercode == null ? null : usercode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column backend_user.userName
     *
     * @return the value of backend_user.userName
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column backend_user.userName
     *
     * @param username the value for backend_user.userName
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column backend_user.userType
     *
     * @return the value of backend_user.userType
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    public Long getUsertype() {
        return usertype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column backend_user.userType
     *
     * @param usertype the value for backend_user.userType
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    public void setUsertype(Long usertype) {
        this.usertype = usertype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column backend_user.createdBy
     *
     * @return the value of backend_user.createdBy
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    public Long getCreatedby() {
        return createdby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column backend_user.createdBy
     *
     * @param createdby the value for backend_user.createdBy
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    public void setCreatedby(Long createdby) {
        this.createdby = createdby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column backend_user.creationDate
     *
     * @return the value of backend_user.creationDate
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    public Date getCreationdate() {
        return creationdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column backend_user.creationDate
     *
     * @param creationdate the value for backend_user.creationDate
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    public void setCreationdate(Date creationdate) {
        this.creationdate = creationdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column backend_user.modifyBy
     *
     * @return the value of backend_user.modifyBy
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    public Long getModifyby() {
        return modifyby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column backend_user.modifyBy
     *
     * @param modifyby the value for backend_user.modifyBy
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    public void setModifyby(Long modifyby) {
        this.modifyby = modifyby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column backend_user.modifyDate
     *
     * @return the value of backend_user.modifyDate
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    public Date getModifydate() {
        return modifydate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column backend_user.modifyDate
     *
     * @param modifydate the value for backend_user.modifyDate
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column backend_user.userPassword
     *
     * @return the value of backend_user.userPassword
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    public String getUserpassword() {
        return userpassword;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column backend_user.userPassword
     *
     * @param userpassword the value for backend_user.userPassword
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword == null ? null : userpassword.trim();
    }
}