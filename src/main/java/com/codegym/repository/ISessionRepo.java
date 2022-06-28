package com.codegym.repository;

import com.codegym.model.entity.AddMoney;
import com.codegym.model.entity.Notification;
import com.codegym.model.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ISessionRepo extends JpaRepository<Session, Long> {

    @Query( nativeQuery = true, value = "select * from session where idUSer = ?" )
    Optional<Session> getId(Long idUser);
}
