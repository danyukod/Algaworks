package com.algaworks.socialbooks.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "O campo nome não pode ser vazio.")
    private String nome;

    @JsonInclude(Include.NON_NULL)
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "Campo publicação é de preenchimento obrigatório.")
    private Date publicacao;

    @JsonInclude(Include.NON_NULL)
    @NotNull(message = "Campo editora é de preenchimento obrigatório.")
    private String editora;

    @JsonInclude(Include.NON_NULL)
    @NotEmpty(message = "O resumo deve ser preenchido.")
    @Size(max = 1500, message = "O resumo não pode conter mais de 1500 caracteres.")
    private String resumo;

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(mappedBy = "livro")
    private List<Comentario> comentarios;

    @JsonInclude(Include.NON_NULL)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "AUTOR_ID")
    private Autor autor;

    public Livro() {
    }

    public Livro(String nome) {
        this.nome = nome;
    }

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

    public Date getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(Date publicacao) {
        this.publicacao = publicacao;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
