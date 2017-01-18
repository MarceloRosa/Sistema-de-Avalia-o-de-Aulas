/**
 * 
 */
package br.unifor.saa.aspectj;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.unifor.saa.entity.Papel;
import br.unifor.saa.entity.Permissao;
import br.unifor.saa.entity.Usuario;
import br.unifor.saa.entity.enums.TipoPapel;
import br.unifor.saa.to.SegurancaTO;

/**
 * @author patrick.cunha
 * @since 07/05/2015
 */
@Aspect
@Component
@Scope("session")
public class SecurityConfig {

	@Inject
	private SegurancaTO segurancaTO;
	private static final Logger LOG = Logger.getLogger(SecurityConfig.class);
	private static final String SECURITY_TAG = "[SEGURANCA] ";

	@Before("@within(RolesAllowed) || @within(PermitAll) || @annotation(RolesAllowed) || @annotation(PermitAll)")
	public void checkSecurity(JoinPoint joinPoint) {

		Method metodo = ((MethodSignature) joinPoint.getSignature())
				.getMethod();

		// Verifica se o acesso ao metodo esta liberado.
		if (metodo.getAnnotation(PermitAll.class) != null) {
			return;
		}

		// Contexto de segurança do usuário logado
		Usuario usuario = this.segurancaTO.getUsuario();
		if (usuario == null || !this.segurancaTO.isAutenticado()) {
			this.dispararAcessoNegado();
		}

		LOG.debug(SECURITY_TAG + "Usuário: " + usuario);
		LOG.debug(SECURITY_TAG + "Funcionalidade acessada: "
				+ joinPoint.getSignature());

		// Se o usuario por Administrador, libera todos os acessos
		for (Papel papel : usuario.getPapeis()) {
			if (TipoPapel.ADMINISTRADOR.equals(papel.getTipoPapel())) {
				return;
			}
		}

		RolesAllowed permissoesDoMetodo = metodo
				.getAnnotation(RolesAllowed.class) != null
						? metodo.getAnnotation(RolesAllowed.class)
						: metodo.getDeclaringClass()
								.getAnnotation(RolesAllowed.class);

		if (permissoesDoMetodo != null) {
			final List<String> permissoesRequeridas = new ArrayList<>(
					Arrays.asList(permissoesDoMetodo.value()));

			final List<String> permissoesDoUsuario = this
					.retornaPermissoesDoUsuario(usuario);

			// interseccao entre as listas
			permissoesRequeridas.retainAll(permissoesDoUsuario);
			if (permissoesRequeridas.size() > 0) {
				return;
			}

		} else {
			this.dispararAcessoNegado();
		}

	}

	/**
	 * @param usuario
	 * @return Lista de permissoes do usuario
	 */
	private List<String> retornaPermissoesDoUsuario(Usuario usuario) {
		List<String> permissoesDoUsuario = new ArrayList<String>();
		for (Papel papel : usuario.getPapeis()) {
			for (Permissao permissao : papel.getPermissoes()) {
				permissoesDoUsuario.add(permissao.getPermissao());
			}
		}
		return permissoesDoUsuario;
	}

	/**
	 * 
	 */
	private void dispararAcessoNegado() {
		SecurityException se = new SecurityException(SECURITY_TAG
				+ "Capturada uma tentativa de acesso indevido. Tentativa abortada.");
		LOG.error(
				SECURITY_TAG
						+ "Capturada uma tentativa de acesso indevido. Tentativa abortada.",
				se);

		throw se;
	}
	
	public void setSegurancaTO(SegurancaTO segurancaTO) {
		this.segurancaTO = segurancaTO;
	}

}
