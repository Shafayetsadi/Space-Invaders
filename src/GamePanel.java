import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable{
    final int maxScreenCol = 65;
    final int maxScreenRow = 45;
    final int tileSize = 16;

    final int screenWidth = tileSize * maxScreenCol; // 768
    final int screenHeight = tileSize * maxScreenRow; // 576

    int FPS = 60;
    int frames = 0;
    int direction = -1;
    int deaths = 0;
    int numberOfAliens = 45;
    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    Player player;
    List<Alien> aliens;
    List<Shoot> shoots;

    boolean inGame = false;
    String explosionImg = "src/images/exp01.png";
    String message = "Press enter to start the game.";

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.DARK_GRAY);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

        init();
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    public void init(){
        aliens = new ArrayList<>();
        for(int i=1; i<4; i++){
            for(int j=1; j<16; j++){
                var alien = new Alien(this, 48*j+12*j, 48*i+12*i);
                aliens.add(alien);
            }
        }
        player = new Player(this, keyHandler);
        shoots = new ArrayList<>();
        shoots.add(new Shoot(this, player));
        deaths = 0;
    }


    @Override
    public void run(){
        double drawInterval = (double) 1000000000 /FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1){
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update(){
        if(!inGame && keyHandler.enterPressed){
            inGame = true;
            init();
        }
        if(inGame){
            if(deaths == numberOfAliens){
                inGame = false;
                message = "Game won! Press enter to start new game.";
                deaths = 0;
                return;
            }
            player.update();

            if(frames == 30){
                shoots.add(new Shoot(this, player));
                frames = 0;
            }
            else{
                frames++;
            }
            for(Shoot shoot: shoots){
                if(shoot.isVisible()){
                    int shootX = shoot.getX();
                    int shootY = shoot.getY();

                    for(Alien alien : aliens){
                        int alienX = alien.getX();
                        int alienY = alien.getY();

                        if(alien.isVisible() && shoot.isVisible()){
                            if (shootX >= (alienX) && shootX <= (alienX + 48)
                                    && shootY >= (alienY) && shootY <= (alienY + 48)) {
                                var ii = new ImageIcon(explosionImg);
                                alien.setImage(ii.getImage());
                                alien.setDying(true);
                                deaths++;
                                shoot.setVisible(false);
                            }
                        }
                    }
                    shoot.update();
                    if(shoot.getY() <= 0) shoot.setVisible(false);
                }
            }



            for (Alien alien : aliens) {
                int x = alien.getX();
                if (x >= screenWidth - 48 - 5 && direction != -1) {
                    direction = -1;
                    for (Alien a : aliens) {
                        a.setY(a.getY() + 5);
                    }
                }
                if (x <= 5 && direction != 1) {
                    direction = 1;
                    for (Alien a : aliens) {
                        a.setY(a.getY() + 5);
                    }
                }
            }

            for (Alien alien : aliens) {
                if (alien.isVisible()) {
                    int y = alien.getY();
                    if (y > screenHeight - 48) {
                        inGame = false;
                        message = "Invasion! Press enter to start new game.";
                        deaths = 0;
                    }
                    alien.update(direction);
                }
            }

            
            var generator = new Random();

            for (Alien alien : aliens) {
                int shot = generator.nextInt(8);
                Rocket rocket = alien.getRocket();

                if (shot == 5 && alien.isVisible() && rocket.isDestroyed()) {

                    rocket.setDestroyed(false);
                    rocket.setX(alien.getX());
                    rocket.setY(alien.getY());
                }

                int bombX = rocket.getX();
                int bombY = rocket.getY();
                int playerX = player.getX();
                int playerY = player.getY();

                if (player.isVisible() && !rocket.isDestroyed()) {

                    if (bombX >= (playerX)
                            && bombX <= (playerX + 48)
                            && bombY >= (playerY)
                            && bombY <= (playerY + 48)) {

                        var ii = new ImageIcon(explosionImg);
                        player.setImage(ii.getImage());
                        player.setDying(true);
                        rocket.setDestroyed(true);
                    }
                }

                if (!rocket.isDestroyed()) {

                    rocket.setY(rocket.getY() + 1);

                    if (rocket.getY() >= screenHeight - 16) {

                        rocket.setDestroyed(true);
                    }
                }
            }
        }


    }
    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D)graphics;

        if(inGame) {
            if (player.isVisible()) {
                player.draw(graphics2D, this);
            }
            if (player.isDying()) {
                player.setVisible(false);
                inGame = false;
                message = "You are dead! Press enter to start new game";
            }
            for (Shoot shoot: shoots){
                if (shoot.isVisible()) {
                    shoot.draw(graphics2D, this);
                }
            }

            for(Alien alien : aliens){
                if(alien.isVisible()){
                    alien.draw(graphics2D);
                }
                if (alien.isDying()) {
                    alien.setVisible(false);
                }
            }

            for (Alien alien : aliens) {
                Rocket rocket = alien.getRocket();
                if (!rocket.isDestroyed()) {
                    rocket.draw(graphics2D);
                }
            }
        }
        else{
            gameMenu(graphics2D);
        }
        graphics2D.dispose();
    }
    public void gameMenu(Graphics2D graphics2D){
        graphics2D.setColor(Color.black);
        graphics2D.fillRect(0, 0, this.screenWidth, this.screenHeight);

        graphics2D.setColor(new Color(0, 32, 48));
        graphics2D.fillRect(50, this.screenWidth / 2 - 30, this.screenWidth - 100, 50);
        graphics2D.setColor(Color.white);
        graphics2D.drawRect(50, this.screenWidth / 2 - 30, this.screenWidth - 100, 50);

        var small = new Font("Helvetica", Font.BOLD, 14);
        var fontMetrics = this.getFontMetrics(small);

        graphics2D.setColor(Color.white);
        graphics2D.setFont(small);
        graphics2D.drawString(message, (this.screenWidth - fontMetrics.stringWidth(message)) / 2,
                this.screenWidth/ 2);
    }
}
