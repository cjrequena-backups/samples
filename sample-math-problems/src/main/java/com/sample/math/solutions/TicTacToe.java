package com.sample.math.solutions;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * 
 * @author cjrequena
 *
 */
public class TicTacToe {

	private static int SIZE = 3;
	private int[][] board = new int[SIZE][SIZE];

	public static void main(String[] args) {
		new TicTacToe().run();
	}

	/**
	 * 
	 */
	public void run() {
		try {
			File file = new File("input1.txt");
			List<String> lines = Files.readAllLines(Paths.get(file.getAbsolutePath()), Charset.defaultCharset());
			int numTestCases = Integer.valueOf(lines.get(0));
			int nextPlayerLine = -1;
			String nextPlayer;
			for (int i = 0; i < numTestCases; i++) {
				if (i == 0) {
					nextPlayerLine = 1;
				} else {
					nextPlayerLine += 4;
				}
				nextPlayer = lines.get(nextPlayerLine).trim();

				String row0 = lines.get(nextPlayerLine + 1);
				String row1 = lines.get(nextPlayerLine + 2);
				String row2 = lines.get(nextPlayerLine + 3);

				for (int n = 0; n < 3; n++) {
					String row = "";
					if (n == 0) {
						row = row0;
					} else if (n == 1) {
						row = row1;
					} else {
						row = row2;
					}
					for (int m = 0; m < 3; m++) {
						if (("" + row.charAt(m)).equals("_")) {
							board[n][m] = -1;
						} else if (("" + row.charAt(m)).equals("X")) {
							board[n][m] = 1;
						} else {
							board[n][m] = 0;
						}
					}
				}

				if (nextPlayer.equals("X")) {
					moveComputerPlayer_X();
				} else if (nextPlayer.equals("O")) {
					moveComputerPlayer_O();
				} else {
					System.out.println("ERROR");
				}

				if (getWinner() == 0) {
					System.out.println("Case " + (i + 1) + ": O wins");
				} else if (getWinner() == 1) {
					System.out.println("Case " + (i + 1) + ": X wins");
				} else {
					System.out.println("Case " + (i + 1) + ": Draw");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param n
	 * @param m
	 */
	public void moveHumanPlayer_O(int n, int m) {
		if (n >= 0 && m >= 0 && n < SIZE && m < SIZE && board[n][m] == -1) {
			if (getWinner() == -1) {
				board[n][m] = 0;
			}
		}
	}

	/**
	 * 
	 */
	private void moveComputerPlayer_X() {
		if (!isGammeOver()) {
			int f = 0, c = 0;
			int v = Integer.MIN_VALUE;
			int aux;
			for (int n = 0; n < SIZE; n++) {
				for (int m = 0; m < SIZE; m++) {
					if (board[n][m] == -1) {
						board[n][m] = 1;
						aux = min();
						if (aux > v) {
							v = aux;
							f = n;
							c = m;
						}
						board[n][m] = -1;
					}
				}
			}
			board[f][c] = 1;
		}
	}

	/**
	 * 
	 */
	private void moveComputerPlayer_O() {
		if (!isGammeOver()) {
			int f = 0, c = 0;
			int v = Integer.MIN_VALUE;
			int aux;
			for (int n = 0; n < SIZE; n++) {
				for (int m = 0; m < SIZE; m++) {
					if (board[n][m] == -1) {
						board[n][m] = 0;
						aux = min();
						if (aux > v) {
							v = aux;
							f = n;
							c = m;
						}
						board[n][m] = -1;
					}
				}
			}
			board[f][c] = 0;
		}
	}

	/**
	 * 
	 * @return
	 */
	private boolean isFullBoard() {
		for (int n = 0; n < SIZE; n++) {
			for (int m = 0; m < SIZE; m++) {
				if (board[n][m] == -1) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 
	 * @return
	 */
	private boolean isGammeOver() {
		return isFullBoard() || getWinner() != -1;
	}

	/**
	 * 
	 * @return
	 */
	public int getWinner() {

		if (board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] != -1) {
			return board[0][0];
		}
		if (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] != -1) {
			return board[0][2];
		}
		for (int n = 0; n < SIZE; n++) {
			if (board[n][0] == board[n][1] && board[n][0] == board[n][2] && board[n][0] != -1) {
				return board[n][0];
			}
			if (board[0][n] == board[1][n] && board[0][n] == board[2][n] && board[0][n] != -1) {
				return board[0][n];
			}
		}
		return -1;
	}

	/**
	 * 
	 * @return
	 */
	private int max() {
		if (isGammeOver()) {
			if (getWinner() != -1)
				return -1;
			else
				return 0;
		}
		int v = Integer.MIN_VALUE;
		int aux;
		for (int n = 0; n < SIZE; n++) {
			for (int m = 0; m < SIZE; m++) {
				if (board[n][m] == -1) {
					board[n][m] = 1;
					aux = min();
					if (aux > v)
						v = aux;
					board[n][m] = -1;

				}
			}
		}
		return v;
	}

	/**
	 * 
	 * @return
	 */
	private int min() {
		if (isGammeOver()) {
			if (getWinner() != -1)
				return 1;
			else
				return 0;
		}
		int v = Integer.MAX_VALUE;
		int aux;
		for (int n = 0; n < SIZE; n++) {
			for (int m = 0; m < SIZE; m++) {
				if (board[n][m] == -1) {
					board[n][m] = 0;
					aux = max();
					if (aux < v)
						v = aux;
					board[n][m] = -1;
				}
			}
		}
		return v;
	}

	/**
	 * 
	 * @return
	 */
	public int[][] getBoard() {
		return board;
	}

}
