package com.javanauta.bffagendador.business;

import com.javanauta.bffagendador.business.dto.in.RequestComunicacao;
import com.javanauta.bffagendador.infrastructure.client.ComunicacaoClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ComunicacaoService {
  private final ComunicacaoClient comunicacaoClient;

  public void enviarEmail(RequestComunicacao comunicacaoDTO) {
    comunicacaoClient.agendar(comunicacaoDTO);
  }

}
