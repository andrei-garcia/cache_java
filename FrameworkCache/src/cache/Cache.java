package cache;


import java.util.HashMap;
import java.util.Map;



public class Cache {
		
	private   Map<String,Object> lista = new HashMap<String,Object>();
	private static Cache cache = new Cache();
	
	private Cache(){
		
	}
	
	
	

	public  boolean hasKey(String s){
		if(cache.lista.containsKey(s))return true;
		return false;
	}
	
	
	public Object get(String key){
		return cache.lista.get(key);
		
	}
	
	
	
	public static Cache getCache() {
		return cache;
	}




	public  void add(String key,Object o,int tempo){
		cache.lista.put(key,o);
		monitoraCache(tempo,key);
	}
	
	public  void purg(){
		cache.lista.clear();
	}
	
	private static  void monitoraCache( final int tempo,final String key) {
		  
	     new Thread(new Runnable() { 
	     
	            @Override
	            public void run() {
	            	int time=tempo;
	                while (true) {
	                    try {
	                    if(time==0){
	                    	cache.lista.remove(key);
	                    	System.out.println("removido do cache");
	                    	return;
	                    }
	                    
	                    System.out.println("monitorando cache");
	                    Thread.sleep(1000); 
	                    time--;
	                    } catch (Exception e) {
	                    	System.err.println(e);
	                    }                    
	                }
	            }
	        }).start();          
	    }
}
