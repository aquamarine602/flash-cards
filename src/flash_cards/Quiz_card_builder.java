package flash_cards;

/*
 * Chloe Spilker
 * 1.3.18
 * creates a set of cards
 */

import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Quiz_card_builder {
	
	private JTextArea question;
	private JTextArea answer;
	private ArrayList<Quiz_card> card_list;		// contains objects of quizcard class
	private JFrame frame;

	public static void main(String[] args) {
		Quiz_card_builder builder = new Quiz_card_builder();
		builder.go();

	}
	
	public void go() {
		// builds gui -- menu bar
		frame = new JFrame("Quiz Card Builder");
		JPanel main_pan = new JPanel();
		Font big_font = new Font("sanserif", Font.BOLD,24);
		
		// text area creates area for user to type their text
		// creates the box for the user to type the question
		question = new JTextArea(6, 20);
		question.setLineWrap(true);			// line wrap around box
		question.setWrapStyleWord(true);	// word style?
		question.setFont(big_font);
		
		// creates scroller for the question box
		// creates scroller -- always vertical, never horizontal
		JScrollPane q_scroller = new JScrollPane(question);
		q_scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		q_scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		// do same thing but with answer
		answer = new JTextArea(6, 20);
		answer.setLineWrap(true);
		answer.setWrapStyleWord(true);
		answer.setFont(big_font);
		
		JScrollPane a_scroller = new JScrollPane(answer);
		a_scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		a_scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		// button to continue to the next card
		JButton next_b = new JButton("Next Card");
		
		// creates list of cards
		card_list = new ArrayList<Quiz_card>();
		
		// creates 2 labels -- one for the question box and one for the answer box
		JLabel q_label = new JLabel("Question:");
		JLabel a_label = new JLabel("Answer:");		
		
		// adds them to the main panel
		main_pan.add(q_label);
		main_pan.add(q_scroller);
		main_pan.add(a_label);
		main_pan.add(a_scroller);
		main_pan.add(next_b);
		next_b.addActionListener(new Next_card_listener());
		
		// creates a menu bar
		// create a file bar where you can add and save cards
		JMenuBar menu_bar = new JMenuBar();
		JMenu file_menu = new JMenu("File");
		JMenuItem new_menu_item = new JMenuItem("New");
		JMenuItem save_menu_item = new JMenuItem("Save");
		new_menu_item.addActionListener(new New_menu_listener());
		save_menu_item.addActionListener(new Save_menu_listener());
		file_menu.add(new_menu_item);
		file_menu.add(save_menu_item);
		menu_bar.add(file_menu);
		frame.setJMenuBar(menu_bar);
		
		// gives frame a border and a size
		frame.getContentPane().add(BorderLayout.CENTER, main_pan);
		frame.setSize(500, 540);
		
		frame.setVisible(true);
		
	}
	
	public class Next_card_listener implements ActionListener{
		public void actionPerformed(ActionEvent ev) {
			// gets text inside the boxes, saves it and clears the boxes
			Quiz_card card = new Quiz_card(question.getText(), answer.getText());
			card_list.add(card);
			clear_card();
		}
		
	}
	
	public class New_menu_listener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			// creates a new set of cards
			card_list.clear();
			clear_card();
		}
	}
	
	public class Save_menu_listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Quiz_card card = new Quiz_card(question.getText(), answer.getText());
			card_list.add(card);
			
			// chooses file to save in
			JFileChooser file_save = new JFileChooser();
			file_save.showSaveDialog(frame);
			save_file(file_save.getSelectedFile());
		}
	}

	private void clear_card() {
		// clears out the card setting question and answer to blank strings
		question.setText("");
		answer.setText("");
		question.requestFocus();
	}
	
	private void save_file(File file) {
		// does the file writing -- called by the save menu listener
		try {	// what we want to try to do
			// buffered writer writes to something -- new filewriter to 'file'
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			for (Quiz_card card:card_list) {
				// for each card in card_list, writer writes the card's question
				// and answer to 'file'
				writer.write(card.getQuestion() + "/");
				writer.write(card.getAnswer() + "\n");
			}
			writer.close(); // closes the writer -- ALWAYS close writer after use
		} catch (IOException ex) {	// if there is an issue
			System.out.println("Couldn't write card_list out.");
			ex.printStackTrace();	// probably prints where the error was
		}
	}
}
