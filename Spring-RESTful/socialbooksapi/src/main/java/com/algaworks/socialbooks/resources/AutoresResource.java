package com.algaworks.socialbooks.resources;

import com.algaworks.socialbooks.domain.Autor;
import com.algaworks.socialbooks.services.AutoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutoresResource {

    @Autowired
    private AutoresService autoresService;

    @RequestMapping(method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity<List<Autor>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(autoresService.listar());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> salvar(@Valid @RequestBody Autor autor) {
        autor = autoresService.salvar(autor);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(autor.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Autor> buscar(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(autoresService.buscar(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> atualizar(@RequestBody Autor autor, @PathVariable Long id) {
        autor.setId(id);
        autoresService.atualizar(autor);
        return ResponseEntity.noContent().build();
    }

}
