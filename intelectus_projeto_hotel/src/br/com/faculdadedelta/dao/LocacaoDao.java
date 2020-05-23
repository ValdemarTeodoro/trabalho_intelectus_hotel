package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.Cliente;
import br.com.faculdadedelta.modelo.Locacao;
import br.com.faculdadedelta.modelo.Quarto;
import br.com.faculdadedelta.modelo.Status;
import br.com.faculdadedelta.util.Conexao;

public class LocacaoDao {
	public void incluir(Locacao locacao) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO locacoes(id_status, id_cliente, id_quarto, data_entrada, data_previsao_saida) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, locacao.getStatus().getId());
			ps.setLong(2, locacao.getCliente().getId());
			ps.setLong(3, locacao.getQuarto().getId());
			ps.setDate(4, new java.sql.Date(locacao.getDataEntrada().getTime()));
			ps.setDate(5, new java.sql.Date(locacao.getDataPrevisaoSaida().getTime()));
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}
	}
	public void alterar(Locacao locacao) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE locacoes SET id_status=?, id_cliente=?, id_quarto=?, data_entrada=?, data_previsao_saida=? WHERE id_locacao=?";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, locacao.getStatus().getId());
			ps.setLong(2, locacao.getCliente().getId());
			ps.setLong(3, locacao.getQuarto().getId());
			ps.setDate(4, new java.sql.Date(locacao.getDataEntrada().getTime()));
			ps.setDate(5, new java.sql.Date(locacao.getDataPrevisaoSaida().getTime()));
			ps.setLong(6, locacao.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}
	}
	public void alugar(Locacao locacao) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE locacoes SET id_status=2 WHERE id_locacao=?";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, locacao.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}
	}
	public void excluir(Locacao locacao) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "Delete from locacoes where id_locacao=?";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, locacao.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}
	}
	public Locacao popularLocacao(ResultSet rs) throws SQLException{
		Locacao locacao = new Locacao();
		locacao.setId(rs.getLong("idLocacao"));
		locacao.setDataEntrada(rs.getDate("dataEntrada"));
		locacao.setDataPrevisaoSaida(rs.getDate("dataPreviSaida"));
		
		Cliente cliente = new Cliente();
		cliente.setId(rs.getLong("idCliente"));
		cliente.setNome(rs.getString("nomeCliente").trim());
		cliente.setCpf(rs.getString("cpfCliente").trim());
		locacao.setCliente(cliente);
		
		Status status = new Status();
		status.setId(rs.getLong("idStatus"));
		status.setDescricao(rs.getString("descricaoStatus").trim());
		locacao.setStatus(status);
		
		Quarto quarto = new Quarto();
		quarto.setId(rs.getLong("idQuarto"));
		quarto.setNumeroQuarto(rs.getString("numeroQuarto").trim());
		quarto.setTipoQuarto(rs.getString("tipoQuarto").trim());
		quarto.setValorQuarto(rs.getDouble("valorQuarto"));
		locacao.setQuarto(quarto);
		return locacao;
	}
	public List<Locacao> listaReservados() throws ClassNotFoundException, SQLException{
		List<Locacao> listaRetorno = new ArrayList<>();
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT l.id_locacao AS idLocacao, c.id_cliente AS idCliente, s.id_status AS idStatus, q.id_quarto AS idQuarto, l.data_entrada AS dataEntrada, l.data_previsao_saida AS dataPreviSaida, s.descricao AS descricaoStatus, c.nome AS nomeCliente, c.cpf As cpfCliente, q.numero_quarto AS numeroQuarto, q.tipo_de_quarto As tipoQuarto, q.valor_quarto As valorQuarto FROM locacoes l inner join status s on l.id_status = s.id_status inner join clientes c on l.id_cliente = c.id_cliente inner join quartos q on l.id_quarto = q.id_quarto where l.id_status = '1'";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = null;
		try {
			rs = ps.executeQuery();
			while(rs.next()) {
				listaRetorno.add(popularLocacao(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}
		return listaRetorno;
	}
	public List<Locacao> listaAlugados() throws ClassNotFoundException, SQLException{
		List<Locacao> listaRetorno = new ArrayList<>();
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT l.id_locacao AS idLocacao, c.id_cliente AS idCliente, s.id_status AS idStatus, q.id_quarto AS idQuarto, l.data_entrada AS dataEntrada, l.data_previsao_saida AS dataPreviSaida, s.descricao AS descricaoStatus, c.nome AS nomeCliente, c.cpf As cpfCliente, q.numero_quarto AS numeroQuarto, q.tipo_de_quarto As tipoQuarto, q.valor_quarto As valorQuarto FROM locacoes l inner join status s on l.id_status = s.id_status inner join clientes c on l.id_cliente = c.id_cliente inner join quartos q on l.id_quarto = q.id_quarto where l.id_status = 2";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = null;
		try {
			rs = ps.executeQuery();
			while(rs.next()) {
				listaRetorno.add(popularLocacao(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}
		return listaRetorno;
	}
	public Locacao pesquisarPorId(Long id) throws Exception{
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id_locacao, id_status, id_cliente, id_quarto, data_entrada, data_previsao_saida FROM locacoes where id_locacao=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = null;
		Locacao retorno = new Locacao();
		try {
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				retorno = popularLocacao(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException();
		}
		return retorno;
	}
}
