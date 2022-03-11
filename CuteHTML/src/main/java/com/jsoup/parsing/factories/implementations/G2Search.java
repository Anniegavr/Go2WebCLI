package com.jsoup.parsing.factories.implementations;

import com.jsoup.parsing.PageEl;
import com.jsoup.parsing.factories.abstractions.IComm;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class G2Search extends IComm {
    PageEl pageEl;
    Scanner scanner;

    public G2Search(PageEl pageEl, Scanner scanner) {
        this.pageEl = pageEl;
        this.scanner = scanner;
    }


    @Override
    public void workflow() throws IOException {
        System.out.println("Insert searched term: ");
        String searchedTerm = scanner.nextLine();
        pageEl.searchEngine(searchedTerm);
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
