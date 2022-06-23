package com.codegym.service.notification;

import com.codegym.model.entity.Notification;
import com.codegym.service.IGeneralService;

import java.util.List;

public interface INotificationService extends IGeneralService<Notification> {
    List<Notification> findAllNotification(Long id);
}
