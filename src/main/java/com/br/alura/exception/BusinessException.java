package com.br.alura.exception;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class BusinessException extends Exception {

	private static final long serialVersionUID = 1L;

	private List<String> msgs;

	public BusinessException() {
		super();
		msgs = new ArrayList<String>();
	}

	public BusinessException(String msg) {
		super(msg);
		msgs = new ArrayList<String>();
		msgs.add(msg);
	}

	public List<String> getMsgs() {
		return msgs;
	}

	public void setMsgs(List<String> msgs) {
		this.msgs = msgs;
	}
	
	
	

}
