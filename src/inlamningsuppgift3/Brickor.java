/*
 *  
Java18-OOJ
 */
package inlamningsuppgift3;

import java.awt.*;
import static java.awt.BorderLayout.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;


public class Brickor extends JFrame {
   JPanel p= new JPanel();
   JButton nspel= new JButton("Nytt Spel");
   Font fntB= new Font("Serief",Font.BOLD,30);
   Font fntL=new Font("Comic Sans MS",Font.BOLD,40);
   JLabel[] labArray = new JLabel[16]; 
   
   Brickor(){
       
       p.setLayout(new GridLayout(4,4));
       nspel.setFont(fntB);
       nspel.setForeground(Color.gray);
       nspel.setOpaque(true);
       nspel.setBackground(Color.pink);
       nspel.setBorderPainted(false);
       
       nspel.addActionListener(l->{
          for(int i=0;i<500;i++){
              tommaFlytta();
          }
       });
       for(int i=0;i<16;i++){  
           if(i==0){
             labArray[0]=new JLabel("",JLabel.CENTER);
           }
           else{
            labArray[i]= new JLabel(String.valueOf(i),JLabel.CENTER);
           }
            labArray[i].setSize(20, 20);
            labArray[i].setFont(fntL);
            labArray[i].setOpaque(true);
            labArray[i].setBackground(Color.getHSBColor(30, 50, 20));
            labArray[i].setForeground(Color.pink);
            labArray[i].setBorder(BorderFactory.createRaisedBevelBorder());
            p.add(labArray[i],CENTER);
            labArray[i].addMouseListener(musLyss);
       }
       
       this.add(p,CENTER);
       this.add(nspel,SOUTH);
       pack();
       this.setSize(400,480);
       this.setVisible(true);
       this.setBackground(Color.white);
       this.setLocation(300,200);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       
   }
   
    public void tommaFlytta(){
        int index=sokTomma();           
        int rand= (int)(Math.random()*4);//Anger en opetations namn

        JLabel temp= new JLabel();
        if(!(index%4==3) && rand==0){
            temp.setText(labArray[index].getText());
            labArray[index].setText(labArray[index+1].getText());
            labArray[index+1].setText(temp.getText());           
        }
        if(!(index%4==0) && rand==1){
            temp.setText(labArray[index].getText());
            labArray[index].setText(labArray[index-1].getText());
            labArray[index-1].setText(temp.getText());        
        }
        if(index>3 && rand==2){
            temp.setText(labArray[index].getText());
            labArray[index].setText(labArray[index-4].getText());
            labArray[index-4].setText(temp.getText());        
        }
        if(index<12 && rand==3){
            temp.setText(labArray[index].getText());
            labArray[index].setText(labArray[index+4].getText());
            labArray[index+4].setText(temp.getText());        
        }
    }
    
    public int sokTomma(){
            for(int i=0;i<16;i++){
                if("".equals(labArray[i].getText().trim()))     
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
            temp.setText(labArray[i].getText());
            labArray[i].setText(labArray[index].getText());
            labArray[index].setText(temp.getText());       
        }        
    }
     
    public boolean ifWin(){
        for(int i=1;i<16;i++){
            if(!(labArray[i].getText().equals(Integer.toString(i)))){
                return false;
            }
        }
       return true;
    }
    
    
    MouseAdapter musLyss= new MouseAdapter(){
        @Override
         public void mouseClicked(MouseEvent e){         
            for(int i=0;i<16;i++){
                if(e.getComponent()==labArray[i]){
                bytaIcon(i);
                if(ifWin())
                    JOptionPane.showMessageDialog(null, "You Win!");   
                } 
            }
         }
    };

}
