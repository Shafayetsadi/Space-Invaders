import javax.swing.*;
import java.awt.*;

public class Alien extends Entity{
    GamePanel gp;
    Rocket rocket;
    int random;

    public Alien(GamePanel gp, int x, int y){
        this.gp = gp;
        this.x = x;
        this.y = y;
        this.random = (int)(Math.random()*3)+1;
        rocket = new Rocket(gp, x, y, random);
        loadImage();
    }
    public void loadImage(){
//        generate a random number between 1 and 3

        ImageIcon ii = new ImageIcon("src/images/Enemy0"+random+".png");
        setImage(ii.getImage());
    }
    public void update(int direction){
        this.x += direction;
    }
    public Rocket getRocket(){
        return rocket;
    }
    public void draw(Graphics2D graphics2D){
        graphics2D.drawImage(image, x, y, 48, 48, gp);
    }
    public void initRocket(){
        rocket.setX(x+24);
        rocket.setY(y+48);
        rocket.setDestroyed(false);
        rocket.setVisible(true);
    }
}
