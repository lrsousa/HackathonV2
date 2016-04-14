package com.stefanini.hackathon2.repositorios;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.junit.Test;

import com.stefanini.hackathon2.entidades.Funcionario;

public class TesteFuncionarioRepositorio {
	
	@Inject
	private EntityManager entityManager;
	
	public void inserir(Funcionario funcionario) {
		System.out.println(funcionario.getEmail());
		entityManager.persist(funcionario);
	}

	@Test
	public void testarPersistencia() {
		Funcionario f = new Funcionario();
		f.setCpf("1521545");
		f.setEmail("leonarrangel@gmail.com");
		f.setNome("leonardo");
		f.setSalario(111222);
		inserir(f);
		
	}
}
