/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.awt.Color;

/**
 *
 * @author nourb
 */
public class Factory {
    public static Shape create(int button,int x1, int y1, int x2, int y2, Color color, boolean selected,boolean filled)
    {   if(button==0)
    {return new Line( x1,  y1,  x2,  y2,  color,  selected,filled);}
    else if(button==1)
    {return new Rectangle( x1,  y1,  x2,  y2,  color,  selected,filled);}
      else if(button==2)
      {return new triangle( x1,  y1,  x2,  y2,  color,  selected,filled);}
        else if(button==3)
        {return new square( x1,  y1,  x2,  y2,   color,  selected,filled);}
      else if(button==4)
      {return new Circle( x1,  y1,  x2,  y2,  color,  selected,filled);}
      else if(button==5)
      {return new triangle2( x1,  y1,  x2,  y2,  color,  selected,filled);}
      else {return null;}
        }
    
}
