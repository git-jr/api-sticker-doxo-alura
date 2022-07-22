package br.com.alura.linguagens.api;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.ToIntFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LinguagemController {

    @Autowired
    private LinguagemRepository repositorio;

    @GetMapping("/linguagens")
    public List<Linguagem> obterLinguagens() {
        List<Linguagem> linguagens = repositorio.findAll();

        ToIntFunction<Linguagem> ordenarPorRanking = l -> l.getRanking();
        Comparator<Linguagem> compararRanking = Comparator.comparingInt(ordenarPorRanking);
        linguagens.sort(compararRanking);

        return linguagens;
    }

    @PostMapping("/linguagens")
    public ResponseEntity<Linguagem> cadastrarLinguagens(@RequestBody Linguagem linguagem) {
        Linguagem linguagemSalva = repositorio.save(linguagem);
        return new ResponseEntity<>(linguagemSalva, HttpStatus.CREATED);
    }

    @GetMapping("/linguagens/{id}")
    public Linguagem buscaPorId(@PathVariable String id) {
        Optional<Linguagem> linguagem = repositorio.findById(id);
        return linguagem.get();
    }

    @DeleteMapping("/linguagens/{id}")
    public String deletaPorId(@PathVariable String id) {
        repositorio.deleteById(id);
        return "Deletado";
    }

    @PostMapping("/linguagens/{id}")
    public ResponseEntity<Linguagem> update(@RequestBody Linguagem linguagem, @PathVariable String id) {
        Optional<Linguagem> linguagemAntiga = repositorio.findById(id);
        linguagem.setId(linguagemAntiga.get().getId());
        return new ResponseEntity<>(repositorio.save(linguagem), HttpStatus.OK);
    }

}
