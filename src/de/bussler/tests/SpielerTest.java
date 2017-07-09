package de.bussler.tests;

import de.bussler.Minesweeper.Board;
import de.bussler.Minesweeper.Spieler;

public class SpielerTest {

	
	public void test()
	{
		final Board board= new Board(8);
		String SpielerName= "Max";
		Spieler Spieler1= new Spieler( SpielerName, board);
		Spieler1.getUserInput();
		
	}
}
