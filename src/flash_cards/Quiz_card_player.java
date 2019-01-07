package flash_cards;

// a program that reads cards from a saved list

import java.util.*;

import java.awt.event.*;
import javax.swing.*;

import flash_cards.Quiz_card_builder.New_menu_listener;
import flash_cards.Quiz_card_builder.Save_menu_listener;

import java.awt.*;
import java.io.*;

@SuppressWarnings("unused")
public class Quiz_card_player {
	
	private JTextArea display;
	@SuppressWarnings("unused")
	private JTextArea answer;
	private ArrayList<Quiz_card>card_list;
	private Quiz_card current_card;
	private int current_card_index;
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
		next_b.addActionListener(new Next_card_listener());
		
		// menu bar to load in a deck of cards
		JMenuBar menu_bar = new JMenuBar();
		JMenu file_menu = new JMenu("File");
		JMenuItem load_menu_item = new JMenuItem("Load");
		load_menu_item.addActionListener(new Open_menu_listener());
		file_menu.add(load_menu_item);
		menu_bar.add(file_menu);
		
		// set frame stuff
		frame.setJMenuBar(menu_bar);
		frame.getContentPane().add(BorderLayout.CENTER, main_pan);
		frame.setSize(500, 300);
		frame.setVisible(true);
	}
	
	public class Next_card_listener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (is_show_answer) {
				display.setText(current_card.getAnswer());
				next_b.setText("Next Card");
				is_show_answer = false;
			} else {
				if  (current_card_index < card_list.size()) {
					show_next_card();
				} else {
					display.setText("That was the last card.");
					next_b.setEnabled(false);
				}
			}
		}
	}
	
	public class Open_menu_listener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			JFileChooser fileOpen = new JFileChooser();
			fileOpen.showOpenDialog(frame);
			load_file(fileOpen.getSelectedFile());
			
		}
	}
	
	private void show_next_card() {
		current_card = card_list.get(current_card_index);
		current_card_index++;
		display.setText(current_card.getQuestion());
		is_show_answer = true;
	}
	
	private void load_file(File file) {
		card_list = new ArrayList<Quiz_card>();
		
		try {
			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null) {
				make_card(line);
			} 
		} catch (Exception ex) {
			System.out.println("Couldn't read the file.");
			ex.printStackTrace();
		}
		show_next_card();
	}
	
	private void make_card(String line_to_parse) {
		String[] results = line_to_parse.split("/");
		Quiz_card card = new Quiz_card(results[0], results[1]);
		card_list.add(card);
		System.out.println("Card made.");
	}

}
