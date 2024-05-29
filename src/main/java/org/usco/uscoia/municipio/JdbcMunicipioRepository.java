package org.usco.uscoia.municipio;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jfree.util.Log;
//import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Repository
public class JdbcMunicipioRepository implements MunicipioRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	String CREATE_SQL = "INSERT INTO public.municipio(\n" + " departamento, nombre, acronimo)\n" + " VALUES (?, ?, ?)";
	String READ_SQL = "SELECT id, departamento, nombre, acronimo\n" + "	FROM public.municipio";
	String UPDATE_SQL = "UPDATE public.municipio" + " SET departamento=?, nombre=?, acronimo=?" + " WHERE id=?";
	String DELETE_SQL = "DELETE FROM public.municipio" + " WHERE id=?";

	
	public JasperPrint exportPDF(int departamentotId) throws SQLException, FileNotFoundException, JRException {
		try {
			Connection connection = jdbcTemplate.getDataSource().getConnection();
			File file = ResourceUtils.getFile("classpath:municipios3.jrxml");
			if (file.exists()) {
			    System.out.println("JRXML file found.");
			} else {
			    System.out.println("JRXML file not found.");
			}
			JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
			
			
			Map<String, Object> parameters = new HashMap<>() ;
			parameters.put("DEPARTAMENTO_ID", departamentotId) ;
			System.out.println(parameters);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
			return jasperPrint;
			
			
		} catch (Exception exception) {
			Log.error("Error");
			return null;
		}
	}
	
	@Override
	public int create(Municipio municipio) {
		return jdbcTemplate.update(CREATE_SQL,
				new Object[] { municipio.getDepartamento(), municipio.getNombre(), municipio.getAcronimo() });
	}

	@Override
	public List<Municipio> read() {
		return jdbcTemplate.query(READ_SQL, BeanPropertyRowMapper.newInstance(Municipio.class));
	}

	@Override
	public int update(int id, Municipio municipio) {
		return jdbcTemplate.update(UPDATE_SQL, new Object[] { municipio.getDepartamento(), municipio.getNombre(),
				municipio.getAcronimo(), id });
	}

	@Override
	public int delete(int id) {
		return jdbcTemplate.update(DELETE_SQL, id);
	}

}
