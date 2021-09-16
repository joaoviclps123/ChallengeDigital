package br.com.challenge.bean;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import net.sf.json.JSONObject;

@Entity
@Table (name = "T_GVM_CLIENTE_PJ")
public class ClientePJ {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_GVM_CLIENTE_PJ_SEQ")
	@SequenceGenerator(sequenceName = "T_GVM_CLIENTE_PJ_SEQ", allocationSize = 1, name = "T_GVM_CLIENTE_PJ_SEQ")
	@Column(name="cd_cliente_pj")
	private Integer cdCliente;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cd_endereco")
	private Endereco endereco;
	
	@Column(name="nr_cnpj")
	private BigInteger nrCNPJ;
	
	@Column(name="nr_telefone_pj")
	private BigInteger nrTelefone;
	
	@Column(name="email_cliente_pj")
	private String email;
	
	@Column(name="nm_cliente_pj")
	private String nome;

	
	public Integer getCdCliente() {
		return cdCliente;
	}

	public void setCdCliente(Integer cdCliente) {
		this.cdCliente = cdCliente;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public BigInteger getNrCNPJ() {
		return nrCNPJ;
	}

	public void setNrCNPJ(BigInteger nrCNPJ) {
		this.nrCNPJ = nrCNPJ;
	}

	public BigInteger getNrTelefone() {
		return nrTelefone;
	}

	public void setNrTelefone(BigInteger nrTelefone) {
		this.nrTelefone = nrTelefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		
		json.put("idCliente", this.cdCliente);
		json.put("cnpj", this.nrCNPJ);
		json.put("nome", this.nome);
		
		json.put("endereco", this.endereco.toJSON());
		
		json.put("telefone", this.nrTelefone);
		json.put("email", this.email);
		return json;
	}
	
}
