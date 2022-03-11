package com.jsoup.parsing;

import net.htmlparser.jericho.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class PageEl {
    String webLink;

    public PageEl(String webLink) {
        this.webLink = webLink;
    }

    public PageEl(){}

    public void setWebLink(String webLink) {
        this.webLink = webLink;
    }

    public String getWebLink() {
        return webLink;
    }

    public void fetchPage(String url){
        this.printNicely(this.go2web(this.getWebLink(), false));
    }
    /**
     * Prints out all the text on the page
     * @param doc - generated html document from the request
     */
    public void printNicely(Document doc) {
        String html = doc.html();
        Source htmlSource = new Source(html);
        Segment segment = new Segment(htmlSource, 0, htmlSource.length());
        Renderer htmlRender = new Renderer(segment).setIncludeHyperlinkURLs(true);
        System.out.println(htmlRender);

    }

    /**
     * Acts as a Google search engine
     * @param googleSearchQuery - the terms being searched
     * @throws IOException - exception thrown in case of wrong input / output
     */
    public void searchEngine(String googleSearchQuery) throws IOException {
        //URL encode string in JAVA to use with google search
        System.out.println("Searching for: " + googleSearchQuery);
        googleSearchQuery = googleSearchQuery.trim();
        googleSearchQuery = URLEncoder
                .encode(googleSearchQuery, StandardCharsets.UTF_8.toString());
        String queryUrl = "https://www.google.com/search?q=" + googleSearchQuery + "&num=10";
        System.out.println("This are the results for "+queryUrl);
        displayLinks(queryUrl, true);
    }

    /**
     * The user can see all the webpage's links, along with the accompanying text
     * @param url - the page from which we want to display the links
     * @param isSearch - boolean parameter; if it's a search page
     */
    public void displayLinks(String url, boolean isSearch){
        Document doc;
        int displayedLinkNo = 0;
        if (isSearch){
            doc = go2web(url, true);
        }else {
            doc = go2web(url, false);
        }

        Elements links = doc.select("a[href]");
        if (isSearch){ //if it's a search' results that we display
            for (Element link : links) {
                if (displayedLinkNo < 10){
                    // get the value from href attribute
                    String temp = link.attr("href");
                    if(temp.startsWith("/url?q=")){
                        //use regex to get domain name
                        System.out.println("\nlink : " + link.attr("href"));
                        System.out.println("text : " + link.text());
                        displayedLinkNo +=1;
                    }
                } else { //if 10 links have been displayed
                    break;
                }
            }
        } else if (isSearch == false){ //if we are simply displaying links from a webpage other than a search
            for (Element link : links) {
                // get the value from href attribute
                String temp = link.attr("href");
                if(temp.contains("https:")){
//                    //use regex to get domain name
                    System.out.println("\nlink : " + temp);
                    System.out.println("text : " + link.text());
                }
            }
        }

    }

    /**
     * Function to search through the current webpage's links
     * @param url - the page through which the searched should go through
     * @param st - the keyword that links in the current webpage should contain
     */
    public void searchLinkInCurrentPage(String url, String st) {
        Document doc = go2web(url, false);
        Elements links = doc.select("a[href]");
        for (Element link : links) {
            // get the value from href attribute
            String temp = link.attr("href");
            if(temp.contains("https:") && temp.contains(st)){
                System.out.println("\nlink : " + temp);
                System.out.println("text : " + link.text());
            }
        }
    }


    /**
     * Function to fetch the entire web page, along with all the tags
     * @param webLink - link to the actual page
     * @param isASearch - parameter to pass onto the other methods
     * @return - an html doc file
     */
    public Document go2web(String webLink, boolean isASearch){

        URL url = null;
        try {
            url = new URL(webLink);
            URLConnection connection = url.openConnection(); //Establishes a socket connection to the specified url
            if (isASearch){
                final String agent = "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)";
                System.out.println();
                /**
                 * User-Agent is mandatory otherwise Google will return HTTP response
                 * code: 403
                 */
                connection.setRequestProperty("User-Agent", agent);
            }
            String redirect = connection.getHeaderField("Location");
            if (redirect != null){
                connection = new URL(redirect).openConnection();
                System.out.println("Redirect is "+redirect);
            }

            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;

                StringBuilder stringBuilder = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {

                    stringBuilder.append(inputLine);
                }
                Document doc = Jsoup.parse(stringBuilder.toString());
                String title = doc.title();
                System.out.println("title : " + title);
                return doc;

            } catch (UnknownHostException ex) {
                System.out.println("Server not found: " + ex.getMessage());
            } catch (IOException ex) {
                System.out.println("I/O error: " + ex.getMessage());
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }



}
