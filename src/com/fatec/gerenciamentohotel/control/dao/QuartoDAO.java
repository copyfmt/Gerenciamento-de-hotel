package src.com.fatec.gerenciamentohotel.control.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import src.com.fatec.gerenciamentohotel.control.TipoDeQuartoControl;
import src.com.fatec.gerenciamentohotel.control.connection.ConnectionDB;
import src.com.fatec.gerenciamentohotel.control.dao.exceptions.DAOException;
import src.com.fatec.gerenciamentohotel.entity.Quarto;
import src.com.fatec.gerenciamentohotel.entity.dao.IObjectDAO;

public class QuartoDAO implements IObjectDAO<Quarto, String> {

	@Override
	public void insert(Quarto q) throws DAOException {
		try {
			Connection con = ConnectionDB.getInstance().getConnection();
			PreparedStatement pstmt;
			// ID não é AUTO_INCREMENT, representa o numero da porta dos quartos
			pstmt = con
					.prepareStatement(" insert into quarto values (?, ?, ?) ");
			pstmt.setInt(1, q.getNumQuarto());
			pstmt.setLong(2, q.getTipoDeQuarto().getId());
			pstmt.setShort(3, q.getAndar());
			pstmt.executeQuery();
		} catch (SQLException e) {
			if (e.getMessage().contains("Duplicate entry")) {
				throw new DAOException("Quarto " + q.getNumQuarto() + " já existe...");
			} else {
				throw new DAOException("Erro ao inserir Quarto");
			}
		}
	}

	@Override
	public Quarto select(String numQuarto) throws DAOException {
		Quarto quar;
		try {
			Connection con = ConnectionDB.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"select * from quarto where num_quarto = ?");
			pstmt.setLong(1, Long.parseLong(numQuarto));
			ResultSet rs = pstmt.executeQuery();
			if (rs.first()) {
				quar = new Quarto();
				do {
					TipoDeQuartoControl tqc = new TipoDeQuartoControl();
					quar.setNumQuarto(rs.getInt("num_quarto"));
					quar.setTipoDeQuarto(
							tqc.selectTipoQuarto(rs.getInt("id_tipo_quarto")));
					quar.setAndar(rs.getShort("andar"));
				} while (rs.next());
				return quar;
			}
		} catch (SQLException e) {
			throw new DAOException("Erro ao buscar Quarto");
		}
		return null;
	}

	@Override
	public List<Quarto> selectAll(String numQuarto) throws DAOException {
		return null;
	}
}