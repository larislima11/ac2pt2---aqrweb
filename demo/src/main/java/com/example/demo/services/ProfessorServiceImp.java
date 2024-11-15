package com.example.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.CursoDTO;
import com.example.demo.dtos.ProfessorDTO;
import com.example.demo.models.Curso;
import com.example.demo.models.Professor;
import com.example.demo.repository.CursoRepository;
import com.example.demo.repository.ProfessorRepository;

@Service
public class ProfessorServiceImp implements ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public void adicionar(ProfessorDTO professorDTO) {
        Professor professor = Professor.builder()
            .nome(professorDTO.getNome())
            .cpf(professorDTO.getCpf())
            .cep(professorDTO.getCep())
            .endereco(professorDTO.getEndereco())
            .cidade(professorDTO.getCidade())
            .uf(professorDTO.getUF())
            .telefone(professorDTO.getTelefone())
            .especializacoes(professorDTO.getCurso() != null ? 
                professorDTO.getCurso().stream()
                    .map(cursoDTO -> cursoRepository.findById(cursoDTO.getId())
                        .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado")))
                    .collect(Collectors.toList()) : null)
            .build();
        professorRepository.save(professor);
    }

    @Override
    public List<ProfessorDTO> professoresPorEspecialidade(Long idCurso) {
        Curso curso = cursoRepository.findById(idCurso)
            .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));
        List<Professor> professores = professorRepository.findByEspecializacoes(curso);
        return professores.stream()
            .map(this::convertToProfessorDTO)
            .collect(Collectors.toList());
    }

    @Override
    public void adicionarEspecializacao(Long idProfessor, Long idCurso) {
        Curso curso = cursoRepository.findById(idCurso)
            .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));
        Professor professor = professorRepository.findById(idProfessor)
            .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));
        
        if (!professor.getEspecializacoes().contains(curso)) {
            professor.getEspecializacoes().add(curso);
            professorRepository.save(professor);
        }
    }

    @Override
    public List<ProfessorDTO> buscarTodosProfessores() {
        return professorRepository.findAll().stream()
            .map(this::convertToProfessorDTO)
            .collect(Collectors.toList());
    }

    @Override
    public void criarProfessor(ProfessorDTO professorDTO) {
        adicionar(professorDTO);
    }

    @Override
    public List<ProfessorDTO> listarProfessoresPorCurso(Long idCurso) {
        return professoresPorEspecialidade(idCurso);
    }

    @Override
    public List<ProfessorDTO> listarTodosOsProfessores() {
        return buscarTodosProfessores();
    }

    private ProfessorDTO convertToProfessorDTO(Professor professor) {
        return ProfessorDTO.builder()
            .id(professor.getId())
            .nome(professor.getNome())
            .cpf(professor.getClass())
            .cep(professor.getClass())
            .endereco(professor.getEndereco())
            .cidade(professor.getCidade())
            .UF(professor.getId())
            .telefone(professor.getTelefone())
            .curso(professor.getEspecializacoes().stream()
                .map(curso -> new CursoDTO(curso.getId(), curso.getNome(), curso.getCargaHoraria()))
                .collect(Collectors.toList()))
            .build();
    }
}