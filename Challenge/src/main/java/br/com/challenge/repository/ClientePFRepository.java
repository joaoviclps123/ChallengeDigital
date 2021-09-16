package br.com.challenge.repository;

import java.math.BigInteger;


import org.springframework.data.jpa.repository.JpaRepository;
import br.com.challenge.bean.ClientePF;

public interface ClientePFRepository extends JpaRepository<ClientePF, Integer> {
	
	ClientePF findByCdCliente(Integer cdCliente);
	ClientePF findByNrCPF(BigInteger cpf);
	

}
