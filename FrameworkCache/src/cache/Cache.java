package cache;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;



public class Cache {
		
	private   Map<String,Object> listaObjetosNoCache = new HashMap<String,Object>();
	private   Map<String,Object> listaChavesMonitoradas = new HashMap<String,Object>();
	private static Cache cache = new Cache();
	private long tempoEmMilisegundos = System.currentTimeMillis();	
	
	
	private Cache(){
		
	}
	
	static {
		cache.monitorarChavesDoCache();
		
	}
	
	
	
	
	public  boolean hasKey(String s){
		if(cache.listaObjetosNoCache.containsKey(s))return true;
		return false;
	}
	
	
	


	public Object get(String key){
		return cache.listaObjetosNoCache.get(key);
		
	}
	
	
	
	public static Cache getCache() {
		return cache;
	}




	public  void add(String key,Object o,int tempo){
		
		addChaveObjetoNoCache(key,o);
		addChaveParaMonitorar(key,tempo);
		
	}
	
	public  void limpaListaDoCache(){
		cache.listaObjetosNoCache.clear();
	}
	
	private void addChaveParaMonitorar(String key,int tempo){
		cache.listaChavesMonitoradas.put(key,calculaTempoDeDuracaoNoCache(tempo));
		
	}
	
	private void addChaveObjetoNoCache(String key,Object objeto){
		cache.listaObjetosNoCache.put(key,objeto);
	}
	
	private void monitorarChavesDoCache() {
		Timer timer = new Timer();
		
		timer.schedule(new MonitoraChaves(),1000,1000);
		
	}
	
	private long calculaTempoDeDuracaoNoCache(int tempo){
		long tempoDeDuracaoNoCache = (tempo*1000)+tempoEmMilisegundos; ;
		return tempoDeDuracaoNoCache;
		
	}
	

	
	class MonitoraChaves extends TimerTask {
	
	 
	
	@Override
	public void run() {
		ArrayList chavesParaExcluir = new ArrayList();
		
		for (int i = 0; i < listaChavesMonitoradas.size(); i++) {
			
		}
		
	}
		
	}
}
