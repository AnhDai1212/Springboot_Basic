package vn.daianh.spring_basic.controller;


import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import vn.daianh.spring_basic.configuration.SecurityConfig;
import vn.daianh.spring_basic.dto.response.UserResponse;
import vn.daianh.spring_basic.entity.User;
import vn.daianh.spring_basic.dto.request.ApiResponse;
import vn.daianh.spring_basic.dto.request.UserCreationRequest;
import vn.daianh.spring_basic.dto.request.UserUpdateRequest;
import vn.daianh.spring_basic.service.UserService;

import java.util.List;


@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request){
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(request));
        return apiResponse;
    }

    @GetMapping
    ApiResponse<List<UserResponse>> getUsers(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("Username: " + authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getUsers())
                .code(1000)
                .build();

//        ApiResponse<List<UserResponse>> getUsers(){
//            ApiResponse<List<UserResponse>> response = ApiResponse.<List<UserResponse>>builder()
//                    .result(userService.getUsers())
//                    .build();
//            response.setCode(1000); // Đặt lại giá trị code
//            return response;
    }

    @GetMapping("/{userId}")
    ApiResponse<UserResponse> getUser(@PathVariable("userId") String userId){
        return ApiResponse.<UserResponse>builder()
                .result(userService.getUser(userId))
                .build();
    }
    @GetMapping("/myInfo")
    ApiResponse<UserResponse> getMyInfo(){
        return ApiResponse.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }

    @PutMapping("/{userId}")
    ApiResponse<UserResponse> updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request){
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUser(userId, request))
                .build();
    }

    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable String userId){
            userService.deleteUser(userId);
            return "User has been deleted";
    }
}
