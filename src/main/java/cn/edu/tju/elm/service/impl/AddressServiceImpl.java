package cn.edu.tju.elm.service.impl;

import cn.edu.tju.elm.mappers.AddressMapper;
import cn.edu.tju.elm.model.DeliveryAddress;
import cn.edu.tju.elm.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public int addAddress(DeliveryAddress deliveryAddress) {
        return addressMapper.addAddress(deliveryAddress);
    }
}
