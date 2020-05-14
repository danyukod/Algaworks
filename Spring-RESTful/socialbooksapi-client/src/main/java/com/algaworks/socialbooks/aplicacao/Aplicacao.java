package com.algaworks.socialbooks.aplicacao;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class Aplicacao {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();

        RequestEntity<Void> request = RequestEntity.get(URI.create("http://localhost:8080/livros"))
                .header("Authorization", "Basic c3ByaW5nOnNlY3JldA==").build();

        ResponseEntity<Livro[]> response = restTemplate.exchange(request, Livro[].class);

        for(Livro livro : response.getBody()){
            System.out.println("Livro: " + livro.getNome());
        }
    }
}
