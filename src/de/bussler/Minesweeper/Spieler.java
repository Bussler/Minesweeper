package de.bussler.Minesweeper;

import java.util.Scanner;

public class Spieler {

	String name;
	Board VisibleBoard;
	
	public Spieler(String name, Board Visibleboard)
	{this.name=name;
		this.VisibleBoard=Visibleboard;
	}
	
	
	public Zug getUserInput() //gets the turn of the player with the help of a scanner
	{
		System.out.println(getName()+" what's your turn?");
		
		while(true)
		{
		
		final Scanner turnin = new Scanner(System.in, "UTF-8");
		final String turn = turnin.nextLine(); // parsing the turn of the player
		System.out.println(getName() + " ,your turn was " + turn + "!");
		
		try{
			final Zug result= umwandeln(turn); // String gets translated into a turn
		return result;
		} catch(final IllegalArgumentException e)
		{
			System.out.println(e.getMessage());
		}
		}
		
	}
	
	public Zug umwandeln(String turn) // translates the turn of the player to numbers for the board
	{
		final char posZ= turn.charAt(0);
		final char posS= turn.charAt(1);
		
		final int Zeile= posZ-65; //Letter
		final int Spalte= posS-49; //Number
		
		if (Zeile > VisibleBoard.getSize()-1 || Zeile < 0 || Spalte > VisibleBoard.getSize()-1 || Spalte < 0) // If the turn is invalid
		{
			throw new IllegalArgumentException(getName() + " , please follow the rules, first type in the letter, then the number of your turn (e.g. A2)");
		}
		
		final Zug pturn= new Zug(Zeile, Spalte);
		return pturn;
		
	}
	
	public String getName()
	{
		return this.name;
	}
	
	
	
}
