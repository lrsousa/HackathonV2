package com.stefanini.hackathon2.repositorios;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.stefanini.hackathon2.entidades.Estoque;

public class EstoqueRepositorio {
	
	@Inject
	private EntityManager entityManager;
	
	public void inserir(Estoque estoque) {
		entityManager.persist(estoque);
	}

	public void atualizar(Estoque estoque) {
		entityManager.merge(estoque);
	}

	@SuppressWarnings("unchecked")
	public List<Estoque> todosEstoques() {
		return entityManager.createQuery("select e from " + Estoque.class.getSimpleName() + " e").getResultList();
	}
	
	public void remover(Estoque estoque) {
		entityManager.remove(estoque);
	}
	
	public void removePorId(Integer idEstoque) {
		Estoque entity = entityManager.find(Estoque.class, idEstoque);
		entityManager.remove(entity);
	}
	
	public Estoque pesquisaPorId(Integer idEstoque) {
		return entityManager.find(Estoque.class, idEstoque);
	}
}
