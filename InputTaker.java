import java.awt.event.*;

public class InputTaker implements KeyListener {

    public boolean up, down, left, right, shift, z, x, c, v, esc, p, r;

    @Override
    public void keyTyped(KeyEvent e) 
    {
    }

    @Override
    public void keyPressed(KeyEvent e) 
    {
        int code = e.getKeyCode();

        
        if(code == KeyEvent.VK_R)
        {
            r = true;
        }
        if(code == KeyEvent.VK_P)
        {
            p = true;
        }
        if(code == KeyEvent.VK_W)
        {
            up = true;
        }
        if(code == KeyEvent.VK_S)
        {
            down = true;
        }
        if(code == KeyEvent.VK_A)
        {
            left = true; 
        }
        if(code == KeyEvent.VK_D)
        {
            right = true;
        }
        if(code == KeyEvent.VK_SHIFT)
        {
            shift = true;
        }
        if(code == KeyEvent.VK_X)
        {
            x = true;
        }
        if(code == KeyEvent.VK_Z)
        {
            z = true;
        }
        if(code == KeyEvent.VK_C)
        {
            c = true;
        }
        if(code == KeyEvent.VK_V)
        {
            v = true;
        }
        if(code == KeyEvent.VK_ESCAPE)
        {
            esc = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) 
    {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_R)
        {
            r = false;
        }
        if(code == KeyEvent.VK_P)
        {
            p = false;
        }
        if(code == KeyEvent.VK_W)
        {
            up = false;
        }
        if(code == KeyEvent.VK_S)
        {
            down = false;
        }
        if(code == KeyEvent.VK_A)
        {
            left = false; 
        }
        if(code == KeyEvent.VK_D)
        {
            right = false;
        }
        if(code == KeyEvent.VK_SHIFT)
        {
            shift = false;
        }
        if(code == KeyEvent.VK_Z)
        {
            z = false;
        }
        if(code == KeyEvent.VK_X)
        {
            x = false;
        }
        if(code == KeyEvent.VK_C)
        {
            c = false;
        }
        if(code == KeyEvent.VK_V)
        {
            v = false;
        }
        if(code == KeyEvent.VK_ESCAPE)
        {
            esc = false;
        }
    }

}