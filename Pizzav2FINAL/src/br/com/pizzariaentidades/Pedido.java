package br.com.pizzariaentidades;

import java.util.ArrayList;

public class Pedido {
	private String name;
	private long ID;
	private static long cont = 1;
	private ArrayList<String> ingredientesAleatorio = new ArrayList<>();

	public Pedido(String name, ArrayList<String> ingredientesAleatorio) {
		this.name = name;
		this.ingredientesAleatorio = ingredientesAleatorio;

		this.ID = cont++;
	}

	public String getName() {
		return name;
	}

	public long getID() {
		return ID;
	}

	public ArrayList<String> getIngredientesAleatorios() {
		return ingredientesAleatorio;
	}

}
