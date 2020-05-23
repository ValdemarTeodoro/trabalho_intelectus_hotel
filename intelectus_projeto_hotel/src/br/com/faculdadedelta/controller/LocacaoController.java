package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.LocacaoDao;
import br.com.faculdadedelta.modelo.Cliente;
import br.com.faculdadedelta.modelo.Locacao;
import br.com.faculdadedelta.modelo.Quarto;
import br.com.faculdadedelta.modelo.Status;

@ManagedBean
@SessionScoped
public class LocacaoController {
	private String CADASTRO_LOCACAO = "cadastroLocacao.xhtml";
	
	private Locacao locacao = new Locacao();
	private LocacaoDao dao = new LocacaoDao();
	private Cliente clienteSelecionado = new Cliente();
	private Quarto quartoSelecionado = new Quarto();
	private Status statusSelecionado = new Status();

	public Locacao getLocacao() {
		return locacao;
	}

	public void setLocacao(Locacao locacao) {
		this.locacao = locacao;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public Quarto getQuartoSelecionado() {
		return quartoSelecionado;
	}

	public void setQuartoSelecionado(Quarto quartoSelecionado) {
		this.quartoSelecionado = quartoSelecionado;
	}

	public Status getStatusSelecionado() {
		return statusSelecionado;
	}

	public void setStatusSelecionado(Status statusSelecionado) {
		this.statusSelecionado = statusSelecionado;
	}

	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void limparCampos() {
		clienteSelecionado = new Cliente();
		quartoSelecionado = new Quarto();
		statusSelecionado = new Status();
		locacao = new Locacao();
	}
	public String salvar() {
		try {
			if(locacao.getId()==null) {
				if(locacao.getDataPrevisaoSaida().after(locacao.getDataEntrada())) {
					locacao.setCliente(clienteSelecionado);
					locacao.setStatus(statusSelecionado);
					locacao.setQuarto(quartoSelecionado);
					dao.incluir(locacao);
					exibirMensagem("Inclusão realizada com sucesso!");
					limparCampos();
				}else {
					exibirMensagem("A data de previsão de saída tem que ser após data de entrada");
				}
			}else {
				if(locacao.getDataPrevisaoSaida().after(locacao.getDataEntrada())) {
					locacao.setCliente(clienteSelecionado);
					locacao.setStatus(statusSelecionado);
					locacao.setQuarto(quartoSelecionado);
					dao.alterar(locacao);
					exibirMensagem("Alteração realizada com sucesso");
					limparCampos();
				}else {
					exibirMensagem("A data de previsão de saída tem que ser após data de entrada");
				}
			}	
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro. Não pode cadastrar quarto alocado ou reservado!");
		}
		return CADASTRO_LOCACAO;
	}
	public String editar() {
		quartoSelecionado = locacao.getQuarto();
		clienteSelecionado = locacao.getCliente();
		statusSelecionado = locacao.getStatus();
		
		return CADASTRO_LOCACAO;
		
	}
	public String alugar() {
		try {
				locacao.setCliente(clienteSelecionado);
				locacao.setStatus(statusSelecionado);
				locacao.setQuarto(quartoSelecionado);
				dao.alugar(locacao);
				exibirMensagem("Alteração para alugado, realizado com sucesso");
				limparCampos();
			
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return CADASTRO_LOCACAO;
	}
	public String excluir() {
		try {
		dao.excluir(locacao);
		exibirMensagem("Exclusão realizada com sucesso");
		limparCampos();
	
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
	}
		return CADASTRO_LOCACAO;
	}
	public List<Locacao> getListaAlugados(){
		List<Locacao> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.listaAlugados();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return listaRetorno;
	}
	public List<Locacao> getListaReservados(){
		List<Locacao> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.listaReservados();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return listaRetorno;
	}
}
