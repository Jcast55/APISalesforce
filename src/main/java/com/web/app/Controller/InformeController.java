package com.web.app.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.web.app.Model.Informe;
import com.web.app.Service.InformeService;

@RestController
@RequestMapping("/informes")
public class InformeController {
    private final InformeService informeService;

    @Autowired
    public InformeController(InformeService informeService) {
        this.informeService = informeService;
    }

    @GetMapping
    public ResponseEntity<List<Informe>> getAllInformes() {
        List<Informe> informes = informeService.getAllInformes();
        return new ResponseEntity<>(informes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Informe> getInformeById(@PathVariable Long id) {
        Informe informe = informeService.getInformeById(id);
        if (informe != null) {
            return new ResponseEntity<>(informe, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Informe> createInforme(@RequestBody Informe informe) {
        Informe createdInforme = informeService.createInforme(informe);
        return new ResponseEntity<>(createdInforme, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Informe> updateInforme(@PathVariable Long id, @RequestBody Informe informe) {
        Informe updatedInforme = informeService.updateInforme(id, informe);
        if (updatedInforme != null) {
            return new ResponseEntity<>(updatedInforme, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInforme(@PathVariable Long id) {
        informeService.deleteInforme(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

   @GetMapping("/info/{idInforme1}")
public ResponseEntity<Informe> getInformeByIdInforme1(@PathVariable String idInforme1) {
    Informe informe = informeService.getInformesByIdInforme1(idInforme1);
    if (informe != null) {
        return new ResponseEntity<>(informe, HttpStatus.OK);
    } else {
        // También manejar solicitudes con parámetros de consulta
        String idInformeFromQueryParam = ServletUriComponentsBuilder.fromCurrentRequest()
            .replacePath("/informes/info/{idInforme1}")
            .buildAndExpand(idInforme1)
            .toUri()
            .getQuery();
        
        if (idInformeFromQueryParam != null && !idInformeFromQueryParam.isEmpty()) {
            Informe informeFromQueryParam = informeService.getInformesByIdInforme1(idInformeFromQueryParam);
            if (informeFromQueryParam != null) {
                return new ResponseEntity<>(informeFromQueryParam, HttpStatus.OK);
            }
        }
        
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

}

