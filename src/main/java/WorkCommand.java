import java.io.File;
import java.lang.Process;
import java.lang.ProcessBuilder;
import java.util.concurrent.Executors;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class WorkCommand implements Command {
    private final String issue;
    private final File testenv = new File("/home/c3/prog/java/notarepo/");

    public WorkCommand (String[] args) {
        issue = args[0];
    }

    public String run () throws Exception {
        Executer.Output o1 = Executer.run(testenv, "git", "checkout", "-b", "issue-"+issue);
        Executer.Output o2 = Executer.run(testenv, "git", "status");
        return String.format("%s%n%s%n",
            o1.output, o2.output);
    }
}
