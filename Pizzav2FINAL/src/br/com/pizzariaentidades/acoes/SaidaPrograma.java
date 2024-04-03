package br.com.pizzariaentidades.acoes;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.com.pizzariaentidades.Pedido;
import br.com.pizzariaentidades.Pizza;
import br.com.pizzariaentidades.acoes.Metodo;

public class SaidaPrograma {
	public static void main(String[] args) throws NaoExistePedido, IngredienteInsuficiente, NumberFormatException {
		Pedido pedido = new Pedido(null, null);
		Pizza pizza = new Pizza(null);
		Metodo metodo = new Metodo();
		String menu = """
					1) Receber um pedido
					2) Olhar pedido atual
					3) Preparar uma pizza
					4) Servir pedido
					5) Estatísticas dos pedidos
					6) Sair do programa

				""";
		try {
			int opcao = 0;
			do {
				String opcaoSTR = JOptionPane.showInputDialog(null, menu + "Insira uma opção");
				opcao = Integer.parseInt(opcaoSTR);
				switch (opcao) {
				case 1:
					String cliente = JOptionPane.showInputDialog(null, "Insira o nome do cliente");

					if (!cliente.trim().isEmpty()) {
						metodo.receberUmPedido(cliente);
						JOptionPane.showMessageDialog(null, "Pedido recebido com sucesso!");

					} else {
						JOptionPane.showMessageDialog(null, "Insira o nome do cliente!");
					}
					break;
				case 2:
					try {
						if (!metodo.getPedidos().isEmpty()) {
							JOptionPane.showMessageDialog(null, metodo.olharPedidoAtual());
						} else {
							throw new NaoExistePedido();
						}
					} catch (NaoExistePedido e) {
						JOptionPane.showMessageDialog(null, e);
					}
					break;
				case 3:
				    try {
				        ArrayList<String> ingredientesPraPorNaPizza = new ArrayList<>();

				        JOptionPane.showMessageDialog(null, pizza.ingredientesStr());
				        
				        for (int i = 0; i < 5; i++) {
				            String ingrediente = JOptionPane.showInputDialog(null, "Digite o ingrediente");
				            ingredientesPraPorNaPizza.add(ingrediente);
				        }

				        if (ingredientesPraPorNaPizza.size() == 5
				                && ingredientesPraPorNaPizza.stream().allMatch(pizza.getIngredientes()::contains)) {
				            metodo.criarPizza(ingredientesPraPorNaPizza);
				            JOptionPane.showMessageDialog(null, "Pizza preparada com sucesso!!");
				        } else {
				            throw new IngredienteInsuficiente();
				        }
				    } catch (IngredienteInsuficiente e) {
				        JOptionPane.showMessageDialog(null, e);
				    }
				    break;



				 case 4:
                     try {
                         if (metodo.getPedidos().isEmpty()) {
                             throw new NaoExistePedido();
                         } else if (metodo.servirPedido()) {
                             JOptionPane.showMessageDialog(null, "Pedido servido com sucesso!!");
                         } else {
                             JOptionPane.showMessageDialog(null, "O pedido não foi servido.");
                         }
                     } catch (NaoExistePedido e) {
                         JOptionPane.showMessageDialog(null, e);
                     }
                     break;
				 case 5:
                     JOptionPane.showMessageDialog(null, metodo.calcularEstatisticas());
                     break;
				case 6:
					break;
				default:
					JOptionPane.showMessageDialog(null, "Obrigado por testar o programa!");

				}

			} while (opcao != 6);

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Insira valores válidos!" + e);

		}

	}
}
