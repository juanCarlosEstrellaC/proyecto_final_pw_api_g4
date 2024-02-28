package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.repository.modelo.Cliente;


import com.example.demo.repository.modelo.Vehiculo;

import com.example.demo.service.IClienteService;
import com.example.demo.service.IReservaService;
import com.example.demo.service.IVehiculoService;

//METODOS ESTÁTICOS
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Controller
@RequestMapping("/reportes")
public class ReporteController {

	@Autowired
	private IClienteService iClienteService;

	@Autowired
	private IVehiculoService iVehiculoService;

	@Autowired
	private IReservaService reservaService;

	@GetMapping("/inicio")
	public String paginaPrincipal(Cliente cliente, Vehiculo vehiculo) {
		return "vistaInicio";
	}

/*
	@GetMapping("/reporteReservas")
	public String reporteReserva(ReservaTo reserva, Model modelo) {

		List<ReservaTo> listaReservas = this.reservaService.buscarPorFechas(reserva.getFechaInicio(),
				reserva.getFechaFin());
		modelo.addAttribute("listaReservas", listaReservas);
		return "vistaReporteReservas";
	}

	@GetMapping("/buscarReservas")
	public String buscarReservas(ReservaTo reservaTo) {
		return "vistaBuscarReportes";
	}
	*/
}
