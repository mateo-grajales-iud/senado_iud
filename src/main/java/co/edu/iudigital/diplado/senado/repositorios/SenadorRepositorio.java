package co.edu.iudigital.diplado.senado.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.iudigital.diplado.senado.modelos.Senador;

public interface SenadorRepositorio extends CrudRepository<Senador, Integer>{
	
	public List<Senador> findAll();
	
	public List<Senador> findByNombreContaining(String nombre);
	
	public List<Senador> findByDepartamento(int departamento);
	
	public List<Senador> findByPartido(int partido);
	
	public Senador findById(int id);
	
	public Senador findByCedula(String cedula);

}
