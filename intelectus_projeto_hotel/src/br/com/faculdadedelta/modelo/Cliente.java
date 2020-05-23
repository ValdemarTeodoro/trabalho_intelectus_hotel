package br.com.faculdadedelta.modelo;

import java.net.URL;
import java.util.Date;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Cliente {
	private Long id;
	private String nome;
	private String sobrenome;
	private Date dataNascimento;
	private String rg;
	private String cpf;
	private String telefone;
	private String email;
	private String cep="";
	private String uf="";
	private String cidade="";
	private String bairro="";
	private String tipoLogradouro="";
	private String logradouro="";
	private String complemento="";
	private int resultado = 0;

	public Cliente() {
		super();
	}

	public Cliente(Long id, String nome, String sobrenome, Date dataNascimento, String rg, String cpf, String telefone, String email, String cep,
			String uf, String cidade, String bairro, String tipoLogradouro, String logradouro, String complemento,
			int resultado) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.dataNascimento = dataNascimento;
		this.rg = rg;
		this.cpf = cpf;
		this.telefone = telefone;
		this.email = email;
		this.cep = cep;
		this.uf = uf;
		this.cidade = cidade;
		this.bairro = bairro;
		this.tipoLogradouro = tipoLogradouro;
		this.logradouro = logradouro;
		this.complemento = complemento;
		this.resultado = resultado;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public int getResultado() {
		return resultado;
	}

	public void setResultado(int resultado) {
		this.resultado = resultado;
	}


	@SuppressWarnings("rawtypes")
    public Cliente(String cep) {
 
        try {
            URL url = new URL(
                    "http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep
                            + "&formato=xml");
 
            Document document = getDocumento(url);
 
            Element root = document.getRootElement();
 
            for (Iterator i = root.elementIterator(); i.hasNext();) {
                Element element = (Element) i.next();
                
                if(element.getQualifiedName().equals("cep"))
                	setCep(element.getText());
 
                if (element.getQualifiedName().equals("uf"))
                    setUf(element.getText());
 
                if (element.getQualifiedName().equals("cidade"))
                    setCidade(element.getText());
 
                if (element.getQualifiedName().equals("bairro"))
                    setBairro(element.getText());
 
                if (element.getQualifiedName().equals("tipo_logradouro"))
                    setTipoLogradouro(element.getText());
 
                if (element.getQualifiedName().equals("logradouro"))
                    setLogradouro(element.getText());
 
                if (element.getQualifiedName().equals("resultado"))
                    setResultado(Integer.parseInt(element.getText()));
                
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
 
    public Document getDocumento(URL url) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(url);
 
        return document;
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
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
