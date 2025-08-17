package com.filmchoice.entities;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Idioma")
public class Idioma {

    @Id
    @GeneratedValue(generator = "jpa_idioma_seq")
    @SequenceGenerator(name = "jpa_idioma_seq", sequenceName = "idioma_id_seq",  allocationSize = 1 )
    private Long id;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @ManyToMany
    @JoinTable(name="idioma_id",
                joinColumns = @JoinColumn(name="idioma_id"), 
                inverseJoinColumns = @JoinColumn(name="filme_id")
                )
    private List<Filme> filmes;

    public Idioma(){};
    public Idioma(String tipo) {
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Idioma idioma = (Idioma) o;
        return id != null && Objects.equals(id, idioma.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Idioma{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
