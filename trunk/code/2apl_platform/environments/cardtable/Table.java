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
	leftDisplayPane = new JPanel(new GridBagLayout());
	playersPane = new JPanel[5];
	cardsPane = new JPanel[5];

	for(int i=0; i<5; i++) {
	  playersPane[i] = new JPanel(new GridBagLayout());
	  cardsPane[i] = new JPanel(new GridBagLayout());
	  GridBagConstraints c = new GridBagConstraints();
	  c.fill = GridBagConstraints.HORIZONTAL;
	  c.anchor = GridBagConstraints.CENTER;
	  c.gridx = 0;
	  c.gridy = i;
	  leftDisplayPane.add(playersPane[i], c);
	  c.gridx = 1;
	  leftDisplayPane.add(cardsPane[i], c);
	}

	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	layeredTablePane = new JLayeredPane();
	Point origin = new Point(10, 20);
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
 	  label.setBackground(Color.gray);
 	  label.setForeground(Color.black);
 	  label.setBorder(BorderFactory.createLineBorder(Color.black));
 	  label.setBounds(origin.x, origin.y, 73, 97);

	  layeredTablePane.add(label, new Integer(i));
	}
	
	infoTextArea = new JTextArea(5,00);
	infoTextArea.setEditable(false);
	GameInfo = new JScrollPane(infoTextArea);

	VerticalSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, layeredTablePane,GameInfo);
	//VerticalSplitPane.setPreferredSize(new Dimension(150,50));
	VerticalSplitPane.setOneTouchExpandable(false);
	VerticalSplitPane.setDividerLocation(450);
       
	HorizontalSplitPlane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftDisplayPane,VerticalSplitPane );
	//HorizontalSplitPlane.setPreferredSize(new Dimension(150,150));
	HorizontalSplitPlane.setOneTouchExpandable(false);
	HorizontalSplitPlane.setDividerLocation(900);

	getContentPane().add(HorizontalSplitPlane);
	pack();
	setSize( 1280, 768 );
        setVisible( true ); 
    }

        public void displayHand(String name, int position, Card[] cards) {
	  for(int i=0; i<8; i++) {
	    cardsPane[position].add(new JLabel(cards[i].getCardImage()), i);	    
	  }
	  setVisible( true );
  	}

	public void playedCard(String name, int position, Card card) {
	  JLabel label = (JLabel) layeredTablePane.getComponent(position);
	  label.setIcon(card.getCardImage());
	  setVisible( true );
	}

	public void addPlayer(String name, int position, int score, int bid) {
	  Integer scoreInt = new Integer(score);
	  Integer bidInt = new Integer(bid);

	  java.net.URL imgURL = getClass().getResource("cards/b.gif");
	  ImageIcon cardBack = new ImageIcon(imgURL);
	  
	  GridBagConstraints c = new GridBagConstraints();
	  c.fill = GridBagConstraints.HORIZONTAL;
	  c.anchor = GridBagConstraints.CENTER;
	  c.gridx = 0;
	  c.gridy = 0;
	  playersPane[position].add(new JLabel("Player: "+name), c);
	  c.gridy = 1;
	  playersPane[position].add(new JLabel("Bid: "+bidInt.toString()), c);
	  c.gridy = 2;
	  playersPane[position].add(new JLabel(new String("Points: -")), c);
	  c.gridy = 3;
	  playersPane[position].add(new JLabel("Score: "+scoreInt.toString()), c);
	
	  c.fill = GridBagConstraints.VERTICAL;
	  c.anchor = GridBagConstraints.CENTER;
	  c.gridx = 0;
	  c.gridy = 0;
	  cardsPane[position].add(new JLabel(cardBack), c);
	  c.gridx = 1;
	  cardsPane[position].add(new JLabel(cardBack), c);
	  c.gridx = 2;
	  cardsPane[position].add(new JLabel(cardBack), c);
	  c.gridx = 3;
	  cardsPane[position].add(new JLabel(cardBack), c);
	  c.gridx = 4;
	  cardsPane[position].add(new JLabel(cardBack), c);
	  c.gridx = 5;
	  cardsPane[position].add(new JLabel(cardBack), c);
	  c.gridx = 6;
	  cardsPane[position].add(new JLabel(cardBack), c);
	  c.gridx = 7;
	  cardsPane[position].add(new JLabel(cardBack), c);

	  setVisible( true );
    }
      
      public void updateScore(String name, int position, int score) {
	Integer scoreInt = new Integer(score);
	JLabel scoreLab = (JLabel) playersPane[position].getComponent(3);
	scoreLab.setText("Score: "+scoreInt.toString());
	setVisible( true );
      }

      public void updateBid(String name, int position, int bid) {
	Integer bidInt = new Integer(bid);
	JLabel bidLab = (JLabel) playersPane[position].getComponent(1);
	bidLab.setText("Bid: "+bidInt.toString());
	setVisible( true );
      }

      public void writeLog(String text) {
	infoTextArea.append(text+"\n");
	infoTextArea.setCaretPosition(infoTextArea.getDocument().getLength());
      }
}







