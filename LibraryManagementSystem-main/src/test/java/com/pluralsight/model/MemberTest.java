package com.pluralsight.model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class MemberTest {
    private Member member;

    @BeforeEach
    void setUp() {
        member = new Member("M1001", "John Doe", "john@test.com");
    }

    @Test
    void testMemberCreation() {
        assertEquals("M1001", member.getMemberId());
        assertEquals("John Doe", member.getName());
        assertEquals("john@test.com", member.getEmail());
        assertEquals(0, member.getBorrowedCount());
        assertTrue(member.getBorrowedItems().isEmpty());
    }

    @Test
    void testBorrowBook() {
        assertTrue(member.borrowItem("1234567890"));
        assertEquals(1, member.getBorrowedCount());
        assertTrue(member.hasBorrowedItem("1234567890"));

        // Can't borrow same book twice
        assertFalse(member.borrowItem("1234567890"));
        assertEquals(1, member.getBorrowedCount());
    }

    @Test
    void testReturnBook() {
        member.borrowItem("1234567890");
        assertTrue(member.returnItem("1234567890"));
        assertEquals(0, member.getBorrowedCount());
        assertFalse(member.hasBorrowedItem("1234567890"));

        // Can't return book not borrowed
        assertFalse(member.returnItem("1234567890"));
    }

    @Test
    void testBorrowMultipleBooks() {
        member.borrowItem("1234567890");
        member.borrowItem("0987654321");
        assertEquals(2, member.getBorrowedCount());
        assertTrue(member.hasBorrowedItem("1234567890"));
        assertTrue(member.hasBorrowedItem("0987654321"));
    }

    @Test
    void testMemberEquality() {
        Member sameMember = new Member("M1001", "Different Name", "different@email.com");
        assertEquals(member, sameMember);
        assertEquals(member.hashCode(), sameMember.hashCode());

        Member differentMember = new Member("M1002", "John Doe", "john@test.com");
        assertNotEquals(member, differentMember);
    }
}