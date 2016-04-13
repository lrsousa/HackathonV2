package com.stefanini.hackathon2.servicos;

import java.util.List;

import javax.inject.Inject;

import com.stefanini.hackathon2.entidades.Estoque;
import com.stefanini.hackathon2.repositorios.EstoqueRepositorio;
import com.stefanini.hackathon2.transacao.Transacional;

public class EstoqueServico {

	@Inject
	private EstoqueRepositorio repositorio;
	
	@Transacional
	public void salvar(Estoque estoque) {
		if(estoque.getIdEstoque() == null) {
			repositorio.inserir(estoque);
		} else {
			repositorio.atualizar(estoque);
		}
	}

	public void deletar(Estoque estoque) {
		repositorio.remover(estoque);
	}

	public List<Estoque> carregaTodoEstoqueDoBanco() {
		return repositorio.todosEstoques();
	}

}
