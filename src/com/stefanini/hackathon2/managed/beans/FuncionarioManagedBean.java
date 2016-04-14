package com.stefanini.hackathon2.managed.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.stefanini.hackathon2.entidades.Funcionario;
import com.stefanini.hackathon2.servicos.FuncionarioServico;
import com.stefanini.hackathon2.util.Mensageiro;

@ManagedBean
@ViewScoped
public class FuncionarioManagedBean {
	
	private Funcionario funcionario;
	private List<Funcionario> listaDeFuncionariosCadastrados;
	
	@Inject
	private FuncionarioServico servico;
	
	public void salvar() {
		servico.salvar(getFuncionario());
		Mensageiro.notificaInformacao("Parabéns", "Funcionario cadastrado com sucesso");
		carregarListaDeFuncionarios();
		limpar();
	}
	
	public void deletar(Funcionario funcionario) {
		servico.deletar(funcionario);
		Mensageiro.notificaInformacao("Parabéns", "Funcionario cadastrado com sucesso");
		carregarListaDeFuncionarios();
		limpar();
	}
	
	public void limpar() {
		setFuncionario(new Funcionario());
	}
	
	private void carregarListaDeFuncionarios() {
		setListaDeFuncionariosCadastrados(servico.carregaTodosFuncionariosDoBanco());
	}
	
	public List<Funcionario> getListaDeFuncionariosCadastrados() {
		if(listaDeFuncionariosCadastrados == null) {
			carregarListaDeFuncionarios();
		}
		return listaDeFuncionariosCadastrados;
	}
	
	public void setListaDeFuncionariosCadastrados(List<Funcionario> listaDeFuncionariosCadastrados) {
		this.listaDeFuncionariosCadastrados = listaDeFuncionariosCadastrados;
	}
	
	public Funcionario getFuncionario() {
		if(this.funcionario == null) {
			limpar();
		}
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
}
