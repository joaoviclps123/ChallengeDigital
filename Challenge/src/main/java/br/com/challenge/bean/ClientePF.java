package br.com.challenge.bean;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

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
@Table (name = "T_GVM_CLIENTE_PF") 
public class ClientePF {

		
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_GVM_CLIENTE_PF_SEQ")
	@SequenceGenerator(sequenceName = "T_GVM_CLIENTE_PF_SEQ", allocationSize = 1, name = "T_GVM_CLIENTE_PF_SEQ")
	@Column(name="cd_cliente_pf")
	private Integer cdCliente;
	
	@Column(name="nr_cpf")
	private BigInteger nrCPF;
	
	@Column(name="nm_cliente")
	private String nome;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cd_endereco")
	private Endereco endereco;	
	
	@Column(name="dt_nascimento")
	private Date dtNascimento;
	
	@Column(name="nr_telefone_pf")
	private BigInteger nrTelefone;
	
	@Column(name="email_cliente_pf")
	private String email;

	public Integer getCdCliente() {
		return cdCliente;
	}

	public void setCdCliente(Integer cdCliente) {
		this.cdCliente = cdCliente;
	}

	public BigInteger getNrCPF() {
		return nrCPF;
	}

	public void setNrCPF(BigInteger nrCPF) {
		this.nrCPF = nrCPF;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Date getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
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

	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		
		json.put("idCliente", this.cdCliente);
		json.put("cpf", this.nrCPF);
		json.put("nome", this.nome);
		
		json.put("endereco", this.endereco.toJSON());
		
		json.put("telefone", this.nrTelefone);
		json.put("email", this.email);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		json.put("dtNascimento", sdf.format(this.dtNascimento)); 
		
		return json;
	}
}
