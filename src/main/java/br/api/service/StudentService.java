package br.api.service;

import br.api.entities.Student;
import br.api.errors.StudentsErrors.*;
import br.api.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Transactional
    public void register(Student student) throws RegisterError {
        if (student.getName() == null || student.getEmail() == null || student.getPassword() == null ||
                student.getBirthDate() == null || student.getCep() == null || student.getCidade() == null) {
            throw new RegisterError("Todos os campos devem ser preenchidos");
        }

        try {
            studentRepository.save(student);
        } catch (Exception e) {
            throw new RegisterError("Erro ao registrar aluno");
        }
    }

    public void login(String email, String password) throws LoginError {
        Student student = studentRepository.findStudentByEmail(email.toLowerCase());
        if (student == null || !Objects.equals(student.getPassword(), password)) {
            throw new LoginError("Email ou senha incorretos");
        }
    }

    @Transactional
    public void update(Student student) throws UpdateStudentError {
        try {
            studentRepository.save(student);
        } catch (Exception e) {
            throw new UpdateStudentError("Erro ao atualizar aluno");
        }
    }

    @Transactional
    public void delete(Long id) throws DeleteStudentError {
        try {
            studentRepository.deleteById(id);
        } catch (Exception e) {
            throw new DeleteStudentError("Erro ao deletar aluno");
        }
    }
}
