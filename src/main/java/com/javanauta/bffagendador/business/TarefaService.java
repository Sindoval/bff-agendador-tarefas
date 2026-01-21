package com.javanauta.bffagendador.business;

import com.javanauta.bffagendador.business.dto.in.RequestTarefaDTO;
import com.javanauta.bffagendador.business.dto.out.ResponseTarefaDTO;
import com.javanauta.bffagendador.infrastructure.client.TarefasClient;
import com.javanauta.bffagendador.infrastructure.enums.StatusNotificacaoEnum;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TarefaService {

  private final TarefasClient tarefasClient;

  public ResponseTarefaDTO gravarTarefa(String token, RequestTarefaDTO tarefaDTO) {
    return tarefasClient.gravarTarefa(tarefaDTO, token);
  }

  public List<ResponseTarefaDTO> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInical, LocalDateTime dataFinal, String token) {
    return tarefasClient.buscarListaDeTarefasPorPeriodo(dataInical, dataFinal, token);
  }

  public List<ResponseTarefaDTO> buscaTarefaPorEmail(String token) {
    return tarefasClient.buscarTarefasPoremail(token);
  }

  public void deletaTarefaPorId(String id, String token) {
    tarefasClient.deletaTarefaPorId(id, token);
  }

  public ResponseTarefaDTO alteraStatus(StatusNotificacaoEnum status, String id, String token) {
    return tarefasClient.alteraStatusNotificacao(status, id, token);
  }

  public ResponseTarefaDTO updateTarefas(RequestTarefaDTO dto, String id, String token) {
    return tarefasClient.updateTarefa(dto, id, token);
  }
}
