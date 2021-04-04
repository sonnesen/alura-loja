package br.com.alura.loja.orcamento;

public final class Reprovado extends SituacaoOrcamento {

  @Override
  public void finalizar(Orcamento orcamento) {
    orcamento.situacao = new Finalizado();
  }
}
