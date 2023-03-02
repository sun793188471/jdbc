package com.cn.beep.data.entity;

/**
 * @author YCKJ3465
 * @since 2023/2/21 下午6:13
 */

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("`order`")
public class Order {

    /**
     * 订单号
     */
    private Long orderId;
    /**
     * 订单总金额
     */
    private String orderPrice;
    /**
     * 用戶id
     */
    private String userId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
