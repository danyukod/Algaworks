package com.algaworks.socialbooks.resources;

import com.algaworks.socialbooks.services.LivrosService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;

@WebMvcTest
public class LivrosResourcesTest {

    @Autowired
    private LivrosResources livrosResources;

    @MockBean
    private LivrosService livrosService;

    @BeforeEach
    public void setup() {
        standaloneSetup(this.livrosResources);
    }

    @Test
    public void deveRetornarSucesso_QuandoListarFilmes() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/livros")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void deveRetornarPaginaNaoEncontrada_QuandoListarFilmes() {

        given()
                .accept(ContentType.JSON)
                .when().get("/filmes")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

}
