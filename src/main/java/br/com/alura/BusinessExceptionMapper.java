package br.com.alura;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.br.alura.exception.BusinessException;

import br.com.alura.Dto.MensagemErro;

@Provider
public class BusinessExceptionMapper implements ExceptionMapper<BusinessException> {

	@Override
	public Response toResponse(BusinessException e) {
		return Response
				.status(Response.Status.BAD_REQUEST)
				.entity(MensagemErro.build(e.getMsgs()))
				.build();
	}

}
