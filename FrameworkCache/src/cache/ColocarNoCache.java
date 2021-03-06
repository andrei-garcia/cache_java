
package cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *Anota��o para ser utilizada sobre uma interface ,sendo anotada
 * em cima de um m�todo,metodo o qual sera inserido no cache da JVM seu objeto de retorno
 *
 *@author Andrei Garcia
 *@category anota��o
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ColocarNoCache {

	int tempo() default 60;


}
