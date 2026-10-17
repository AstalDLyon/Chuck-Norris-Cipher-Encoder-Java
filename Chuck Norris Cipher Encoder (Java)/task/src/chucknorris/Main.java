package chucknorris;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Please input operation (encode/decode/exit):");
            String operation = scanner.nextLine().trim();

            if (operation.equals("exit")) {
                System.out.println("Bye!");
                break;
            } else if (operation.equals("encode")) {
                System.out.println("Input string:");
                String input = scanner.nextLine();
                String binary = binaryConvert(input);
                String encodedMessage = encodeChuckNorris(binary);
                System.out.println("Encoded string:");
                System.out.println(encodedMessage);
                System.out.println(); // Linha em branco solicitada pelo exemplo
            } else if (operation.equals("decode")) {
                System.out.println("Input encoded string:");
                String input = scanner.nextLine();

                String binaryDecode = decodeChuckNorris(input);
                if (binaryDecode == null) {
                    System.out.println("Encoded string is not valid.");
                } else {
                    String message = convertToTextString(binaryDecode);
                    System.out.println("Decoded string:");
                    System.out.println(message);
                }
                System.out.println(); // Linha em branco solicitada pelo exemplo
            } else {
                System.out.printf("There is no '%s' operation%n%n", operation);
            }
        }
    }

    public static String binaryConvert(String input) {
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
                result.append("0".repeat(count));
                result.append(" ");

                currentBit = binaryString.charAt(i);
                if (currentBit == '1') {
                    result.append("0 ");
                } else {
                    result.append("00 ");
                }
                count = 1;
            }
        }
        result.append("0".repeat(count));

        return result.toString();
    }

    public static String decodeChuckNorris(String encoded) {
        // Validação 1: Contém caracteres diferentes de '0' ou espaço
        if (!encoded.matches("[0 ]+")) {
            return null;
        }

        String[] blocks = encoded.split(" ");

        // Validação 3: O número de blocos é ímpar
        if (blocks.length % 2 != 0) {
            return null;
        }

        StringBuilder binary = new StringBuilder();

        for (int i = 0; i < blocks.length; i += 2) {
            String typeBlock = blocks[i];
            String countBlock = blocks[i + 1];

            // Validação 2: O primeiro bloco de cada sequência não é "0" ou "00"
            if (!typeBlock.equals("0") && !typeBlock.equals("00")) {
                return null;
            }

            char bit = typeBlock.equals("0") ? '1' : '0';
            int count = countBlock.length();

            for (int j = 0; j < count; j++) {
                binary.append(bit);
            }
        }

        // Validação 4: O comprimento da string binária resultante não é múltiplo de 7
        if (binary.length() % 7 != 0) {
            return null;
        }

        return binary.toString();
    }

    public static String convertToTextString(String binaryString) {
        StringBuilder text = new StringBuilder();

        for (int i = 0; i < binaryString.length(); i += 7) {
            String byteStr = binaryString.substring(i, Math.min(i + 7, binaryString.length()));
            int charCode = Integer.parseInt(byteStr, 2);
            text.append((char) charCode);
        }
        return text.toString();
    }
}
