package vn.daianh.spring_basic.controller;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import vn.daianh.spring_basic.dto.request.ApiResponse;
import vn.daianh.spring_basic.dto.request.PermissionRequest;
import vn.daianh.spring_basic.dto.request.RoleRequest;
import vn.daianh.spring_basic.dto.response.PermissionResponse;
import vn.daianh.spring_basic.dto.response.RoleResponse;
import vn.daianh.spring_basic.service.PermissionService;
import vn.daianh.spring_basic.service.RoleService;

import java.util.List;

@RestController
@RequestMapping("/roles")
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {

    RoleService roleService;

    @PostMapping
    ApiResponse<RoleResponse> create(@RequestBody RoleRequest request) {
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.create(request))
                .build();
    }
    @GetMapping
    ApiResponse<List<RoleResponse>> getAll() {
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleService.getAll())
                .build();
    }
    @DeleteMapping("/{role}")
    ApiResponse<Void> delete(@PathVariable String role) {
        roleService.delete(role);
        return ApiResponse.<Void>builder().message("Role deleted successfully").build();
    }

}
