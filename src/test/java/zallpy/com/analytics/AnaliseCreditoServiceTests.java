package zallpy.com.analytics;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import zallpy.com.analytics.model.AnaliseCredito;
import zallpy.com.analytics.model.Cliente;
import zallpy.com.analytics.repository.AnaliseCreditoRepository;
import zallpy.com.analytics.service.AnaliseCreditoService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnaliseCreditoServiceTests {
	
	@InjectMocks
	AnaliseCreditoService analiseCreditoService;
	
	@Mock
	AnaliseCreditoRepository analiseCreditoRepository;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
    //Teste de caso: Busca de analise de crédito de CPF não cadastrado;
    @Test(expected=Exception.class)
    public void findAnaliseCreditoByCpf() throws Exception
    {
        when(analiseCreditoRepository.findCreditAnalysisByClientCpf("123456")).thenReturn(null);
        analiseCreditoService.findCreditAnalysisByClientCpf(("123456"));
        
    }
}
