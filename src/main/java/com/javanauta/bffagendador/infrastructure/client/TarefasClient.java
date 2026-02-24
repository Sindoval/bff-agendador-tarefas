package com.javanauta.bffagendador.infrastructure.client;


import com.javanauta.bffagendador.business.dto.in.RequestTarefaDTO;
import com.javanauta.bffagendador.business.dto.out.ResponseTarefaDTO;
import com.javanauta.bffagendador.infrastructure.enums.StatusNotificacaoEnum;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "agendador-tarefas", url = "${tarefas.url}")
public interface TarefasClient {

  @PostMapping
  ResponseTarefaDTO gravarTarefa(@RequestBody RequestTarefaDTO tarefaDTO,
      @RequestHeader("Authorization") String token);

  @GetMapping("/eventos")
  List<ResponseTarefaDTO> buscarListaDeTarefasPorPeriodo(
      @RequestParam @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime dataInicial,
      @RequestParam @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime dataFinal,
      @RequestHeader("Authorization") String token
  );

  @GetMapping
  List<ResponseTarefaDTO> buscarTarefasPoremail (@RequestHeader("Authorization") String token);

  @DeleteMapping
  void deletaTarefaPorId(@RequestParam("id") String id,
      @RequestHeader("Authorization") String token);

  @PatchMapping
  ResponseTarefaDTO alteraStatusNotificacao(
      @RequestParam("status") StatusNotificacaoEnum status,
      @RequestParam("id") String id,
      @RequestHeader("Authorization") String token);

  @PatchMapping("/email")
  void atualizaEmailTarefas(
      @RequestBody String newEmail,
      @RequestParam String oldEmail,
      @RequestHeader("Authorization") String token
  );

  @PutMapping
  ResponseTarefaDTO updateTarefa(
      @RequestBody RequestTarefaDTO tarefaDTO,
      @RequestParam("id") String id,
      @RequestHeader("Authorization") String token);
}
