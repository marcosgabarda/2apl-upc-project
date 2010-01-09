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

ImageIcon CardBack = new ImageIcon("cards/b.gif");


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






        Info = new JTextArea(5,00);
        Info.setEditable(false);
        Info.append("Player1 has connected \n");
        Info.append("Player2 has connected \n");
        Info.append("Player3 has connected \n");
        Info.append("Player4 has connected \n");
        Info.append("Player5 has connected \n");
        Info.append("Game Started! \n");
        Info.append("Bidding Started! \n");
        Info.append("Player 1 Bids.... \n");
        Info.append("You get the point Fab \n");

        GameInfo = new JScrollPane(Info);

        VerticalSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                                    TablePane,GameInfo);

        VerticalSplitPane.setPreferredSize(new Dimension(360,600));

        //AddPlayersGUI();
        addComponentsToPane(CardDisplayPane);



        HorizontalSplitPlane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                                    CardDisplayPane,VerticalSplitPane );

        //Provide a preferred size for the split pane.
        HorizontalSplitPlane.setPreferredSize(new Dimension(800,600));

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
        return label;
    }


    public JSplitPane getSplitPane() {
        return HorizontalSplitPlane;
    }


    public static void addComponentsToPane(Container pane) {
        JButton button;
	pane.setLayout(new GridBagLayout());
	GridBagConstraints c = new GridBagConstraints();
	GridBagConstraints c1 = new GridBagConstraints();
	GridBagConstraints c2 = new GridBagConstraints();
        GridBagConstraints c3 = new GridBagConstraints();
        GridBagConstraints c4 = new GridBagConstraints();
	GridBagConstraints c5 = new GridBagConstraints();
	GridBagConstraints c6 = new GridBagConstraints();
        GridBagConstraints c7 = new GridBagConstraints();
        GridBagConstraints c8 = new GridBagConstraints();
        GridBagConstraints c9 = new GridBagConstraints();


        ImageIcon Image = new ImageIcon("cards/3s.gif");
        ImageIcon Image2 = new ImageIcon("cards/2c.gif");
        ImageIcon Image3 = new ImageIcon("cards/7d.gif");
        ImageIcon Image4 = new ImageIcon("cards/5s.gif");

//Fill in 1st Player

        //Player Name
        JButton P1NameTag = new JButton("Player1");
	c.fill = GridBagConstraints.HORIZONTAL;
	c.gridx = 0;
	c.gridy = 0;
        c.gridwidth = 4;
        pane.add(P1NameTag, c);

        //Player Score
        JButton P1Score = new JButton("Score : X");
	c.fill = GridBagConstraints.HORIZONTAL;
	c.gridx = 4;
	c.gridy = 0;
        c.gridwidth = 2;
        pane.add(P1Score, c);

        //Player Bid
        JButton P1Bid = new JButton("Bid : X");
	c.fill = GridBagConstraints.HORIZONTAL;
	c.gridx = 6;
	c.gridy = 0;
        c.gridwidth = 2;
        pane.add(P1Bid, c);





        //1st Card
        JButton P1C1 = new JButton(Image);
        P1C1.setPreferredSize(new Dimension(55,80));
	c1.fill = GridBagConstraints.HORIZONTAL;
	c1.gridx = 0;
	c1.gridy = 1;
        pane.add(P1C1, c1);

        //2nd Card
        JButton P1C2 = new JButton(Image);
        P1C2.setPreferredSize(new Dimension(55,80));
	c1.fill = GridBagConstraints.HORIZONTAL;
	c1.gridx = 1;
	c1.gridy = 1;
        pane.add(P1C2, c1);

        //3rd Card
        JButton P1C3 = new JButton(Image);
        P1C3.setPreferredSize(new Dimension(55,80));
	c1.fill = GridBagConstraints.HORIZONTAL;
	c1.gridx = 2;
	c1.gridy = 1;
        pane.add(P1C3, c1);

        //4th Card
        JButton P1C4 = new JButton(Image);
        P1C4.setPreferredSize(new Dimension(55,80));
	c1.fill = GridBagConstraints.HORIZONTAL;
	c1.gridx = 3;
	c1.gridy = 1;
        pane.add(P1C4, c1);

        //5th Card
        JButton P1C5 = new JButton(Image);
        P1C5.setPreferredSize(new Dimension(55,80));
	c1.fill = GridBagConstraints.HORIZONTAL;
	c1.gridx = 4;
	c1.gridy = 1;
        pane.add(P1C5, c1);

        //6th Card
        JButton P1C6 = new JButton(Image);
        P1C6.setPreferredSize(new Dimension(55,80));
	c1.fill = GridBagConstraints.HORIZONTAL;
	c1.gridx = 5;
	c1.gridy = 1;
        pane.add(P1C6, c1);

        //7th Card
        JButton P1C7 = new JButton(Image);
        P1C7.setPreferredSize(new Dimension(55,80));
	c1.fill = GridBagConstraints.HORIZONTAL;
	c1.gridx = 6;
	c1.gridy = 1;
        pane.add(P1C7, c1);

        //8th Card
        JButton P1C8 = new JButton(Image);
        P1C8.setPreferredSize(new Dimension(55,80));
	c1.fill = GridBagConstraints.HORIZONTAL;
	c1.gridx = 7;
	c1.gridy = 1;
        pane.add(P1C8, c1);


 //Fill in 2nd Player

        //Player Name
        JButton P2NameTag = new JButton("Player2");
	c2.fill = GridBagConstraints.HORIZONTAL;
	c2.gridx = 0;
	c2.gridy = 2;
        c2.gridwidth = 4;
        pane.add(P2NameTag, c2);

        //Player Score
        JButton P2Score = new JButton("Score : X");
	c2.fill = GridBagConstraints.HORIZONTAL;
	c2.gridx = 4;
	c2.gridy = 2;
        c2.gridwidth = 2;
        pane.add(P2Score, c2);

        //Player Bid
        JButton P2Bid = new JButton("Bid : X");
	c2.fill = GridBagConstraints.HORIZONTAL;
	c2.gridx = 6;
	c2.gridy = 2;
        c2.gridwidth = 2;
        pane.add(P2Bid, c2);


        //1st Card
        JButton P2C1 = new JButton(Image);
        P2C1.setPreferredSize(new Dimension(55,80));
	c3.fill = GridBagConstraints.HORIZONTAL;
	c3.gridx = 0;
	c3.gridy = 3;
        pane.add(P2C1, c3);

        //2nd Card
        JButton P2C2 = new JButton(Image);
        P2C2.setPreferredSize(new Dimension(55,80));
	c3.fill = GridBagConstraints.HORIZONTAL;
	c3.gridx = 1;
	c3.gridy = 3;
        pane.add(P2C2, c3);

        //3rd Card
        JButton P2C3 = new JButton(Image);
        P2C3.setPreferredSize(new Dimension(55,80));
	c3.fill = GridBagConstraints.HORIZONTAL;
	c3.gridx = 2;
	c3.gridy = 3;
        pane.add(P2C3, c3);

        //4th Card
        JButton P2C4 = new JButton(Image);
        P2C4.setPreferredSize(new Dimension(55,80));
	c3.fill = GridBagConstraints.HORIZONTAL;
	c3.gridx = 3;
	c3.gridy = 3;
        pane.add(P2C4, c3);

        //5th Card
        JButton P2C5 = new JButton(Image);
        P2C5.setPreferredSize(new Dimension(55,80));
	c3.fill = GridBagConstraints.HORIZONTAL;
	c3.gridx = 4;
	c3.gridy = 3;
        pane.add(P2C5, c3);

        //6th Card
        JButton P2C6 = new JButton(Image);
        P2C6.setPreferredSize(new Dimension(55,80));
	c3.fill = GridBagConstraints.HORIZONTAL;
	c3.gridx = 5;
	c3.gridy = 3;
        pane.add(P2C6, c3);

        //7th Card
        JButton P2C7 = new JButton(Image);
        P2C7.setPreferredSize(new Dimension(55,80));
	c3.fill = GridBagConstraints.HORIZONTAL;
	c3.gridx = 6;
	c3.gridy = 3;
        pane.add(P2C7, c3);

        //8th Card
        JButton P2C8 = new JButton(Image);
        P2C8.setPreferredSize(new Dimension(55,80));
	c3.fill = GridBagConstraints.HORIZONTAL;
	c3.gridx = 7;
	c3.gridy = 3;
        pane.add(P2C8, c3);


 //Fill in 3rd Player

        //Player Name
        JButton P3NameTag = new JButton("Player3");
	c4.fill = GridBagConstraints.HORIZONTAL;
	c4.gridx = 0;
	c4.gridy = 4;
        c4.gridwidth = 4;
        pane.add(P3NameTag, c4);

        //Player Score
        JButton P3Score = new JButton("Score : X");
	c4.fill = GridBagConstraints.HORIZONTAL;
	c4.gridx = 4;
	c4.gridy = 4;
        c4.gridwidth = 2;
        pane.add(P3Score, c4);

        //Player Bid
        JButton P3Bid = new JButton("Bid : X");
	c4.fill = GridBagConstraints.HORIZONTAL;
	c4.gridx = 6;
	c4.gridy = 4;
        c4.gridwidth = 2;
        pane.add(P3Bid, c4);


        //1st Card
        JButton P3C1 = new JButton(Image);
        P3C1.setPreferredSize(new Dimension(55,80));
	c5.fill = GridBagConstraints.HORIZONTAL;
	c5.gridx = 0;
	c5.gridy = 5;
        pane.add(P3C1, c5);

        //2nd Card
        JButton P3C2 = new JButton(Image);
        P3C2.setPreferredSize(new Dimension(55,80));
	c5.fill = GridBagConstraints.HORIZONTAL;
	c5.gridx = 1;
	c5.gridy = 5;
        pane.add(P3C2, c5);

        //3rd Card
        JButton P3C3 = new JButton(Image);
        P3C3.setPreferredSize(new Dimension(55,80));
	c5.fill = GridBagConstraints.HORIZONTAL;
	c5.gridx = 2;
	c5.gridy = 5;
        pane.add(P3C3, c5);

        //4th Card
        JButton P3C4 = new JButton(Image);
        P3C4.setPreferredSize(new Dimension(55,80));
	c5.fill = GridBagConstraints.HORIZONTAL;
	c5.gridx = 3;
	c5.gridy = 5;
        pane.add(P3C4, c5);
        //5th Card
        JButton P3C5 = new JButton(Image);
        P3C5.setPreferredSize(new Dimension(55,80));
	c5.fill = GridBagConstraints.HORIZONTAL;
	c5.gridx = 4;
	c5.gridy = 5;
        pane.add(P3C5, c5);

        //6th Card
        JButton P3C6 = new JButton(Image);
        P3C6.setPreferredSize(new Dimension(55,80));
	c5.fill = GridBagConstraints.HORIZONTAL;
	c5.gridx = 5;
	c5.gridy = 5;
        pane.add(P3C6, c5);

        //7th Card
        JButton P3C7 = new JButton(Image);
        P3C7.setPreferredSize(new Dimension(55,80));
	c5.fill = GridBagConstraints.HORIZONTAL;
	c5.gridx = 6;
	c5.gridy = 5;
        pane.add(P3C7, c5);

        //8th Card
        JButton P3C8 = new JButton(Image);
        P3C8.setPreferredSize(new Dimension(55,80));
	c5.fill = GridBagConstraints.HORIZONTAL;
	c5.gridx = 7;
	c5.gridy = 5;
        pane.add(P3C8, c5);



 //Fill in 4th Player

        //Player Name
        JButton P4NameTag = new JButton("Player4");
	c6.fill = GridBagConstraints.HORIZONTAL;
	c6.gridx = 0;
	c6.gridy = 6;
        c6.gridwidth = 4;
        pane.add(P4NameTag, c6);

        //Player Score
        JButton P4Score = new JButton("Score : X");
	c6.fill = GridBagConstraints.HORIZONTAL;
	c6.gridx = 4;
	c6.gridy = 6;
        c6.gridwidth = 2;
        pane.add(P4Score, c6);

        //Player Bid
        JButton P4Bid = new JButton("Bid : X");
	c6.fill = GridBagConstraints.HORIZONTAL;
	c6.gridx = 6;
	c6.gridy = 6;
        c6.gridwidth = 2;
        pane.add(P4Bid, c6);


        //1st Card
        JButton P4C1 = new JButton(Image);
        P4C1.setPreferredSize(new Dimension(55,80));
	c7.fill = GridBagConstraints.HORIZONTAL;
	c7.gridx = 0;
	c7.gridy = 7;
        pane.add(P4C1, c7);

        //2nd Card
        JButton P4C2 = new JButton(Image);
        P4C2.setPreferredSize(new Dimension(55,80));
	c7.fill = GridBagConstraints.HORIZONTAL;
	c7.gridx = 1;
	c7.gridy = 7;
        pane.add(P4C2, c7);

        //3rd Card
        JButton P4C3 = new JButton(Image);
        P4C3.setPreferredSize(new Dimension(55,80));
	c7.fill = GridBagConstraints.HORIZONTAL;
	c7.gridx = 2;
	c7.gridy = 7;
        pane.add(P4C3, c7);

        //4th Card
        JButton P4C4 = new JButton(Image);
        P4C4.setPreferredSize(new Dimension(55,80));
	c7.fill = GridBagConstraints.HORIZONTAL;
	c7.gridx = 3;
	c7.gridy = 7;
        pane.add(P4C4, c7);
        //5th Card
        JButton P4C5 = new JButton(Image);
        P4C5.setPreferredSize(new Dimension(55,80));
	c7.fill = GridBagConstraints.HORIZONTAL;
	c7.gridx = 4;
	c7.gridy = 7;
        pane.add(P4C5, c7);

        //6th Card
        JButton P4C6 = new JButton(Image);
        P4C6.setPreferredSize(new Dimension(55,80));
	c7.fill = GridBagConstraints.HORIZONTAL;
	c7.gridx = 5;
	c7.gridy = 7;
        pane.add(P4C6, c7);

        //7th Card
        JButton P4C7 = new JButton(Image);
        P4C7.setPreferredSize(new Dimension(55,80));
	c7.fill = GridBagConstraints.HORIZONTAL;
	c7.gridx = 6;
	c7.gridy = 7;
        pane.add(P4C7, c7);

        //8th Card
        JButton P4C8 = new JButton(Image);
        P4C8.setPreferredSize(new Dimension(55,80));
	c7.fill = GridBagConstraints.HORIZONTAL;
	c7.gridx = 7;
	c7.gridy = 7;
        pane.add(P4C8, c7);




 //Fill in 5th Player

        //Player Name
        JButton P5NameTag = new JButton("Player5");
	c8.fill = GridBagConstraints.HORIZONTAL;
	c8.gridx = 0;
	c8.gridy = 8;
        c8.gridwidth = 4;
        pane.add(P5NameTag, c8);

        //Player Score
        JButton P5Score = new JButton("Score : X");
	c8.fill = GridBagConstraints.HORIZONTAL;
	c8.gridx = 4;
	c8.gridy = 8;
        c8.gridwidth = 2;
        pane.add(P5Score, c8);

        //Player Bid
        JButton P5Bid = new JButton("Bid : X");
	c8.fill = GridBagConstraints.HORIZONTAL;
	c8.gridx = 6;
	c8.gridy = 8;
        c8.gridwidth = 2;
        pane.add(P5Bid, c8);


        //1st Card
        JButton P5C1 = new JButton(Image);
        P5C1.setPreferredSize(new Dimension(55,80));
	c9.fill = GridBagConstraints.HORIZONTAL;
	c9.gridx = 0;
	c9.gridy = 9;
        pane.add(P5C1, c9);

        //2nd Card
        JButton P5C2 = new JButton(Image);
        P5C2.setPreferredSize(new Dimension(55,80));
	c9.fill = GridBagConstraints.HORIZONTAL;
	c9.gridx = 1;
	c9.gridy = 9;
        pane.add(P5C2, c9);

        //3rd Card
        JButton P5C3 = new JButton(Image);
        P5C3.setPreferredSize(new Dimension(55,80));
	c9.fill = GridBagConstraints.HORIZONTAL;
	c9.gridx = 2;
	c9.gridy = 9;
        pane.add(P5C3, c9);

        //4th Card
        JButton P5C4 = new JButton(Image);
        P5C4.setPreferredSize(new Dimension(55,80));
	c9.fill = GridBagConstraints.HORIZONTAL;
	c9.gridx = 3;
	c9.gridy = 9;
        pane.add(P5C4, c9);
        //5th Card
        JButton P5C5 = new JButton(Image);
        P5C5.setPreferredSize(new Dimension(55,80));
	c9.fill = GridBagConstraints.HORIZONTAL;
	c9.gridx = 4;
	c9.gridy = 9;
        pane.add(P5C5, c9);

        //6th Card
        JButton P5C6 = new JButton(Image);
        P5C6.setPreferredSize(new Dimension(55,80));
	c9.fill = GridBagConstraints.HORIZONTAL;
	c9.gridx = 5;
	c9.gridy = 9;
        pane.add(P5C6, c9);
        //7th Card
        JButton P5C7 = new JButton(Image);
        P5C7.setPreferredSize(new Dimension(55,80));
	c9.fill = GridBagConstraints.HORIZONTAL;
	c9.gridx = 6;
	c9.gridy = 9;
        pane.add(P5C7, c9);

        //8th Card
        JButton P5C8 = new JButton(Image);
        P5C8.setPreferredSize(new Dimension(55,80));
	c9.fill = GridBagConstraints.HORIZONTAL;
	c9.gridx = 7;
	c9.gridy = 9;
        pane.add(P5C8, c9);





/*
	button = new JButton("5");
	c.fill = GridBagConstraints.HORIZONTAL;
	c.ipady = 0;       //reset to default
	c.weighty = 1.0;   //request any extra vertical space
	c.anchor = GridBagConstraints.PAGE_END; //bottom of space
	c.insets = new Insets(10,0,0,0);  //top padding
	c.gridx = 1;       //aligned with button 2
	c.gridwidth = 2;   //2 columns wide
	c.gridy = 2;       //third row
	pane.add(button, c);
 * */

	}    
}







