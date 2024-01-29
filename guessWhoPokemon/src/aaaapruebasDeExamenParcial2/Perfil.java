package aaaapruebasDeExamenParcial2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class Perfil {
	private String nombre;
    private int partidasJugadas = 0;
    private int partidasGanadas = 0;

    public Perfil(String nombre) {
        this.nombre = nombre;
        this.partidasJugadas += partidasJugadas;
        this.partidasGanadas += partidasGanadas;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }

    public int getPartidasJugadas() {
        return partidasJugadas;
    }
    public void setPartidasJugadas (int partidasJugadas) {
        this.partidasJugadas += partidasJugadas;
    }

    public int getPartidasGanadas() {
        return partidasGanadas;
    }
    public void setPartidasGanadas (int partidasGanadas	) {
        this.partidasGanadas += partidasGanadas;
    }


  /*  public void incrementarPartidasJugadas() {
        partidasJugadas++;
    }*/

    /*public void incrementarPartidasGanadas() {
        partidasGanadas++;
    }*/

    public void guardarPerfil() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombre + ".txt"))) {
            writer.write("Partidas Jugadas: " + partidasJugadas);
            writer.newLine();
            writer.write("Partidas Ganadas: " + partidasGanadas);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargarPerfil() {
        try {
            Scanner scanner = new Scanner(new File(nombre + ".txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("Partidas Jugadas: ")) {
                    partidasJugadas = Integer.parseInt(line.substring(18));
                } else if (line.startsWith("Partidas Ganadas: ")) {
                    partidasGanadas = Integer.parseInt(line.substring(18));
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public boolean perfilExists() {
        File profileFile = new File(nombre + ".txt");
        return profileFile.exists();
    }
    public void actualizarPerfil(int partidasJugadas, int partidasGanadas) {
        this.partidasJugadas += partidasJugadas;
        this.partidasGanadas += partidasGanadas;
    }

}
