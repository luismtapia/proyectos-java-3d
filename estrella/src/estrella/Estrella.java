package estrella;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;
import com.sun.j3d.utils.geometry.Stripifier;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import javax.media.j3d.Alpha;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Geometry;
import javax.media.j3d.Material;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.TriangleFanArray;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

/**
 * @author linuxer
 */
public class Estrella extends Applet
{
    GeometryInfo geometryInfo;
    public static void main(String[] args)
    {
        Frame frame = new MainFrame(new Estrella(), 450, 450);
    }
    
    
    /**
     * LE ponemos la apariencia a la estrella para que pueda verse bien la iluminación
     * @return 
     */
    Appearance createMaterialAppearance(){

        Appearance materialAppear = new Appearance();
        PolygonAttributes polyAttrib = new PolygonAttributes();
        polyAttrib.setCullFace(PolygonAttributes.CULL_NONE);
        materialAppear.setPolygonAttributes(polyAttrib);

        Material material = new Material();

        material.setDiffuseColor(new Color3f(0.1f, .61f, 5.98f));
        materialAppear.setMaterial(material);

        return materialAppear;
    }
    
    Appearance createWireFrameAppearance(){

        Appearance materialAppear = new Appearance();
        PolygonAttributes polyAttrib = new PolygonAttributes();
        polyAttrib.setPolygonMode(PolygonAttributes.POLYGON_LINE);
        materialAppear.setPolygonAttributes(polyAttrib);
        ColoringAttributes redColoring = new ColoringAttributes();
        redColoring.setColor(6.0f, 0.0f, 6.0f);
        materialAppear.setColoringAttributes(redColoring);

        return materialAppear;
    }
    
    public BranchGroup createSceneGraph()
    {

        BranchGroup root = new BranchGroup();

        TransformGroup spin = new TransformGroup();
        spin.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        root.addChild(spin);
        
        // crea y agrega el esqueleto y las caras
        Shape3D shape3dCaras = new Shape3D();
        Shape3D shape3dEsqueleto = new Shape3D();
        
        shape3dCaras.addGeometry(caras().getGeometryArray());
        shape3dCaras.setAppearance(createMaterialAppearance());
        shape3dEsqueleto.setAppearance(createMaterialAppearance());
        
        // crea y agrega la apariencia del esqueleto
        shape3dEsqueleto.addGeometry(esqueleto());
        Appearance appearance = new Appearance();
        PolygonAttributes polyAttrib = new PolygonAttributes();
        polyAttrib.setPolygonMode(PolygonAttributes.POLYGON_LINE);
        appearance.setPolygonAttributes(polyAttrib);
        shape3dEsqueleto.setAppearance(appearance);
        
        spin.addChild(shape3dEsqueleto);
        spin.addChild(shape3dCaras);

        Transform3D yAxis = new Transform3D();
        Alpha rotationAlpha = new Alpha(-1, 4000);

        RotationInterpolator rotator = new RotationInterpolator(rotationAlpha, spin);
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
        rotator.setSchedulingBounds(bounds);
        spin.addChild(rotator);
        
        AmbientLight lightA = new AmbientLight();
        lightA.setInfluencingBounds(new BoundingSphere());
        spin.addChild(lightA);

        DirectionalLight lightD1 = new DirectionalLight();
        lightD1.setInfluencingBounds(new BoundingSphere());
        Vector3f direction1 = new Vector3f(-1.0f, -1.0f, -0.5f);
        direction1.normalize();
        lightD1.setDirection(direction1);
        lightD1.setColor(new Color3f(0.0f, 0.0f, 1.0f));
        spin.addChild(lightD1);

        DirectionalLight lightD2 = new DirectionalLight();
        lightD2.setInfluencingBounds(new BoundingSphere());
        Vector3f direction2 = new Vector3f(1.0f, -1.0f, -0.5f);
        direction2.normalize();
        lightD2.setDirection(direction2);
        lightD2.setColor(new Color3f(1.0f, 0.0f, 0.0f));
        spin.addChild(lightD2);

        root.compile();

        return root;
    }

    public Estrella()
    {
        this.setLayout(new BorderLayout());
        GraphicsConfiguration config =
           SimpleUniverse.getPreferredConfiguration();
        setLocation(350, 390);

        Canvas3D canvas3D = new Canvas3D(config);
        add("Center", canvas3D);

        BranchGroup scene = createSceneGraph();

        SimpleUniverse simpleU = new SimpleUniverse(canvas3D);

        simpleU.getViewingPlatform().setNominalViewingTransform();

        simpleU.addBranchGraph(scene);
    }

    private GeometryInfo caras()
    {
        int  stripCounts[] = {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7}; //, N+1, N+1, N+1};

        TriangleFanArray dode = new TriangleFanArray(7+7+7+7+7+7+7+7+7+7+7+7, TriangleFanArray.COORDINATES|TriangleFanArray.COLOR_3, stripCounts);
        Point3f[] coordenadas = new Point3f[7+7+7+7+7+7+7+7+7+7+7+7];
        Color3f[] colores = new Color3f[7+7+7+7+7+7+7+7+7+7+7+7];
        Color3f red = new Color3f(.2f, 0.5f, 0.3f);
        

        
        coordenadas[0] = new Point3f(0f/2, -1.5f/2, 0f/2); // V
        coordenadas[1] = new Point3f(0f/2, -0.68f/2, 0.5f/2);  // A
        coordenadas[2] = new Point3f(0.47553f/2, -0.68f/2, 0.12941f/2);  // B
        coordenadas[3] = new Point3f(0.27f/2, -0.67f/2, -0.44f/2);  // E
        coordenadas[4] = new Point3f(-0.33f/2, -0.67f/2, -0.42f/2);  // D
        coordenadas[5] = new Point3f(-0.5f/2, -0.67f/2, 0.16f/2);  // C
        coordenadas[6] = new Point3f(0f/2, -0.68f/2, 0.5f/2);  // A

        coordenadas[7] = new Point3f(-0.77f/2, -0.69f/2, 1.09f/2);  // z
        coordenadas[8] = new Point3f(0f/2, -0.68f/2, 0.5f/2);  // A
        coordenadas[9] = new Point3f(-0.5f/2, -0.67f/2, 0.16f/2);  // C
        coordenadas[10] = new Point3f(-0.78f/2, -0.14f/2, 0.29f/2); // G
        coordenadas[11] = new Point3f(-0.46f/2, 0.16f/2, 0.7f/2); // L
        coordenadas[12] = new Point3f(0.02f/2, -0.18f/2, 0.83f/2); // F
        coordenadas[13] = new Point3f(0f/2, -0.68f/2, 0.5f/2);  // A


        coordenadas[14] = new Point3f(-1.3f/2, -0.63f/2, -0.39f/2);  // A1
        coordenadas[15] = new Point3f(-0.5f/2, -0.67f/2, 0.16f/2);  // C
        coordenadas[16] = new Point3f(-0.33f/2, -0.67f/2, -0.42f/2); // D
        coordenadas[17] = new Point3f(-0.51f/2, -0.12f/2, -0.65f/2); // H
        coordenadas[18] = new Point3f(-0.79f/2, 0.19f/2, -0.22f/2); // M
        coordenadas[19] = new Point3f(-0.78f/2, -0.14f/2, 0.29f/2); // G
        coordenadas[20] = new Point3f(-0.5f/2, -0.67f/2, 0.16f/2);  // C

        coordenadas[21] = new Point3f(-0.06f/2, -0.63f/2, -1.36f/2); // B1
        coordenadas[22] = new Point3f(-0.33f/2, -0.67f/2, -0.42f/2); // D
        coordenadas[23] = new Point3f(0.27f/2, -0.67f/2, -0.44f/2); // E
        coordenadas[24] = new Point3f(0.46f/2, -0.14f/2, -0.68f/2); // I
        coordenadas[25] = new Point3f(-0.02f/2, 0.19f/2, -0.82f/2); // N
        coordenadas[26] = new Point3f(-0.51f/2, -0.12f/2, -0.65f/2); // H
        coordenadas[27] = new Point3f(-0.33f/2, -0.67f/2, -0.42f/2); // D


        coordenadas[28] = new Point3f(1.24f/2, -0.69f/2, -0.48f/2); // C1
        coordenadas[29] = new Point3f(0.27f/2, -0.67f/2, -0.44f/2); // E
        coordenadas[30] = new Point3f(0.47553f/2, -0.68f/2, 0.12941f/2);  // B
        coordenadas[31] = new Point3f(0.79f/2, -0.18f/2, 0.23f/2); // J
        coordenadas[32] = new Point3f(0.78f/2, 0.16f/2, -0.27f/2); // O
        coordenadas[33] = new Point3f(0.46f/2, -0.14f/2, -0.68f/2); // I
        coordenadas[34] = new Point3f(0.27f/2, -0.67f/2, -0.44f/2); // E


        coordenadas[35] = new Point3f(0.81f/2, -0.72f/2, 1.04f/2);  // D1
        coordenadas[36] = new Point3f(0.47553f/2, -0.68f/2, 0.12941f/2);  // B
        coordenadas[37] = new Point3f(0f/2, -0.68f/2, 0.5f/2);  // A
        coordenadas[38] = new Point3f(0.02f/2, -0.18f/2, 0.83f/2); // F
        coordenadas[39] = new Point3f(0.51f/2, 0.13f/2, 0.67f/2); // K
        coordenadas[40] = new Point3f(0.79f/2, -0.18f/2, 0.23f/2); // J
        coordenadas[41] = new Point3f(0.47553f/2, -0.68f/2, 0.12941f/2);  // B

        //------------------------------------------------------------

        coordenadas[42] = new Point3f(0.06f/2, 0.63f/2, 1.36f/2); // H1
        coordenadas[43] = new Point3f(0.51f/2, 0.13f/2, 0.67f/2); // K
        coordenadas[44] = new Point3f(0.02f/2, -0.18f/2, 0.83f/2); // F
        coordenadas[45] = new Point3f(-0.46f/2, 0.16f/2, 0.7f/2); // L
        coordenadas[46] = new Point3f(-0.27f/2, 0.67f/2, 0.45f/2); // Q
        coordenadas[47] = new Point3f(0.33f/2, 0.66f/2, 0.43f/2); // P
        coordenadas[48] = new Point3f(0.51f/2, 0.13f/2, 0.67f/2); // K

        coordenadas[49] = new Point3f(-1.24f/2, 0.69f/2, 0.48f/2); // G1
        coordenadas[50] = new Point3f(-0.46f/2, 0.16f/2, 0.7f/2); // L
        coordenadas[51] = new Point3f(-0.78f/2, -0.14f/2, 0.29f/2); // G
        coordenadas[52] = new Point3f(-0.79f/2, 0.19f/2, -0.22f/2); // M
        coordenadas[53] = new Point3f(-0.48f/2, 0.69f/2, -0.11f/2); // R
        coordenadas[54] = new Point3f(-0.27f/2, 0.67f/2, 0.45f/2); // Q
        coordenadas[55] = new Point3f(-0.46f/2, 0.16f/2, 0.7f/2); // L

        coordenadas[56] = new Point3f(-0.81f/2, 0.72f/2, -1.04f/2); // F1
        coordenadas[57] = new Point3f(-0.79f/2, 0.19f/2, -0.22f/2); // M
        coordenadas[58] = new Point3f(-0.51f/2, -0.12f/2, -0.65f/2); // H
        coordenadas[59] = new Point3f(-0.02f/2, 0.19f/2, -0.82f/2); // N
        coordenadas[60] = new Point3f(0f/2, 0.69f/2, -0.48f/2); // S
        coordenadas[61] = new Point3f(-0.48f/2, 0.69f/2, -0.11f/2); // R
        coordenadas[62] = new Point3f(-0.79f/2, 0.19f/2, -0.22f/2); // M

        coordenadas[63] = new Point3f(0.77f/2, 0.69f/2, -1.09f/2); // E1
        coordenadas[64] = new Point3f(-0.02f/2, 0.19f/2, -0.82f/2); // N
        coordenadas[65] = new Point3f(0.46f/2, -0.14f/2, -0.68f/2); // I
        coordenadas[66] = new Point3f(0.78f/2, 0.16f/2, -0.27f/2); // O
        coordenadas[67] = new Point3f(0.5f/2, 0.67f/2, -0.15f/2); // T
        coordenadas[68] = new Point3f(0f/2, 0.69f/2, -0.48f/2); // S
        coordenadas[69] = new Point3f(-0.02f/2, 0.19f/2, -0.82f/2); // N

        coordenadas[70] = new Point3f(1.3f/2, 0.63f/2, 0.39f/2); // I1
        coordenadas[71] = new Point3f(0.78f/2, 0.16f/2, -0.27f/2); // O
        coordenadas[72] = new Point3f(0.79f/2, -0.18f/2, 0.23f/2); // J
        coordenadas[73] = new Point3f(0.51f/2, 0.13f/2, 0.67f/2); // K
        coordenadas[74] = new Point3f(0.33f/2, 0.66f/2, 0.43f/2); // P
        coordenadas[75] = new Point3f(0.5f/2, 0.67f/2, -0.15f/2); // T
        coordenadas[76] = new Point3f(0.78f/2, 0.16f/2, -0.27f/2); // O

        coordenadas[77] = new Point3f(0f/2, 1.5f/2, 0f/2); // U
        coordenadas[78] = new Point3f(0.5f/2, 0.67f/2, -0.15f/2); // T
        coordenadas[79] = new Point3f(0.33f/2, 0.66f/2, 0.43f/2); // P
        coordenadas[80] = new Point3f(-0.27f/2, 0.67f/2, 0.45f/2); // Q
        coordenadas[81] = new Point3f(-0.48f/2, 0.69f/2, -0.11f/2); // R
        coordenadas[82] = new Point3f(0f/2, 0.69f/2, -0.48f/2); // S
        coordenadas[83] = new Point3f(0.5f/2, 0.67f/2, -0.15f/2); // T

        for(int i=0;i<colores.length;i++)
            colores[i] = red;

        //dode.setCoordinates(0, coordenadas);
        //dode.setColors(0, colores);
        
        /**
         * EN esta parte es dónde le agregamos la iluminación a la estrella (EN las caras de la estrella)
         * 
         */
        
        geometryInfo = new GeometryInfo(GeometryInfo.TRIANGLE_FAN_ARRAY);

        geometryInfo.setColors(colores);
        geometryInfo.setCoordinates(coordenadas);
        geometryInfo.setStripCounts(stripCounts);
        NormalGenerator ng = new NormalGenerator();
        ng.generateNormals(geometryInfo);
        Stripifier st = new Stripifier();
        st.stripify(geometryInfo);

        return geometryInfo;
    }

    private Geometry esqueleto()
    { 
        int  counts[] = {7,7,7,7,7,7,7,7,7,7,7,7};
        TriangleFanArray lsa = new TriangleFanArray(7+7+7+7+7+7+7+7+7+7+7+7, TriangleFanArray.COORDINATES, counts);
        Point3f[] coordenadas = new Point3f[7+7+7+7+7+7+7+7+7+7+7+7];
        Color3f[] colores = new Color3f[7+7+7+7+7+7+7+7+7+7+7+7];
        Color3f red = new Color3f(1.0f, 1.0f, 1.0f);
        Color3f yellow = new Color3f(0.0f, 0.0f, 0.0f);

        
        coordenadas[0] = new Point3f(0f/2, -1.5f/2, 0f/2); // V
        coordenadas[1] = new Point3f(0f/2, -0.68f/2, 0.5f/2);  // A
        coordenadas[2] = new Point3f(-0.5f/2, -0.67f/2, 0.16f/2);  // C
        coordenadas[3] = new Point3f(-0.33f/2, -0.67f/2, -0.42f/2);  // D
        coordenadas[4] = new Point3f(0.27f/2, -0.67f/2, -0.44f/2);  // E
        coordenadas[5] = new Point3f(0.47553f/2, -0.68f/2, 0.12941f/2);  // B
        coordenadas[6] = new Point3f(0f/2, -0.68f/2, 0.5f/2);  // A
        
        coordenadas[7] = new Point3f(0f/2, 1.5f/2, 0f/2); // U
        coordenadas[8] = new Point3f(0.5f/2, 0.67f/2, -0.15f/2); // T
        coordenadas[9] = new Point3f(0f/2, 0.69f/2, -0.48f/2); // S
        coordenadas[10] = new Point3f(-0.48f/2, 0.69f/2, -0.11f/2); // R
        coordenadas[11] = new Point3f(-0.27f/2, 0.67f/2, 0.45f/2); // Q
        coordenadas[12] = new Point3f(0.33f/2, 0.66f/2, 0.43f/2); // P
        coordenadas[13] = new Point3f(0.5f/2, 0.67f/2, -0.15f/2); // T
        
        coordenadas[14] = new Point3f(-0.77f/2, -0.69f/2, 1.09f/2);  // z
        coordenadas[15] = new Point3f(0f/2, -0.68f/2, 0.5f/2);  // A
        coordenadas[16] = new Point3f(0.02f/2, -0.18f/2, 0.83f/2); // F
        coordenadas[17] = new Point3f(-0.46f/2, 0.16f/2, 0.7f/2); // L
        coordenadas[18] = new Point3f(-0.78f/2, -0.14f/2, 0.29f/2); // G
        coordenadas[19] = new Point3f(-0.5f/2, -0.67f/2, 0.16f/2);  // C
        coordenadas[20] = new Point3f(0f/2, -0.68f/2, 0.5f/2);  // A
        
        coordenadas[21] = new Point3f(0.77f/2, 0.69f/2, -1.09f/2); // E1
        coordenadas[22] = new Point3f(-0.02f/2, 0.19f/2, -0.82f/2); // N
        coordenadas[23] = new Point3f(0f/2, 0.69f/2, -0.48f/2); // S
        coordenadas[24] = new Point3f(0.5f/2, 0.67f/2, -0.15f/2); // T
        coordenadas[25] = new Point3f(0.78f/2, 0.16f/2, -0.27f/2); // O
        coordenadas[26] = new Point3f(0.46f/2, -0.14f/2, -0.68f/2); // I
        coordenadas[27] = new Point3f(-0.02f/2, 0.19f/2, -0.82f/2); // N

        coordenadas[28] = new Point3f(-1.3f/2, -0.63f/2, -0.39f/2);  // A1
        coordenadas[29] = new Point3f(-0.5f/2, -0.67f/2, 0.16f/2);  // C
        coordenadas[30] = new Point3f(-0.78f/2, -0.14f/2, 0.29f/2); // G
        coordenadas[31] = new Point3f(-0.79f/2, 0.19f/2, -0.22f/2); // M
        coordenadas[32] = new Point3f(-0.51f/2, -0.12f/2, -0.65f/2); // H
        coordenadas[33] = new Point3f(-0.33f/2, -0.67f/2, -0.42f/2); // D
        coordenadas[34] = new Point3f(-0.5f/2, -0.67f/2, 0.16f/2);  // C
        
        coordenadas[35] = new Point3f(1.3f/2, 0.63f/2, 0.39f/2); // I1
        coordenadas[36] = new Point3f(0.78f/2, 0.16f/2, -0.27f/2); // O
        coordenadas[37] = new Point3f(0.5f/2, 0.67f/2, -0.15f/2); // T
        coordenadas[38] = new Point3f(0.33f/2, 0.66f/2, 0.43f/2); // P
        coordenadas[39] = new Point3f(0.51f/2, 0.13f/2, 0.67f/2); // K
        coordenadas[40] = new Point3f(0.79f/2, -0.18f/2, 0.23f/2); // J
        coordenadas[41] = new Point3f(0.78f/2, 0.16f/2, -0.27f/2); // O
        
        coordenadas[42] = new Point3f(-0.06f/2, -0.63f/2, -1.36f/2); // B1
        coordenadas[43] = new Point3f(-0.33f/2, -0.67f/2, -0.42f/2); // D
        coordenadas[44] = new Point3f(-0.51f/2, -0.12f/2, -0.65f/2); // H
        coordenadas[45] = new Point3f(-0.02f/2, 0.19f/2, -0.82f/2); // N
        coordenadas[46] = new Point3f(0.46f/2, -0.14f/2, -0.68f/2); // I
        coordenadas[47] = new Point3f(0.27f/2, -0.67f/2, -0.44f/2); // E
        coordenadas[48] = new Point3f(-0.33f/2, -0.67f/2, -0.42f/2); // D
        
        coordenadas[49] = new Point3f(0.06f/2, 0.63f/2, 1.36f/2); // H1
        coordenadas[50] = new Point3f(0.51f/2, 0.13f/2, 0.67f/2); // K
        coordenadas[51] = new Point3f(0.33f/2, 0.66f/2, 0.43f/2); // P
        coordenadas[52] = new Point3f(-0.27f/2, 0.67f/2, 0.45f/2); // Q
        coordenadas[53] = new Point3f(-0.46f/2, 0.16f/2, 0.7f/2); // L
        coordenadas[54] = new Point3f(0.02f/2, -0.18f/2, 0.83f/2); // F
        coordenadas[55] = new Point3f(0.51f/2, 0.13f/2, 0.67f/2); // K
        
        coordenadas[56] = new Point3f(1.24f/2, -0.69f/2, -0.48f/2); // C1
        coordenadas[57] = new Point3f(0.27f/2, -0.67f/2, -0.44f/2); // E
        coordenadas[58] = new Point3f(0.46f/2, -0.14f/2, -0.68f/2); // I
        coordenadas[59] = new Point3f(0.78f/2, 0.16f/2, -0.27f/2); // O
        coordenadas[60] = new Point3f(0.79f/2, -0.18f/2, 0.23f/2); // J
        coordenadas[61] = new Point3f(0.47553f/2, -0.68f/2, 0.12941f/2);  // B
        coordenadas[62] = new Point3f(0.27f/2, -0.67f/2, -0.44f/2); // E
        
        coordenadas[63] = new Point3f(-1.24f/2, 0.69f/2, 0.48f/2); // G1
        coordenadas[64] = new Point3f(-0.46f/2, 0.16f/2, 0.7f/2); // L
        coordenadas[65] = new Point3f(-0.27f/2, 0.67f/2, 0.45f/2); // Q
        coordenadas[66] = new Point3f(-0.48f/2, 0.69f/2, -0.11f/2); // R
        coordenadas[67] = new Point3f(-0.79f/2, 0.19f/2, -0.22f/2); // M
        coordenadas[68] = new Point3f(-0.78f/2, -0.14f/2, 0.29f/2); // G
        coordenadas[69] = new Point3f(-0.46f/2, 0.16f/2, 0.7f/2); // L
        
        coordenadas[70] = new Point3f(0.81f/2, -0.72f/2, 1.04f/2);  // D1
        coordenadas[71] = new Point3f(0.47553f/2, -0.68f/2, 0.12941f/2);  // B
        coordenadas[72] = new Point3f(0.79f/2, -0.18f/2, 0.23f/2); // J
        coordenadas[73] = new Point3f(0.51f/2, 0.13f/2, 0.67f/2); // K
        coordenadas[74] = new Point3f(0.02f/2, -0.18f/2, 0.83f/2); // F
        coordenadas[75] = new Point3f(0f/2, -0.68f/2, 0.5f/2);  // A
        coordenadas[76] = new Point3f(0.47553f/2, -0.68f/2, 0.12941f/2);  // B
        
        coordenadas[77] = new Point3f(-0.81f/2, 0.72f/2, -1.04f/2); // F1
        coordenadas[78] = new Point3f(-0.79f/2, 0.19f/2, -0.22f/2); // M
        coordenadas[79] = new Point3f(-0.48f/2, 0.69f/2, -0.11f/2); // R
        coordenadas[80] = new Point3f(0f/2, 0.69f/2, -0.48f/2); // S
        coordenadas[81] = new Point3f(-0.02f/2, 0.19f/2, -0.82f/2); // N
        coordenadas[82] = new Point3f(-0.51f/2, -0.12f/2, -0.65f/2); // H
        coordenadas[83] = new Point3f(-0.79f/2, 0.19f/2, -0.22f/2); // M

        for(int i=0;i<colores.length;i++)
            colores[i] = red;

        lsa.setCoordinates(0, coordenadas);
        //lsa.setColors(0, colores);

        return lsa;
    }
}
