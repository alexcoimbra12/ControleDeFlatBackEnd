package br.com.alexcoimbra12.flat.ws.exception;

public class ListException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1923016488576909600L;

	public ListException (){
		super();
	}
	
	public ListException (String mensagem){
		super(mensagem);
	}
	
	public ListException (String mensagem, Throwable cause){
		super(mensagem, cause);
	}
}
