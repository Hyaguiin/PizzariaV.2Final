package br.com.pizzariaentidades.acoes;

public class NaoExistePedido extends Exception {
	public NaoExistePedido() {
		super("Não existem pedidos cadastrados!");
	}
}
