package com.codegym.service.iconUser;

import com.codegym.model.entity.Icon;
import com.codegym.repository.IIconRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class IconSV implements IIconSV{
    @Autowired
    private IIconRepo iconRepo;
    @Override
    public List<Icon> findAll() {
        return iconRepo.findAll();
    }

    @Override
    public Icon save(Icon icon) {
        return null;
    }

    @Override
    public void removeById(Long id) {

    }

    @Override
    public Optional<Icon> findById(Long id) {
        return iconRepo.findById(id);
    }
}
