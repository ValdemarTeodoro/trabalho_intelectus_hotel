package br.com.faculdadedelta.modelo;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;



@ManagedBean
@SessionScoped
public class Locacao {
	private Long id;
	private int id_cliente;
	private int id_quarto;
	private int id_status;
	private Cliente cliente;
	private Quarto quarto;
	private Status status;
	private Date dataEntrada;
	private Date dataPrevisaoSaida;
	public Locacao() {
		super();
	}
	
	public Locacao(Long id, int id_cliente, int id_quarto, int id_status, Cliente cliente, Quarto quarto, Status status,
			Date dataEntrada, Date dataPrevisaoSaida) {
		super();
		this.id = id;
		this.id_cliente = id_cliente;
		this.id_quarto = id_quarto;
		this.id_status = id_status;
		this.cliente = cliente;
		this.quarto = quarto;
		this.status = status;
		this.dataEntrada = dataEntrada;
		this.dataPrevisaoSaida = dataPrevisaoSaida;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}

	public int getId_quarto() {
		return id_quarto;
	}

	public void setId_quarto(int id_quarto) {
		this.id_quarto = id_quarto;
	}

	public int getId_status() {
		return id_status;
	}

	public void setId_status(int id_status) {
		this.id_status = id_status;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Quarto getQuarto() {
		return quarto;
	}

	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Date getDataPrevisaoSaida() {
		return dataPrevisaoSaida;
	}

	public void setDataPrevisaoSaida(Date dataPrevisaoSaida) {
		this.dataPrevisaoSaida = dataPrevisaoSaida;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Locacao other = (Locacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
