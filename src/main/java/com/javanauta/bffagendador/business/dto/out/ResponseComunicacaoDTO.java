package com.javanauta.bffagendador.business.dto.out;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javanauta.bffagendador.infrastructure.enums.ModoEnvioEnum;
import com.javanauta.bffagendador.infrastructure.enums.StatusNotificacaoEnum;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ResponseComunicacaoDTO {
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
  private LocalDateTime dataEnvio;
  private LocalDateTime dataEvento;
  private String emailDestinatario;
  private String nomeTarefa;
  private String descricao;
  private ModoEnvioEnum modoDeEnvio;
  private StatusNotificacaoEnum statusEnvio;

}
