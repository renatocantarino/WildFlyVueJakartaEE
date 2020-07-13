package br.com.alura;

import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.alura.dto.MensagemErro;

@Provider
public class ConstraintValidatorMapper implements ExceptionMapper<ConstraintViolationException> {

	@Override
	public Response toResponse(ConstraintViolationException e) {
		return Response.status(Response.Status.BAD_REQUEST)
				.entity(MensagemErro.build(e.getConstraintViolations().stream()
						.map(constraintViolation -> constraintViolation.getMessage()).collect(Collectors.toList())))
				.build();
	}

}
