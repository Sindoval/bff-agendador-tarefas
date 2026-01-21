package com.javanauta.bffagendador.business.dto.out;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.javanauta.bffagendador.infrastructure.enums.StatusNotificacaoEnum;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseTarefaDTO {
  private String id;
  private String nomeTarefa;
  private String descricao;
  @JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
  private LocalDateTime dataCriacao;
  @JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
  private LocalDateTime dataEvento;
  private String emailUsuario;
  @JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
  private LocalDateTime dataAlteracao;
  private StatusNotificacaoEnum status;
}
