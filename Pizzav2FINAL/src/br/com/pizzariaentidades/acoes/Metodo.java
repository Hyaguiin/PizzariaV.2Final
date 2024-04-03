package br.com.pizzariaentidades.acoes;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Random;

import br.com.pizzariaentidades.Pedido;
import br.com.pizzariaentidades.Pizza;

public class Metodo {
	Pizza pizza = new Pizza(null);
	private ArrayList<Pizza> pizzasCriadas = new ArrayList<>();
	private ArrayList<Pizza> pizzasServidas = new ArrayList<>();
	private Queue<Pedido> pedidos = new ArrayDeque<>();
	private Queue<Pedido> pedidosServidos = new ArrayDeque<>();

	public ArrayList<Pizza> getPizzasCriadas() {
		return pizzasCriadas;

	}

	public ArrayList<Pizza> getPizzasServidas() {
		return pizzasServidas;
	}

	public Queue<Pedido> getPedidos() {
		return pedidos;
	}

	public Queue<Pedido> getPedidosServidos() {
		return pedidosServidos;
	}

	public void criarPizza(ArrayList<String> ingredientes) {
	    if (ingredientes != null && ingredientes.size() == 5) {
	        Pizza novaPizza = new Pizza(ingredientes);
	        pizzasCriadas.add(novaPizza);
	    }
	}


	public void receberUmPedido(String nome) {
		Random pedidoCliente = new Random();
		Pedido pedido = null;
		if (nome != null) {
			ArrayList<String> ingredientesAleatoriosLista = new ArrayList<>();

			for (int i = 0; i < 5; i++) {
				int indexDaLista = pedidoCliente.nextInt(pizza.getIngredientes().size());
				String IngredienteAchado = pizza.getIngredientes().get(indexDaLista);
				ingredientesAleatoriosLista.add(IngredienteAchado);
			}
			pedido = new Pedido(nome, ingredientesAleatoriosLista);
			pedidos.add(pedido);
		}
	}

	public String olharPedidoAtual() {
		StringBuilder pedidoAtualSRT = new StringBuilder();
		if (!pedidos.isEmpty()) {
			Pedido pedidoAtual = pedidos.peek(); 
			 pedidoAtualSRT.append("Pedido atual: ").append("==================").append("\n");
			 pedidoAtualSRT.append("ID : ").append(pedidoAtual.getID()).append("\n");
			 pedidoAtualSRT.append("Nome : ").append(pedidoAtual.getName()).append("\n");
			 pedidoAtualSRT.append("Ingredientes: ").append(pedidoAtual.getIngredientesAleatorios()).append("\n");
			 pedidoAtualSRT.append(("=================="));
			}
		return pedidoAtualSRT.toString();

	}
	
	public Boolean servirPedido() {
        Boolean pedidoServido = false;
        Pizza pizzaAchada = null;
        if (!pedidos.isEmpty()) {
            for (Pizza p : pizzasCriadas) {
                int ingredientesCorretos = 0;
                for (String pedidoIngrediente : pedidos.peek().getIngredientesAleatorios()) {
                    if (p.getIngredientes().contains(pedidoIngrediente)) {
                        ingredientesCorretos++;
                    }
                }
                if (ingredientesCorretos >= 3) {
                    pizzaAchada = p;
                    pedidos.poll();
                    pedidoServido = true;
                    break;
                }
            }
            pizzasServidas.add(pizzaAchada);
            pizzasCriadas.remove(pizzaAchada);
        }
        return pedidoServido;
    }
	public String calcularEstatisticas() {
	    StringBuilder estatisticas = new StringBuilder();

	    int totalPizzasServidas = pizzasServidas.size();
	    estatisticas.append("Pizzas servidas: ").append(totalPizzasServidas).append("\n");

	    if (totalPizzasServidas > 0) {
	        HashMap<String, Integer> ingredientesUtilizados = new HashMap<>();
	        for (Pizza pizza : pizzasServidas) {
	            for (String ingrediente : pizza.getIngredientes()) {
	                ingredientesUtilizados.put(ingrediente, ingredientesUtilizados.getOrDefault(ingrediente, 0) + 1);
	            }
	        }

	        String ingredienteMaisPedido = "";
	        int vezesMaisPedido = 0;
	        for (Map.Entry<String, Integer> entry : ingredientesUtilizados.entrySet()) {
	            if (entry.getValue() > vezesMaisPedido) {
	                ingredienteMaisPedido = entry.getKey();
	                vezesMaisPedido = entry.getValue();
	            }
	        }
	        estatisticas.append("Ingrediente mais pedido: ").append(ingredienteMaisPedido).append("\n");

	        ArrayList<String> ingredientesNaoUtilizados = new ArrayList<>();
	        ArrayList<String> ingredientesDisponiveis = pizza.getIngredientes();
	        for (String ingrediente : ingredientesDisponiveis) {
	            if (!ingredientesUtilizados.containsKey(ingrediente)) {
	                ingredientesNaoUtilizados.add(ingrediente);
	            }
	        }
	        estatisticas.append("Ingredientes não utilizados: ").append(ingredientesNaoUtilizados).append("\n");

	        int totalIngredientesCorretos = 0;
	        for (Pizza pizza : pizzasServidas) {
	            if (pizza.getIngredientes().size() == 5) {
	                totalIngredientesCorretos += 5;
	            }
	        }
	        double quantidadeMediaIngredientesCorretos = (double) totalIngredientesCorretos / totalPizzasServidas;
	        estatisticas.append("Quantidade média de ingredientes corretos por pizza: ").append(quantidadeMediaIngredientesCorretos).append("\n");
	    } else {
	        estatisticas.append("Nenhuma pizza servida ainda.\n");
	        estatisticas.append("Ingrediente mais pedido: N/A\n");
	        estatisticas.append("Ingredientes não utilizados: N/A\n");
	        estatisticas.append("Quantidade média de ingredientes corretos por pizza: N/A\n");
	    }

	    int pedidosNaFila = pedidos.size();
	    estatisticas.append("Pedidos na fila: ").append(pedidosNaFila).append("\n");

	    return estatisticas.toString();
	}


}
	

