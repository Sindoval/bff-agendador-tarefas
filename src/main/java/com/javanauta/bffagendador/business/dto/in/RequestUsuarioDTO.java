package com.javanauta.bffagendador.business.dto.in;

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
public class RequestUsuarioDTO {
  private String nome;
  private String email;
  private String senha;
  private List<RequestEnderecoDTO> enderecos;
  private List<RequestTelefoneDTO> telefones;
}
