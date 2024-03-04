package com.example.demo.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.modelo.Reserva;
import com.example.demo.repository.modelo.Vehiculo;
import com.example.demo.service.IClienteService;
import com.example.demo.service.ICobroService;
import com.example.demo.service.IReservaService;
import com.example.demo.service.IVehiculoService;
import com.example.demo.service.to.ClienteTO;
import com.example.demo.service.to.DatosReservaTO;
import com.example.demo.service.to.ReservaTO;


@RestController//Servicio
@RequestMapping(path="/clientes")
@CrossOrigin//(value="http://localhost:8080")
public class ClienteControllerRestFul {

	@Autowired
	private IClienteService iClienteService;

	@Autowired
	private IVehiculoService vehiculoService;

	@Autowired
	private IReservaService reservaService;

	@Autowired
	private ICobroService cobroService;

	//BUSCAR TODOS LOS CLIENTES
	//http://localhost:8082/API/v1.0/Renta/clientes
	
	// Buscar Clientes desde el cliente.
		// http://localhost:8082/API/v1.0/Renta/clientes
		@GetMapping(path = "/{cedula}", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<ClienteTO> buscarClienteCedula(@PathVariable String cedula) {
			System.out.println(cedula);
			ClienteTO cliente = this.iClienteService.buscarPorCedula(cedula);
			System.out.println(cedula);
			System.out.println(cliente);
			return ResponseEntity.status(HttpStatus.OK).body(cliente);
	 
		}
	
	@GetMapping
	public  ResponseEntity<List<ClienteTO>> buscarTodos() {
		List<ClienteTO> lista = this.iClienteService.buscarTodos();
		HttpHeaders cabeceras = new HttpHeaders();
		cabeceras.add("mensaje_242", "Lista consultada de manera satisfactoria.");
		cabeceras.add("mensaje_info", "El sistema va estar en mantenimiento el fin de semana.");
		return new ResponseEntity<>(lista, cabeceras, 242); //todo lo que no es de ka data principal va en al cabecera
	}

	
	//buscar reserva por numero
	@GetMapping(path="/{numero}/reservas")
	public ResponseEntity<List<ReservaTO>> consultarReservasPorNumero(@PathVariable Integer numero){
		List<ReservaTO> lista = this.reservaService.buscarReservasPorIdCliente(numero);
		return ResponseEntity.status(HttpStatus.OK).body(lista);
	}
	
	//1.b: RESERVAR VEHICULO
		@PostMapping(path="/generarReserva",consumes =MediaType.APPLICATION_JSON_VALUE)
		public void reservarAuto(@RequestBody DatosReservaTO datoReserva) {
			Reserva nuevaReserva = this.reservaService.reservarVehiculo(datoReserva.getPlaca(), datoReserva.getCedula(),
					datoReserva.getFechaInicio(), datoReserva.getFechaFin());
			BigDecimal precio = this.vehiculoService.buscarPorPlaca(datoReserva.getPlaca()).getRenta();
			// Reserva
			// datos=this.reservaService.buscarAutoReserva(datosReserva.getCedula());
			System.out.println(precio);
			this.cobroService.realizarPago(datoReserva.getTarjeta(), precio, nuevaReserva);
			//modelo.addAttribute("datosReserva", datoReserva);
			//return "vistaListaReservas";
		}
	
		// 1.c REGISTRARSE COMO CLLENTE
		// http://localhost:8082/API/v1.0/Renta/clientes
		@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Integer> guardar(@RequestBody ClienteTO cliente) {
			System.out.println("ClienteTO: " + cliente);
			try {
				boolean registroExitoso = this.iClienteService.registro(cliente);
				if (registroExitoso) {
					return ResponseEntity.status(HttpStatus.CREATED).body(1);
				} else {
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(0);

				}
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(0);
			}
		}

	
	//1.d ACTUALIZAR CLIENTE, A EXCEPCIÓN DE LA CÉDULA
	//http://localhost:8082/API/v1.0/Renta/clientes/id
	@PatchMapping(path="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void actualizarParcial(@RequestBody ClienteTO cliente, @PathVariable Integer id) {
		this.iClienteService.actualizarParcial(cliente.getNombre(), cliente.getApellido(), cliente.getFechaNacimiento(),cliente.getGenero() ,cliente.getRegistro(), id);
	}

	/*
	//ACTUALIZAR UN CLIENTE, actualización completa
	@PutMapping(path="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void actualizarCliente(@RequestBody Cliente cliente, @PathVariable Integer id ){

		cliente.setId(id);
		this.iClienteService.actualizar(cliente);
	}
	*/
	
	
	
	//ELIMINCAR CLIENTE
	 @DeleteMapping(path="/{id}")
	    public void borrar(@PathVariable Integer id){
	        this.iClienteService.eliminar(id);
	    }

	//BUSCAR VEHICULOS
	@GetMapping("/buscarAutos")
	public String buscarAutosDisponibles(Model modelo, Vehiculo vehiculo) {

		List<Vehiculo> listaVehiculos = this.vehiculoService.vehiculosDisponibles(vehiculo.getMarca(),
				vehiculo.getModelo());
		modelo.addAttribute("vehiculos", listaVehiculos);
		return "vistaAutosDisponibles";
	}

	

	
	/*@GetMapping("/reservas")
	public String buscarAutosDisponibles(DatoReserva datoReserva, RedirectAttributes a) {

		boolean disponible = this.reservaService.buscarvehiculoDisponible(datoReserva.getPlaca(),
				datoReserva.getFechaInicio(), datoReserva.getFechaFin());

		if (disponible == false) {
			a.addFlashAttribute("error", "ATENCION: Este vehiculo No esta disponible");
			return "redirect:/clientes/mostrarDisponibles";
		}

		return "vistaReservar";
	}
	


	@GetMapping("/mostrarDisponibles")
	public String autosDisponibles(DatoReserva datoReserva) {
		return "vistaListaAutosDisponibles";
	}
	
	@GetMapping("/vehiculosClientes")
	public String vehiculosClientes(Vehiculo vehiculo) {
		return "vistaListaVehiculosCliente";
	}
	
	@GetMapping("/registroCliente")
	public String registro(Cliente cliente, Vehiculo vehiculo) {
		return "vistaRegistroCliente";
	}
	
	@GetMapping("/vistaActualizar")
	public String vistaACtualizar(Cliente cliente) {
		return "vistaActualizarCliente";
	}*/

}