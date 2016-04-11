package com.stefanini.hackathon2.repositorios;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.stefanini.hackathon2.entidades.Emprestimo;

@SuppressWarnings("all")
public class EmprestimoRepositorio {

	@Inject
	private EntityManager entityManager;
	
	public void inserir(Emprestimo emprestimo) {
		entityManager.persist(emprestimo);
	}

	public List<Emprestimo> todosEmprestimos() {
		List<Emprestimo> emprestimos = entityManager.createQuery("select e from " + Emprestimo.class.getSimpleName() + " e").getResultList();
		return  emprestimos;
	}

	public void remover(Emprestimo emprestimo) {
		entityManager.createQuery("delete from " + Emprestimo.class.getSimpleName() + " where id=:paramIdEmprestimo")
							.setParameter("paramIdEmprestimo", emprestimo.getId()).executeUpdate();
	}
	
	public void removerPorId(Integer id) {
		Emprestimo entity = entityManager.find(Emprestimo.class, id);
		entityManager.remove(entity);	
	}
	
	public void atualizar(Emprestimo emprestimo) {
		entityManager.merge(emprestimo);	
	}
	
	public Emprestimo pesquisarPorID(Integer id) {
		return entityManager.find(Emprestimo.class, id);
	}
	
}
