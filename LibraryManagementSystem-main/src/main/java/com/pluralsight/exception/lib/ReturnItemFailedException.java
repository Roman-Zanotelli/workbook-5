package com.pluralsight.exception.lib;

public class ReturnItemFailedException extends RuntimeException {
    public ReturnItemFailedException(String memberID, String itemID) {
        super(String.format("Return Item Function Failed, MemberID: %s ItemID: %s", memberID, itemID));
    }
}
