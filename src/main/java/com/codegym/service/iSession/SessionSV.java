package com.codegym.service.iSession;

import com.codegym.model.entity.InOut;
import com.codegym.model.entity.Session;
import com.codegym.repository.IInOutRepo;
import com.codegym.repository.ISessionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class SessionSV implements ISession {
    @Autowired
    private ISessionRepo iSessionRepo;
    @Override
    public List<Session> findAll() {
        return iSessionRepo.findAll();
    }

    @Override
    public Session save(Session session) {
        return iSessionRepo.save(session);
    }

    @Override
    public void removeById(Long id) {
        iSessionRepo.deleteById(id);
    }
    @Override
    public Optional<Session> findById(Long id) {
        return iSessionRepo.findById(id);
    }

    @Override
    public Optional<Session> getId(Long idUser) {
        return iSessionRepo.getId(idUser);
    }
}
