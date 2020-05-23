package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.faculdadedelta.dao.QuartoDao;
import br.com.faculdadedelta.modelo.Quarto;

@ManagedBean
@SessionScoped
public class QuartoController {
	
	private Quarto quarto = new Quarto();
	private QuartoDao dao = new QuartoDao();
	
	public Quarto getQuarto() {
		return quarto;
	}
	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}

	public void limparCampos() {
		quarto = new Quarto();
	}
	
	
	public List<Quarto> getLista(){
		List<Quarto> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.lista();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaRetorno;
	}
}
