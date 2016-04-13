package com.stefanini.hackathon2.entidades;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.stefanini.hackathon2.conversores.LocalDateAttributeConverter;

@Entity
public class Emprestimo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idEmprestimo;
	
	@ManyToMany(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "idLivro", nullable = false)
	private List<Livro> livros;
	
	@ManyToOne(cascade=CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name="cpf", nullable=false)
	private Pessoa pessoa;
	
	@Column(nullable=false)
	@Convert(converter = LocalDateAttributeConverter.class)
	private LocalDate dataRetirada;
	
	@Column
	@Convert(converter = LocalDateAttributeConverter.class)
	private LocalDate dataDevolucaoEfetiva;
	
	public Emprestimo() {
		pessoa = new Pessoa();
		this.dataRetirada = LocalDate.now();
	}
	
	public Integer getIdEmprestimo() {
		return idEmprestimo;
	}
	public void setIdEmprestimo(Integer id) {
		this.idEmprestimo = id;
	}
	public LocalDate getDataRetirada() {
		return dataRetirada;
	}
	public void setDataRetirada(LocalDate dataRetirada) {
		this.dataRetirada = dataRetirada;
	}
	public LocalDate getDataDevolucaoEfetiva() {
		return dataDevolucaoEfetiva;
	}
	public void setDataDevolucaoEfetiva(LocalDate dataDevolucaoEfetiva) {
		this.dataDevolucaoEfetiva = dataDevolucaoEfetiva;
	}
	public List<Livro> getLivros() {
		return livros;
	}
	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
}
