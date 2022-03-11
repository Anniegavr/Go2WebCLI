package com.jsoup.parsing;

import com.jsoup.parsing.factories.implementations.CommFactory;

import static javax.swing.UIManager.getString;

/**
 * AbstractHttp http = new ConcreteHttp();
 * AbstractDisplay display = new ConcreteDisplay();
 * AbstractHttpResponse response = http.get(url);
 * display.render(response.body());
 */

public class HttpClient {

    public static void main(String[] args) throws Exception {
        CommFactory commandFactory = new CommFactory();
        commandFactory.initiateDialogue();
    }
}
