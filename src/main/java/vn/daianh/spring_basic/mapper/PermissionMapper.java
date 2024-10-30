package vn.daianh.spring_basic.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import vn.daianh.spring_basic.dto.request.PermissionRequest;
import vn.daianh.spring_basic.dto.request.UserCreationRequest;
import vn.daianh.spring_basic.dto.request.UserUpdateRequest;
import vn.daianh.spring_basic.dto.response.PermissionResponse;
import vn.daianh.spring_basic.dto.response.UserResponse;
import vn.daianh.spring_basic.entity.Permission;
import vn.daianh.spring_basic.entity.User;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);

}
