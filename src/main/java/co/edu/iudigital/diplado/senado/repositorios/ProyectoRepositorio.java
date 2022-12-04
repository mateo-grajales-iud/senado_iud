package co.edu.iudigital.diplado.senado.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.iudigital.diplado.senado.modelos.Proyecto;

public interface ProyectoRepositorio extends CrudRepository<Proyecto, Integer> {
	
	public List<Proyecto> findAll();
	
	public List<Proyecto> findBySenador(int senador);
	
	public List<Proyecto> findByPartido(int partido);
	
	public List<Proyecto> findByEstado(int estado);
	
	public Proyecto findById(int id);

}
