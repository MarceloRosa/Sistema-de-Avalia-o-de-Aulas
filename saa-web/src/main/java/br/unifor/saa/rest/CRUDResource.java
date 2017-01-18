package br.unifor.saa.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.spi.container.ContainerRequest;

//import br.unifor.saa.business.CRUDBusiness;
import br.unifor.saa.exceptions.BOException;

/*@SuppressWarnings("rawtypes")*/
@SuppressWarnings("unchecked")
public abstract class CRUDResource<ID, T extends Serializable, E> implements
		Serializable {
/*
	*//**
	 * 
	 *//*
	private static final long serialVersionUID = -351110298515349296L;

	public CRUDResource() {
		super();
	}

	public Response findAll() {
		List<E> list = getService().buscarTodos();
		GenericEntity generic = new GenericEntity<List<E>>(list) {
		};
		EntityListType entityList = getCollectionType(list);
		MediaType mediaType = getRequest().getMediaType();
		if (mediaType.toString().equals(APPLICATION_JSON.toString())) {
			return Response.status(201).type(APPLICATION_JSON).entity(generic)
					.build();
		} else {
			return Response.status(200).entity(entityList)
					.build();
		}

	}

	public Response findById(ID id) {
		T e = (T) getService().buscarPorId(id);
		MediaType mediaType = getRequest().getMediaType();
		if (mediaType.toString().equals(APPLICATION_JSON.toString())) {
			return Response.status(201).type(APPLICATION_JSON).entity(e)
					.build();
		} else {
			return Response.status(200).type(APPLICATION_XML).entity(e).build();
		}
	}

	public Response inserir(T entidade) throws BOException {
		getService().salvar(getEntidade(entidade));
		MediaType mediaType = getRequest().getMediaType();
		if (mediaType.toString().equals(APPLICATION_JSON.toString())) {
			return Response.status(201).type(APPLICATION_JSON).entity(entidade)
					.build();
		} else {
			return Response.status(200).type(APPLICATION_XML).entity(entidade).build();
		}
	}

	protected abstract E getEntidade(T entidade);

	public Response atualizar(T entidade, ID id) throws BOException {
		((EntityType) entidade).setId(new BigInteger(id.toString()));
		T atual = (T) getService().atualizar(getEntidade(entidade));
		MediaType mediaType = getRequest().getMediaType();
		if (mediaType.toString().equals(APPLICATION_JSON.toString())) {
			return Response.status(201).type(APPLICATION_JSON).entity(atual)
					.build();
		} else {
			return Response.status(200).type(APPLICATION_XML).entity(atual)
					.build();
		}
	}

	public Response remover(ID id) throws BOException {
		E entidade = getService().remover(id);
		MediaType mediaType = getRequest().getMediaType();
		//MediaType default
		String strMedia = APPLICATION_XML.toString();
		if (mediaType != null) {
			strMedia = mediaType.toString();
		} else {
			List<MediaType> mediaTypes = getRequest().getAcceptableMediaTypes();
			if (mediaTypes != null && !mediaTypes.isEmpty()) {
				strMedia = mediaTypes.get(0).toString();
			}
		}
		if (strMedia.equals(APPLICATION_JSON.toString())) {
			return Response.status(201).type(APPLICATION_JSON).entity(entidade)
					.build();
		} else {
			return Response.status(200).type(APPLICATION_XML).entity(entidade)
					.build();
		}
	}

	protected abstract CRUDBusiness<ID, E> getService();
	protected abstract ContainerRequest getRequest();
	protected abstract EntityListType getCollectionType(List<E> list);
*/
}