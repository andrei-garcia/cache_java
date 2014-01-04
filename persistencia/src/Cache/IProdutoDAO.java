package Cache;

import java.util.List;

import cache.ColocarNoCache;

public interface IProdutoDAO {
	public void salva(Produto p);
	@ColocarNoCache
	public List<Produto> lista();
}