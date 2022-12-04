package co.edu.iudigital.diplado.senado.controllers;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.iudigital.diplado.senado.modelos.Partido;
import co.edu.iudigital.diplado.senado.modelos.Proyecto;
import co.edu.iudigital.diplado.senado.repositorios.ProyectoRepositorio;

@RestController
@RequestMapping("api/proyecto")
public class ProyectoController {
	
	@Autowired
	private ProyectoRepositorio pr;
	
	@GetMapping("todos")
	public ResponseEntity<List<Proyecto>> getTodos(){
		return new ResponseEntity<List<Proyecto>>(pr.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("porId/{id}")
	public ResponseEntity<Proyecto> getPorId(@PathVariable("id") int id){
		Proyecto p = pr.findById(id);
		if (p == null) {
			return new ResponseEntity<Proyecto>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Proyecto>(p, HttpStatus.FOUND);
		}
	}
	
	@GetMapping("porSenador/{id}")
	public ResponseEntity<List<Proyecto>> getPorSenador(@PathVariable("id") int id){
		List<Proyecto> p = pr.findBySenador(id);
		if (p == null) {
			return new ResponseEntity<List<Proyecto>>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Proyecto>>(p, HttpStatus.FOUND);
		}
	}
	
	@GetMapping("porPartido/{id}")
	public ResponseEntity<List<Proyecto>> getPorPartido(@PathVariable("id") int id){
		List<Proyecto> p = pr.findByPartido(id);
		if (p == null) {
			return new ResponseEntity<List<Proyecto>>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Proyecto>>(p, HttpStatus.FOUND);
		}
	}
	
	@GetMapping("porEstado/{id}")
	public ResponseEntity<List<Proyecto>> getPorEstado(@PathVariable("id") int id){
		List<Proyecto> p = pr.findByEstado(id);
		if (p == null) {
			return new ResponseEntity<List<Proyecto>>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Proyecto>>(p, HttpStatus.FOUND);
		}
	}
	
	@PostMapping("crearProyecto")
	public ResponseEntity<String> crearPartido(@RequestBody Proyecto p){
		if (p.getNombre() == null || p.getNombre().trim().isBlank()) {
			return new ResponseEntity<String>("{ \"mensaje\": \"Nombre esta vacio\" }", HttpStatus.BAD_REQUEST);
		} if (p.getSenador() == 0 || p.getPartido() == 0 || p.getEstado() == 0) {
			return new ResponseEntity<String>("{ \"mensaje\": \"Falta información necesaria (id de senador, partido o estado)\" }", HttpStatus.BAD_REQUEST);
		} else {
			p.setFecha_creacion(new Date(Date.from(Instant.now()).getTime()));
			p.setFecha_estado(new Date(Date.from(Instant.now()).getTime()));
			return new ResponseEntity<String>("{ \"mensaje\": \"Partido creado\" }", HttpStatus.CREATED);
		}		
	}
	
	@PostMapping("editarPartido")
	public ResponseEntity<String> editarPartido(@RequestBody Proyecto p){
		if (p.getId() == 0) {
			return new ResponseEntity<String>("{ \"mensaje\": \"Falta el ID del partido o es invalido\" }", HttpStatus.BAD_REQUEST);
		} else if (p.getNombre() == null || p.getNombre().trim().isBlank()) {
			return new ResponseEntity<String>("{ \"mensaje\": \"Nombre esta vacio\" }", HttpStatus.BAD_REQUEST);
		} else {
			Proyecto p2 = pr.findById(p.getId());
			if (p2 == null) {
				return new ResponseEntity<String>("{ \"mensaje\": \"El partido no existe\" }", HttpStatus.BAD_REQUEST);
			} else {
				pr.save(p);
				return new ResponseEntity<String>("{ \"mensaje\": \"Partido modificado\" }", HttpStatus.CREATED);
			}
		}		
	}

}
