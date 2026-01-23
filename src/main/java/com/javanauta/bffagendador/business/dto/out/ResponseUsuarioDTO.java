package com.javanauta.bffagendador.business.dto.out;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseUsuarioDTO {
  private String nome;
  private String email;
  private String senha;
  private List<ResponseEnderecoDTO> enderecos;
  private List<ResponseTelefoneDTO> telefones;


}
