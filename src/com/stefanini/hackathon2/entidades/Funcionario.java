package com.stefanini.hackathon2.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

//@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Entity
public class Funcionario extends Pessoa {
	
	@Column(nullable=false)
	private double salario;
	
	@Column(nullable=false)
	private String email;
	
	public Funcionario() {}
	
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
