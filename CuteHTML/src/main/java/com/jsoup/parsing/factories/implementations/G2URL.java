package com.jsoup.parsing.factories.implementations;

import com.jsoup.parsing.PageElement;
import com.jsoup.parsing.factories.abstractions.ICommand;

import java.util.Objects;
import java.util.Scanner;

public class G2URL extends ICommand {
    PageElement pageElement;
    Scanner scanner;

    public G2URL(PageElement pageElement, Scanner scanner) {
        this.pageElement = pageElement;
        this.scanner = scanner;
    }

    @Override
    public void workflow(){
        System.out.print("Insert link: ");
        String url = scanner.nextLine();
        PageElement pageElement = new PageElement(url);
        System.out.println("Additional: would you like to see the page's content, or just the links?\n"+
                "page / links : ");
        String chose = scanner.nextLine();
        if (Objects.equals(chose, "page")){
            pageElement.fetchPage(url);
        }else if (Objects.equals(chose, "links")){
            fetchLinks(url);
        }else {
            System.out.println("Wrong command, retry.\n");
            workflow();
        }
    }

    public void fetchLinks(String url){
        System.out.println("Searching for a particular link? Let us help.\n y\\n: ");
        String yesOrNo = scanner.nextLine();
        if (Objects.equals(yesOrNo, "y")){
            System.out.println("Insert keyword, to find links: ");
            String keyword = scanner.nextLine();
            pageElement.searchLinkInCurrentPage(keyword);
        } else if (Objects.equals(yesOrNo, "n")){
            pageElement.displayLinks(url, false);
        }else {
            System.out.println("Wrong command, retry.\n");
            fetchLinks(url);
        }
    }
}
