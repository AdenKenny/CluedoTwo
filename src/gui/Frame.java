package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import cluedo.Board;

public class Frame extends JFrame implements MouseListener {

	Canvas canvas;

	public Frame(Board board) {
		super("Cluedo");
		this.canvas = new Canvas(board);
		setPreferredSize(new Dimension(1000, 790));
		setLayout(new BorderLayout()); // use border layout
		add(canvas, BorderLayout.CENTER); // add canvas
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack(); // pack components tightly together
		setResizable(false); // prevent us from being resizeable
		setVisible(true);
		addMouseListener(this);
	}

	public Canvas getCanvas() {
		return canvas;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();
		if (mouseX >= 32 && mouseX <= 776 && mouseY >= 6 && mouseY <= 773) {
			canvas.x((mouseX - 30) / 31);
			canvas.y((int)((mouseY - 6) / 29.5) - 1);
		}
		canvas.repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//not used
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//not used
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		//not used
	}

	@Override
	public void mouseExited(MouseEvent e) {
		//not used
	}
}
