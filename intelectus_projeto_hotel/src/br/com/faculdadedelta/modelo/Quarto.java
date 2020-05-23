package br.com.faculdadedelta.modelo;

public class Quarto {
	private Long id;
	private String numeroQuarto;
	private String tipoQuarto;
	private String categoriQuarto;
	private double ValorQuarto;
	public Quarto() {
		super();
	}
	public Quarto(Long id, String numeroQuarto, String tipoQuarto, String categoriQuarto, double valorQuarto) {
		super();
		this.id = id;
		this.numeroQuarto = numeroQuarto;
		this.tipoQuarto = tipoQuarto;
		this.categoriQuarto = categoriQuarto;
		ValorQuarto = valorQuarto;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumeroQuarto() {
		return numeroQuarto;
	}
	public void setNumeroQuarto(String numeroQuarto) {
		this.numeroQuarto = numeroQuarto;
	}
	public String getTipoQuarto() {
		return tipoQuarto;
	}
	public void setTipoQuarto(String tipoQuarto) {
		this.tipoQuarto = tipoQuarto;
	}
	public String getCategoriQuarto() {
		return categoriQuarto;
	}
	public void setCategoriQuarto(String categoriQuarto) {
		this.categoriQuarto = categoriQuarto;
	}
	public double getValorQuarto() {
		return ValorQuarto;
	}
	public void setValorQuarto(double valorQuarto) {
		ValorQuarto = valorQuarto;
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
		Quarto other = (Quarto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
