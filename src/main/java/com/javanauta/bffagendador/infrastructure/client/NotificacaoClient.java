package com.javanauta.bffagendador.infrastructure.client;

import com.javanauta.bffagendador.business.dto.out.ResponseTarefaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notificacao", url = "${notificacao.url}")
public interface NotificacaoClient {

  @PostMapping
  void enviarEmail(@RequestBody ResponseTarefaDTO tarefaDTO);
}
