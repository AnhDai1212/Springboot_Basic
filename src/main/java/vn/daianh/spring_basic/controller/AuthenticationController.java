package vn.daianh.spring_basic.controller;

import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.daianh.spring_basic.dto.request.ApiResponse;
import vn.daianh.spring_basic.dto.request.AuthenticationRequest;
import vn.daianh.spring_basic.dto.request.IntrospecRequest;
import vn.daianh.spring_basic.dto.response.AuthenticationResponse;
import vn.daianh.spring_basic.dto.response.IntrospectResponse;
import vn.daianh.spring_basic.service.AuthenticationService;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

    AuthenticationService authenticationService;
    @PostMapping("/token")
    public ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){

//        AuthenticationResponse result = authenticationService.authenticate(request);   // Cách sử dụng builder (hơi khó hiểu)
//        return ApiResponse.<AuthenticationResponse>builder()
//                .result(result)
//                .build();
      AuthenticationResponse result = authenticationService.authenticate(request);
      ApiResponse<AuthenticationResponse> apiResponse = new ApiResponse<>();
      apiResponse.setResult(result );
      return apiResponse;
    }

    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospecRequest request) throws ParseException, JOSEException {

//        var result = authenticationService.introspect(request);   // Cách này vẫn được sử dụng builder (hơi khó hiểu)
//        return ApiResponse.<IntrospectResponse>builder()
//                .result(result)
//                .build();
        var result = authenticationService.introspect(request);
        ApiResponse<IntrospectResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(result);
        return apiResponse;
    }
}
