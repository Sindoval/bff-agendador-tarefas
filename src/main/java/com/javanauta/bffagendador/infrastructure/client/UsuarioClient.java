package com.javanauta.bffagendador.infrastructure.client;

import com.javanauta.bffagendador.business.dto.in.RequestEnderecoDTO;
import com.javanauta.bffagendador.business.dto.in.RequestTelefoneDTO;
import com.javanauta.bffagendador.business.dto.in.RequestUsuarioDTO;
import com.javanauta.bffagendador.business.dto.out.ResponseEnderecoDTO;
import com.javanauta.bffagendador.business.dto.out.ResponseTelefoneDTO;
import com.javanauta.bffagendador.business.dto.out.ResponseUsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

  @GetMapping
  ResponseUsuarioDTO buscaUsuarioPorEmail(@RequestParam("email") String email,
      @RequestHeader("Authorization") String token);

  @PostMapping
  ResponseUsuarioDTO salvarUsuario(@RequestBody RequestUsuarioDTO usuarioDTO);

  @PostMapping("/login")
  String login(@RequestBody RequestUsuarioDTO usuarioDto);

  @DeleteMapping("/{email}")
  void deletaUsuarioPorEmail(@PathVariable String email,
      @RequestHeader("Authorization") String token);

  @PutMapping
  ResponseUsuarioDTO atualizaDadosUsuario(@RequestBody RequestUsuarioDTO usuarioDTO,
      @RequestHeader("Authorization") String token);

  @PutMapping("/endereco")
  ResponseEnderecoDTO atualizaEndereco(@RequestParam("id") Long id,
      @RequestBody RequestEnderecoDTO enderecoDTO,
      @RequestHeader("Authorization") String token);

  @PutMapping("/telefone")
  ResponseTelefoneDTO atualizaTelefone(@RequestParam("id") Long id,
      @RequestBody RequestTelefoneDTO telefoneDTO,
      @RequestHeader("Authorization") String token);

  @PostMapping("/endereco")
  ResponseEnderecoDTO salvarEndereco(@RequestBody RequestEnderecoDTO enderecoDTO,
      @RequestHeader("Authorization") String token);

  @PostMapping("/telefone")
  ResponseTelefoneDTO salvarTelefone(@RequestBody RequestTelefoneDTO telefoneDTO,
      @RequestHeader("Authorization") String token);
}
