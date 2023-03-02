package com.cn.beep.data.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.beep.data.entity.Order;
import com.cn.beep.data.mapper.OrderMapper;
import org.springframework.stereotype.Service;

/**
 * @author YCKJ3465
 * @since 2023/2/21 下午6:14
 */
@Service
public class OrderService extends ServiceImpl<OrderMapper, Order> {

}
