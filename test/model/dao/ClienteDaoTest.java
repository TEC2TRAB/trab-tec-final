package model.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import classes.ConnectionFactory;
import model.Cliente;

public class ClienteDaoTest {

	Connection connection;
	ClienteDao dao;
	Cliente cliente;
	Calendar dataTest = Calendar.getInstance();
	
	@Before
	public void setUp() throws Exception {
		connection = new ConnectionFactory().getConnection();
		connection.setAutoCommit(false);
		
		dao = new ClienteDao(connection);
		
		cliente = new Cliente();
		cliente.setId(99);
		cliente.setDataDeCadastro(dataTest);
		cliente.setNome("Teste");
		cliente.setDtNasc(dataTest);
		cliente.setSexo('m');
		cliente.setCPF(12474203532L);
		cliente.setRG(505767338L);
		cliente.setCep("60356325");
		cliente.setRua("Rua Teste");
		cliente.setNumero(999);
		cliente.setEstado("CE");
		cliente.setCidade("Fortaleza");
		cliente.setBairro("Teste");
	}

	@After
	public void tearDown() throws Exception {
		connection.rollback();
		connection.close();
	}

	@Test
	public void testCadastrar() throws SQLException {
		dao.cadastrar(cliente);
		List<Cliente> c = dao.consultar(cliente.getCPF());
		if(c.size() == 1){
			assertEquals(cliente.getCPF(), c.get(0).getCPF(), 0);
		}else{
			fail("A busca por identificador unico retornou mais de um resultado");
		}
	}
	
	@Test
	public void testAtualizar() throws SQLException{
		dao.cadastrar(cliente);
		cliente.setBairro("TesteAtualizar");
		dao.atualizar(cliente);
		
		List<Cliente> c = dao.consultar(cliente.getCPF());
		
		assertEquals(cliente.getBairro(), c.get(0).getBairro());
	}
	
	@Test
	public void testBuscarPorNome() throws SQLException{
		String nome = "JOSÉ";
		List<Cliente> clientes = dao.consultar(nome);
		for(Cliente c : clientes){	
			assertEquals(nome, c.getNome());
		}	
	}
	
	@Test
	public void testRemover() throws SQLException{
		dao.cadastrar(cliente);
		dao.remover(cliente.getCPF());
		List<Cliente> c = dao.consultar(cliente.getCPF());
		if(c.size() != 0){
			fail("O Cliente não foi removido");
		}
	}
	
	@Test(expected = SQLException.class)
	public void testCadastrarDoisClientesComMesmoCPF() throws SQLException{
		dao.cadastrar(cliente);
		cliente.setNome("TesteDeCpfIgual");
		dao.cadastrar(cliente);
	}
}
