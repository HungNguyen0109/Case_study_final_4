package com.codegym.controller;
import com.codegym.model.dto.ChangePassword;
import com.codegym.model.dto.JwtResponse;
import com.codegym.model.dto.SignUpForm;
import com.codegym.model.entity.User;
import com.codegym.model.entity.UserInfo;
import com.codegym.service.JwtService;
import com.codegym.service.user.IUserService;
import com.codegym.service.userInfo.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        //Kiểm tra username và pass có đúng hay không
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        //Lưu user đang đăng nhập vào trong context của security
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = userService.findByUserName(user.getUsername());
        return ResponseEntity.ok(new JwtResponse(currentUser.getId(), jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @PostMapping("/register")
    public ResponseEntity<User> register( @RequestBody SignUpForm user) {
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String avatar = "avatar.jpg";
        User user1 = new User(user.getUsername(), user.getPassword());
        userService.save(user1);
        UserInfo userInfo = new UserInfo(
                user.getName(),
                avatar,
                user.getPhoneNumber(),
                user.getBirthDay(),
                user.getAddress(),
                user1
        );
        userInfoService.save(userInfo);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }

    @PostMapping("/changePassword/{id}")
    public ResponseEntity<User> changePassword(@PathVariable Long id, @RequestBody ChangePassword changePassword) {
        Optional<User> user = this.userService.findById(id);
        String newPassword;
        String oldPassword = changePassword.getOldPassword();
        if (!user.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            if (passwordEncoder.matches(oldPassword, user.get().getPassword())) {
                if (changePassword.getNewPassword().equals(changePassword.getConfirmNewPassword())) {
                    newPassword = changePassword.getNewPassword();
                } else {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        user.get().setPassword(newPassword);
        user.get().setId(id);
        this.userService.save(user.get());
        return new ResponseEntity<>(user.get(), HttpStatus.OK);

    }

    @GetMapping("/username/{username}")
    public ResponseEntity<Boolean> usernameExitCheck(@PathVariable String username) {
        Boolean check = false;
        List<User> users = this.userService.findAll();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                check = true;
                break;
            }
        }
        return new ResponseEntity<>(check, HttpStatus.OK);
    }
}
