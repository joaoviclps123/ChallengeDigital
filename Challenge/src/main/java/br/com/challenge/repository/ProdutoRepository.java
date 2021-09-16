package br.com.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.challenge.bean.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	Produto findByCdProduto(Integer cdProduto);



}
