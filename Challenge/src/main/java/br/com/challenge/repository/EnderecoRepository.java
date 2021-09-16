package br.com.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.challenge.bean.Endereco;


public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}
