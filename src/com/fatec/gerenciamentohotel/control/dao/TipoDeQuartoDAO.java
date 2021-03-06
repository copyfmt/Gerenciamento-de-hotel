package com.fatec.gerenciamentohotel.control.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fatec.gerenciamentohotel.control.connection.ConnectionDB;
import com.fatec.gerenciamentohotel.control.dao.exceptions.DAOException;
import com.fatec.gerenciamentohotel.entity.TipoDeQuarto;
import com.fatec.gerenciamentohotel.entity.dao.IObjectDAO;

public class TipoDeQuartoDAO implements IObjectDAO<TipoDeQuarto, String> {

	@Override
	public void insert(TipoDeQuarto t) throws DAOException {
		try {
			Connection con = ConnectionDB.getInstance().getConnection();
			PreparedStatement pstmt;
			// omitir o ID já que no banco ele é AUTO_INCREMENT
			pstmt = con.prepareStatement(" Insert into tipo_quarto "
					+ " (tipo_quarto, preco, qtd_adultos, qtd_criancas) values  (?, ?, ?, ?) ");
			pstmt.setString(1, t.getDescricao());
			pstmt.setFloat(2, t.getValorDiaria());
			pstmt.setInt(3, t.getQuantidadeAdultos());
			pstmt.setInt(4, t.getQuantidadeCriancas());
			pstmt.executeQuery();
		} catch (SQLException except) {
			if (except.getMessage().contains("Duplicate entry")) {
				throw new DAOException(
						"Codigo " + t.getId() + " do Tipo de Quarto \""
								+ t.getDescricao() + "\" ja existe...");
			} else {
				throw new DAOException("Erro ao inserir Tipo de Quarto");
			}
		}
	}

	@Override
	public TipoDeQuarto select(String numQuarto) throws DAOException {
		TipoDeQuarto tQuarto;
		try {
			Connection con = ConnectionDB.getInstance().getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("Select * from tipo_quarto where id = ?");
			pstmt.setLong(1, Long.parseLong(numQuarto));
			ResultSet rs = pstmt.executeQuery();
			if (!rs.wasNull()) {
				tQuarto = new TipoDeQuarto();
				while (rs.next()) {
					tQuarto.setId(rs.getInt("id"));
					tQuarto.setDescricao(rs.getString("tipo_quarto"));
					tQuarto.setValorDiaria(rs.getFloat("preco"));
					tQuarto.setQuantidadeAdultos(rs.getShort("qtd_adultos"));
					tQuarto.setQuantidadeCriancas(rs.getShort("qtd_criancas"));
				}
				return tQuarto;
			} else {
				throw new DAOException("Tipo de quarto não encontrado.");
			}
		} catch (SQLException except) {
			throw new DAOException("Erro ao buscar Tipo de Quarto");
		}
	}

	@Override
	public List<TipoDeQuarto> selectAll(String numQuarto) throws DAOException {
		TipoDeQuarto tQuarto;
		List<TipoDeQuarto> l = new ArrayList<>();
		try {
			Connection con = ConnectionDB.getInstance().getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("Select * from tipo_quarto");
			ResultSet rs = pstmt.executeQuery();
			if (rs.first()) {
				do {
					tQuarto = new TipoDeQuarto();
					tQuarto.setId(rs.getInt("id"));
					tQuarto.setDescricao(rs.getString("tipo_quarto"));
					tQuarto.setValorDiaria(rs.getFloat("preco"));
					tQuarto.setQuantidadeAdultos(rs.getShort("qtd_adultos"));
					tQuarto.setQuantidadeCriancas(rs.getShort("qtd_criancas"));
					l.add(tQuarto);
				} while (rs.next());
				return l;
			} else {
				throw new DAOException("Nao ha Tipos de Quarto.");
			}
		} catch (SQLException except) {
			throw new DAOException("Erro ao buscar Tipo de Quarto");
		}
	}

	@Override
	public void update(TipoDeQuarto obj) throws DAOException {
		try {
			Connection con = ConnectionDB.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement("update tipo_quarto set preco = ?,"
					+ " qtd_adultos = ?, qtd_criancas = ? where id = ?");
			pstmt.setFloat(1, obj.getValorDiaria());
			pstmt.setShort(2, obj.getQuantidadeAdultos());
			pstmt.setShort(3, obj.getQuantidadeCriancas());
			pstmt.setLong(4, obj.getId());
			final int res = pstmt.executeUpdate();
			final int resInesperado = 0;
			if(res == resInesperado) {
				throw new SQLException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("Impossivel alterar Tipo de Quarto");
		}
	}

	@Override
	public void delete(String id) throws DAOException {
		try {
			Connection con = ConnectionDB.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement("delete from tipo_quarto"
					+ " where id = ?");
			pstmt.setInt(1, Integer.parseInt(id));
			final int res = pstmt.executeUpdate();
			final int resInesperado = 0;
			if(res == resInesperado) {
				throw new SQLException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("Impossivel exlcuir Tipo de Quarto");
		}
	}
}
