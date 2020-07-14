package br.com.alura.Dao;

import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.br.alura.exception.BusinessException;

import br.com.alura.Models.Agendamento;

@Stateless
public class ScheduleDAO {

	@PersistenceContext
	private EntityManager _entEntityManager;

	public List<Agendamento> Todos(Boolean enviados) {

		return _entEntityManager
				.createQuery("SELECT a from Agendamento a where a.enviado = :pStatus", Agendamento.class)
				.setParameter("pStatus", enviados).getResultList();
	}

	public Agendamento Salvar(Agendamento agendamento) throws BusinessException {

		Optional<Agendamento> _optional = obterByEmail(agendamento);
		if (_optional.isPresent())
			throw new BusinessException("Email já agendado!");

		agendamento.setEnviado(false);
		_entEntityManager.persist(agendamento);

		return agendamento;
	}

	private Optional<Agendamento> obterByEmail(Agendamento agendamento) {

		return _entEntityManager
						.createQuery("SELECT a from Agendamento a where a.email = :pEmail and a.enviado = false",Agendamento.class)
						.setParameter("pEmail", agendamento.getEmail())
						.setMaxResults(1)
						.getResultList()
						.stream()
						.findFirst();
	}
	
	
	public void Atualizar(Agendamento agendamento)
	{
		_entEntityManager.merge(agendamento);
	}
	
	
	
	

}
