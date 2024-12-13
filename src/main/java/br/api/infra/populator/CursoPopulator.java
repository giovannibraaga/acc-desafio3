package br.api.infra.populator;

import br.api.dto.CursoDTO;
import br.api.entities.Curso;
import org.springframework.stereotype.Component;

@Component
public class CursoPopulator {
    public Curso toEntity(CursoDTO cursoDTO) {
        Curso curso = new Curso();
        curso.setName(cursoDTO.getName());
        curso.setDescription(cursoDTO.getDescription());
        return curso;
    }

    public CursoDTO toDTO(Curso curso) {
        CursoDTO cursoDTO = new CursoDTO();
        cursoDTO.setId(curso.getId());
        cursoDTO.setName(curso.getName());
        cursoDTO.setDescription(curso.getDescription());
        return cursoDTO;
    }
}