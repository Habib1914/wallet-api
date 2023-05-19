package br.com.limaisaias.walletapi.service;

import br.com.limaisaias.walletapi.entity.Usuario;

import java.util.Optional;

public interface UsuarioService {

    Usuario save(Usuario usuario);

    Optional<Usuario> findByEmail(String email);

}
