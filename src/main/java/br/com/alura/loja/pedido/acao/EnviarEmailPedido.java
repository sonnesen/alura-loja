package br.com.alura.loja.pedido.acao;

import br.com.alura.loja.pedido.Pedido;

public class EnviarEmailPedido implements AcaoAposGerarPedido {

  @Override
  public void executar(Pedido pedido) {
    System.out.println("Enviar e-mail com as informações do pedido");
  }
}
