package com.stefanini.hackathon2.entidades;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.stefanini.hackathon2.conversores.LocalDateAttributeConverter;

@Entity
public class Emprestimo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idEmprestimo;
	
	@ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="idPessoa", nullable=true)
	private Pessoa pessoa;
	
	@ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="idLivro", nullable=true)
	private Livro livro;

	@Column(nullable=false)
	@Convert(converter = LocalDateAttributeConverter.class)
	private LocalDate dataRetirada;
	@Column(nullable=false)
	@Convert(converter = LocalDateAttributeConverter.class)
	private LocalDate dataDevolucaoPrevista;
	@Column(nullable=false)
	@Convert(converter = LocalDateAttributeConverter.class)
	private LocalDate dataDevolucaoEfetiva;
	@Column
	private Integer diasEmAtraso;
	
	public Emprestimo() {}

	public Emprestimo(Pessoa pessoa, Livro livro, LocalDate dataRetirada, LocalDate dataDevolucaoPrevista) {
		super();
		this.pessoa = pessoa;
		this.livro = livro;
		this.dataRetirada = dataRetirada;
		this.dataDevolucaoPrevista = dataDevolucaoPrevista;
		this.dataDevolucaoEfetiva = null;
		this.diasEmAtraso = null;
	}

	
	public Integer getId() {
		return idEmprestimo;
	}
	public void setId(Integer id) {
		this.idEmprestimo = id;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public Livro getLivro() {
		return livro;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	public LocalDate getDataRetirada() {
		return dataRetirada;
	}
	public void setDataRetirada(LocalDate dataRetirada) {
		this.dataRetirada = dataRetirada;
	}
	public LocalDate getDataDevolucaoPrevista() {
		return dataDevolucaoPrevista;
	}
	public void setDataDevolucaoPrevista(LocalDate dataDevolucaoPrevista) {
		this.dataDevolucaoPrevista = dataDevolucaoPrevista;
	}
	public LocalDate getDataDevolucaoEfetiva() {
		return dataDevolucaoEfetiva;
	}
	public void setDataDevolucaoEfetiva(LocalDate dataDevolucaoEfetiva) {
		this.dataDevolucaoEfetiva = dataDevolucaoEfetiva;
	}
	public Integer getDiasEmAtraso() {
		return diasEmAtraso;
	}
	public void setDiasEmAtraso(Integer diasEmAtraso) {
		this.diasEmAtraso = diasEmAtraso;
	}
}
