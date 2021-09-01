/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java3d;

/**
 *
 * @author luis
 */


import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import java.util.Enumeration;

public class Rastreador extends Applet implements ActionListener{  

    Button blue_button;
    Button stop1_button; 
    Button stop2_button;
    
    private TMove tm1;
    private TMove tm2;    
    private TMove tm3;
    private TMove tm4;    

    private View view = null;
    private Appearance app;
    private RotationInterpolator transInterpolator;
    private RotationInterpolator rotatorInterpolator;
    private RotationInterpolator rotatorInterpolador2;
    boolean animationOn1 = true;
    boolean animationOn2 = true;
    
    public Rastreador() {
        
        setBackground(new Color(200, 200, 200));
        setLayout(null);
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        Canvas3D c = new Canvas3D(config);
        c.setSize(490, 445);        
        c.setLocation(5, 5);        
        add(c);

        BranchGroup scene = createSceneGraph();
        SimpleUniverse u = new SimpleUniverse(c);

        u.getViewingPlatform().setNominalViewingTransform();
        view = u.getViewer().getView();
        u.addBranchGraph(scene);
        setLayout(new BorderLayout());
        add("South", createButtonPanel());
    }
   
    Panel createButtonPanel(){
                
            // Create the button container.
        Panel buttonPanel = new Panel();
        buttonPanel.setSize(500, 50);
        buttonPanel.setLocation(0, 500);
        buttonPanel.setVisible(true);
        buttonPanel.setLayout(null);

        // Create the buttons.
        blue_button = new Button("Configura��o Inicial");
        blue_button.setSize(135, 25);
        blue_button.setLocation(10, 10);
        blue_button.setVisible(true);
        blue_button.addActionListener(this);

        stop1_button = new Button("Parar Anima��o 1");
        stop1_button.setSize(135, 25);
        stop1_button.setLocation(170, 10);
        stop1_button.setVisible(true);
        stop1_button.addActionListener(this);

        stop2_button = new Button("Parar Anima��o 2");
        stop2_button.setSize(135, 25);
        stop2_button.setLocation(330, 10);
        stop2_button.setVisible(true);
        stop2_button.addActionListener(this);

        buttonPanel.add(blue_button);
        buttonPanel.add(stop1_button);
        buttonPanel.add(stop2_button);

      // Add the button container to the applet.
        return buttonPanel;
    }



    public void actionPerformed (ActionEvent event) {
        Object target = event.getSource();
         // Process the button events.
        if (target == blue_button) {
            tm1.colisao = false;
            tm2.colisao = false;            
            tm3.colisao = false;
            tm4.colisao = false;                            
                  app.setColoringAttributes(new ColoringAttributes(new Color3f(0.3f, 0.0f, 0.6f), ColoringAttributes.FASTEST));                                             
              
        }
        
        else if (target == stop1_button) {              
            try { 
                if (animationOn1) {
                    animationOn1 = false;
                    transInterpolator.setEnable(false);
                    rotatorInterpolator.setEnable(false);
                    stop1_button.setLabel("Reiniciar Anima�ao 1");
                }
                else {
                    animationOn1 = true;
                    transInterpolator.setEnable(true);
                    rotatorInterpolator.setEnable(true);
                    stop1_button.setLabel("Parar Anima��o 1");
                }
            }
            catch (Exception e) {
                System.err.println ("Exception " + e);
            }
        }
                    
        else if (target == stop2_button) {
            try {          
                if (animationOn2) {                                                    
 
                    animationOn2 = false;
                    rotatorInterpolador2.setEnable(false);
                    stop2_button.setLabel("Reiniciar Anima�ao 2");
                }
                else {
                    animationOn2 = true;
                    rotatorInterpolador2.setEnable(true);
                    stop2_button.setLabel("Parar Anima��o 2");
                }
            }
            catch (Exception e) {
                System.err.println ("Exception " + e);
            }           
        }       
        
    }   

    // Envoltorio Caixa
    Shape3D criaEnvoltorioCaixa(float x, float y, float z, float sizeX, float sizeY, float sizeZ, float transpa){
        
        Shape3D envoltorioCaixa = new Box(x, y, z, sizeX, sizeY, sizeZ);
        envoltorioCaixa.setCapability(Node.ALLOW_LOCAL_TO_VWORLD_READ);        
        envoltorioCaixa.setCapability(Shape3D.ALLOW_GEOMETRY_READ);  
        envoltorioCaixa.getGeometry().setCapability(GeometryArray.ALLOW_COORDINATE_READ);  
              Appearance appCaixa = new Appearance();
              ColoringAttributes caCaixa = new ColoringAttributes();
              caCaixa.setColor(1.0f, 1.0f, 0.0f);             
              appCaixa.setCapability(appCaixa.ALLOW_COLORING_ATTRIBUTES_WRITE);
              appCaixa.setColoringAttributes(caCaixa);                
              appCaixa.setTransparencyAttributes(new 
TransparencyAttributes(TransparencyAttributes.FASTEST, transpa));                
              envoltorioCaixa.setAppearance(appCaixa);
              
        return envoltorioCaixa;    
    }

    // Envoltorio Esfera 
    TransformGroup criaEnvoltorioEsfera(float raio, float x, float y, float z, float transp){
        
        Sphere envoltorioEsfera = new Sphere(raio);

        // ADDED
        envoltorioEsfera.getShape().getGeometry().setCapability(GeometryArray.ALLOW_COORDINATE_READ);
              Appearance appEsfera = new Appearance();
              ColoringAttributes caEsfera = new ColoringAttributes();
              caEsfera.setColor(1.0f, 1.0f, 1.0f);            
              appEsfera.setColoringAttributes(caEsfera);                      
              appEsfera.setTransparencyAttributes(new 
TransparencyAttributes(TransparencyAttributes.FASTEST, transp));                
              envoltorioEsfera.setAppearance(appEsfera);
              
              Transform3D t = new Transform3D();
              t.set(1.0, new Vector3d(x, y, z));
              TransformGroup esferaTrans = new TransformGroup(t);
              
              esferaTrans.addChild(envoltorioEsfera);
              
        return esferaTrans;    
    }


    public BranchGroup createSceneGraph() {
    
        BranchGroup objRoot = new BranchGroup();

        TransformGroup objScale = new TransformGroup();
        Transform3D t3d = new Transform3D();
        t3d.setScale(0.4);
        objScale.setTransform(t3d);
        objRoot.addChild(objScale);

          BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);

        // Luz Ambiente
              AmbientLight aLgt = new AmbientLight(new Color3f(0.7f, 0.7f, 0.7f));
              aLgt.setInfluencingBounds(bounds);              
        objScale.addChild(aLgt);

            // Background
              Color3f bgColor = new Color3f(0.05f, 0.05f, 0.2f);
              Background bg = new Background(bgColor);
              bg.setApplicationBounds(bounds);
              objScale.addChild(bg);
              
        // objTrans1  
              TransformGroup objTrans1 = new TransformGroup();
              objTrans1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
              objScale.addChild(objTrans1);
              
        // objTrans2  
            TransformGroup objTrans2 = new TransformGroup();
              objTrans2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
              objTrans1.addChild(objTrans2);
              
        // objTrans3  
              Transform3D t = new Transform3D();
              t.set(0.2, new Vector3d(0.0, 1.5, 0.0));
              TransformGroup objTrans3 = new TransformGroup(t);
              objTrans2.addChild(objTrans3);
    
        // objTransObj1
            TransformGroup objTransObj1 = new TransformGroup();
              objTransObj1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
              objScale.addChild(objTransObj1);
              
        // objTransObj2
//          TransformGroup objTransObj2 = new TransformGroup();
//            objTransObj2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
//            objTransObj1.addChild(objTransObj2);
        
        // objTrans11  
              Transform3D t11 = new Transform3D();
              t11.set(0.2, new Vector3d(0.0, 0.0, 0.0));
              TransformGroup objTrans11 = new TransformGroup(t11);            
              objTrans11.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);         
              objTransObj1.addChild(objTrans11);


        float aIcosaedro = 2.8f; // aresta cubo
        float aOctaedro = 2.5f;  // aresta cubo
        float hCilindro = 3.0f;  // altura cilindro
        float rCilindro = 1.4f;  // raio base cilindro      
        float aTetraedro = 2.0f; // aresta cubo
        int numeroFacesLaterais = 10; // numero de faces laterais

        // ICOSAEDRO
        Shape3D shapeIco = new Icosahedron(0.0f ,-8.5f ,0.0f);  
        shapeIco.setCapability(Node.ALLOW_LOCAL_TO_VWORLD_READ);                
        shapeIco.setCapability(Shape3D.ALLOW_GEOMETRY_READ);  
//            objTrans11.addChild(shapeIco);
           // Esfera Envoltorio: Icosaedro
//        objTrans11.addChild(criaEnvoltorioEsfera(aIcosaedro * 0.7f ,0.0f, -8.5f, 0.0f, 0.6f)); 
           // Cubo Envoltorio
        Shape3D caixaEnvoltorio3 = criaEnvoltorioCaixa(0.0f, -8.5f, 0.0f, aIcosaedro, aIcosaedro, aIcosaedro, 1.0f);
//        objTrans11.addChild(caixaEnvoltorio3);        

        caixaEnvoltorio3.setCapability(Shape3D.ALLOW_GEOMETRY_READ);  

        // OCTAEDRO
        Shape3D shapeOcta = new Octahedron(-4.5f ,0f ,0.0f, aOctaedro);  
        shapeOcta.setCapability(Node.ALLOW_LOCAL_TO_VWORLD_READ);                
        shapeOcta.setCapability(Shape3D.ALLOW_GEOMETRY_READ);  
//        objTrans11.addChild(shapeOcta);
          // Esfera Envoltorio: Octaedro                        
//        objTrans11.addChild(criaEnvoltorioEsfera(aOctaedro * 0.5f, -4.5f, 0.0f, 0.0f, 0.6f)); 
          // Cubo Envoltorio      
        Shape3D caixaEnvoltorio1 = criaEnvoltorioCaixa(-4.5f, 0.0f, 0.0f, aOctaedro, aOctaedro, aOctaedro, 1.0f);
//        objTrans11.addChild(caixaEnvoltorio1);        
        caixaEnvoltorio1.setCapability(Shape3D.ALLOW_GEOMETRY_READ);  


        // CILINDRO
        Shape3D shapeCylin = new Cylinder(8.5f, 0.0f, rCilindro, hCilindro, numeroFacesLaterais);  
        shapeCylin.setCapability(Node.ALLOW_LOCAL_TO_VWORLD_READ);                
        shapeCylin.setCapability(Shape3D.ALLOW_GEOMETRY_READ);  
              Appearance appCylin = shapeCylin.getAppearance();
              ColoringAttributes caCylin = new ColoringAttributes();
              caCylin.setColor(1.0f, 0.0f, 0.0f);             
              appCylin.setCapability(appCylin.ALLOW_COLORING_ATTRIBUTES_WRITE);
              appCylin.setColoringAttributes(caCylin);    
//      objTrans11.addChild(shapeCylin);
          // Esfera Envoltorio: Cilindro 
        float raio = 0.0f;
        if (hCilindro > 2*rCilindro) raio = hCilindro * 0.7f;
        else raio = (float) Math.sqrt(Math.pow(rCilindro,2) + Math.pow(hCilindro/4,2));
//        objTrans11.addChild(criaEnvoltorioEsfera(raio, 8.5f, 0.0f, 0.0f, 0.6f));
          // Cubo Envoltorio      
        Shape3D caixaEnvoltorio4 = criaEnvoltorioCaixa(8.5f, 0.0f, 0.0f, 2*rCilindro, hCilindro, 2*rCilindro, 1.0f);
//            objTrans11.addChild(caixaEnvoltorio4);        
  
        caixaEnvoltorio4.setCapability(Shape3D.ALLOW_GEOMETRY_READ);  

        // TETRAEDRO
        Shape3D shapeTetra = new Tetrahedron(0.0f ,5.2f ,0.0f, aTetraedro);  
        shapeTetra.setCapability(Node.ALLOW_LOCAL_TO_VWORLD_READ);                
        shapeTetra.setCapability(Shape3D.ALLOW_GEOMETRY_READ);  
        shapeTetra.getGeometry().setCapability(GeometryArray.ALLOW_COORDINATE_READ);  

        objTrans11.addChild(shapeTetra);
        // Esfera Envoltorio: Tetraedro
        objTrans11.addChild(criaEnvoltorioEsfera(aTetraedro * 0.71f, 0.0f, 5.2f, 0.0f, 
0.6f));             
          // Cubo Envoltorio             
        Shape3D caixaEnvoltorio2 = criaEnvoltorioCaixa(0.0f, 5.2f, 0.0f, aTetraedro, 
aTetraedro, aTetraedro, 1.0f);        
        objTrans11.addChild(caixaEnvoltorio2);        
        caixaEnvoltorio2.setCapability(Shape3D.ALLOW_GEOMETRY_READ);  
        Geometry geoTetraedro = shapeTetra.getGeometry();
        GeometryArray gaTetraedro = (GeometryArray) geoTetraedro;

        // ADDED
        gaTetraedro.setCapability(GeometryArray.ALLOW_COORDINATE_READ);                
     

        gaTetraedro.setCapability(GeometryArray.ALLOW_NORMAL_READ);                    
 
                
        
        // Cubo Principal
        Shape3D shape = new Box(0.0f, 0.0f, 0.0f, 2.5f, 2.5f, 2.5f);  
        shape.setCapability(Node.ALLOW_LOCAL_TO_VWORLD_READ);        
        shape.setCapability(Shape3D.ALLOW_GEOMETRY_READ);


              app = shape.getAppearance();
              ColoringAttributes ca = new ColoringAttributes();
              ca.setColor(0.3f, 0.0f, 0.6f);
              app.setCapability(app.ALLOW_COLORING_ATTRIBUTES_WRITE);
              app.setColoringAttributes(ca);      
              objTrans3.addChild(shape);

        Geometry geo = shape.getGeometry();

        GeometryArray ga = (GeometryArray) geo;
        ga.setCapability(GeometryArray.ALLOW_COORDINATE_READ);                     
        ga.setCapability(GeometryArray.ALLOW_NORMAL_READ);                     
                                                    
                
        // Colisao 2 - TETRAEDRO
        tm2 = new TMove(caixaEnvoltorio2, objTrans1, shapeTetra, shape, 1);        
//        tm2 = new TMove(shape, objTrans1, shapeTetra, caixaEnvoltorio2, 
transInterpolator, rotatorInterpolator, 1);         
        tm2.setSchedulingBounds(bounds);
        objTrans1.addChild(tm2); 
        
        // Behaviour
        // Colisao 1 - OCTAEDRO
        tm1 = new TMove(shape, objTrans1, shapeTetra, caixaEnvoltorio1, -2);        
        tm1.setSchedulingBounds(bounds);
//        objTrans1.addChild(tm1); 
        
        // Colisao 3 - ICOSAEDRO
        tm3 = new TMove(shape, objTrans1, shapeTetra, caixaEnvoltorio3, 3);        
        tm3.setSchedulingBounds(bounds);
//        objTrans1.addChild(tm3); 
        
        // Colisao 4 - CILINDRO
        tm4 = new TMove(shape, objTrans1, shapeTetra, caixaEnvoltorio4, 4, 
numeroFacesLaterais);        
        tm4.setSchedulingBounds(bounds);
//        objTrans1.addChild(tm4); 


        // translacao
              Transform3D yAxis1 = new Transform3D();
              yAxis1.rotX(Math.PI/2);
          
              Alpha tickTockAlpha = new Alpha(-1, Alpha.DECREASING_ENABLE | 
Alpha.INCREASING_ENABLE,
                                                                     0, 0,
                                                                     15000, 0, 0,
                                                                     15000, 0, 0);
              transInterpolator = new RotationInterpolator(tickTockAlpha, objTrans1, 
yAxis1, (float) Math.PI*2.0f, (float) 0.0f);
              transInterpolator.setSchedulingBounds(bounds);
              objTrans2.addChild(transInterpolator);

        // rotacao 1
              Transform3D yAxis2 = new Transform3D();
              Alpha rotationAlpha = new Alpha(-1, Alpha.INCREASING_ENABLE,
                                                                      0, 0,
                                                                      8000, 0, 0,
                                                                      0, 0, 0);
              rotatorInterpolator = new RotationInterpolator(rotationAlpha, objTrans2, 
yAxis2, 0.0f, (float) Math.PI*2.0f);
              rotatorInterpolator.setSchedulingBounds(bounds);
              objTrans2.addChild(rotatorInterpolator);
            
              // rotacao 2
              Transform3D yAxis3 = new Transform3D();
              Alpha rotationAlpha2 = new Alpha(-1, Alpha.INCREASING_ENABLE,
                                                                      0, 0,
                                                                      12000, 0, 0,
                                                                      0, 0, 0);

              rotatorInterpolador2 = new RotationInterpolator(rotationAlpha2, 
objTransObj1, yAxis3, 0.0f, (float) Math.PI*2.0f);
              rotatorInterpolador2.setSchedulingBounds(bounds);
              objTransObj1.addChild(rotatorInterpolador2);
    
        objRoot.compile();

              return objRoot;
    }



    public static void main(String[] args) {
                                        
              new MainFrame(new Rastreador(), 500, 500);
              
    }    
    
}