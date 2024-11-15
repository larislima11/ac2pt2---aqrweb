package com.example.demo.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class CursoDTO {
    private Long id;
    private String nome;
    private int cargaHoraria;
    private List<ProfessorDTO> professores;
    public CursoDTO(Long id, String nome, int cargaHoraria) {
        this.id = id;
        this.cargaHoraria = cargaHoraria;
    }
}