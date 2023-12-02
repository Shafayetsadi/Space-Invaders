import javax.swing.*;

public class SpaceInvaders {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Space Invaders");

        ImageIcon logo = new ImageIcon("src/images/logo.jpg");
        window.setIconImage(logo.getImage());

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}