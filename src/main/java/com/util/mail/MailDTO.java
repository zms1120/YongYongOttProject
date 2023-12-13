package com.util.mail;

public class MailDTO extends MailStaticDTO{
	private String to;
	private int number;
	
	public MailDTO() {
		super();
		this.number = (int)(Math.random() * (90000)) + 100000;
	}
	
	public MailDTO(String to) {
		this();
		this.to = to;
	}

	public String getTo() {
		return to;
	}

	public int getNumber() {
		return number;
	}

	// 받는 사람 외 변경 금지
	public void setTo(String to) {
		this.to = to;
	}
	
	
}
