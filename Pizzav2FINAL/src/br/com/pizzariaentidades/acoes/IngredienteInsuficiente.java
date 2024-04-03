package br.com.pizzariaentidades.acoes;

public class IngredienteInsuficiente extends Exception {
	public IngredienteInsuficiente() {
		super("Adicione ao menos 5 Ingredientes!!");
	}
}
