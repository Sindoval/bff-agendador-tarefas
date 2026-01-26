package com.javanauta.bffagendador.controller;

import com.javanauta.bffagendador.business.TarefaService;
import com.javanauta.bffagendador.business.dto.in.RequestTarefaDTO;
import com.javanauta.bffagendador.business.dto.out.ResponseTarefaDTO;
import com.javanauta.bffagendador.infrastructure.enums.StatusNotificacaoEnum;
import com.javanauta.bffagendador.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tarefas")
@Tag(name = "Tarefas", description = "Cadastra tarefas de usuráios")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefaController {

  private final TarefaService tarefaService;

  @PostMapping
  @Operation(summary = "Salvar Tarefas de Usuários", description = "Cria uma nova tarefa")
  @ApiResponse(responseCode = "200", description = "Tarefa salvo com sucesso")
  @ApiResponse(responseCode = "500", description = "Erro de servidor")
  public ResponseEntity<ResponseTarefaDTO> gravarTarefa(@RequestBody RequestTarefaDTO tarefaDTO,
      @RequestHeader(name = "Authorization", required = false) String token) {
    return ResponseEntity.ok(tarefaService.gravarTarefa(token, tarefaDTO));
  }

  @GetMapping("/eventos")
  @Operation(summary = "Busca tarefas por Período", description = "Busca tarefas cadastradas por período")
  @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
  @ApiResponse(responseCode = "500", description = "Erro de servidor")
  @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
  public ResponseEntity<List<ResponseTarefaDTO>> buscarListaDeTarefasPorPeriodo(
      @RequestParam @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime dataInicial,
      @RequestParam @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime dataFinal,
      @RequestHeader(name = "Authorization", required = false) String token
  ) {
    return ResponseEntity.ok(tarefaService.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal, token));
  }

  @GetMapping
  @Operation(summary = "Busca lista tarefas por Email de Usuário", description = "Busca tarefas cadastradas por Usuário")
  @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
  @ApiResponse(responseCode = "500", description = "Erro de servidor")
  @ApiResponse(responseCode = "403", description = "Email não encontrado")
  @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
  public ResponseEntity<List<ResponseTarefaDTO>> buscarTarefasPoremail(
      @RequestHeader(name = "Authorization", required = false) String token) {
    return ResponseEntity.ok(
        tarefaService.buscaTarefaPorEmail(token)
    );
  }

  @DeleteMapping
  @Operation(summary = "Deleta tarefas cadastradas por Id", description = "Deleta tarefas cadastradas por Id")
  @ApiResponse(responseCode = "200", description = "Tarefa deletada")
  @ApiResponse(responseCode = "500", description = "Erro de servidor")
  @ApiResponse(responseCode = "403", description = "Tarefa id não encontrado")
  @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
  public ResponseEntity<Void> deletaTarefaPorId(
      @RequestParam("id") String id,
      @RequestHeader(name = "Authorization", required = false) String token) {
    tarefaService.deletaTarefaPorId(id, token);
    return ResponseEntity.ok().build();
  }

  @PatchMapping
  @Operation(summary = "Altera status de tarefas", description = "Altera status das tarefa cadastrada")
  @ApiResponse(responseCode = "200", description = "Status de tarefa alterado")
  @ApiResponse(responseCode = "500", description = "Erro de servidor")
  @ApiResponse(responseCode = "403", description = "Tarefa id não encontrado")
  @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
  public ResponseEntity<ResponseTarefaDTO> alteraStatusNotificacao(
      @RequestParam("status") StatusNotificacaoEnum status,
      @RequestParam("id") String id,
      @RequestHeader(name = "Authorization", required = false) String token) {
    return ResponseEntity.ok(tarefaService.alteraStatus(status, id, token));
  }

  @PutMapping
  @Operation(summary = "Altera dados da tarefas", description = "Altera dados da tarefa cadastrada")
  @ApiResponse(responseCode = "200", description = "Tarefas alterada")
  @ApiResponse(responseCode = "500", description = "Erro de servidor")
  @ApiResponse(responseCode = "403", description = "Tarefa id não encontrado")
  @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
  public ResponseEntity<ResponseTarefaDTO> updateTarefa(
      @RequestBody RequestTarefaDTO tarefaDTO,
      @RequestParam("id") String id,
      @RequestHeader(name = "Authorization", required = false) String token) {
    return ResponseEntity.ok(tarefaService.updateTarefas(tarefaDTO, id, token));
  }
}
