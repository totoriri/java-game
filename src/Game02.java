//importing built-in packages.
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Random;

import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import javax.swing.border.EmptyBorder;



//in order to make our Game class represent our Game window,  "extend JFrame" is added after class name.
//at the same time , i need to import "javax.swing.jframe"
public class Game02 extends JFrame{

    private JPanel panel;
    private JLabel[] holes = new JLabel[16];
    private int[] board = new int[16];
    
    private int score = 0;
    private JLabel lblScore;
  
   

//    this is a function including all the the code that is related to initializing the gui(swing components)
    private void initGUI(){
        setTitle("Whack A Mole");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//       setbounds(x,y width,height)
        setBounds(100, 100, 608, 720);

        JPanel contentPane = new JPanel();
        contentPane = new JPanel();
        contentPane.setBackground(new Color(204, 119, 34));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);

// adding title 
        JLabel lblTitle = new JLabel("Whack A Mole");
        lblTitle.setForeground(new Color(51, 51, 51));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Century Gothic", Font.BOLD, 20));
        lblTitle.setBounds(0, 0, 602, 47);
        contentPane.add(lblTitle);

// making a panel
        panel = new JPanel();
        panel.setBackground(new Color(225, 225, 204));
        panel.setBounds(32, 105, 535, 546);
        panel.setLayout(null);
        
// adding a cursor
        panel.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                loadImage("/hammer.png").getImage(),
                new Point(0,0),"custom cursor1"));
        contentPane.add(panel);

        
// adding a score board
        lblScore = new JLabel("Score: 0");
        lblScore.setHorizontalAlignment(SwingConstants.TRAILING);
        lblScore.setFont(new Font("Cambria", Font.BOLD, 14));
        lblScore.setForeground(new Color(135, 206, 250));
        lblScore.setBounds(423, 54, 144, 33);
        contentPane.add(lblScore);
        
        setContentPane(contentPane);
        
        
// adding 16 holes to panel
        holes[0] = new JLabel("0");
        holes[0].setName("0");
        holes[0].setBounds(0, 396, 132, 132);
        panel.add(holes[0]);

        holes[1] = new JLabel("1");
        holes[1].setName("1");
        holes[1].setBounds(132, 396, 132, 132);
        panel.add(holes[1]);

        holes[2] = new JLabel("2");
        holes[2].setName("2");
        holes[2].setBounds(264, 396, 132, 132);
        panel.add(holes[2]);

        holes[3] = new JLabel("3");
        holes[3].setName("3");
        holes[3].setBounds(396, 396, 132, 132);
        panel.add(holes[3]);

        holes[4] = new JLabel("4");
        holes[4].setName("4");
        holes[4].setBounds(0, 264, 132, 132);
        panel.add(holes[4]);

        holes[5] = new JLabel("5");
        holes[5].setName("5");
        holes[5].setBounds(132, 264, 132, 132);
        panel.add(holes[5]);

        holes[6] = new JLabel("6");
        holes[6].setName("6");
        holes[6].setBounds(264, 264, 132, 132);
        panel.add(holes[6]);

        holes[7] = new JLabel("7");
        holes[7].setName("7");
        holes[7].setBounds(396, 264, 132, 132);
        panel.add(holes[7]);

        holes[8] = new JLabel("8");
        holes[8].setName("8");
        holes[8].setBounds(0, 132, 132, 132);
        panel.add(holes[8]);

        holes[9] = new JLabel("9");
        holes[9].setName("9");
        holes[9].setBounds(132, 132, 132, 132);
        panel.add(holes[9]);

        holes[10] = new JLabel("10");
        holes[10].setName("10");
        holes[10].setBounds(264, 132, 132, 132);
        panel.add(holes[10]);

        holes[11] = new JLabel("11");
        holes[11].setName("11");
        holes[11].setBounds(396, 132, 132, 132);
        panel.add(holes[11]);

        holes[12] = new JLabel("12");
        holes[12].setName("12");
        holes[12].setBounds(0, 0, 132, 132);
        panel.add(holes[12]);

        holes[13] = new JLabel("13");
        holes[13].setName("13");
        holes[13].setBounds(132, 0, 132, 132);
        panel.add(holes[13]);

        holes[14] = new JLabel("14");
        holes[14].setName("14");
        holes[14].setBounds(264, 0, 132, 132);
        panel.add(holes[14]);

        holes[15] = new JLabel("15");
        holes[15].setName("15");
        holes[15].setBounds(396, 0, 132, 132);
        panel.add(holes[15]);
        
    }
    
    

     private void pressedButton(int id){
         int val = board[id];

         //if val is 1 = mole
//         it means if it is a mole , increase the score by 1
         
         if(val==1){ 
             score++;
           //if val is 0 = empty hole
//         it means if it is  a empty hole , decrease  the score by 1
         }else{ //val==0
             score--;
         }

 //update the score
         lblScore.setText("Score: " + score); 

//resetting the board.
         clearBoard();
         
 //generating another random mole
         genRandMole();
     }

    
     private void initEvents(){
         for(int i = 0; i < holes.length; i++){
             holes[i].addMouseListener(new MouseAdapter() {
                 public void mouseClicked(MouseEvent e){
                     JLabel lbl = (JLabel)e.getSource();
                     int id = Integer.parseInt(lbl.getName());
                     pressedButton(id);
                 }
             });
         }
         
     }

     
    private void clearBoard(){
        for(int i = 0; i < 16; i++){
            holes[i].setIcon(loadImage("/mogura-hole.png"));
            board[i] = 0;
        }
    }
    
    
//  generating a random number from0-15 that determines which hole a　mole will appear.
    private void genRandMole(){
        Random rnd = new Random(); //seeding random with current time
        int moleID = rnd.nextInt(16);
//        ランダムに選ばれたあるインデックスに対応したholeがモグラの画像に切り替わる
        holes[moleID].setIcon(loadImage("/mogura-mogura.png"));
//        これはモグラの画像になったところが１の処理されるところだよって定義してる
        board[moleID] = 1;
    }

   
//this is a function for loading images
    private ImageIcon loadImage(String path){
        Image image = new ImageIcon(this.getClass().getResource(path)).getImage();
        Image scaledImage = image.getScaledInstance(132, 132,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
    
    
//this is a constructor(calling other functions)
    public Game02() {
        initGUI();
        clearBoard();
        initEvents();
    }

//main method
    public static void main(String[] args) {
        Game02 frame = new Game02();
//      making the frame(game object) visible on the screen 
        frame.setVisible(true);
    }
}

