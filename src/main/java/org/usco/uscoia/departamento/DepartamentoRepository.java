package org.usco.uscoia.departamento;


import java.util.List;

public interface DepartamentoRepository {
	
	public int create (Departamento departamento);
	
	public List<Departamento> read ();
	
	public int update (int id, Departamento departamento);
	
	public int delete (int id);

}
