package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.Cliente;
import br.com.faculdadedelta.util.Conexao;

public class ClienteDao {
	public void incluir(Cliente cliente) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO clientes(nome, sobrenome, data_nascimento, rg, cpf, telefone, email, cep, uf, cidade, bairro, tipo_logradouro, logradouro, complemento) VALUES (?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, cliente.getNome().trim());
			ps.setString(2, cliente.getSobrenome().trim());
			ps.setDate(3, new java.sql.Date(cliente.getDataNascimento().getTime()));
			ps.setString(4, cliente.getRg().trim());
			ps.setString(5, cliente.getCpf().trim());
			ps.setString(6, cliente.getTelefone().trim());
			ps.setString(7, cliente.getEmail().trim());
			ps.setString(8, cliente.getCep().trim());
			ps.setString(9, cliente.getUf().trim());
			ps.setString(10, cliente.getCidade().trim());
			ps.setString(11, cliente.getBairro().trim());
			ps.setString(12, cliente.getTipoLogradouro().trim());
			ps.setString(13, cliente.getLogradouro().trim());
			ps.setString(14, cliente.getComplemento().trim());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void alterar(Cliente cliente) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE clientes SET nome=?, sobrenome=?, data_nascimento=?, rg=?, cpf=?, telefone=?, email=?, cep=?, uf=?, cidade=?, bairro=?, tipo_logradouro=?, logradouro=?, complemento=? WHERE id_cliente=?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, cliente.getNome().trim());
			ps.setString(2, cliente.getSobrenome().trim());
			ps.setDate(3, new java.sql.Date(cliente.getDataNascimento().getTime()));
			ps.setString(4, cliente.getRg().trim());
			ps.setString(5, cliente.getCpf().trim());
			ps.setString(6, cliente.getTelefone().trim());
			ps.setString(7, cliente.getEmail().trim());
			ps.setString(8, cliente.getCep().trim());
			ps.setString(9, cliente.getUf().trim());
			ps.setString(10, cliente.getCidade().trim());
			ps.setString(11, cliente.getBairro().trim());
			ps.setString(12, cliente.getTipoLogradouro().trim());
			ps.setString(13, cliente.getLogradouro().trim());
			ps.setString(14, cliente.getComplemento().trim());
			ps.setLong(15, cliente.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void excluir(Cliente cliente) throws ClassNotFoundException, SQLException {
		Connection  conn = Conexao.conectarNoBancoDeDados();
		String sql ="DELETE FROM clientes WHERE id_cliente=?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, cliente.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public List<Cliente> lista() throws ClassNotFoundException, SQLException{
		Connection  conn = Conexao.conectarNoBancoDeDados();
		String sql ="SELECT id_cliente, nome, sobrenome, data_nascimento, rg, cpf, telefone, email, cep, uf, cidade, bairro, tipo_logradouro, logradouro, complemento FROM clientes";
		PreparedStatement ps = conn.prepareStatement(sql);
		List<Cliente> listaRetorno = new ArrayList<>();
		ResultSet rs = null;
		try {
			rs = ps.executeQuery();
			while(rs.next()) {
				listaRetorno.add(popularCliente(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, rs);
		}
		return listaRetorno;
	}
	public Cliente pequisarPorId(Long id) throws SQLException, ClassNotFoundException {
		Connection  conn = Conexao.conectarNoBancoDeDados();
		String sql ="SELECT id_cliente, nome, sobrenome, data_nascimento, rg, cpf, telefone, email, cep, uf, cidade, bairro, tipo_logradouro, logradouro, complemento FROM clientes WHERE id_cliente=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		Cliente retorno = new Cliente();
		ResultSet rs = null;
		try {
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				retorno = popularCliente(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, rs);
		}
		return retorno;
	}
	public Cliente popularCliente(ResultSet rs) throws SQLException {
		Cliente cliente = new Cliente();
		
		cliente.setId(rs.getLong("id_cliente"));
		cliente.setNome(rs.getString("nome").trim());
		cliente.setSobrenome(rs.getString("sobrenome").trim());
		cliente.setDataNascimento(rs.getDate("data_nascimento"));
		cliente.setRg(rs.getString("rg").trim());
		cliente.setCpf(rs.getString("cpf").trim());
		cliente.setTelefone(rs.getString("telefone").trim());
		cliente.setEmail(rs.getString("email").trim());
		cliente.setCep(rs.getString("cep").trim());
		cliente.setUf(rs.getString("uf").trim());
		cliente.setCidade(rs.getString("cidade").trim());
		cliente.setBairro(rs.getString("bairro").trim());
		cliente.setTipoLogradouro(rs.getString("tipo_logradouro").trim());
		cliente.setLogradouro(rs.getString("logradouro").trim());
		cliente.setComplemento(rs.getString("complemento").trim());
		return cliente;
	}
	public Cliente pequisarPorRG(String rg) throws SQLException, ClassNotFoundException {
		Connection  conn = Conexao.conectarNoBancoDeDados();
		String sql ="SELECT rg FROM clientes WHERE rg=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = null;
		Cliente cliente = null;
		try {
			ps.setString(1, rg);
			rs = ps.executeQuery();
			if(rs.next()) {
				cliente = new Cliente();
				cliente.setRg(rg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, rs);
		}
		return cliente;
	}
	public Cliente pequisarPorCpf(String cpf) throws SQLException, ClassNotFoundException {
		Connection  conn = Conexao.conectarNoBancoDeDados();
		String sql ="SELECT cpf FROM clientes WHERE cpf=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = null;
		Cliente cliente = null;
		try {
			ps.setString(1, cpf);
			rs = ps.executeQuery();
			if(rs.next()) {
				cliente = new Cliente();
				cliente.setRg(cpf);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, rs);
		}
		return cliente;
	}
}
