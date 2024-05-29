package org.usco.uscoia.departamento;

public class Departamento {
	private int id;
	private int pais;
	private String nombre;
	private String acronimo;

	public Departamento() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Departamento(int id, int pais, String nombre, String acronimo) {
		super();
		this.id = id;
		this.pais = pais;
		this.nombre = nombre;
		this.acronimo = acronimo;
	}

	public Departamento(int pais, String nombre, String acronimo) {
		super();
		this.pais = pais;
		this.nombre = nombre;
		this.acronimo = acronimo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPais() {
		return pais;
	}

	public void setPais(int pais) {
		this.pais = pais;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAcronimo() {
		return acronimo;
	}

	public void setAcronimo(String acronimo) {
		this.acronimo = acronimo;
	}

	@Override
	public String toString() {
		return "Departamento [id=" + id + ", pais=" + pais + ", nombre=" + nombre + ", acronimo=" + acronimo + "]";
	}
	

}
