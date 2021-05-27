package com.rlima.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rlima.cursomc.domain.Categoria;
import com.rlima.cursomc.domain.Cidade;
import com.rlima.cursomc.domain.Cliente;
import com.rlima.cursomc.domain.Endereco;
import com.rlima.cursomc.domain.Estado;
import com.rlima.cursomc.domain.ItemPedido;
import com.rlima.cursomc.domain.Pagamento;
import com.rlima.cursomc.domain.PagamentoComBoleto;
import com.rlima.cursomc.domain.PagamentoComCartao;
import com.rlima.cursomc.domain.Pedido;
import com.rlima.cursomc.domain.Produto;
import com.rlima.cursomc.domain.enums.EstadoPagamento;
import com.rlima.cursomc.domain.enums.TipoCliente;
import com.rlima.cursomc.repositories.CategoriaRepository;
import com.rlima.cursomc.repositories.CidadeRepository;
import com.rlima.cursomc.repositories.ClienteRepository;
import com.rlima.cursomc.repositories.EnderecoRepository;
import com.rlima.cursomc.repositories.EstadoRepository;
import com.rlima.cursomc.repositories.ItemPedidoRepository;
import com.rlima.cursomc.repositories.PagamentoRepository;
import com.rlima.cursomc.repositories.PedidoRepository;
import com.rlima.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository; //essas instancias permitem o armazenamento de uma categoria e/ou produto
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}
	

	@Override
	public void run(String... args) throws Exception {
		
		// Instancias de teste
		// as instancias precisam ser feitas na ordem correta na logica do sistema, tambem garante a coerencia dos dados
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3)); //agora as categorias conhecem o produtos relacionados
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1)); //agora os produtos conhecem sua categoria 
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));		
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2)); //salva todas as categorias e os produtos criados e relacionados
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		//-------------------------------------------------------------------------------------------------------------------------------------------
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1); // a associacao é feita ja no construtor
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		//-------------------------------------------------------------------------------------------------------------------------------------------
		// o id deve ser omitido do construtor? ou ja setado automaticamente como null?
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "325441545845", TipoCliente.PESSOAFISICA);
		//a seguinte logica pode e deve ser implementada diretamento no setTelefones de Cliente
		cli1.getTelefones().addAll(Arrays.asList("212545847", "956554825")); //get pois recupera o array telefones, não seta valor a uma veriavel comum
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "apto 303", "Jardim", "325245587", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "325254522", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		//----------------------------------------------------------------------------------------------------------------------------------------------
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:32"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		//----------------------------------------------------------------------------------------------------------------------------------------------
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
		
		
	}
	
	

}
