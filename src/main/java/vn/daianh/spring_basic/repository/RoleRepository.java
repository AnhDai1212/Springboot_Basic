package vn.daianh.spring_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.daianh.spring_basic.entity.Permission;
import vn.daianh.spring_basic.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
}
