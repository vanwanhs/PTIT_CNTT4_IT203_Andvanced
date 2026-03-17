package lesson3;
import java.util.*;

public class RemoteControl {

    private Map<Integer, Command> buttons = new HashMap<>();
    private Stack<Command> history = new Stack<>();

    // gán nút
    public void setCommand(int slot, Command command) {
        buttons.put(slot, command);
        System.out.println("Đã gán " + command.getClass().getSimpleName() + " cho nút " + slot);
    }

    // nhấn nút
    public void pressButton(int slot) {
        Command command = buttons.get(slot);

        if (command != null) {
            command.execute();
            history.push(command);
        } else {
            System.out.println("Chưa gán nút này!");
        }
    }

    // undo
    public void undo() {
        if (!history.isEmpty()) {
            Command last = history.pop();
            System.out.print("Undo: ");
            last.undo();
        } else {
            System.out.println("Không có lệnh để undo!");
        }
    }
}
