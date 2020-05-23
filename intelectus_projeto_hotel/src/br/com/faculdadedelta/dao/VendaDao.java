package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.faculdadedelta.modelo.Cliente;
import br.com.faculdadedelta.modelo.Produto;
import br.com.faculdadedelta.modelo.Venda;
import br.com.faculdadedelta.util.Conexao;

public class VendaDao {
	public void incluir(Venda venda) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO vendas(data_venda, id_cliente, id_produto, quantidade, valor_total) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setDate(1, new java.sql.Date(new Date().getTime()));
			ps.setLong(2, venda.getCliente().getId());
			ps.setLong(3, venda.getProduto().getId());
			ps.setInt(4, venda.getQuantidade());
			ps.setDouble(5, venda.getValorTotal());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void alterar(Venda venda) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE vendas SET data_venda=?, id_cliente=?, id_produto=?, quantidade=?, valor_total=? WHERE id_venda=?";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setDate(1, new java.sql.Date(new Date().getTime()));
			ps.setLong(2, venda.getCliente().getId());
			ps.setLong(3, venda.getProduto().getId());
			ps.setInt(4, venda.getQuantidade());
			ps.setDouble(5, venda.getValorTotal());
			ps.setLong(6, venda.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void excluir(Venda venda) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM vendas WHERE id_venda=?";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, venda.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public Venda popularVenda(ResultSet rs) throws SQLException {
		Venda venda = new Venda();
		venda.setId(rs.getLong("vendaId"));
		venda.setDataVenda(rs.getDate("vendaData"));
		venda.setQuantidade(rs.getInt("quantidade"));
		venda.setValorTotal(rs.getDouble("valorTotal"));
		
		Cliente cliente = new Cliente();
		cliente.setId(rs.getLong("clienteId"));
		cliente.setNome(rs.getString("nomeCliente").trim());
		cliente.setSobrenome(rs.getString("sobrenomeCliente").trim());
		cliente.setCpf(rs.getString("cpfCliente").trim());
		venda.setCliente(cliente);
		
		Produto produto = new Produto();
		produto.setId(rs.getLong("produtoId"));
		produto.setNomeProduto(rs.getString("nomeProduto").trim());
		produto.setValor(rs.getDouble("valorProduto"));
		venda.setProduto(produto);
		
		return venda;
	}
	public List<Venda> lista() throws ClassNotFoundException, SQLException{
		List<Venda> listaRetorno = new ArrayList<>();
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT v.id_venda as vendaId, v.data_venda as vendaData, c.id_cliente as clienteId, c.nome as nomeCliente, c.sobrenome as sobrenomeCliente, c.cpf as cpfCliente, p.id_produto as produtoId, p.nome_produto as nomeProduto, p.valor as valorProduto, v.quantidade as quantidade, v.valor_total as valorTotal FROM vendas v inner join clientes c on v.id_cliente = c.id_cliente inner join produtos p on v.id_produto = p.id_produto";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = null;
		try {
			rs = ps.executeQuery();
			while(rs.next()) {
				listaRetorno.add(popularVenda(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
		return listaRetorno;
	}
	public Venda pesquisarPorId(Long id) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id_venda, data_venda, id_cliente, id_produto, quantidade, valor_total FROM vendas where id_venda =?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = null;
		Venda retorno = new Venda();
		try {
			ps.setLong(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				retorno = popularVenda(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
		return retorno;
	}
}
