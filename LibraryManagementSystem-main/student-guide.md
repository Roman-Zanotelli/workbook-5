# Student Guide - Library Management System

## 📋 Progress Tracker

### Phase 1: Bug Fixes (Complete First)
- [ ] **Ticket #1** - Book Equality Test Failing
- [ ] **Ticket #2** - Member Can Borrow Same Book Multiple Times
- [ ] **Ticket #3** - Book Borrowing Always Fails
- [ ] **Ticket #4** - Movie Borrow Duration Incorrect
- [ ] **Ticket #5** - Search Function Case Sensitivity Issue
- [ ] **Ticket #6** - Genre Search Returns Too Many Results
- [ ] **Ticket #7** - Compilation Error in UI Layer
- [ ] **Ticket #8** - Magazine Constructor Type Error
- [ ] **Ticket #9** - Missing Import in Test Class

### Phase 2: Movie Features
- [ ] **Ticket #10** - Implement Movie-Specific Methods in Library Service
- [ ] **Ticket #11** - Create Movie Search Methods in Library Service
- [ ] **Ticket #12** - Implement Complete Movie Menu System

### Phase 3: Magazine Features
- [ ] **Ticket #13** - Implement Magazine-Specific Methods in Library Service
- [ ] **Ticket #14** - Create Magazine Search Methods in Library Service
- [ ] **Ticket #15** - Implement Complete Magazine Menu System

### Phase 4: UI Enhancements
- [ ] **Ticket #16** - Add Duration Display for Movie Operations
- [ ] **Ticket #17** - Add Issue Information Display for Magazine Operations

---

## 🎯 Project Overview

The Library Management System is a Java console application that demonstrates Object-Oriented Programming concepts including inheritance, polymorphism, collections, and file I/O. The system manages books, movies, and magazines with member borrowing capabilities.

### Key Features
- **Item Management**: Books, Movies, and Magazines with inheritance hierarchy
- **Member System**: Registration and borrowing tracking
- **Search Functionality**: Multiple search options by type, genre, author/director/publisher
- **File Persistence**: CSV-based data storage
- **Logging System**: Comprehensive operation logging
- **Polymorphic Design**: Unified handling of different item types

### Architecture Overview
```
com.library/
├── model/          # Data classes (Item, Book, Movie, Magazine, Member)
├── service/        # Business logic (Library, Logger)  
├── ui/             # User interface (LibrarySystem)
└── data/           # CSV files (items.csv, members.csv, borrowed.csv)
```

---

## 🚀 Getting Started

### 1. Project Structure
Your repository contains:
- **Source code** in `src/main/java/com/pluralsight/`
- **Test code** in `src/test/java/com/pluralsight/`
- **Sample data** in `data/` directory
- **Ticket documentation** in `bug-tickets.md` and `feature-tickets.md`

### 2. Understanding the Current State
The application currently has:
- ✅ **Working Book functionality** (use as reference)
- ❌ **9 bugs** preventing proper operation
- ❌ **Missing Movie and Magazine features**

---

## 🔧 IntelliJ IDEA Setup

### Opening the Project
1. **Open IntelliJ IDEA**
2. **File → Open** and select the project folder
3. IntelliJ will automatically detect it as a Maven project
4. Let IntelliJ import dependencies and index the project

### Project Structure
- IntelliJ will show the project in a tree structure
- Source code is in `src/main/java`
- Test code is in `src/test/java`
- Data files are in the `data` folder at project root

---

## ▶️ Running the Application

### Setting Up Run Configuration
1. **Right-click** on `LibrarySystem.java` in `src/main/java/com/pluralsight/ui/`
2. Select **"Run 'LibrarySystem.main()'"** or **"Debug 'LibrarySystem.main()'"**
3. IntelliJ will create a run configuration automatically

### Running the Application
- **Green Play Button** in the toolbar
- **Run → Run 'LibrarySystem'** from menu
- **Shift + F10** keyboard shortcut
- Right-click in the code editor and select **"Run 'LibrarySystem.main()'"**

### Console Output
- The application will run in IntelliJ's built-in console
- You can interact with the menu system directly in the console
- All output will appear in the "Run" tab at the bottom of the screen

---

## 🧪 Running Tests

### Running All Tests
1. Right-click on the `test` folder in the project tree
2. Select **"Run 'All Tests'"**
3. IntelliJ will execute all test classes

### Running Specific Tests
- **Single Test Class**: Right-click on a test file (e.g., `BookTest.java`) → Run
- **Single Test Method**: Right-click on a specific test method → Run
- **Test Package**: Right-click on a package in test folder → Run

### Test Results Window
- IntelliJ shows test results in a dedicated tab
- **Green**: Tests passed
- **Red**: Tests failed
- Click on failed tests to see error details
- Use the tree view to navigate specific test failures

### Debugging Tests
- Right-click on test → **"Debug 'TestName'"**
- Set breakpoints by clicking the line gutter
- Step through code to understand test failures

---

## 📁 Understanding the Data

### CSV Files Structure

**items.csv** - All library items
```csv
ID,Type,Title,Creator,Genre,Available,Extra1,Extra2
9780061120084,Book,To Kill a Mockingbird,Harper Lee,Fiction,true,,
MVE001,Movie,The Matrix,The Wachowski Sisters,Sci-Fi,true,136,
```

**members.csv** - Library members
```csv
MemberID,Name,Email
M1001,Alice Johnson,alice@email.com
```

**borrowed.csv** - Active loans
```csv
MemberID,ItemID
M1001,9780451524935
```

### Log Files
Application logs are written to `logs/library.log` with timestamped entries for all operations.

---

## 🛠️ Development Workflow

### Recommended Approach
1. **Start with Bug Fixes** (Phase 1)
    - Run tests to see failures
    - Read error messages in IntelliJ's test results
    - Fix one bug at a time
    - Re-run tests to verify fixes

2. **Implement Features** (Phases 2-3)
    - Use working Book implementation as reference
    - Follow the same patterns for Movies and Magazines
    - Run the application to test manually

3. **Polish the UI** (Phase 4)
    - Enhance display formatting
    - Ensure consistent user experience

### IntelliJ Development Tips
- **Auto-import**: Alt + Enter on unresolved references
- **Code completion**: Ctrl + Space for suggestions
- **Refactoring**: Right-click → Refactor for safe renaming/moving
- **Quick fixes**: Red lightbulb icons show suggested fixes
- **Find usages**: Ctrl + Alt + F7 to see where code is used

### Debugging Strategy
- **Breakpoints**: Click line gutter to set breakpoints
- **Debug mode**: Right-click → Debug instead of Run
- **Step controls**: Step over (F8), Step into (F7), Continue (F9)
- **Variable inspection**: Hover over variables or use Debug panel
- **Evaluate expressions**: Alt + F8 in debug mode

---

## 📚 Learning Resources

### Key Concepts Demonstrated
- **Inheritance**: Item → Book/Movie/Magazine
- **Polymorphism**: Items collection holds different types
- **Collections**: HashMap for storage, ArrayList for results
- **File I/O**: CSV reading/writing with BufferedReader/Writer
- **Exception Handling**: Try-catch blocks for file operations
- **Static Methods**: Utility methods in Library class

### Code Patterns to Follow
Look at the Book implementation for examples of:
- Constructor patterns
- toString() formatting
- Search method structure
- Menu organization
- Error handling

### IntelliJ Features to Use
- **Structure View**: See class outline and methods
- **Find in Path**: Ctrl + Shift + F to search across files
- **Go to Declaration**: Ctrl + Click on method/class names
- **TODO View**: Track // TODO comments you add
- **Version Control**: Built-in Git integration

---

## ✅ Completion Checklist

Before submitting your work, ensure:
- [ ] All unit tests pass (green in test runner)
- [ ] Application runs without compilation errors
- [ ] All menu options work correctly
- [ ] Search functions are case-insensitive
- [ ] Borrowing and returning works for all item types
- [ ] CSV files save and load properly
- [ ] Logs are generated correctly

---

## 🆘 Getting Help

If you encounter issues:
1. **Check IntelliJ's error indicators** (red underlines, error markers)
2. **Read test failure details** in the test results panel
3. **Use IntelliJ's quick fixes** (Alt + Enter on errors)
4. **Review the ticket descriptions** for implementation guidance
5. **Look at working Book code** for patterns
6. **Check the console output** when running the application
7. **Examine log files** in the `logs` directory

### IntelliJ Help Features
- **Error tooltips**: Hover over red underlined code
- **Intention actions**: Alt + Enter for suggested fixes
- **Code inspection**: Analyze → Inspect Code for potential issues
- **Find in Files**: Search for similar implementations

Remember: The goal is to learn through debugging and implementation. Use IntelliJ's powerful tools to understand the code structure and identify issues efficiently!