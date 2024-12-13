package br.api.service;

import br.api.entities.Student;
import br.api.errors.StudentsErrors.*;
import br.api.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterSuccess() {
        Student student = new Student();
        student.setName("Giovanni Braga");
        student.setEmail("giovanni@email.com");
        student.setPassword("Giovanni123");
        student.setBirthDate("07-28-2005");
        student.setCep("03446000");
        student.setCidade("São Paulo");

        assertDoesNotThrow(() -> studentService.register(student));
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void testeCadastroComCamposFaltando() {
        Student student = new Student();
        student.setName("Giovanni Braga");
        student.setEmail("giovanni@email.com");
        student.setPassword("Giovanni123");

        Exception exception = assertThrows(RegisterError.class, () -> studentService.register(student));
        assertEquals("Todos os campos devem ser preenchidos", exception.getMessage());
        verify(studentRepository, times(0)).save(any());
    }

    @Test
    void testeLoginOK() {
        Student student = new Student();
        student.setEmail("giovanni@email.com");
        student.setPassword("Giovanni123");

        when(studentRepository.findStudentByEmail("giovanni@email.com")).thenReturn(student);

        assertDoesNotThrow(() -> studentService.login("giovanni@email.com", "Giovanni123"));
    }

    @Test
    void testeLoginEmailNaoExiste() {
        when(studentRepository.findStudentByEmail("giovanni@email.com")).thenReturn(null);

        LoginError exception = assertThrows(LoginError.class, () -> studentService.login("giovanni@email.com", "Giovanni123"));
        assertEquals("Email ou senha incorretos", exception.getMessage());
    }

    @Test
    void testLoginInvalidPassword() {
        Student student = new Student();
        student.setEmail("giovanni@email.com");
        student.setPassword("Giovanni123");

        when(studentRepository.findStudentByEmail("giovanni@email.com")).thenReturn(student);

        LoginError exception = assertThrows(LoginError.class, () -> studentService.login("giovanni@email.com", "dsadsadsadsad3dsadsadsadsa"));
        assertEquals("Email ou senha incorretos", exception.getMessage());
    }

    @Test
    void testeUpdateOK() {
        Student student = new Student();
        student.setEmail("giovanni@email.com");
        student.setName("GiovanniBernardo");

        assertDoesNotThrow(() -> studentService.update(student));
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void testeApagarOK() {
        assertDoesNotThrow(() -> studentService.delete(1L));
        verify(studentRepository, times(1)).deleteById(1L);
    }

    @Test
    void testeDeDeleteComErroDB() {
        doThrow(new RuntimeException("Database error")).when(studentRepository).deleteById(2L);

        DeleteStudentError exception = assertThrows(DeleteStudentError.class, () -> studentService.delete(2L));
        assertEquals("Erro ao deletar aluno", exception.getMessage());
    }
}
