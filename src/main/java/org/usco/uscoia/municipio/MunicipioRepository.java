package org.usco.uscoia.municipio;

import java.util.List;

public interface MunicipioRepository {
	
	public int create(Municipio municipio);

	public List<Municipio> read();

	public int update(int id, Municipio municipio);

	public int delete(int id);

}
