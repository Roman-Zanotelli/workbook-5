package com.pluralsight.service;

import com.pluralsight.exception.LibraryException;
import com.pluralsight.exception.lib.*;
import com.pluralsight.model.Item;
import com.pluralsight.model.Book;
import com.pluralsight.model.Movie;
import com.pluralsight.model.Magazine;
import com.pluralsight.model.Member;
import java.util.*;
import java.io.*;

public class Library {
    private Map<String, Item> items;
    private Map<String, Member> members;
    private static int memberCounter = 1000;
    private Logger logger = Logger.getInstance();
    private static Library self;

    private Library() {
        this.items = new HashMap<>();
        this.members = new HashMap<>();
        logger.info("Library system initialized");
    }

    public static Library getInstance(){
        if(self == null) self = new Library();
        return self;
    }
    // Item management
    public boolean addItem(Item item) {
        if (!items.containsKey(item.getId())) {
            items.put(item.getId(), item);
            logger.info("Item added: " + item.getTitle() + " (ID: " + item.getId() + ")");
            return true;
        }
        logger.warn("Attempted to add duplicate item: " + item.getId());
        return false; // Item already exists
    }

    public Item getItem(String id) {
        Item item = items.get(id);
        if (item == null) {
            logger.warn("Item not found: " + id);
        }
        return item;
    }

    public List<Item> getAllItems() {
        logger.debug("Retrieved all items, count: " + items.size());
        return new ArrayList<>(items.values());
    }

    public List<Item> getAvailableItems() {
        List<Item> available = new ArrayList<>();
        for (Item item : items.values()) {
            if (item.isAvailable()) {
                available.add(item);
            }
        }
        logger.debug("Retrieved available items, count: " + available.size());
        return available;
    }

    // Backward compatibility methods for books
    public boolean addBook(Book book) {
        return addItem(book);
    }

    public Book getBook(String isbn) {
        Item item = getItem(isbn);
        return (item instanceof Book) ? (Book) item : null;
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        for (Item item : items.values()) {
            if (item instanceof Book) {
                books.add((Book) item);
            }
        }
        return books;
    }

    public List<Book> getAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        for (Item item : items.values()) {
            if (item instanceof Book && item.isAvailable()) {
                availableBooks.add((Book) item);
            }
        }
        return availableBooks;
    }

    // Member management
    public boolean addMember(Member member) {
        if (!members.containsKey(member.getMemberId())) {
            members.put(member.getMemberId(), member);
            logger.info("Member registered: " + member.getName() + " (ID: " + member.getMemberId() + ")");
            return true;
        }
        logger.warn("Attempted to add duplicate member: " + member.getMemberId());
        return false; // Member already exists
    }

    public Member getMember(String memberId) {
        Member member = members.get(memberId);
        if (member == null) {
            logger.warn("Member not found: " + memberId);
            throw new MemberNotFoundException(memberId);
        }
        return member;
    }

    public static String generateMemberId() {
        return "M" + (++memberCounter);
    }

    // Search functionality
    public List<Item> searchItems(String query) {
        List<Item> results = new ArrayList<>();
        query = query.toLowerCase();

        for (Item item : items.values()) {
            if (item.getTitle().toLowerCase().contains(query) ||
                    item.getCreator().toLowerCase().contains(query) ||
                    item.getGenre().toLowerCase().contains(query) ||
                    item.getId().toLowerCase().contains(query)) {
                results.add(item);
            }
        }
        logger.info("Search performed for: '" + query + "', results: " + results.size());
        return results;
    }

    public List<Item> searchByGenre(String genre) {
        List<Item> results = new ArrayList<>();
        for (Item item : items.values()) {
            if (item.getGenre().trim().equalsIgnoreCase(genre.trim())) {
                results.add(item);
            }
        }
        logger.info("Genre search for '" + genre + "', results: " + results.size());
        return results;
    }

    //Generic By Title
    public List<Item> searchByTitle(String title){
        return items.values().stream().filter(item -> item.getTitle().equalsIgnoreCase(title)).toList();
    }

    public List<Item> searchByCreator(String creator) {
        List<Item> results = new ArrayList<>();
        for (Item item : items.values()) {
            if (item.getCreator().toLowerCase().contains(creator.toLowerCase())) {
                results.add(item);
            }
        }
        logger.info("Creator search for '" + creator + "', results: " + results.size());
        return results;
    }

    // Backward compatibility for book searches
    public List<Book> searchBooks(String query) {
        List<Book> bookResults = new ArrayList<>();
        List<Item> allResults = searchItems(query);
        for (Item item : allResults) {
            if (item instanceof Book) {
                bookResults.add((Book) item);
            }
        }
        return bookResults;
    }

    public List<Book> searchByAuthor(String author) {
        List<Book> bookResults = new ArrayList<>();
        List<Item> allResults = searchByCreator(author);
        for (Item item : allResults) {
            if (item instanceof Book) {
                bookResults.add((Book) item);
            }
        }
        return bookResults;
    }

    // Borrow/Return operations
    public boolean borrowItem(String memberId, String itemId) throws LibraryException {
        Member member = members.get(memberId);
        Item item = items.get(itemId);

        if (member == null) {
            logger.error("Borrow attempt failed - Member not found: " + memberId);
            throw new MemberNotFoundException(memberId);
        }

        if (item == null) {
            logger.error("Borrow attempt failed - Item not found: " + itemId);
            throw new ItemNotFoundException(itemId);
        }

        if (!item.isAvailable()) {
            logger.warn("Borrow attempt failed - Item not available: " + item.getTitle() + " (ID: " + itemId + ")");
            throw new ItemNotAvailableException(item.getTitle(), itemId);
        }

        if (member.hasBorrowedItem(item)) {
            logger.warn("Borrow attempt failed - Member already has item: " + memberId + ", ID: " + itemId);
            throw new ItemAlreadyBorrowedException(memberId, itemId);
        }
        if(!member.borrowItem(item)){
            logger.warn("Borrow attempt failed - borrowItem(item) returned false: " + memberId + ", ID: " + itemId);
            throw new BorrowItemFailedException(memberId, itemId);
            
        }
        item.setAvailable(false);
        member.borrowItem(item);
        logger.info("Item borrowed successfully - Member: " + member.getName() + " (" + memberId + "), Item: " + item.getTitle() + " (" + itemId + ")");
        return true;
    }

    public boolean returnItem(String memberId, String itemId) throws LibraryException{
        Member member = members.get(memberId);
        Item item = items.get(itemId);

        if (member == null) {
            logger.error("Return attempt failed - Member not found: " + memberId);
            throw new MemberNotFoundException(memberId);
        }

        if (item == null) {
            logger.error("Return attempt failed - Item not found: " + itemId);
            throw new ItemNotFoundException(itemId);
        }

        if (!member.hasBorrowedItem(item)) {
            logger.warn("Return attempt failed - Member doesn't have item: " + memberId + ", ID: " + itemId);
            throw new ItemNotBorrowedException(memberId, itemId);
        }

        if(!member.returnItem(item)){
            logger.warn("Return attempt failed - returnItem(item) returned false: " + memberId + ", ID: " + itemId);
            throw new ReturnItemFailedException(memberId , itemId);
        }
        item.setAvailable(true);
        member.returnItem(item);
        logger.info("Item returned successfully - Member: " + member.getName() + " (" + memberId + "), Item: " + item.getTitle() + " (" + itemId + ")");
        return true;
    }

    // Backward compatibility for book operations
    public boolean borrowBook(String memberId, String isbn) {
        return borrowItem(memberId, isbn);
    }

    public boolean returnBook(String memberId, String isbn) {
        return returnItem(memberId, isbn);
    }

    // File I/O operations
    public void saveToCSV() throws IOException {
        logger.info("Starting data save operation");

        // Create data directory if it doesn't exist
        java.io.File dataDir = new java.io.File("data");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
            logger.info("Created data directory");
        }

        try {
            // Save all items (unified format)
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/items.csv"))) {
                writer.write("ID,Type,Title,Creator,Genre,Available,Extra1,Extra2");
                writer.newLine();

                for (Item item : items.values()) {
                    String extra1 = "", extra2 = "";
                    if (item instanceof Movie) {
                        Movie movie = (Movie) item;
                        extra1 = String.valueOf(movie.getDuration());
                    } else if (item instanceof Magazine) {
                        Magazine mag = (Magazine) item;
                        extra1 = mag.getIssueNumber();
                        extra2 = mag.getPublicationDate();
                    }

                    writer.write(String.format("%s,%s,%s,%s,%s,%b,%s,%s",
                            item.getId(), item.getType(), item.getTitle(),
                            item.getCreator(), item.getGenre(), item.isAvailable(),
                            extra1, extra2));
                    writer.newLine();
                }
            }

            // Save members
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/members.csv"))) {
                writer.write("MemberID,Name,Email");
                writer.newLine();

                for (Member member : members.values()) {
                    writer.write(String.format("%s,%s,%s",
                            member.getMemberId(), member.getName(), member.getEmail()));
                    writer.newLine();
                }
            }

            // Save borrowed items relationships
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/borrowed.csv"))) {
                writer.write("MemberID,ItemID,CheckOutDate");
                writer.newLine();

                for (Member member : members.values()) {
                    for (Item item : member.getBorrowedItems()) {
                        writer.write(String.format("%s,%s,%s", member.getMemberId(), item.getId(), member.getBorrowedItemDateAsString(item)));
                        writer.newLine();
                    }
                }
            }

            logger.info("Data save operation completed successfully");
        } catch (IOException e) {
            logger.error("Failed to save data", e);
            throw e;
        }
    }

    public void loadFromCSV() throws IOException {
        logger.info("Starting data load operation");

        // Create data directory if it doesn't exist
        java.io.File dataDir = new java.io.File("data");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
            logger.info("Created data directory");
        }

        try {
            // Load all items from items.csv (primary source)
            File itemsFile = new File("data/items.csv");
            if (itemsFile.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(itemsFile))) {
                    String line = reader.readLine(); // Skip header
                    int itemCount = 0;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(",");
                        if (parts.length >= 6) {
                            String id = parts[0];
                            String type = parts[1];
                            String title = parts[2];
                            String creator = parts[3];
                            String genre = parts[4];
                            boolean available = Boolean.parseBoolean(parts[5]);

                            Item item = null;
                            if ("Book".equals(type)) {
                                item = new Book(id, title, creator, genre, available);
                            } else if ("Movie".equals(type) && parts.length >= 7) {
                                int duration = Integer.parseInt(parts[6]);
                                item = new Movie(id, title, creator, genre, duration, available);
                            } else if ("Magazine".equals(type) && parts.length >= 8) {
                                String issueNumber = parts[6];
                                String pubDate = parts.length > 7 ? parts[7] : "";
                                item = new Magazine(id, title, creator, genre, issueNumber, pubDate, available);
                            }

                            if (item != null) {
                                items.put(id, item);
                                itemCount++;
                            }
                        }
                    }
                    logger.info("Loaded " + itemCount + " items from items.csv");
                }
            } else {
                logger.warn("items.csv not found. Please ensure the data directory contains this file.");
            }

            // Load members
            File membersFile = new File("data/members.csv");
            if (membersFile.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(membersFile))) {
                    String line = reader.readLine(); // Skip header
                    int memberCount = 0;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(",");
                        if (parts.length == 3) {
                            Member member = new Member(parts[0], parts[1], parts[2]);
                            members.put(member.getMemberId(), member);
                            memberCount++;
                            // Update counter to prevent ID conflicts
                            if (parts[0].startsWith("M")) {
                                try {
                                    int id = Integer.parseInt(parts[0].substring(1));
                                    if (id >= memberCounter) {
                                        memberCounter = id;
                                    }
                                } catch (NumberFormatException e) {
                                    logger.warn("Malformed member ID: " + parts[0]);
                                }
                            }
                        }
                    }
                    logger.info("Loaded " + memberCount + " members from CSV");
                }
            }

            // Load borrowed items relationships
            File borrowedFile = new File("data/borrowed.csv");
            if (borrowedFile.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(borrowedFile))) {
                    String line = reader.readLine(); // Skip header
                    int borrowedCount = 0;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(",");
                        if (parts.length == 3) {
                            Member member = members.get(parts[0]);
                            if (member != null) {
                                member.borrowItem(items.get(parts[1]), parts[2]);
                                borrowedCount++;
                            }
                        }
                    }
                    logger.info("Loaded " + borrowedCount + " borrowed item records from CSV");
                }
            }

            logger.info("Data load operation completed successfully");
        } catch (IOException e) {
            logger.error("Failed to load data", e);
            throw e;
        }
    }

    // Validation methods
    public static boolean isValidISBN(String isbn) {
        boolean valid = isbn != null && isbn.matches("\\d{10}|\\d{13}");
        if (!valid) {
            Logger.getInstance().warn("Invalid ISBN format: " + isbn);
        }
        return valid;
    }

    public static boolean isValidEmail(String email) {
        boolean valid = email != null && email.contains("@") && email.contains(".") && email.charAt(0) != '@';
        if (!valid) {
            Logger.getInstance().warn("Invalid email format: " + email);
        }
        return valid;
    }


    //Movie Features
    public List<Movie> getAllMovies(){

        return items.values().stream().filter(item -> item instanceof Movie).map(item -> (Movie) item).toList();
    }

    public List<Movie> getAvailableMovies(){
        return getAvailableItems().stream().filter(item -> item instanceof Movie).map(item -> (Movie) item).toList();
    }

    public List<Movie> searchMoviesByDirector(String director){
        return searchByCreator(director).stream().filter(item -> item instanceof Movie).map(item -> (Movie) item).toList();
    }
    public List<Movie> searchMoviesByGenre(String genre){
        return searchByGenre(genre).stream().filter(item -> item instanceof Movie).map(item -> (Movie) item).toList();
    }
    public List<Movie> searchMoviesByTitle(String title){
        return searchByTitle(title).stream().filter(item -> item instanceof Movie).map(item -> (Movie) item).toList();
    }
    public List<Movie> searchMovies(String query){
        return searchItems(query).stream().filter(item -> item instanceof Movie).map(item -> (Movie) item).toList();
    }
    public void displayMovieDuration(String movieId){
        if(items.get(movieId) instanceof Movie movie){
            System.out.printf("Movie: %s | Movie Duration: %d min", movie.getTitle(), movie.getDuration());
            return;
        }
        System.out.println("Movie Not Found, Please Check Movie ID");
    }




    //Magazine Features
    public List<Magazine> getAllMagazine(){
        return items.values().stream().filter(item -> item instanceof Magazine).map(item -> (Magazine) item).toList();
    }
    public List<Magazine> getAvailableMagazine(){
        return getAvailableItems().stream().filter(item -> item instanceof Magazine).map(item -> (Magazine) item).toList();
    }
    public List<Magazine> searchMagazinesByPublisher(String publisher){
        return searchByCreator(publisher).stream().filter(item -> item instanceof Magazine).map(item -> (Magazine) item).toList();
    }
    public List<Magazine> searchMagazinesByGenre(String genre){
        return searchByGenre(genre).stream().filter(item -> item instanceof Magazine).map(item -> (Magazine) item).toList();
    }
    public List<Magazine> searchMagazinesByTitle(String title){
        return searchByTitle(title).stream().filter(item -> item instanceof  Magazine).map(item -> (Magazine) item).toList();
    }
    public List<Magazine> searchMagazines(String query){
        return searchItems(query).stream().filter(item -> item instanceof Magazine).map(item -> (Magazine) item).toList();
    }
    public void displayIssueInfo(String magId){
        if(items.get(magId) instanceof Magazine mag){
            System.out.printf("Magazine: %s | Issue#: %s | Publication Date: %s\n",mag.getTitle(), mag.getIssueNumber(), mag.getPublicationDate());
            return;
        }
        System.out.println("Magazine Not Found, Please Check Magazine ID");
    }

    //Late fee
    public float calculateLateFees(String memberId) throws MemberNotFoundException {
        Member member = members.get(memberId);
        if(member == null) throw new MemberNotFoundException(memberId);
        return member.calculateLateFees();
    }
}