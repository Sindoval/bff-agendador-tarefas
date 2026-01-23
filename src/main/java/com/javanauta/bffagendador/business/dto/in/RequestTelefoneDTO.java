package com.javanauta.bffagendador.business.dto.in;

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
public class RequestTelefoneDTO {
  private String numero;
  private String ddd;
}
