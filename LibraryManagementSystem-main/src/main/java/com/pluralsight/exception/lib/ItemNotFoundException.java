package com.pluralsight.exception.lib;

import com.pluralsight.exception.LibraryException;

public class ItemNotFoundException extends LibraryException {
    public ItemNotFoundException(String itemID) {
        super(String.format("Item Not Found, ItemID: %s", itemID));
    }
}
