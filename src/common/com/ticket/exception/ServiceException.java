package com.ticket.exception;

/**
 * 自定义的业务逻辑层异常
 * @ClassName: ServiceException   
 * @Description: 捕获业务层的一些异常   
 * @author HiSay  
 * @date Jul 8, 2013 10:24:06 PM   
 *
 */
public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ServiceException() {
		super();
	}
	public ServiceException(String message) {
		super(message);
	}
	public ServiceException(String message, Throwable e) {
		super(message, e);
	}
	public ServiceException(Throwable e) {
		super(e);
	}
}
