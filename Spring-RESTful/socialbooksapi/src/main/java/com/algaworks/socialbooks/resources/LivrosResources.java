package com.algaworks.socialbooks.resources;

import com.algaworks.socialbooks.domain.Livro;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class LivrosResources {

    @RequestMapping(value = "/livros", method = RequestMethod.GET)
    public List<Livro> listar() {

        Livro l1 = new Livro("Rest Aplicado");
        Livro l2 = new Livro("Git passo-a-passo");

        Livro[] livros = {l1, l2};

        return Arrays.asList(livros);

    }

}
