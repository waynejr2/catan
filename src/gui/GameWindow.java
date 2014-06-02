package gui;

import game.*;

import java.awt.Button;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import lib.GraphPaperLayout;


public class GameWindow {
		
	ArrayList<Player> players = new ArrayList<Player>();
	CatanBoard board;
	private Player thisPlayer;
	public final static int INTERVAL = 20;
	
	
	final static int SCRSIZE = 900; //TODO specify
	
	
	public GameWindow(ArrayList<Player> players) {
		board = new CatanBoard(players);
		
		createAndShowGUI();

		Timer timer = new Timer(INTERVAL,
				new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						// Refresh the board
						board.repaint(); //TODO fix validate
					}
				});

		timer.start();
	}
	
	private void createAndShowGUI() {
		
		JFrame frame = new JFrame("Catan");
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		Dimension d = new Dimension(5,5);

		frame.setSize(SCRSIZE + 500, SCRSIZE);
		//frame.setLayout(new GraphPaperLayout(d));
		Container content = frame.getContentPane();
		content.setLayout(new GraphPaperLayout(d));
		//content.add(board);
		content.add(board,new Rectangle(0,0,4,4));
		content.add(new SideBar(this),new Rectangle(4,0,1,4));
		
		content.add(new Button("Pretty cards here"),new Rectangle(0,4,5,1));
		//frame.add(board,new Rectangle(0,0,500,500));
		//content.add(new Button("I"), new Rectangle(0,0,1,2));
		
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);
		
		board.repaint();
	}
	
	public CatanBoard getBoard() {
		return board;
	}
}
