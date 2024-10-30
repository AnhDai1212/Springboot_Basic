package vn.daianh.spring_basic.service;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.daianh.spring_basic.dto.response.UserResponse;
import vn.daianh.spring_basic.entity.User;
import vn.daianh.spring_basic.enums.Role;
import vn.daianh.spring_basic.exception.AppException;
import vn.daianh.spring_basic.exception.ErrorCode;
import vn.daianh.spring_basic.mapper.UserMapper;
import vn.daianh.spring_basic.repository.RoleRepository;
import vn.daianh.spring_basic.repository.UserRepository;
import vn.daianh.spring_basic.dto.request.UserCreationRequest;
import vn.daianh.spring_basic.dto.request.UserUpdateRequest;

import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {

    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;

    public UserResponse createUser(UserCreationRequest request){

        if(userRepository.existsByUsername(request.getUsername())){
            throw new AppException(ErrorCode.USER_EXISTED);
//                throw new RuntimeException("Error");
        }

        User user = userMapper.toUser(request);
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        HashSet<String> roles = new HashSet<>();

        roles.add(Role.USER.name());

//        user.setRoles(roles);

//        User user = User.builder()
//                .username(request.getUsername())
//                .password(request.getPassword())
//                .firstName(request.getFirstName())
//                .lastName(request.getLastName())
//                .dob(request.getDob())
//                .build();

        return userMapper.toUserResponse(userRepository.save(user));
    }
    public UserResponse getMyInfo() {
        var contex = SecurityContextHolder.getContext();   // Lấy tất cả thông tin trong contex hiện tại ra,
        String name = contex.getAuthentication().getName();

        User user = userRepository.findByUsername(name).orElseThrow(
                () -> new RuntimeException(ErrorCode.USER_NOT_EXISTED.getMessage())
        );

        return userMapper.toUserResponse(user);

    }

    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        userMapper.updateUser(user, request);
        if(user.getPassword() != null && !user.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        var roles = roleRepository.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(roles));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.deleteById(userId);
    }

//    @PreAuthorize("hasRole('ROLE_ADMIN')") // chi co role moi truy cap dc
    @PreAuthorize("hasAuthority('CREATE_DATA')")  // co the truy cap dc ca perrmissuion
    public List<UserResponse> getUsers(){

//        return userRepository.findAll();
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse).toList();
    }

    @PostAuthorize("returnObject.username == authentication.name")
    public UserResponse getUser(String id){
        log.info("In method get user by ID");
        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found")));
    }
}
