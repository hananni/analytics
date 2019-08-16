package zallpy.com.analytics.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import zallpy.com.analytics.model.Cliente;
import zallpy.com.analytics.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping("/")
	public List<Cliente> findAll() {
		return clienteService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable(value = "id") Long id) {
		return ResponseEntity.ok().body(clienteService.findById(id));
	}
	
	@PostMapping("/")
	public Cliente save(@Valid @RequestBody Cliente cliente) throws Exception {
		return clienteService.save(cliente);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> update(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Cliente cliente) throws Exception {
		return ResponseEntity.ok(clienteService.update(id, cliente));
	}
	
	@DeleteMapping("/{id}")
	public Boolean deleteById(@PathVariable(value = "id") Long id){
		clienteService.deleteById(id);
		return true;
	}
	

}
