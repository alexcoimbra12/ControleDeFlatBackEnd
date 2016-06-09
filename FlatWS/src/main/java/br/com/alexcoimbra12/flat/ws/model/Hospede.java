package br.com.alexcoimbra12.flat.ws.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name = "hospede")
public class Hospede {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;

	@Column
	private String nome;

	@Column
	private String cpf;

	@Column
	private String telefone;

	@ManyToOne
	@JoinColumn(name = "numero_apartamento", nullable = false)
	private Apartamento apartamento;

	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_imobiliaria", nullable = false)
	private Imobiliaria imobiliaria;
	
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public int getId() {
		return this.id;
	}

	public Apartamento getApartamento() {
		return apartamento;
	}

	public void setApartamento(Apartamento apartamento) {
		this.apartamento = apartamento;
	}

	public Imobiliaria getImobiliaria() {
		return imobiliaria;
	}

	public void setImobiliaria(Imobiliaria imobiliaria) {
		this.imobiliaria = imobiliaria;
	}

	@Override
	public String toString() {
		return "Hospede [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", telefone=" + telefone + "]";
	}

}