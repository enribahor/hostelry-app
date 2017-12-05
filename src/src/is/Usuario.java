package is;
/**
 * Gestiona los usuarios, que constan de id y contraseña, para hacer login en la aplicación
 * @author Jesús
 */
public class Usuario {

	public Usuario(String nombre, String contrasena){
		this.id = nombre;
		this.password = contrasena.toCharArray();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public char[] getPassword() {
		return password;
	}
	public void setPassword(char[] password) {
		this.password = password;
	}
	private String id;
	private char[] password;

}
