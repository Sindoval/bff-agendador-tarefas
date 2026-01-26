package com.javanauta.bffagendador.controller;


import com.javanauta.bffagendador.business.UsuarioService;
import com.javanauta.bffagendador.business.dto.in.RequestEnderecoDTO;
import com.javanauta.bffagendador.business.dto.in.RequestTelefoneDTO;
import com.javanauta.bffagendador.business.dto.in.RequestUsuarioDTO;
import com.javanauta.bffagendador.business.dto.out.ResponseEnderecoDTO;
import com.javanauta.bffagendador.business.dto.out.ResponseTelefoneDTO;
import com.javanauta.bffagendador.business.dto.out.ResponseUsuarioDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "usuario", description = "Cadastro e login de usuários")
public class UsuarioController {
  private final UsuarioService usuarioService;

  @PostMapping
  @Operation(summary = "Salvar Usuários", description = "Cria um novo usuário")
  @ApiResponse(responseCode = "200", description = "Usuário salvo com sucesso")
  @ApiResponse(responseCode = "400", description = "Usuário já cadastrado")
  @ApiResponse(responseCode = "500", description = "Erro de servidor")
  public ResponseEntity<ResponseUsuarioDTO> salvarUsuario(@RequestBody RequestUsuarioDTO usuarioDTO) {
    return ResponseEntity.ok(usuarioService.salvarUsuario(usuarioDTO));
  }

  @PostMapping("/login")
  @Operation(summary = "Login Usuários", description = "Login do usuario")
  @ApiResponse(responseCode = "200", description = "Usuário logado com sucesso")
  @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
  @ApiResponse(responseCode = "500", description = "Erro de servidor")
  public String login(@RequestBody RequestUsuarioDTO usuarioDto) {
   return usuarioService.loginUsuario(usuarioDto);
  }

  @GetMapping
  @Operation(summary = "Buscar dados de Usuários por Email",
      description = "Buscar dados do usuário")
  @ApiResponse(responseCode = "200", description = "Usuário encontrado")
  @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
  @ApiResponse(responseCode = "500", description = "Erro de servidor")
  @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
  public ResponseEntity<ResponseUsuarioDTO> buscarUsuarioPorEmail(@RequestParam("email") String email,
  @RequestHeader(name = "Authorization", required = false) String token) {
    return ResponseEntity.ok(
        usuarioService.buscarUsuarioPorEmail(email, token)
    );
  }

  @DeleteMapping("/{email}")
  @Operation(summary = "Deletar Usuários por Id", description = "Delete usuário")
  @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso")
  @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
  @ApiResponse(responseCode = "500", description = "Erro de servidor")
  @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
  public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable String email,
      @RequestHeader(name = "Authorization", required = false) String token) {
    usuarioService.deletaUsuarioPorEmail(email, token);
    return ResponseEntity.ok().build();
  }

  @PutMapping
  @Operation(summary = "Atualizar dados de Usuários", description = "Atualiza dados do usuário")
  @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso")
  @ApiResponse(responseCode = "400", description = "Usuário não encontrado")
  @ApiResponse(responseCode = "500", description = "Erro de servidor")
  @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
  public ResponseEntity<ResponseUsuarioDTO> atualizaDadosUsuario(@RequestBody RequestUsuarioDTO usuarioDTO,
      @RequestHeader(name = "Authorization", required = false) String token) {
    return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(token, usuarioDTO));
  }

  @PutMapping("/endereco")
  @Operation(summary = "Atualizar Endereco de Usuários", description = "Atualiza endereco do usuário")
  @ApiResponse(responseCode = "200", description = "Endereco atualizado com sucesso")
  @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
  @ApiResponse(responseCode = "500", description = "Erro de servidor")
  @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
  public ResponseEntity<ResponseEnderecoDTO> atualizaEndereco(@RequestParam("id") Long id,
      @RequestBody RequestEnderecoDTO enderecoDTO,
      @RequestHeader(name = "Authorization", required = false) String token) {
    return ResponseEntity.ok(usuarioService.atualizaEndereco(id, enderecoDTO, token));
  }

  @PutMapping("/telefone")
  @Operation(summary = "Atualizar Telefone de Usuários", description = "Atualiza telefone do usuário")
  @ApiResponse(responseCode = "200", description = "Telefone atualizado com sucesso")
  @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
  @ApiResponse(responseCode = "500", description = "Erro de servidor")
  @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
  public ResponseEntity<ResponseTelefoneDTO> atualizaTelefone(@RequestParam("id") Long id,
      @RequestBody RequestTelefoneDTO telefoneDTO,
      @RequestHeader(name = "Authorization", required = false) String token) {
    return ResponseEntity.ok(usuarioService.atualizaTelefone(id, telefoneDTO, token));
  }

  @PostMapping("/endereco")
  @Operation(summary = "Salva Endereco de Usuários", description = "Salva endereco do usuário")
  @ApiResponse(responseCode = "200", description = "Endereco salvo com sucesso")
  @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
  @ApiResponse(responseCode = "500", description = "Erro de servidor")
  @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
  public ResponseEntity<ResponseEnderecoDTO> salvarEndereco(@RequestBody RequestEnderecoDTO enderecoDTO,
      @RequestHeader(name = "Authorization", required = false) String token) {
    return ResponseEntity.ok(usuarioService.cadastroEndereco(token, enderecoDTO));
  }

  @PostMapping("/telefone")
  @Operation(summary = "Salva Telefone de Usuários", description = "Salva telefone do usuário")
  @ApiResponse(responseCode = "200", description = "Telefone salvo com sucesso")
  @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
  @ApiResponse(responseCode = "500", description = "Erro de servidor")
  @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
  public ResponseEntity<ResponseTelefoneDTO> salvarTelefone(@RequestBody RequestTelefoneDTO telefoneDTO,
      @RequestHeader(name = "Authorization", required = false) String token) {
    return ResponseEntity.ok(usuarioService.cadastroTelefone(token, telefoneDTO));
  }
}
