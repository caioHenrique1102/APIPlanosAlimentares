package com.caiodev.planosalimentares.Model.Repository;
import com.caiodev.planosalimentares.Model.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    Optional<UserDetails> findByUsuario(String usuario);

    void deleteByUsuario (String usuario);
}
