public class Application {
    public static void main(String[] args) {
        Game game = new Game();
        try {
            game.run();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
