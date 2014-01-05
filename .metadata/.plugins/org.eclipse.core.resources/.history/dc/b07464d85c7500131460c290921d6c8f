package teste;

import cache.Cache;
import Cache.IProdutoDAO;
import Cache.ProdutoDAO;



public class Main {

	public static void main(String[] args) {
		ProdutoDAO dao = new ProdutoDAO();
		ProdutoDAO dao2 = new ProdutoDAO();
		System.out.println(dao.lista().toString());
		
		System.out.println(dao.lista().toString());
		System.out.println(dao2.lista().toString());
		
		IProdutoDAO dao3 = Cache.proxy(IProdutoDAO.class,new ProdutoDAO());
		System.out.println(dao3.lista());
		System.out.println(dao3.lista());
	
	}

}
