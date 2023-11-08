package com.web.app.Service;

import java.util.List;

import com.web.app.Model.Informe;

public interface InformeService {
    List<Informe> getAllInformes();
    Informe getInformeById(Long idInforme);
    Informe createInforme(Informe informe);
    Informe updateInforme(Long idInforme, Informe informe);
    void deleteInforme(Long idInforme);
    Informe getInformesByIdInforme1(String idInforme1);
}