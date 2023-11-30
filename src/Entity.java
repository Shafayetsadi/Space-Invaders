import java.awt.*;

public class Entity {
    int x, y;
    int speed;
    Image image;
    boolean visible;
    boolean dying;

    public Entity(){
        visible = true;
    }
    public void setVisible(boolean visible){
        this.visible = visible;
    }
    public boolean isVisible(){
        return visible;
    }
    public void setImage(Image image){
        this.image = image;
    }
    public Image getImage(){
        return image;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setDying(boolean dying){
        this.dying = dying;
    }
    public boolean isDying(){
        return dying;
    }
}
