package br.com.alexcoimbra12.flat.ws.util;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "result")
public class ResultMessage {

	private String resultMessage;

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

}
