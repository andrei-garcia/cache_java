package Teste;

import static org.junit.Assert.*;

import org.junit.Test;

import cache.Cache;
import Cache.IProdutoDAO;
import Cache.Produto;
import Cache.ProdutoDAO;

public class Teste{
	
	
	
	int tempoDeDuracaoNoCache = 1;
	
	
	@Test
	public void test_has_Key() {
		Produto produto = new Produto();
		Cache.pegaOCache().addObjetoNoCache("produtos",produto,tempoDeDuracaoNoCache);
		assertTrue(Cache.pegaOCache().temAchave("produtos"));
		
	}
	
	
 	@Test
	public void test_add_no_Cache() {
		Produto produto2 = new Produto();
		Produto produto4 = new Produto();
		Cache.pegaOCache().addObjetoNoCache("produtos2",produto2,tempoDeDuracaoNoCache);
		
		produto4=(Produto)Cache.pegaOCache().pegarObjeto("produtos2");
		assertTrue(produto2.equals(produto4));
		
	}


	

	@Test
	public void test_limpar_Cache() {
		Produto produto = new Produto();
		Cache.pegaOCache().addObjetoNoCache("produtos",produto,tempoDeDuracaoNoCache);
		Cache.pegaOCache().limpaListaDoCache();
		assertFalse(Cache.pegaOCache().temAchave("produtos"));
		
	}
	
	@Test
	public void test_pegar_Cache() {
		Cache cache1= Cache.pegaOCache();
		Cache cache2= Cache.pegaOCache();
		
		assertTrue(cache1.equals(cache2));
		
	}
	
	@Test
	public void test_pegar_Objeto_Do_Cache() {
		Produto produto = new Produto();
		Cache.pegaOCache().addObjetoNoCache("produtos",produto,tempoDeDuracaoNoCache);
		assertEquals(Cache.pegaOCache().pegarObjeto("produtos"),produto);
		
	}
	
	@Test
	public void test_verificar_tempo_do_objeto() {
		Produto produto = new Produto();
		Cache.pegaOCache().addObjetoNoCache("produtos",produto,tempoDeDuracaoNoCache);
		int tempo = Cache.pegaOCache().verificaTempoRestanteDoObjeto("produtos");
		assertEquals(Cache.pegaOCache().verificaTempoRestanteDoObjeto("produtos"),tempo);
		
	}
	
	
	@Test
	public void test_verificar_altera_tempo_do_objeto() {
		Produto produto = new Produto();
		Cache.pegaOCache().addObjetoNoCache("produtos",produto,tempoDeDuracaoNoCache);
		Cache.pegaOCache().alteraTempoDoObjeto("produtos", 500);
		assertEquals(Cache.pegaOCache().verificaTempoRestanteDoObjeto("produtos"),500);
		
	}

	@Test
	public void test_add_objeto_atraves_anotacao() {
		IProdutoDAO dao3 = Cache.proxy(IProdutoDAO.class,new ProdutoDAO());
	
		dao3.lista();
		
		assertTrue(Cache.pegaOCache().temAchave("Cache.ProdutoDAO.lista"));
		
	}
	

}
