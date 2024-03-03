package com.example.demo.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.modelo.DTO.ReservaDTO;
import com.example.demo.service.IReservaService;
import com.example.demo.service.to.ReporteTO;

@RestController
@RequestMapping(path = "/reservas") // http://localhost:8080/reservas
@CrossOrigin // "http://localhost:4200
public class ReservaControllerRestFul {

    @Autowired
    private IReservaService iReservaService;

    // reportes , por fechas de inicio y fin
     @GetMapping(path = "/reporte", produces = "application/json")
     public ResponseEntity<List<ReporteTO>> reporte(@RequestParam String fechaInicio, @RequestParam String fechaFin) {
    	 
    	 
    
    	 
    	DateTimeFormatter isoFecha= DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	//var fechaa= LocalDate.parse(fechaFin, isoFecha);
    	var fechaf=LocalDateTime.parse(fechaFin, isoFecha);
    	// LocalDate a= LocalDate.parse(fechaFin);
    	 
       //  List<ReporteTO> lista = this.iReservaService.Reporte(fechaInicio, fechaFin);
         System.out.println(isoFecha);
         System.out.println(fechaf);
        // System.out.println(lista);
         return ResponseEntity.status(HttpStatus.OK).body(null);

     }

     @PutMapping(path = "/retiro")
     public void retiroReservado(@RequestParam String numeroReserva){
    	System.out.println("sefsen"+numeroReserva);
        this.iReservaService.retirarVehiculo(numeroReserva);


     } 
     
     @GetMapping(path = "/{numeroReserva}", produces = "application/json")
 	public ResponseEntity<ReservaDTO> buscarReserva(@PathVariable String numeroReserva) {


    	 ReservaDTO reserva = this.iReservaService.buscarAutoReserva(numeroReserva);
 	  
    	 System.out.println("rrrr"+reserva);
 		return ResponseEntity.status(HttpStatus.OK).body(reserva);
 		// http://localhost:8080/API/v1.0/Matricula/estudiantes/{id} GET
 	}
     
     



    
}
