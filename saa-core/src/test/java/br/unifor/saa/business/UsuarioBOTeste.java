package br.unifor.saa.business;

import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.unifor.saa.dao.UsuarioDAO;
import br.unifor.saa.entity.Papel;
import br.unifor.saa.entity.Usuario;
import br.unifor.saa.entity.enums.TipoPapel;
import br.unifor.saa.exceptions.BOException;
import br.unifor.saa.util.Encripta;

public class UsuarioBOTeste {

	private static final String SENHA = "123";
	private static final String EMAIL = "marcelorosa2@hotmail.com";
	private UsuarioDAO usuarioDAO;
	private UsuarioBO usuarioBO;
	private Usuario usuario;

	@Before
	public void setUp() throws Exception {
		usuarioBO = new UsuarioBO();
		usuarioDAO = createNiceMock(UsuarioDAO.class);
		usuario = new Usuario();
		usuario.setEmail(EMAIL);
		usuario.setSenha(Encripta.encripta(SENHA));
	}

	@Test(expected = BOException.class)
	public void testLogarException() throws BOException {
		usuarioBO.loggar(null, null);
	}

	@Test
	public void testLogar() throws BOException {

		expect(usuarioDAO.buscarPorEmailSenha(EMAIL, Encripta.encripta(SENHA)))
				.andReturn(usuario);

		replay(usuarioDAO);
		usuarioBO.setUsuarioDAO(usuarioDAO);
		Usuario usuario2 = usuarioBO.loggar(EMAIL, SENHA);

		assertEquals(usuario, usuario2);
	}

	@Test
	public void testSalvar() throws BOException {
		expect(usuarioDAO.buscarPorEmail(EMAIL)).andReturn(null);

		replay(usuarioDAO);
		usuarioBO.setUsuarioDAO(usuarioDAO);
		usuarioBO.salvar(usuario);
	}

	@Test(expected = BOException.class)
	public void testSalvarUsuarioExistente() throws BOException {
		expect(usuarioDAO.buscarPorEmail(EMAIL)).andReturn(usuario);

		replay(usuarioDAO);
		usuarioBO.setUsuarioDAO(usuarioDAO);
		usuarioBO.salvar(usuario);
	}

	@Test
	public void testeAtualizar() throws BOException {
		usuarioBO.setUsuarioDAO(usuarioDAO);
		usuarioBO.atualizar(usuario);

	}

	@Test
	public void testebuscaPapelPorTipo() throws BOException {
		Papel papel = new Papel();
		papel.setTipoPapel(TipoPapel.ALUNO);
		expect(usuarioDAO.buscarPapelPorTipoPapel(TipoPapel.ALUNO)).andReturn(papel);
		replay(usuarioDAO);
		usuarioBO.setUsuarioDAO(usuarioDAO);
		Papel papel2 = usuarioBO.buscaPapelPorTipo(TipoPapel.ALUNO);
		assertEquals(papel, papel2);

	}
}
