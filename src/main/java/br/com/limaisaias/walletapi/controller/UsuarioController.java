package br.com.limaisaias.walletapi.controller;

import br.com.limaisaias.walletapi.dto.UsuarioDTO;
import br.com.limaisaias.walletapi.entity.Usuario;
import br.com.limaisaias.walletapi.response.Response;
import br.com.limaisaias.walletapi.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Response<UsuarioDTO>> create(@Valid @RequestBody UsuarioDTO body, BindingResult result) {
        Response<UsuarioDTO> usuarioDTOResponse = new Response<UsuarioDTO>();
        if (result.hasErrors()) {
            result.getAllErrors().forEach(err -> usuarioDTOResponse.getErrors().add(err.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(usuarioDTOResponse);
        }
        Usuario usuario = usuarioService.save(convertDtoToEntity(body));
        usuarioDTOResponse.setData(convertEntityToDto(usuario));
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDTOResponse);
    }

    private Usuario convertDtoToEntity(UsuarioDTO body) {
        return Usuario.builder().id(body.getId()).name(body.getName()).email(body.getEmail()).password(body.getPassword()).build();
    }

    private UsuarioDTO convertEntityToDto(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setName(usuario.getName());
        dto.setEmail(usuario.getEmail());
        dto.setPassword(usuario.getPassword());
        return dto;
    }
}
