package com.alejandro.tareadwesalejandro.servicios;

import com.alejandro.tareadwesalejandro.modelo.Credenciales;
import com.alejandro.tareadwesalejandro.repositorios.CredencialesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class ServiciosDetallesUsuarios implements UserDetailsService {

    @Autowired
    private CredencialesRepository credencialesRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Credenciales cred = credencialesRepository.findByUsuario(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        String rol = cred.getPersona().getRol();
        return new User(
                cred.getUsuario(),
                cred.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + rol))
        );
    }

}
