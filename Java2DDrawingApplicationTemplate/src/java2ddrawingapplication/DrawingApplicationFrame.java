/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2ddrawingapplication;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import java.lang.Integer;


/**
 *
 * @author acv
 */
public class DrawingApplicationFrame extends JFrame
{
    private final JPanel panel;
    private final JPanel firstLine;
    private final JPanel secondLine;
    private final JPanel firstHalf;
    
    private final JButton undo;
    private final ActionListener back;
    private final JButton clear;
    private final ActionListener remove;
    private final JButton colorChooser1;
    private final ActionListener chooseColor1;
    private final JButton colorChooser2;
    private final ActionListener chooseColor2;
    
    private final JComboBox<String> selectShape;
    private final String shapesArray[];
    private final JLabel comboBox;
    private final JLabel options;
    private final JCheckBox filled;
    private final JCheckBox gradient;
    private final JCheckBox dashed;
    private final JSpinner width;
    private final JLabel w;
    private final JSpinner length;
    private final JLabel l;
    private JLabel statusBar;
    private Color color1;
    private Color color2;
    private Point startPoint;
    private Point endPoint;
    private Point currentPoint;
    private ArrayList <MyShapes> shapes;
    private DrawPanel panel2 = new DrawPanel();
    
    
    
    
    
    
    
    
    
    // Create the panels for the top of the application. One panel for each
    // line and one to contain both of those panels.

    // create the widgets for the firstLine Panel.

    //create the widgets for the secondLine Panel.

    // Variables for drawPanel.

    // add status label
  
    // Constructor for DrawingApplicationFrame
    public DrawingApplicationFrame()
    {   
        super("Java 2D Drawings");
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,500);
        //setLayout(new GridLayout(1,2));
        shapesArray = new String[]{"Rectangle", "Oval", "Line"};
        selectShape = new JComboBox<String>(shapesArray);
        comboBox = new JLabel("Shape:");
        color1 = Color.BLACK;
        color2 = Color.BLACK;
        
        panel = new JPanel();
       
        panel.setLayout(new BorderLayout());
        
        firstHalf = new JPanel();
        
        firstHalf.setLayout(new BorderLayout());
        firstLine = new JPanel();
        firstLine.setBackground(Color.CYAN);
        firstHalf.add(firstLine, BorderLayout.NORTH);
        
        firstLine.add(comboBox);
        firstLine.add(selectShape);
        
        colorChooser1 = new JButton("1st Color..");
        firstLine.add(colorChooser1);
        chooseColor1 = new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent x){
              
               color1 = JColorChooser.showDialog(null,"Choose a color", Color.LIGHT_GRAY);
               if (color1 == null){
                   color1 = Color.LIGHT_GRAY;
                   
                   
               }
               
            
               
               }
       };
        colorChooser1.addActionListener(chooseColor1);
        colorChooser2 = new JButton("2nd Color..");
        firstLine.add(colorChooser2);
        chooseColor2 = new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent x){
               color2 = JColorChooser.showDialog(null, "Choose a color", color2);
               if (color2 == null){
                   color2 = color1;
                   
               }
            
               
               }
       };
        colorChooser2.addActionListener(chooseColor2);
        undo = new JButton("Undo");
        firstLine.add(undo);
        back = new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent x){
               
               if (shapes.size() != 0){
                   shapes.remove(0);
                   panel2.repaint();
                   
                   
                   
               }
              
               
               }
       };
        undo.addActionListener(back);
        
        clear = new JButton("Clear");
        firstLine.add(clear);
        
        remove = new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent x){
                shapes = new ArrayList();
                panel2.repaint();
            
               
               }
       };
        clear.addActionListener(remove);
        
        
        secondLine = new JPanel();
        secondLine.setBackground(Color.CYAN);
        firstHalf.add(secondLine,BorderLayout.SOUTH);
        
        options = new JLabel("Options:");
        
        secondLine.add(options);
        
        filled = new JCheckBox("Filled");
        secondLine.add(filled);
        
        gradient = new JCheckBox("Use Gradient");
        secondLine.add(gradient);
       
        dashed = new JCheckBox("Dashed");
        secondLine.add(dashed);
        
        w = new JLabel("Line Width:");
        secondLine.add(w);
        
        width = new JSpinner();
        width.setBounds(4,0,16,2);
        secondLine.add(width);
        
        l = new JLabel("Dash Length:");
        secondLine.add(l);
        
        length = new JSpinner();
        length.setBounds(4,0,16,2);
        secondLine.add(length);
       
       
       
       statusBar = new JLabel("");
     
       
       panel.add(firstHalf, BorderLayout.NORTH);
       panel.add(panel2, BorderLayout.CENTER);
       panel.add(statusBar, BorderLayout.SOUTH);
       add(panel);
       
             
       
        
        // add widgets to panels
        
        // firstLine widgets

        // secondLine widgets

        // add top panel of two panels

        // add topPanel to North, drawPanel to Center, and statusLabel to South
        
        //add listeners and event handlers
    }


    // Create event handlers, if needed

    // Create a private inner class for the DrawPanel.
    private class DrawPanel extends JPanel
    {

        public DrawPanel()
        {
            super();
            this.setBackground(Color.WHITE);
            MouseHandler m = new MouseHandler();
            this.addMouseListener(m);
            this.addMouseMotionListener(m);
            shapes = new ArrayList();
            
            
        }

        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            for (MyShapes shape: shapes){
                shape.draw(g2d);
            
            }
            

        }


        private class MouseHandler extends MouseAdapter implements MouseListener, MouseMotionListener
        {
            int mouseY;
            int mouseX;
            
            private Paint paint;
            private Stroke stroke;     
            private Boolean fill;
            
            
            
            public MouseHandler(){
                super();
                paint = Color.BLACK;
                stroke = new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
                fill = false;
                
                
               
               
                
                
                
            
                
                
                
            }
            public void mousePressed(MouseEvent event)
                    
            {   if(gradient.isSelected()){
                    if (color2 == null){
                        color2 = color1;
                    }
                   paint = new GradientPaint(0, 0, color1, 50, 50, color2, true);
                }
                else{
                    paint = color1;
                
                }
                startPoint = new Point(event.getX(), event.getY());
                if (selectShape.getSelectedItem() == "Rectangle"){
                    MyShapes rectangle = new MyRectangle(startPoint, startPoint, paint, stroke, false);
                    shapes.add(0,rectangle);
                    panel2.repaint();
                    
                
                }
                else if (selectShape.getSelectedItem() == "Line"){
                    MyShapes line = new MyLine(startPoint, startPoint, paint, stroke);
                    shapes.add(0,line);
                    panel2.repaint();
                    
                    
                
                }
                else if (selectShape.getSelectedItem() == "Oval"){
                    MyShapes oval = new MyOval(startPoint, startPoint, paint, stroke, fill);
                    shapes.add(0,oval);
                    panel2.repaint();
                
                }
                //x = (Integer)width.getValue();
                //y[0] = (Integer)length.getValue();
              
                if (filled.isSelected()){
                    fill = true;
                
                
                }
                else{
                    fill = false;
                
                }
                
                
                
                int lengthValue = (Integer)length.getValue();
                float x = (float)lengthValue;
                float []y = {x};
                int widthValue = (Integer)width.getValue();
                float z = (float)widthValue;
                
                if (dashed.isSelected())
            {   
               if (x > 0){
                stroke = new BasicStroke(z, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10, y, 0);
               }
            }
               else
            {
                stroke = new BasicStroke(z, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
            }
                
         
                
            }

            public void mouseReleased(MouseEvent event)
                    
            {   endPoint= new Point(event.getX(), event.getY());
                
                if (selectShape.getSelectedItem() == "Line"){
                    MyShapes endLine = new MyLine(startPoint, endPoint, paint, stroke);
                    shapes.set(0,endLine);
                    panel2.repaint();
                }
                else if(selectShape.getSelectedItem() == "Rectangle"){
                    MyShapes endRectangle = new MyRectangle(startPoint, endPoint, paint, stroke, fill);
                    shapes.set(0,endRectangle);
                    panel2.repaint();
                
                }
                else if (selectShape.getSelectedItem() == "Oval"){
                    MyShapes endOval = new MyOval(startPoint, endPoint, paint, stroke, fill);
                    shapes.set(0,endOval);
                    panel2.repaint();
                
                }
               
                
            }

            @Override
            public void mouseDragged(MouseEvent event)
            {   
                currentPoint = new Point(event.getX(), event.getY());
                if (selectShape.getSelectedItem() == "Line"){
                    
                    MyShapes currentLine = new MyLine(startPoint, currentPoint, paint, stroke);
                    shapes.set(0,currentLine);
                    panel2.repaint();
                }
                else if (selectShape.getSelectedItem() == "Rectangle"){
                    MyShapes currentRectangle = new MyRectangle(startPoint, currentPoint, paint, stroke, fill);
                    shapes.set(0,currentRectangle);
                    panel2.repaint();
                
                }
                else if (selectShape.getSelectedItem() == "Oval"){
                    MyShapes currentOval = new MyOval(startPoint, currentPoint, paint, stroke, fill);
                    shapes.set(0,currentOval);
                    panel2.repaint();
                
                }
                
               
            }

            @Override
            public void mouseMoved(MouseEvent event)
                    
            {   
                
                mouseY = MouseInfo.getPointerInfo().getLocation().y;
                mouseX = MouseInfo.getPointerInfo().getLocation().x;
                
                statusBar.setText("(" + this.mouseX + ","+ this.mouseY + ")");
               
                
                
                
            }
        }

    }
}
