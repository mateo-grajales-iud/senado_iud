package co.edu.iudigital.diplado.senado.controllers;

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
import co.edu.iudigital.diplado.senado.repositorios.PartidoRepositorio;

@RestController
@RequestMapping("api/partido")
public class PartidoController {
	
	@Autowired
	private PartidoRepositorio pr;
	
	@GetMapping("todos")
	public ResponseEntity<List<Partido>> getTodos(){
		return new ResponseEntity<List<Partido>>(pr.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("porId/{id}")
	public ResponseEntity<Partido> getPorId(@PathVariable("id") int id){
		Partido p = pr.findById(id);
		if (p == null) {
			return new ResponseEntity<Partido>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Partido>(p, HttpStatus.FOUND);
		}
	}
	
	@GetMapping("porNombre/{nombre}")
	public ResponseEntity<List<Partido>> getPorId(@PathVariable("nombre") String nombre){
		List<Partido> p = pr.findByNombreContaining(nombre);
		if (p == null || p.size() == 0) {
			return new ResponseEntity<List<Partido>>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Partido>>(p, HttpStatus.FOUND);
		}
	}
	
	@PostMapping("crearPartido")
	public ResponseEntity<String> crearPartido(@RequestBody Partido p){
		if (p.getNombre() == null || p.getNombre().trim().isBlank()) {
			return new ResponseEntity<String>("{ \"mensaje\": \"Nombre esta vacio\" }", HttpStatus.BAD_REQUEST);
		} else {
			Partido p2 = pr.findByNombre(p.getNombre().trim());
			if (p2 != null) {
				return new ResponseEntity<String>("{ \"mensaje\": \"El partido ya existe\" }", HttpStatus.BAD_REQUEST);
			} else {
				pr.save(p);
				return new ResponseEntity<String>("{ \"mensaje\": \"Partido creado\" }", HttpStatus.CREATED);
			}
		}		
	}
	
	@PostMapping("editarPartido")
	public ResponseEntity<String> editarPartido(@RequestBody Partido p){
		if (p.getId() == 0) {
			return new ResponseEntity<String>("{ \"mensaje\": \"Falta el ID del partido o es invalido\" }", HttpStatus.BAD_REQUEST);
		} else if (p.getNombre() == null || p.getNombre().trim().isBlank()) {
			return new ResponseEntity<String>("{ \"mensaje\": \"Nombre esta vacio\" }", HttpStatus.BAD_REQUEST);
		} else {
			Partido p2 = pr.findById(p.getId());
			if (p2 == null) {
				return new ResponseEntity<String>("{ \"mensaje\": \"El partido no existe\" }", HttpStatus.BAD_REQUEST);
			} else {
				pr.save(p);
				return new ResponseEntity<String>("{ \"mensaje\": \"Partido modificado\" }", HttpStatus.CREATED);
			}
		}		
	}

}
