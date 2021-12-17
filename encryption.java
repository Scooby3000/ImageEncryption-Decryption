package com.company;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.applet.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.Arrays;

class encryption
{

    public static void operate(int key)
    {

        JFileChooser fileChooser=new JFileChooser();
        fileChooser.showOpenDialog(null);
        File file=fileChooser.getSelectedFile();

        //file FileInputStream

        try
        {

            FileInputStream fis=new FileInputStream(file);

            byte []data=new byte[fis.available()];
            fis.read(data);
            int i=0;
            byte j=(byte)key;
            for(byte b:data)
            {
                System.out.println(b);
                data[i]=(byte)(b^key);
                i++;
            }


            FileOutputStream fos=new FileOutputStream(file);

            //write byte key

            fos.write(j);
            fos.write(data);
            fos.close();
            fis.close();
            JOptionPane.showMessageDialog(null, "Done");

        }

        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void decrypt(int key)
    {

        JFileChooser fileChooser=new JFileChooser();
        fileChooser.showOpenDialog(null);
        File file=fileChooser.getSelectedFile();

        //file FileInputStream

        try
        {

            FileInputStream fis=new FileInputStream(file);

            byte []data=new byte[fis.available()];
            fis.read(data);

            byte j = (byte)key;
            byte first = data[0];

            if (j == first)
            {
                System.out.println("SAME KEY");

            }

            else
            {
                System.out.println("WRONG KEY ! PLEASE ENTER CORRECT KEY !");
                JOptionPane.showMessageDialog(null, "WRONG KEY ! PLEASE ENTER CORRECT KEY !" ,"ERROR",JOptionPane.ERROR_MESSAGE);
                return;
            }

            int i=0;
            for(byte b:data)
            {
                System.out.println(b);
                data[i]=(byte)(b^key);
                i++;
            }

            byte []newdata = Arrays.copyOfRange(data,1,data.length);
            FileOutputStream fos=new FileOutputStream(file);
            fos.write(newdata);
            fos.close();
            fis.close();
            JOptionPane.showMessageDialog(null, "Done");

        }

        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    static class imageSwing implements ActionListener {

        JMenuItem aboutUs = new JMenuItem("About US ");
        JMenuItem aboutProject = new JMenuItem("About Project");

        imageSwing(){

            System.out.println("this is testing");

            JFrame f=new JFrame();

            JMenuBar jMenuBar = new JMenuBar();
            JMenu jmenu2 = new JMenu("About");
            jmenu2.add(aboutUs);
            jmenu2.add(aboutProject);

            jMenuBar.add(jmenu2);

            f.setTitle("Image Encryption and Decryption!");
            //f.setSize(600,600);
            f.setBounds(300, 90, 500, 500 );
            f.setResizable(false);
            f.setJMenuBar(jMenuBar);
            f.setLocationRelativeTo(null);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            aboutUs.addActionListener(this);
            aboutProject.addActionListener(this);

            Font font=new Font("Roboto",Font.BOLD,25);

            //creating button

            JButton button=new JButton();
            button.setText("Encrypt");
            button.setFont(font);
            button.setSize(100,30);
            button.setLocation(100,100);
            button.setBackground(Color.CYAN);

            //creating text field

            JTextField textField=new JTextField(10);
            textField.setFont(font);
            textField.setSize(100,30);
            textField.setLocation(200,200);

            //creating decrypt button

            JButton button1=new JButton();
            button1.setText("Decrypt");
            button1.setFont(font);
            button1.setLocation(300,300);
            button1.setBackground(Color.CYAN);

            button.addActionListener(e->{
                System.out.println("button clicked");

                try
                {

                    String text = textField.getText();
                    int temp = Integer.parseInt(text);
                    operate(temp);
                }

                catch(NumberFormatException h)
                {
                    JOptionPane.showMessageDialog(null, "Please Enter a number!","ERROR",JOptionPane.ERROR_MESSAGE);
                }

            });

            button1.addActionListener(e->{
                System.out.println("button1 clicked");

                try
                {

                    String text = textField.getText();
                    int temp = Integer.parseInt(text);
                    decrypt(temp);
                }

                catch(NumberFormatException h)
                {
                    JOptionPane.showMessageDialog(null, "You can only enter numbers!","ERROR",JOptionPane.ERROR_MESSAGE);
                }

            });

            f.setLayout(new FlowLayout());

            f.add(button);
            f.add(button1);
            f.add(textField);
            f.setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == aboutUs )
            {
                JOptionPane.showMessageDialog(null,"Hello, My Name is Sumanta and my roll no is 28 \n" +
                        "Hello, My Name is Sneh and my roll no is 11\n" +
                        "Hello, My Name is Dhairya and my roll no is 12", "About Us", JOptionPane.PLAIN_MESSAGE);


            }

            if (e.getSource() == aboutProject )
            {
                JOptionPane.showMessageDialog(null,"Hello, This is our project Image Encrypto and Decrypto\n" +
                        "You can easily hide any important image or photo which you dont want the other users of this computer to see ,\n" +
                        "like screenshots of bank details or passwords etc ,private photos and many more.\n " +
                        "What you have to do is just enter a key which you need to remember and then select the file you want to hide and done it would be encrypted\n" +
                        "now in order to decrypt it you need to enter the same key which you inserted before and select the file and done that's it , its decrypted \n" +
                        "HAPPY CODING", "About Project", JOptionPane.PLAIN_MESSAGE);
            }

        }
    }



    public static void main(String[] args) {

        new imageSwing();

    }
}
