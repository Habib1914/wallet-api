package br.com.limaisaias.walletapi.service.impl;

import br.com.limaisaias.walletapi.entity.Usuario;
import br.com.limaisaias.walletapi.repository.UsuarioRepository;
import br.com.limaisaias.walletapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository repository;

    @Override
    public Usuario save(Usuario usuario) {
        return repository.save(usuario);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return repository.findByEmailEquals(email);
    }
}
