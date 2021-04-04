package br.com.alura.loja;

import java.math.BigDecimal;
import java.util.Arrays;
import br.com.alura.loja.desconto.CalculadoraDeDescontos;
import br.com.alura.loja.http.JavaHttpClient;
import br.com.alura.loja.imposto.CalculadoraDeImpostos;
import br.com.alura.loja.imposto.ICMS;
import br.com.alura.loja.imposto.ISS;
import br.com.alura.loja.orcamento.ItemOrcamento;
import br.com.alura.loja.orcamento.Orcamento;
import br.com.alura.loja.orcamento.OrcamentoProxy;
import br.com.alura.loja.orcamento.RegistroOrcamento;
import br.com.alura.loja.pedido.DadosDoPedido;
import br.com.alura.loja.pedido.GerarPedidoHandler;
import br.com.alura.loja.pedido.acao.EnviarEmailPedido;
import br.com.alura.loja.pedido.acao.LogDePedido;
import br.com.alura.loja.pedido.acao.SalvarPedidoNoBancoDeDados;

public class LojaApp {

  public static void main(String[] args) {

    testarOrcamentos();
    testarPedidos();
    testarAdapter();
    testarComposicao();

  }

  private static void testarComposicao() {
    Orcamento antigo = new Orcamento();
    antigo.adicionarItem(new ItemOrcamento(new BigDecimal("200")));
    antigo.reprovar();

    Orcamento novo = new Orcamento();
    novo.adicionarItem(new ItemOrcamento(new BigDecimal("500")));
    novo.adicionarItem(antigo);
    
    OrcamentoProxy proxy = new OrcamentoProxy(novo);
    
    System.out.println(proxy.getValor());
    System.out.println(proxy.getValor());
    System.out.println(proxy.getValor());
    System.out.println(proxy.getValor());
    System.out.println(proxy.getValor());
    System.out.println(proxy.getValor());

  }

  private static void testarAdapter() {
    Orcamento orcamento = new Orcamento();
    orcamento.adicionarItem(new ItemOrcamento(BigDecimal.TEN));
    orcamento.aprovar();
    orcamento.finalizar();

    RegistroOrcamento registro = new RegistroOrcamento(new JavaHttpClient());
    registro.registrar(orcamento);
  }

  private static void testarOrcamentos() {
    Orcamento primeiro = new Orcamento();
    primeiro.adicionarItem(new ItemOrcamento(new BigDecimal("10")));
    primeiro.adicionarItem(new ItemOrcamento(new BigDecimal("20")));
    primeiro.adicionarItem(new ItemOrcamento(new BigDecimal("30")));
    primeiro.adicionarItem(new ItemOrcamento(new BigDecimal("40")));
    primeiro.adicionarItem(new ItemOrcamento(new BigDecimal("50")));
    primeiro.adicionarItem(new ItemOrcamento(new BigDecimal("50")));

    Orcamento segundo = new Orcamento();
    segundo.adicionarItem(new ItemOrcamento(new BigDecimal("1000")));

    CalculadoraDeImpostos calculadoraDeImpostos = new CalculadoraDeImpostos();
    System.out
        .println(
            String.format("ICMS: %s", calculadoraDeImpostos.calcular(primeiro, new ICMS(null))));
    System.out
        .println(String.format("ISS: %s", calculadoraDeImpostos.calcular(primeiro, new ISS(null))));
    System.out
        .println(String.format("ICMS + ISS: %s",
            calculadoraDeImpostos.calcular(primeiro, new ICMS(new ISS(null)))));

    CalculadoraDeDescontos calculadoraDeDescontos = new CalculadoraDeDescontos();
    System.out.println(
        String.format("Desconto para o primeiro orçamento: %s",
            calculadoraDeDescontos.calcular(primeiro)));
    System.out.println(
        String.format("Desconto para o segundo orçamento: %s",
            calculadoraDeDescontos.calcular(segundo)));
  }

  private static void testarPedidos() {
    String cliente = "José da Silva";
    BigDecimal valorOrcamento = new BigDecimal("600");
    int quantidadeItens = 4;

    DadosDoPedido dadosDoPedido = new DadosDoPedido(cliente, valorOrcamento, quantidadeItens);
    new GerarPedidoHandler(
        Arrays.asList(
            new SalvarPedidoNoBancoDeDados(),
            new EnviarEmailPedido(),
            new LogDePedido()))
                .executa(dadosDoPedido);
  }

}
