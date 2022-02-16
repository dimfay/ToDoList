package ru.team.todo.ui;

import ru.team.todo.ui.commands.Command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Menu {

    private final Map<String, Command> commands = new HashMap<>();

    public Menu addCommand(Command command) {
        commands.put(command.getName(), command);
        return this;
    }

    public void startUI() {
        while (true) {
            try {
                System.out.println("Available commands: ");
                printCommands();

                Scanner scanner = new Scanner(System.in);
                String userInput = scanner.nextLine();

                userInput = userInput.trim();
                if (userInput.isEmpty()) {
                    continue;
                }

                Command cmd = this.commands.get(userInput.toLowerCase());
                if (cmd == null) {
                    System.out.println("Command not found!");
                    continue;
                }
                cmd.executeCommand();

            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void printCommands() {
        List<Command> sortedCommands = new ArrayList<>(this.commands.values());
        Collections.sort(sortedCommands);
        for (Command cmd : sortedCommands) {
            System.out.println("'" + cmd.getName() + "': " + cmd.getDescription());
        }
    }

}
