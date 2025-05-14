package com.pluralsight.exception.lib;

import com.pluralsight.exception.LibraryException;

public class ItemNotAvailableException extends LibraryException {
    public ItemNotAvailableException(String itemTitle, String itemID) {
        super(String.format("Item Not Available, Title: %s ID: %s", itemTitle, itemID));
    }
}
