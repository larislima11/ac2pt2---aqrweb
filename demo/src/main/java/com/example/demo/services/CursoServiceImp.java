package com.example.demo.services;

import java.util.Collection;
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
public class CursoServiceImp implements CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Override
    public void criarCurso(CursoDTO cursoDTO) {
        Curso cursoEntity = Curso.builder()
            .nome(cursoDTO.getNome())
            .cargaHoraria(cursoDTO.getCargaHoraria())
            .build();
        cursoRepository.save(cursoEntity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void associarProfessorAoCurso(Long idCurso, Long idProfessor) {
        Curso curso = cursoRepository.findById(idCurso)
            .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));
        Professor professor = professorRepository.findById(idProfessor)
            .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));

        if (!curso.getProfessores().contains(professor)) {
            curso.getProfessores().add(professor);
            professor.getEspecializacoes().addAll((Collection<? extends Professor>) curso);
            cursoRepository.save(curso);
            professorRepository.save(professor);
        }
    }

    @Override
    public List<CursoDTO> listarTodosOsCursos() {
        List<Curso> cursos = cursoRepository.findAll();
        return cursos.stream()
            .map(this::convertToCursoDTO)
            .collect(Collectors.toList());
    }

    @Override
    public CursoDTO buscarCursoPorId(Long idCurso) {
        Curso curso = cursoRepository.findById(idCurso)
            .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));
        return convertToCursoDTO(curso);
    }

    private CursoDTO convertToCursoDTO(Curso curso) {
        return CursoDTO.builder()
            .id(curso.getId())
            .nome(curso.getNome())
            .cargaHoraria(curso.getCargaHoraria())
            .professores(
                curso.getProfessores().stream()
                    .map(professor -> new ProfessorDTO(
                        professor.getId(),
                        professor.getNome(),
                        professor.getClass(),
                        professor.getClass(),
                        professor.getEndereco(),
                        professor.getCidade(),
                        professor.getId(),
                        professor.getTelefone(),
                        null))
                    .collect(Collectors.toList())
            )
            .build();
    }

    @Override
    public List<CursoDTO> buscarCursos() {
        throw new UnsupportedOperationException("Unimplemented method 'buscarCursos'");
    }

    @Override
    public void adicionar(CursoDTO cursoDTO) {
        throw new UnsupportedOperationException("Unimplemented method 'adicionar'");
    }

    @Override
    public void adicionarProfessor(Long idCurso, Long idProfessor) {
        throw new UnsupportedOperationException("Unimplemented method 'adicionarProfessor'");
    }
}
