package com.project.gestaoFesta.controller;

import com.project.gestaoFesta.model.Convidado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.project.gestaoFesta.repository.Convidados;
import com.project.gestaoFesta.service.ConvidadosService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/convidados")
public class ConvidadosController {

    @Autowired
    private ConvidadosService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Convidado>> listar() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id_convidado}")
    public ResponseEntity confirmar(@PathVariable Long id_convidado) {
        return ResponseEntity.ok(service.checkPresence(id_convidado));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Convidado> salvar(@RequestBody Convidado convidado) {
        return ResponseEntity.ok(service.save(convidado));
    }

    @DeleteMapping(value = "/{convidado}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Optional<Convidado>> deletar(@PathVariable("convidado") long id){
        return ResponseEntity.ok(service.delete(id));
    }
}
