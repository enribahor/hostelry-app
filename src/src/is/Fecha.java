package is;

/**
 * Permite el manejo de fechas, consta de las unidades basicas de estas: año,
 * mes, dia, hora y minutos Se utiliza en las reservas Con los métodos equals,
 * mismoDia, esMayorQue y siguienteSemana podemos comparar varias fechas y
 * mostrar por pantalla las reservas en determinados periodos de tiempo
 * 
 * @author JaimeDan
 */
public class Fecha {
	public Fecha() {
		año = -1;
		mes = -1;
		dia = -1;
		hora = -1;
		minutos = -1;
	}
	
	/**
	 * 
	 * @param año
	 * @param mes
	 * @param dia
	 * @param hora
	 * @param minutos
	 */
	public Fecha(int año, int mes, int dia, int hora, int minutos) {
		this.año = año;
		this.mes = mes;
		this.dia = dia;
		this.hora = hora;
		this.minutos = minutos;

	}

	/**
	 * @return the año
	 */
	public int getAño() {
		return año;
	}

	/**
	 * @return the mes
	 */
	public int getMes() {
		return mes;
	}

	/**
	 * @return the dia
	 */
	public int getDia() {
		return dia;
	}

	/**
	 * @return the hora
	 */
	public int getHora() {
		return hora;
	}

	/**
	 * @return the minutos
	 */
	public int getMinutos() {
		return minutos;
	}

	/**
	 * @param año
	 *            the año to set
	 */
	public void setAño(int año) {
		this.año = año;
	}

	/**
	 * @param mes
	 *            the mes to set
	 */
	public void setMes(int mes) {
		this.mes = mes;
	}

	/**
	 * @param dia
	 *            the dia to set
	 */
	public void setDia(int dia) {
		this.dia = dia;
	}

	/**
	 * @param hora
	 *            the hora to set
	 */
	public void setHora(int hora) {
		this.hora = hora;
	}

	/**
	 * @param minutos
	 *            the minutos to set
	 */
	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}

	public Fecha siguienteSemana() {
		if (this.esBisiesto())
			this.diasMes[1]++;
		int numDia = this.dia;
		numDia += 7;
		int mes = this.mes, año = this.año;
		int div = numDia / this.diasMes[this.mes - 1];

		numDia = numDia % this.diasMes[this.mes - 1];
		if (div == 1) {
			if (this.mes == 12) {
				año = this.año + 1;
			} else
				this.mes++;
		}

		if (this.esBisiesto())
			this.diasMes[1]--;
		return new Fecha(año, mes, numDia, this.hora, this.minutos);
	}

	public Fecha siguienteMes() {
		// Atención!! puede que calcule una fecha no valida, como 31 de febrero
		// por ejemplo
		int numMes = (this.mes + 1) % 12, numAño = this.año, numDia = this.dia;
		// si el mes es enero, es que hemos cambiado de año
		if (numMes == 0)
			numAño++;
		// si el año es bisiesto sumamos 1 dia a febrero
		if (this.esBisiesto())
			this.diasMes[1]++;
		// si hemos excedido el dia maximo del mes, asignamos el dia maximo del
		// mes
		if (numDia > this.diasMes[numMes])
			numDia = this.diasMes[numMes];
		// volvemos a poner febrero con 28 dias
		if (this.esBisiesto())
			this.diasMes[1]--;

		return new Fecha(numAño, numMes, numDia, this.hora, this.minutos);
	}

	public boolean esBisiesto() {
		return ((this.año % 4 == 0 && this.año % 100 != 0) || this.año % 400 == 0);
	}

	public boolean esMayorQue(Fecha fecha2) {

		return ((this.año > fecha2.año)
				|| (this.año == fecha2.año && this.mes > fecha2.mes)
				|| (this.año == fecha2.año && this.mes == fecha2.mes && this.dia > fecha2.dia)
				|| (this.año == fecha2.año && this.mes == fecha2.mes
						&& this.dia == fecha2.dia && this.hora > fecha2.hora) || (this.año == fecha2.año
				&& this.mes == fecha2.mes
				&& this.dia == fecha2.dia
				&& this.hora == fecha2.hora && this.minutos > fecha2.minutos));

	}

	public boolean equals(Fecha fecha2) {
		return (this.año == fecha2.año && this.mes == fecha2.mes
				&& this.dia == fecha2.dia && this.hora == fecha2.hora && this.minutos == fecha2.minutos);
	}

	public boolean mismoDia(Fecha f) {
		return (this.año == f.año && this.mes == f.mes && this.dia == f.dia);
	}

	public Fecha parse(String cad) {
		String[] comando = cad.split(" ");
		String[] fecha = comando[0].split("/");
		String[] time = comando[1].split(":");
		
		this.año = Integer.parseInt(fecha[2]);
		this.mes = Integer.parseInt(fecha[1]) - 1;
		this.dia = Integer.parseInt(fecha[0]);
		this.hora = Integer.parseInt(time[0]);
		this.minutos = Integer.parseInt(time[1]);
		return this;

	}

	public int parseMonth(String cad) {
		if (cad.equalsIgnoreCase("Jan"))
			return 0;
		else if (cad.equalsIgnoreCase("Feb"))
			return 1;
		else if (cad.equalsIgnoreCase("Mar"))
			return 2;
		else if (cad.equalsIgnoreCase("Apr"))
			return 3;
		else if (cad.equalsIgnoreCase("May"))
			return 4;
		else if (cad.equalsIgnoreCase("Jun"))
			return 5;
		else if (cad.equalsIgnoreCase("Jul"))
			return 6;
		else if (cad.equalsIgnoreCase("Aug"))
			return 7;
		else if (cad.equalsIgnoreCase("Sep"))
			return 8;
		else if (cad.equalsIgnoreCase("Oct"))
			return 9;
		else if (cad.equalsIgnoreCase("Nov"))
			return 10;
		else if (cad.equalsIgnoreCase("Dec"))
			return 11;
		else
			return 0;
	}

	public void parseDayFirst(String uneditedDate) {
		String[] comando = uneditedDate.split(" ");
		this.dia = Integer.parseInt(comando[0]);
		this.mes = Integer.parseInt(comando[1]);
		this.año = Integer.parseInt(comando[2]);
		this.hora = Integer.parseInt(comando[3]);
		this.minutos = Integer.parseInt(comando[4]);
	}

	public Fecha parse(String cad, int horas, int minutos) {
		String[] comando = cad.split(" ");
		for (int i = 0; i < comando.length; i++) {
			System.out.println(comando[i] + " ");
		}
		this.año = Integer.parseInt(comando[5], 10);

		this.mes = parseMonth(comando[1]);

		this.dia = Integer.parseInt(comando[2], 10);
		this.hora = horas;
		this.minutos = minutos;
		return this;
	}

	public String toString() {
		return this.dia + "/" + (this.mes + 1) + "/" + this.año + " "
				+ (this.hora < 10 ? "0" + this.hora : this.hora) + ":"
				+ (this.minutos < 10 ? "0" + this.minutos : this.minutos);
	}

	private int año;
	private int mes;
	private int dia;

	private int hora;
	private int minutos;
	private int[] diasMes = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30,
			31 };

}
