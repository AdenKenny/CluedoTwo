package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class SelectedPanel extends JPanel {
	
	public SelectedPanel() {
		super();
		createButtons();
		this.repaint();
	}
	
	public void createButtons() {
		this.setSize(new Dimension(400, 400));
		
		GridLayout layout = new GridLayout(6, 1);
		
		layout.setVgap(1);
		layout.setHgap(1);

		
		this.setLayout(layout);

		ButtonListener b = new ButtonListener(this);
		
		JRadioButton colonelMustard = new JRadioButton("Colonel Mustard");
		colonelMustard.setSize(new Dimension(100, 15));
		colonelMustard.setActionCommand("Colonel Mustard");
		colonelMustard.addActionListener(b);
		this.add(colonelMustard);
		
		JRadioButton mrsWhite = new JRadioButton("Mrs White");
		mrsWhite.setSize(new Dimension(100, 100));
		mrsWhite.setActionCommand("Mrs White");
		mrsWhite.addActionListener(b);
		this.add(mrsWhite);
		
		JRadioButton missScarlett = new JRadioButton("Miss Scarlett");
		missScarlett.setSize(new Dimension(100, 100));
		missScarlett.setActionCommand("Miss Scarlett");
		missScarlett.addActionListener(b);
		this.add(missScarlett);
		
		JRadioButton professorPlum = new JRadioButton("Professor Plum");
		professorPlum.setSize(new Dimension(100, 100));
		professorPlum.setActionCommand("Professor Plum");
		professorPlum.addActionListener(b);
		this.add(professorPlum);
		
		JRadioButton mrsPeacock = new JRadioButton("Mrs Peacock");
		mrsPeacock.setSize(new Dimension(100, 100));
		mrsPeacock.setActionCommand("Mrs Peacock");
		mrsPeacock.addActionListener(b);
		this.add(mrsPeacock);
		
		JRadioButton reverendGreen = new JRadioButton("Reverend Green");
		reverendGreen.setSize(new Dimension(100, 100));
		reverendGreen.setActionCommand("Reverend Green");
		reverendGreen.addActionListener(b);
		this.add(reverendGreen);
		
		
		this.setVisible(true);
	}
	
	class ButtonListener implements ActionListener {

		SelectedPanel parent; //The panel from which the component which this listener is assigned to belongs to.
		
		public ButtonListener(SelectedPanel parent) {
			this.parent = parent;
		}	
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String person = e.getActionCommand();
			
			Object caller = e.getSource(); //The component that called this.
			
			if(caller instanceof Component) { //Check to make sure we should actually remove this.
				this.parent.remove((Component) caller); //Remove the component from the parent panel.
				this.parent.repaint();
			}
			
			else {
				throw new RuntimeException(); //Some kind of error.
			}
			
		}
		
	}
		
}
