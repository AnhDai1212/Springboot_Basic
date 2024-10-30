package vn.daianh.spring_basic.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import vn.daianh.spring_basic.dto.response.PermissionResponse;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleRequest {
    String name;
    String description;
    Set<String> permissions;

}
