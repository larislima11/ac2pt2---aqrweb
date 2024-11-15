package com.example.demo.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.dtos.CursoDTO;
import com.example.demo.dtos.ProfessorDTO;
import com.example.demo.models.Curso;
import com.example.demo.models.Professor;
import com.example.demo.repository.CursoRepository;
import com.example.demo.repository.ProfessorRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class LoadDataBase {

    @Bean
    CommandLineRunner initDatabase(CursoRepository cursoRepository, 
                                   ProfessorRepository professorRepository) {
        return args -> {
            CursoDTO cursoDTO1 = new CursoDTO(1L, "Arquitetura Web", 35);
            CursoDTO cursoDTO2 = new CursoDTO(2L, "Bussiness Inteligence", 35);
            CursoDTO cursoDTO3 = new CursoDTO(3L, "Programação Mobile", 30);

            Curso curso1 = new Curso(cursoDTO1.getId(), cursoDTO1.getNome(), cursoDTO1.getCargaHoraria());
            Curso curso2 = new Curso(cursoDTO2.getId(), cursoDTO2.getNome(), cursoDTO2.getCargaHoraria());
            Curso curso3 = new Curso(cursoDTO3.getId(), cursoDTO3.getNome(), cursoDTO3.getCargaHoraria());

            cursoRepository.save(curso1);
            cursoRepository.save(curso2);
            cursoRepository.save(curso3);

            ProfessorDTO professorDTO1 = new ProfessorDTO();
            professorDTO1.setId(1L);
            professorDTO1.setNome("Russo Passapusso");
            professorDTO1.setCpf("12345678900");
            professorDTO1.setCep(12345678);
            professorDTO1.setEndereco("Rua A, 123");
            professorDTO1.setCidade("Belo Horizonte");
            professorDTO1.setUF("MG");
            professorDTO1.setTelefone("31987654321");

            ProfessorDTO professorDTO2 = new ProfessorDTO();
            professorDTO2.setId(2L);
            professorDTO2.setNome("Emicida");
            professorDTO2.setCpf("09876543211");
            professorDTO2.setCep(87654321);
            professorDTO2.setEndereco("Rua B, 456");
            professorDTO2.setCidade("São Paulo");
            professorDTO2.setUF("SP");
            professorDTO2.setTelefone("31987654322");

            List<CursoDTO> cursosProfessor1 = new ArrayList<>();
            cursosProfessor1.add(cursoDTO1); 
            cursosProfessor1.add(cursoDTO3); 

            List<CursoDTO> cursosProfessor2 = new ArrayList<>();
            cursosProfessor2.add(cursoDTO2); 

            professorDTO1.setCurso(cursosProfessor1);
            professorDTO2.setCurso(cursosProfessor2);

            Professor professor1 = new Professor(professorDTO1.getId(), professorDTO1.getNome(), professorDTO1.getCpf(), professorDTO1.getCep(),
                                                 professorDTO1.getEndereco(), professorDTO1.getCidade(), professorDTO1.getUF(), professorDTO1.getTelefone(), null);
            Professor professor2 = new Professor(professorDTO2.getId(), professorDTO2.getNome(), professorDTO2.getCpf(), professorDTO2.getCep(),
                                                 professorDTO2.getEndereco(), professorDTO2.getCidade(), professorDTO2.getUF(), professorDTO2.getTelefone(), null);

            professor1.getCursos().add(curso1);
            professor1.getCursos().add(curso3);

            professor2.getCursos().add(curso2);

            professorRepository.save(professor1);
            professorRepository.save(professor2);
        };
    }
}