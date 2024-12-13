package br.api.controller;

import br.api.entities.Student;
import br.api.errors.StudentsErrors.DeleteStudentError;
import br.api.errors.StudentsErrors.LoginError;
import br.api.errors.StudentsErrors.RegisterError;
import br.api.errors.StudentsErrors.UpdateStudentError;
import br.api.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity register(@RequestBody Student student) throws RegisterError {
        try {
            studentService.register(student);
            return ResponseEntity.created(new URI("/student/" + student.getId())).build();
        } catch (URISyntaxException e) {
            throw new RegisterError("Erro ao registrar o aluno");
        }
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Student student) {
        try {
            studentService.login(student.getEmail(), student.getPassword());
            return ResponseEntity.ok("Login realizado com sucesso");
        } catch (LoginError e) {
            return ResponseEntity.status(401).body("Email ou senha incorretos");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro interno ao realizar o login");
        }
    }

    @PutMapping
    public void update(@RequestParam Student student) throws UpdateStudentError {
        studentService.update(student);
    }

    @DeleteMapping
    public void delete(@RequestParam Long id) throws DeleteStudentError {
        studentService.delete(id);
    }
}
