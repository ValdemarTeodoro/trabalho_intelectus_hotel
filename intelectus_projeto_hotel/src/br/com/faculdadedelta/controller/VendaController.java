package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.VendaDao;
import br.com.faculdadedelta.modelo.Cliente;
import br.com.faculdadedelta.modelo.Produto;
import br.com.faculdadedelta.modelo.Venda;
@ManagedBean
@SessionScoped
public class VendaController {
	private String CADASTRO_VENDA = "vendaProduto.xhtml";
	private String mensagens = "Erro ao realizar a operação, tente novamente mais tarde ! ";
	
	private Venda venda = new Venda();
	private VendaDao dao = new VendaDao();
	private Cliente clienteSelecionado = new Cliente();
	private Produto produtoSelecionado = new Produto();
	
	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

	public void limparCampos() {
		venda = new Venda();
		clienteSelecionado = new Cliente();
		produtoSelecionado = new Produto();
	}
	
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);	
	}
	
	public String salvar() {
		try {
			if(venda.getId()==null) {
				venda.setCliente(clienteSelecionado);
				venda.setProduto(produtoSelecionado);
				dao.incluir(venda);
				exibirMensagem("Inclusão realizada com sucesso!");
				limparCampos();
			}else {
				venda.setCliente(clienteSelecionado);
				venda.setProduto(produtoSelecionado);
				dao.alterar(venda);
				exibirMensagem("Alteração realizada com sucesso!");
				limparCampos();
			}
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem(mensagens + e.getMessage());
		}
		return CADASTRO_VENDA;
	}
	public String editar() {
		clienteSelecionado = venda.getCliente();
		produtoSelecionado = venda.getProduto();
		return CADASTRO_VENDA;
	}
	public String excluir() {
		try {
			dao.excluir(venda);
			exibirMensagem("Exclusão realizada com sucesso!");
			limparCampos();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem(mensagens + e.getMessage());
		}
		return CADASTRO_VENDA;
	}
	public List<Venda> getLista(){
		List<Venda> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.lista();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem(mensagens + e.getMessage());
		}
		return listaRetorno;
		
	}
}
