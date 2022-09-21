import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    private static final AtomicInteger countSize3 = new AtomicInteger();
    private static final AtomicInteger countSize4 = new AtomicInteger();
    private static final AtomicInteger countSize5 = new AtomicInteger();


    public static void main(String[] args) throws InterruptedException {

        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }

        Thread thread3 = new Thread(() -> {
            for (String s : texts) {
                if (s.length() == 3) {
                    if (s.equals(new StringBuilder(s).reverse().toString())) {
                        countSize3.incrementAndGet();
                    } else if (s.chars().allMatch(c -> c == s.charAt(0))) {
                        countSize3.incrementAndGet();
                    } else {
                        char[] d = s.toCharArray();
                        Arrays.sort(d);
                        countSize3.incrementAndGet();
                    }
                }

            }
        });
        Thread thread4 = new Thread(() -> {
            for (String s : texts) {
                if (s.length() == 4) {
                    if (s.equals(new StringBuilder(s).reverse().toString())) {
                        countSize4.incrementAndGet();
                    } else if (s.chars().allMatch(c -> c == s.charAt(0))) {
                        countSize4.incrementAndGet();
                    } else {
                        char[] d = s.toCharArray();
                        Arrays.sort(d);
                        countSize4.incrementAndGet();
                    }
                }
            }
        });

        Thread thread5 = new Thread(() -> {
            for (String s : texts) {
                if (s.length() == 5) {
                    if (s.equals(new StringBuilder(s).reverse().toString())) {
                        countSize5.incrementAndGet();
                    } else if (s.chars().allMatch(c -> c == s.charAt(0))) {
                        countSize5.incrementAndGet();
                    } else {
                        char[] d = s.toCharArray();
                        Arrays.sort(d);
                        countSize5.incrementAndGet();

                    }
                }
            }
        });
        thread3.start();
        thread4.start();
        thread5.start();
        thread3.join();
        thread4.join();
        thread5.join();

        System.out.println("Красивых слов с длиной - 3: " + countSize3 + " шт.");
        System.out.println("Красивых слов с длиной - 4: " + countSize4 + " шт.");
        System.out.println("Красивых слов с длиной - 5: " + countSize5 + " шт.");
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
}
