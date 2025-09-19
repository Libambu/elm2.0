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
        return businessMapper.getBusinesses();
    }

    @Override
    public int addBusiness(Business business) {
        return businessMapper.addBusiness(business);
    }

    @Override
    public Business getBusinessById(Long id) {
        return businessMapper.getBusinessById(id);
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

}
