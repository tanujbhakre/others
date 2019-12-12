import board.Board;
import board.tictactoe.TicTacToeBoard;
import game.GameManager;
import model.Player;
import tictactoe.io.IOProvider;
import tictactoe.io.ScannerIOProvider;

public class Main {

	public static void main(String args[]) {
		IOProvider ioProvider = new ScannerIOProvider();
		Player player1 = new Player("Tanuj");
		Player player2 = new Player("Chaitanya");
		Board board = new TicTacToeBoard(3, 3);
		GameManager gameManager = new GameManager(ioProvider, board, player1, player2);
		gameManager.play();
	}
}
