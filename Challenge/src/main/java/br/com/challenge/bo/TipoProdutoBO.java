package br.com.challenge.bo;



import java.util.List;

import br.com.challenge.bean.ClientePF;
import br.com.challenge.bean.TipoProduto;
import br.com.challenge.repository.TipoProdutoRepository;
import net.sf.json.JSONObject;

public class TipoProdutoBO {

	private TipoProdutoRepository tRepository;
	
	private TipoProdutoBO() {
		
	}
	
	public TipoProdutoBO(TipoProdutoRepository tRepository) {
		this.tRepository = tRepository;
	}
	
	public TipoProduto cadastrarTipoProduto(TipoProduto novoTipoProduto) {		
		return this.tRepository.save(novoTipoProduto);
	}
	
	public void deletarTipoProduto(TipoProduto tipoProduto) {
		this.tRepository.delete(tipoProduto);
	}
	public List<TipoProduto> buscarTodosTiposProdutos(){
		return this.tRepository.findAll();
	}

	public TipoProduto buscarTipoProdutoPorId(int cdTipoProduto) {
		return this.tRepository.findByCdTipoProduto(cdTipoProduto);
	}
}

