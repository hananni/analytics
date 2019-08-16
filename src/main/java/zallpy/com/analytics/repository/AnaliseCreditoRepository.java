package zallpy.com.analytics.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import zallpy.com.analytics.model.AnaliseCredito;

public interface AnaliseCreditoRepository extends JpaRepository<AnaliseCredito, Long>{
	 @Query( value = "select * from analise a " +
	            " inner join cliente b on b.id = a.cliente_id " +
	            " where b.cpf = :cpf", nativeQuery = true)
	Optional<AnaliseCredito> findCreditAnalysisByClientCpf(@Param("cpf") String cpf);
}