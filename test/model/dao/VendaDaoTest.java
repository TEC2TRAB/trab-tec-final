package model.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import classes.ConnectionFactory;
import model.Venda;

public class VendaDaoTest {

	Connection connection;
	VendaDao dao;
	Venda venda;
	
	@Before
	public void setUp() throws Exception {
		connection = new ConnectionFactory().getConnection();
		connection.setAutoCommit(false);
		
		dao = new VendaDao(connection);
	}

	@After
	public void tearDown() throws Exception {
		connection.rollback();
		connection.close();
	}

	@Test
	public void testConsultarPorCpf() throws SQLException {
		long cpf = 60456164308L;
		
		List<Venda> vendas = dao.consultar(cpf);
		for(Venda v: vendas)
			assertEquals(cpf, v.getCpfCliente(), 0);
	}

	@Test
	public void testConsultarPorId() throws SQLException {
		long id = 3;
		
		List<Venda> vendas = dao.consultar(id);
		for(Venda v: vendas)
			assertEquals(id, v.getIdVendedor(), 0);
	}

	@Test
	public void testConsultarPorData() throws SQLException, ParseException {
		String data = "2016-06-30";
		java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(data);
		Date sqlDate = new Date(utilDate.getTime());
		
		List<Venda> vendas = dao.consultar(sqlDate);
		for(Venda v: vendas)
			assertEquals(utilDate, v.getDataVenda().getTime());
	}

}
