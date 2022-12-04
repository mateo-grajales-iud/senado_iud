package co.edu.iudigital.diplado.senado.modelos;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "relacion_proyecto_senador")
@IdClass(RelacionProyectoSenador.class)
public class RelacionProyectoSenador implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private int proyecto;
	
	@Id
	private int senador;
	
	private int voto;
	
	public RelacionProyectoSenador() {}

	public RelacionProyectoSenador(int proyecto, int senador, int voto) {
		this.proyecto = proyecto;
		this.senador = senador;
		this.voto = voto;
	}

	public int getProyecto() {
		return proyecto;
	}

	public void setProyecto(int proyecto) {
		this.proyecto = proyecto;
	}

	public int getSenador() {
		return senador;
	}

	public void setSenador(int senador) {
		this.senador = senador;
	}

	public int getVoto() {
		return voto;
	}

	public void setVoto(int voto) {
		this.voto = voto;
	}

}
