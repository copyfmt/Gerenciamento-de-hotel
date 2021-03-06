package com.fatec.gerenciamentohotel.control.dao.exceptions;

import java.io.PrintStream;
import java.io.PrintWriter;

public class DAOException extends Exception {

	private static final long serialVersionUID = 1L;
	private String desc;

	public DAOException(String desc) {
		this.desc = desc;
	}

	@Override
	public String getMessage() {
		// return super.getMessage();
		return String.format("Erro DAO: " + "[ %s ]", this.desc);
	}

	@Override
	public synchronized Throwable fillInStackTrace() {
		return super.fillInStackTrace();
	}

	@Override
	public synchronized Throwable getCause() {
		return super.getCause();
	}

	@Override
	public String getLocalizedMessage() {
		return super.getLocalizedMessage();
	}

	@Override
	public StackTraceElement[] getStackTrace() {
		return super.getStackTrace();
	}

	@Override
	public synchronized Throwable initCause(Throwable cause) {
		return super.initCause(cause);
	}

	@Override
	public void printStackTrace() {
		super.printStackTrace();
	}

	@Override
	public void printStackTrace(PrintStream s) {
		super.printStackTrace(s);
	}

	@Override
	public void printStackTrace(PrintWriter s) {
		super.printStackTrace(s);
	}

	@Override
	public void setStackTrace(StackTraceElement[] stackTrace) {
		super.setStackTrace(stackTrace);
	}

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

}
