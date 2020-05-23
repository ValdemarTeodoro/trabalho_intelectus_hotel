package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.ClienteDao;
import br.com.faculdadedelta.modelo.Cliente;
@ManagedBean
@SessionScoped
public class ClienteController {
	private static final long serialVersionUID = 1L;
	 
	private String CADASTO_CLIENTE = "cadastroCliente.xhtml";
	private String LISTA_CLIENTE= "listaCliente.xhtml";
	
	private Cliente cliente = new Cliente();
	private ClienteDao dao = new ClienteDao();
    private String cep;
    

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void encontraCEP() {
        Cliente cepWebService = new Cliente(getCep());
 
        if (cepWebService.getResultado() == 1) {
            cliente.setTipoLogradouro(cepWebService.getTipoLogradouro());
            cliente.setLogradouro(cepWebService.getLogradouro());
            cliente.setUf(cepWebService.getUf());
            cliente.setCidade(cepWebService.getCidade());
            cliente.setBairro(cepWebService.getBairro());
            cliente.setCep(cep);
        } else {
 
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Servidor não está respondendo",
                            "Servidor não está respondendo"));
        }
    }

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void limparCampos() {
		cliente = new Cliente();
		cliente = new Cliente(cep = "");
	}
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
    public String salvar() {
    	try {
			if(cliente.getId()==null) {
				if(dao.pequisarPorRG(cliente.getRg())==null && dao.pequisarPorCpf(cliente.getCpf())==null) {
					dao.incluir(cliente);
					exibirMensagem("Inclusão Realizada com sucesso!");
					limparCampos();
				}else {
					exibirMensagem("O RG ou o CPF digitado já foram registrado!");
				}
			}else {
				if(dao.pequisarPorRG(cliente.getRg())==null && dao.pequisarPorCpf(cliente.getCpf())==null) {
					dao.alterar(cliente);
					exibirMensagem("Alteração realizada com sucesso!");
					limparCampos();
				}else {
					exibirMensagem("O RG ou o CPF digitado já foram registrado!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("O CPF digitado já foi registrado!");
		}
		return CADASTO_CLIENTE;
    }
    public String excluir() {
    	try {
				dao.excluir(cliente);
				exibirMensagem("Exclusão Realizada com sucesso!");
				limparCampos();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação! " + e.getMessage());
		}
		return LISTA_CLIENTE;
    }
    public String editar() {
    	cep = cliente.getCep();
		return CADASTO_CLIENTE;
    }
    public List<Cliente> getLista(){
    	List<Cliente> listaRetorno = new ArrayList<>();
    	try {
			listaRetorno = dao.lista();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação! " + e.getMessage());
		}
		return listaRetorno;
    }
}

