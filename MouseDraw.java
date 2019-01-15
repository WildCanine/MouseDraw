package mousedraw;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
public class MouseDraw extends JFrame{
    JMenuBar mainMenuBar=new JMenuBar();
    JMenu fileMenu=new JMenu("File");
    JMenu boardMenu=new JMenu("BoardColor");
    JMenu pensizeMenu=new JMenu("PenSize");
    JMenuItem newMenuItem=new JMenuItem("New");
    JMenuItem exitMenuItem=new JMenuItem("Exit");
    JMenuItem blackMenuItem=new JMenuItem("Black");
    JMenuItem yellowMenuItem=new JMenuItem("Yellow");
    JMenuItem redMenuItem=new JMenuItem("Red");
    JMenuItem fineMenuItem=new JMenuItem("Fine");
    JMenuItem smallMenuItem=new JMenuItem("Small");
    JMenuItem mediumMenuItem=new JMenuItem("Medium");
    JMenuItem largeMenuItem=new JMenuItem("Large");
    JPanel drawPanel=new JPanel();
    JLabel leftColorLabel=new JLabel();
    JLabel rightColorLabel=new JLabel();
    JPanel colorPanel=new JPanel();
    JLabel[] colorLabel=new JLabel[18];
    Color drawColor, leftColor, rightColor;
    double xPrevious, yPrevious;
    Graphics2D g2D;
    public MouseDraw() {
        setTitle("Matthew_004");
        setResizable(false);
        setSize(800, 600);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                exitForm(e);
            }
        });
        getContentPane().setLayout(new GridBagLayout());
        setJMenuBar(mainMenuBar);
        fileMenu.setMnemonic('F');
        mainMenuBar.add(fileMenu);
        fileMenu.add(newMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);
        newMenuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                newMenuItemActionPerformed(e);
            }
        });
        exitMenuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                exitMenuItemActionPerformed(e);
            }
        });
        boardMenu.setMnemonic('B');
        mainMenuBar.add(boardMenu);
        boardMenu.add(blackMenuItem);
        boardMenu.addSeparator();
        blackMenuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                boardColor(e);
            }


        });
        yellowMenuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                boardColor(e);
            }


        });
        fineMenuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                boardColor(e);
            }


        });
        smallMenuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                penSize(e);
            }


        });
        mediumMenuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                penSize(e);
            }


        });
        largeMenuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                penSize(e);
            }


        });
        largeMenuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                penSize(e);
            }


        });
        boardMenu.add(yellowMenuItem);
        boardMenu.addSeparator();
        boardMenu.add(redMenuItem);
        pensizeMenu.setMnemonic('P');
        mainMenuBar.add(pensizeMenu);
        pensizeMenu.add(fineMenuItem);
        pensizeMenu.addSeparator();
        pensizeMenu.add(smallMenuItem);
        pensizeMenu.addSeparator();
        pensizeMenu.add(mediumMenuItem);
        pensizeMenu.addSeparator();
        pensizeMenu.add(largeMenuItem);
        drawPanel.setPreferredSize(new Dimension(500, 400));
        drawPanel.setBackground(new Color(200, 200, 200));
        GridBagConstraints gridConstraints=new GridBagConstraints();
        gridConstraints.gridx=0;
        gridConstraints.gridy=0;
        gridConstraints.gridheight=2;
        gridConstraints.fill =GridBagConstraints.BOTH;
        gridConstraints.weightx =1;
        gridConstraints.weighty = 2;
        gridConstraints.insets=new Insets(10, 10, 10, 10);
        getContentPane().add(drawPanel, gridConstraints);
        drawPanel.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                drawPanelMousePressed(e);
            }
        });
        drawPanel.addMouseMotionListener(new MouseMotionAdapter()
        {
            public void mouseDragged(MouseEvent e)
            {
                drawPanelMouseDragged(e);
            }
        });
        drawPanel.addMouseListener(new MouseAdapter()
        {
            public void mouseReleased(MouseEvent e)
            {
                drawPanelMouseReleased(e);
            }
        });

        leftColorLabel.setPreferredSize(new Dimension(40, 40));
        leftColorLabel.setOpaque(true);
        gridConstraints=new GridBagConstraints();
        gridConstraints.gridx=1;
        gridConstraints.gridy=0;
        gridConstraints.anchor=GridBagConstraints.NORTH;
        gridConstraints.insets=new Insets(10, 5, 10, 10);
        getContentPane().add(leftColorLabel, gridConstraints);
        rightColorLabel.setPreferredSize(new Dimension(40, 40));
        rightColorLabel.setOpaque(true);
        gridConstraints=new GridBagConstraints();
        gridConstraints.gridx=2;
        gridConstraints.gridy=0;
        gridConstraints.anchor=GridBagConstraints.SOUTH;
        gridConstraints.insets=new Insets(10, 5, 10, 40);
        getContentPane().add(rightColorLabel, gridConstraints);
        colorPanel.setPreferredSize(new Dimension(80,320));
        colorPanel.setBorder(BorderFactory.createTitledBorder("Colors"));
        gridConstraints=new GridBagConstraints();
        gridConstraints.gridx=1;
        gridConstraints.gridy=1;
        gridConstraints.gridwidth=2;
        gridConstraints.anchor=GridBagConstraints.NORTH;
        gridConstraints.insets=new Insets(10, 10, 10, 40);
        getContentPane().add(colorPanel, gridConstraints);
        colorPanel.setLayout(new GridBagLayout());
        int j=0;
        for (int i=0; i<18; i++)
        {
            colorLabel[i]=new JLabel();
            colorLabel[i].setPreferredSize(new Dimension(30, 30));
            colorLabel[i].setOpaque(true);
            gridConstraints=new GridBagConstraints();
            gridConstraints.gridx=j;
            gridConstraints.gridy=i-j*9;
            colorPanel.add(colorLabel[i], gridConstraints);
            if (i==8)
            {
                j++;
            }
            colorLabel[i].addMouseListener(new MouseAdapter()
            {
                public void mousePressed(MouseEvent e)
                {
                    colorMousePressed(e);
                }
            });
        }
        colorLabel[0].setBackground(new Color(255, 106, 106));
       	colorLabel[1].setBackground(new Color(255, 0, 0));
       	colorLabel[2].setBackground(new Color(139, 0, 0));
       	colorLabel[3].setBackground(new Color(152, 251,152));
       	colorLabel[4].setBackground(new Color(0, 255, 0));
       	colorLabel[5].setBackground(new Color(0, 100, 0));
       	colorLabel[6].setBackground(new Color(135, 206, 255));
       	colorLabel[7].setBackground(new Color(0, 0, 255));
       	colorLabel[8].setBackground(new Color(0,0, 139));        	
       	colorLabel[9].setBackground(new Color(255, 246, 143));
       	colorLabel[10].setBackground(new Color(255, 255, 0));
       	colorLabel[11].setBackground(new Color(139, 139, 0));
		colorLabel[12].setBackground(new Color(255, 165, 0));
		colorLabel[13].setBackground(new Color(255, 128, 0));
		colorLabel[14].setBackground(new Color(139, 90, 0));
		colorLabel[15].setBackground(new Color(255, 0, 255));
		colorLabel[16].setBackground(new Color(205, 0, 205));
		colorLabel[17].setBackground(new Color(139, 0, 139));
		leftColor=colorLabel[1].getBackground();
		leftColorLabel.setBackground(leftColor);
		rightColor=colorLabel[7].getBackground();
		rightColorLabel.setBackground(rightColor);
        pack();
        setLocationRelativeTo(null);  
        g2D=(Graphics2D) drawPanel.getGraphics();
    }
    private void exitForm(WindowEvent e) {
        g2D.dispose();
        System.exit(0);
    }
    private void boardColor(ActionEvent e) {
    	if(e.getSource() == blackMenuItem) {
			drawPanel.setBackground(new Color(0, 0, 0));
		}
		else if(e.getSource() == yellowMenuItem) {
			drawPanel.setBackground(new Color(255, 255, 0));
		}
		else if(e.getSource() == redMenuItem) {
			drawPanel.setBackground(new Color(255, 0, 0));
		}
    }
    private void penSize(ActionEvent e) {
    	if(e.getSource() == fineMenuItem) {
    		g2D.setStroke(new BasicStroke(1));
		}
		else if(e.getSource() == smallMenuItem) {
			g2D.setStroke(new BasicStroke((float) 2.5));
		}
		else if(e.getSource() == mediumMenuItem) {
			g2D.setStroke(new BasicStroke(5));
		}
		else if(e.getSource() == largeMenuItem)
			g2D.setStroke(new BasicStroke(10));

    }
    private void newMenuItemActionPerformed(ActionEvent e) {
        int response;
        response = JOptionPane.showConfirmDialog(null, "Are you sure you want to clear?", "New Drawing", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION)
        {
        	g2D.setPaint(drawPanel.getBackground());
        	g2D.fill(new Rectangle2D.Double(0, 0, drawPanel.getWidth(), drawPanel.getHeight()));
        }
    }
    private void exitMenuItemActionPerformed(ActionEvent e) {
        int response;
        response=JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the program?", "Exit Program", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response==JOptionPane.NO_OPTION) {
            return;
        }
        else {
            exitForm(null);
        }
    }
    private void drawPanelMousePressed(MouseEvent e) {
        if (e.getButton()==MouseEvent.BUTTON1||e.getButton()==MouseEvent.BUTTON3) {
            xPrevious=e.getX();
            yPrevious=e.getY();
            if (e.getButton()==MouseEvent.BUTTON1) {
                drawColor=leftColor;
            }
            else {
                drawColor=rightColor;
            }
        }
    }
    private void drawPanelMouseDragged(MouseEvent e) {
        Line2D.Double myLine=new Line2D.Double(xPrevious, yPrevious, e.getX(),e.getY());
        g2D.setPaint(drawColor);
        g2D.draw(myLine);
        xPrevious=e.getX();
        yPrevious=e.getY();
    }
    private void drawPanelMouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1 || e.getButton() == MouseEvent.BUTTON3)
        {
        	Line2D.Double myLine = new Line2D.Double(xPrevious, yPrevious, e.getX(), e.getY());
        	g2D.setPaint(drawColor);
        	g2D.draw(myLine);
        }
    }
    private void colorMousePressed(MouseEvent e) {
        Component clickedColor=e.getComponent();
        if(e.getButton()==MouseEvent.BUTTON1) {
            leftColor=clickedColor.getBackground();
            leftColorLabel.setBackground(leftColor);
        }
        else if (e.getButton()==MouseEvent.BUTTON3) {
            rightColor=clickedColor.getBackground();
            rightColorLabel.setBackground(rightColor);
        }
    }
}