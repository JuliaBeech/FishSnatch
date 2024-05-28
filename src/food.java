import javax.swing.ImageIcon;

public class food {
    private int x;
    private int y;
    private int width;
    private int height;
    private ImageIcon pic;

    public food(){
        x=0;
        y=0;
        pic = new ImageIcon();
        width=100;
        height=100;
    }

    public food(int xV, int yV, ImageIcon p, int w, int h){
        x=xV;
        y=yV;
        pic=p;
        width=w;
        height=h;
    }
    
    public food(int xV, int yV) {
    	x=xV;
    	y=yV;
    	pic = new ImageIcon("fishflakes.png");
    	width=50;
    	height=50;
    }
    

    //add getters and setters

    public int getX(){
       return x;
    }
   public int getY(){
       return y;
     }
     public ImageIcon getPic(){
       return pic;
     }
     public int getW(){
       return width;
    }
   public int getH(){
       return height;
    }

     public void setX(int xV){
     x=xV;
     }
    public void setY(int yV){
      y=yV;
     }

    

   
    public void setW(int value) {
      this.width = value;
    }

 
    public void setHeight(int value) {
      this.height = value;
    }

  
    public void setPic(ImageIcon value) {
      this.pic = value;
    }

    public boolean Collision(Pictures b) {
      return getX()+getW()>=b.getX()&&getX()+getW()<=b.getX()+b.getW()&&
          getY()+getH()>=b.getY() &&getY()<=b.getY()+b.getH();
    }
}


