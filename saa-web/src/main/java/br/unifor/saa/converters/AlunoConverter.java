package br.unifor.saa.converters;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;

import org.springframework.stereotype.Component;

import br.unifor.saa.business.UsuarioBO;
import br.unifor.saa.entity.Usuario;
import br.unifor.saa.util.MessagesResources;

/**
 * @author adrianopatrick@gmail.com
 * @since 27 de abr de 2016
 */
@Component(value = "alunoConverter")
@ManagedBean(name = "alunoConverter")
public class AlunoConverter implements Converter {

	@Inject
	private UsuarioBO usuarioBO;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.
	 * FacesContext, javax.faces.component.UIComponent, java.lang.String)
	 */
	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		if (value != null && value.trim().length() > 0) {
			try {
				return usuarioBO.buscarPorId(Long.parseLong(value));
			} catch (NumberFormatException e) {
				throw new ConverterException(new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						MessagesResources.getMessages("erro_conversao"),
						MessagesResources.getMessages("erro_cast_usuario")));
			}
		} else {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.
	 * FacesContext, javax.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if (object != null) {
			return String.valueOf(((Usuario) object).getId());
		} else {
			return null;
		}
	}

}
