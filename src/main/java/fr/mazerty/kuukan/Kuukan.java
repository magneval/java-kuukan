package fr.mazerty.kuukan;

import com.jme3.app.SimpleApplication;
import com.jme3.font.BitmapText;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
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

        IntStream.range(0, 1000).forEach(i -> {
            Geometry geometry = new Geometry("sphere" + i, sphere);
            geometry.setMaterial(material);
            geometry.setLocalTranslation(mt.nextFloat(), mt.nextFloat(), mt.nextFloat());
            rootNode.attachChild(geometry);
        });

        BitmapText text = new BitmapText(guiFont);
        text.setText("Pseudorandom cubic galaxy");
        text.setLocalTranslation(0, settings.getHeight(), 0);

        guiNode.attachChild(text);

        FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
        fpp.addFilter(new BloomFilter(BloomFilter.GlowMode.Objects));
        viewPort.addProcessor(fpp);
    }

}
