package raamwerk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 *
 * @author Jasper De Vrient
 */
public abstract class InputFile {

    private String filename;

    public InputFile(String filename) {
        this.filename = filename;
    }

    public Properties getProperties() {
        File f = new File(filename);
        Properties p = null;
        if (f.exists() && f.canRead()) {
            try (FileInputStream input = new FileInputStream(f)) {
                
                p = new Properties();
                p.load(input);
                
            } catch (Exception e) {
                p = null;
                throw new IllegalArgumentException("",e);
            }

        }
        try {
            if (!f.exists())
                f.createNewFile();
        } catch (Exception ex) {}
        if (p == null && f.canWrite()) {
            try (FileOutputStream output = new FileOutputStream(f)) {
                p = new  Properties();
                
                createDefault(p);
                
                p.store(output, null);
            } catch (Exception e) {
                p = null;
                throw new IllegalArgumentException("",e);
            }
        }
        if (p == null) {
            p = new Properties();
            createDefault(p);
        }
            
        return p;
    }

    protected abstract void createDefault(Properties properties);
}
