package co.edu.iudigital.diplado.senado.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Senador {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(length = 100)
	private String nombre;
	
	@Column(length = 12)
	private String cedula;
	
	private int departamento;
	
	private int partido;
	
	private boolean activo;
	
	public Senador() {}

	public Senador(int id, String nombre, String cedula, int departamento, int partido, boolean activo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.cedula = cedula;
		this.departamento = departamento;
		this.partido = partido;
		this.activo = activo;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDepartamento() {
		return departamento;
	}

	public void setDepartamento(int departamento) {
		this.departamento = departamento;
	}

	public int getPartido() {
		return partido;
	}

	public void setPartido(int partido) {
		this.partido = partido;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

}
