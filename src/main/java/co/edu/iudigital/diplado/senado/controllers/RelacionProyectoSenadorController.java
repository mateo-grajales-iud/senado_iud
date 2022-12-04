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

import co.edu.iudigital.diplado.senado.modelos.RelacionProyectoSenador;
import co.edu.iudigital.diplado.senado.modelos.Senador;
import co.edu.iudigital.diplado.senado.repositorios.RelacionProyectoSenadorRepositorio;

@RestController
@RequestMapping("api/relacion")
public class RelacionProyectoSenadorController {
	
	@Autowired
	private RelacionProyectoSenadorRepositorio pr;
	
	@GetMapping("todos")
	public ResponseEntity<List<RelacionProyectoSenador>> getAllRelacion() {
		return new ResponseEntity<List<RelacionProyectoSenador>>(pr.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("porProyecto/{proyecto}")
	public ResponseEntity<List<RelacionProyectoSenador>> getPorNombre(@PathVariable("proyecto") int proyecto){
		return new ResponseEntity<List<RelacionProyectoSenador>>(pr.findByProyecto(proyecto), HttpStatus.OK);
	}
	
	@GetMapping("porSenador/{senador}")
	public ResponseEntity<List<RelacionProyectoSenador>> getPorSenador(@PathVariable("senador") int senador){
		return new ResponseEntity<List<RelacionProyectoSenador>>(pr.findBySenador(senador), HttpStatus.OK);
	}
	
	@GetMapping("porProyectoYSenador/{proyecto}/{senador}")
	public ResponseEntity<RelacionProyectoSenador> getPorCedula(@PathVariable("proyecto") int proyecto, @PathVariable("senador") int senador){
		RelacionProyectoSenador s = pr.findByProyectoAndSenador(proyecto, senador);
		if (s == null) { 
			return new ResponseEntity<RelacionProyectoSenador>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<RelacionProyectoSenador>(s, HttpStatus.FOUND);
		}
	}
	
	@PostMapping("crearRelacion")
	public ResponseEntity<String> crearSenador(@RequestBody RelacionProyectoSenador s){
		if (s.getProyecto() == 0|| s.getSenador() == 0 || s.getVoto() == 0) {
			return new ResponseEntity<String>("{ \"mensaje\": \"Falta información necesaria\" }", HttpStatus.BAD_REQUEST);
		} else {
			RelacionProyectoSenador s1 = pr.findByProyectoAndSenador(s.getProyecto(), s.getSenador());
			if (s1 != null) {
				return new ResponseEntity<String>("{ \"mensaje\": \"El senador ya tiene una voto registrado\" }", HttpStatus.BAD_REQUEST);
			} else {
				pr.save(s);
				return new ResponseEntity<String>("{ \"mensaje\": \"Voto registrado\" }", HttpStatus.CREATED);
			}
		}
	}	

}
