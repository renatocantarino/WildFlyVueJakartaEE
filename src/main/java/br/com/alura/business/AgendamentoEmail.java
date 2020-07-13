package br.com.alura.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.alura.DAO.ScheduleDAO;
import br.com.alura.Models.Agendamento;

/**
 * @author renatocantarino
 */

@Stateless
public class AgendamentoEmail {

	@Inject
	private ScheduleDAO _agendamento;

	public List<Agendamento> Todos() {

		return _agendamento.Todos();

	}

	public Agendamento Salvar(Agendamento agendamento) {
		
		return _agendamento.Salvar(agendamento);
	}

}
