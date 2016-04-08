package com.stefanini.hackathon2.managed.beans;

import java.util.List;

import javax.inject.Inject;

import com.stefanini.hackathon2.entidades.Emprestimo;
import com.stefanini.hackathon2.servicos.EmprestimoServico;
import com.stefanini.hackathon2.util.Mensageiro;

public class EmprestimoManagedBean {
	private Emprestimo emprestimo;
	private List<Emprestimo> listaDeEmprestimosCadastrados;

	@Inject
	private EmprestimoServico servico;
	
	public EmprestimoManagedBean(){}
	
	public void salvar() {
		servico.salvar(getEmprestimo());
		Mensageiro.notificaInformacao("Parabéns", "Usuario cadastrado com sucesso");
		carregarListaDeEmprestimos();
		limpar();
	}
	
	public void deletar(Emprestimo emprestimo) {
		servico.deletar(emprestimo);
		Mensageiro.notificaInformacao("Parabéns", "Usuario cadastrado com sucesso");
		carregarListaDeEmprestimos();
		limpar();
	}
	
	public void limpar() {
		setPessoa(new Emprestimo());
	}
	
	private void carregarListaDeEmprestimos() {
		setListaDeEmprestimosCadastrados(servico.carregaTodosLivrosDoBanco());
	}
	
	public List<Emprestimo> getListaDeEmprestimosCadastrados() {
		if(listaDeEmprestimosCadastrados == null) {
			carregarListaDeEmprestimos();
		}
		return listaDeEmprestimosCadastrados;
	}
	
	public void setListaDeEmprestimosCadastrados(List<Emprestimo> listaDeEmprestimosCadastrados) {
		this.listaDeEmprestimosCadastrados = listaDeEmprestimosCadastrados;
	}

	public Emprestimo getEmprestimo() {
		return emprestimo;
	}
	public void setPessoa(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
	}
}
