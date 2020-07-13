package br.com.alura.DAO;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.alura.Models.Agendamento;

@Stateless
public class ScheduleDAO {

	@PersistenceContext
	private EntityManager _entEntityManager;

	public List<Agendamento> Todos() {

		return _entEntityManager.createQuery("SELECT a from Agendamento a", Agendamento.class).getResultList();

	}

	public Agendamento Salvar(Agendamento agendamento) {
		_entEntityManager.persist(agendamento);
		
		return agendamento;
	}

}
