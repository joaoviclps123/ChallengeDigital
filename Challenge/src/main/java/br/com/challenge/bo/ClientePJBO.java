package br.com.challenge.bo;

import java.math.BigInteger;
import java.util.List;


import br.com.challenge.bean.ClientePJ;
import br.com.challenge.bean.Endereco;
import br.com.challenge.repository.ClientePJRepository;
import br.com.challenge.repository.EnderecoRepository;
import net.sf.json.JSONObject;

public class ClientePJBO {
	
	private ClientePJRepository cRepository;
	private EnderecoRepository eRepository;
	
	private ClientePJBO() {
		
	}
	
	public ClientePJBO(ClientePJRepository cRepository, EnderecoRepository enderecoRepository) {
		this.cRepository = cRepository;
		this.eRepository = enderecoRepository;
	}
	public ClientePJ cadastrarClientePJ(ClientePJ novoClientePJ) {
		
		Endereco endereco = novoClientePJ.getEndereco();
		endereco = this.eRepository.save(endereco);
		novoClientePJ.setEndereco(endereco);
		
		return this.cRepository.save(novoClientePJ);
	}
	public ClientePJ alterarCliente (JSONObject alteraJson) {
		Integer cdCliente = alteraJson.getInt("cd");
		
		ClientePJ alteraCliente = this.cRepository.findByCdCliente(cdCliente);
		
		alteraCliente.setEmail(alteraJson.getString("email"));
		alteraCliente.setNome(alteraJson.getString("nome"));
		alteraCliente.setNrCNPJ(BigInteger.valueOf(alteraJson.getLong("cnpj")));
		
		Endereco endereco = alteraCliente.getEndereco();
		
		endereco.setEndereco(alteraJson.getString("endereco"));
		endereco.setLogradouro(alteraJson.getString("endereco"));
		endereco.setNrEndereco(alteraJson.getInt("numero"));
		
		endereco = this.eRepository.save(endereco);
		
		alteraCliente.setEndereco(endereco);
		
		
		alteraCliente = this.cRepository.save(alteraCliente);
		
		return alteraCliente;
	}
	public void deletarCliente(ClientePJ cliente) {
		this.cRepository.delete(cliente);
		this.eRepository.delete(cliente.getEndereco());
	}
	public List<ClientePJ> buscarTodosClientes(){
		return this.cRepository.findAll();
	}
	public ClientePJ buscarClientePJPorCNPJ(BigInteger cnpj) {
		return this.cRepository.findByNrCNPJ(cnpj);
	}
}
