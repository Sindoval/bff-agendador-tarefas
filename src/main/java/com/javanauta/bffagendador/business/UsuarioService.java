package com.javanauta.bffagendador.business;

import com.javanauta.bffagendador.business.dto.ViaCepDTOResponse;
import com.javanauta.bffagendador.business.dto.in.RequestEnderecoDTO;
import com.javanauta.bffagendador.business.dto.in.RequestTelefoneDTO;
import com.javanauta.bffagendador.business.dto.in.RequestUsuarioDTO;
import com.javanauta.bffagendador.business.dto.out.ResponseEnderecoDTO;
import com.javanauta.bffagendador.business.dto.out.ResponseTelefoneDTO;
import com.javanauta.bffagendador.business.dto.out.ResponseUsuarioDTO;
import com.javanauta.bffagendador.infrastructure.client.TarefasClient;
import com.javanauta.bffagendador.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

  private final UsuarioClient usuarioClient;
  private final TarefasClient tarefasClient;

  public ResponseUsuarioDTO salvarUsuario(RequestUsuarioDTO usuarioDTO) {
    return usuarioClient.salvarUsuario(usuarioDTO);
  }

  public String loginUsuario(RequestUsuarioDTO usuarioDTO) {
    return usuarioClient.login(usuarioDTO);
  }

  public ResponseUsuarioDTO buscarUsuarioPorEmail(String email, String token) {
    return usuarioClient.buscaUsuarioPorEmail(email, token);
  }

  public void deletaUsuarioPorEmail(String email, String token) {
    usuarioClient.deletaUsuarioPorEmail(email, token);
  }

  public ResponseUsuarioDTO atualizaDadosUsuario(String token, RequestUsuarioDTO usuarioDTO) {
    ResponseUsuarioDTO usuarioAtualizado = usuarioClient.atualizaDadosUsuario(usuarioDTO, token);

    if (usuarioAtualizado.getEmailAnterior() != null &&
      !usuarioAtualizado.getEmail().equals(usuarioAtualizado.getEmailAnterior())
    ) {
      String tokenParaUso = (usuarioAtualizado.getNovoToken() != null) ?
          usuarioAtualizado.getNovoToken() : token;
      tarefasClient.atualizaEmailTarefas(usuarioAtualizado.getEmail(), usuarioAtualizado.getEmailAnterior(), tokenParaUso);
    }
    return usuarioAtualizado;
  }

  public ResponseEnderecoDTO atualizaEndereco(Long idEndereco, RequestEnderecoDTO enderecoDTO, String token) {
    return usuarioClient.atualizaEndereco(idEndereco, enderecoDTO, token);
  }

  public ResponseTelefoneDTO atualizaTelefone(Long idTelefone, RequestTelefoneDTO telefoneDTO, String token) {
    return usuarioClient.atualizaTelefone(idTelefone, telefoneDTO, token);
  }

  public ResponseEnderecoDTO cadastroEndereco(String token, RequestEnderecoDTO enderecoDTO) {
    return usuarioClient.salvarEndereco(enderecoDTO, token);
  }

  public ResponseTelefoneDTO cadastroTelefone(String token, RequestTelefoneDTO telefoneDTO) {
    return usuarioClient.salvarTelefone(telefoneDTO, token);
  }

  public ViaCepDTOResponse buscarEnderecoCep(String cep) {
    return usuarioClient.buscarDadosCep(cep);
  }
}
