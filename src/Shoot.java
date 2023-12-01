import javax.swing.*;
import java.awt.*;

public class Shoot extends Entity{
    GamePanel gp;
    public Shoot(GamePanel gp){
        this.gp = gp;
        this.x = gp.screenWidth/2 - 24;
        this.y = gp.screenHeight - 80;
        speed = 4;
        loadImage();
        this.visible = true;
    }
    public Shoot(GamePanel gp, int x, int y){
        this.gp = gp;
        this.x = x;
        this.y = y;
        speed = 4;
        loadImage();
        this.visible = true;
    }
    void resetPosition(){
        this.x = gp.screenWidth/2 - 24;
        this.y = gp.screenHeight - 80;
    }
    public void update(){
        y -= 4;
//        if(y <= 0) setVisible(false);
    }
    public void loadImage(){
        ImageIcon ii = new ImageIcon("src/images/bullet02.png");
        setImage(ii.getImage());
    }
    public void draw(Graphics2D graphics2D, GamePanel gp){
        graphics2D.drawImage(image, x, y, 8, 16, gp);
    }
}