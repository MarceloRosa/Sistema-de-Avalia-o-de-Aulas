package br.unifor.saa.rest;

import br.unifor.saa.rest.model.Usuario;
/**
 * @author marcelorosa2@hotmail.com
 * @since  05 de janeiro de 2017
 */
public class UsuarioJsonBuilder extends JsonBuilder<Usuario> {

	private static UsuarioJsonBuilder builder;

	public static UsuarioJsonBuilder getInstance() {
		if (builder == null) {
			builder = new UsuarioJsonBuilder();
		}
		return builder;
	}

	@Override
	protected Usuario createEntidade() {
		return new Usuario();
	}
}
