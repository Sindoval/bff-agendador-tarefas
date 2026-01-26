package com.javanauta.bffagendador.business;

import com.javanauta.bffagendador.business.dto.in.RequestEnderecoDTO;
import com.javanauta.bffagendador.business.dto.in.RequestTelefoneDTO;
import com.javanauta.bffagendador.business.dto.in.RequestUsuarioDTO;
import com.javanauta.bffagendador.business.dto.out.ResponseEnderecoDTO;
import com.javanauta.bffagendador.business.dto.out.ResponseTelefoneDTO;
import com.javanauta.bffagendador.business.dto.out.ResponseUsuarioDTO;
import com.javanauta.bffagendador.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {
  private final UsuarioClient client;

  public ResponseUsuarioDTO salvarUsuario(RequestUsuarioDTO usuarioDTO) {
    return client.salvarUsuario(usuarioDTO);
  }

  public String loginUsuario(RequestUsuarioDTO usuarioDTO) {
    return client.login(usuarioDTO);
  }

  public ResponseUsuarioDTO buscarUsuarioPorEmail(String email, String token) {
    return client.buscaUsuarioPorEmail(email, token);
  }

  public void deletaUsuarioPorEmail(String email, String token) {
    client.deletaUsuarioPorEmail(email, token);
  }

  public ResponseUsuarioDTO atualizaDadosUsuario(String token, RequestUsuarioDTO usuarioDTO) {
    return client.atualizaDadosUsuario(usuarioDTO, token);
  }

  public ResponseEnderecoDTO atualizaEndereco(Long idEndereco, RequestEnderecoDTO enderecoDTO, String token) {
    return client.atualizaEndereco(idEndereco, enderecoDTO, token);
  }

  public ResponseTelefoneDTO atualizaTelefone(Long idTelefone, RequestTelefoneDTO telefoneDTO, String token) {
    return client.atualizaTelefone(idTelefone, telefoneDTO, token);
  }

  public ResponseEnderecoDTO cadastroEndereco(String token, RequestEnderecoDTO enderecoDTO) {
    return client.salvarEndereco(enderecoDTO, token);
  }

  public ResponseTelefoneDTO cadastroTelefone(String token, RequestTelefoneDTO telefoneDTO) {
    return client.salvarTelefone(telefoneDTO, token);
  }
}
