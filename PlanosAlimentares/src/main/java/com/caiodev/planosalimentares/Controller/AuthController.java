package com.caiodev.planosalimentares.Controller;

import com.caiodev.planosalimentares.DTO.Request.RegisterDTORequest;
import com.caiodev.planosalimentares.DTO.Request.UsuarioDTORequest;
import com.caiodev.planosalimentares.DTO.Response.RegisterDTOResponse;
import com.caiodev.planosalimentares.DTO.Response.UsuarioDTOResponse;
import com.caiodev.planosalimentares.Model.Entity.Usuario;
import com.caiodev.planosalimentares.Model.Repository.UsuarioRepository;
import com.caiodev.planosalimentares.Security.TokenConfig;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenConfig tokenConfig;

    public AuthController(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenConfig tokenConfig) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenConfig = tokenConfig;
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioDTOResponse> login(@Valid @RequestBody UsuarioDTORequest usuarioDTORequest){
        UsernamePasswordAuthenticationToken userPass = new UsernamePasswordAuthenticationToken(usuarioDTORequest.usuario() , usuarioDTORequest.senha());
        Authentication authentication = authenticationManager.authenticate(userPass);
        Usuario usuario = (Usuario) authentication.getPrincipal();
        String token = tokenConfig.generateToken(usuario);
        return ResponseEntity.ok().body(new UsuarioDTOResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterDTOResponse> register(@Valid @RequestBody RegisterDTORequest registerDTORequest){
        Usuario user= new Usuario();
        user.setUsuario(registerDTORequest.usuario());
        user.setSenha(passwordEncoder.encode(registerDTORequest.senha()));

        usuarioRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
