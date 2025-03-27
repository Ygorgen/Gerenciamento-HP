package com.GerenciamentoHP.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.GerenciamentoHP.Model.Setor;
import com.GerenciamentoHP.Service.SetorService;

@RestController
@RequestMapping("/setores")
public class SetorController {
 
    @Autowired
    SetorService setorService;

    @PostMapping
    public ResponseEntity<Setor> criarSetor(@RequestBody Setor setor) {
        Setor setorSalvo = setorService.salvarSetor(setor);
        return ResponseEntity.status(HttpStatus.CREATED).body(setorSalvo);
    }

     @GetMapping
    public ResponseEntity<List<Setor>> getAllSetores() {
        List<Setor> setores = setorService.listarTodosSetores();
        return ResponseEntity.ok(setores);
    }
}
