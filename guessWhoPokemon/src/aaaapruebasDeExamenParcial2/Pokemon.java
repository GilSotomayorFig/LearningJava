package aaaapruebasDeExamenParcial2;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Pokemon {
	private String nombre;
    private String tipo;
    private ArrayList<String> ataques = new ArrayList<String>();

    public Pokemon(String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
        conseguirAtaquesAleatorios();
    }

    protected abstract ArrayList<String> obtenerAtaquesDisponibles();

    private void conseguirAtaquesAleatorios() {
        ArrayList<String> ataquesDisponibles = obtenerAtaquesDisponibles();
        Collections.shuffle(ataquesDisponibles);

        for (int i = 0; i < 4 && i < ataquesDisponibles.size(); i++) {
            ataques.add(ataquesDisponibles.get(i));
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ArrayList<String> getAtaques() {
        return ataques;
    }

    public void setAtaques(ArrayList<String> ataques) {
        this.ataques = ataques;
    }
}
