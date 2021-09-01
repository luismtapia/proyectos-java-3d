package java3d;

import java3d.Interaction.MouseBehaviorApp;
import com.sun.j3d.utils.applet.MainFrame;
import java.awt.Frame;

/**
 *
 * @author luis
 */
public class Java3D {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        VentanaInteraccion VI=new VentanaInteraccion();
        //VI.setVisible(true);
            Frame frame = new MainFrame(new MouseBehaviorApp(), 256, 256);
    }
    
}
