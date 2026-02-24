package com.javanauta.bffagendador.business.converter;

import com.javanauta.bffagendador.business.dto.in.RequestComunicacao;
import com.javanauta.bffagendador.business.dto.out.ResponseTarefaDTO;
import com.javanauta.bffagendador.infrastructure.enums.ModoEnvioEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ComunicacaoConverter {

  public RequestComunicacao toComunicacaoDTO(ResponseTarefaDTO tarefaDTO) {
    return RequestComunicacao.builder()
        .nomeTarefa(tarefaDTO.getNomeTarefa())
        .emailDestinatario(tarefaDTO.getEmailUsuario())
        .modoEnvio(ModoEnvioEnum.EMAIL)
        .dataEvento(tarefaDTO.getDataEvento())
        .descricao(tarefaDTO.getDescricao())
        .build();
  }
}
