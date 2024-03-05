package com.example.demo.controller;

//METODOS ESTÁTICOS
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.modelo.DTO.VehiculoDTO;
import com.example.demo.service.IVehiculoService;
import com.example.demo.service.to.VehiculoTO2;

@RestController // Servicio
@RequestMapping(path = "/vehiculos")
@CrossOrigin // (value="http://localhost:8080")
public class VehiculoControllerRestful {

	@Autowired
	private IVehiculoService vehiculoService;

	// 1.a: BUSCAR VEHICULOS DISPONIBLES
	// http://localhost:8082/API/v1.0/Renta/vehiculos/buscarAutos?marca=nombreMarca&modelo=nombreModelo
	@GetMapping(path = "/buscarAutos22", produces = "application/json")
	public ResponseEntity<List<VehiculoDTO>> buscaPorMarcayModelo(@RequestParam(required = false) String marca,
			@RequestParam(required = false) String modelo) {

		List<VehiculoDTO> lista = new ArrayList<>();

		if (marca != null || modelo != null) {
			lista = this.vehiculoService.buscarVehiculosPorMarcayModelo(marca, modelo);
		}

		for (VehiculoDTO vehi : lista) {
			Link link = linkTo(methodOn(VehiculoControllerRestful.class).buscaPorMarcayModelo(marca, modelo))
					.withSelfRel();
			vehi.add(link);
		}

		return ResponseEntity.status(HttpStatus.OK).body(lista);
	}

	// 1.a: BUSCAR VEHICULOS DISPONIBLES
	// http://localhost:8082/API/v1.0/Renta/vehiculos/buscarAutos/vto?marca=nombreMarca&modelo=nombreModelo
	@GetMapping(path = "/buscarAutos/vto", produces = "application/json")
	public ResponseEntity<List<VehiculoTO2>> buscaPorMarcayModelo22(@RequestParam(required = false) String marca,
			@RequestParam(required = false) String modelo) {
		List<VehiculoTO2> lista = new ArrayList<>();
		if (marca != null || modelo != null) {
			lista = this.vehiculoService.buscarVehiculosPorMarcayModelo22(marca, modelo);
		}

		System.out.println(lista);
//		for (var vehi : lista) {
//			Link link = linkTo(methodOn(VehiculoControllerRestful.class).buscaPorMarcayModelo(marca, modelo))
//					.withSelfRel();
//			vehi.add(link);
//		}

		return ResponseEntity.status(HttpStatus.OK).body(lista);
	}
	
	
	// http://localhost:8082/API/v1.0/Renta/vehiculos/buscarReservas?marca=nombreMarca&modelo=nombreModelo
	@GetMapping(path = "/buscarReservas", produces = "application/json")
	public ResponseEntity<List<VehiculoTO2>> buscaPorMarcay(@RequestParam(required = false) String marca,
			@RequestParam(required = false) String modelo) {
		List<VehiculoTO2> lista = new ArrayList<>();
		if (marca != null || modelo != null) {
			lista = this.vehiculoService.buscarVehiculosPorMarcayModelo22(marca, modelo);
		}
		
		System.out.println(lista);
//		for (var vehi : lista) {
//			Link link = linkTo(methodOn(VehiculoControllerRestful.class).buscaPorMarcayModelo(marca, modelo))
//					.withSelfRel();
//			vehi.add(link);
//		}
		
		return ResponseEntity.status(HttpStatus.OK).body(lista);
	}
}
