package zallpy.com.analytics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import zallpy.com.analytics.model.AnaliseCredito;
import zallpy.com.analytics.service.AnaliseCreditoService;

@RestController
@RequestMapping("/analise")
public class AnaliseCreditoController {

	@Autowired
	private AnaliseCreditoService analiseCreditoService;
	
	@GetMapping("/cliente/{cpf}")
	public AnaliseCredito findAnaliseCreditoByClienteCpf(@PathVariable(value = "cpf") String cpf) throws Exception {
		return analiseCreditoService.findCreditAnalysisByClientCpf(cpf);
	}
	
}
