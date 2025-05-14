package com.alejandro.tareadwesalejandro.modelo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table (name = "Plantas")
public class Plantas implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true)
	private String codigo;
	
	@Column
	private String nombreComun;
	
	@Column
	private String nombreCientifico;
	
	@OneToMany
	(mappedBy = "planta", cascade = CascadeType.ALL)
	private Set<Ejemplares> ejemplares = new HashSet<Ejemplares>();
	
	public Plantas () {}
	
	public Plantas(String codigo, String nombreComun, String nombreCientifico) {
		super();
		this.codigo = codigo;
		this.nombreComun = nombreComun;
		this.nombreCientifico = nombreCientifico;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombreComun() {
		return nombreComun;
	}

	public void setNombreComun(String nombreComun) {
		this.nombreComun = nombreComun;
	}

	public String getNombreCientifico() {
		return nombreCientifico;
	}

	public void setNombreCientifico(String nombreCientifico) {
		this.nombreCientifico = nombreCientifico;
	}

	public Set<Ejemplares> getEjemplares() {
		return ejemplares;
	}

	public void setEjemplares(Set<Ejemplares> ejemplares) {
		this.ejemplares = ejemplares;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, ejemplares, id, nombreCientifico, nombreComun);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Plantas other = (Plantas) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(ejemplares, other.ejemplares)
				&& Objects.equals(id, other.id) && Objects.equals(nombreCientifico, other.nombreCientifico)
				&& Objects.equals(nombreComun, other.nombreComun);
	}

	@Override
	public String toString() {
		return "Plantas [id=" + id + ", codigo=" + codigo + ", nombreComun=" + nombreComun + ", nombreCientifico="
				+ nombreCientifico + ", ejemplares=" + ejemplares + "]";
	}


}
