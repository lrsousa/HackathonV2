package com.stefanini.hackathon2.servicos;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.stefanini.hackathon2.entidades.Emprestimo;
import com.stefanini.hackathon2.entidades.Livro;
import com.stefanini.hackathon2.repositorios.EmprestimoRepositorio;
import com.stefanini.hackathon2.repositorios.LivroRepositorio;
import com.stefanini.hackathon2.repositorios.PessoaRepositorio;
import com.stefanini.hackathon2.transacao.Transacional;

@SuppressWarnings("all")
public class EmprestimoServico {
	
	@Inject
	private EmprestimoRepositorio repositorio;
	
	@Transacional
	public void salvar(Emprestimo emprestimo) {
		if (emprestimo.getIdEmprestimo() == null) {
			repositorio.inserir(emprestimo);
		} else {
			repositorio.atualizar(emprestimo);
		}
	}

	@Transacional
	public List<Emprestimo> carregaTodosLivrosDoBanco() {
		return repositorio.todosEmprestimos();
	}

	@Transacional
	public void deletar(Emprestimo emprestimo) {
		repositorio.remover(emprestimo);
	}
	
	@Transacional
	public int calcIntervaloDias(LocalDate inicio, LocalDate fim) {
		LocalDate diaDaSemana = inicio;
		Integer qtdDias = 0;

		if(fim == null) {
			fim = LocalDate.now();
		}
		
		while(diaDaSemana.isBefore(fim)) {
			if(diaDaSemana.getDayOfWeek() == DayOfWeek.FRIDAY) {
				diaDaSemana = diaDaSemana.plusDays(3);
			} else {
				diaDaSemana = diaDaSemana.plusDays(1);
			}
			qtdDias++;
		}
		return qtdDias <= 4 ? 0 : qtdDias - 4;
	}
}
