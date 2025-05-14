package com.pluralsight.exception.lib;

import com.pluralsight.exception.LibraryException;

public class MemberNotFoundException extends LibraryException {
    public MemberNotFoundException(String memberID) {
        super(String.format("Member Not Found, MemberID: %s", memberID));
    }
}
