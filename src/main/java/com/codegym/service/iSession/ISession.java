package com.codegym.service.iSession;

import com.codegym.model.entity.InOut;
import com.codegym.model.entity.Session;
import com.codegym.service.IGeneralService;

import java.util.Optional;

public interface ISession extends IGeneralService<Session> {
    Optional<Session> getId(Long idUser);
}
