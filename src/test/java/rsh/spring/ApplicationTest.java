package rsh.spring;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationTest {

	@Test
	public void bootstrapsApplication() {
		new AnnotationConfigApplicationContext(ApplicationConfig.class).close();
	}
}
