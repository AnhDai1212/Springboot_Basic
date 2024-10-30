package vn.daianh.spring_basic.service;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.daianh.spring_basic.dto.request.PermissionRequest;
import vn.daianh.spring_basic.dto.response.PermissionResponse;
import vn.daianh.spring_basic.entity.Permission;
import vn.daianh.spring_basic.mapper.PermissionMapper;
import vn.daianh.spring_basic.repository.PermissionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService {

    PermissionRepository permissionResponsitory;
    PermissionMapper permissionMapper;
    public PermissionResponse create(PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);
        permission = permissionResponsitory.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }
    public List<PermissionResponse> getAll() {
        var permission = permissionResponsitory.findAll();
        return permission.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    public void delete(String permission) {
        permissionResponsitory.deleteById(permission);
    }

}
