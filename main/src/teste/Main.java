package teste;

import cache.Cache;
import Cache.IProdutoDAO;
import Cache.ProdutoDAO;



public class Main {

	public static void main(String[] args) {
	
		IProdutoDAO dao3 = Cache.proxy(IProdutoDAO.class,new ProdutoDAO());
		System.out.println(dao3.lista());
		System.out.println(dao3.lista());
	
	}

}
