package cn.edu.tju.elm.service.impl;

import cn.edu.tju.core.model.User;
import cn.edu.tju.core.security.service.UserService;
import cn.edu.tju.elm.mappers.AddressMapper;
import cn.edu.tju.elm.model.DeliveryAddress;
import cn.edu.tju.elm.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private UserService userService;

    @Override
    public int addAddress(DeliveryAddress deliveryAddress) {

        User me = userService.getUserWithAuthorities().get();
        deliveryAddress.setCreator(me.getId());
        deliveryAddress.setCreateTime(LocalDateTime.now());
        deliveryAddress.setUpdater(me.getId());
        deliveryAddress.setUpdateTime(LocalDateTime.now());
        return addressMapper.addAddress(deliveryAddress);
    }
}
