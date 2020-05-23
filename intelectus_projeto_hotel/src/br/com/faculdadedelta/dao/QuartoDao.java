package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.Quarto;
import br.com.faculdadedelta.util.Conexao;

public class QuartoDao {
	
	public List<Quarto> lista() throws ClassNotFoundException, SQLException{
		List<Quarto> listaRetorno = new ArrayList<>();
		Connection  conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id_quarto, numero_quarto, tipo_de_quarto, valor_quarto FROM quartos";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = null;
		try {
			rs = ps.executeQuery();
			while(rs.next()) {
				listaRetorno.add(popularQuarto(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException();
		}finally {
			Conexao.fecharConexao(ps, conn, rs);
		}
		return listaRetorno;
	}
	public Quarto pesquisarPorId(Long id) throws ClassNotFoundException, SQLException {
		Quarto retorno = new Quarto();
		Connection  conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id_quarto, numero_quarto, tipo_de_quarto, valor_quarto FROM quartos where id_quarto=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = null;
		try {
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				retorno = popularQuarto(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException();
		}finally {
			Conexao.fecharConexao(ps, conn, rs);
		}
		return retorno;
	}
	public Quarto popularQuarto(ResultSet rs) throws SQLException{
		Quarto quarto = new Quarto();
		quarto.setId(rs.getLong("id_quarto"));
		quarto.setNumeroQuarto(rs.getString("numero_quarto").trim());
		quarto.setTipoQuarto(rs.getString("tipo_de_quarto").trim());
		quarto.setValorQuarto(rs.getDouble("valor_quarto"));
		return quarto;
	}
}
