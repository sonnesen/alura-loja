package br.com.alura.loja.http;

import java.util.Map;

public class JavaHttpClient implements HttpAdapter {

  @Override
  public void post(String url, Map<String, Object> dados) {
    try {
      System.out.println("Enviando dados...!");
      Thread.sleep(500);
      System.out.println("Dados enviados com sucesso!");
    } catch (Exception e) {
      throw new RuntimeException("Erro ao enviar os dados!", e);
    }

  }

}
