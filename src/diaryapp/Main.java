package diaryapp;

import java.util.Scanner;

public class Main {
    private static final Scanner input = new Scanner(System.in);
    private static final Diary diary = new Diary("data/notes.txt");

    public static void main(String[]args) {
        diary.checkFileExistence();

        while (true) {
            clearScreen();
            System.out.println("---- YOUR NOTES ----");
            System.out.println("[1] Add a note");
            System.out.println("[2] View all notes");
            System.out.println("[3] Edit a note");
            System.out.println("[4] Delete a note");
            System.out.println("[5] Insert a note");
            System.out.println("[6] Exit");
            System.out.print("Your choice: ");

            String choice = input.nextLine();

            switch (choice) {
                case "1" -> diary.addNote();
                case "2" -> diary.viewNoteEntries();
                case "3" -> diary.modifyNote();
                case "4" -> diary.deleteNote();
                case "5" -> diary.insertNote();
                case "6" -> diary.exitNotes();
                default -> {
                    System.out.println("Invalid input!");
                    pause();
                }
            }
        }
    }

        public static void pause() {
            System.out.print("Press Enter to continue.");
            input.nextLine();
        }

        private static void clearScreen() {
            System.out.print("\033[H\033[2J");
            System.out.flush();;
        }
}