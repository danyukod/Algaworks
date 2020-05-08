package com.algaworks.socialbooks.resources;

import com.algaworks.socialbooks.domain.Livro;
import com.algaworks.socialbooks.services.LivrosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController()
@RequestMapping("/livros")
public class LivrosResources {

    @Autowired
    private LivrosService livrosService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Livro>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(livrosService.listar());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> salvar(@RequestBody Livro livro) {
        livro = livrosService.salvar(livro);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(livro.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
        Livro livro = livrosService.buscar(id);
        return ResponseEntity.status(HttpStatus.OK).body(livro);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        livrosService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> atualizar(@RequestBody Livro livro, @PathVariable Long id) {
        livro.setId(id);
        livrosService.atualizar(livro);
        return ResponseEntity.noContent().build();
    }
}
