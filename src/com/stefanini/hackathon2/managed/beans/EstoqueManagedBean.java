package com.stefanini.hackathon2.managed.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.stefanini.hackathon2.entidades.Estoque;
import com.stefanini.hackathon2.servicos.EstoqueServico;
import com.stefanini.hackathon2.util.Mensageiro;

@ManagedBean
@ViewScoped
public class EstoqueManagedBean {
	Estoque estoque;
	List<Estoque> listaEstoque;
	
	@Inject
	private EstoqueServico servico;

	public EstoqueManagedBean() {}
	
	public void salvar() {
		getEstoque().setQuantidadeEstoque(getEstoque().getQuantidadeAcervo());
		servico.salvar(getEstoque());
		Mensageiro.notificaInformacao("Parabéns!", "Estoque salvo com sucesso!");
		limpar();
	}
	
	public void deletar(Estoque estoque) {
		servico.deletar(estoque);
		Mensageiro.notificaInformacao("Parabéns!", "Estoque deletado com sucesso!");
		limpar();
	}
	
	public void limpar() {
		setEstoque(new Estoque());
	}
	
	public void carregaListaDeEstoque() {
		setListaEstoque(servico.carregaTodoEstoqueDoBanco());
	}
	
	public List<Estoque> getListaEstoque() {
		if(listaEstoque == null) {
			carregaListaDeEstoque();
		}
		return listaEstoque;
	}
	
	public void setListaEstoque(List<Estoque> listaEstoque) {
		this.listaEstoque = listaEstoque;
	}
	
	public Estoque getEstoque() {
		if(estoque == null) {
			limpar();
		}
		return estoque;
	}
	
	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}
}
