import java.lang.InterruptedException;
import java.lang.Process;
import java.lang.ProcessBuilder;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Executer {

    static class Output {
        int exitcode;
        String output;

        protected Output(int exitcode, String output) {
            this.exitcode = exitcode;
            this.output = output;
        }

        public String toString () {
            return String.format("Executer.Output@%s[exitcode=%d,output='%s']",
                System.identityHashCode(Output.class),
                exitcode,
                output);
        }
    }

    public static Output run (File env, String... parts) {
        ProcessBuilder builder = new ProcessBuilder();
        builder.command(parts);
        builder.directory(env);

        try {
            Process process = builder.start();
            InputStream stream = process.getInputStream();
            InputStreamReader r = new InputStreamReader(stream);
            BufferedReader br = new BufferedReader(r);

            List<String> lines = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            int exitcode = process.waitFor();

            return new Executer.Output(exitcode, String.join(System.lineSeparator(), lines));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main (String[] args) {
        File env = new File(System.getProperty("user.dir"));
        Executer.Output output = Executer.run(env, "ls", "-a");
        System.out.println(output);
    }
}
