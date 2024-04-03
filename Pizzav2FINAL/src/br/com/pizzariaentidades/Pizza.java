package br.com.pizzariaentidades;

import java.util.ArrayList;

public class Pizza {
	private ArrayList<String> pizzas = new ArrayList<>();
	private final ArrayList<String> ingrediente = new ArrayList<String>();

	public Pizza(ArrayList<String> pizzas) {
		this.pizzas = pizzas;

	}

	public ArrayList<String> getPizzas() {
		return pizzas;
	}

	public void setPizzas(ArrayList<String> pizzas) {
		this.pizzas = pizzas;
	}

	public final ArrayList<String> getIngredientes() {
		ingrediente.add("frango");
		ingrediente.add("carne");
		ingrediente.add("ovo");
		ingrediente.add("tomate");
		ingrediente.add("queijo");
		ingrediente.add("camarão");
		ingrediente.add("chocolate");
		ingrediente.add("salmão");
		ingrediente.add("calabresa");
		ingrediente.add("peperoni");
		return ingrediente;
	}

	public String ingredientesStr() {
		StringBuilder allIngredientesDisponiveis = new StringBuilder("Ingredientes disponíveis:\n");

		ArrayList<String> ingredientesDisponiveis = getIngredientes();

		if (!ingredientesDisponiveis.isEmpty()) {
			for (String ingrediente : ingredientesDisponiveis) {
				allIngredientesDisponiveis.append("==========================").append("\n");
				allIngredientesDisponiveis.append(ingrediente).append("\n");
			}

		}
		return allIngredientesDisponiveis.toString();
	}
}