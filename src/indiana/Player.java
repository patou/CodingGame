import java.util.Scanner;

class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int W = in.nextInt(); // number of columns.
        int H = in.nextInt(); // number of rows.
        in.nextLine();
        Case[][] tab = new Case[H][];
        for (int i = 0; i < H; i++) {
            tab[i] = new Case[W];
            for (int j = 0; j < W; j++) {
                tab[i][j] = Case.build(in.nextInt());
            }
        }
        int EX = in.nextInt(); // the coordinate along the X axis of the exit (not useful for this first mission, but must be read).

        // game loop
        doLoop(in, tab, W, H, EX);
    }

    static void doLoop(Scanner in, Case[][] tab, int w, int h, int ex) {
        while (true) {
            int XI = in.nextInt();
            int YI = in.nextInt();
            Position POS = Position.valueOf(in.next());

            System.out.println(doLoop(tab, XI, YI, POS));
        }
    }

    static String doLoop(Case[][] tab, int xi, int yi, Position POS) {
        Case c = tab[yi][xi];
        System.err.println(c);
        int p = getPosition(c.forme, POS.mask);
        int in = p >> 2;
        Position out = findOutPosition(c, in);
        if (out != null && POS != out) {
            System.err.println(out);
            switch (out) {
                case BOTTOM:
                    yi++;
                    break;
                case LEFT:
                    xi--;
                    break;
                case RIGHT:
                    xi++;
                    break;
            }
        }
        return xi + " " + yi;
    }

    private static Position findOutPosition(Case c, int in) {
        Position out = null;
        for (Position to : Position.values()) {
            int o = getPosition(c.forme, to.mask) & in;
            if (o > 0) {
                out = to;
            }
        }
        return out;
    }

    static int getPosition(int forme, int mask) {
        return (forme >> ((mask - 1) * 4)) & 0xF;
    }


    /**
     * number : Number of the form given by the problem
     * forme : An four digit hexa number, eash digit is 2 bit for the input number and 2 digit for the output number
     */
    enum Case {
        Type0(0, 0x0000),
        Type1(1, 0x4414),
        Type2(2, 0x0609),
        Type3(3, 0x4010),
        Type4(4, 0x4821),
        Type5(5, 0x4128),
        Type6(6, 0x0609),
        Type7(7, 0x4830),
        Type8(8, 0x0432),
        Type9(9, 0x4038),
        Type10(10, 0x4001),
        Type11(11, 0x4100),
        Type12(12, 0x0410),
        Type13(13, 0x0014);
        int number;
        int forme;
        Case(int number, int forme) {
            this.number = number;
            this.forme = forme;
        }
        static Case build(int number) {
            return Case.valueOf("Type"+number);
        }
    }

    enum Position {
        TOP(4), RIGHT(3), BOTTOM(2), LEFT(1);
        int mask;
        Position(int mask) {
            this.mask = mask;
        }
    }
}