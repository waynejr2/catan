package gui;

import game.Game;
import game.GameRunner;
import game.Player;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lib.GraphPaperLayout;

public class SideBar extends JPanel {
	
	private ComponentList rollPanel 	= new ComponentList();
	private ComponentList mainPanel		= new ComponentList();
	private ComponentList buyPanel		= new ComponentList();
	private ComponentList tradePanel	= new ComponentList();
	private KComponent currentPlayer;
	
	public SideBar(final GameWindow display) {
		
		this.setLayout(new GraphPaperLayout(new Dimension(14,24)));
		
		// Current player title (always in sidebar)
		//-------------------------------------------------------------------
		
		currentPlayer = new KComponent(new JLabel(""), new Rectangle(2,0,10,1));
		currentPlayer.getComponent().setFont(new Font("Arial", 1, 16));
		setCurrentPlayer(GameRunner.currentPlayer);
		add(currentPlayer.getComponent(), currentPlayer.getRectangle());
		
		// Roll panel:
		//-------------------------------------------------------------------
		
		JButton roll = new JButton(new AbstractAction() {
			public void actionPerformed(ActionEvent a) {
				Game g = display.getBoard().getGame();
				
				int roll = g.roll(GameRunner.currentPlayer);
				
				if (roll != 7) {
					mainPanel();
				}
				else {
					g.halfCards();
					
					//TODO move-robber layout
					//TODO take-cards layout
					
					mainPanel();
				}
//				JLabel rollNumb = new JLabel("roll value: " + roll);
//				rollNumb.setFont(new Font("Arial", 1, 16));
//				add(rollNumb, new Rectangle(2,2,10,1));
			}
		});
		roll.setText("roll the dice");
		rollPanel.add(new KComponent(roll, new Rectangle(3,5,8,3)));
		
		// Main panel:
		//-------------------------------------------------------------------
		
		JButton buy = new JButton(new AbstractAction() {
			public void actionPerformed(ActionEvent a) {
				buyPanel();
			}
		});
		buy.setText("buy");
		mainPanel.add(new KComponent(buy, new Rectangle(3,5,8,3)));
		
		JButton trade = new JButton(new AbstractAction() {
			public void actionPerformed(ActionEvent a) {
				tradePanel();
			}
		});
		trade.setText("trade");
		mainPanel.add(new KComponent(trade, new Rectangle(3,10,8,3)));
		
		JButton endTurn = new JButton(new AbstractAction() {
			public void actionPerformed(ActionEvent a) {
				GameRunner.turnDone = true;
				rollPanel();
			}
		});
		endTurn.setText("end your turn");
		mainPanel.add(new KComponent(endTurn, new Rectangle (3,18,8,3)));
		
		// Trade panel:
		//-------------------------------------------------------------------

		JLabel tradeText = new JLabel("Trade with...");
		tradePanel.add(new KComponent(tradeText, new Rectangle(4,4,6,2)));
		
		// Trade with players
		JButton tradePlayer = new JButton(new AbstractAction() {
			public void actionPerformed(ActionEvent a) {
				//TODO player-trade layout
				
				mainPanel();
			}
		});
		tradePlayer.setText("a player");
		tradePanel.add(new KComponent(tradePlayer, new Rectangle(1,6,6,2)));
		
		// Trade with stock
		JButton tradeStock = new JButton(new AbstractAction() {
			public void actionPerformed(ActionEvent a) {
				//TODO stock-trade layout
				mainPanel();
			}
		});
		tradeStock.setText("the stock");
		tradePanel.add(new KComponent(tradeStock, new Rectangle(7,6,6,2)));
		
		// Return to main panel
		JButton returnMain = new JButton(new AbstractAction() {
			public void actionPerformed(ActionEvent a) {
				mainPanel();
			}
		});
		returnMain.setText("return to main panel");
		tradePanel.add(new KComponent(returnMain, new Rectangle(3,11,8,2)));
		
		// Buy panel:
		//-------------------------------------------------------------------
		
		JLabel buyText = new JLabel("Buy a...");
		buyPanel.add(new KComponent(buyText, new Rectangle(4,4,6,2)));
		
		// Buy settlement
		JButton buySettlement = new JButton(new AbstractAction() {
			public void actionPerformed(ActionEvent a) {
				Game g = display.getBoard().getGame();

				boolean bought = g.buySettlement(GameRunner.currentPlayer);
				
				if (bought) {
					//TODO place-settlement layout
					buyPanel();
				}
				else {
					//TODO throw-error layout
					buyPanel();
				}
			}
		});
		buySettlement.setText("settlement");
		buyPanel.add(new KComponent(buySettlement, new Rectangle(1,6,6,2)));
		
		// Buy city
		JButton buyCity = new JButton(new AbstractAction() {
			public void actionPerformed(ActionEvent a) {
				Game g = display.getBoard().getGame();

				boolean bought = g.buyCity(GameRunner.currentPlayer);
				
				if (bought) {
					//TODO upgrade-settlement layout
					buyPanel();
				}
				else {
					//TODO throw-error layout
					buyPanel();
				}
			}
		});
		buyCity.setText("city");
		buyPanel.add(new KComponent(buyCity, new Rectangle(7,6,6,2)));
		
		// Buy road
		JButton buyRoad = new JButton(new AbstractAction() {
			public void actionPerformed(ActionEvent a) {
				Game g = display.getBoard().getGame();

				boolean bought = g.buyRoad(GameRunner.currentPlayer);
				
				if (bought) {
					//TODO place-road layout
					buyPanel();
				}
				else {
					//TODO throw-error layout
					buyPanel();
				}
			}
		});
		buyRoad.setText("road");
		buyPanel.add(new KComponent(buyRoad, new Rectangle(1,8,6,2)));
		
		// Buy devcard
		JButton buyCard = new JButton(new AbstractAction() {
			public void actionPerformed(ActionEvent a) {
				Game g = display.getBoard().getGame();

				boolean bought = g.buyDevCard(GameRunner.currentPlayer);
				
				if (bought) {
					buyPanel();
				}
				else {
					//TODO throw-error layout
					buyPanel();
				}
				mainPanel();
			}
		});
		buyCard.setText("dev card");
		buyPanel.add(new KComponent(buyCard, new Rectangle(7,8,6,2)));
		
		// Return to main panel
		buyPanel.add(new KComponent(returnMain, new Rectangle(3,12,8,2)));
		
		//-------------------------------------------------------------------

		setPanel(rollPanel);
	}
	
	private void setPanel(ComponentList cL) {
		this.removeAll();
		this.add(currentPlayer.getComponent(), currentPlayer.getRectangle());
		
		for (int i = 0; i < cL.size(); i++) {
			this.add(cL.get(i).getComponent(), cL.get(i).getRectangle());
		}
		
		repaint();
		validate();
	}
	
	public void buyPanel() {
		setPanel(buyPanel);
	}
	
	public void tradePanel() {
		setPanel(tradePanel);
	}
	
	public void rollPanel() {
		setCurrentPlayer(GameRunner.currentPlayer);
		setPanel(rollPanel);
	}
	
	public void mainPanel() {
		setPanel(mainPanel);
	}
	
	public void setCurrentPlayer(Player p) {
		((JLabel) currentPlayer.getComponent()).setText("Player: " + p.getName());
	}
}
