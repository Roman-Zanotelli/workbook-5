package com.pluralsight.exception.lib;

import com.pluralsight.exception.LibraryException;

public class BorrowItemFailedException extends LibraryException {
    public BorrowItemFailedException(String memberID, String itemID) {
        super(String.format("Borrow Item Function Failed, MemberID: %s ItemID: %s", memberID, itemID));
    }
}
