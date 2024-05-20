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

public class Library {

	private Map<Integer, User> users;
    private Map<Integer, Book> books;
    
    public Library()
    {
    	///// bdd a faire
    	// faire les books, users et historique associées
    }
    
    
    public void registration(User user)
    {
    	///// bdd a faire
    	this.users.put(user.getId(), user);
    }
    
    public void editUserInfo(int id, String name, int maxBorrow, History history, List<Book> books)
    {
    	///// bdd a faire
    	this.users.put(id, new User(id, name, maxBorrow, history, books));
    }
    
    public User searchUser(int id)
    {
        return this.users.get(id);
    }
    

    public static List<Map<String, String>> searchBooks(String queryString)
            throws IOException, InterruptedException, ParserConfigurationException, SAXException {
        // Construire l'URL de recherche
        String apiUrl = "http://catalogue.bnf.fr/api/SRU";
        String urlString = apiUrl + "?version=1.2&operation=searchRetrieve&query=" + URLEncoder.encode(queryString, "UTF-8") + "&recordSchema=dublincore";
       
	// Create a HTTP client
        HttpClient client = HttpClient.newHttpClient();

        // Create a HTTP request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .build();

        // Send the request and get the answer
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Parse the xml file received form the output
        List<Map<String, String>> booksInfo = parseXMLResponse(response.body());

       return booksInfo;
    }

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

            // Normalize the XML Structure
            document.getDocumentElement().normalize();

            // Get all records
            NodeList records = document.getElementsByTagName("srw:record");

            for (int i = 0; i < records.getLength(); i++) 
            {
                Map<String, String> bookInfo = new HashMap<>();
                Node record = records.item(i);

                // Get all elements with the tag "dc:creator"
                NodeList creators = ((Element) record).getElementsByTagName("dc:creator");
                for (int j = 0; j < creators.getLength(); j++) {
                    bookInfo.put("Author" + (j > 0 ? (" " + (j + 1)) : ""), creators.item(j).getTextContent());
                }

                // Get all elements with the tag "dc:title"
                NodeList titles = ((Element) record).getElementsByTagName("dc:title");
                if(titles.item(0) != null)
                	bookInfo.put("Title", titles.item(0).getTextContent());
                

                // Get all elements with the tag "dc:type"
                NodeList types = ((Element) record).getElementsByTagName("dc:type");
                if(types.item(0) != null)
                	bookInfo.put("Document Type" , types.item(0).getTextContent());
                

                // Get all elements with the tag "dc:contributor"
                NodeList contributors = ((Element) record).getElementsByTagName("dc:contributor");
                for (int j = 0; j < contributors.getLength(); j++) {
                    bookInfo.put("Contributor" + (j > 0 ? (" " + (j + 1)) : ""), contributors.item(j).getTextContent());
                }

                // Get other informations of the publishers
                NodeList publishers = ((Element) record).getElementsByTagName("dc:publisher");
                String publiString = "";
                for (int j = 0; j < publishers.getLength(); j++) {
                	publiString += publishers.item(j).getTextContent() + "  ";
                }
                bookInfo.put("Publisher", publiString);
                
                // Get the date
                NodeList dates = ((Element) record).getElementsByTagName("dc:date");
                if(dates.item(0) != null)
                	bookInfo.put("Date", dates.item(0).getTextContent());
                
                // Get the ISBN or the link to the notice
                NodeList identifiers = ((Element) record).getElementsByTagName("dc:identifier");
                for (int j = 0; j < identifiers.getLength(); j++) {
                    Node identifier = identifiers.item(j);
                    if (identifier.getTextContent().contains("ISBN")) {
                        bookInfo.put("ISBN", identifier.getTextContent());
                    } else if (identifier.getTextContent().contains("http")) {
                        bookInfo.put("Identifier", identifier.getTextContent());
                    }
                }

                // Get the descriptions
                NodeList descriptions = ((Element) record).getElementsByTagName("dc:description");
                String descriptString = "";
                for (int j = 0; j < descriptions.getLength(); j++) {
                	descriptString += descriptions.item(j).getTextContent() + " ";
                }
                bookInfo.put("Description", descriptString);
                
                // Get the format
                NodeList formats = ((Element) record).getElementsByTagName("dc:format");
                if(formats.item(0) != null)
                	bookInfo.put("Format", formats.item(0).getTextContent());
                

                booksInfo.add(bookInfo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return booksInfo;
    }

    
    public List<Book> mostBorrowed()
    {
    	List<Book> results = new ArrayList<Book>();
    	
    	int i = 0;
    	Book book;
    			
    	do{
    		book = null;
    		
    		for(int j = 0; j < this.books.size(); j++) // for each book of the library
    		{
    			if(!results.contains(this.books.get(j))) // if it is not already in the results list
    			{
    				if(book == null || this.books.get(j).getAllBorrow30Days() > book.getAllBorrow30Days())
    				{
    					book = this.books.get(j);
    				}
    			}
    		}
    		
    		if(book != null)
    		{
    			results.add(book);
    		}
    		i++;
    		
    	}while(i < 30 && book != null);
    	
    	return results;
    }
    
    public void borrow(User user, Book book) // throws exception
    {
    	///// bdd a faire
    	
    	List<ExemplaryBook> exemplaries = book.getExemplaries();
    	ExemplaryBook exemplary = null;
    	int i = 0;
    	int size = exemplaries.size();
    	
    	while(exemplary == null && i < size) // we search an exemplary of the book which is not borrowed
    	{
    		if(exemplaries.get(i).getBorrower() == null)
    			exemplary = exemplaries.get(i);
    		i++;
    	}
    	
    	if(exemplary != null && user.getBooks().size() < user.getMaxBorrow())
    	{
    		user.borrow(book);
        	exemplary.setBorrower(user);
    	}
    	else
    	{
    		// throw exception
    	}
    }
    
}
