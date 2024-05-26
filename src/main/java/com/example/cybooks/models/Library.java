package com.example.cybooks.models;

import java.io.*;
import java.util.*;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.xml.sax.InputSource;
import javax.xml.parsers.*;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * The Library class provides methods to search for books in the BnF (Bibliothèque nationale de France) catalog
 * and parse the XML response to extract book information.
 */
public class Library {

    /**
     * Searches for books in the BnF catalog using the given query string.
     *
     * @param queryString the query string for searching books
     * @return a list of maps containing book information
     * @throws IOException if an I/O error occurs
     * @throws InterruptedException if the operation is interrupted
     * @throws ParserConfigurationException if a parser configuration error occurs
     * @throws SAXException if a SAX error occurs during parsing
     */
    public static List<Map<String, String>> searchBooks(String queryString)
            throws IOException, InterruptedException, ParserConfigurationException, SAXException {
        // Construct the search URL
        String apiUrl = "http://catalogue.bnf.fr/api/SRU";
        String urlString = apiUrl + "?version=1.2&operation=searchRetrieve&query=" + URLEncoder.encode(queryString, "UTF-8") + "&recordSchema=dublincore";

        // Create an HTTP client
        HttpClient client = HttpClient.newHttpClient();

        // Create an HTTP request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .build();

        // Send the request and get the response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Parse the XML response
        return parseXMLResponse(response.body());
    }

    /**
     * Parses the XML response to extract book information.
     *
     * @param xmlString the XML response as a string
     * @return a list of maps containing book information
     */
    public static List<Map<String, String>> parseXMLResponse(String xmlString) {
        List<Map<String, String>> booksInfo = new ArrayList<>();

        try {
            // Convert the XML string to an InputStream using UTF-8 encoding
            InputStream is = new ByteArrayInputStream(xmlString.getBytes("UTF-8"));

            // Create a DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            // Create a DocumentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parse the InputStream and build the Document object
            Document document = builder.parse(new InputSource(is));

            // Normalize the XML structure
            document.getDocumentElement().normalize();

            // Get all records
            NodeList records = document.getElementsByTagName("srw:record");

            // Get every book from the XML
            for (int i = 0; i < records.getLength(); i++) {
                Map<String, String> bookInfo = new HashMap<>();
                Node record = records.item(i);

                // Get all elements with the tag "dc:creator"
                NodeList creators = ((Element) record).getElementsByTagName("dc:creator");
                for (int j = 0; j < creators.getLength(); j++) {
                    bookInfo.put("Author" + (j > 0 ? (" " + (j + 1)) : ""), creators.item(j).getTextContent());
                }

                // Get all elements with the tag "dc:title"
                NodeList titles = ((Element) record).getElementsByTagName("dc:title");
                if (titles.item(0) != null)
                    bookInfo.put("Title", titles.item(0).getTextContent());

                // Get all elements with the tag "dc:type"
                NodeList types = ((Element) record).getElementsByTagName("dc:type");
                if (types.item(0) != null)
                    bookInfo.put("Document Type", types.item(0).getTextContent());

                // Get all elements with the tag "dc:contributor"
                NodeList contributors = ((Element) record).getElementsByTagName("dc:contributor");
                for (int j = 0; j < contributors.getLength(); j++) {
                    bookInfo.put("Contributor" + (j > 0 ? (" " + (j + 1)) : ""), contributors.item(j).getTextContent());
                }

                // Get other information about the publishers
                NodeList publishers = ((Element) record).getElementsByTagName("dc:publisher");
                StringBuilder publiString = new StringBuilder();
                for (int j = 0; j < publishers.getLength(); j++) {
                    publiString.append(publishers.item(j).getTextContent()).append("  ");
                }
                bookInfo.put("Publisher", publiString.toString().trim());

                // Get the date
                NodeList dates = ((Element) record).getElementsByTagName("dc:date");
                if (dates.item(0) != null)
                    bookInfo.put("Date", dates.item(0).getTextContent());

                // Add the book information to the list
                booksInfo.add(bookInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return booksInfo;
    }
}
