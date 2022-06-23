package com.codegym.service.inout;

import com.codegym.model.entity.InOut;
import com.codegym.service.IGeneralService;

public interface IInOutSV extends IGeneralService<InOut> {
    Integer getInFlow(Long idWallet, int month, int year);

    Integer getOutFlow(Long idWallet, int month, int year);
}
