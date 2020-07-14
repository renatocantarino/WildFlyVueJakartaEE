package br.com.alura.Resources;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.br.alura.exception.BusinessException;

import br.com.alura.Business.AgendamentoEmail;
import br.com.alura.Models.Agendamento;

@Path("/api/agendamento")
public class AgendamentoResource {

	@Inject
	private AgendamentoEmail _agendamento;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response Listar() {

		return Response.ok(_agendamento.Todos(false)).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response Salvar(@Valid Agendamento agendamento) throws BusinessException {
		return Response.ok(_agendamento.Salvar(agendamento)).build();
	}

}
