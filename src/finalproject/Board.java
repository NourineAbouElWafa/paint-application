/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

import Classes.Circle;
import Classes.Factory;
import Classes.Line;
import Classes.Observer;
import Classes.Rectangle;
import Classes.Shape;
import Classes.square;
import Classes.triangle;
import Classes.triangle2;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author nourb
 */
public class Board extends JPanel implements MouseListener, MouseMotionListener, Observer {

   Color currentColor = Color.GRAY;
    int x1, y1;
    Point p = MouseInfo.getPointerInfo().getLocation();
    int button = -1;
    boolean draw = false;
    boolean first = false;
    boolean filled = false;
    int x2, y2;
    private Shape selected = null;

    public Shape getSelected() {
        return selected;
    }

    public void setSelected(Shape selected) {
        this.selected = selected;
    }
    
    ArrayList<Shape> x = new ArrayList<Shape>();
    ArrayList<Shape> s = new ArrayList<Shape>();
    static Stack<ArrayList<Shape>> stack1 = new Stack<ArrayList<Shape>>();
    static Stack<ArrayList<Shape>> stackundone = new Stack<ArrayList<Shape>>();

    public Board() {
        addMouseListener(this);
        addMouseMotionListener(this);
        stack1.push(new ArrayList<Shape>());
        GUI.attach(this);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Iterator<Shape> ST = x.iterator();
        while (ST.hasNext()) {
            Shape n = ST.next();
            n.draw(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {

    }

    @Override
    public void mousePressed(MouseEvent me) {
        x1 = me.getX();
        y1 = me.getY();
        System.out.println("filled is " + filled);
      
        if (button < 6) {
            Shape s = Factory.create(button, x1, y1, x1, y1, currentColor, draw, filled);
            x.add(s);
            if (selected != null) {
                selected.setSelected(false);
            }
            repaint();
        } else if (button == 6) {

            for (Shape s : x) {
                s.setSelected(false);
            }
            Iterator<Shape> ST = x.iterator();
            while (ST.hasNext()) {
                Shape n = ST.next();
                if (n.selectShape(x1, y1)) {

                    selected = n;
                    selected.setSelected(true);
                    s.add(selected);
                    System.out.println("selected");
                    break;
                }
            }

            repaint();

        } else if (button == 7) {
            if (selected != null) {
                x.remove(selected);
                repaint();
            }
        } else if (button == 8) {
//            selected.setSelected(true);
            if (selected != null) {
                try {
                    Shape copy = (Shape) selected.clone();
                    copy.setX1(copy.getX1() + 30);
                    copy.setY1(copy.getY1() + 30);
                    copy.setX2(copy.getX2() + 30);
                    copy.setY2(copy.getY2() + 30);
                    copy.setSelected(false);
                    x.add(copy);
                   // selected.setSelected(false);
                    repaint();
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        } else if (button == 12) {
//            selected.setSelected(true);
            if (selected != null) {
                selected.setColor(currentColor);
                selected.setFilled(true);
                repaint();
            }

        }

    }

    @Override
    public void mouseReleased(MouseEvent me) {
        if (button > -1) {
            System.out.println("released ");
            ArrayList<Shape> y = new ArrayList<>();
            // y.addAll(x);
            for (int i = 0; i < x.size(); i++) {
                try {
                    y.add((Shape) x.get(i).clone());
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            stack1.push(y);
            repaint();

            if (button > 5) {
                GUI.getInstance().changeCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

        }

    }

    @Override
    public void mouseEntered(MouseEvent me) {

    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        if (button == 0) {
            x2 = me.getX();
            y2 = me.getY();
            Shape s = x.get(x.size() - 1);
            if (s instanceof Line) {
                Line l = (Line) s;
                l.setX2(x2);
                l.setY2(y2);
            }

            repaint();
            first = false;
        } else if (button == 1) {
            x2 = me.getX();
            y2 = me.getY();

            Shape s = x.get(x.size() - 1);
            if (s instanceof Rectangle) {
                Rectangle r = (Rectangle) s;
                r.setX2(x2);
                r.setY2(y2);
            }

            repaint();
            first = false;
        } else if (button == 2) {
            x2 = me.getX();
            y2 = me.getY();

            Shape s = x.get(x.size() - 1);
            if (s instanceof triangle) {
                triangle r = (triangle) s;
                r.setX2(x2);
                r.setY2(y2);

            }

            repaint();
            first = false;

        } else if (button == 3) {
            x2 = me.getX();
            y2 = me.getY();

            Shape s = x.get(x.size() - 1);
            if (s instanceof square) {
                square r = (square) s;

                int newx = r.getX1(), newy = r.getY1();
                int newx2 = x2, newy2 = y2;
                if (x2 < x1 && y2 < y1) {
                    newx = x2;
                    newy = y2;
                    newx2 = x1;
                    newy2 = y1;
                } else if (x1 < x2 && y2 < y1) {
                    newx = x1;
                    newy = y2;
                    newx2 = x2;
                    newy2 = y1;
                } else if (x1 > x2 && y2 > y1) {
                    newx = x2;
                    newy = y1;
                    newx2 = x1;
                    newy2 = y2;
                }
                r.setX1(newx);
                r.setY1(newy);

                r.setX2(newx2);
                r.setY2(newy2);

            }

            repaint();
            first = false;
        } else if (button == 4) {
            x2 = me.getX();
            y2 = me.getY();

            Shape s = x.get(x.size() - 1);
            if (s instanceof Circle) {
                Circle r = (Circle) s;
                r.setX2(x2);
                r.setY2(y2);

            }

            repaint();
            first = false;
        } else if (button == 5) {
            x2 = me.getX();
            y2 = me.getY();

            Shape s = x.get(x.size() - 1);
            if (s instanceof triangle2) {
                triangle2 r = (triangle2) s;
                r.setX2(x2);
                r.setY2(y2);

            }

            repaint();
            first = false;
        } else if (button == 9) {
            GUI.getInstance().changeCursor(new Cursor(Cursor.MOVE_CURSOR));

            x2 = me.getX();
            y2 = me.getY();

            if (selected != null) {

                Shape r = (Shape) selected;
               
                r.setX1(r.getX1() + x2 - x1);
                r.setY1(r.getY1() + y2 - y1);
                r.setX2(r.getX2() + x2 - x1);
                r.setY2(r.getY2() + y2 - y1);
                x1 = x2;
                y1 = y2;
                repaint();
                selected.setSelected(false);
                
                
            }

        } else if (button == 10) {
//            selected.setSelected(true);
            x2 = me.getX();
            y2 = me.getY();
            if (selected != null) {
                GUI.getInstance().changeCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));
                Shape r = (Shape) selected;
                r.setX1(r.getX1() + x2 - x1);
                r.setY1(r.getY1() + y2 - y1);
                x1 = x2;
                y1 = y2;
                repaint();
                selected.setSelected(false);

            }

        }
        if (!stackundone.empty()) {
            stackundone.clear();
        }

    }
    

    @Override
    public void mouseMoved(MouseEvent me) {

        
    }

    public void undo() {
        System.out.println("stack size before " + Board.stack1.size());
        System.out.println("stack size2 before " + Board.stackundone.size());
        if (stack1.size() > 1) {
            stackundone.push(stack1.pop());

            x = new ArrayList<Shape>();
            x.addAll(stack1.peek());
            repaint();

            System.out.println("stack size " + Board.stack1.size());
            System.out.println("stack size2  " + Board.stackundone.size());
          
        }
    }

    public void redo() {
        System.out.println("redo stack size2 before " + Board.stackundone.size());
        if (!stackundone.empty()) {
            stack1.push(stackundone.pop());
            x = new ArrayList<Shape>();
            x.addAll(stack1.peek());
            repaint();
            System.out.println("redo stack size2  " + Board.stackundone.size());
                        

        }

    }

    @Override
    public void update() {
        stackundone.clear();

    }

}
