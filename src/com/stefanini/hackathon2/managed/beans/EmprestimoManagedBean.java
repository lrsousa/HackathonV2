package com.stefanini.hackathon2.managed.beans;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.stefanini.hackathon2.entidades.Emprestimo;
import com.stefanini.hackathon2.entidades.Livro;
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
		List<Livro> livrosParaRetirada = new ArrayList<Livro>();
		Emprestimo emprestimo = getEmprestimo();
		for (Livro livro : emprestimo.getLivros()) {
			if(livro.getEstoque().getQuantidadeEstoque() > 1) {
				Mensageiro.notificaInformacao(livro.getNome(), "disponível");
				livro.getEstoque().setQuantidadeEstoque(livro.getEstoque().getQuantidadeEstoque() - 1);
				livrosParaRetirada.add(livro);
			}
		}
		emprestimo.setLivros(livrosParaRetirada);
		
		servico.salvar(emprestimo);
		Mensageiro.notificaInformacao("Parabéns", "Emprestimo cadastrado com sucesso");
		carregarListaDeEmprestimos();
		limpar();
	}
	
	public void deletar(Emprestimo emprestimo) {
		servico.deletar(emprestimo);
		Mensageiro.notificaInformacao("Parabéns", "Emprestimo deletado com sucesso");
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
	
	public void finalizarEmprestimo(Emprestimo emprestimo) {
		emprestimo.setDataDevolucaoEfetiva(LocalDate.now());
		servico.salvar(emprestimo);
	}
	
	public String getDiasEmAtraso(String dataRetiradaString, String dataEntregaString) {
		LocalDate localDateRetirada;
		LocalDate localDateEntrega = null;
		if(dataEntregaString.length() == 10) {
			localDateEntrega = LocalDate.parse(dataEntregaString);
		}		
		try {
			localDateRetirada = LocalDate.parse(dataRetiradaString);
			return String.valueOf(servico.calcIntervaloDias(localDateRetirada, localDateEntrega));
		} catch (Exception e) {
			return "0";
		}
	}
}
