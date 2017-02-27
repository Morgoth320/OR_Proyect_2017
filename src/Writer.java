import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by Ian on 18/2/2017.
 */
public class Writer {

    /**
     * Static method used to write the general results of the simulation into an html file using the Apache Velocity library
     * It takes the template for the index html from the templates folder, it then switches the values with the dollar sign
     * in the html for the parameters passed. After that, for every simulation ran, it creates a hyperlink to the specific simulation
     * and creates an html file for each one of them. Finally, the final result is written to a new html file in the statistics directory
     * @param numberOfSimulations number of simulations ran
     * @param maxTimePerSimulation maximum time allowed for the simulations to run
     * @param kConnections maximum number of connections
     * @param systemCalls maximum amount of system calls for the simulations
     * @param nAvailableProcesses available processes for query processing
     * @param pAvailableProcesses available processes for query transactions
     * @param mAvailableProcesses available processes for query executions
     * @param timeout timeout of the queries.
     */
    public static void writeIndex(int numberOfSimulations, double maxTimePerSimulation, int kConnections, int systemCalls,
                                  int nAvailableProcesses, int pAvailableProcesses, int mAvailableProcesses, double timeout) {
        VelocityEngine ve = new VelocityEngine();
        ve.init();

        Template t = ve.getTemplate("templates/IndexTemplate.html");
        VelocityContext vc = new VelocityContext();

        vc.put("numberOfSimulations", "" + numberOfSimulations);
        vc.put("maxTimePerSimulation", "" + maxTimePerSimulation);
        vc.put("kConnections", "" + kConnections);
        vc.put("systemCalls", "" + systemCalls);
        vc.put("nAvailableProcesses", "" + nAvailableProcesses);
        vc.put("pAvailableProcesses", "" + pAvailableProcesses);
        vc.put("mAvailableProcesses", "" + mAvailableProcesses);
        vc.put("timeout", "" + timeout);
        StringWriter sw = new StringWriter();

        t.merge(vc, sw);
        String code = sw.toString();
        String link = "";
        for (int i = 1; i <= numberOfSimulations; i++) {
            String path = "statistics/simulation" + i + ".html";
            link += "\t\t<a href=" + path + ">Simulation" + i + "</a><br>\n";
            File file = new File(path);
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        code = code.replaceAll("</body>", link + "\n\t</body>");
        try {
            FileWriter fw = new FileWriter("statistics/index.html");
            fw.write(code);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String... args) {
        Writer.writeIndex(5, 2.345, 3, 5, 6, 1, 5, 3.444);
    }
}