package br.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.api.entities.Adress;

public interface CepRepository extends JpaRepository<Adress, String> {
    
}