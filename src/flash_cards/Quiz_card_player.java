package flash_cards;

// a program that reads cards from a saved list

import java.util.*;
import java.awt.event.*;
import javax.swing.*;
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
		
		display = new JTextArea(6, 20);
		display.setLineWrap(true);
		display.setEditable(false);
		display.setFont(big_font);
		
		JScrollPane q_scroller = new JScrollPane(display);
		q_scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		q_scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		next_b = new JButton("Show Question");
		main_pan.add(q_scroller);
		main_pan.add(next_b);
	}

}
