import javax.swing.*;
import java.awt.*;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyHandler;

    public Player(GamePanel gp, KeyHandler keyHandler){
        this.gp = gp;
        this.keyHandler = keyHandler;
        setDefaultValues();
        loadImage();
    }

    public void setDefaultValues(){
        x = gp.screenWidth/2 - 24;
        y = gp.screenHeight-48-10;
        speed = 4;
    }

    public void loadImage(){
        ImageIcon ii = new ImageIcon("src/images/player.png");
        setImage(ii.getImage());
    }

    public void update(){
        if (keyHandler.leftPressed) {
            x -= speed;
        } else if (keyHandler.rightPressed) {
            x += speed;
        }
        if(x <= 2) x = 2;
        if(x >= (gp.screenWidth-52)) x = gp.screenWidth-52;
    }
    public void draw(Graphics2D graphics2D, GamePanel gp){
        graphics2D.drawImage(image, x, y, 48, 48, gp);
    }
}
