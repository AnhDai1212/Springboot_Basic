package vn.daianh.spring_basic.service;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.daianh.spring_basic.dto.request.ApiResponse;
import vn.daianh.spring_basic.dto.request.PermissionRequest;
import vn.daianh.spring_basic.dto.request.RoleRequest;
import vn.daianh.spring_basic.dto.response.PermissionResponse;
import vn.daianh.spring_basic.dto.response.RoleResponse;
import vn.daianh.spring_basic.entity.Permission;
import vn.daianh.spring_basic.entity.Role;
import vn.daianh.spring_basic.mapper.PermissionMapper;
import vn.daianh.spring_basic.mapper.RoleMapper;
import vn.daianh.spring_basic.repository.PermissionRepository;
import vn.daianh.spring_basic.repository.RoleRepository;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {

   RoleRepository roleRepository;
   RoleMapper roleMapper;
   PermissionRepository permissionRepository;

   public RoleResponse create(RoleRequest request) {
      var role = roleMapper.toRole(request);
      var permissions = permissionRepository.findAllById(request.getPermissions());
      role.setPermissions(new HashSet<>(permissions));
      role = roleRepository.save(role);
      return roleMapper.toRoleResponse(role);
   }

   public List<RoleResponse> getAll() {
      var role = roleRepository.findAll();
      return role.stream() // Chuyển danh sách role thành stream qua stream API đê có thể ánh xạ rõ ràng
              .map(roleMapper::toRoleResponse) // Ánh xạ đối tượng role sang roleResponse/ map là một thao tác của Stream API, cho phép bạn ánh xạ (chuyển đổi) từng phần tử trong stream từ kiểu này sang kiểu khác.
              .toList(); // Chuyển kết quả stream thành một chuỗi
   }

   public void delete(String role) {
      roleRepository.deleteById(role);
   }

}
