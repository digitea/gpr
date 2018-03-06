import java.io.File;
import java.lang.Process;
import java.lang.ProcessBuilder;
import java.util.concurrent.Executors;

public class WorkCommand implements Command {
    private final String issue;

    public WorkCommand (String[] args) {
        issue = args[0];
    }

    public String run () throws Exception {
        ProcessBuilder builder = new ProcessBuilder();
        builder.command("git", "checkout", "-b", "issue-" + issue);

        builder.directory(new File(System.getProperty("user.home")));
        Process process = builder.start();

        process.waitFor();

        return new InfoCommand().run();
    }
}
