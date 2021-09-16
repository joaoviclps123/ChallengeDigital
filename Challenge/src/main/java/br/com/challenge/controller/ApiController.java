package br.com.challenge.controller;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.challenge.bean.ClientePF;
import br.com.challenge.bean.ClientePJ;
import br.com.challenge.bean.Endereco;
import br.com.challenge.bean.Produto;
import br.com.challenge.bean.TipoProduto;
import br.com.challenge.bo.ClientePFBO;
import br.com.challenge.bo.ClientePJBO;
import br.com.challenge.bo.ProdutoBO;
import br.com.challenge.bo.TipoProdutoBO;
import br.com.challenge.repository.ClientePFRepository;
import br.com.challenge.repository.ClientePJRepository;
import br.com.challenge.repository.EnderecoRepository;
import br.com.challenge.repository.ProdutoRepository;
import br.com.challenge.repository.TipoProdutoRepository;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/api")
public class ApiController {

	@Autowired
	private TipoProdutoRepository tRepository;
	
	@Autowired
	private ClientePFRepository cRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ClientePJRepository cjRepository;
	
	@Autowired
	private ProdutoRepository pRepository;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@RequestMapping(value="/cadastrarTipoProduto", produces="application/json", method = RequestMethod.POST)
	public JSONObject cadastrarTipoProduto(@RequestBody JSONObject tipoProduto) {
		TipoProdutoBO tpb = new TipoProdutoBO(this.tRepository);
		
		TipoProduto tp = new TipoProduto();
		tp.setDsTipo(tipoProduto.getString("nome"));
		
		tp = tpb.cadastrarTipoProduto(tp);
		
		return tp.toJSON();
	}
	
	@RequestMapping(value="/cadastrarClientePF", produces="application/json", method = RequestMethod.POST)
	public JSONObject cadastrarClientePF(@RequestBody JSONObject clientePF) {
		ClientePF cliente = new ClientePF();
		Endereco endereco = new Endereco();
		
		ClientePFBO cbo = this.getClientePFBO();
		
		try {
			endereco.setEndereco(clientePF.getString("endereco"));
			endereco.setLogradouro(clientePF.getString("endereco"));
			endereco.setNrEndereco(clientePF.getInt("nrEndereco"));
			
			String dataNascimento = clientePF.getString("dtNascimento");
			cliente.setDtNascimento(sdf.parse(dataNascimento));
			cliente.setEmail(clientePF.getString("email"));
			cliente.setEndereco(endereco);
			cliente.setNome(clientePF.getString("nome"));
			cliente.setNrCPF(BigInteger.valueOf(clientePF.getLong("cpf")));
			cliente.setNrTelefone(BigInteger.valueOf(clientePF.getLong("telefone")));
			
			cliente = cbo.cadastrarClientePF(cliente);
			
			return cliente.toJSON();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		JSONObject json = new JSONObject();
		json.put("error", "Ocorreu um erro ao salvar o Cliente PF");
		
		return json;
	}

	@RequestMapping(value="/delete/clientepf/{cpfCliente}", produces="application/json", method = RequestMethod.GET)
	public JSONObject deletarClientePF(@PathVariable("cpfCliente") BigInteger cpfCliente) {
		JSONObject json = new JSONObject();
		
		ClientePFBO cbo = this.getClientePFBO();
		
		try {
			ClientePF cliente = cbo.buscarClientePorCpf(cpfCliente);
			cbo.deletarCliente(cliente);
			json.put("msg", "Cliente " + cliente.getNome() + " deletado com sucesso");
			return json;
		}catch(Exception e) {
			
		}
		json.put("msg", "Ocorreu um erro ao deletar o cliente");
		return json;
		
	}
	
	@RequestMapping(value="/busca/clientepf", produces="application/json", method = RequestMethod.GET)
	public JSONObject buscarTodosOsClientesPF() {
		JSONObject json = new JSONObject();
		JSONArray arrayClientes = new JSONArray();
		
		ClientePFBO cbo = this.getClientePFBO();
		
		List<ClientePF> listaCliente = cbo.buscarTodosClientes();
		for(ClientePF cliente : listaCliente) {
			arrayClientes.add(cliente.toJSON());
		}
		
		return json;
		
	}
	
	@RequestMapping(value="/busca/clientepf/{cpf}", produces="application/json", method = RequestMethod.GET)
	public JSONObject buscarClientePorCPF(@PathVariable("cpf") BigInteger cpf) {
		JSONObject json = new JSONObject();
		ClientePFBO cbo = this.getClientePFBO();
		
		ClientePF cliente = cbo.buscarClientePorCpf(cpf);
		
		json.put("cliente", cliente.toJSON());
		
		return json;
	}
	@RequestMapping(value="/alterarClientePF", produces="application/json", method = RequestMethod.POST)
	public JSONObject alterarClientePF(@RequestBody JSONObject clientePF) {
		ClientePFBO cbo = this.getClientePFBO();
		
		try {
			ClientePF cliente = cbo.alterarCliente(clientePF);
			return cliente.toJSON();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		JSONObject json = new JSONObject();
		json.put("error", "Ocorreu um erro ao salvar o Cliente PF");
		
		return json;
		
		
	}
	
		
	@RequestMapping(value="/cadastrarClientePJ", produces="application/json", method = RequestMethod.POST)
	public JSONObject cadastrarClientePJ(@RequestBody JSONObject clientePJ) {
		ClientePJ cliente = new ClientePJ();
		Endereco endereco = new Endereco();
		
		ClientePJBO cbo = this.getClientePJBO();
		
		try {
			endereco.setEndereco(clientePJ.getString("endereco"));
			endereco.setLogradouro(clientePJ.getString("endereco"));
			endereco.setNrEndereco(clientePJ.getInt("nrEndereco"));
			
			
			cliente.setEmail(clientePJ.getString("email"));
			cliente.setEndereco(endereco);
			cliente.setNome(clientePJ.getString("nome"));
			cliente.setNrCNPJ(BigInteger.valueOf(clientePJ.getLong("cnpj")));
			cliente.setNrTelefone(BigInteger.valueOf(clientePJ.getLong("telefone")));
			
			cliente = cbo.cadastrarClientePJ(cliente);
			
			return cliente.toJSON();
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject json = new JSONObject();
		json.put("error", "Ocorreu um erro ao salvar o Cliente PJ");
		
		return json;
	}
	
	@RequestMapping(value="/busca/clientepj", produces="application/json", method = RequestMethod.GET)
	public JSONObject buscarTodosOsClientesPJ() {
		JSONObject json = new JSONObject();
		JSONArray arrayClientes = new JSONArray();
		
		ClientePJBO cbo = this.getClientePJBO();
		
		List<ClientePJ> listaCliente = cbo.buscarTodosClientes();
		for(ClientePJ cliente : listaCliente) {
			arrayClientes.add(cliente.toJSON());
		}
		json.put("listaCliente", arrayClientes);
		
		return json;	
	}
	
	@RequestMapping(value="/busca/clientepj/{cnpj}", produces="application/json", method = RequestMethod.GET)
	public JSONObject buscarClientePorCNPJ(@PathVariable("cnpj") BigInteger cnpj) {
		JSONObject json = new JSONObject();
		ClientePJBO cbo = this.getClientePJBO();
		
		ClientePJ cliente = cbo.buscarClientePJPorCNPJ(cnpj);
		
		json.put("cliente", cliente.toJSON());
		
		return json;
		
	}
	
	@RequestMapping(value="/alterarClientePJ", produces="application/json", method = RequestMethod.POST)
	public JSONObject alterarClientePJ(@RequestBody JSONObject clientePJ) {
		ClientePJBO cbo = this.getClientePJBO();
		
		try {
			ClientePJ cliente = cbo.alterarCliente(clientePJ);
			return cliente.toJSON();
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject json = new JSONObject();
		json.put("error", "Ocorreu um erro ao salvar o Cliente PJ");
		
		return json;
	}
	
	@RequestMapping(value="/delete/clientepj/{cnpjCliente}", produces="application/json", method = RequestMethod.GET)
	public JSONObject deletarClientePJ(@PathVariable("cnpjCliente") BigInteger cnpjCliente) {
		JSONObject json = new JSONObject();
		
		ClientePJBO cbo = this.getClientePJBO();
		
		try {
			ClientePJ cliente = cbo.buscarClientePJPorCNPJ(cnpjCliente);
			cbo.deletarCliente(cliente);
			json.put("msg", "Cliente " + cliente.getNome() + " deletado com sucesso");
			return json;
		}catch(Exception e) {
			
		}
		json.put("msg", "Ocorreu um erro ao deletar o cliente");
		return json;
		
	}
	
	@RequestMapping(value="/cadastrarProduto", produces="application/json", method = RequestMethod.POST)
	public JSONObject cadastrarProduto(@RequestBody JSONObject novoProduto) {
		Produto produto = new Produto();
		
		TipoProdutoBO tbo = this.getTipoProduto();
		ProdutoBO pbo = this.getProdutoBO();
		
		TipoProduto tipoProduto = tbo.buscarTipoProdutoPorId(novoProduto.getInt("idTipoProduto"));

		try {
			String dataVencimento = novoProduto.getString("dtVencimento");
			produto.setDtVencimento(this.sdf.parse(dataVencimento));
			produto.setNrProduto(novoProduto.getInt("nrProduto"));
			produto.setNmProduto(novoProduto.getString("nome"));
			produto.setDsProduto(novoProduto.getString("dsProduto"));
			produto.setTipoProduto(tipoProduto);
			
			
			produto = pbo.cadastrarProduto(produto);
			
			return produto.toJSON();
		}catch (ParseException e) {
			e.printStackTrace();
		}
		JSONObject json = new JSONObject();
		json.put("error", "Ocorreu um erro ao salvar o Produto");
		
		return json;
	}
	@RequestMapping(value="/busca/produto", produces="application/json", method = RequestMethod.GET)
	public JSONObject buscarTodosOsProdutos() {
		JSONObject json = new JSONObject();
		JSONArray arrayProdutos = new JSONArray();
		
		ProdutoBO pbo = this.getProdutoBO();
		
		List<Produto> listaProdutos = pbo.buscarTodosProdutos();
		for(Produto produto  : listaProdutos) {
			arrayProdutos.add(produto.toJSON());
		}
		json.put("listaProduto", arrayProdutos);
		
		return json;	
	}
	
	@RequestMapping(value="/alterarProduto", produces="application/json", method = RequestMethod.POST)
	public JSONObject alterarProduto(@RequestBody JSONObject produto) {
		ProdutoBO pbo = this.getProdutoBO();
		
		try {
			Produto produtoCadastrar = pbo.alterarProduto(produto);
			return produtoCadastrar.toJSON();
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject json = new JSONObject();
		json.put("error", "Ocorreu um erro ao salvar o Produto");
		
		return json;
	}
	
	
	private TipoProdutoBO getTipoProduto() {
		return new TipoProdutoBO(this.tRepository);
	}

	private ProdutoBO getProdutoBO() {
		return new ProdutoBO(this.pRepository, this.tRepository);
	}

	private ClientePFBO getClientePFBO() {
		return new ClientePFBO(this.cRepository, this.enderecoRepository);
	}


	private ClientePJBO getClientePJBO() {
		return new ClientePJBO(this.cjRepository, this.enderecoRepository);
	}
	
}
