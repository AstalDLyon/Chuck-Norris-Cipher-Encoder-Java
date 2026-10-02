package chucknorris;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input string:");
        String input = scanner.nextLine();
        System.out.println("The result:");
        for (char c : input.toCharArray()){
            int temp = c;
            String binary = Integer.toBinaryString(temp);
            System.out.printf("%c = %s\n", c, binary);
        }
    }
}