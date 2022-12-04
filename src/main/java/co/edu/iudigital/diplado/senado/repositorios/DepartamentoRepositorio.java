package co.edu.iudigital.diplado.senado.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.iudigital.diplado.senado.modelos.Departamento;

public interface DepartamentoRepositorio extends JpaRepository<Departamento, Integer>{
	
	public List<Departamento> findAll();
}
