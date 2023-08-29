package firstgraphicalprogram;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameFrame extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JButton startButton,endButton,rankButton;
	JPanel panel = new JPanel();
	
	public GameFrame() {
		//add new panel 

		this.add(panel);
		
		//set the frame
//		this.setLayout(new BorderLayout(100,500));
		this.setTitle("Medusa");// add the title of the panel
		this.setSize(800,600);
		this.setLocationRelativeTo(null); //the window in the middle
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//set the panel setting in the center of the frame
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 100));
		
		panel.setBackground(Color.cyan);
		
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon("medusaImg.png"));
		label.setBounds(50,30,800,600);
		
		this.add(label);
				
		
		
		// add 3 button into the panel
		startButton=new JButton("Start Game");
		panel.add(startButton); 
		startButton.addActionListener(this);
		
		endButton=new JButton("End Game");
		panel.add(endButton); 
		endButton.addActionListener(this);
		
		rankButton=new JButton("Ranking Top 10");
		panel.add(rankButton); 
		rankButton.addActionListener(this);
		
		//where the panel in the frame
		this.add(panel, BorderLayout.CENTER);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == startButton) {
			StartPanel startPanel = new StartPanel();
			panel.removeAll();
			
			this.add(startPanel);
			//startPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 100));
			this.pack();
			this.setVisible(true);
			 startPanel.requestFocusInWindow(); 
			
		}else if(e.getSource() == endButton) {
			////can't see frame!
			//Destroy the JFrame object
			this.setVisible(false);
			this.dispose();
			
		}else if(e.getSource() == rankButton) {
			RankPanel rankPanel = new RankPanel();
			panel.removeAll();
			this.add(rankPanel);

		}
		
	}

}
