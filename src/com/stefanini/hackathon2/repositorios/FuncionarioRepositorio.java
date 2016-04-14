package com.stefanini.hackathon2.repositorios;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.stefanini.hackathon2.entidades.Funcionario;

public class FuncionarioRepositorio {
	
	@Inject
	private EntityManager entityManager;
	
	public void inserir(Funcionario funcionario) {
		entityManager.persist(funcionario);
	}

	public void atualizar(Funcionario funcionario) {
		entityManager.merge(funcionario);
	}

	@SuppressWarnings("unchecked")
	public List<Funcionario> todosFuncionarios() {
		return entityManager.createQuery("select f from " + Funcionario.class.getSimpleName() + " f").getResultList();
	}
	
	public void remover(Funcionario funcionario) {
		entityManager.remove(funcionario);
	}
	
	public void removePorId(Integer idFuncionario) {
		Funcionario entity = entityManager.find(Funcionario.class, idFuncionario);
		entityManager.remove(entity);
	}
	
	public Funcionario pesquisaPorId(Integer idFuncionario) {
		return entityManager.find(Funcionario.class, idFuncionario);
	}
}
