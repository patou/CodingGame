import java.util.Scanner;
import java.util.Stack;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class CGFunge {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        in.nextLine();
        char[][] tab = new char[N][];
        for (int i = 0; i < N; i++) {
            tab[i] = in.nextLine().toCharArray();
        }
        int px = 0, py = 0, dx = 1, dy = 0;
        Stack<Character> stack = new Stack<>();
        boolean stringMode = false;
        while (true) {
            char c = tab[py][px];
            //System.err.println(py + " " + px + " = "+ c);
            if (stringMode && c != '"') {
                stack.push(c);    
            }
            else {
                switch(c) {
                    case '>':
                        dx = 1;
                        dy = 0;
                        break;
                    case '<':
                        dx = -1;
                        dy = 0;
                        break;
                    case '^':
                        dx = 0;
                        dy = -1;
                        break;
                    case 'v':
                        dx = 0;
                        dy = 1;
                        break;
                    case 'E':
                        return;
                    case 'S':
                        px += dx;
                        py += dy;
                        break;
                    case '"':
                        stringMode = !stringMode;
                        break;
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                        stack.push(Character.valueOf((char)(c - '0')));
                        break;
                    case '+':
                    case '*':
                    case '-':
                        Character n2 = stack.pop();
                        Character n1 = stack.pop();
                        int r = 0;
                        switch(c) {
                            case '+':
                                r = n1 + n2;
                                break;
                            case '*':
                                r = n1 * n2;
                                break;
                            case '-':
                                r = n1 - n2;
                                break;
                        }
                        stack.push((char)r);
                        break;
                    case 'P':
                        stack.pop();
                        break;
                    case 'X':
                        Character p1 = stack.pop();
                        Character p2 = stack.pop();
                        stack.push(p1);
                        stack.push(p2);
                        break;
                    case 'D':
                        Character ndup = stack.peek();
                        stack.push(ndup);
                        break;
                    case '_':
                        dy = 0;
                        dx = (stack.pop() == 0) ? 1 : -1;
                        break;
                    case '|':
                        dx = 0;
                        dy = (stack.pop() == 0) ? 1 : -1;
                        break;
                    case 'I':
                        System.out.print((int)stack.pop());
                        break;
                    case 'C':
                        System.out.print(stack.pop());
                        break;
                }
            }
            px += dx;
            py += dy;
        }
    }
}