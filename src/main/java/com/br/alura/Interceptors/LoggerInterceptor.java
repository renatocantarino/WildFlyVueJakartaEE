package com.br.alura.Interceptors;

import java.util.logging.Logger;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@Priority(1)
@com.br.alura.Interceptors.Logger
public class LoggerInterceptor {

	@AroundInvoke
	public Object treatException(InvocationContext context) throws Exception {
		Logger logger = Logger.getLogger(context.getTarget().getClass().getName());

		try {
			return context.proceed();
		} catch (Exception e) {

			logger.info(e.getMessage());
			throw e;
		}

	}

}
