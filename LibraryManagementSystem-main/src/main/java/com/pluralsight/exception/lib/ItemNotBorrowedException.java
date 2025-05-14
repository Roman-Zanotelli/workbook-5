package com.pluralsight.exception.lib;

import com.pluralsight.exception.LibraryException;

public class ItemNotBorrowedException extends LibraryException {
    public ItemNotBorrowedException(String memberID, String itemID) {
        super(String.format("Item Not Currently Borrowed, MemberID: %s ItemID: %s", memberID, itemID));
    }
}
