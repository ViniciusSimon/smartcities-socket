package br.com.fiap.service;

import java.rmi.RemoteException;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.fiap.dao.ClienteDAO;
import br.com.fiap.entity.Cliente;

public class ClienteServiceImpl implements ClienteService {

	@Override
	public Cliente buscar(int id) throws RemoteException {
		EntityManager em = Persistence.createEntityManagerFactory("smartcities-dao").createEntityManager();
		ClienteDAO dao = new ClienteDAO(em);
		return dao.buscar(id);
	}

}
