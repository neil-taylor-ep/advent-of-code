package adventcode.day13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Day13 {

    static ListComparator comparator = new ListComparator();

    public static void main(String[] args) throws IOException {

        List allPackets = new ArrayList();

        // Part 1
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("input13.txt")))) {

            int count = 0;
            int index = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                index++;

                List packet1 = parse(line);
                allPackets.add(packet1);

                List packet2 = parse(reader.readLine());
                allPackets.add(packet2);

                reader.readLine();

                if (comparator.compare(packet1, packet2) == -1) {
                    count += index;
                }
            }
            System.out.println(count + " sum of indices.");
        }

        // Part 2
        allPackets.add(parse("[[2]]"));
        allPackets.add(parse("[[6]]"));
        allPackets.sort(comparator);

        int decoderKey = 1;
        int index = 0;
        for (Object o : allPackets) {
            index++;
            if (o.toString().equals("[[2]]") || o.toString().equals("[[6]]")) {
                decoderKey *= index;
            }
        }
        System.out.println("Decoder key: " + decoderKey);

    }

    private static List parse(String input) {
        List list = new ArrayList();
        StringBuilder buffer = new StringBuilder();
        for (int pos = 1; pos < input.length(); pos++) {
            char c = input.charAt(pos);
            if (c == '[') {
                //Find the matching closing bracket
                int depth = 0;
                int closingBracketPos = 0;
                for (int i = pos + 1; i < input.length(); i++) {
                    if (input.charAt(i) == '[') {
                        depth++;
                    } else if (input.charAt(i) == ']') {
                        if (depth == 0) {
                            closingBracketPos = i;
                            break;
                        } else {
                            depth--;
                        }
                    }
                }
                String substring = input.substring(pos, closingBracketPos + 1);
                list.add(parse(substring));
                pos = closingBracketPos;
            } else if (c > 47 && c < 58) {
                buffer.append(c);
            } else if (c == ',' || c == ']') {
                if (buffer.length() > 0) {
                    list.add(Integer.parseInt(buffer.toString()));
                    buffer.setLength(0);
                }
            }

        }

        return list;
    }
}