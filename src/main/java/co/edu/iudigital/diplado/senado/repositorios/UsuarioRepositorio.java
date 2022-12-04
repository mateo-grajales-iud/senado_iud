package co.edu.iudigital.diplado.senado.repositorios;

import org.springframework.data.repository.CrudRepository;

import co.edu.iudigital.diplado.senado.modelos.Usuario;

public interface UsuarioRepositorio extends CrudRepository<Usuario, Integer> {
	
	public Usuario findByCedula(String cedula);

}
