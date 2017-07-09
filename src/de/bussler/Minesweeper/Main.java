package de.bussler.Minesweeper;

import java.util.Scanner;

import de.bussler.Minesweeper.Board;
import de.bussler.Minesweeper.Spieler;
import de.bussler.Minesweeper.Spielleiter;
import de.bussler.Minesweeper.Zug;

public class Main {

	
	public static void main(String[] args)
	{
		String playername=spielerName();
		int bSize=boardSize();
		
		final Board board= new Board(bSize);
		Spieler Spieler1= new Spieler( playername, board);
		final Spielleiter Spielleiter1=new Spielleiter(bSize, Spieler1);

		//Spielleiter1.Ipaint(); Just a tool for tests
		Spielleiter1.play();
		
	}
	
	public static String spielerName() // Method to get the playername
	{
		System.out.println("What is your name, player?");
		
		final Scanner namein = new Scanner(System.in, "UTF-8");
		final String name = namein.nextLine(); // parsing the turn of the player
		System.out.println("Welcome "+name+"!");
		
		return name;
	}
	
	public static int boardSize()
	{
		System.out.println("What is the size of the desired board? (Ranging from 3-9)");
		
		final Scanner sizein = new Scanner(System.in);
		final String size = sizein.nextLine(); // parsing the turn of the player
		
		final char cSize= size.charAt(0);
		final int iSize= cSize-48; //Number
		
		return iSize;
	}
}




