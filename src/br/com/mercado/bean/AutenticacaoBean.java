package br.com.mercado.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.mercado.dao.FuncionarioDAO;
import br.com.mercado.domain.Funcionario;
import br.com.mercado.util.FacesUtil;

@ManagedBean
@SessionScoped
public class AutenticacaoBean {
	private Funcionario funcionarioLogado; // injeção, oq é? é quando eu tenho um objeto que ja estar instanciado e consigo coloca-lo ele em outro objeto
	
	public Funcionario getFuncionarioLogado() {
		if(funcionarioLogado == null) {
			funcionarioLogado = new Funcionario();
		}
		return funcionarioLogado;
	}
	
	public void setFuncionarioLogado(Funcionario funcionarioLogado) {
		this.funcionarioLogado = funcionarioLogado;
	}
	
	public String autenticar(){ // apos autenticar eu tenho um destino
		try{
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioLogado = funcionarioDAO.autenticar(
					funcionarioLogado.getNome(), funcionarioLogado.getSenha());
			
			if(funcionarioLogado == null){
				FacesUtil.adicionarMsgErro("Nome e/ou senha inválidos");
				return null; // quer dizer que eu vou ficar na mesma pagina e não redirecionar um novo destino
			} else{
				FacesUtil
						.adicionarMsgInfo("Usuario autenticado com sucesso");
				return "/pages/principal.xhtml?faces-redirect=true"; // navega para o destino desejado
			}
		} catch (RuntimeException ex) {
			FacesUtil
				.adicionarMsgErro("Erro ao tentar autenticar no sistema: "
					+ ex.getMessage());
			return null;
		}
	}
	
	public String sair(){ // metodos que retornam string ele retorna o um destino
		funcionarioLogado = null;
		return "/pages/autenticacao.xhtml?faces-redirect=true";
	}
	
}
