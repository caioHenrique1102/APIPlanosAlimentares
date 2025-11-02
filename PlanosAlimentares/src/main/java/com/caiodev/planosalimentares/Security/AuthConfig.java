package com.caiodev.planosalimentares.Security;

import com.caiodev.planosalimentares.Exception.UserNotFoundExeption;
import com.caiodev.planosalimentares.Model.Repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthConfig implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    public AuthConfig(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByUsuario(username).orElseThrow(() -> new UserNotFoundExeption("User Not found"));
    }
}
