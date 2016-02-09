package classes;

import org.junit.Before;
import org.junit.Test;

public class ConnectionFactoryTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetConnection() {
		new ConnectionFactory().getConnection();
	}

}
