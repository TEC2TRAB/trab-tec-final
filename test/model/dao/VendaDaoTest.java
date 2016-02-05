package model.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import classes.ConnectionFactory;
import model.Itens;
import model.Venda;

public class VendaDaoTest {

	Connection connection;
	VendaDao dao;
	
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
	
	@Test
	public void testCadastrar() throws SQLException {
		boolean found = false;
		
		Venda venda = new Venda();
		venda.setCpfCliente(51073628779L);
		venda.setIdVendedor(3);
		venda.setValorTotal(20);
		
		Itens item = new Itens();
		item.setIdProduto(1);
		item.setPreco(3.21);
		item.setQuantidade(1);
		ArrayList<Itens> itens = new ArrayList<>(); 
        itens.add(item);
        venda.setItens(itens);
        
        dao.cadastrar(venda);
        List<Venda> vendas = dao.consultar(venda.getCpfCliente());
        for(Venda v: vendas) {
        	if(v.getCpfCliente() == venda.getCpfCliente() &&
        			v.getIdVendedor() == venda.getIdVendedor() &&
        				v.getValorTotal() == venda.getValorTotal())
        		found = true;
        }
        
        if(!found)
        	fail("A venda não foi encontrada.");
	}
	
	@Test(expected = SQLException.class)
	public void testVendaClienteErrado() throws SQLException {
		
		boolean found = false;
		
		Venda venda = new Venda();
		venda.setCpfCliente(5107362878L);
		venda.setIdVendedor(3);
		venda.setValorTotal(20);
		
		Itens item = new Itens();
		item.setIdProduto(1);
		item.setPreco(3.21);
		item.setQuantidade(1);
		ArrayList<Itens> itens = new ArrayList<>(); 
        itens.add(item);
        venda.setItens(itens);
        
        dao.cadastrar(venda);
        List<Venda> vendas = dao.consultar(venda.getCpfCliente());
        for(Venda v: vendas) {
        	if(v.getCpfCliente() == venda.getCpfCliente() &&
        			v.getIdVendedor() == venda.getIdVendedor() &&
        				v.getValorTotal() == venda.getValorTotal())
        		found = true;
        }
        
        if(!found)
        	fail("A venda não foi encontrada.");
		
	}
	
	
	@Test(expected = SQLException.class)
	public void testVendaVendedorErrado() throws SQLException {
		
		boolean found = false;
		
		Venda venda = new Venda();
		venda.setCpfCliente(51073628779L);
		venda.setIdVendedor(666);
		venda.setValorTotal(20);
		
		Itens item = new Itens();
		item.setIdProduto(1);
		item.setPreco(3.21);
		item.setQuantidade(1);
		ArrayList<Itens> itens = new ArrayList<>(); 
        itens.add(item);
        venda.setItens(itens);
        
        dao.cadastrar(venda);
        List<Venda> vendas = dao.consultar(venda.getCpfCliente());
        for(Venda v: vendas) {
        	if(v.getCpfCliente() == venda.getCpfCliente() &&
        			v.getIdVendedor() == venda.getIdVendedor() &&
        				v.getValorTotal() == venda.getValorTotal())
        		found = true;
        }
        
        if(!found)
        	fail("A venda não foi encontrada.");
	}
	
	
	
	@Test
	public void testVendaDadosNull() throws SQLException {
		
		boolean found = false;
		
		Venda venda = new Venda();
		venda.setCpfCliente(51073628779L);
		venda.setIdVendedor(3);
		
		
		Itens item = new Itens();
		item.setIdProduto(1);
		item.setPreco(3.21);
		item.setQuantidade(1);
		venda.setValorTotal(item.getPreco());
		ArrayList<Itens> itens = new ArrayList<>(); 
        itens.add(item);
        venda.setItens(itens);
        
        assertEquals(3.21, venda.getValorTotal(),0); 
		
	}

}
