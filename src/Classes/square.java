/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;


/**
 *
 * @author User
 */
public class square extends Shape {

   
    private int side;
         final static float dash1[] = { 10.0f };
final static BasicStroke dashed = new BasicStroke(1.0f,
      BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);
final static BasicStroke t=new BasicStroke(3);

    public square(int x1, int y1, int x2, int y2, Color color, boolean selected,boolean filled) {
        super(x1, y1, x2, y2, color, selected,filled);
    }
 
    

    public int getSide() {
        return side;
    }

    public void setSide(int side) {
        this.side = side;
    }

    


    

  

    @Override
    public void draw(Graphics g) {
          Graphics2D g2=(Graphics2D)g;
        Stroke defaultStroke = t;
        g2.setStroke(t);
        g.setColor(this.getColor());
        side = Math.abs(getX2() - getX1());
        
        if (getX1() < getX2() && getY1() < getY2()) {
             if(this.isFilled()== false){
            g.drawRect(getX1(), getY1(), side, side);
             }
             else if(this.isFilled()==true)
        {
            g.fillRect(getX1(), getY1(), side, side);
        }
            if(this.isSelected()==true){
                g2.setStroke(dashed);
                g.drawRect(getX1()-3, getY1()-3,side+6 , side+6);
                g2.setStroke(defaultStroke);
            }
        } else if (getX1() > getX2() && getY1() > getY2()) {
            if(this.isFilled()== false){
            g.drawRect(getX2(), getY2(), side, side);
            }
            else if(this.isFilled()==true){
            g.fillRect(getX2(), getY2(), side, side);
            }
              if(this.isSelected()==true){
                  g2.setStroke(dashed);
                g.drawRect(getX2()-3, getY2()-3,side+6 , side+6);
                g2.setStroke(defaultStroke);
            }
        } else if (getX1() < getX2() && getY2() < getY1()) {
            if(this.isFilled()== false){
            g.drawRect(getX1(),getY2(), side, side);
            }
               else if(this.isFilled()==true){
            g.fillRect(getX1(), getY2(), side, side);
            }
              if(this.isSelected()==true){
                   g2.setStroke(dashed);
                g.drawRect(getX1()-3, getY2()-3,side +6, side+6);
                g2.setStroke(defaultStroke);
            }
        } else if (getX2() < getX1() && getY2() > getY1()) {
            if(this.isFilled()== false){
           
            g.drawRect(getX2(), getY1(), side, side);
            }
               else if(this.isFilled()==true){
            g.fillRect(getX2(), getY1(), side, side);
            }
           if(this.isSelected()==true){
                g2.setStroke(dashed);
                g.drawRect(getX2()-3, getY1()-3,side+6 ,side+6);
                g2.setStroke(defaultStroke);
            }
        }
    }

    @Override
    public boolean selectShape(int x, int y) {
        /*java.awt.Rectangle r = new java.awt.Rectangle(this.getX1(),this.getY1(),Math.abs(this.getX1()-this.getX2()),Math.abs(this.getY1()-this.getY2()));
       if (r.contains(x,y))
       {
           return true;
       }
       else
           return false;
    }*/
        java.awt.Rectangle r = null;
         if (getX1() < getX2() && getY1() < getY2()) {
         r = new java.awt.Rectangle(this.getX1(), this.getY1(), Math.abs(this.getX1() - this.getX2()),Math.abs(this.getX1() - this.getX2()));
         }
         else if (getX1() > getX2() && getY1() > getY2()) {
             r = new java.awt.Rectangle(this.getX2(), this.getY2(), Math.abs(this.getX1() - this.getX2()), Math.abs(this.getX1() - this.getX2()));
         }
         else if (getX1() > getX2() &&getY1() < getY2()) {
                r = new java.awt.Rectangle(this.getX2(), this.getY1(), Math.abs(this.getX1() - this.getX2()),Math.abs(this.getX1() - this.getX2()));
         }
         else if (getX2() > getX1() && getY2() < getY1()) {
             r = new java.awt.Rectangle(this.getX1(), this.getY2(), Math.abs(this.getX1() - this.getX2()), Math.abs(this.getX1() - this.getX2()));
         }
         
        if (r.contains(x, y)) {
            
            return true;
        } else {
            return false;
        }

    
}}