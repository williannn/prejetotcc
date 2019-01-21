package br.com.mercado.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.mercado.dao.FabricanteDAO;
import br.com.mercado.dao.ProdutoDAO;
import br.com.mercado.domain.Fabricante;
import br.com.mercado.domain.Produto;
import br.com.mercado.util.FacesUtil;


@ManagedBean
@ViewScoped
public class ProdutoBean {
	private Produto produtoCadastro;
	
	private List<Produto> listaProdutos;
	private List<Produto> listaProdutosFiltrados;
	
	private String acao;
	private Long codigo;
	
	private List<Fabricante> listaFabricantes;
	
	public Produto getProdutoCadastro() {
		return produtoCadastro;
	}
	public void setProdutoCadastro(Produto produtoCadastro) {
		this.produtoCadastro = produtoCadastro;
	}
	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}
	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}
	public List<Produto> getListaProdutosFiltrados() {
		return listaProdutosFiltrados;
	}
	public void setListaProdutosFiltrados(
			List<Produto> listaProdutosFiltrados) {
		this.listaProdutosFiltrados = listaProdutosFiltrados;
	}
	public String getAcao() {
		return acao;
	}
	public void setAcao(String acao) {
		this.acao = acao;
	}
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public List<Fabricante> getListaFabricantes() {
		return listaFabricantes;
	}
	
	public void setListaFabricantes(List<Fabricante> listaFabricantes) {
		this.listaFabricantes = listaFabricantes;
	}
	
	public void novo() {
		produtoCadastro = new Produto();
	}
	
	public void salvar() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.salvar(produtoCadastro);

			produtoCadastro = new Produto();

			FacesUtil.adicionarMsgInfo("Produto Salvo com Sucesso");
		} catch (RuntimeException ex) {
			FacesUtil
			.adicionarMsgErro("Erro ao tentar salvar os dados do produto: "
					+ ex.getMessage());
		}
	}
	
	public void carregarPesquisa() {

		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			listaProdutos = produtoDAO.listar();

		} catch (RuntimeException ex) {
			FacesUtil.adicionarMsgErro("Erro ao tentar listar os produtos: " 
					+ ex.getMessage());
		}
	}
	
	public void carregarCadastro() {
		try {
			if (codigo != null) {
				ProdutoDAO produtoDAO = new ProdutoDAO();
				produtoCadastro = produtoDAO.buscarPorCodigo(codigo);
			} else {
				produtoCadastro = new Produto();
			}
			
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			listaFabricantes = fabricanteDAO.listar();
		} catch (RuntimeException ex) {
			FacesUtil.adicionarMsgErro("Erro ao tentar obter os dados do produto: " 
					+ ex.getMessage());
		}
	}
	
	public void excluir() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.exluir(produtoCadastro);

			FacesUtil.adicionarMsgInfo("Produto Removido com Sucesso");
		} catch (RuntimeException ex) {
			FacesUtil
				.adicionarMsgErro("Erro ao tentar remover um Produto: " 
						+ ex.getMessage());
		}
	}
	
	public void editar() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.editar(produtoCadastro);

			FacesUtil.adicionarMsgInfo("Produto Editado com sucesso");
		} catch (RuntimeException ex) {
			FacesUtil.adicionarMsgErro("Erro ao tentar editar os dados do produto: " + ex.getMessage());
		}
	}
	
	public void buscarPorCodigo(){
		try {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.buscarPorCodigo(codigo);

		FacesUtil.adicionarMsgInfo("Produto encontrado com sucesso");
	} catch (RuntimeException ex) {
		FacesUtil.adicionarMsgErro("Erro ao tentar encontrar o produto: " + ex.getMessage());
	}
		
	}
	
}
