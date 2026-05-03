package com.starterkit.springboot.brs.controller.v1.api;

import com.starterkit.springboot.brs.controller.v1.request.UserSignupRequest;
import com.starterkit.springboot.brs.dto.model.user.UserDto;
import com.starterkit.springboot.brs.dto.response.Response;
import com.starterkit.springboot.brs.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Response> signup(@Valid @RequestBody UserSignupRequest request) {

        log.info("Signup request received for email: {}", request.getEmail());

        UserDto userDto = mapToDto(request);

        UserDto createdUser = userService.signup(userDto);

        return ResponseEntity
                .ok(Response.ok().setPayload(createdUser));
    }

    private UserDto mapToDto(UserSignupRequest request) {
        return new UserDto()
                .setEmail(request.getEmail())
                .setPassword(request.getPassword())
                .setFirstName(request.getFirstName())
                .setLastName(request.getLastName())
                .setMobileNumber(request.getMobileNumber())
                .setAdmin(false);
    }
}
