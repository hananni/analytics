package zallpy.com.analytics.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import zallpy.com.analytics.enums.StatusProposta;

@Entity
@Table(name="analise")
public class AnaliseCredito implements Serializable {
	
	private static final long serialVersionUID = 5942541650719594572L;

	public AnaliseCredito(StatusProposta statusProposta, Double limiteInicio, Double limiteFim) {
		super();
		this.statusProposta = statusProposta;
		this.limiteInicio = limiteInicio;
		this.limiteFim = limiteFim;
	}
	
	public AnaliseCredito(StatusProposta statusProposta, String motivoNegacao) {
		super();
		this.statusProposta = statusProposta;
		this.motivoNegacao = motivoNegacao;
	}
	
	public AnaliseCredito() {
		super();
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Enumerated
	@Column(name="status")
	private StatusProposta statusProposta;
	
	@Column(name="limite_inicio")
	private Double limiteInicio;
	
	@Column(name="limite_fim")
	private Double limiteFim;
	
	@Column(name="motivo_negacao")
	private String motivoNegacao;
	
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StatusProposta getStatusProposta() {
		return statusProposta;
	}

	public void setStatusProposta(StatusProposta statusProposta) {
		this.statusProposta = statusProposta;
	}

	public Double getLimiteInicio() {
		return limiteInicio;
	}

	public void setLimiteInicio(Double limiteInicio) {
		this.limiteInicio = limiteInicio;
	}

	public Double getLimiteFim() {
		return limiteFim;
	}

	public void setLimiteFim(Double limiteFim) {
		this.limiteFim = limiteFim;
	}

	public String getMotivoNegacao() {
		return motivoNegacao;
	}

	public void setMotivoNegacao(String motivoNegacao) {
		this.motivoNegacao = motivoNegacao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((limiteFim == null) ? 0 : limiteFim.hashCode());
		result = prime * result + ((limiteInicio == null) ? 0 : limiteInicio.hashCode());
		result = prime * result + ((motivoNegacao == null) ? 0 : motivoNegacao.hashCode());
		result = prime * result + ((statusProposta == null) ? 0 : statusProposta.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnaliseCredito other = (AnaliseCredito) obj;
		if (limiteFim == null) {
			if (other.limiteFim != null)
				return false;
		} else if (!limiteFim.equals(other.limiteFim))
			return false;
		if (limiteInicio == null) {
			if (other.limiteInicio != null)
				return false;
		} else if (!limiteInicio.equals(other.limiteInicio))
			return false;
		if (motivoNegacao == null) {
			if (other.motivoNegacao != null)
				return false;
		} else if (!motivoNegacao.equals(other.motivoNegacao))
			return false;
		if (statusProposta != other.statusProposta)
			return false;
		return true;
	}
	
}
