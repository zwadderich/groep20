package raamwerk;

import java.nio.file.Files;
import java.util.*;
import java.io.*;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 *
 * @author Jasper De Vrient
 */
public class SimulatieRunner {

    private PriorityQueue<SimulatieWrapper> queue = new PriorityQueue<>();
    private String outputDir;
    private Output output = new Output();

    public Output getOutput() {
        return output;
    }

    public SimulatieRunner(String outputDir) {
        this.outputDir = outputDir + File.separator + GregorianCalendar.getInstance().get(GregorianCalendar.YEAR) +
                GregorianCalendar.getInstance().get(GregorianCalendar.MONTH) +
                GregorianCalendar.getInstance().get(GregorianCalendar.DAY_OF_MONTH) + 
                GregorianCalendar.getInstance().get(GregorianCalendar.HOUR) +
                GregorianCalendar.getInstance().get(GregorianCalendar.MINUTE) +
                GregorianCalendar.getInstance().get(GregorianCalendar.SECOND);
    }

    public void offer(Simulatie simulatie, int aantalKeer, InputFile input) {
        Properties p = input.getProperties();
        SimulatieWrapper sw = new SimulatieWrapper(aantalKeer, simulatie, p);
        sw.setOutput(output);
        queue.offer(sw);

    }

    public void run() {
        try {
            Path p = Paths.get(outputDir);
            if (Files.notExists(p)) {
                Files.createDirectories(p);
            }
            
            while (!queue.isEmpty()) {
                SimulatieWrapper sw = queue.poll();
                int i = 0;
               for (OutputWriter w : output.getWriters())
                   w.setOutput( new File((p.toString() + File.separator  + sw.getAantal() + "_" +  sw.fileName() + "_" + i++ + w.extention())));
               sw.voerUit();
               
            }
        } catch (Exception ex) {
throw new IllegalArgumentException("",ex);
        }

    }

}
