package com.cn.beep.data.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.beep.data.entity.OrderInfo;
import com.cn.beep.data.mapper.OrderInfoMapper;
import org.springframework.stereotype.Service;

/**
 * @author YCKJ3465
 * @since 2023/2/21 下午6:14
 */
@Service
public class OrderInfoService extends ServiceImpl<OrderInfoMapper, OrderInfo> {

    //public final TestShard testShard;
    //
    //public OrderInfoService(TestShard testShard) {
    //    this.testShard = testShard;
    //}
}
