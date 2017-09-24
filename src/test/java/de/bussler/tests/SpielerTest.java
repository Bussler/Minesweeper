package test.java.de.bussler.tests;

import main.java.de.bussler.Minesweeper.Board;
import main.java.de.bussler.Minesweeper.Spieler;

public class SpielerTest {

	
	public void test()
	{
		final Board board= new Board(8);
		String SpielerName= "Max";
		Spieler Spieler1= new Spieler( SpielerName, board);
		Spieler1.getUserInput();
		
	}
}
