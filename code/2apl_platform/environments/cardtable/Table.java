package cardtable;

import javax.swing.*;
import java.awt.*;
import java.lang.Integer;



public class Table extends JFrame{

    private JPanel leftDisplayPane;
    private JPanel[] playersPane, cardsPane;

    private JLayeredPane layeredPane;
    private JSplitPane VerticalSplitPane;
    private JScrollPane GameInfo;
    private JSplitPane HorizontalSplitPlane;
    private JTextArea infoTextArea;
    private int round;
    private Hand[] hands;

    public Table( ){
	super( "Briscola Chiamata" );
	round = 0;
	hands = new Hand[5];

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

	layeredPane = new JLayeredPane();
	Point origin = new Point(10, 20);
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

	  layeredPane.add(label, new Integer(i));
	}
	JLabel label = new JLabel("Player : -");
	origin.x = 73;
	origin.y += offset+97;
	label.setBounds(origin.x, origin.y, 150, 20);
	layeredPane.add(label, 5);
	label = new JLabel("Round : -");
	origin.x += 150;
	origin.y += 0;
	label.setBounds(origin.x, origin.y, 150, 20);
	layeredPane.add(label, 6);
	label = new JLabel("Briscola : -");
	origin.x = 73;
	origin.y += 40;
	label.setBounds(origin.x, origin.y, 300, 20);
	layeredPane.add(label, 7);
	
	infoTextArea = new JTextArea(5,00);
	infoTextArea.setEditable(false);
	GameInfo = new JScrollPane(infoTextArea);

	VerticalSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, layeredPane,GameInfo);
	VerticalSplitPane.setOneTouchExpandable(false);
	VerticalSplitPane.setDividerLocation(450);
       
	HorizontalSplitPlane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftDisplayPane,VerticalSplitPane );
	HorizontalSplitPlane.setOneTouchExpandable(false);
	HorizontalSplitPlane.setDividerLocation(900);

	getContentPane().add(HorizontalSplitPlane);
	pack();
	setSize( 1280, 768 );
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

	public void displayHand(String name, int position, Hand hand) {
	  hands[position] = hand;
	  for(int i=0; i<8; i++) {
	    cardsPane[position].add(new JLabel(hand.getCard(i).getCardImage()), i);	    
	  }
	  setVisible( true );
  	}

	public void playedCard(String name, int position, Card card) {
	  java.net.URL imgURL = getClass().getResource("cards/b.gif");
	  ImageIcon cardBack = new ImageIcon(imgURL);

	  int pos = hands[position].findCard(card);
	  JLabel imgLab = (JLabel) cardsPane[position].getComponent(pos);
	  imgLab.setIcon(cardBack);

	  JLabel label = (JLabel) layeredPane.getComponent(position);
	  label.setIcon(card.getCardImage());
	  JLabel player = (JLabel) layeredPane.getComponent(5);
	  player.setText("Player : "+name);
	  if(round==0) {
	    JLabel roundLab = (JLabel) layeredPane.getComponent(6);
	    roundLab.setText("Round : 1");
	    round++;
	  }
	  setVisible( true );
	}     

      public void briscolaDeclared(String name, int position, Card card) {
	JLabel briscolaLab = (JLabel) layeredPane.getComponent(7);
	briscolaLab.setText("Briscola : "+card.getRank().getName()+" of "+card.getSuit().getName()+" by "+name);
	JLabel declarer = (JLabel) playersPane[position].getComponent(0);
	declarer.setForeground(Color.blue);
	for(int i=0;i<5;i++) {
	  if(hands[i].findCard(card)!=-1) {
	    int pos = hands[i].findCard(card);
	    JLabel teammate = (JLabel) playersPane[i].getComponent(0);
	    teammate.setForeground(Color.blue);
	  }
	}
	setVisible( true );
      }
 
      public void updateScore(String name, int position, int score) {
	Integer scoreInt = new Integer(score);
	JLabel scoreLab = (JLabel) playersPane[position].getComponent(3);
	scoreLab.setText("Score: "+scoreInt.toString());
	setVisible( true );
      }

      public void updatePoints(String name, int position, int points) {
	java.net.URL imgURL = getClass().getResource("cards/b.gif");
	  ImageIcon cardBack = new ImageIcon(imgURL);

	Integer pointsInt = new Integer(points);
	JLabel pointsLab = (JLabel) playersPane[position].getComponent(2);
	pointsLab.setText("Points: "+pointsInt.toString());
	JLabel roundLab = (JLabel) layeredPane.getComponent(6);
	round++;
	if(round<9) {
	  Integer roundInt = new Integer(round);
	  roundLab.setText("Round : "+roundInt.toString());
	}
	for (int i = 0; i < 5; i++) {
	JLabel cardLab = (JLabel) layeredPane.getComponent(i);
	  cardLab.setIcon(cardBack);
	}
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







