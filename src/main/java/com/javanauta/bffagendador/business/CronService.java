package com.javanauta.bffagendador.business;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.javanauta.bffagendador.business.dto.in.RequestUsuarioDTO;
import com.javanauta.bffagendador.business.dto.out.ResponseTarefaDTO;
import com.javanauta.bffagendador.infrastructure.enums.StatusNotificacaoEnum;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {
  private final TarefaService tarefaService;
  private final EmailService emailService;
  private final UsuarioService usuarioService;
  private final ObjectMapper objectMapper;

  @Value("${admin.email}")
  private String email;

  @Value("${admin.senha}")
  private String senha;

  @Scheduled(cron = "${cron.horario}")
  public void buscaTarefasProximaHora() throws JsonProcessingException {
    String token = login(toRequestDTO());
    log.info("Iniciada a busca por tarefas");
    LocalDateTime nextHour = LocalDateTime.now().plusHours(1);
    LocalDateTime nextHourPlusFive = LocalDateTime.now().plusHours(1).plusMinutes(5);

    List<ResponseTarefaDTO> listaTarefas = tarefaService.
        buscaTarefasAgendadasPorPeriodo(nextHour, nextHourPlusFive, token);
    log.info("Tarefas encontradas: \n{}",  objectMapper
        .writerWithDefaultPrettyPrinter()
        .writeValueAsString(listaTarefas));
    listaTarefas.forEach((tarefa) -> {
      emailService.enviarEmail(tarefa);
      log.info("Email enviado para: " + tarefa.getEmailUsuario());
      tarefaService.alteraStatus(StatusNotificacaoEnum.NOTIFICADO, tarefa.getId(), token);
    });

    log.info("Finalizada a busca e notificaćão de tarefas");
  }

  public String login(RequestUsuarioDTO dto) {
    return usuarioService.loginUsuario(dto);
  }

  public RequestUsuarioDTO toRequestDTO() {
    return RequestUsuarioDTO.builder()
        .email(email)
        .senha(senha)
        .build();
  }
}
