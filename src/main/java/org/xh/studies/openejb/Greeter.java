package org.xh.studies.openejb;

import javax.ejb.Remote;

@Remote
public interface
Greeter {

	String sayHello(String to);

}
