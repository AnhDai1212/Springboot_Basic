package vn.daianh.spring_basic.controller;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import vn.daianh.spring_basic.dto.request.ApiResponse;
import vn.daianh.spring_basic.dto.request.PermissionRequest;
import vn.daianh.spring_basic.dto.request.UserCreationRequest;
import vn.daianh.spring_basic.dto.request.UserUpdateRequest;
import vn.daianh.spring_basic.dto.response.PermissionResponse;
import vn.daianh.spring_basic.dto.response.UserResponse;
import vn.daianh.spring_basic.service.PermissionService;
import vn.daianh.spring_basic.service.UserService;

import java.util.List;
@RestController
@RequestMapping("/permissions")
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionController {

    PermissionService permissionService;

    @PostMapping
    ApiResponse<PermissionResponse> create(@RequestBody PermissionRequest request) {
        return ApiResponse.<PermissionResponse>builder()
                .result(permissionService.create(request))
                .build();
    }
    @GetMapping
    ApiResponse<List<PermissionResponse>> getAll() {
        return ApiResponse.<List<PermissionResponse>>builder()
                .result(permissionService.getAll())
                .build();
    }
    @DeleteMapping("/{permission}")
    ApiResponse<Void> delete(@PathVariable String permission) {
        permissionService.delete(permission);
        return ApiResponse.<Void>builder().message("Permission deleted successfully").build();
    }

}
