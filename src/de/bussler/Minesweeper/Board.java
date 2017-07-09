package de.bussler.Minesweeper;

public class Board {

	public String[][] Spielfeld;
	int Size;
	
	public Board(int h)
	{Spielfeld= new String[h][h];
	Size=h;
	}
	
	public void set(int x, int y, String content) // changes the content of Spielfeld
	{
		Spielfeld[x][y]= content;
	}
	
	public String get(int x, int y) // returns content of Spielfeld
	{
		return Spielfeld[x][y];
	}
	
	public boolean aufgedeckt(int x, int y) // checks, if a tile on the board is aleady clicked
	{
		boolean aufgedeckt= true;
		if(Spielfeld[x][y].equals(" "))
		{
			aufgedeckt= false;
		}
		return aufgedeckt;
	}
	
	public int getSize()
	{
		return Size;
	}
	
		
}
