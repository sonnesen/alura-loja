package br.com.alura.loja.orcamento;

import java.math.BigDecimal;

public final class Aprovado extends SituacaoOrcamento {

  @Override
  public BigDecimal calcularValorDescontoExtra(Orcamento orcamento) {
    return orcamento.getValor().multiply(new BigDecimal("0.02"));
  }

  @Override
  public void finalizar(Orcamento orcamento) {
    orcamento.situacao = new Finalizado();
  }

}
