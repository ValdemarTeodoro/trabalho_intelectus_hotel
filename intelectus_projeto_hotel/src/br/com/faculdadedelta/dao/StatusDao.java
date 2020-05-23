package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import br.com.faculdadedelta.modelo.Status;
import br.com.faculdadedelta.util.Conexao;

public class StatusDao {
	public List<Status> lista() throws ClassNotFoundException, SQLException{
		List<Status> listaRetorno = new ArrayList<>();
		Connection  conn = Conexao.conectarNoBancoDeDados();
		String sql = "Select id_status, descricao FROM status";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = null;
		try {
			rs = ps.executeQuery();
			while(rs.next()) {
				listaRetorno.add(popularStatus(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException();
		}finally {
			Conexao.fecharConexao(ps, conn, rs);
		}
		return listaRetorno;
	}
	public Status pesquisarPorId(Long id) throws ClassNotFoundException, SQLException {
		Status retorno = new Status();
		Connection  conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id_status, descricao FROM status WHERE id_status=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = null;
		try {
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				retorno = popularStatus(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException();
		}finally {
			Conexao.fecharConexao(ps, conn, rs);
		}
		return retorno;
	}
	public Status popularStatus(ResultSet rs) throws SQLException{
		Status status = new Status();
		status.setId(rs.getLong("id_status"));
		status.setDescricao(rs.getString("descricao").trim());
		return status;
	}
}
