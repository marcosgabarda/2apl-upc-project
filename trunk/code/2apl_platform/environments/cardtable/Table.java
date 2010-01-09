package cardtable;

import javax.swing.*;
import java.awt.*;



public class Table extends JFrame{

    public JLabel[][] PlayerCards = new JLabel[8][5];

    private JPanel CardDisplayPane;
    private JLayeredPane TablePane;
    private JSplitPane VerticalSplitPane;
    private JScrollPane GameInfo;
    private JSplitPane HorizontalSplitPlane;
    private JTextArea Info;

    private ImageIcon CardBack = new ImageIcon("cards/b.gif");


	public Table( ){
		super( "Briscola Chiamata" );
    //----------GUI STUFF----------------------------



        CardDisplayPane = new JPanel();


        TablePane = new JLayeredPane();



         Point origin = new Point(50, 20);
         Color[] layerColors = { Color.yellow, Color.magenta,
                                    Color.cyan,   Color.red,
                                    Color.green };
        //This is the offset for computing the origin for the next label.
        int offset = 35;

        //Add several overlapping, colored labels to the layered pane
        //using absolute positioning/sizing.
        for (int i = 0; i < 5; i++) {
            JLabel label = createColoredLabel("card",
                                              layerColors[i], origin);
            TablePane.add(label, new Integer(i));
            origin.x += offset;
            origin.y += offset;
        }

	// where cards are displayed
	CardDisplayPane.setLayout(new GridBagLayout());

        Info = new JTextArea(5,00);
        Info.setEditable(false);
        GameInfo = new JScrollPane(Info);

        VerticalSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                                    TablePane,GameInfo);

        VerticalSplitPane.setPreferredSize(new Dimension(150,150));
	VerticalSplitPane.setOneTouchExpandable(false);
	VerticalSplitPane.setDividerLocation(400);

       
        HorizontalSplitPlane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                                    CardDisplayPane,VerticalSplitPane );

        //Provide a preferred size for the split pane.
        HorizontalSplitPlane.setPreferredSize(new Dimension(150,150));
	HorizontalSplitPlane.setOneTouchExpandable(false);
	HorizontalSplitPlane.setDividerLocation(600);


 		getContentPane().add(HorizontalSplitPlane);

                setVisible( true );
	}

        private JLabel createColoredLabel(String text,
                                      Color color,
                                      Point origin) {
        JLabel label = new JLabel(text);
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setOpaque(true);
        label.setBackground(color);
        label.setForeground(Color.black);
        label.setBorder(BorderFactory.createLineBorder(Color.black));
        label.setBounds(origin.x, origin.y, 140, 140);

	pack();
	setSize( 1024, 768 );
	setVisible( true );

        return label;
    }


    public JSplitPane getSplitPane() {
        return HorizontalSplitPlane;
    }

    public void addPlayer(String name, int position, int score, int bid) {
        JButton button;
	GridBagConstraints c = new GridBagConstraints();
	GridBagConstraints c1 = new GridBagConstraints();
	Integer scoreInt = new Integer(score);
	Integer bidInt = new Integer(bid);

        JButton P1NameTag = new JButton(name);
	c.fill = GridBagConstraints.HORIZONTAL;
	c.gridx = 0;
	c.gridy = 0;
        c.gridwidth = 4;
        CardDisplayPane.add(P1NameTag, c);

        //Player Score
        JButton P1Score = new JButton("Score : "+ scoreInt.toString());
	c.fill = GridBagConstraints.HORIZONTAL;
	c.gridx = 4;
	c.gridy = 0;
        c.gridwidth = 2;
        CardDisplayPane.add(P1Score, c);

        //Player Bid
        JButton P1Bid = new JButton("Bid : "+ bidInt.toString());
	c.fill = GridBagConstraints.HORIZONTAL;
	c.gridx = 6;
	c.gridy = 0;
        c.gridwidth = 2;
        CardDisplayPane.add(P1Bid, c);

        //1st Card
        JButton P1C1 = new JButton(CardBack);
        P1C1.setPreferredSize(new Dimension(55,80));
	c1.fill = GridBagConstraints.HORIZONTAL;
	c1.gridx = 0;
	c1.gridy = 1;
        CardDisplayPane.add(P1C1, c1);

        //2nd Card
        JButton P1C2 = new JButton(CardBack);
        P1C2.setPreferredSize(new Dimension(55,80));
	c1.fill = GridBagConstraints.HORIZONTAL;
	c1.gridx = 1;
	c1.gridy = 1;
        CardDisplayPane.add(P1C2, c1);

        //3rd Card
        JButton P1C3 = new JButton(CardBack);
        P1C3.setPreferredSize(new Dimension(55,80));
	c1.fill = GridBagConstraints.HORIZONTAL;
	c1.gridx = 2;
	c1.gridy = 1;
        CardDisplayPane.add(P1C3, c1);

        //4th Card
        JButton P1C4 = new JButton(CardBack);
        P1C4.setPreferredSize(new Dimension(55,80));
	c1.fill = GridBagConstraints.HORIZONTAL;
	c1.gridx = 3;
	c1.gridy = 1;
        CardDisplayPane.add(P1C4, c1);

        //5th Card
        JButton P1C5 = new JButton(CardBack);
        P1C5.setPreferredSize(new Dimension(55,80));
	c1.fill = GridBagConstraints.HORIZONTAL;
	c1.gridx = 4;
	c1.gridy = 1;
        CardDisplayPane.add(P1C5, c1);

        //6th Card
        JButton P1C6 = new JButton(CardBack);
        P1C6.setPreferredSize(new Dimension(55,80));
	c1.fill = GridBagConstraints.HORIZONTAL;
	c1.gridx = 5;
	c1.gridy = 1;
        CardDisplayPane.add(P1C6, c1);

        //7th Card
        JButton P1C7 = new JButton(CardBack);
        P1C7.setPreferredSize(new Dimension(55,80));
	c1.fill = GridBagConstraints.HORIZONTAL;
	c1.gridx = 6;
	c1.gridy = 1;
        CardDisplayPane.add(P1C7, c1);

        //8th Card
        JButton P1C8 = new JButton(CardBack);
        P1C8.setPreferredSize(new Dimension(55,80));
	c1.fill = GridBagConstraints.HORIZONTAL;
	c1.gridx = 7;
	c1.gridy = 1;
        CardDisplayPane.add(P1C8, c1);
    }


      public void writeLog(String text) {
	Info.append(text+"\n");
	Info.setCaretPosition(Info.getDocument().getLength());
      }
}







