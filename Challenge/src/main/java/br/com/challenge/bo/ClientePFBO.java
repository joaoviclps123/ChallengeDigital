package br.com.challenge.bo;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import br.com.challenge.bean.ClientePF;
import br.com.challenge.bean.Endereco;
import br.com.challenge.repository.ClientePFRepository;
import br.com.challenge.repository.EnderecoRepository;
import net.sf.json.JSONObject;

public class ClientePFBO {
	
	private ClientePFRepository cRepository;
	private EnderecoRepository eRepository;
	
	private ClientePFBO(){
		
	}
	public ClientePFBO(ClientePFRepository cRepository, EnderecoRepository enderecoRepository) {
		this.cRepository = cRepository;
		this.eRepository = enderecoRepository;
	}
	public ClientePF cadastrarClientePF(ClientePF novoClientePF) {
		
		Endereco endereco = novoClientePF.getEndereco();
		endereco = this.eRepository.save(endereco); 
		novoClientePF.setEndereco(endereco);
		
		return this.cRepository.save(novoClientePF);
	}
	public ClientePF alterarCliente (JSONObject alteraJson) throws ParseException{
		Integer cdCliente = alteraJson.getInt("cd");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		ClientePF alteraCliente = this.cRepository.findByCdCliente(cdCliente);
		
		alteraCliente.setDtNascimento(sdf.parse(alteraJson.getString("dtNascimento")));
		alteraCliente.setEmail(alteraJson.getString("email"));
		alteraCliente.setNome(alteraJson.getString("nome"));
		alteraCliente.setNrCPF(BigInteger.valueOf(alteraJson.getLong("cpf")));
		alteraCliente.setNrTelefone(BigInteger.valueOf(alteraJson.getLong("telefone")));
		
		Endereco endereco = alteraCliente.getEndereco();
		
		endereco.setEndereco(alteraJson.getString("endereco"));
		endereco.setLogradouro(alteraJson.getString("endereco"));
		endereco.setNrEndereco(alteraJson.getInt("numero"));
		
		endereco = this.eRepository.save(endereco);
		
		alteraCliente.setEndereco(endereco);
		
		
		alteraCliente = this.cRepository.save(alteraCliente);
		
		return alteraCliente;

	}
	
	public void deletarCliente(ClientePF cliente) {
		this.cRepository.delete(cliente);
		this.eRepository.delete(cliente.getEndereco());
	}
	
	public List<ClientePF> buscarTodosClientes(){
		return this.cRepository.findAll();
	}
	
	public ClientePF buscarClientePorCpf(BigInteger cpf) {
		return this.cRepository.findByNrCPF(cpf);
	}
	
}