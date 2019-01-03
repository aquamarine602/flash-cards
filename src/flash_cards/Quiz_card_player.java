package flash_cards;

// a program that reads cards from a saved list

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

import flash_cards.Quiz_card_builder.New_menu_listener;
import flash_cards.Quiz_card_builder.Save_menu_listener;

import java.awt.*;
import java.io.*;

public class Quiz_card_player {
	
	private JTextArea display;
	private JTextArea answer;
	private ArrayList<Quiz_card>card_list;
	private Quiz_card current_card;
	private int current_card_cndex;
	private JFrame frame;
	private JButton next_b;
	private boolean is_show_answer;

	public static void main(String[] args) {
		Quiz_card_player reader = new Quiz_card_player();	// creates object
		reader.go();	// constructor of sorts

	}

	public void go() {
		// builds gui
		frame = new JFrame("Flash Card Player");
		JPanel main_pan = new JPanel();
		Font big_font = new Font("sanserif", Font.BOLD, 24);
		
		// displays question
		display = new JTextArea(6, 20);
		display.setLineWrap(true);
		display.setEditable(false);		// ONLY displays question
		display.setFont(big_font);
		
		// scroller for display
		JScrollPane q_scroller = new JScrollPane(display);
		q_scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		q_scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		// next button
		next_b = new JButton("Show Question");
		main_pan.add(q_scroller);
		main_pan.add(next_b);
		//nextButton.addActionListener(new Next_card_listener());
		
		// menu bar to load in a deck of cards
		JMenuBar menu_bar = new JMenuBar();
		JMenu file_menu = new JMenu("File");
		JMenuItem load_menu_item = new JMenuItem("Load");
		//load_menu_item.addActionListener(new Open_menu_listener());
		file_menu.add(load_menu_item);
		menu_bar.add(file_menu);
		
		// set frame stuff
		frame.setJMenuBar(menu_bar);
		frame.getContentPane().add(BorderLayout.CENTER, main_pan);
		frame.setSize(640, 500);
		frame.setVisible(true);
	}

}
