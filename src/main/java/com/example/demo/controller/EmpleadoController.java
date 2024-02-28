package com.example.demo.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.repository.modelo.Cliente;
import com.example.demo.repository.modelo.Vehiculo;
import com.example.demo.service.IClienteService;
import com.example.demo.service.IReservaService;
import com.example.demo.service.IVehiculoService;
import com.example.demo.service.to.ClienteTO;
import com.example.demo.service.to.VehiculoTO;

//METODOS ESTÁTICOS
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController//Servicio
@RequestMapping("/empleados")
public class EmpleadoController {

	@Autowired
	private IClienteService iClienteService;
	@Autowired
	private IVehiculoService iVehiculoService;
	@Autowired
	private IReservaService reservaService;

	static List<String> genero = null;

	static {
		genero = new ArrayList<>();
		genero.add("F");
		genero.add("M");
	}

	//2.a: REGISTRAR CLIENTE
	//http://localhost:8082/API/v1.0/Renta/empleados
	@PostMapping(consumes =MediaType.APPLICATION_JSON_VALUE)
	public void registroComoEmpleado(@RequestBody ClienteTO cliente) {//en la inserción no se coloca el id, ni registro
		this.iClienteService.registroComoEmpleado(cliente);
	}


	//2.b: BUSCAR CLIENTES POR APELLIDO
	//http://localhost:8082/API/v1.0/Renta/empleados/buscarCliente?apellido=
	@GetMapping(path="/buscarCliente",produces = "application/json")
	public ResponseEntity<List<ClienteTO>> buscarClienteApellido(@RequestParam(required = false) String apellido) {
		List<ClienteTO> lista = this.iClienteService.buscarPorApellido(apellido);
			
			for(ClienteTO clie: lista) {
				Link link = linkTo(methodOn(ClienteControllerRestFul.class).consultarReservasPorNumero(clie.getId()))
						.withRel("reservas");
				clie.add(link);
			}	
			return ResponseEntity.status(HttpStatus.OK).body(lista); 
	}

	//2.b: ACTUALIZAR TODOS LOS DATOS DEL CLIENTE
	//http://localhost:8082/API/v1.0/Renta/empleados/id
	@PutMapping(path="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void actualizarClienteE(@RequestBody ClienteTO cliente,@PathVariable Integer id ){
		cliente.setId(id);
		this.iClienteService.actualizar(cliente);
		
	}

	//2.b: ELIMINAR CLIENTE
	//http://localhost:8082/API/v1.0/Renta/empleados/id
	@DeleteMapping(path="/{id}")
	public void borrarCliente(@PathVariable Integer id) {
		this.iClienteService.eliminar(id);
		
	}
	

	//--------------------------- VEHICULOS ----------------------------------------
	
	//2.c INGRESAR VEHICULO
	//http://localhost:8082/API/v1.0/Renta/empleados/insertarVehiculo
	@PostMapping(path="/insertarVehiculo",consumes =MediaType.APPLICATION_JSON_VALUE)
	public void insertarVehiculo(@RequestBody VehiculoTO vehiculo) {//en la inserción no se coloca el id
		this.iVehiculoService.insertar(vehiculo);
	}


	//2.d BUSCAR VEHICULO POR MARCA
	//http://localhost:8082/API/v1.0/Renta/empleados
	@GetMapping(path="/buscarVehiculo",produces = "application/json")
	public ResponseEntity<List<VehiculoTO>> buscarPorMarca(@RequestParam(required = false) String marca) {
		List<VehiculoTO> lista = this.iVehiculoService.buscarPorMarca(marca);
			
			for(VehiculoTO vehi: lista) {
				Link link = linkTo(methodOn(EmpleadoController.class).buscarPorMarca(marca))
						.withSelfRel();
				vehi.add(link);
			}	
			return ResponseEntity.status(HttpStatus.OK).body(lista); 
	}
	
	//2.d: ACTUALIZAR TODOS LOS DATOS DEL VEHICULO
	//http://localhost:8082/API/v1.0/Renta/empleados/actualizarVehiculo/{id}
	@PutMapping(path="/actualizarVehiculo/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
	public void actualizarVehiculo(@RequestBody VehiculoTO vehiculo,@PathVariable Integer id ) {//no se inserta el id
		vehiculo.setId(id);
		this.iVehiculoService.actualizar(vehiculo);
		//List<String> estadoL = Arrays.asList("Disponible", "No Disponible");
		
	}


	//2.d: ELIMINAR VEHICULO
	//http://localhost:8082/API/v1.0/Renta/empleados/borrar/{id}
	@DeleteMapping(path="/borrarVehiculo/{id}")
	public void borrarVehiculo(@PathVariable Integer id) {
		Vehiculo v = this.iVehiculoService.buscarPorId(id);
		if (v.getEstado().equals("No Disponible")) {
			System.out.println("No es posible eliminar");
		}
		this.iVehiculoService.eliminar(id);
	}




/*
	@GetMapping("/retirarVehiculo")
	public String retirarVehiculo(RetiroTo retiroTo, Model modelo) {

		// this.reservaService.retiro(retiroTo.getNumero());
		RetiroTo retiro = this.reservaService.buscarReservas(retiroTo.getNumero());
		modelo.addAttribute("retiro", retiro);
		return "vistaRetiroVehiculo";
	}
*/
/*	@PostMapping("/insertarRetiro")
	public String insertarRetiro(RetiroTo retiroTo) {
		this.reservaService.retiro(retiroTo.getNumero());
		// modelo.addAttribute("retiro", retiroTo);
		return "redirect:/empleados/inicio";
	}

	@GetMapping("/retirarSinReserva")
	public String reservavacia(Vehiculo vehiculo, RetiroTo retiroTo, Model modelo) {
		modelo.addAttribute("vehiculo", vehiculo);
		modelo.addAttribute("retiroTo", retiroTo);
		return "vistaSinReserva";
	}
	
	@GetMapping("/retiroVehiculo")
	public String retiroVehiculo(RetiroTo retiroTo) {
		return "vistaRetirarVehiculo";
	}
	*/
}