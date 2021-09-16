package br.com.challenge.bo;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import br.com.challenge.bean.ClientePF;
import br.com.challenge.bean.Endereco;
import br.com.challenge.bean.Produto;
import br.com.challenge.bean.TipoProduto;
import br.com.challenge.repository.ProdutoRepository;
import br.com.challenge.repository.TipoProdutoRepository;
import net.sf.json.JSONObject;

public class ProdutoBO {
	
	private ProdutoRepository pRepository;
	private TipoProdutoRepository tRepository;

	private ProdutoBO() {
		
	}
	public ProdutoBO(ProdutoRepository pRepository, TipoProdutoRepository tRepository) {
		this.pRepository = pRepository;
		this.tRepository = tRepository;
	}
	public Produto cadastrarProduto(Produto novoProduto) {
		return this.pRepository.save(novoProduto);
	}

	public Produto alterarProduto(JSONObject alteraJson) throws ParseException  {
		Integer cdProduto = alteraJson.getInt("cd");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Produto alteraProduto = this.pRepository.findByCdProduto(cdProduto);
		
		alteraProduto.setDtVencimento(sdf.parse(alteraJson.getString("dtVencimento")));
		alteraProduto.setDsProduto(alteraJson.getString("dsProduto"));
		alteraProduto.setNmProduto(alteraJson.getString("nome"));
		alteraProduto.setNrProduto(alteraJson.getInt("nrProduto"));
		alteraProduto = this.pRepository.save(alteraProduto);
		
		
		return alteraProduto;
	}
	public void deletarProduto(Produto produto) {
		this.pRepository.delete(produto);
		this.tRepository.delete(produto.getTipoProduto());
	}
	public List<Produto> buscarTodosProdutos(){
		return this.pRepository.findAll();
	}
}

