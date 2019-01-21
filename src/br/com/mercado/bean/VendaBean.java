package br.com.mercado.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.mercado.dao.FuncionarioDAO;
import br.com.mercado.dao.ProdutoDAO;
import br.com.mercado.dao.VendaDAO;
import br.com.mercado.domain.Funcionario;
import br.com.mercado.domain.Item;
import br.com.mercado.domain.Produto;
import br.com.mercado.domain.Venda;
import br.com.mercado.util.FacesUtil;

@ManagedBean
@ViewScoped
public class VendaBean {

	private List<Produto> listaProdutos;
	private List<Produto> listaProdutosFiltrados;
	private List<Venda> listaVendas;
	private List<Venda> listaVendasFiltrados;
	
	private Venda vendaCadastro;
	private List<Item> listaItens; // carrinho de compras
	
	@ManagedProperty(value = "#{autenticacaoBean}") // é o nome do managed bean quando estar estanciado com o tomcat
	private AutenticacaoBean autenticacaoBean; // agora aqui consegui puxar o funcionario logado
	
	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}
	
	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}
	
	public List<Venda> getListaVendas() {
		return listaVendas;
	}
	
	public void setListaVendas(List<Venda> listaVendas) {
		this.listaVendas = listaVendas;
	}
	
	public List<Produto> getListaProdutosFiltrados() {
		return listaProdutosFiltrados;
	}
	
	public void setListaProdutosFiltrados(List<Produto> listaProdutosFiltrados) {
		this.listaProdutosFiltrados = listaProdutosFiltrados;
	}
	
	public List<Venda> getListaVendasFiltrados() {
		return listaVendasFiltrados;
	}
	
	public void setListaVendasFiltrados(List<Venda> listaVendasFiltrados) {
		this.listaVendasFiltrados = listaVendasFiltrados;
	}
	
	public Venda getVendaCadastro() {
		if(vendaCadastro == null) {
			vendaCadastro = new Venda();
			vendaCadastro.setValor(new BigDecimal("0.00"));
		}
		return vendaCadastro;
	}
	
	public void setVendaCadastro(Venda vendaCadastro) {
		this.vendaCadastro = vendaCadastro;
	}
	
	public List<Item> getListaItens() {
		if(listaItens == null) {
			listaItens = new ArrayList<>();
		}
		return listaItens;
	}
	
	public void setListaItens(List<Item> listaItens) {
		this.listaItens = listaItens;
	}
	
	public AutenticacaoBean getAutenticacaoBean() {
		return autenticacaoBean;
	}
	
	public void setAutenticacaoBean(AutenticacaoBean autenticacaoBean) {
		this.autenticacaoBean = autenticacaoBean;
	}
	
	public void carregarProdutos() {

		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			listaProdutos = produtoDAO.listar();

		} catch (RuntimeException ex) {
			FacesUtil.adicionarMsgErro("Erro ao tentar listar os produtos: " 
					+ ex.getMessage());
		}
	}
	
	public void adicionar(Produto produto) {
		int posicaoEncontrada = -1;
		
		for(int pos = 0; pos < listaItens.size() && posicaoEncontrada < 0; pos++) {
			Item itemTemp = listaItens.get(pos);
			
			if(itemTemp.getProduto().equals(produto)) {
				posicaoEncontrada = pos;
			}
		}
		
		Item item = new Item();
		item.setProduto(produto);
		
		if(posicaoEncontrada < 0){
		item.setQuantidade(1);
		item.setValor(produto.getPreco());
		listaItens.add(item);
		}else{
			Item itemTemp = listaItens.get(posicaoEncontrada);
			item.setQuantidade(itemTemp.getQuantidade() + 1);
			item.setValor(produto.getPreco().multiply(new BigDecimal (item.getQuantidade())));
			listaItens.set(posicaoEncontrada, item);
		}
		
		vendaCadastro.setValor(vendaCadastro.getValor().add(produto.getPreco()));
	}
	
	public void remover(Item item){
int posicaoEncontrada = -1;
		
		for(int pos = 0; pos < listaItens.size() && posicaoEncontrada < 0; pos++) {
			Item itemTemp = listaItens.get(pos);
			
			if(itemTemp.getProduto().equals(item.getProduto())) {
				posicaoEncontrada = pos;
			}
		}
		
		if(posicaoEncontrada > -1) {
			listaItens.remove(posicaoEncontrada);
			vendaCadastro.setValor(vendaCadastro.getValor().subtract(item.getValor()));
		}
	}
	
	public void carregarDadosVenda(){
		vendaCadastro.setHorario(new Date());
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscarPorCodigo(autenticacaoBean.getFuncionarioLogado()
				.getCodigo());
		vendaCadastro.setFuncionario(funcionario);
	}
	
	public void carregarPesquisaVenda() {

		try {
			VendaDAO vendaDAO = new VendaDAO();
			listaVendas = vendaDAO.listar();

		} catch (RuntimeException ex) {
			FacesUtil.adicionarMsgErro("Erro ao tentar listar as vendas: " 
					+ ex.getMessage());
		}
	}
	
	public void salvar(){
		try {
			vendaCadastro = new Venda();
			vendaCadastro.setValor(new BigDecimal("0.00"));
			
			listaItens = new ArrayList<Item>();
			
			FacesUtil.adicionarMsgInfo("Venda salva com sucesso");
		} catch (RuntimeException ex) {
		FacesUtil.adicionarMsgErro("Erro ao tentar salvar a venda: " 
				+ ex.getMessage());
		}
		
	}
}

