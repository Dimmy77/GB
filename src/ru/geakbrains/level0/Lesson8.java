package ru.geakbrains.level0;

import javax.swing.*;
import java.awt.*;


public class Lesson8 extends JFrame{

    public Lesson8() {
        super("My Window");
        setSize(500,600);
        setLocation(100,100);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JButton btn1 =new JButton("Выход");
        btn1.addActionListener(e -> {System.exit(0);});

        JButton btn2=new JButton("Обо мне");
        btn2.addActionListener(a-> {
            WindowsAbout(Lesson8.this);
        });

        JButton btn3=new JButton("Предупреждение");
        btn3.addActionListener(e -> {

            JOptionPane.showMessageDialog(Lesson8.this, "Hello world!!!", "Warning",
                    JOptionPane.WARNING_MESSAGE);
        });

        JButton btn4=new JButton("А поговорить?!");
        btn4.addActionListener(e -> {
            Object[] option= {"Yes", "No", "Ask letter"};
            int n = JOptionPane.showOptionDialog(Lesson8.this, "Вы уверены?", null,
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[2]);
        });

        GridLayout layout = new GridLayout(1,3); //укладывает по сетке
        layout.addLayoutComponent("Поле 1",btn1);
        layout.addLayoutComponent("Поле 2",btn2);
        layout.addLayoutComponent("Поле 3",btn3);
        layout.addLayoutComponent("Поле 4",btn4);
        JPanel btnPanel = new JPanel(layout);
        btnPanel.add(btn1);
        btnPanel.add(btn2);
        btnPanel.add(btn3);
        btnPanel.add(btn4);
        add(btnPanel,BorderLayout.SOUTH); //BorderLayout (по умолчанию)- север, юг, запад, восток, центр

        setVisible(true);
        //FlowLayout flowLayout = new FlowLayout(); //укладывает по порядку включения объектов на слой

    }

    public static void WindowsAbout(JFrame parent){
            JDialog nWindows = new JDialog(parent);
            nWindows.setTitle("Обо мне");
            nWindows.setSize(parent.getWidth()/3, parent.getHeight()/5);
            nWindows.setLocation(parent.getX()+parent.getWidth()/3, parent.getY()+parent.getHeight()/3);
            JLabel helloW = new JLabel("I'm student Geekbrain.ru", JLabel.CENTER);
            nWindows.add(helloW,BorderLayout.CENTER);
            nWindows.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            nWindows.setModalityType(Dialog.ModalityType.TOOLKIT_MODAL);
            nWindows.setVisible(true);
    }

    public static void main(String[] args){
        Lesson8 x= new Lesson8();
    }
}
