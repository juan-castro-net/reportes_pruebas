package org.usco.uscoia.municipio;

public class Municipio {

	private int id;
	private int departamento;
	private String nombre;
	private String acronimo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDepartamento() {
		return departamento;
	}

	public void setDepartamento(int departamento) {
		this.departamento = departamento;
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
		return "Municipio [id=" + id + ", departamento=" + departamento + ", nombre=" + nombre + ", acronimo="
				+ acronimo + "]";
	}

	public Municipio() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Municipio(int departamento, String nombre, String acronimo) {
		super();
		this.departamento = departamento;
		this.nombre = nombre;
		this.acronimo = acronimo;
	}

	public Municipio(int id, int departamento, String nombre, String acronimo) {
		super();
		this.id = id;
		this.departamento = departamento;
		this.nombre = nombre;
		this.acronimo = acronimo;
	}

}
