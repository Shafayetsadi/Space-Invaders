import javax.swing.*;
import java.awt.*;

public class Rocket extends Entity{
    public boolean destroyed;
    GamePanel gp;
    int random=2;
    public Rocket(GamePanel gp, int x, int y, int random){
        this.gp = gp;
        this.x = x;
        this.y = y;
        speed = 2;

        setDestroyed(true);
        this.random = random;
        loadImage();
    }
    public void loadImage(){
        ImageIcon ii = new ImageIcon("src/images/bullet0"+random+".png");
        setImage(ii.getImage());
    }
    public void setDestroyed(boolean destroyed){
        this.destroyed = destroyed;
    }
    public boolean isDestroyed(){
        return destroyed;
    }

//    public void update(int a, int b){
//        if(y < 0) {
//            x = a+20;
//            y = b;
//        }
//        else y -= speed;
////        y -= speed;
//    }
    public void draw(Graphics2D graphics2D){
        graphics2D.drawImage(image, x, y, 8, 16, gp);
    }
}
