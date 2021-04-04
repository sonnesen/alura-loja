package br.com.alura.loja.orcamento;

import java.util.Map;
import br.com.alura.loja.http.HttpAdapter;

public class RegistroOrcamento {

  private HttpAdapter http;

  public RegistroOrcamento(HttpAdapter http) {
    this.http = http;
  }

  public void registrar(Orcamento orcamento) {
    if (!orcamento.isFinalizado()) {
      throw new SituacaoOrcamentoException("Orçamento não finalizado!");
    }

    String url = "http://api.externa/orcamento";
    Map<String, Object> dados = Map.of(
        "valor", orcamento.getValor(),
        "quantidadeItens", orcamento.getQuantidadeItens());
    http.post(url, dados);
  }
}
