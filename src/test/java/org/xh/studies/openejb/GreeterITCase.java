package org.xh.studies.openejb;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GreeterITCase {

	private static Context ctx;
	private static ApplicationContext springCtx;

	@BeforeClass
	public static void setUp() throws NamingException {
		Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.RemoteInitialContextFactory");
		env.put(Context.PROVIDER_URL, "ejbd://localhost:4201");
		ctx = new InitialContext(env);
		springCtx = new ClassPathXmlApplicationContext("META-INF/spring/beans.xml");
	}

	@AfterClass
	public static void tearDown() throws NamingException {
		ctx.close();
	}

	@Test
	public void sayHelloRemoteEJBTest() throws NamingException {
		Greeter greeter = (Greeter) ctx.lookup("GreeterImplRemote");
		String result = greeter.sayHello("£ukasz");
		String expected = "Hello £ukasz! How are you?";
		Assert.assertEquals(expected, result);
	}
	
	@Test
	public void sayHelloSpringProxyTest() throws NamingException {
		Greeter greeter = (Greeter) springCtx.getBean("greeterBean");
		String result = greeter.sayHello("£ukasz");
		String expected = "Hello £ukasz! How are you?";
		Assert.assertEquals(expected, result);
	}

}
