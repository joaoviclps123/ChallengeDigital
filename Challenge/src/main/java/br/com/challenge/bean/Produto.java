package br.com.challenge.bean;

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
@Table (name = "T_GVM_PRODUTO")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_GVM_PRODUTO_SEQ")
	@SequenceGenerator(sequenceName = "T_GVM_PRODUTO_SEQ", allocationSize = 1, name = "T_GVM_PRODUTO_SEQ")
	@Column(name="cd_produto")
	private Integer cdProduto;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cd_tipo_produto")
	private TipoProduto tipoProduto;
	
	@Column(name="ds_produto")
	private String dsProduto;
	
	@Column(name="dt_vencimento")
	private Date dtVencimento;
	
	@Column(name="nr_produto")
	private Integer nrProduto;
	
	@Column(name="nm_produto")
	private String nmProduto;

	public Integer getCdProduto() {
		return cdProduto;
	}

	public void setCdProduto(Integer cdProduto) {
		this.cdProduto = cdProduto;
	}

	public String getDsProduto() {
		return dsProduto;
	}

	public void setDsProduto(String dsProduto) {
		this.dsProduto = dsProduto;
	}

	public Date getDtVencimento() {
		return dtVencimento;
	}

	public void setDtVencimento(Date dtVencimento) {
		this.dtVencimento = dtVencimento;
	}

	public Integer getNrProduto() {
		return nrProduto;
	}

	public void setNrProduto(Integer nrProduto) {
		this.nrProduto = nrProduto;
	}

	public String getNmProduto() {
		return nmProduto;
	}

	public void setNmProduto(String nmProduto) {
		this.nmProduto = nmProduto;
	}

	public TipoProduto getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(TipoProduto tipoProduto) {
		this.tipoProduto = tipoProduto;
	}

	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		
		json.put("idProduto", this.cdProduto);
		json.put("nrProduto", this.nrProduto);
		json.put("nome", this.nmProduto);
		
		json.put("tipoProduto", this.tipoProduto.toJSON());
		
		json.put("dsProduto", this.dsProduto);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		json.put("dtVencimento", sdf.format(this.dtVencimento)); 
		
		return json;
	}
}
