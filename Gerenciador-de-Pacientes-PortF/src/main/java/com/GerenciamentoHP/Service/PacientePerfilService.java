package com.GerenciamentoHP.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.GerenciamentoHP.DTO.PacientePerfilDto;
import com.GerenciamentoHP.Model.PacientePerfil;
import com.GerenciamentoHP.Repository.PacientePerfilRepository;
import com.GerenciamentoHP.Repository.SetorRepository;

@Service
public class PacientePerfilService {

    @Autowired
    private PacientePerfilRepository pacientePerfilRepository;

    @Autowired
    SetorRepository setorRepository;

    public PacientePerfil salvarPacientePerfil(PacientePerfil pacientePerfil) {
        Optional<PacientePerfil> pacienteExistente = pacientePerfilRepository.findByrg(pacientePerfil.getRg());
        if (pacienteExistente.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Usuário Já Cadastrado");
        }
        return pacientePerfilRepository.save(pacientePerfil);
    }

    public List<PacientePerfil> getAll() {
        return pacientePerfilRepository.findAll();
    }

    public Optional<PacientePerfil> findByAtendimento(Long atendimento) {
        return pacientePerfilRepository.findById(atendimento);
    }

    public Optional<PacientePerfil> findByRg(Integer rg) {
        return pacientePerfilRepository.findByrg(rg);
    }

    public PacientePerfil atualizarPaciente(Long atendimento, PacientePerfilDto pacientePerfilDto) {
        return pacientePerfilRepository.findById(atendimento).map(paciente -> {
            paciente.setNome(pacientePerfilDto.nome());
            paciente.setRg(pacientePerfilDto.rg());
            paciente.setDataNascimento(pacientePerfilDto.dataNascimento());
            paciente.setPlano(pacientePerfilDto.plano());

            return pacientePerfilRepository.save(paciente);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado"));
    }


}
