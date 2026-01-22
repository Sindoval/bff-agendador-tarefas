package com.javanauta.bffagendador.business;

import com.javanauta.bffagendador.business.dto.out.ResponseTarefaDTO;
import com.javanauta.bffagendador.infrastructure.client.NotificacaoClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
  private final NotificacaoClient notificacaoClient;

  public void enviarEmail(ResponseTarefaDTO tarefaDTO) {
    notificacaoClient.enviarEmail(tarefaDTO);
  }

}
