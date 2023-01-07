import javax.swing.JFrame;
import javax.swing.*;
import java.awt.event.*;

//Bhaskar Sharma
//PPAFNW
//NOTE: Ryisnow’s tutorial regarding how to make a 2D game has been greatly followed and studied through throughout the making of this game.
//The tutorial can be found on this link https://www.youtube.com/watch?v=om59cwR7psI&list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq&index=1&ab_channel=RyiSnow
//Due to that, certain generic game functions, such as the GameLoop, collision check, input handler, etc. may seem similar to other games
//that followed the same tutorial.  

public class Plant implements ActionListener
{
    public static void main(String args[])
    {
        JMenuBar mb = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem m1 = new JMenuItem("Exit");
        menu.add(m1);
        mb.add(menu);
        m1.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
              System.exit(1);
            } 
          } );

        JFrame window = new JFrame("Field of Flowers");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setJMenuBar(mb);
        
        GameScreen screen = new GameScreen();
        screen.window = window;
        window.add(screen);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        screen.setupGame();
        screen.startGameThread();
    }

    public void actionPerformed(ActionEvent e)
    {
        System.exit(1);
    }
}