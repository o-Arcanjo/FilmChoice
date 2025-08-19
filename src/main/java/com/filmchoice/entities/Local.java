package com.filmchoice.entities;

import jakarta.persistence.*;
import org.locationtech.jts.geom.Point;

@Entity
@Table(name = "local")
public class Local {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(name = "imagem_url", length = 255)
    private String imagemUrl;

    @Column(columnDefinition = "GEOGRAPHY(Point,4326)")
    private Point geom;

    // Construtores
    public Local() {
    }

    public Local(String nome, String imagemUrl, Point geom) {
        this.nome = nome;
        this.imagemUrl = imagemUrl;
        this.geom = geom;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public Point getGeom() {
        return geom;
    }

    public void setGeom(Point geom) {
        this.geom = geom;
    }
}
