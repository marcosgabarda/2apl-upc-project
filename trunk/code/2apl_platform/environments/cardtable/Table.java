package cardtable;

import javax.swing.*;
import java.awt.*;



public class Table extends JFrame{

    private JPanel leftDisplayPane;
    private JPanel[] playersPane, cardsPane;

    private JLayeredPane layeredTablePane;
    private JSplitPane VerticalSplitPane;
    private JScrollPane GameInfo;
    private JSplitPane HorizontalSplitPlane;
    private JTextArea infoTextArea;

    public Table( ){
	super( "Briscola Chiamata" );
	leftDisplayPane = new JPanel(new GridLayout(5,2));
	playersPane = new JPanel[5];
	cardsPane = new JPanel[5];

	for(int i=0; i<5; i++) {
	  playersPane[i] = new JPanel(new GridLayout(4,1));
	  cardsPane[i] = new JPanel(new GridLayout(1,8));
	  leftDisplayPane.add(playersPane[i]);
	  leftDisplayPane.add(cardsPane[i]);
	}

	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	layeredTablePane = new JLayeredPane();
	Point origin = new Point(50, 20);
	Color[] layerColors = { Color.yellow, Color.magenta,
				Color.cyan,   Color.red,
				Color.green };

	int offset = 35;
	for (int i = 0; i < 5; i++) {
	  JLabel label = new JLabel();
	  
	  origin.x += offset;
	  origin.y += offset;

	  label.setVerticalAlignment(JLabel.TOP);
	  label.setHorizontalAlignment(JLabel.CENTER);
	  label.setOpaque(true);
	  label.setBackground(Color.red);
	  label.setForeground(Color.black);
	  label.setBorder(BorderFactory.createLineBorder(Color.black));
	  label.setBounds(origin.x, origin.y, 140, 140);

	  layeredTablePane.add(label, new Integer(i));
	}
	
	infoTextArea = new JTextArea(5,00);
	infoTextArea.setEditable(false);
	GameInfo = new JScrollPane(infoTextArea);

	VerticalSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, layeredTablePane,GameInfo);
	VerticalSplitPane.setPreferredSize(new Dimension(150,150));
	VerticalSplitPane.setOneTouchExpandable(false);
	VerticalSplitPane.setDividerLocation(400);
       
	HorizontalSplitPlane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftDisplayPane,VerticalSplitPane );
	HorizontalSplitPlane.setPreferredSize(new Dimension(150,150));
	HorizontalSplitPlane.setOneTouchExpandable(false);
	HorizontalSplitPlane.setDividerLocation(600);

	getContentPane().add(HorizontalSplitPlane);
	setVisible( true );
    }

        public void playCard(String name, int position) {

	  java.net.URL imgURL = getClass().getResource("cards/b.gif");
	  ImageIcon cardBack = new ImageIcon(imgURL);

	  cardsPane[position].add(new JLabel(cardBack), position);
  	}


	public void addPlayer(String name, int position, int score, int bid) {
	  Integer scoreInt = new Integer(score);
	  Integer bidInt = new Integer(bid);

	  java.net.URL imgURL = getClass().getResource("cards/b.gif");
	  ImageIcon cardBack = new ImageIcon(imgURL);
	  
	  playersPane[position].add(new JLabel("Player: "+name));
	  playersPane[position].add(new JLabel("Bid: "+bidInt.toString()));
	  playersPane[position].add(new JLabel(new String("Points: points here")));
	  playersPane[position].add(new JLabel("Score: "+scoreInt.toString()));
	
	  cardsPane[position].add(new JLabel(cardBack));
	  cardsPane[position].add(new JLabel(cardBack));
	  cardsPane[position].add(new JLabel(cardBack));
	  cardsPane[position].add(new JLabel(cardBack));
	  cardsPane[position].add(new JLabel(cardBack));
	  cardsPane[position].add(new JLabel(cardBack));
	  cardsPane[position].add(new JLabel(cardBack));
	  cardsPane[position].add(new JLabel(cardBack));
    }
      
      public void updateScore(String name, int position, int score) {
	Integer scoreInt = new Integer(score);
	JLabel scoreLab = (JLabel) playersPane[position].getComponent(4);
	scoreLab.setText(scoreInt.toString());
      }

      public void updateBid(String name, int position, int bid) {
	Integer bidInt = new Integer(bid);
	JLabel bidLab = (JLabel) playersPane[position].getComponent(2);
	bidLab.setText(bidInt.toString());
      }

      public void writeLog(String text) {
	infoTextArea.append(text+"\n");
	infoTextArea.setCaretPosition(infoTextArea.getDocument().getLength());
      }
}







