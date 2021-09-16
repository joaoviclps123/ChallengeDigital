package br.com.challenge.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import net.sf.json.JSONObject;

@Entity
@Table (name = "T_GVM_ENDERECO") 
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_GVM_ENDERECO_SEQ")
	@SequenceGenerator(sequenceName = "T_GVM_ENDERECO_SEQ", allocationSize = 1, name = "T_GVM_ENDERECO_SEQ")
	@Column(name="cd_endereco")
	private Integer id;
	
	@Column(name="ds_logradouro")
	private String logradouro;
	
	@Column(name="ds_endereco")
	private String endereco;
	
	@Column(name="nr_endereco")
	private Integer nrEndereco;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Integer getNrEndereco() {
		return nrEndereco;
	}

	public void setNrEndereco(Integer nrEndereco) {
		this.nrEndereco = nrEndereco;
	}

	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		
		json.put("endereco", this.endereco);
		json.put("logradouro", this.logradouro);
		json.put("numeroEndereco", this.nrEndereco);
		return json;
	}
}
