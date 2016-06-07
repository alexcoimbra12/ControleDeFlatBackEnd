package br.com.alexcoimbra12.flat.ws.exception;

public class HospedeNullException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8524556746776052758L;

	public HospedeNullException() {
		super();
	}

	public HospedeNullException(String mensagem) {
		super(mensagem);
	}

	public HospedeNullException(String mensagem, Throwable cause) {
		super(mensagem, cause);
	}
}
