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

import co.edu.iudigital.diplado.senado.modelos.Departamento;
import co.edu.iudigital.diplado.senado.modelos.Partido;
import co.edu.iudigital.diplado.senado.modelos.Senador;
import co.edu.iudigital.diplado.senado.repositorios.DepartamentoRepositorio;
import co.edu.iudigital.diplado.senado.repositorios.SenadorRepositorio;

@RestController
@RequestMapping("api/senador")
public class SenadorController {

	@Autowired
	private SenadorRepositorio sr;

	@GetMapping("todos")
	public ResponseEntity<List<Senador>> getAllSenadores() {
		return new ResponseEntity<List<Senador>>(sr.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("porCedula/{cedula}")
	public ResponseEntity<Senador> getPorCedula(@PathVariable("cedula") String cedula){
		Senador s = sr.findByCedula(cedula);
		if (s == null) { 
			return new ResponseEntity<Senador>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Senador>(s, HttpStatus.FOUND);
		}
	}
	
	@GetMapping("porNombre/{nombre}")
	public ResponseEntity<List<Senador>> getPorNombre(@PathVariable("nombre") String nombre){
		return new ResponseEntity<List<Senador>>(sr.findByNombreContaining(nombre), HttpStatus.OK);
	}
	
	@GetMapping("porDepartamento/{departamento}")
	public ResponseEntity<List<Senador>> getPorDepartamento(@PathVariable("departamento") int departamento){
		return new ResponseEntity<List<Senador>>(sr.findByDepartamento(departamento), HttpStatus.OK);
	}
	
	@GetMapping("porPartido/{partido}")
	public ResponseEntity<List<Senador>> getPorPartido(@PathVariable("partido") int partido){
		return new ResponseEntity<List<Senador>>(sr.findByPartido(partido), HttpStatus.OK);
	}
	
	@PostMapping("crearSenador")
	public ResponseEntity<String> crearSenador(@RequestBody Senador s){
		if (s.getCedula() == null || s.getDepartamento() == 0 || s.getNombre() == null || s.getPartido() == 0) {
			return new ResponseEntity<String>("{ \"mensaje\": \"Falta información necesaria\" }", HttpStatus.BAD_REQUEST);
		} else {
			Senador s1 = sr.findByCedula(s.getCedula());
			if (s1 != null) {
				return new ResponseEntity<String>("{ \"mensaje\": \"El senador ya existe\" }", HttpStatus.BAD_REQUEST);
			} else {
				sr.save(s);
				return new ResponseEntity<String>("{ \"mensaje\": \"Senador creado\" }", HttpStatus.CREATED);
			}
		}
	}
	
	@PostMapping("editarSenador")
	public ResponseEntity<String> editarSenador(@RequestBody Senador s){
		if (s.getCedula() == null || s.getDepartamento() == 0 || s.getNombre() == null || s.getPartido() == 0) {
			return new ResponseEntity<String>("{ \"mensaje\": \"Falta información necesaria\" }", HttpStatus.BAD_REQUEST);
		} else {
			Senador s1 = sr.findByCedula(s.getCedula());
			if (s1 == null) {
				return new ResponseEntity<String>("{ \"mensaje\": \"El senador no existe\" }", HttpStatus.BAD_REQUEST);
			} else {
				sr.save(s);
				return new ResponseEntity<String>("{ \"mensaje\": \"Senador modificado\" }", HttpStatus.CREATED);
			}
		}
	}

}
