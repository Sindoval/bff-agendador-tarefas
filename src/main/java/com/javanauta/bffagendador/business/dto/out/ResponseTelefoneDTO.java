package com.javanauta.bffagendador.business.dto.out;

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
public class ResponseTelefoneDTO {
  private Long id;
  private String numero;
  private String ddd;
}
