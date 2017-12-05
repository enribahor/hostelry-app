package is.restaurante;

/**
 * Enumerado con los diferentes tipos de plato
 * @author Kike
 */
public enum TipoPlatos {
		PRIMERO, SEGUNDO, POSTRE, BEBIDA; 
		
		public int toInt(){
			switch(this){
			case PRIMERO: return 1;
			case SEGUNDO: return 2;
			case POSTRE: return 3;
			case BEBIDA: return 0;
			
			}
			return 0;
		}
}
