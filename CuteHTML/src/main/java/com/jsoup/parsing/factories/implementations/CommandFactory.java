package com.jsoup.parsing.factories.implementations;

import com.jsoup.parsing.PageElement;
import com.jsoup.parsing.factories.abstractions.ICommand;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class CommandFactory extends ICommand {

    public void initiateDialogue() throws IOException {
        System.out.println("""
                Options:
                1. go2web -u     | to get the content of the webpage located at the URL
                2. go2web -s     | to search the term with Google and see the top 10 results
                3. go2web -h     | to get help
                4. go2web -x     | to exit
                """);
        workflow();
    }
    @Override
    public void workflow() throws IOException {
        String option = null;
        Scanner scanner = new Scanner(System.in);
        option = scanner.nextLine();
        switch (option) {
            case "go2web -u" -> { //Go to url
                PageElement pageElement = new PageElement();
                G2URL g2URL = new G2URL(pageElement, scanner);
                g2URL.workflow();
                workflow();
            }
            case "go2web -s" -> { //Google search
                PageElement searchPageElement = new PageElement();
                G2Search g2Search = new G2Search(searchPageElement, scanner);
                g2Search.workflow();
                workflow();
            }
            case "go2web -h" -> { //Get help
                System.out.println("""
                        Type "go2web -i" to see available instructions
                        Type "clear" to clear the screen.
                        """);
                String s = scanner.nextLine();
                if (Objects.equals(s, "go2web -i")) {
                    initiateDialogue();
                } else if (Objects.equals(s, "clear")) {
                    System.out.flush();
                    initiateDialogue();
                } else {
                    System.out.println("Wrong command, retry.\n");
                    initiateDialogue();
                }
            }
            case "go2web -x" -> //Google search
                    System.exit(0);
            default -> System.out.println("Select one of the available options.\n" +
                    "Type \"g2web -h\" for help.");
        }
    }
}
