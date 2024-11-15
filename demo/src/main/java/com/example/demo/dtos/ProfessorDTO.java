package com.example.demo.dtos;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfessorDTO {
    private Long id;
    private String nome;
    private String cpf;
    private int cep;
    private String endereco;
    private String cidade;
    private String UF;
    private String telefone;
    private List<CursoDTO> curso;
}