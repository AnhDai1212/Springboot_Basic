package vn.daianh.spring_basic.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import vn.daianh.spring_basic.dto.response.UserResponse;
import vn.daianh.spring_basic.entity.User;
import vn.daianh.spring_basic.dto.request.UserCreationRequest;
import vn.daianh.spring_basic.dto.request.UserUpdateRequest;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

//    @Mapping(source = "firstname", target = "lastname")
//    @Mapping(target = "lastname", ignore = true)

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest userUpdateRequest);

    UserResponse toUserResponse(User user);

}
