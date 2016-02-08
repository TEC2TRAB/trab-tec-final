package model.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import classes.ConnectionFactory;
import model.Funcionario;
import java.util.Calendar;
import java.util.List;

public class FuncionarioDaoTest {
	
	Connection connection;
	FuncionarioDao dao;
	Funcionario funcionario;
	Calendar dataTest = Calendar.getInstance();
	
	@Before
	public void setUp() throws Exception {
		connection = new ConnectionFactory().getConnection();
		connection.setAutoCommit(false);
		
		dao = new FuncionarioDao(connection);
		
		funcionario = new Funcionario();
		funcionario.setId(99);
		funcionario.setNome("Teste");
		funcionario.setLogin("teste");
		funcionario.setSenha("teste");
		funcionario.setAdmissao(dataTest);
		funcionario.setCPF(12474203532L);
		funcionario.setRG(505767338L);
		funcionario.setCidade("Fortaleza");
		funcionario.setBairro("teste");
		funcionario.setComple("teste");
		funcionario.setEstado("teste");
		funcionario.setPermissao("Vendedor");
		funcionario.setDtNasc(dataTest);
		funcionario.setNumero(999);
		funcionario.setSexo('m');
	}

	@After
	public void tearDown() throws Exception {
		connection.rollback();
		connection.close();
	}
	
	@Test
	public void testCadastrar() throws SQLException{
		dao.cadastrar(funcionario);
		List<Funcionario> f = dao.consultar(funcionario.getCPF());
		if(f.size() == 1){
			assertEquals(funcionario.getCPF(), f.get(0).getCPF(), 0);
		}else{
			fail("A busca por um indentificador unico retornou mais de um resultado");
		}
	}
	
	@Test 
	public void testAtualizar() throws SQLException{
		dao.cadastrar(funcionario);
		funcionario.setBairro("TesteAtualizar");
		dao.atualizar(funcionario);
		
		List<Funcionario> f = dao.consultar(funcionario.getCPF());
		
		assertEquals(funcionario.getBairro(), f.get(0).getBairro());
	}

}
