package com.pluralsight.model;

import com.pluralsight.service.Library;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Member {
    private String memberId;
    private String name;
    private String email;
    private Map<Item, LocalDate> borrowedItems;

    public Member(String memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.borrowedItems = new HashMap<>();
    }

    // Getters
    public String getMemberId() { return memberId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public List<Item> getBorrowedItems() { return borrowedItems.keySet().stream().toList(); }

    // Item management
    public boolean borrowItem(Item item) {
        if(borrowedItems.containsKey(item)) return false;
        borrowedItems.put(item, LocalDate.now());
        return true;
    }
    public boolean borrowItem(String id){
        Item item = Library.getInstance().getItem(id);
        if(item == null) return false;
        return borrowItem(item);
    }

    public void borrowItem(Item item, String date) {
        if(borrowedItems.containsKey(item)) return;
        borrowedItems.put(item, LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    public boolean returnItem(Item item) {
        return borrowedItems.remove(item) != null; //returns true if something existed
    }
    public boolean returnItem(String id){
        Item item = Library.getInstance().getItem(id);
        if(item == null) return false;
        return returnItem(item);
    }


    public boolean hasBorrowedItem(Item item) {
        return borrowedItems.containsKey(item);
    }
    public boolean hasBorrowedItem(String id){
        Item item = Library.getInstance().getItem(id);
        if(item == null) return false;
        return hasBorrowedItem(item);
    }

    public int getBorrowedCount() {
        return borrowedItems.size();
    }

    public String getBorrowedItemDateAsString(Item item){
        return borrowedItems.get(item).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    //calculate total fees for this user
    public float calculateLateFees(){
        float fee = 0;
        for(Map.Entry<Item, LocalDate> item : borrowedItems.entrySet()){
            fee += item.getKey().getLateFee() * item.getValue().until(LocalDate.now(), ChronoUnit.DAYS);
        }
        return fee;
    }

    @Override
    public String toString() {
        return String.format("%s: %s (%s) - %d items borrowed - $%.2f in late fees",
                memberId, name, email, borrowedItems.size(), calculateLateFees());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Member member = (Member) obj;
        return memberId.equals(member.memberId);
    }

    @Override
    public int hashCode() {
        return memberId.hashCode();
    }
}