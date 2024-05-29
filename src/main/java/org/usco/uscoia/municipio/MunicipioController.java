package org.usco.uscoia.municipio;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class MunicipioController {

	@Autowired
	MunicipioRepository municipioRepository;
	
	@Autowired
	JdbcMunicipioRepository jdbcMunicipioRepository;
//	ReportServiceMunucipio reportService;
	
	@GetMapping("/municipio/reporte/{id}")
	public void generateReport(HttpServletResponse response, @PathVariable int id)throws IOException, JRException, SQLException {
//		response.setContentType("application/x-download");
		response.setContentType("application/pdf");
//		response.setHeader("Content-Disposition", String.format("attachment; filename=\"municipio.pdf\""));
		response.setHeader("Content-Disposition", String.format("inline; filename=\"testmunicipio.pdf\""));
		OutputStream out = response.getOutputStream();
		
		JasperPrint jasperPrint = jdbcMunicipioRepository.exportPDF(id);
    	JasperExportManager.exportReportToPdfStream(jasperPrint, out);
//		reportService.exportReport(id, out);
	}
	
	@PostMapping("/municipio")
	public ResponseEntity<String> createMunicipio(@RequestBody Municipio municipio) {
		try {
			municipioRepository
					.create(new Municipio(municipio.getDepartamento(), municipio.getNombre(), municipio.getAcronimo()));
			return new ResponseEntity<>("Municipio creado con exito", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/municipio")
	public ResponseEntity<List<Municipio>> getAllMunicipios() {
		try {
			ArrayList<Municipio> municipios = new ArrayList<Municipio>();

			municipioRepository.read().forEach(municipios::add);

			if (municipios.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(municipios, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/municipio/{id}")
	public ResponseEntity<String> updateMunicipio(@PathVariable("id") int id, 
			@RequestBody Municipio municipio) {
		try {
			municipioRepository.update(id,
					new Municipio(municipio.getDepartamento(), municipio.getNombre(), municipio.getAcronimo()));
			return new ResponseEntity<>("Municipio actualizado con exito", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/municipio/{id}")
	public ResponseEntity<String> deleteMunicipio(@PathVariable("id") int id) {
		try {
			municipioRepository.delete(id);
			return new ResponseEntity<>("Municipio eliminado con exito", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
