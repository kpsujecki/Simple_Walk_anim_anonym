package com.company;

import java .awt.*;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;



public class Simple_Walk_anim_anonym{
    public int wielkosc=300;
    public int x=wielkosc/2;
    public int y=wielkosc/2;
    static final int FPS_MIN = 0;
    static final int FPS_MAX = 200;
    static final int FPS_INIT = 5;

    public static void main(String s[]) {
        JRadioButton rb1,rb2, rb3;
        rb1=new JRadioButton("RED");
        rb2=new JRadioButton("BLUE");
        rb3=new JRadioButton("GREEN");
        JFrame frame = new JFrame("błądzenie przypadkowe");
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        Wykresanimstart wykres = new Wykresanimstart();
        JLabel label = new JLabel("Etykieta");
        Border border = BorderFactory.createLineBorder(Color.BLUE, 3);
        label.setBorder(border);
        JSlider suwak = new JSlider(JSlider.VERTICAL,
                FPS_MIN, FPS_MAX, FPS_INIT);

        JButton button = new JButton();
        button.setText("START");
        button.setEnabled(false);
        ButtonGroup bg = new ButtonGroup();
        bg.add(rb1);
        bg.add(rb2);
        bg.add(rb3);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                wykres.rysowac=true;
                wykres.repaint();
                suwak.setEnabled(false);

            }
        });
        suwak.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (rb1.isSelected()) {
                    wykres.kolor = Color.RED;
                    wykres.repaint();
                }
                if (rb2.isSelected()) {
                    wykres.kolor = Color.BLUE;
                    wykres.repaint();
                }
                if (rb3.isSelected()) {
                    wykres.kolor = Color.GREEN;
                    wykres.repaint();
                }
                wykres.x = suwak.getValue();
                wykres.y = suwak.getValue();
                wykres.repaint();
                button.setEnabled(true);
            }
        });
        rb1.setBounds(100,150, 100,30);
        rb2.setBounds(100,100, 100,30);
        rb3.setBounds(100,50, 100,30);


        panel.add(rb1);
        panel.add(rb2);
        panel.add(rb3);

        panel.add(suwak);
        panel.add(label);
        panel.add(button);

        panel.setBackground(Color.orange);
        panel.add(wykres);
        frame.add(panel);
        frame.setSize(350, 350);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


    }

}

class Wykresanimstart extends JPanel{
    Color kolor = Color.CYAN;
    int wielkosc=300;
    int x=wielkosc/2;
    int y=wielkosc/2;
    int step=0;
    boolean rysowac=false;
    public       Wykresanimstart(){
        setBorder(BorderFactory.createLineBorder(Color.green, 2));
    }

    public Dimension getPreferredSize()
    {return new Dimension(wielkosc,wielkosc);}

    public void paintComponent(Graphics graf){
        super.paintComponent(graf);
        graf.setColor(kolor);
        graf.fillOval(x, y, 6, 6);

        if (rysowac) {
            step++;
            double r=Math.random();
            if(r<0.25) {x++; if(x==wielkosc) x=0;}
            else if (r<0.5) {x--;if(x==-1) x=wielkosc-1;}
            else if (r<0.75) {y++;if(y==wielkosc) y=0;}
            else {y--;if(y==-1) y=wielkosc-1;}
            graf.fillOval(x, y, 4, 4);
            if (true) {
                try {
                    Thread.sleep(50);               // sleep for 10 msec
                } catch (InterruptedException t){}
                repaint();

            }

        }

    }

}