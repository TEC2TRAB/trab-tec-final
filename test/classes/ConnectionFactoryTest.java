package classes;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Before;
import org.junit.Test;

public class ConnectionFactoryTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetConnection() {
		Connection connection = new ConnectionFactory().getConnection();
	}

}
