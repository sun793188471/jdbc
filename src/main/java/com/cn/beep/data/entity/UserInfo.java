package com.cn.beep.data.entity;

/**
 * @author YCKJ3465
 * @since 2023/2/21 下午6:13
 */
public class UserInfo {

    /**
     * userId
     */
    private Long userId;
    /**
     * userName
     */
    private String userName;
    /**
     * userSex
     */
    private String userSex;
    /**
     * userAge
     */
    private Integer userAge;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }
}
