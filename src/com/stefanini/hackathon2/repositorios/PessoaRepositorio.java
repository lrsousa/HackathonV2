package com.stefanini.hackathon2.repositorios;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.stefanini.hackathon2.entidades.Pessoa;

@SuppressWarnings("all")
public class PessoaRepositorio {

	@Inject
	private EntityManager entityManager;
	
	public void inserir(Pessoa pessoa) {
		entityManager.persist(pessoa);
	}

	public List<Pessoa> todasPessoas() {
//		TODO listar usando outra coisa
//		return entityManager.createQuery("select l from " + Livro.class.getSimpleName() + " l").getResultList();
		return null;
	}

	public void remover(Pessoa pessoa) {
		entityManager.remove(entityManager.merge(pessoa));
	}
	
	public void removerPorId(Integer id) {
		Livro entity = entityManager.find(Pessoa.class, id);
		entityManager.remove(entity);	
	}
	
	public void atualizar(Pessoa pessoa) {
		entityManager.merge(pessoa);	
	}
	
	public Livro pesquisarPorID(Integer id) {
		return entityManager.find(Pessoa.class, id);
	}
}
