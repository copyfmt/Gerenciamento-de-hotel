package src.com.fatec.gerenciamentohotel.control;

import java.util.List;

import javax.swing.JOptionPane;

import src.com.fatec.gerenciamentohotel.control.dao.ReservaDAO;
import src.com.fatec.gerenciamentohotel.control.dao.exceptions.DAOException;
import src.com.fatec.gerenciamentohotel.entity.Reserva;

public class ReservaControl {
	public void insert(Reserva r) {
		if (r.getCheckIn() == null) {
			displayScreenMessage("Erro", "CheckIn Vazio", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// checkout pode ser null, assim validamos se esta ou não ativo o
		if (r.getFuncionario() == null) {
			// funcionario nao pode ser vazio, vai ser atribuido por propriedade
			// estatica do sistema, validando o login e procurando suas
			// informações no banco
			displayScreenMessage("Erro", "Funcionario Vazio, contate um administrador",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (r.getHospede() == null) {
			displayScreenMessage("Erro", "A reserva deve conter um hóspede válido",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (r.getQuarto() == null) {
			displayScreenMessage("Erro", "A reserva deve conter um quarto válido",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (r.getStatus() == Character.MIN_VALUE) {
			displayScreenMessage("Erro", "Status Vazio", JOptionPane.ERROR_MESSAGE);
			return;
		} else if (r.getStatus() != 'A') {
			if (r.getStatus() != 'I') {
				displayScreenMessage("Status Incorreto",
						"Status deve ser A (Ativo) ou I (Inativo)",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		try {
			ReservaDAO rdao = new ReservaDAO();
			rdao.insert(r);
			displayScreenMessage("Reserva", "Reserva cadastrada com sucesso!", JOptionPane.WARNING_MESSAGE);
		} catch (DAOException e) {
			displayScreenMessage("Reserva", e.getMessage(), JOptionPane.WARNING_MESSAGE);
		}
	}

	public Reserva selectReserva(String cpfHospede) {
		try {
			ReservaDAO rdao = new ReservaDAO();
			return rdao.select(cpfHospede);
		} catch (DAOException e) {
			displayScreenMessage("Reserva", e.getMessage(), JOptionPane.WARNING_MESSAGE);
			return new Reserva();
		}
	}

	public List<Reserva> selectHistoricoReservas(String cpfHospede) {
		try {
			ReservaDAO rdao = new ReservaDAO();
			return rdao.selectAll(cpfHospede);
		} catch (DAOException e) {
			displayScreenMessage("Reserva", e.getMessage(), JOptionPane.WARNING_MESSAGE);
		}
		return null;
	}

	private void displayScreenMessage(String titulo, String mensagem, int errorType) {
		JOptionPane.showMessageDialog(null, mensagem, titulo, errorType);
	}
}
