package aaaapruebasDeExamenParcial2;

import java.util.ArrayList;

public class Fuego extends Pokemon{
	public Fuego(String nombre, String tipo) {
        super(nombre, tipo);
    }

    @Override
    protected ArrayList<String> obtenerAtaquesDisponibles() {
        ArrayList<String> ataquesDisponibles = new ArrayList<String>();
        ataquesDisponibles.add("Hydro pump");
        ataquesDisponibles.add("Solar Beam");
        ataquesDisponibles.add("Eruption");
        ataquesDisponibles.add("Flamethrower");
        ataquesDisponibles.add("Aqua Jet");
        ataquesDisponibles.add("Whirlpool");
        ataquesDisponibles.add("Synthesis");
        ataquesDisponibles.add("Petal Dance");
        return ataquesDisponibles;
    }
}

