package adventcode.day11;

import java.io.IOException;
import java.util.Arrays;

public class Day11 {

    public static void main(String[] args) throws IOException {

        // Setup
        //Monkey[] monkeys = getTestMonkeys();
        Monkey[] monkeys = getRealMonkeys();

        long gcm = 1;
        for (Monkey monkey : monkeys) {
            gcm *= monkey.getDivisor();
        }

        // Play
        for (int round = 1; round <= 10000; round++) {
            System.out.println("Round " + round);
            for (int i = 0; i<monkeys.length; i++) {
                Monkey monkey = monkeys[i];
                while (monkey.hasItems()) {
                    InspectResult result = monkey.inspect(gcm);
                    monkeys[result.target].give(result.item);
                }
            }
            for (Monkey monkey : monkeys) {
                System.out.println(monkey);
            }
        }
        Arrays.sort(monkeys, Monkey::compareTo);
        long answer = monkeys[0].getInspectCount() * monkeys[1].getInspectCount();
        System.out.println("Monkey business level " +  answer);

    }

    private static Monkey[] getTestMonkeys() {
        Monkey[] monkeys = new Monkey[4];

        monkeys[0] = new Monkey(new int[]{79, 98}, 23, 2, 3) {
            @Override
            long operation(long number) {
                return number * 19;
            }
        };

        monkeys[1] = new Monkey(new int[]{54, 65, 75, 74}, 19, 2, 0) {
            @Override
            long operation(long number) {
                return number + 6;
            }
        };

        monkeys[2] = new Monkey(new int[]{79, 60, 97}, 13, 1, 3) {
            @Override
            long operation(long number) {
                return number * number;
            }
        };

        monkeys[3] = new Monkey(new int[]{74}, 17, 0, 1) {
            @Override
            long operation(long number) {
                return number + 3;
            }
        };
        return monkeys;
    }

    private static Monkey[] getRealMonkeys() {
        Monkey[] monkeys = new Monkey[8];

        monkeys[0] = new Monkey(new int[]{66, 71, 94}, 3, 7, 4) {
            @Override
            long operation(long number) {
                return number * 5;
            }
        };

        monkeys[1] = new Monkey(new int[]{70}, 17, 3, 0) {
            @Override
            long operation(long number) {
                return number + 6;
            }
        };

        monkeys[2] = new Monkey(new int[]{62, 68, 56, 65, 94, 78}, 2, 3, 1) {
            @Override
            long operation(long number) {
                return number + 5;
            }
        };

        monkeys[3] = new Monkey(new int[]{89, 94, 94, 67}, 19, 7, 0) {
            @Override
            long operation(long number) {
                return number + 2;
            }
        };

        monkeys[4] = new Monkey(new int[]{71, 61, 73, 65, 98, 98, 63}, 11, 5, 6) {
            @Override
            long operation(long number) {
                return number * 7;
            }
        };

        monkeys[5] = new Monkey(new int[]{55, 62, 68, 61, 60}, 5, 2, 1) {
            @Override
            long operation(long number) {
                return number + 7;
            }
        };

        monkeys[6] = new Monkey(new int[]{93, 91, 69, 64, 72, 89, 50, 71}, 13, 5, 2) {
            @Override
            long operation(long number) {
                return number + 1;
            }
        };

        monkeys[7] = new Monkey(new int[]{76, 50}, 7, 4, 6) {
            @Override
            long operation(long number) {
                return number * number;
            }
        };
        return monkeys;
    }
}
