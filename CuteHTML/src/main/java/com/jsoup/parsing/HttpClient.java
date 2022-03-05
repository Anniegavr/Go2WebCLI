package com.jsoup.parsing;

import com.jsoup.parsing.factories.implementations.CommandFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

import static javax.swing.UIManager.getString;

/**
 * AbstractHttp http = new ConcreteHttp();
 * AbstractDisplay display = new ConcreteDisplay();
 * AbstractHttpResponse response = http.get(url);
 * display.render(response.body());
 */

public class HttpClient {

    public static void main(String[] args) throws Exception {
        CommandFactory commandFactory = new CommandFactory();
        commandFactory.initiateDialogue();
    }
}
