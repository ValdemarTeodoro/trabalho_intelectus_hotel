package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.Produto;
import br.com.faculdadedelta.util.Conexao;

public class ProdutoDao {
	public void incluir(Produto produto) throws ClassNotFoundException, SQLException {
		Connection  conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO produtos(nome_produto, valor) VALUES (?, ?)";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, produto.getNomeProduto().trim());
			ps.setDouble(2, produto.getValor());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		} finally {
			Conexao
			.fecharConexao(ps, conn, null);
		}
	}
	public void alterar(Produto produto) throws ClassNotFoundException, SQLException {
		Connection  conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE produtos SET nome_produto=?, valor=? WHERE id_produto=?";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, produto.getNomeProduto().trim());
			ps.setDouble(2, produto.getValor());
			ps.setLong(3, produto.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		} finally {
			Conexao
			.fecharConexao(ps, conn, null);
		}
	}
	public void excluir(Produto produto) throws ClassNotFoundException, SQLException {
		Connection  conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM produtos WHERE id_produto=?";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, produto.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		} finally {
			Conexao
			.fecharConexao(ps, conn, null);
		}
	}
	public Produto popularProduto(ResultSet rs) throws SQLException{
		Produto produto = new Produto();
		produto.setId(rs.getLong("id_produto"));
		produto.setNomeProduto(rs.getString("nome_produto").trim());
		produto.setValor(rs.getDouble("valor"));
		return produto;
	}
	public List<Produto> lista() throws ClassNotFoundException, SQLException{
		List<Produto> listaRetorno = new ArrayList<>();
		Connection  conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id_produto, nome_produto, valor FROM produtos";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = null;
		try {
			rs = ps.executeQuery();
			while(rs.next()) {
				listaRetorno.add(popularProduto(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException();
		}finally {
			Conexao.fecharConexao(ps, conn, rs);
		}
		return listaRetorno;
	}
	public Produto pesquisarPorId(Long id) throws ClassNotFoundException, SQLException {
		Produto retorno = new Produto();
		Connection  conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id_produto, nome_produto, valor FROM produtos WHERE id_produto=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = null;
		try {
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				retorno = popularProduto(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException();
		}finally {
			Conexao.fecharConexao(ps, conn, rs);
		}
		return retorno;
	}
}
