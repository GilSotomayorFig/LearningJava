package aaaapruebasDeExamenParcial2;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Random;

public class Programa {
	private Scanner scanner;
	private Scanner scanner2;
	private ArrayList<String> opcionesTipo;
    private ArrayList<String> opcionesAtaque;
    private Scanner scannerr;
    private int partidasGanadas = 0;
    private int partidasJugadas = 0;
	
	public Programa() {
        this.scanner = new Scanner(System.in);
        opcionesTipo = new ArrayList<>();
        opcionesAtaque = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

	
	public void ejecutar() {
	    
	    Pokemon Squirtle = new Agua("Squirtle", "Agua");
	    Pokemon Totodile = new Agua("Totodile", "Agua");
	    Pokemon Mudkip = new Agua("Mudkip", "Agua");
	    Pokemon Froakie = new Agua("Froakie", "Agua");
	    Pokemon Quaxly = new Agua("Quaxly", "Agua");
	    Pokemon Charmander = new Fuego("Charmander", "Fuego");
	    Pokemon Cyndaquil = new Fuego("Cyndaquil", "Fuego");
	    Pokemon Torchic = new Fuego("Torchic", "Fuego");
	    Pokemon Litten = new Fuego("Litten", "Fuego");
	    Pokemon Fuecoco = new Fuego("Fuecoco", "Fuego");
	    Pokemon Bulbasaur = new Planta("Bulbasaur", "Planta");
	    Pokemon Chikorita = new Planta("Chikorita", "Planta");
	    Pokemon Treecko = new Planta("Treecko", "Planta");
	    Pokemon Rowlet = new Planta("Rowlet", "Planta");
	    Pokemon Sprigatito = new Planta("Sprigatito", "Planta");
	    
	    ArrayList<Pokemon> todosLosPokemon = new ArrayList<Pokemon>();
	    todosLosPokemon.add(Squirtle);
	    todosLosPokemon.add(Totodile);
	    todosLosPokemon.add(Mudkip);
	    todosLosPokemon.add(Froakie);
	    todosLosPokemon.add(Quaxly);
	    todosLosPokemon.add(Charmander);
	    todosLosPokemon.add(Cyndaquil);
	    todosLosPokemon.add(Torchic);
	    todosLosPokemon.add(Litten);
	    todosLosPokemon.add(Fuecoco);
	    todosLosPokemon.add(Bulbasaur);
	    todosLosPokemon.add(Chikorita);
	    todosLosPokemon.add(Treecko);
	    todosLosPokemon.add(Rowlet);
	    todosLosPokemon.add(Sprigatito);
	    Collections.shuffle(todosLosPokemon);
	    
	    Random random = new Random();
	    int indicePokemonSecreto = random.nextInt(todosLosPokemon.size());
	    Pokemon pokemonSecreto = todosLosPokemon.get(indicePokemonSecreto);
	    
	    
	    
	    Menu menu = new Menu();
	 
	    int numeroDePreguntas = 0;
	    ArrayList<String> tiposAdivinados = new ArrayList<>();
        ArrayList<String> ataquesAdivinados = new ArrayList<>();
        ArrayList<Pokemon> pokemonRestantes = new ArrayList<>(todosLosPokemon);
        int intentos = 0;
        final int MAX_INTENTOS = 3; 

        // Copia original de opciones disponibles
        ArrayList<String> opcionesTipoOriginal = new ArrayList<>(menu.getOpcionesTipo());
        ArrayList<String> opcionesAtaqueOriginal = new ArrayList<>(menu.getOpcionesAtaque());
      
        
        System.out.print("Ingresa tu nombre de perfil: ");
        String nombrePerfil = scanner.nextLine() + ".txt";
        Perfil perfil = new Perfil(nombrePerfil);
        if (perfil.perfilExists()) {
            perfil.cargarPerfil();
        } else {
            perfil.guardarPerfil();
        }

        
        for (Pokemon pokemon : todosLosPokemon) {
	        String nombre = pokemon.getNombre();
	        String tipo = pokemon.getTipo();
	        String ataques = String.join(", ", pokemon.getAtaques());

	        String formatted = String.format("%-20s :%-10s: %s", nombre, tipo, ataques);
	        System.out.println(formatted);
	    }
        
        while (intentos < MAX_INTENTOS) {
        	
        	perfil.guardarPerfil();
            int opcion = menu.mostrarMenu();

            if (opcion == 1) {
                String tipoAdivinado = menu.adivinarTipo();

                boolean respuestaCorrecta = pokemonSecreto.getTipo().equalsIgnoreCase(tipoAdivinado);

                if (respuestaCorrecta) {
                    System.out.println("¡Respuesta correcta!");
                    tiposAdivinados.add(tipoAdivinado);
                    numeroDePreguntas++;

                    pokemonRestantes.removeIf(pokemon -> !pokemon.getTipo().equalsIgnoreCase(tipoAdivinado));

                    menu.setOpcionesTipo(new ArrayList<>(opcionesTipoOriginal));
                    menu.setOpcionesAtaque(new ArrayList<>(opcionesAtaqueOriginal));
                } else {
                    System.out.println("Tipo de Pokémon incorrecto. Intente nuevamente.");
                    pokemonRestantes.removeIf(pokemon -> pokemon.getTipo().equalsIgnoreCase(tipoAdivinado));
                }
                imprimirListaPokemonRestantes(pokemonRestantes);
            } else if (opcion == 2) {
                String ataqueAdivinado = menu.adivinarAtaque();

                boolean respuestaCorrecta = pokemonSecreto.getAtaques().contains(ataqueAdivinado.toLowerCase());

                if (pokemonSecreto.getAtaques().stream().anyMatch(ataque -> ataque.equalsIgnoreCase(ataqueAdivinado))) {
                	
                	pokemonRestantes.removeIf(pokemon -> !pokemon.getAtaques().stream().anyMatch(ataque -> ataque.equalsIgnoreCase(ataqueAdivinado)));
                    System.out.println("¡Respuesta correcta!");
                    ataquesAdivinados.add(ataqueAdivinado);
                    numeroDePreguntas++;

                    imprimirListaPokemonRestantes(pokemonRestantes);

                } else {
                	pokemonRestantes.removeIf(pokemon -> pokemon.getAtaques().stream().anyMatch(ataque -> ataque.equalsIgnoreCase(ataqueAdivinado)));
                    System.out.println("Ataque de Pokémon incorrecto. Intente nuevamente.");

                    
                    imprimirListaPokemonRestantes(pokemonRestantes);

                }
            } else {
                System.out.println("Opción no válida. Por favor, seleccione 1, 2, 3 o 4.");
            }

            intentos++;
        }

        if (intentos >= MAX_INTENTOS) {
            String nombreAdivinado = menu.adivinarNombre();
            boolean respuestaCorrecta = pokemonSecreto.getNombre().equalsIgnoreCase(nombreAdivinado);
            
            if (respuestaCorrecta) {
                System.out.println("¡Respuesta correcta!");
                partidasGanadas++;
                partidasJugadas++;
                perfil.actualizarPerfil(partidasJugadas, partidasGanadas);
                perfil.guardarPerfil();

               
               
            } else {
                System.out.println("Incorrecto. El Pokémon secreto era: " + pokemonSecreto.getNombre());
                partidasJugadas++;
                perfil.actualizarPerfil(partidasJugadas, partidasGanadas);
                perfil.guardarPerfil();
              
            }
        
            mostrarDespedida();
            
            
        
            
            int opcionFinal = menu.finalOpcion();
            
            if (opcionFinal == 1) {
               
            	
                ejecutar();
            } else if (opcionFinal == 2) {
              
                cerrarJuego();
            } else {
            	
                System.out.println("Opción no válida. El juego se cerrará.");
                cerrarJuego();
            }
        }

        menu.cerrar();
    }

	private static void imprimirListaPokemonRestantes(ArrayList<Pokemon> pokemonList) {
	    System.out.println("Pokémon restantes:");
	    for (Pokemon pokemon : pokemonList) {
	        String nombre = pokemon.getNombre();
	        String tipo = pokemon.getTipo();
	        ArrayList<String> ataques = pokemon.getAtaques();

	        StringBuilder ataquesStr = new StringBuilder();
	        for (String ataque : ataques) {
	            ataquesStr.append(ataque).append(", ");
	        }

	        if (ataquesStr.length() > 2) {
	            ataquesStr.setLength(ataquesStr.length() - 2);
	        }

	        String formatted = String.format("%-20s :%-10s: %s", nombre, tipo, ataquesStr.toString());
	        System.out.println(formatted);
	    }
	}
	
	
	
	
	
	public static void mostrarDespedida() {
	    System.out.println("\nGracias por jugar!");
	    System.out.println("Que deseas hacer?");
	    System.out.println("1) Volver a jugar");
	    System.out.println("2) Cerrar el juego");
	    System.out.print("Selecciona una opcion: ");
	}
	public void cerrarJuego() {
		scanner.close();
	    System.out.println("Adios!");
	    System.exit(0);
	}
}
