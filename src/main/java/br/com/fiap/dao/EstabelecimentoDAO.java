package br.com.fiap.dao;

import javax.persistence.EntityManager;

import br.com.fiap.entity.Estabelecimento;

public class EstabelecimentoDAO extends GenericDAO<Estabelecimento, Integer>{

	public EstabelecimentoDAO(EntityManager em) {
		super(em);
	}
	
}
