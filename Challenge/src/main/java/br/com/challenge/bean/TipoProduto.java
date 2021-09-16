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
@Table (name = "T_GVM_TIPO_PRODUTO")
public class TipoProduto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_GVM_TIPO_PRODUTO_SEQ")
	@SequenceGenerator(sequenceName = "T_GVM_TIPO_PRODUTO_SEQ", allocationSize = 1, name = "T_GVM_TIPO_PRODUTO_SEQ")
	@Column(name="cd_tipo_produto")
	private Integer cdTipoProduto;
	
	@Column(name="ds_tipo_produto")
	private String dsTipo;

	
	public String getDsTipo() {
		return dsTipo;
	}

	public void setDsTipo(String dsTipo) {
		this.dsTipo = dsTipo;
	}

	public Integer getCdTipoProduto() {
		return cdTipoProduto;
	}

	public void setCdTipoProduto(Integer cdTipoProduto) {
		this.cdTipoProduto = cdTipoProduto;
	}

	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		json.put("idTipoProduto", this.cdTipoProduto);
		json.put("dsTipo", this.dsTipo);
		return json;
	}
}
