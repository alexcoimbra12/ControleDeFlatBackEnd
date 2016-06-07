package br.com.alexcoimbra12.flat.ws.exception;

public class ImobiliariaNullException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 959038768222482989L;

	public ImobiliariaNullException() {
		super();
	}

	public ImobiliariaNullException(String mensagem) {
		super(mensagem);
	}

	public ImobiliariaNullException(String mensagem, Throwable cause) {
		super(mensagem, cause);
	}
}
