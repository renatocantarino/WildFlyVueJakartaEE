package com.br.alura.jobs;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

import br.com.alura.Business.AgendamentoEmail;
import br.com.alura.Models.Agendamento;

@Singleton
public class Email {

	@Inject
	private AgendamentoEmail _agendamento;

	@Inject
	@JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
	private JMSContext _jmsContext;

	@Resource(mappedName = "java:/jms/queue/EmailQueue")
	private Queue _jmsQueue;

	@Schedule(hour = "*", minute = "0,10,20,30,40,50")
	public void Sender() {

		List<Agendamento> lista = _agendamento.Todos(false);
		lista.stream().forEach(item -> {
			_jmsContext.createProducer().send(_jmsQueue, item);
			_agendamento.Enviada(item);
		});
	}
}
