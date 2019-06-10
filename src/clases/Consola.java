package clases;
import java.util.Scanner;

public class Consola {
	public static void print(String texto) {
		System.out.print(texto);
	}
	public static void println(String texto) {
		System.out.println(texto);
	}
	public static String pedirString() {
		Scanner sc = new Scanner(System.in);
		String texto = sc.next();
		sc.close();
		return texto;
	}
	public static int pedirInt() {
		Scanner sc = new Scanner(System.in);
		int numero = sc.nextInt();
		sc.close();
		return numero;
	}
}
