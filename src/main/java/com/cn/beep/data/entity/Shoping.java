package com.cn.beep.data.entity;

/**
 * @author YCKJ3465
 * @since 2023/2/21 下午6:13
 */
public class Shoping {

    /**
     * 商品id
     */
    private Long shopingId;
    /**
     * 商品名称
     */
    private String shopingName;
    /**
     * 商品价格
     */
    private String shopingPrice;

    public Long getShopingId() {
        return shopingId;
    }

    public void setShopingId(Long shopingId) {
        this.shopingId = shopingId;
    }

    public String getShopingName() {
        return shopingName;
    }

    public void setShopingName(String shopingName) {
        this.shopingName = shopingName;
    }

    public String getShopingPrice() {
        return shopingPrice;
    }

    public void setShopingPrice(String shopingPrice) {
        this.shopingPrice = shopingPrice;
    }
}
