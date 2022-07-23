package br.com.alura.linguagens.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LinguagemController {

    @Autowired
    private LinguagemRepository repositorio;

    @GetMapping("/linguagens")
    public List<Linguagem> obterLinguagens() {
        List<Linguagem> linguagens = repositorio.findAll(Sort.by(Sort.Direction.DESC,"ranking"));

        return linguagens;
    }

    @PostMapping("/linguagens")
    public ResponseEntity<Linguagem> cadastrarLinguagens(@RequestBody Linguagem linguagem) {
        Linguagem linguagemSalva = repositorio.save(linguagem);
        return new ResponseEntity<>(linguagemSalva, HttpStatus.CREATED);
    }

    @PutMapping("/linguagens/{id}")
    public ResponseEntity<Linguagem> atualizar(@RequestBody Linguagem linguagem, @PathVariable String id) {
        Optional<Linguagem> linguagemAntiga = repositorio.findById(id);
        linguagem.setId(linguagemAntiga.get().getId());
        return new ResponseEntity<>(repositorio.save(linguagem), HttpStatus.OK);
    }


    @DeleteMapping("/linguagens/{id}")
    public String deleta(@PathVariable String id) {
        repositorio.deleteById(id);
        return "Deletado";
    }

    @GetMapping("/linguagens/{id}")
    public Linguagem buscaPorId(@PathVariable String id) {
        Optional<Linguagem> linguagem = repositorio.findById(id);
        return linguagem.get();
    }

    @PatchMapping("/vote/{id}")
    public Linguagem votaLinguagem(@PathVariable String id){
        var linguagem = repositorio.findById(id)
        .orElseThrow( () -> new IllegalArgumentException("Falha na votação, id não encontrado"));

        linguagem.setRanking(linguagem.getRanking()+1);
        return repositorio.save(linguagem);
    }

}
