package vn.daianh.spring_basic.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.daianh.spring_basic.dto.request.RoleRequest;
import vn.daianh.spring_basic.dto.response.RoleResponse;
import vn.daianh.spring_basic.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "permissions", ignore = true)  // tự động bỏ qua attribute permission khi mapper
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}