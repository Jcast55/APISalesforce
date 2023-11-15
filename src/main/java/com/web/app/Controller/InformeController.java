package com.web.app.Controller;

import java.util.List;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;

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

   
@PutMapping(value = "/informes/{id}", consumes = {"application/json", "text/plain;charset=UTF-8"})
public ResponseEntity<Informe> updateInforme(
        @PathVariable Long id,
        @RequestBody(required = false) String informeData,
        @RequestHeader("Content-Type") String contentType) {

    // Verifica el tipo de contenido de la solicitud
    if (!contentType.equalsIgnoreCase("application/json") && !contentType.equalsIgnoreCase("text/plain;charset=UTF-8")) {
        // Devuelve una respuesta de tipo de contenido no admitido si es diferente
        return new ResponseEntity<>(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    // Si el cuerpo de la solicitud es nulo, puedes manejarlo según tus necesidades
    if (informeData == null) {
        // Devuelve una respuesta adecuada para el cuerpo de la solicitud nulo
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    Informe updatedInforme;

    // Procesa informeData según el tipo de contenido
    if (contentType.equalsIgnoreCase("application/json")) {
        // Procesa como JSON
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Informe informeObject = objectMapper.readValue(informeData, Informe.class);
            updatedInforme = informeService.updateInforme(id, informeObject);
        } catch (IOException e) {
            e.printStackTrace(); // Manejar la excepción según tus necesidades
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    } else {
        // Procesa como texto plano
        String[] parts = informeData.split("\n");
        
        // Asegúrate de manejar el array 'parts' adecuadamente
        // Crear un objeto Informe con los valores extraídos.

        // Ejemplo básico (asegúrate de manejar los índices correctamente):
        try {
            Informe informeObject = new Informe();
            informeObject.setHoraInicio(parseDate(parts[0]));
            informeObject.setIdInforme1(parts[1]);
            informeObject.setHorasTrabajadas(Integer.parseInt(parts[2]));
            informeObject.setHoraFinalizado(parseDate(parts[3]));
            informeObject.setIdContact(parts[4]);

            updatedInforme = informeService.updateInforme(id, informeObject);
        } catch (ParseException e) {
            e.printStackTrace(); // Manejar la excepción según tus necesidades
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    if (updatedInforme != null) {
        return new ResponseEntity<>(updatedInforme, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

// Método para convertir String a Date
private Date parseDate(String dateString) throws ParseException {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
    return dateFormat.parse(dateString);
}


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInforme(@PathVariable Long id) {
        informeService.deleteInforme(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/info/{idInforme1}")
    public ResponseEntity<Informe> getInformeByIdInforme1(@PathVariable String idInforme1,
            @RequestParam(required = false) String idInforme) {
        String id = idInforme != null ? idInforme : idInforme1;
        Informe informe = informeService.getInformesByIdInforme1(id);
        if (informe != null) {
            return new ResponseEntity<>(informe, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
