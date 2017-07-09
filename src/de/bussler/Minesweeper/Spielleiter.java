package de.bussler.Minesweeper;

public class Spielleiter {

	public Board VisibleBoard;
	public Board InvisibleBoard;
	public Spieler MSpieler;
	public int BombenFelder;
	
	public Spielleiter(int l, Spieler spieler)
	{
		VisibleBoard = new Board(l);
		InvisibleBoard = new Board(l);
		this.MSpieler=spieler;
		
		SpielfelderBelegen();
		this.BombenFelder=zählegewonnen();
		paint();
	}
	
	public void play() // method to play the game
	{
		
		do{
			
		Zug SZug= MSpieler.getUserInput();
		if(regeln(SZug.getX(), SZug.getY())==true)
		{
			aufdecken(SZug.getX(), SZug.getY());
			paint();
		}
		else
		{
			System.out.println("You already clicked on this field!");
		}
		
		if(verloren(SZug)==true)
		{
			System.out.println(MSpieler.getName()+", you clicked on a bomb and lost!");
			return;
		}
		
		} while(gewonnen()==false);
		
		System.out.println(MSpieler.getName()+", you won the game!");
	}
	
	public void paint() //Paints the visible board for the player in the console
	{
		for(int i=0; i< VisibleBoard.getSize();i++)
		{
			System.out.println("-----------");
			for (int j=0; j< VisibleBoard.getSize(); j++)
			{
				System.out.print("|");
				System.out.print(VisibleBoard.get(i, j));
			}
			
			System.out.print("|");
			System.out.println("");
		}
	}
	
	public void Ipaint() //Paints the Invisible board for the player in the console
	{
		for(int i=0; i< InvisibleBoard.getSize();i++)
		{
			System.out.println("-----------");
			for (int j=0; j< InvisibleBoard.getSize(); j++)
			{
				System.out.print("|");
				System.out.print(InvisibleBoard.get(i, j));
			}
			
			System.out.print("|");
			System.out.println("");
		}
	}
	
	public void SpielfelderBelegen() // sets the contents for the boards at the beginning of the match
	{
		int z=25;
		int zufallszahl;
		
		for(int a=0; a<InvisibleBoard.getSize(); a++) // content for the invisible board
		{
			for(int b=0; b<InvisibleBoard.getSize();b++)
			{
				zufallszahl=(int)(Math.random()*100);
				if(zufallszahl<z)
				{
					InvisibleBoard.set(a, b, "B"); //TODO Count the bombs when you set them, delete zählegewonnen
				}
				else
				{
					InvisibleBoard.set(a, b, "X");
				}
			}
		}
		
		
		for(int i=0; i< VisibleBoard.getSize();i++) // contents for the visible board
		{
			for(int j=0;j<VisibleBoard.getSize();j++)
			{
				VisibleBoard.set(i, j, " ");
			}
		}
	}
	
	public boolean regeln(int x, int y) //checks, if the field is empty
	{
		boolean erlaubt= false;
		if(VisibleBoard.get(x, y).equals(" "))
		{
			erlaubt=true;
		}
		return erlaubt;
	}
	
	public boolean gewonnen() // checks, if a player has won the game
	{
		int f=0;
		for(int i=0; i< VisibleBoard.getSize();i++)
		{
		
			for (int j=0; j< VisibleBoard.getSize(); j++)
			{
				if(VisibleBoard.get(i, j).equals(" "))
				{
					f++;
				}
			}
			
			
		}
		
		if(f==BombenFelder)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	public boolean verloren(Zug zug)
	{
		boolean verloren=false;
		if(InvisibleBoard.get(zug.getX(), zug.getY()).equals("B"))
		{
			verloren=true;
		}
		
		return verloren;
	}
	
	public int zählegewonnen() //Counts the bomb fields, to determine, when  player wins the game
	{
		int g=0;
		
		for(int i=0; i< InvisibleBoard.getSize();i++)
		{
		
			for (int j=0; j< InvisibleBoard.getSize(); j++)
			{
				if(InvisibleBoard.get(i, j).equals("B"))
				{
					g++;
				}
			}
			
			
		}
		return g;
		
	}
	
	public void aufdecken(int x, int y)//Method to reveal the tiles
	{
		if(regeln(x, y)==true)
		{
		VisibleBoard.set(x, y, InvisibleBoard.get(x, y));
		int z= zählen(x,y);
		if(z==0)
		{
			rekursivesAufdecken(x,y);
		}
		
		if(z!=0 && InvisibleBoard.get(x, y).equals("X"))
		{
			VisibleBoard.set(x,y, ""+z);
		}
		
		}
	
	}
	
	public int zählen(int x, int y) //counts the number of surrounding bombs TODO Fehler beheben
	{int bombenUmkreis=0;
		
	if(x>0)
	{
		if(y>0)
		{
	if(InvisibleBoard.get(x-1, y-1).equals("B"))
	{
		bombenUmkreis++;
	}
		}
	
	if(InvisibleBoard.get(x-1, y).equals("B"))
	{
		bombenUmkreis++;
	}
	
	if(y<InvisibleBoard.getSize()-1)
	{
	if(InvisibleBoard.get(x-1, y+1).equals("B"))
	{
		bombenUmkreis++;
	}
	}
	
	}
	
	if(x<InvisibleBoard.getSize()-1)
	{
		if(y>0)
		{
			if(InvisibleBoard.get(x+1, y-1).equals("B"))
			{
				bombenUmkreis++;
			}
		}
		
		if(InvisibleBoard.get(x+1, y).equals("B"))
		{
			bombenUmkreis++;
		}
		
		if(y<InvisibleBoard.getSize()-1)
		{
			if(InvisibleBoard.get(x+1, y+1).equals("B"))
			{
				bombenUmkreis++;
			}
		}
	}
	
	if(y>0)
	{
		if(InvisibleBoard.get(x, y-1).equals("B"))
		{
			bombenUmkreis++;
		}
	}
	
	if(y<InvisibleBoard.getSize()-1)
	{
		if(InvisibleBoard.get(x, y+1).equals("B"))
		{
			bombenUmkreis++;
		}
	}
	
	
	return bombenUmkreis;
	}
	
	public void rekursivesAufdecken(int x, int y)
	{int a=0;
	 int b=0;
		
	 
		 
		 if(x>0 && y>0)
		 {
			 a=x-1;
			 b=y-1;
			 if(VisibleBoard.get(a, b).equals(" ")) //TODO double safety
			 {
				 aufdecken(a,b);
			 }
			 
		 }
		 
		 if(y>0)
		 {
			 a=x;
			 b=y-1;
			 aufdecken(a,b);
		 }
		 
		 if(x<VisibleBoard.getSize()-1 && y>0)
		 {
			 a=x+1;
			 b=y-1;
			 aufdecken(a,b);
		 }
		 
		 if(x<VisibleBoard.getSize()-1)
		 {
			 a=x+1;
			 b=y;
			 aufdecken(a,b);
		 }
		 
		 if(x>0)
		 {
			 a=x-1;
			 b=y;
			 aufdecken(a,b);
		 }
		 
		 if(x>0 && y< VisibleBoard.getSize()-1)
		 {
			 a=x-1;
			 b=y+1;
			 aufdecken(a,b);
		 }
		 
		 if(y<VisibleBoard.getSize()-1)
		 {
			 a=x;
			 b=y+1;
			 aufdecken(a,b);
		 }
		 
		 if(x<VisibleBoard.getSize()-1 && y< VisibleBoard.getSize()-1)
		 {
			 a=x+1;
			 b=y+1;
			 aufdecken(a,b);
		 }
		 
	 
	}
	
}
