package aaaapruebasDeExamenParcial2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private Scanner scanner2;
    private ArrayList<String> opcionesTipo;
    private ArrayList<String> opcionesAtaque;

    public Menu() {
        this.scanner = new Scanner(System.in);
        opcionesTipo = new ArrayList<>();
        opcionesAtaque = new ArrayList<>();
    }

    public void inicializarOpciones(List<String> tipos, List<String> ataques) {
        opcionesTipo.addAll(tipos);
        opcionesAtaque.addAll(ataques);
    }

    public void eliminarOpcionTipo(String tipo) {
        opcionesTipo.remove(tipo);
    }

    public void eliminarOpcionAtaque(String ataque) {
        opcionesAtaque.remove(ataque);
    }

    public List<String> getOpcionesTipo() {
        return opcionesTipo;
    }

    public List<String> getOpcionesAtaque() {
        return opcionesAtaque;
    }

    public void setOpcionesTipo(List<String> opcionesTipo) {
        this.opcionesTipo = new ArrayList<>(opcionesTipo);
    }

    public void setOpcionesAtaque(List<String> opcionesAtaque) {
        this.opcionesAtaque = new ArrayList<>(opcionesAtaque);
    }

    public int mostrarMenu() {
        System.out.println("¿Qué desea hacer?");
        System.out.println("1) De qué tipo es el Pokémon");
        System.out.println("2) Qué ataques tiene");
        return scanner.nextInt();
    }

    public String adivinarTipo() {
        scanner.nextLine();
        System.out.println("¿Qué tipo cree que es el Pokémon?");
        String respuestaJugador = scanner.nextLine();
        return respuestaJugador;
    }

    public String adivinarAtaque() {
        scanner.nextLine();
        System.out.println("¿Qué ataque cree que tiene el Pokémon?");
        String respuestaJugador = scanner.nextLine();
        return respuestaJugador;
    }

    public void cerrar() {
        scanner.close();
    }

    public String adivinarNombre() {
        System.out.println("¿Cuál pokemon de la lista crees que es el pokemon secreto?");
        return scanner.nextLine();
    }
    
    public int finalOpcion(){
    	int opcion1234 = scanner.nextInt();
    	
    	return opcion1234;
    }
    
}
