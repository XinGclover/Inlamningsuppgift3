/*
 *  
Java18-OOJ
 */
package inlamningsuppgift3;

import java.awt.*;
import static java.awt.BorderLayout.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static java.security.AccessController.getContext;
import javax.swing.*;


public class Brickor extends JFrame {
   JPanel p= new JPanel();
   JButton nspel= new JButton("Nytt Spel");
   Font fnt= new Font("Serief",Font.BOLD,30);
   JLabel[] labArray = new JLabel[16]; 
   Icon tomma= new ImageIcon("src/numberpictures/0.jpg");
   JLabel tom=new JLabel(tomma);
   boolean win=false;

   
   Brickor(){
       p.setLayout(new GridLayout(4,4));
       nspel.setFont(fnt);
       nspel.setBackground(Color.DARK_GRAY);
       nspel.setBorderPainted(true);
       nspel.addActionListener(l->{
          blandaBrickorsImage();
       });
       
       for(int i=0;i<16;i++){
            String picpath="src/numberpictures/"+i+".jpg";         
            labArray[i]= new JLabel(new ImageIcon(picpath),JLabel.CENTER);
            p.add(labArray[i]);
            labArray[i].addMouseListener(musLyss);
       }
       if(win==true)
           System.out.println(ifWin());
       
       this.add(p,CENTER);
       this.add(nspel,SOUTH);
//       pack();
       this.setSize(400,480);
       this.setVisible(true);
       this.setBackground(Color.white);
       this.setLocation(300,200);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       
   }
   
    public void addLabel(){
        for(int i=0;i<16;i++){               
                String picpath="src/numberpictures/"+i+".jpg";         
                labArray[i]= new JLabel(new ImageIcon(picpath),JLabel.CENTER);
                p.add(labArray[i]);
       }    
   }
    
    public void blandaBrickorsImage(){
        JLabel temp= new JLabel();            
        for(int i=0;i<16;i++){
            int rand= (int)(Math.random()*16);
            if(rand==i)
                continue;
            else{
                temp.setIcon(labArray[i].getIcon());
                labArray[i].setIcon(labArray[rand].getIcon());
                labArray[rand].setIcon(temp.getIcon());               
                }             
            }             
    }
    
    public int sokTomma(){
            for(int i=0;i<16;i++){
                if(labArray[i].getIcon().toString().equals(tomma.toString()))     
                 return i; 
                }
       return 0;         
    }
    
    public void bytaIcon(int i){
        int index=sokTomma();
        JLabel temp = new JLabel(new ImageIcon());
        if(((i==index-1)&&!(index%4==0)) // Klicka label finns vänst vid tomma
         ||((i==index+1)&&!(index%4==3)) // höger vid tomma
         ||(i==index+4)||(i==index-4)  ){ // ner och uppe vid tomma
            temp.setIcon(labArray[i].getIcon());
            labArray[i].setIcon(labArray[index].getIcon());
            labArray[index].setIcon(temp.getIcon());       
        }        
    }
     
    public String ifWin(){
        for(int i=0;i<16;i++){
            if(labArray[i].getIcon().toString().equals("src/numberpictures/"+i+".jpg")) {
                win=true;
                return ("You win!");
            }
        }
       return null;
    }
    
    
    MouseAdapter musLyss= new MouseAdapter(){
        @Override
         public void mouseClicked(MouseEvent e){
            JLabel temp = new JLabel(new ImageIcon());
            
            for(int i=0;i<16;i++){
                if(e.getComponent()==labArray[i]){
                bytaIcon(i);
//              temp.setIcon(labArray[i].getIcon());
//               labArray[i].setIcon(labArray[i+1].getIcon());
//               labArray[i+1].setIcon(temp.getIcon());
//               labArray[1].setIcon(labArray[3].getIcon());                 
                }
            }
         }
    };

}
