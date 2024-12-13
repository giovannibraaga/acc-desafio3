package br.api.controller;

import br.api.dto.CursoDTO;
import br.api.entities.Curso;
import br.api.errors.CursoErrors;
import br.api.errors.CursoErrors.RegisterError;
import br.api.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping
    public void register(Curso curso) throws RegisterError {
        cursoService.register(curso);
    }

    @GetMapping
    public String listarCursos(Model model) {
        List<CursoDTO> cursos = cursoService.listarCursos();
        model.addAttribute("cursos", cursos);
        return "studentRegistration";
    }

    @GetMapping("/listar")
    public List<CursoDTO> listaDeCursos() {
        return cursoService.listarCursos();
    }

}