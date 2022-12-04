package co.edu.iudigital.diplado.senado.modelos;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Proyecto {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private int id;

	@Column(length = 100)
	private String nombre;

	private int senador;

	private int partido;

	private int estado;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date fechaEstado;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date fechaCreacion;

	public Proyecto() {
	}

	public Proyecto(int id, String nombre, int senador, int partido, int estado, Date fecha_estado,
			Date fecha_creacion) {
		this.id = id;
		this.nombre = nombre;
		this.senador = senador;
		this.partido = partido;
		this.estado = estado;
		this.fechaEstado = fecha_estado;
		this.fechaCreacion = fecha_creacion;
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

	public int getSenador() {
		return senador;
	}

	public void setSenador(int senador) {
		this.senador = senador;
	}

	public int getPartido() {
		return partido;
	}

	public void setPartido(int partido) {
		this.partido = partido;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Date getFecha_estado() {
		return fechaEstado;
	}

	public void setFecha_estado(Date fecha_estado) {
		this.fechaEstado = fecha_estado;
	}

	public Date getFecha_creacion() {
		return fechaCreacion;
	}

	public void setFecha_creacion(Date fecha_creacion) {
		this.fechaCreacion = fecha_creacion;
	}

}
