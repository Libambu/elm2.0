package cn.edu.tju.elm.service.impl;

import cn.edu.tju.core.model.User;
import cn.edu.tju.core.security.service.UserService;
import cn.edu.tju.elm.mappers.BusinessMapper;
import cn.edu.tju.elm.model.Business;
import cn.edu.tju.elm.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessServiceImpl implements BusinessService {
    @Autowired
    private BusinessMapper businessMapper;
    @Autowired
    private UserService userService;

    @Override
    public List<Business> getBusinesses() {
        List<Business> businesses=businessMapper.getBusinesses();
        for (Business business : businesses) {
            getBusinessUserId(business);
        }
        return businesses;
    }

    @Override
    public int addBusiness(Business business) {
        return businessMapper.addBusiness(business);
    }

    @Override
    public Business getBusinessById(Long id) {
        Business business=businessMapper.getBusinessById(id);
        getBusinessUserId(business);
        return business;
    }

    @Override
    public int editBusinessById(Long id, Business business) {
        return businessMapper.editBusinessById(id,business);
    }

    @Override
    public int patchBusinessById(Long id, Business business) {
        return businessMapper.patchBusinessById(id,business);
    }

    @Override
    public int dropBusinessById(Long id) {
        return businessMapper.dropBusinessById(id);
    }

    private void getBusinessUserId(Business business) {
        Long userId=businessMapper.getBusinessUserIdById(business.getId());
        User user=userService.getUserById(userId);
        business.setBusinessOwner(user);
    }
}
