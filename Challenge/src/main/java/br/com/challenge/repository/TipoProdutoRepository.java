package br.com.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.challenge.bean.TipoProduto;

public interface TipoProdutoRepository extends JpaRepository<TipoProduto, Integer> {

	TipoProduto findByCdTipoProduto(Integer cdTipoProduto);

}
