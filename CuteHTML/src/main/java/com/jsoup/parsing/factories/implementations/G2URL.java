package com.jsoup.parsing.factories.implementations;

import com.jsoup.parsing.PageEl;
import com.jsoup.parsing.factories.abstractions.IComm;

import java.util.Objects;
import java.util.Scanner;

public class G2URL extends IComm {
    PageEl pageEl;
    Scanner scanner;

    public G2URL(PageEl pageEl, Scanner scanner) {
        this.pageEl = pageEl;
        this.scanner = scanner;
    }

    @Override
    public void workflow(){
        System.out.print("Insert link: ");
        String url = scanner.nextLine();
        PageEl pageEl = new PageEl(url);
        System.out.println("Additional: would you like to see the page's content, or just the links?\n"+
                "page / links : ");

        String chose = scanner.nextLine();
        if (Objects.equals(chose, "page")){
            pageEl.fetchPage(url);
            System.out.println("########## END OF THE PAGE #############");
            System.out.println("Insert next command from the ones available in the main menu:");
        }else if (Objects.equals(chose, "links")){
            fetchLinks(url);
            System.out.println("\n########## END OF THE PAGE #############");
            System.out.println("Insert next command from the ones available in the main menu:");
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
            pageEl.searchLinkInCurrentPage(url, keyword);
        } else if (Objects.equals(yesOrNo, "n")){
            pageEl.displayLinks(url, false);
        }else {
            System.out.println("Wrong command, retry.\n");
            fetchLinks(url);
        }
    }
}
