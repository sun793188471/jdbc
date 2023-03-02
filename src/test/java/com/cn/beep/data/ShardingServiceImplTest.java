package com.cn.beep.data;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cn.beep.AppTest;
import com.cn.beep.data.entity.Order;
import com.cn.beep.data.entity.OrderInfo;
import com.cn.beep.data.entity.Shoping;
import com.cn.beep.data.entity.UserInfo;
import com.cn.beep.data.service.OrderInfoService;
import com.cn.beep.data.service.OrderService;
import com.cn.beep.data.service.ShopService;
import com.cn.beep.data.service.UserInfoService;
import java.util.Arrays;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author YCKJ3465
 * @since 2023/3/1 下午4:04
 */
public class ShardingServiceImplTest extends AppTest {

    @Autowired
    private ShopService shopService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderInfoService orderInfoService;
    @Autowired
    private UserInfoService userInfoService;

    // 测试数据插入的方法
    @Test
    public void insertSelective() {
        for (int i = 100; i <= 200; i++) {
            Shoping shop = new Shoping();
            shop.setShopingName("黄金零号竹子" + i);
            shop.setShopingPrice(String.valueOf(11111 * i));
            shopService.getBaseMapper().insert(shop);
        }
    }

    // 测试数据查询的方法
    @Test
    public void selectSelective() {
        System.out.println(shopService.getBaseMapper().selectList(new QueryWrapper<Shoping>().in("shoping_id",
                Arrays.asList(1, 2))));
    }

    // 测试订单数据的方法
    @Test
    public void insertOrder() {
        // 插入一条订单数据
        Order order = new Order();
        order.setUserId("111111");
        order.setOrderPrice("100000");
        orderService.getBaseMapper().insert(order);

        // 对同一笔订单插入三条订单详情数据
        for (int i = 1; i <= 3; i++) {
            OrderInfo orderInfo = new OrderInfo();
            // 前面插入订单的方法执行完成后会返回orderID
            orderInfo.setOrderId(String.valueOf(order.getOrderId()));
            orderInfo.setShopingName("黄金" + i + "号竹子");
            orderInfo.setShopingPrice(String.valueOf(1111 * i));
            orderInfoService.getBaseMapper().insert(orderInfo);
        }
    }


    @Test
    public void insertUser() {
        // 插入三条性别为男的用户数据
        for (int i = 1; i <= 3; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserName("竹子" + i + "号");
            userInfo.setUserAge(18 + i);
            userInfo.setUserSex("男");
            userInfoService.getBaseMapper().insert(userInfo);
        }

        // 插入两条性别为女的用户数据
        for (int i = 1; i <= 2; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserName("熊猫" + i + "号");
            userInfo.setUserAge(18 + i);
            userInfo.setUserSex("女");
            userInfoService.getBaseMapper().insert(userInfo);
        }
    }
}
