package br.com.fiap.dao;

import javax.persistence.EntityManager;

import br.com.fiap.entity.Cliente;

public class ClienteDAO extends GenericDAO<Cliente, Integer>{

	public ClienteDAO(EntityManager em) {
		super(em);
	}
	
}
