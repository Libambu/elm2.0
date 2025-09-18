package cn.edu.tju.elm.service;

import cn.edu.tju.elm.model.Business;

import java.util.List;

public interface BusinessService {
    public List<Business> getBusinesses();

    public int addBusiness(Business business);

    public Business getBusinessById(Long id);

    public int editBusinessById(Long id, Business business);

    public int patchBusinessById(Long id, Business business);

    public int dropBusinessById(Long id);
}
