package cn.edu.tju.elm.mappers;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    List<Integer> getDetailIdsById(Long orderId);

    List<Integer> getFoodIdsByDeIds(List<Integer> ids);
}
