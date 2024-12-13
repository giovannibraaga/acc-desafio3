package br.api.service;

import br.api.entities.Curso;
import br.api.errors.CursoErrors;
import br.api.repository.CursoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CursoServiceTest {

    @InjectMocks
    private CursoService cursoService;

    @Mock
    private CursoRepository cursoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testeCadastroCerto() {
        Curso curso = new Curso();
        curso.setName("Spring MVC");
        curso.setDescription("Curso Spring MVC Accenture");

        assertDoesNotThrow(() -> cursoService.register(curso));
        verify(cursoRepository, times(1)).save(curso);
    }

    @Test
    void testeCadastroErroNome() {
        Curso curso = new Curso();
        curso.setDescription("Curso de SAP Commerce Accenture");

        CursoErrors.RegisterError exception = assertThrows(CursoErrors.RegisterError.class, () -> {
            cursoService.register(curso);
        });

        assertEquals("O nome do curso é obrigatório.", exception.getMessage());

        verify(cursoRepository, times(0)).save(any());
    }

    @Test
    void testeCadastroErroDescricao() {
        Curso curso = new Curso();
        curso.setName("SAP Commerce");

        CursoErrors.RegisterError exception = assertThrows(CursoErrors.RegisterError.class, () -> {
            cursoService.register(curso);
        });

        assertEquals("A descrição do curso é obrigatória.", exception.getMessage());

        verify(cursoRepository, times(0)).save(any());
    }
}
