package br.com.alura.loja.orcamento;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Orcamento implements Orcavel {

  private BigDecimal valor;
  protected SituacaoOrcamento situacao;
  private List<Orcavel> itens;

  public Orcamento() {
    this.valor = BigDecimal.ZERO;
    this.itens = new ArrayList<>();
    this.situacao = new EmAnalise();
  }

  public void aplicarDescontoExtra() {
    BigDecimal valorDoDescontoExtra = this.situacao.calcularValorDescontoExtra(this);
    this.valor = this.valor.subtract(valorDoDescontoExtra);
  }

  public void aprovar() {
    this.situacao.aprovar(this);
  }

  public void reprovar() {
    this.situacao.reprovar(this);
  }

  public void finalizar() {
    this.situacao.finalizar(this);
  }

  public BigDecimal getValor(){
    try {
      // Simulando lentidão para retornar o valor do orçamento
      Thread.sleep(2000);
      return valor;
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public int getQuantidadeItens() {
    return this.itens.size();
  }

  public boolean isFinalizado() {
    return situacao instanceof Finalizado;
  }

  public void adicionarItem(Orcavel item) {
    this.valor = valor.add(item.getValor());
    this.itens.add(item);
  }

}
