package zallpy.com.analytics.service;

import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zallpy.com.analytics.model.AnaliseCredito;
import zallpy.com.analytics.model.Cliente;
import zallpy.com.analytics.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	AnaliseCreditoService analiseCreditoService;

	public Cliente findByCpf(String cpf) {
		return clienteRepository.findByCpf(cpf);
	}
	
	public Cliente save(Cliente cliente) throws Exception {
		
		//Regra de negocio que não permite salvar um cliente com o mesmo CPF
		Cliente hasCliente = clienteRepository.findByCpf(cliente.getCpf());
		if(hasCliente != null) {
			throw new Exception("CPF já cadastrado!");
		}
		
		//Salvando o cliente
		cliente = clienteRepository.save(cliente);
		
		//Enviando para API de motor de análise de crédito, para analisar e salvar a proposta do cliente
		analiseCreditoService.reviewAndSaveProposol(cliente);

		return cliente;
	}

	public Boolean deleteById(Long clienteId) {
		this.findById(clienteId);
		clienteRepository.deleteById(clienteId);
		return true;
	}

	public Cliente update(Long id, Cliente cliente) throws Exception {
		this.findById(id);
		if(!id.equals(cliente.getId())) {
			throw new Exception("Código do cliente divergente.");
		}
		return clienteRepository.save(cliente);
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Cliente findById(Long clienteId) {
		return clienteRepository.findById(clienteId).orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado."));
	}
}
