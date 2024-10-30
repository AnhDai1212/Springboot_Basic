package vn.daianh.spring_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.daianh.spring_basic.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {
}
