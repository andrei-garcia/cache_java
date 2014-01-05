package cache;




import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;





public class Cache {
		
	private   Map<String,Object> listaObjetosNoCache = new HashMap<String,Object>();
	private   Map<String,Object> listaChavesMonitoradas = new HashMap<String,Object>();
	private static Cache cache = new Cache();
	
	
	
	private Cache(){
		
	}
	
	static {
		cache.monitorarChavesDoCache();
		
	}
	
	
	
	
	public  boolean hasKey(String s){
		if(listaObjetosNoCache.containsKey(s))return true;
		return false;
	}
	
	
	


	public Object get(String key){
		return listaObjetosNoCache.get(key);
		
	}
	
	
	
	public static Cache getCache() {
		return cache;
	}




	public  void add(String key,Object o,int tempo){
		
		addChaveObjetoNoCache(key,o);
		addChaveParaMonitorar(key,tempo);
		
	}
	
	public  void limpaListaDoCache(){
		listaObjetosNoCache.clear();
	}
	
	public static <T> T proxy(Class<T> iface, final T objetoConcreto) {
		
		@SuppressWarnings("unchecked")
		T proxy = (T) Proxy.newProxyInstance(iface.getClassLoader(),
                new Class[] { iface },
                new InvocationHandler() {
					
					@Override
					public Object invoke(Object proxy, Method method, Object[] args)
							throws Throwable {
						
						String chave = method.getName()+objetoConcreto.getClass().getName();
						
						Field[] atributosDoObjeto = objetoConcreto.getClass().getFields();
						
						for (Field atributo : atributosDoObjeto) {
							chave = chave+atributo.getName();
						}
						
						int tempoConfiguradoNaAnotacao = method.getAnnotation(ColocarNoCache.class).tempo();
						
						if(method.isAnnotationPresent(ColocarNoCache.class)){
							if(getCache().hasKey(chave)){
								System.out.println("dados inseridos no cache");
								return  getCache().get(chave);
								
							}
							else{
								Object o = method.invoke(objetoConcreto, args);
								getCache().add(chave,o,tempoConfiguradoNaAnotacao);
								System.out.println("dados bucados no cache");
								return o;
							}
						}
						return args;
					
					}
				});
		return proxy;
	}
	
	private void addChaveParaMonitorar(String key,int tempo){
		if(!listaChavesMonitoradas.containsKey(key)){
			listaChavesMonitoradas.put(key,calculaTempoDeDuracaoNoCache(tempo));
		}
		
	}
	
	private void addChaveObjetoNoCache(String key,Object objeto){
		if(!listaObjetosNoCache.containsKey(key)){
			listaObjetosNoCache.put(key,objeto);
		}
		
	}
	
	private void monitorarChavesDoCache() {
		Timer timer = new Timer();
		
		timer.schedule(new MonitoraChaves(),1000,1000);
		
	}
	
	private long calculaTempoDeDuracaoNoCache(int tempo){
		long tempoEmMilisegundosDoComputador = tempoAtualDoComputador();
		long tempoDeDuracaoNoCache = (tempo*60000)+tempoEmMilisegundosDoComputador; 
		return tempoDeDuracaoNoCache;
		
	}
	
	private long tempoAtualDoComputador(){
		return System.currentTimeMillis();
	}
	
	class MonitoraChaves extends TimerTask {
	
		private ArrayList<String> verificaChavesParaExclusao(){
			ArrayList<String> chavesParaExcluir = new ArrayList<String>();
			
			long tempoEmMillisDaChave;
			
			long tempoEmMilisegundosDoComputador = tempoAtualDoComputador();
			
			Set<String> chaves = listaChavesMonitoradas.keySet();
			
			for (String chave : chaves) {
				tempoEmMillisDaChave = (long) listaChavesMonitoradas.get(chave);
				if(tempoEmMillisDaChave<=tempoEmMilisegundosDoComputador){
					chavesParaExcluir.add(chave);
				}
			}
			
			return chavesParaExcluir;
		}
		
		private void excluiChaves (ArrayList<String> chaves){
			for (String chave : chaves) {
				System.out.println("chave excluida");
				listaObjetosNoCache.remove(chave);
				listaChavesMonitoradas.remove(chave);
			}
		}
	
		@Override
		public void run() {
			
			ArrayList<String> arrayDeChavesParaExcluir = verificaChavesParaExclusao();
			
			if(arrayDeChavesParaExcluir.size()>0){
				excluiChaves(arrayDeChavesParaExcluir);
			}
			
			
		}
		
	}


}
