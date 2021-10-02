import java.awt.AWTException;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Bot {

	public static void main(String[] args) throws AWTException, IOException, InterruptedException {
		// TODO Auto-generated method stub

		
		
//		
		Robot bot = new Robot();
//
//		String format = "png";
//        
//		
//		
//	
   int tileWidth = 95;
//   
//   
  
   
   
   
 while (true) {
        
            Rectangle screenRect = new Rectangle(5,556  - 170,tileWidth * 4,170 * 2 );
    
       	BufferedImage screenFullImage = bot.createScreenCapture(screenRect);

   //ImageIO.write(screenFullImage, "png",new File("AutomaticPiano\\Test.png"));
       	

//       	
       	
       	int tile1 = (getBlue(screenFullImage,screenFullImage.getHeight()/2,screenFullImage.getHeight(),0 ,tileWidth  ));
       int tile2 = (getBlue(screenFullImage,screenFullImage.getHeight()/2,screenFullImage.getHeight(),tileWidth ,tileWidth*2 ));
       int tile3=(getBlue(screenFullImage,screenFullImage.getHeight()/2,screenFullImage.getHeight(),tileWidth * 2 ,tileWidth *3 ));
       	int tile4 = (getBlue(screenFullImage,screenFullImage.getHeight()/2,screenFullImage.getHeight(),tileWidth *3 ,tileWidth *4 ));

       	
       	
       	
     	
       	int tileRR = (getRed(screenFullImage,screenFullImage.getHeight()/2,screenFullImage.getHeight(),0 ,tileWidth  ));
       int tile2RR = (getRed(screenFullImage,screenFullImage.getHeight()/2,screenFullImage.getHeight(),tileWidth ,tileWidth*2 ));
       int tile3RR=(getRed(screenFullImage,screenFullImage.getHeight()/2,screenFullImage.getHeight(),tileWidth * 2 ,tileWidth *3 ));
       	int tile4RR = (getRed(screenFullImage,screenFullImage.getHeight()/2,screenFullImage.getHeight(),tileWidth *3 ,tileWidth *4 ));
       	
       	
     	int tile1R = (getRed(screenFullImage,0,screenFullImage.getHeight()/2,0 ,tileWidth  ));
        int tile2R = (getRed(screenFullImage,0,screenFullImage.getHeight()/2,tileWidth ,tileWidth*2 ));
        int tile3R=(getRed(screenFullImage,0,screenFullImage.getHeight()/2,tileWidth * 2 ,tileWidth *3 ));
        int tile4R = (getRed(screenFullImage,0,screenFullImage.getHeight()/2 ,tileWidth *3 ,tileWidth *4 ));
       	
       	
       	
//       	
//       	
   		System.out.println(tile1 + "  " + tile1R + " ");
   		System.out.println(tile2 + "  " + tile2R + " ");
   		System.out.println(tile3 + "  " + tile3R + " ");
   		System.out.println(tile4 + "  " +tile4R + " ");

       	
   		
   		
   
       	if(tile1 < 2000000){
       		 
     

       		System.out.println("Pressing Tile 1");
          	 bot.keyPress(KeyEvent.VK_A);

          	 
          	 
       		
       	} else {
       		
      		if(tileRR > 2000000 )
              	 bot.keyRelease(KeyEvent.VK_A);
       		
       	}
       		
       	//468190 
      		
       
     //  	500000
       	if(tile2 < 2000000){
       		
       		
       
       		
       		System.out.println("Pressing Tile 2");
          	 bot.keyPress(KeyEvent.VK_S);

          

       		
       	}else {
       		
       	 if(tile2RR > 2000000)
        	 bot.keyRelease(KeyEvent.VK_S);
      		
      	}    
       	
       	if(tile3 < 2000000){
       		
       		
       		
       		System.out.println("Pressing Tile 3");
          	 bot.keyPress(KeyEvent.VK_K);

          	 
          	 

       	} else {
       		
       		if(tile3RR > 2000000)
            bot.keyRelease(KeyEvent.VK_K);
      		
      	}
       	if(tile4 < 2000000){
       		
       		
       		
       		
       		System.out.println("Pressing Tile 4");
          	 bot.keyPress(KeyEvent.VK_L);

          	 
          	 

       		
       		
       	} else {
       		
       		if(tile4RR> 2000000)
             bot.keyRelease(KeyEvent.VK_L);
      		
      	}
      	
       	
  
      	
 }
       	
       	
       
       	
       	
       	
       
      
       	
       	
       	
       	
       	
       	
       	
       	
      
       	
       	


       	
       	
       	
       	
    //    Run(screenFullImage);

           
//         
       
	}
	
	public static void DragMouse(int x1, int y1, int x2, int y2) throws AWTException, InterruptedException {
		Robot bot = new Robot();
		bot.mouseMove(x1, y1);
		bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		Thread.sleep(200);
		bot.mouseMove(x2, y2);
		bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		
		
	}
	
	
	public static int getBlue(BufferedImage img, int rowStart, int rowEnd, int colStart, int colEnd) {
		
		int total = 0;
		
		 for (int r = rowStart; r < rowEnd; r++) {
	            for (int c = colStart; c < colEnd; c++) {
	            
	            
	        	int pixel1= img.getRGB(c,r);
	               Color color1 = new Color(pixel1, true);
	               total+=  color1.getBlue();	            
	            
	            }
	
	}
	
		 
			return total;
	 
		 
	}
	
	
	
	public static int getRed(BufferedImage img, int rowStart, int rowEnd, int colStart, int colEnd) {
		
		int total = 0;
		
		 for (int r = rowStart; r < rowEnd; r++) {
	            for (int c = colStart; c < colEnd; c++) {
	            
	            
	        	int pixel1= img.getRGB(c,r);
	               Color color1 = new Color(pixel1, true);
	               total+=  color1.getRed();	            
	            
	            }
	
	}
	
		 
			return total;
	 
		 
	}
	
	
	
	
	public static void Run(BufferedImage img ) throws IOException, AWTException, InterruptedException {
		Robot bot = new Robot();

	   		
		   
	    		
	    		    
              
  		      int w1 = img.getWidth();
  		      int h1 = img.getHeight();
  		      
  		      	      int width = w1/4;
  		
	          int total1 = 0;
	          
	     
	          int total2 = 0;

	      
	          int total3 = 0;

	  
	          int total4 = 0;

	          
	          
	          
  		         for (int j = 0; j < h1; j++) {
	    		            for (int z = 0; z < width; z++) {
	    		            	 	               
	    		            	int pixel1= img.getRGB(z, j);
		    		               Color color1 = new Color(pixel1, true);
		    		               total1 +=  color1.getBlue();
		    		       
		    		       
					    		               
	    		            }
  		         
		
  		         }
  		         
  		         


  		         for (int j = 0; j < h1; j++) {
 		            for (int z = width; z < 2 * width; z++) {
 		            	 	               
 		            	int pixel2= img.getRGB(z, j);
	    		               Color color2 = new Color(pixel2, true);
	    		               total2 +=  color2.getBlue();
	    		       
	    		       
				    		               
 		            }
	         
	
	         }
  		         for (int j = 0; j < h1; j++) {
 		            for (int z = 2 * width; z < 3 * width; z++) {
 		            	 	               
 		            	int pixel3= img.getRGB(z, j);
	    		               Color color3 = new Color(pixel3, true);
	    		               total3 += color3.getBlue();
	    		       
	    		       
				    		               
 		            }
	         
	
	         }
  		         for (int j = 0; j < h1; j++) {
 		            for (int z = 3 * width; z < w1; z++) {
 		            	 	               
 		            	int pixel1= img.getRGB(z, j);
	    		               Color color4 = new Color(pixel1, true);
	    		               total4 += color4.getBlue();
	    		       
	    		       
				    		               
 		            }
	         
	
	         }
	         


  		         if(total1 < 700000) {
  		        	 
  		        	 bot.keyPress(KeyEvent.VK_D);
		        	 bot.keyRelease(KeyEvent.VK_D);
  		        	// bot.mouseMove(774, 786);
  		        	// System.out.println("D Pressed");
  		        	 Thread.sleep(1000);
  		         }
  		       if(total2 < 700000) {
		        	 
		        	 bot.keyPress(KeyEvent.VK_F);
		        	 bot.keyRelease(KeyEvent.VK_F);
  		        	// System.out.println("F Pressed");
  		        	//bot.mouseMove(920,845);
  		        	 Thread.sleep(1000);


		         }      if(total3 < 700000) {
  		        	 
		        	 bot.keyPress(KeyEvent.VK_J);
		        	 bot.keyRelease(KeyEvent.VK_J);
  		        	 System.out.println("J Pressed");
  		        	 //bot.mouseMove(1045,776);
  		        	 Thread.sleep(1000);


  		         }      if(total4 < 700000) {
		        	 bot.keyPress(KeyEvent.VK_K);
		        	 bot.keyRelease(KeyEvent.VK_K);


  		        	 Thread.sleep(1000);

  		        	 
  		         }
  		         
	}

}
