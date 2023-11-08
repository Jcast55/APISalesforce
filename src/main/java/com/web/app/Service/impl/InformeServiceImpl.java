package com.web.app.Service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.Dao.InformeRepository;
import com.web.app.Model.Informe;
import com.web.app.Service.InformeService;

import java.util.List;

@Service
public class InformeServiceImpl implements InformeService {
    private final InformeRepository informeRepository;

    @Autowired
    public InformeServiceImpl(InformeRepository informeRepository) {
        this.informeRepository = informeRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Informe> getAllInformes() {
        return informeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Informe getInformeById(Long idInforme) {
        return informeRepository.findById(idInforme).orElse(null);
    }

    @Override
    @Transactional
    public Informe createInforme(Informe informe) {
        return informeRepository.save(informe);
    }

    @Override
    @Transactional
    public Informe updateInforme(Long idInforme, Informe informe) {
        if (informeRepository.existsById(idInforme)) {
            informe.setIdInforme(idInforme);
            return informeRepository.save(informe);
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteInforme(Long idInforme) {
        informeRepository.deleteById(idInforme);

    }

    @Override
    @Transactional(readOnly = true)
    public Informe getInformesByIdInforme1(String idInforme1) {
        return informeRepository.findByIdInforme1(idInforme1);
    }

}