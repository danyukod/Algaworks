package br.com.algaworks.filmes.controller;


import br.com.algaworks.filmes.model.Filme;
import br.com.algaworks.filmes.service.FilmeService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;

@WebMvcTest
public class FilmeControllerTest {

    @Autowired
    private FilmeController filmeController;

    @MockBean
    private FilmeService filmeService;

    @BeforeEach
    public void setup() {
        standaloneSetup(this.filmeController);
    }

    @Test
    public void deveRetornarSucesso_QuandoBuscarFilme() {
        //cenario
        when(this.filmeService
                .obterFilme(1L))
                .thenReturn(new Filme(1L, "O Poderoso Chefao", "Sem descricao"));

        //validacao
        given()
                .accept(ContentType.JSON)
                .when().get("/filmes/{codigo}", 1L)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void deveRetornarNaoEncontrado_QuandoBuscarfilme() {
        //cenario
        when(this.filmeService
                .obterFilme(5L))
                .thenReturn(null);

        //validacao
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/filmes/{codigo}", 5L)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void deveRetornarBadRequest_QuandoBuscarFilme() {

        given()
                .accept(ContentType.JSON)
                .when()
                .get("/filmes/{codigo}", -5L)
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());

        Mockito.verify(this.filmeService, never()).obterFilme(-1L);
    }
}
