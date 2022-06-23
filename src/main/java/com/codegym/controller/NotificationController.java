package com.codegym.controller;

import com.codegym.model.entity.Notification;
import com.codegym.service.notification.INotificationService;
import com.codegym.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification")
@CrossOrigin("*")
public class NotificationController {
    @Autowired
    private INotificationService notificationService;

    @Autowired
    private IUserService userService;

    @GetMapping("/list/{userId}")
    public ResponseEntity<List<Notification>> findAllByUser(@PathVariable Long userId) {
        return new ResponseEntity<>(notificationService.findAllNotification(userId), HttpStatus.OK);
    }
}
