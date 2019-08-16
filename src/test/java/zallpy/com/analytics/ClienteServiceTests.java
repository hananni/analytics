package zallpy.com.analytics;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import zallpy.com.analytics.enums.EstadoCivil;
import zallpy.com.analytics.enums.Genero;
import zallpy.com.analytics.model.Cliente;
import zallpy.com.analytics.repository.AnaliseCreditoRepository;
import zallpy.com.analytics.repository.ClienteRepository;
import zallpy.com.analytics.service.AnaliseCreditoService;
import zallpy.com.analytics.service.ClienteService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClienteServiceTests {

	@InjectMocks
	ClienteService clienteService;

	@Mock
	ClienteRepository clienteRepository;
	
	@Mock
	AnaliseCreditoService analiseCreditoService;
	
	@Mock
	AnaliseCreditoRepository analiseCreditoRepository;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	//Testando instancia no repositório de cliente e analise credito ao enviar um para o método save
    @Test
    public void saveClient() throws Exception
    {
        Cliente cliente = new Cliente(Genero.MASCULINO,20,EstadoCivil.SOLTEIRO,0, 1000.00);
        clienteService.save(cliente);
        //Verificando se cliente foi salvo
        verify(clienteRepository, times(1)).save(cliente);
        verify(analiseCreditoRepository, times(1));
    }
    
    //Teste de caso existir um cliente com o mesmo CPF cadastrado no banco de dados (Esperando uma exceção)
    @Test(expected=Exception.class)
    public void saveClientWithDuplicateCPf() throws Exception
    {
        Cliente cliente = new Cliente();
        cliente.setCpf("123456");
        
        Cliente cliente2 = new Cliente();
        cliente2.setCpf("123456");
        
        when(clienteService.findByCpf("123456")).thenReturn(cliente2);
        clienteService.save(cliente);
    }
}
