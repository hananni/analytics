package zallpy.com.analytics.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import zallpy.com.analytics.enums.EstadoCivil;
import zallpy.com.analytics.enums.Estados;
import zallpy.com.analytics.enums.Genero;

@Entity
@Table(name="cliente")
public class Cliente implements Serializable{

	private static final long serialVersionUID = 3030255301159463071L;
	
	
	public Cliente() {
		super();
	}

	public Cliente(Integer idade, EstadoCivil estadoCivil, Integer dependentes, Double renda) {
		super();
		this.idade = idade;
		this.estadoCivil = estadoCivil;
		this.dependentes = dependentes;
		this.renda = renda;
	}
	
	public Cliente(Genero genero, Integer idade, EstadoCivil estadoCivil, Integer dependentes, Double renda) {
		super();
		this.genero = genero;
		this.idade = idade;
		this.estadoCivil = estadoCivil;
		this.dependentes = dependentes;
		this.renda = renda;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotNull(message="O nome deve ser informado.")
	@Column(name="nome")
	private String nome;
	
	@NotNull(message="O CPF deve ser informado.")
	@Column(name="cpf")
	private String cpf;
	
	@NotNull(message="A idade deve ser informada.")
	@Column(name="idade")
	private Integer idade;
	
	@Enumerated
	@NotNull(message="O gênero deve ser informado.")
	@Column(name="genero")
	private Genero genero;
	
	@Enumerated
	@NotNull(message="O estado civil deve ser informado.")
	@Column(name="estado_civil")
	private EstadoCivil estadoCivil;
	
	@Enumerated
	@NotNull(message="O estado deve ser informado.")
	@Column(name="estado")
	private Estados estado;
	
	@NotNull(message="A quantidade de dependentes deve ser informado.")
	@Column(name="dependentes")
	private Integer dependentes;
	
	@NotNull(message="A renda deve ser informada.")
	@Column(name="renda")
	private Double renda;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public Estados getEstado() {
		return estado;
	}

	public void setEstado(Estados estado) {
		this.estado = estado;
	}

	public Integer getDependentes() {
		return dependentes;
	}

	public void setDependentes(Integer dependentes) {
		this.dependentes = dependentes;
	}

	public Double getRenda() {
		return renda;
	}

	public void setRenda(Double renda) {
		this.renda = renda;
	}
	
}
