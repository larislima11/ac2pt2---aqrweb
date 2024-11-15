package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Curso;
import com.example.demo.models.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    List<Professor> findByEspecializacoes(Curso curso);
    Professor findByRg(String rgProfessor);
}