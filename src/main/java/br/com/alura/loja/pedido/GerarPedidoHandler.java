package br.com.alura.loja.pedido;

import java.time.LocalDateTime;
import java.util.List;
import br.com.alura.loja.orcamento.ItemOrcamento;
import br.com.alura.loja.orcamento.Orcamento;
import br.com.alura.loja.pedido.acao.AcaoAposGerarPedido;

public class GerarPedidoHandler implements GerarPedido {

  private List<AcaoAposGerarPedido> acoes;

  public GerarPedidoHandler(List<AcaoAposGerarPedido> acoes) {
    this.acoes = acoes;
  }

  @Override
  public void executa(DadosDoPedido dadosDoPedido) {
    Orcamento orcamento = new Orcamento();
    orcamento.adicionarItem(new ItemOrcamento(dadosDoPedido.getValorOrcamento()));
    Pedido pedido = new Pedido(dadosDoPedido.getCliente(), LocalDateTime.now(), orcamento);
    acoes.forEach(acao -> acao.executar(pedido));
  }
}
