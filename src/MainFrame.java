import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class MainFrame
{
    public static void main(String[] args)
    {
		Toolkit toolkit = Toolkit.getDefaultToolkit(); //Gets the default toolkit
		
		Dimension scrnsize = toolkit.getScreenSize(); // Get the current screen size
		Dimension panelD = new Dimension((int)scrnsize.getWidth()/3, (int)scrnsize.getHeight()/3);
		
        JFrame frame = new JFrame();
        frame.setSize((int)scrnsize.getWidth()/3, (int)scrnsize.getHeight()/3);
        
        Container cont = frame.getContentPane();
        BusyBeaver panel = new BusyBeaver(panelD);
        cont.add(panel); //Adds a panel the size of the screen
        
        frame.setVisible(true);
    }
}