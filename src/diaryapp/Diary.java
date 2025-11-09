package diaryapp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Diary {
    private final String filepath;
    private final Scanner input = new Scanner(System.in);

    public Diary(String filepath) {
        this.filepath = filepath;
    }

    public void checkFileExistence() {
        FileHandler.checkFileExistence(filepath);
    }

    public void addNote() {
        System.out.print("Enter your note. (Press x to cancel): ");
        String note = input.nextLine();
        if (note.equalsIgnoreCase("x")) return;

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        FileHandler.appendLine(filepath, timestamp + " | " + note);
        System.out.println("Note added!");
        Main.pause();
    } // end addNote

    public void viewNoteEntries() {
        List<String> lines = FileHandler.readAllLines(filepath);
        if (lines.isEmpty()) {
            System.out.println("No notes yet. :(");
        } else {
            for (int i = 0; i < lines.size(); i++) {
                System.out.println((i + 1) + ". " + lines.get(i));
            }
        }
        Main.pause();
    } // end viewNoteEntries

    public void modifyNote() {
        List<String> lines = FileHandler.readAllLines(filepath);
        if (lines.isEmpty()) {
            System.out.println("No note to modify.");
            Main.pause();
            return;
        }
        viewNoteEntries();
        System.out.print("Enter note to modify (Press x to cancel): ");
        String choice = input.nextLine();
        if (choice.equalsIgnoreCase("x")) return;

        try {
            int index = Integer.parseInt(choice);
            if (index  > 0 && index <= lines.size()) {
                System.out.print ("Enter new note: ");
                String newText = input.nextLine();
                if (newText.equalsIgnoreCase("x")) return;

                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                lines.set(index - 1, timestamp + " | " + newText);
                FileHandler.writeAllLines(filepath, lines);
                System.out.print("Note updated.");
            } 
            else {
                System.out.println("Invalid note number.");
            }
        } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
        }
        Main.pause();
    } //  end modifyNote

    public void deleteNote() {
        List<String> lines = FileHandler.readAllLines(filepath);
        if (lines.isEmpty()) {
            System.out.println("No notes to delete.");
            Main.pause();
            return;
        }

       viewNoteEntries();
       System.out.print("Enter note number to delete (Press x to cancel): ");
       String choice = input.nextLine();
       if (choice.equalsIgnoreCase("x")) return;
       
       try {
        int index = Integer.parseInt(choice);
        if (index > 0 && index <= lines.size()) {
            lines.remove(index - 1);
            FileHandler.writeAllLines(filepath, lines);
            System.out.println("Note has been deleted.");
        } else {
            System.out.println("Invalid note number.");
        }
        }catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }
        Main.pause();
    } // end deleteNote

    public void insertNote() {
        List <String> lines = FileHandler.readAllLines(filepath);
        viewNoteEntries();
        System.out.print("Enter index to insert note (Press x to cancel): ");
        String choice = input.nextLine();
        if (choice.equalsIgnoreCase("x")) return;

        try {
            int index = Integer.parseInt(choice);
            if (index >= 0 && index <= lines.size()) {
                System.out.print("Your new entry: ");
                String newEntry = input.nextLine();
                if (newEntry.equalsIgnoreCase("x")) return;

               String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                lines.add(index, timestamp + " | " + newEntry);
                FileHandler.writeAllLines(filepath, lines);
                System.out.println("Entry inserted.");
            } else {
                System.out.println("Invalid index.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }
        Main.pause();
    } // end insertNote

    public void exitNotes() {
        System.out.println("This has been your diary! ^^");
        Main.pause();
        System.exit(0);
    } // end exitNotes
}