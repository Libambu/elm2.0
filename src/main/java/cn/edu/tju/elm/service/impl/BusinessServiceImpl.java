package cn.edu.tju.elm.service.impl;

import cn.edu.tju.core.model.User;
import cn.edu.tju.core.security.service.UserService;
import cn.edu.tju.elm.mappers.BusinessMapper;
import cn.edu.tju.elm.model.Business;
import cn.edu.tju.elm.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessServiceImpl implements BusinessService {
    @Autowired
    private BusinessMapper businessMapper;
    @Autowired
    private UserService userService;

    @Override
    public List<Business> getBusinesses() {
        List<Long> ids = businessMapper.getAllids();
        List<Business> list = new ArrayList<>();
        if(ids!=null){
            for(Long id : ids){
                Business businessById = businessMapper.getBusinessById(id);
                list.add(businessById);
            }
        }
        return list;
    }

    @Override
    public int addBusiness(Business business) {
        User me = userService.getUserWithAuthorities().get();
        business.setCreator(me.getId());
        business.setCreateTime(LocalDateTime.now());
        business.setUpdater(me.getId());
        business.setUpdateTime(LocalDateTime.now());
        return businessMapper.addBusiness(business);
    }

    @Override
    public Business getBusinessById(Long id) {
        return businessMapper.getBusinessById(id);
    }

    @Override
    public int editBusinessById(Long id, Business business) {
        User me = userService.getUserWithAuthorities().get();
        business.setUpdater(me.getId());
        business.setUpdateTime(LocalDateTime.now());
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
