
class Main {
    public static void main(String[] args) {
        Army black = new Army("Black", 40);
        Army white = new Army("White", 1);

        black.fight(black, white);
        }
    }
