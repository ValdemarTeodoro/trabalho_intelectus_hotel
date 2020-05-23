package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.ProdutoDao;
import br.com.faculdadedelta.modelo.Produto;

@ManagedBean
@SessionScoped
public class ProdutoController {
	private String CADASTRO_PRODUTO = "cadastroProduto.xhtml";
	private String LISTA_PRODUTO = "listaProduto.xhtml";
	
	private Produto produto = new Produto();
	private ProdutoDao dao = new ProdutoDao();
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public void limparCampos() {
		produto = new Produto();
	}
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	public String salvar() {
		try {
			if(produto.getId()==null) {
				dao.incluir(produto);
				exibirMensagem("Inclusão realizada com sucesso!");
				limparCampos();
			}else {
				dao.alterar(produto);
				exibirMensagem("Alteração realizada com sucesso!");
				limparCampos();
			}
			} catch (Exception e) {
				e.printStackTrace();
				exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde!");
			}
		return CADASTRO_PRODUTO;
	}
	public String editar() {
		return CADASTRO_PRODUTO;
	}
	public String excluir() {
	try {
			dao.excluir(produto);
			exibirMensagem("Exclusão realizada com sucesso!");
			limparCampos();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde!");
		}
	return LISTA_PRODUTO;
	}
	public List<Produto> getLista(){
		List<Produto> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.lista();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaRetorno;
	}
}
