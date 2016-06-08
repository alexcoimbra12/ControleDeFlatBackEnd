package br.com.alexcoimbra12.flat.ws.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@XmlRootElement(name = "apartamento")
public class Apartamento {

	@Id
	@Column(name = "numero_apto", nullable = false)
	private int numeroApto;

	@OneToMany(mappedBy = "apartamento", targetEntity = Hospede.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonBackReference
	private List<Hospede> hospedeList;

	public int getNumeroApto() {
		return numeroApto;
	}

	public List<Hospede> getHospedeList() {
		return hospedeList;
	}

	public void setHospedeList(List<Hospede> hospedeList) {
		this.hospedeList = hospedeList;
	}

}
