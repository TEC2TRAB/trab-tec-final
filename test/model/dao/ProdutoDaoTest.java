package model.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import classes.ConnectionFactory;
import model.Produto;

public class ProdutoDaoTest {

	ProdutoDao dao;
	Produto produto;
	
	@Before
	public void setUp() throws Exception {
		Connection connection = new ConnectionFactory().getConnection();
		connection.setAutoCommit(false);
		
		dao = new ProdutoDao(connection);
		produto = new Produto();
		produto.setId(99);
		produto.setNome("TEST");
		produto.setPreco(50);
		produto.setDescricao("TEST");
		produto.setQuantidade(5);
	}
	
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConsultarPorId() throws SQLException {
		List<Produto> produtos = dao.consultar(1);
		if(produtos.size() == 1)
			assertEquals(1, produtos.get(0).getId(), 0);
		else
			fail("O método retornou mais de 1 resultado.");
	}
	
	@Test
	public void testConsultarPorNome() throws SQLException {
		String consulta = "ARROZ";
		List<Produto> produtos = dao.consultar(consulta);
		for(Produto p : produtos) {
			assertTrue(p.getNome().contains(consulta));
		}
	}
	
	@Test
	public void testCadastrar() throws SQLException {
		dao.cadastrar(produto);
		List<Produto> produtos = dao.consultar(produto.getId());
		if(produtos.size() == 1) {
			assertEquals(produto.getId(), produtos.get(0).getId(), 0);
			dao.remover(produto.getId());
		} else {
			fail("O método retornou mais de 1 resultado.");
		}
	}
	
	@Test
	public void testRemover() throws SQLException {
		dao.cadastrar(produto);
		dao.remover(produto.getId());
		List<Produto> produtos = dao.consultar(produto.getId());
		if(produtos.size() != 0)
			fail("O método retornou algum resultado.");
	}
}
