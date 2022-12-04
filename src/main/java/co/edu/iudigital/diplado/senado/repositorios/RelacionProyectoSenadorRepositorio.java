package co.edu.iudigital.diplado.senado.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.iudigital.diplado.senado.modelos.RelacionProyectoSenador;

public interface RelacionProyectoSenadorRepositorio extends CrudRepository<RelacionProyectoSenador, Integer>{
	
	public List<RelacionProyectoSenador> findAll();
	
	public List<RelacionProyectoSenador> findByProyecto(int proyecto);
	
	public List<RelacionProyectoSenador> findBySenador(int senador);
	
	public RelacionProyectoSenador findByProyectoAndSenador(int proyecto, int senador);

}
