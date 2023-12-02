import javax.swing.*;
import java.awt.*;

public class Shoot extends Entity{
    GamePanel gp;
    public Shoot(GamePanel gp, Player player){
        this.gp = gp;
        this.x = player.x + 20;
        this.y = player.y;
        speed = 4;
        loadImage();
        this.visible = true;
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
