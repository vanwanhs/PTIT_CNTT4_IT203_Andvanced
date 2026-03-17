package lesson5;

import java.util.List;
public class SleepModeCommand implements Command {
    private List<Command> commands;

    public SleepModeCommand(List<Command> commands) {
        this.commands = commands;
    }

    public void execute() {
        for (Command cmd : commands) {
            cmd.execute();
        }
    }
}