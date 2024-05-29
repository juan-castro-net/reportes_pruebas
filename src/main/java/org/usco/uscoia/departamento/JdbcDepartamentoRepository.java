package org.usco.uscoia.departamento;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcDepartamentoRepository implements DepartamentoRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	String CREATE_SQL = "INSERT INTO public.departamento(\n" + " pais, nombre, acronimo)\n" + "	VALUES (?, ?, ?)";
	String READ_SQL = "SELECT id, pais, nombre, acronimo\n" + "	FROM public.departamento";
	String UPDATE_SQL = "UPDATE public.departamento" + " SET pais=?, nombre=?, acronimo=?" + " WHERE id=?";
	String DELETE_SQL = "DELETE FROM public.departamento" + " WHERE id=?";

	@Override
	public int create(Departamento departamento) {
		return jdbcTemplate.update(CREATE_SQL,
				new Object[] { departamento.getPais(), departamento.getNombre(), departamento.getAcronimo() });
	}

	@Override
	public List<Departamento> read() {
		return jdbcTemplate.query(READ_SQL, BeanPropertyRowMapper.newInstance(Departamento.class));
	}

	@Override
	public int update(int id, Departamento departamento) {
		return jdbcTemplate.update(UPDATE_SQL, new Object[] { departamento.getPais(), departamento.getNombre(),
				departamento.getAcronimo(), id });
	}

	@Override
	public int delete(int id) {
		return jdbcTemplate.update(DELETE_SQL, id);
	}

}
