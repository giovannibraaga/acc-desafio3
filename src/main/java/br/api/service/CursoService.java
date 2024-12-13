package br.api.service;

import br.api.dto.CursoDTO;
import br.api.entities.Curso;
import br.api.errors.CursoErrors.RegisterError;
import br.api.infra.populator.CursoPopulator;
import br.api.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private CursoPopulator cursoPopulator;

    @Transactional
    public void register(Curso curso) throws RegisterError {
        if (curso.getName() == null || curso.getName().isEmpty()) {
            throw new RegisterError("O nome do curso é obrigatório.");
        }

        if (curso.getDescription() == null || curso.getDescription().isEmpty()) {
            throw new RegisterError("A descrição do curso é obrigatória.");
        }

        try {
            cursoRepository.save(curso);
        } catch (Exception e) {
            throw new RegisterError("Erro ao registrar um novo curso");
        }
    }

    public List<CursoDTO> listarCursos() {
        return cursoRepository.findAll().stream()
                .map(cursoPopulator::toDTO)
                .collect(Collectors.toList());
    }
}