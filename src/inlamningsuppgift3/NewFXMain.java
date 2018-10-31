/*
 *  
Java18-OOJ
 */
package inlamningsuppgift3;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author xingao
 */
public class NewFXMain extends Application {
    Button[] butArray= new Button[16];
    
    @Override
    public void start(Stage stage) {
        
        Button nspel = new Button("Nytt Spel");
        nspel.setMinSize(80,10);
        nspel.setPadding(new Insets(10,30,20,30));
        nspel.setId("newgame");
     
        nspel.setOnAction((ActionEvent event) -> {
            for(int i=0;i<500;i++){
                tommaFlytta();
            }
        });
        
        GridPane grid= new GridPane();
        grid.setHgap(1);
        grid.setVgap(1);
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));
       
        
        for(int i=0;i<16;i++){ 
            if(i==0){
                butArray[i]=new Button("");
            }
            else{
                butArray[i]=new Button(String.valueOf(i));
            }
                butArray[i].setAlignment(Pos.CENTER);
                butArray[i].setMinSize(100, 100);
                grid.add(butArray[i], i%4, i/4);
                butArray[i].setOnAction(myeventh);              
        }  
           
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(grid,nspel);
        root.setSpacing(20);
       
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Brickor Spel");
        scene.getStylesheets().add(NewFXMain.class.getResource("Styling.css").toExternalForm());
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public void tommaFlytta(){
        int index=sokTomma();           
        int rand= (int)(Math.random()*4);//Anger en opetations namn
        
        Button temp= new Button();
        if(!(index%4==3) && rand==0){
            temp.setText(butArray[index].getText());
            butArray[index].setText(butArray[index+1].getText());
            butArray[index+1].setText(temp.getText());           
        }
        if(!(index%4==0) && rand==1){
            temp.setText(butArray[index].getText());
            butArray[index].setText(butArray[index-1].getText());
            butArray[index-1].setText(temp.getText());        
        }
        if(index>3 && rand==2){
            temp.setText(butArray[index].getText());
            butArray[index].setText(butArray[index-4].getText());
            butArray[index-4].setText(temp.getText());        
        }
        if(index<12 && rand==3){
            temp.setText(butArray[index].getText());
            butArray[index].setText(butArray[index+4].getText());
            butArray[index+4].setText(temp.getText());        
        }
    }
    
    public int sokTomma(){
            for(int i=0;i<16;i++){
                if(butArray[i].getText().equals(""))     
                 return i; 
                }
       return 0;         
    }
    
    public void bytaIcon(int i){
        int index=sokTomma();
        Button temp = new Button();
        if(((i==index-1)&&!(index%4==0)) // Klicka label finns vänst vid tomma
         ||((i==index+1)&&!(index%4==3)) // höger vid tomma
         ||(i==index+4)||(i==index-4)  ){ // ner och uppe vid tomma
            temp.setText(butArray[i].getText());
            butArray[i].setText(butArray[index].getText());
            butArray[index].setText(temp.getText());       
        }        
    }
     
    public boolean ifWin(){
        for(int i=1;i<16;i++){
                if(!(butArray[i].getText().equals(Integer.toString(i)))) {
                    return false;
                }
        }
       return true;
    }
    
    EventHandler myeventh= new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {        
            for(int i=0;i<16;i++){
                if(event.getSource().equals(butArray[i])){
                bytaIcon(i);
                if(ifWin())
                  System.out.println("You Win!!");
                    
                } 
            } 
        }
     };
}
