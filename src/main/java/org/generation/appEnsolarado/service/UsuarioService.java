package org.generation.appEnsolarado.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.generation.appEnsolarado.model.Usuario;
import org.generation.appEnsolarado.model.UsuarioLogin;
import org.generation.appEnsolarado.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	public Usuario CadastrarUsuario (Usuario usuario) {
		if (repository.findByUsuario(usuario.getUsuario()).isPresent()) {
			return null;
		}
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder ();
		String senhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);
		return repository.save(usuario);		
	}

	public Optional<UsuarioLogin> Logar(Optional<UsuarioLogin> user){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuario = repository.findByUsuario(user.get().getUsuario());
		
		if(usuario.isPresent()) {
			if (encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {
				
				String auth = user.get().getUsuario() + ":" + user.get().getSenha();
				byte[]encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);
				
				user.get().setToken(authHeader);
				user.get().setNomeCompleto(usuario.get().getNomeCompleto());
				user.get().setId(usuario.get().getId());
				user.get().setFotoPerfil(usuario.get().getFotoPerfil());
				user.get().setTipo(usuario.get().getTipo());
				
				return user;
			}	
		}
		return null;
	}
	
	public Optional<Usuario> atualizarSenha(Usuario usuario){

		/* Criprtografa a senha*/
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaEncoder = encoder.encode(usuario.getSenha());
	
		/* Atualiza a senha no objeto usuario, ou seja,
		substitui a senha digitada pela senha criptografada */
		usuario.setSenha(senhaEncoder);
		
		return Optional.of(repository.save(usuario));
	}
}
