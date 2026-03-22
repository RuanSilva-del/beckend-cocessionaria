package com.br.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.model.Automovel;
import com.br.repository.AutomovelRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequestMapping("/cautomovel/")
@RestController
@CrossOrigin(origins="*")
public class AutomovelController {
    @Autowired
    private AutomovelRepository arep;

    @GetMapping("/automovel")
    public List<Automovel> listar(){
        return this.arep.findAll(Sort.by(Sort.Direction.DESC, "codigo" ));
    }

    @GetMapping("/automovel/{id}")
    public ResponseEntity<Automovel> consultar(@PathVariable Long id) {
        Automovel automovel = this.arep.findById(id).orElseThrow(() ->
            new RuntimeException("Automovel nao encontrado: " + id));
        return ResponseEntity.ok(automovel);
    }

    @PostMapping("/automovel")
    public Automovel inserir(@RequestBody Automovel automovel) {
        return this.arep.save(automovel);
    }
    
}