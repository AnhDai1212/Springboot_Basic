package vn.daianh.spring_basic.dto.response;

import jakarta.persistence.ElementCollection;
import lombok.*;
import lombok.experimental.FieldDefaults;
import vn.daianh.spring_basic.enums.Role;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
     String id;
     String username;
//   String password;
     String firstName;
     String lastName;
     LocalDate dob;
//   @ElementCollection
//   Set<Role> roles;
     Set<RoleResponse> roles;

}
