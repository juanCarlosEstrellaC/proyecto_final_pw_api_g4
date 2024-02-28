package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.IReservaService;
import com.example.demo.service.to.ReporteTO;

@RestController
@RequestMapping(path = "/reservas") // http://localhost:8080/reservas
@CrossOrigin(value = "http://localhost:8080") // "http://localhost:4200
public class ReservaControllerRestFul {

    @Autowired
    private IReservaService iReservaService;

    // reportes , por fechas de inicio y fin
     @GetMapping(path = "/reporte", produces = "application/json")
     public ResponseEntity<List<ReporteTO>> reporte(@RequestParam LocalDate fechaInicio, @RequestParam LocalDate fechaFin) {
         List<ReporteTO> lista = this.iReservaService.Reporte(fechaInicio, fechaFin);

         return ResponseEntity.status(HttpStatus.OK).body(lista);

     }

     @PutMapping(path = "/retiro")
     public void retiroReservado(@RequestParam String numeroReserva){
        this.iReservaService.retirarVehiculo(numeroReserva);


     } 



    
}
