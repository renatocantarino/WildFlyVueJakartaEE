package br.com.alura.dto;

import java.time.LocalDateTime;
import java.util.List;

public class MensagemErro {

	private List<String> mensagens;
	private LocalDateTime data;

	public List<String> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<String> mensagens) {
		this.mensagens = mensagens;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public static MensagemErro build(List<String> mensagens) {
		MensagemErro _mensagemErro = new MensagemErro();
		_mensagemErro.setData(LocalDateTime.now());
		_mensagemErro.setMensagens(mensagens);

		return _mensagemErro;
	}

}
