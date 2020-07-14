package com.br.alura.jobs;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import com.br.alura.Interceptors.Logger;

import br.com.alura.Business.AgendamentoEmail;
import br.com.alura.Models.Agendamento;

@Logger
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/queue/EmailQueue"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") })
public class JobMdb implements MessageListener {

	@Inject
	private AgendamentoEmail _agendamentoService;

	@Override
	public void onMessage(Message message) {

		try {
			Agendamento _agendamento = message.getBody(Agendamento.class);
			_agendamentoService.Enviar(_agendamento);
		} catch (JMSException e) {
			throw new RuntimeException(e);

		}

	}

}
