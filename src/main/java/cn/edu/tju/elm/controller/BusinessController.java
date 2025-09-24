package cn.edu.tju.elm.controller;

import cn.edu.tju.core.model.ResultCodeEnum;
import cn.edu.tju.elm.model.Business;
import cn.edu.tju.core.model.HttpResult;
//import cn.edu.tju.elb.service.BusinessService;
import cn.edu.tju.core.security.service.UserService;
import cn.edu.tju.elm.service.BusinessService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/businesses")
@Tag(name="管理店铺", description = "提供对店铺的增删改查功能")
@Slf4j
public class BusinessController {
    @Autowired
    private UserService userService;

    @Autowired
    private BusinessService businessService;

    @GetMapping("")
    public HttpResult<List<Business>> getBusinesses(){
        return HttpResult.success(businessService.getBusinesses());
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public HttpResult<Business> addBusiness(@RequestBody Business business){
        log.info("商家所属于,{}",business.getBusinessOwner().getId());
        businessService.addBusiness(business);
        return HttpResult.success(business);
    }

    @GetMapping("/{id}")
    public HttpResult<Business> getBusiness(@PathVariable("id") Long id){
        Business business=businessService.getBusinessById(id);
        if (business!=null){
            return HttpResult.success(business);
        }
        return HttpResult.failure(ResultCodeEnum.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public HttpResult<Business> updateBusiness(@PathVariable("id") Long id, @RequestBody Business business){
        businessService.editBusinessById(id,business);
        return HttpResult.success(business);
    }

    @PatchMapping("/{id}")
    public HttpResult<Business> patchBusiness(@PathVariable("id") Long id, @RequestBody Business business){
        businessService.patchBusinessById(id,business);
        return HttpResult.success(business);
    }

    @DeleteMapping("/{id}")
    public HttpResult<Business> deleteBusiness(@PathVariable("id") Long id){
        Business business=businessService.getBusinessById(id);
        businessService.dropBusinessById(id);
        business.setDeleted(true);
        return HttpResult.success(business);
    }
}
