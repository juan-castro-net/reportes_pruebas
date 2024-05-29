package org.usco.uscoia.departamento;

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


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class DepartamentoController {

	@Autowired
	DepartamentoRepository departamentoRepository;

	@PostMapping("/departamento")
	public ResponseEntity<String> createDepartamento(@RequestBody Departamento departamento) {
		try {
			departamentoRepository.create(
					new Departamento(departamento.getPais(), departamento.getNombre(), departamento.getAcronimo()));
			return new ResponseEntity<>("Departamento creado con exito", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/departamento")
	public ResponseEntity<List<Departamento>> getAllDepartamentos() {
		try {
			ArrayList<Departamento> departamentos = new ArrayList<Departamento>();

			departamentoRepository.read().forEach(departamentos::add);

			if (departamentos.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(departamentos, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/departamento/{id}")
	public ResponseEntity<String> updateDepartamento(@PathVariable("id") int id,
			@RequestBody Departamento departamento) {
		try {
			departamentoRepository.update(id,
					new Departamento(departamento.getPais(), departamento.getNombre(), departamento.getAcronimo()));
			return new ResponseEntity<>("Departamento actualizado con exito", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/departamento/{id}")
	public ResponseEntity<String> deleteDepartamento(@PathVariable("id") int id) {
		try {
			departamentoRepository.delete(id);
			return new ResponseEntity<>("Departamento eliminado con exito", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
