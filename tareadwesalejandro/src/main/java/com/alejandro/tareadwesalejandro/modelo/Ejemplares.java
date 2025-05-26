package com.alejandro.tareadwesalejandro.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "Ejemplares")
public class Ejemplares implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "fk_planta", referencedColumnName = "codigo", nullable=false)
    private Plantas planta;

    @OneToMany(mappedBy = "ejemplar", cascade = CascadeType.ALL)
    private List<Mensajes> mensajes = new LinkedList<>();

    public Ejemplares() {
    }

    public Ejemplares(String nombre, Plantas planta) {
        this.nombre = nombre;
        this.planta = planta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Plantas getPlanta() {
        return planta;
    }

    public void setPlanta(Plantas planta) {
        this.planta = planta;
    }

    public List<Mensajes> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<Mensajes> mensajes) {
        this.mensajes = mensajes;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mensajes, nombre, planta);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Ejemplares other = (Ejemplares) obj;
        return Objects.equals(id, other.id)
                && Objects.equals(nombre, other.nombre)
                && Objects.equals(planta, other.planta)
                && Objects.equals(mensajes, other.mensajes);
    }

    @Override
    public String toString() {
        return "Ejemplares [id=" + id +
                ", nombre=" + nombre +
                ", planta=" + planta +
                ", mensajes=" + mensajes + "]";
    }
}

