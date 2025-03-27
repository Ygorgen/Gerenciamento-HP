package com.GerenciamentoHP.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.GerenciamentoHP.Model.Setor;
import com.GerenciamentoHP.Repository.PacientePerfilRepository;
import com.GerenciamentoHP.Repository.SetorRepository;

@Service
public class SetorService {

    @Autowired
    PacientePerfilRepository pacientePerfilRepository;

    @Autowired
    SetorRepository setorRepository;

    public Setor salvarSetor(Setor setor) {
        List<Setor> setoresExistentes = setorRepository.findAllByNomeContainingIgnoreCase(setor.getNome());

        if (!setoresExistentes.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Setor Já Cadastrado");
        }
        return setorRepository.save(setor);
    }

    public List<Setor> listarTodosSetores() {
        return setorRepository.findAll();
    }
}
