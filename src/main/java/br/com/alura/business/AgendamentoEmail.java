package br.com.alura.Business;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import com.br.alura.Interceptors.Logger;
import com.br.alura.exception.BusinessException;

import br.com.alura.Dao.ScheduleDAO;
import br.com.alura.Models.Agendamento;

/**
 * @author renatocantarino
 */

@Stateless
@Logger
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AgendamentoEmail {

	@Inject
	private ScheduleDAO _agendamentoRepository;

	@Resource(lookup = "java:jboss/mail/AgendamentoMailSession")
	private Session sessaoEmail;

	private static String EMAIL_FROM = "mail.address";
	private static String EMAIL_USER = "mail.smtp.user";
	private static String EMAIL_PASSWORD = "mail.smtp.pass";

	public List<Agendamento> Todos(Boolean enviados) {

		return _agendamentoRepository.Todos(enviados);

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Agendamento Salvar(Agendamento agendamento) throws BusinessException {

		return _agendamentoRepository.Salvar(agendamento);
	}

	public void Enviar(Agendamento agendamento) {
		try {
			MimeMessage mensagem = new MimeMessage(sessaoEmail);

			mensagem.setFrom(sessaoEmail.getProperty(EMAIL_FROM));
			mensagem.setRecipients(Message.RecipientType.TO, agendamento.getEmail());
			mensagem.setSubject(agendamento.getAssunto());
			mensagem.setText(Optional.ofNullable(agendamento.getMensagem()).orElse(""));

			Transport.send(mensagem, sessaoEmail.getProperty(EMAIL_USER), sessaoEmail.getProperty(EMAIL_PASSWORD));

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

	 public void Enviada(Agendamento agendamento) {
		agendamento.setEnviado(true);
		_agendamentoRepository.Atualizar(agendamento);
	}

}
