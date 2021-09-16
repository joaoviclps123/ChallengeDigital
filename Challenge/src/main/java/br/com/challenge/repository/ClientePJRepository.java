package br.com.challenge.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.challenge.bean.ClientePJ;

public interface ClientePJRepository extends JpaRepository<ClientePJ, Integer> {

	ClientePJ findByCdCliente(Integer cdCliente);
	ClientePJ findByNrCNPJ(BigInteger cnpj);

}
