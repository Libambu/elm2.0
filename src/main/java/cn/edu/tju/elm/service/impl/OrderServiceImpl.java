package cn.edu.tju.elm.service.impl;

import cn.edu.tju.elm.mappers.OrderMapper;
import cn.edu.tju.elm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Integer> getDetailIdsById(Long orderId) {
        return orderMapper.getDetailIdsById(orderId);
    }

    @Override
    public List<Integer> getFoodIdsByDeIds(List<Integer> ids) {
        return orderMapper.getFoodIdsByDeIds(ids);
    }
}
