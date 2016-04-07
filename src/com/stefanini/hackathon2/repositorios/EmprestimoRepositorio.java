package com.stefanini.hackathon2.repositorios;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.stefanini.hackathon2.entidades.Emprestimo;

@SuppressWarnings("all")
public class EmprestimoRepositorio {

	@Inject
	private EntityManager entityManager;
	
	public void inserir(Emprestimo emprestimo) {
		entityManager.persist(emprestimo);
	}

	public List<Emprestimo> todosEmprestimos() {
//		TODO listar usando outra coisa
//		return entityManager.createQuery("select l from " + Livro.class.getSimpleName() + " l").getResultList();
		return null;
	}

	public void remover(Emprestimo emprestimo) {
		entityManager.remove(entityManager.merge(emprestimo));
	}
	
	public void removerPorId(Integer id) {
		Livro entity = entityManager.find(Emprestimo.class, id);
		entityManager.remove(entity);	
	}
	
	public void atualizar(Emprestimo emprestimo) {
		entityManager.merge(emprestimo);	
	}
	
	public Livro pesquisarPorID(Integer id) {
		return entityManager.find(Emprestimo.class, id);
	}
	
}
