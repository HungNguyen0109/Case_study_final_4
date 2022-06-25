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

import javax.validation.Valid;
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
    public ResponseEntity<?> register(@Valid @RequestBody SignUpForm user) {
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            return new ResponseEntity<>("Confirm password not match, please try again!",HttpStatus.OK);
        }
        if (user.getUsername().trim().equals("") || user.getName().trim().equals("") || user.getPassword().trim().equals("") || user.getEmail().trim().equals("")){
            return new ResponseEntity<>("Please enter all the fields!", HttpStatus.OK);
        }
        if (userService.existsByUsername(user.getUsername())){
            return new ResponseEntity<>("This username is already exist, please try again!",HttpStatus.OK);
        }
        if (userInfoService.existsByEmail(user.getEmail())){
            return new ResponseEntity<>("This email is already exist", HttpStatus.OK);
        }
        if (!user.getEmail().matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")){
            return new ResponseEntity<>("Not an email, try again!", HttpStatus.OK);

        }
        else {
            String avatar = "https://firebasestorage.googleapis.com/v0/b/imagesave-e6d91.appspot.com/o/images%2Fdefault-avatar.png?alt=media&token=8e308959-ef07-4512-ab12-5432a170b74b";
            User user1 = new User(user.getUsername(), user.getPassword());
            userService.save(user1);
            UserInfo userInfo = new UserInfo(
                    user.getName(),
                    avatar,
                    user.getPhoneNumber(),
                    user.getEmail(),
                    user.getBirthDay(),
                    user.getAddress(),
                    user1
            );
            userInfoService.save(userInfo);
            return new ResponseEntity<>("Create success!", HttpStatus.CREATED);
        }
    }

    @PostMapping("/changePassword/{id}")
    public ResponseEntity<?> changePassword(@PathVariable Long id, @RequestBody ChangePassword changePassword) {
        Optional<User> user = this.userService.findById(id);
        String newPassword;
        String oldPassword = changePassword.getOldPassword();
        if (!user.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            if (changePassword.getNewPassword().trim().equals("")){
                return new ResponseEntity<>("Password cannot be empty!",HttpStatus.OK);
            }
            if (passwordEncoder.matches(oldPassword, user.get().getPassword())) {
                if (changePassword.getNewPassword().equals(changePassword.getConfirmNewPassword())) {
                    newPassword = changePassword.getNewPassword();
                } else {
                    return new ResponseEntity<>("Confirm password not match!",HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<>("Current password not true!",HttpStatus.OK);
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
