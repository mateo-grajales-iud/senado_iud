package co.edu.iudigital.diplado.senado.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.iudigital.diplado.senado.modelos.Partido;

public interface PartidoRepositorio extends JpaRepository<Partido, Integer>{
	
	public List<Partido> findAll();
	
	public Partido findById(int id);
	
	public List<Partido> findByNombreContaining(String nombre);
	
	public Partido findByNombre(String nombre);

}
