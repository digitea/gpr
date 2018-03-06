import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class CommandParser {
    private static Map<String, Callable<Command>> commands;
    private static String[] args;

    public static Command parse (String name, String[] a) {
        args = a;
        try {
            return commands.get(name).call();
        } catch (Exception e) {
            return new UnknownCommand(name);
        }
    }

    static {
        commands = new HashMap<>();
        commands.put("--help", () -> new HelpCommand());
        commands.put("info", () -> new InfoCommand());
        commands.put("work", () -> new WorkCommand(args));
    }

    public static void main (String[] args) {
        String name = args[0];
        args = Arrays.copyOfRange(args, 1, args.length);
        Command c = CommandParser.parse(name, args);
        try {
            System.out.println(c.run());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
