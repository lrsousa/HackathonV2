package com.stefanini.hackathon2.managed.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.stefanini.hackathon2.entidades.Emprestimo;
import com.stefanini.hackathon2.servicos.EmprestimoServico;
import com.stefanini.hackathon2.util.Mensageiro;

@ManagedBean
@ViewScoped
public class EmprestimoManagedBean {
	private Emprestimo emprestimo;
	private List<Emprestimo> listaDeEmprestimosCadastrados;

	@Inject
	private EmprestimoServico servico;
	
	public EmprestimoManagedBean(){}
	
	public void salvar() {
		Emprestimo emprestimo = getEmprestimo();
//		System.out.println(emprestimo.getLivro().getId() + " - " + emprestimo.getPessoa().getId());
//		System.out.println(emprestimo.getDataRetirada());
//		System.out.println(emprestimo.getDataDevolucaoPrevista());
		emprestimo.setId(null);
		servico.salvar(emprestimo);
		Mensageiro.notificaInformacao("Parabéns", "Emprestimo cadastrado com sucesso");
		carregarListaDeEmprestimos();
		limpar();
	}
	
	public void deletar(Emprestimo emprestimo) {
		servico.deletar(emprestimo);
		Mensageiro.notificaInformacao("Parabéns", "Emprestimo cadastrado com sucesso");
		carregarListaDeEmprestimos();
		limpar();
	}
	
	public void limpar() {
		setEmprestimo(new Emprestimo());
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
		if(emprestimo == null) {
			limpar();
		}
		return emprestimo;
	}
	public void setEmprestimo(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
	}
}
