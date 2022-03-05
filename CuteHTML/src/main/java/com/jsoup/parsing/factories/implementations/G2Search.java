package com.jsoup.parsing.factories.implementations;

import com.jsoup.parsing.PageElement;
import com.jsoup.parsing.factories.abstractions.ICommand;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class G2Search extends ICommand {
    PageElement pageElement;
    Scanner scanner;

    public G2Search(PageElement pageElement, Scanner scanner) {
        this.pageElement = pageElement;
        this.scanner = scanner;
    }


    @Override
    public void workflow() throws IOException {
        System.out.println("Insert searched term: ");
        String searchedTerm = scanner.nextLine();
        pageElement.searchEngine(searchedTerm);
        System.out.println("\nThese were top 10 results.");
        System.out.println("Search another term? y\\n");
        String yesOrNo = scanner.nextLine();
        if (Objects.equals(yesOrNo, "y")){
            workflow();
        } else if (Objects.equals(yesOrNo, "n")){
            System.out.println("Type next command in the main menu:\n");
        }else {
            System.out.println("Wrong command, retry.\n");
            workflow();
        }
    }
}
