import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Solution {

    public static final String CHAR_OFF = " ";

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        long N = in.nextLong();
        in.nextLine();
        String C = in.nextLine();
        int S = in.nextInt();

        printSegments(N, C, S);
    }
    /**
     *
     * Méthode principale
     * @param N La nombre à afficher
     * @param C Le charactère du segment
     * @param S La longueur d'un segment
     */
    private static void printSegments(long N, String C, int S) {
        List<Numbers> list = getNumbers(N);
        int maxline = 2 * S + 3; // 2 segments vertical + 3 lignes de segments horizontales
        for (int line = 0; line < maxline; line++) {
            StringBuilder str = new StringBuilder();
            for (int i = list.size() - 1; i >= 0; i--) {
                Numbers number = list.get(i);

                str.append(buildSegments(number, line, C, S));
                if (i > 0) {
                    str.append(' ');
                }
            }
            System.out.println(str.toString().replaceAll("\\s+$", ""));
        }
    }

    /**
     * Retourne les chiffres d'un nombres dans une liste inversé.
     * @param N
     * @return
     */
    static List<Numbers> getNumbers(long N) {
        List<Numbers> list = new ArrayList<>(10);
        long q, r;
        for (int i = 0;; i++) {
            q = N / 10;
            r = N - (q * 10);
            list.add(Numbers.valueOf(r));
            N = q;
            if (N == 0) break;
        }
        return list;
    }

    /**
     *  Construit une ligne d'un nombre
     * @param number Le nombre à afficher
     * @param line La ligne à afficher
     * @param C Le charactère du segment
     * @param S La longueur d'un segment
     * @return
     */
    static String buildSegments(Numbers number, int line, String C, int S) {
        if (number != null) {
            if (line == 0)
                return  pad(CHAR_OFF, number.segmentsOn(1, C, CHAR_OFF), S, CHAR_OFF);
            else if (line < S + 1)
                return  pad(number.segmentsOn(6, C, CHAR_OFF), CHAR_OFF, S, number.segmentsOn(2, C, CHAR_OFF));
            else if (line == S + 1)
                return  pad(CHAR_OFF, number.segmentsOn(7, C, CHAR_OFF), S, CHAR_OFF);
            else if (line < 2 * S + 2)
                    return  pad(number.segmentsOn(5, C, CHAR_OFF), CHAR_OFF, S, number.segmentsOn(3, C, CHAR_OFF));
            else
                    return  pad(CHAR_OFF, number.segmentsOn(4, C, CHAR_OFF), S, CHAR_OFF);
        }
        return null;
    }

    /**
     * Retourne une chaine avec begin + (S * C) + end
     * @param begin Le charactère de début
     * @param C Le charactère du segment
     * @param S La longueur d'un segment
     * @param end Le charactère de fin
     * @return
     */
    static String pad(String begin, String C, int S, String end) {
        return begin
                +
                String.format("%" + (S) + "s", "")
                        .replace(CHAR_OFF, C)
                + end;
    }

    /**
     * Represente un nombre
     * Segment : Nombre binaire représentant les segments : 0b1234567
     *  -1-
     * |   |
     * 6   2
     * |   |
     *  -7-
     * |   |
     * 5   3
     * |   |
     *  -4-
     */
    public enum Numbers {
        ZERO(0, 0b1111110),
        ONE(1,  0b0110000),
        TWO(2,  0b1101101),
        TREE(3, 0b1111001),
        FOUR(4, 0b0110011),
        FIVE(5, 0b1011011),
        SIX(6,  0b1011111),
        SEVEN(7,0b1110000),
        EIGHT(8,0b1111111),
        NINE(9, 0b1111011);

        long value;
        int segments;
        Numbers(int value, int segments) {
            this.value = value;
            this.segments = segments;
        }

        static Numbers valueOf(long value) {
            for (Numbers number : Numbers.values()) {
                if (number.value == value) {
                    return number;
                }
            }
            return null;
        }

        /**
         * Retourne le charactère on, si le segment est actif pour ce nombre sinon charactère off
         * @param segmentsNumber Numeros du segment
         * @param charOn Le charactère du segment
         * @param charOff Le charactère sans segment
         * @return
         */
        String segmentsOn(int segmentsNumber, String charOn, String charOff) {
            return (this.segments & 0b1 << 7-segmentsNumber) != 0 ? charOn : charOff;
        }
    }
}