package src.com.fatec.gerenciamentohotel;


import src.com.fatec.gerenciamentohotel.boundary.window.MainWindow;
import src.com.fatec.gerenciamentohotel.boundary.window.login.LoginWindow;

public class MainClass {
	public static void main(String[] args) {
		LoginWindow loginWindow = new LoginWindow();
		loginWindow.setVisible(true);
		MainWindow mainWindow = new MainWindow();
		mainWindow.setVisible(true);
	}
}