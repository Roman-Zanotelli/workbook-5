package com.pluralsight.exception.lib;

import com.pluralsight.exception.LibraryException;

public class ItemAlreadyBorrowedException extends LibraryException {
    public ItemAlreadyBorrowedException(String memberID, String itemID) {
        super(String.format("Item Already Borrowed, MemberID: %s ItemID: %s", memberID, itemID));
    }
}
