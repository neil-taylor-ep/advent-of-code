package adventcode.day3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.stream.Collectors;

public class Day3 {

    public static void main(String[] args) throws IOException {

        Day3 d = new Day3();

        // Part 1
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("input3.txt")))) {

            int total = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                total += d.getPriority(line);
            }
            System.out.println(total);
        }

        // Part 2
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("input3.txt")))) {

            int total = 0;
            boolean eof = false;
            do {
                String line = reader.readLine();
                if (line == null) {
                    eof = true;
                } else {
                    Set<Integer> elf1 = d.toSet(line);
                    Set<Integer> elf2 = d.toSet(reader.readLine());
                    Set<Integer> elf3 = d.toSet(reader.readLine());

                    elf1.retainAll(elf2);
                    elf1.retainAll(elf3);
                    assert elf1.size() == 1;
                    total += d.getIntPriority(elf1.iterator().next());
                }
            } while (!eof);
            System.out.println(total);

        }

    }

    public int getPriority(String s) {
        int i = getDuplicate(s);
        return getIntPriority(i);
    }

    private int getIntPriority(int i) {
        if (i > 96) {
            return i - 96;
        } else {
            return i - 64 + 26;
        }
    }

    private int getDuplicate(final String s) {
        int length = s.length();
        assert length % 2 == 0;
        int halfway = length / 2;
        Set<Integer> left = toSet(s.substring(0, halfway));
        Set<Integer> right = toSet(s.substring(halfway, length));

        left.retainAll(right);
        assert left.size() == 1;

        return left.iterator().next();
    }

    private Set<Integer> toSet(String s) {
        return s.chars().boxed().collect(Collectors.toSet());
    }
}
