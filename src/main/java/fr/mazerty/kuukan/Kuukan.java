package fr.mazerty.kuukan;

import com.jme3.app.SimpleApplication;
import com.jme3.font.BitmapText;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.BloomFilter;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Sphere;
import org.apache.commons.math3.random.MersenneTwister;

import java.util.stream.IntStream;

public class Kuukan extends SimpleApplication {

    private final MersenneTwister mt = new MersenneTwister(42);

    public static void main(String[] args) {
        new Kuukan().start();
    }

    @Override
    public void simpleInitApp() {
        Sphere sphere = new Sphere(3, 4, 0.01f);

        Material material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        material.setColor("Color", ColorRGBA.White);
        material.setColor("GlowColor", ColorRGBA.White);

        IntStream.range(0, 3000).forEach(i -> {
            Geometry geometry = new Geometry("sphere" + i, sphere);
            geometry.setMaterial(material);
            geometry.setLocalTranslation(generatePseudoRandomPosition(6, 10));
            rootNode.attachChild(geometry);
        });

        BitmapText text = new BitmapText(guiFont);
        text.setText("Awesome rotating galaxy !");
        text.setLocalTranslation(0, settings.getHeight(), 0);

        guiNode.attachChild(text);

        FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
        fpp.addFilter(new BloomFilter(BloomFilter.GlowMode.Objects));
        viewPort.addProcessor(fpp);
    }

    @Override
    public void simpleUpdate(float tpf) {
        rootNode.rotate(0, tpf / 10, 0);
    }

    private Vector3f generatePseudoRandomPosition(int maxRadius, int flatteningRatio) {
        double radialDistance = mt.nextDouble() * maxRadius;
        double azimuthalAngle = mt.nextDouble() * 2 * Math.PI;
        double polarAngle = mt.nextDouble() * Math.PI;

        double y = radialDistance * Math.cos(polarAngle);
        double projection = radialDistance * Math.sin(polarAngle);
        double x = projection * Math.cos(azimuthalAngle);
        double z = projection * Math.sin(azimuthalAngle);

        return new Vector3f((float) x, (float) y / flatteningRatio, (float) z);
    }

}
