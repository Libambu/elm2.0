package cn.edu.tju.elm.mappers;

import cn.edu.tju.elm.dto.AddressDto;
import cn.edu.tju.elm.model.DeliveryAddress;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddressMapper {

    AddressDto getAddressDtoByid(Long addressId) ;

    int addAddress(DeliveryAddress deliveryAddress);
}
