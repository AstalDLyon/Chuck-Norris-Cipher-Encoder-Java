package chucknorris;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = input(scanner);
        String binary = binaryConvert(input);
        String encodedMessage = encodeChuckNorris(binary);
        System.out.println("The result:");
        System.out.println(encodedMessage);

    }
    public static String input(Scanner scanner){
        System.out.println("Input string:");
        return scanner.nextLine();
    }
    public static String binaryConvert(String input){
        StringBuilder binaryPattern = new StringBuilder();
        for (char c : input.toCharArray()) {
            String binary = Integer.toBinaryString(c);
            String formattedBinary = String.format("%7s", binary).replace(' ', '0');
            binaryPattern.append(formattedBinary);
        }
        return binaryPattern.toString();
    }
    public static String encodeChuckNorris(String binaryString) {
        if (binaryString.isEmpty()) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        char currentBit = binaryString.charAt(0);

        // Define o primeiro bloco baseado no primeiro bit
        if (currentBit == '1') {
            result.append("0 ");
        } else {
            result.append("00 ");
        }

        int count = 0;

        for (int i = 0; i < binaryString.length(); i++) {
            if (binaryString.charAt(i) == currentBit) {
                count++;
            } else {
                // Adiciona a quantidade de zeros correspondente ao tamanho da sequência anterior
                result.append("0".repeat(count));
                result.append(" "); // Espaço separador de blocos

                // Atualiza para o novo bit detectado
                currentBit = binaryString.charAt(i);
                if (currentBit == '1') {
                    result.append("0 ");
                } else {
                    result.append("00 ");
                }
                count = 1; // Reseta o contador para o novo caractere
            }
        }

        // Adiciona o último bloco de contagem de zeros
        result.append("0".repeat(count));

        return result.toString();
    }
}