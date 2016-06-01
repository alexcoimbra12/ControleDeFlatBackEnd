package br.com.alexcoimbra12.flat.ws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name = "imobiliaria")
public class Imobiliaria {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private Integer id;
	@Column
	private String nome;
	@Column
	private String endereco;
	@Column
	private String telContato;
	@Column
	private String nomeCorretor;
	@Column
	private String numCreci;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelContato() {
		return telContato;
	}

	public void setTelContato(String telContato) {
		this.telContato = telContato;
	}

	public String getNomeCorretor() {
		return nomeCorretor;
	}

	public void setNomeCorretor(String nomeCorretor) {
		this.nomeCorretor = nomeCorretor;
	}

	public String getNumCreci() {
		return numCreci;
	}

	public void setNumCreci(String numCreci) {
		this.numCreci = numCreci;
	}

	@Override
	public String toString() {
		return "Imobiliaria [id=" + id + ", nome=" + nome + ", endereco=" + endereco + ", telContato=" + telContato
				+ ", nomeCorretor=" + nomeCorretor + ", numCreci=" + numCreci + "]";
	}
	
	
}
