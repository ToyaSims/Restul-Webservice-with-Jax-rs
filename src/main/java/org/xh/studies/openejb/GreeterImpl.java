package org.xh.studies.openejb;

import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Stateless
public class GreeterImpl implements Greeter {
	
	private static final Log log = LogFactory.getLog(GreeterImpl.class);

	public String sayHello(String to) {
		log.info("Saying hi to: " + to);
		return "Hello " + to + "! How are you?";
	}

}
