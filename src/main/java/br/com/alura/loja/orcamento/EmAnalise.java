package br.com.alura.loja.orcamento;

import java.math.BigDecimal;

public final class EmAnalise extends SituacaoOrcamento {

  @Override
  public BigDecimal calcularValorDescontoExtra(Orcamento orcamento) {
    return orcamento.getValor().multiply(new BigDecimal("0.05"));
  }

  public void aprovar(Orcamento orcamento) {
    orcamento.situacao = new Aprovado();
  }

  public void reprovar(Orcamento orcamento) {
    orcamento.situacao = new Reprovado();
  }
}
