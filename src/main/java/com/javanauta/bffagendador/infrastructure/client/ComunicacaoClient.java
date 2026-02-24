package com.javanauta.bffagendador.infrastructure.client;

import com.javanauta.bffagendador.business.dto.in.RequestComunicacao;
import com.javanauta.bffagendador.business.dto.out.ResponseComunicacaoDTO;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "comunicacao", url = "${comunicacao.url}")
public interface ComunicacaoClient {

  @PostMapping("/agendar")
  ResponseComunicacaoDTO agendar(@RequestBody RequestComunicacao comunicacaoDTO);

  @GetMapping()
  List<ResponseComunicacaoDTO> BuscarDadosPorEmail(@RequestParam String email);
}
