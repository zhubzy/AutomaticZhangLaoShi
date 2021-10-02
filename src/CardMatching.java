import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
public class CardMatching implements ChangeListener {
	   Boolean use_mask = false;
	    Mat img = new Mat(), templ = new Mat();
	    Mat mask = new Mat();
	    int match_method;
	    JLabel imgDisplay = new JLabel(), resultDisplay = new JLabel();
	    public static ArrayList<Point> cardPositions = new ArrayList <Point>();
	    String HandFileName;
	    String CardFileName;
	    
	    double currentPos;
	    
	    public CardMatching() {
	    	
	    	HandFileName = "Problem2.png";
	        img = Imgcodecs.imread(HandFileName, Imgcodecs.IMREAD_COLOR);

	    	
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    public void run() {
	      
	        
	        img = Imgcodecs.imread(HandFileName, Imgcodecs.IMREAD_COLOR);
	    
	        templ = Imgcodecs.imread(CardFileName, Imgcodecs.IMREAD_COLOR);
	       
	        if (img.empty() || templ.empty() || (use_mask && mask.empty())) {
	            System.out.println("Can't read one of the images");
	            System.exit(-1);
	        }
	        matchingMethod();
//	        createJFrame();
	    }
	    private void matchingMethod() {
	        Mat result = new Mat();
	        Mat img_display = new Mat();
	        img.copyTo(img_display);
	        int result_cols = img.cols() - templ.cols() + 1;
	        int result_rows = img.rows() - templ.rows() + 1;
	        result.create(result_rows, result_cols, CvType.CV_32FC1);
	        Boolean method_accepts_mask = (Imgproc.TM_SQDIFF == match_method || match_method == Imgproc.TM_CCORR_NORMED);
	        if (use_mask && method_accepts_mask) {
	            Imgproc.matchTemplate(img, templ, result, match_method, mask);
	        } else {
	            Imgproc.matchTemplate(img, templ, result, match_method);
	        }
//	        Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1, new Mat());
	        Point matchLoc;
	        
	        
	        
//	        for(int i = 0; i < 1; i++) {
//		    	match_method = Imgproc.TM_SQDIFF_NORMED;
//
//	        Core.MinMaxLocResult mmr = Core.minMaxLoc(result);
//	        
//	        if (match_method == Imgproc.TM_SQDIFF || match_method == Imgproc.TM_SQDIFF_NORMED) {
//	            matchLoc = mmr.minLoc;
//		      
//		        
//		        
//		        
//	        } else {
//	            matchLoc = mmr.maxLoc;
//	            System.out.println( currentPos + CardFileName+ "   " + mmr.minVal);
//		        currentPos = matchLoc.x + matchLoc.y;
//		        cardPositions.add(matchLoc);
//	        }
//	      
//	      
//
//	        
//	        Imgproc.rectangle(img_display, matchLoc, new Point(matchLoc.x + templ.cols(), matchLoc.y + templ.rows()),
//	                new Scalar(0, 0, 0), 2, 8, 0);
//	        Imgproc.rectangle(result, matchLoc, new Point(matchLoc.x + templ.cols(), matchLoc.y + templ.rows()),
//	                new Scalar(0, 0, 0), 2, 8, 0);
//	        
//	        captureCardTest(matchLoc.x , matchLoc.y );
//	        
//	        }
	        
	        
	        for(int i = 0; i < 2; i++) {
		    	match_method = Imgproc.TM_CCOEFF_NORMED;

	        Core.MinMaxLocResult mmr = Core.minMaxLoc(result);
	        
	        if (match_method == Imgproc.TM_SQDIFF || match_method == Imgproc.TM_SQDIFF_NORMED) {
	            matchLoc = mmr.minLoc;
		      
		        
		        
		        
	        } else {
	            matchLoc = mmr.maxLoc;
	            System.out.println( currentPos + CardFileName+ "   " + mmr.minVal);
		        currentPos = matchLoc.x + matchLoc.y;
		        cardPositions.add(matchLoc);
	        }
	        
	      
	      

	        
	        Imgproc.rectangle(img_display, matchLoc, new Point(matchLoc.x + templ.cols(), matchLoc.y + templ.rows()),
	                new Scalar(0, 0, 0), 2, 8, 0);
	        Imgproc.rectangle(result, matchLoc, new Point(matchLoc.x + templ.cols(), matchLoc.y + templ.rows()),
	                new Scalar(0, 0, 0), 2, 8, 0);
	        
	        captureCardTest(matchLoc.x , matchLoc.y );
	        
	        }
	        
	    
	        
	        
//	        
//	        Image tmpImg = HighGui.toBufferedImage(img_display);
//	        ImageIcon icon = new ImageIcon(tmpImg);
//	        imgDisplay.setIcon(icon);
//	        result.convertTo(result, CvType.CV_8UC1, 255.0);
//	        tmpImg = HighGui.toBufferedImage(result);
//	        icon = new ImageIcon(tmpImg);
//	        resultDisplay.setIcon(icon);
	    }
	    
        public void test() {
        	 img = Imgcodecs.imread(HandFileName, Imgcodecs.IMREAD_COLOR);
      	    
  	        templ = Imgcodecs.imread(CardFileName, Imgcodecs.IMREAD_COLOR);
        	
        	  if (img.empty() || templ.empty() || (use_mask && mask.empty())) {
  	            System.out.println("Can't read one of the images");
  	            System.exit(-1);
  	        }
        	
        	
        	
 	       
 	  
        	
        	 Mat result = new Mat();
 	        Mat img_display = new Mat();
 	        img.copyTo(img_display);
 	        int result_cols = img.cols() - templ.cols() + 1;
 	        int result_rows = img.rows() - templ.rows() + 1;
 	        result.create(result_rows, result_cols, CvType.CV_32FC1);
 	        Boolean method_accepts_mask = (Imgproc.TM_SQDIFF == match_method || match_method == Imgproc.TM_CCORR_NORMED);
 	        if (use_mask && method_accepts_mask) {
 	            Imgproc.matchTemplate(img, templ, result, match_method, mask);
 	        } else {
 	            Imgproc.matchTemplate(img, templ, result, match_method);
 	        }
// 	        Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1, new Mat());
 	        Point matchLoc;
 	       match_method = Imgproc.TM_CCOEFF_NORMED;

	        Core.MinMaxLocResult mmr = Core.minMaxLoc(result);
	        
	        if (match_method == Imgproc.TM_SQDIFF || match_method == Imgproc.TM_SQDIFF_NORMED) {
	            matchLoc = mmr.minLoc;
		      
		        
		        
		        
	        } else {
	            matchLoc = mmr.maxLoc;
	            System.out.println( currentPos + CardFileName+ "   " + mmr.minVal);
		        currentPos = matchLoc.x + matchLoc.y;
		        cardPositions.add(matchLoc);
	        }
	        
	      
	      

	        
	        Imgproc.rectangle(img_display, matchLoc, new Point(matchLoc.x + templ.cols(), matchLoc.y + templ.rows()),
	                new Scalar(0, 0, 0), 2, 8, 0);
	        Imgproc.rectangle(result, matchLoc, new Point(matchLoc.x + templ.cols(), matchLoc.y + templ.rows()),
	                new Scalar(0, 0, 0), 2, 8, 0);
	        if (match_method == Imgproc.TM_SQDIFF || match_method == Imgproc.TM_SQDIFF_NORMED) {
	            matchLoc = mmr.minLoc;
		      
		   
		        
	        } else {
	            matchLoc = mmr.maxLoc;
	          
	        }
	        Image tmpImg = HighGui.toBufferedImage(img_display);
	        ImageIcon icon = new ImageIcon(tmpImg);
	        imgDisplay.setIcon(icon);
	        result.convertTo(result, CvType.CV_8UC1, 255.0);
	        tmpImg = HighGui.toBufferedImage(result);
	        icon = new ImageIcon(tmpImg);
	        resultDisplay.setIcon(icon);
	        createJFrame();
        	
        }
	    public void captureCards() {
  	        Point smallest = cardPositions.get(0);
  	        Point biggest = cardPositions.get(0);

	        for(Point x : cardPositions ){
	        	   if (x.x < smallest.x) {
	        	      smallest = x;
	        	   } else if (x.x > biggest.x) {
	        		   
	        		   biggest = x;
	        	   }
	        	   
	        	   
	        	}
	        
	        System.out.print("First Card Pos " + smallest);
	        Rect rectCrop = new Rect( (int)smallest.x, (int)smallest.y, templ.width(), templ.height());
	        Mat image_output= img.submat(rectCrop);
	        
	        Imgcodecs.imwrite("Smallest" + smallest + ".jpg", image_output);
	        Imgcodecs.imwrite("Biggest" + biggest + ".jpg", image_output);


	        
	        
//	        int handSize = (int) ((biggest.x - (smallest.x + templ.width()) ) / templ.width() + 2);
//	        System.out.print(handSize );
//	        
//	        for(int x = 0; x < handSize; x++) {
//	
//		        Rect rectCrop = new Rect( (int)smallest.x + templ.width() * x + 2 * x ,(int)smallest.y, templ.width(), templ.height());
//		        Mat image_output= img.submat(rectCrop);
//		        Imgcodecs.imwrite(x + ".jpg", image_output);
//	        }
	    	
	    	
	    }
	    public void captureCardTest(double a, double b) {

	
		      
		        
		        
		        
		        File file = new File ("TestWrite");
	

		        if(file.list().length == 0) {
		        	
		        	Rect rectCrop = new Rect( (int)a, (int)b, templ.width(), templ.height() );
	  		        Mat image_output= img.submat(rectCrop);
	  		        int redSum = sumRed(image_output, "ignore");
	  		        
	  		        if(redSum > 70000) {
	  		        	
		  		        Imgcodecs.imwrite("TestWrite\\" + a + "+" + b +"_red"   + ".jpg", image_output);

	  		        	
	  		        } else {
	  		        	
	  		        
	  		        	
		  		        Imgcodecs.imwrite("TestWrite\\" + a+"+" + b +   "_black"  + ".jpg", image_output);

	  		        }
		        	
		        }
		        
		          for (final File snap: file.listFiles()) {     
		        	  double loc = Double.parseDouble(snap.getName().substring(0, snap.getName().indexOf(".")));
		        	  if(Math.abs(loc - a) < 7) {
		  		        return;
		        	  }
		          }
		          
		        	Rect rectCrop = new Rect( (int)a, (int)b, templ.width(), templ.height() );
	  		        Mat image_output= img.submat(rectCrop);
	  		        int redSum = sumRed(image_output,"ignore"  );
	  		        
	  		        if(redSum > 70000) {
	  		        	
		  		        Imgcodecs.imwrite("TestWrite\\" + a+ "+" + b + "_red"   + ".jpg", image_output);

	  		        	
	  		        } else {
	  		        	
	  		        
	  		        	
		  		        Imgcodecs.imwrite("TestWrite\\" + a+ "+" + b +  "_black"  + ".jpg", image_output);

	  		        }

	    	
	    	
	    }
	    public static int sumRed(Mat mat, String name) {
	    	int total = 0;
	    	 for (int r = 0 ; r < mat.rows() / 2; r++) {
		            for (int c = 0; c < mat.cols() ; c++) {
		            	
		            	
		            	total += mat.get(r, c)[2];
		            	
		            }
		           }
         	System.out.println(total + " RED VAL " + name);

	    	 return total;
	    	
	    }
	    @Override
	    public void stateChanged(ChangeEvent e) {
	        JSlider source = (JSlider) e.getSource();
	        if (!source.getValueIsAdjusting()) {
	            match_method = source.getValue();
	            matchingMethod();
	        }
	    }
	    
		public static void CompareImages() throws IOException {
			
			
			   final File cardsFolder = new File("cardLib");
		        
			   
			   		for(int i = 2; i <3; i ++) {
			   			
			   			
		    		    BufferedImage img2 = ImageIO.read(new File( i + ".jpg"));
		    		   
		    		    double lowestDifference = 100;
		    		    String closestCard = "";
		    		    
	                for (final File cards : cardsFolder.listFiles()) {
	                
	          		  BufferedImage img1 = ImageIO.read(new File("cardLib\\" + cards.getName()));
	    		      int w1 = img1.getWidth();
	    		      int w2 = img2.getWidth();
	    		      int h1 = img1.getHeight();
	    		      int h2 = img2.getHeight();
	    		     
	    		         long diff = 0;
	    		         for (int j = 0; j < h1; j++) {
	    		            for (int z = 0; z < w1; z++) {
	    		               //Getting the RGB values of a pixel
	    		               int pixel1 = img1.getRGB(z, j);
	    		               Color color1 = new Color(pixel1, true);
	    		               int r1 = color1.getRed();
	    		               int g1 = color1.getGreen();
	    		               int b1 = color1.getBlue();
	    		               int pixel2 = img2.getRGB(z, j);
	    		               Color color2 = new Color(pixel2, true);
	    		               int r2 = color2.getRed();
	    		               int g2 = color2.getGreen();
	    		               int b2= color2.getBlue();
	    		               //sum of differences of RGB values of the two images
	    		               long data = Math.abs(r1-r2)+Math.abs(g1-g2)+ Math.abs(b1-b2);
	    		               diff = diff+data;
	    		            }
	    		         }
	    		         double avg = diff/(w1*h1*3);
	    		         double percentage = (avg/255)*100;
	    		         System.out.println("Difference: "+percentage + " " + cards.getName());
	                	
	    		         if(percentage < lowestDifference) {
	    		        	 lowestDifference = percentage;
	    		        	 closestCard = cards.getName();
	    		        	 
	    		         }
	                	
	                }
	                
	                System.out.println("card detected + " + closestCard);
	                
			   		}
		}
			
	
			   		public static Integer TestCompareImages(String FileName1, String FileName2) throws IOException {
						
						
					       
						  						   		
		          		  BufferedImage img1 = ImageIO.read(new File(FileName1));
					      BufferedImage img2 = ImageIO.read(new File( FileName2));
					    		   
					    		
					    		    
				                
				    		      int w1 = img1.getWidth();
				    		      int w2 = img2.getWidth();
				    		      int h1 = img1.getHeight();
				    		      int h2 = img2.getHeight();
				    		      int r1 = 0 ;
		    		               int g1 = 0;
		    		               int b1 = 0;
		    		               int r2 =0;
		    		               int g2 =0;
		    		               int b2=0;
				    		         int diff = 0;
				    		         
				    		         
				    		         for (int j = 0; j < h2; j++) {
					    		            for (int z = 0; z < w2; z++) {
					    		            	 int pixel2 = img2.getRGB(z, j);
						    		               Color color2 = new Color(pixel2, true);
						    		                r2 = color2.getRed();
						    		                g2 = color2.getGreen();
						    		                b2= color2.getBlue();
					    		            	
					    		            	
					    		            }
				    		         
				    		         }
				    		         
				    		         
				    		         for (int j = 0; j < h1; j++) {
				    		            for (int z = 0; z < w1; z++) {
				    		               //Getting the RGB values of a pixel
				    		               int pixel1 = img1.getRGB(z, j);
				    		               Color color1 = new Color(pixel1, true);
				    		                r1 = color1.getRed();
				    		                g1 = color1.getGreen();
				    		                b1 = color1.getBlue();
				    		              
				    		              
				    		            }
				    		         }
				    		         
				    		         
				    		         //sum of differences of RGB values of the two images
			    		               int data = Math.abs(r1-r2)+Math.abs(g1-g2)+ Math.abs(b1-b2);
			    		               diff = diff+data;
				    		         double avg = diff/(w1*h1*3);
				    		         double percentage = (avg/255)*100;
//				    		         System.out.println("Difference: "+diff + "           " + FileName2);
				                	
				    		   
				              return diff;
				                
				                
						   		}
						
			   		
			   		
			   		public static boolean cardIsRed(Mat mat) {
			   			
			   			
			   			
			   			
			   			return false;
			   			
			   		}
				
			   		public static long GetRGBTotal(String FileName1) throws IOException {
						
						
					       
					   		
		          		  BufferedImage img1 = ImageIO.read(new File(FileName1));
			
					    		   
					    		
					    		    
				                
				    		      int w1 = img1.getWidth();
				    		      int h1 = img1.getHeight();
				    		      long data = 0;
		    		               int pixel1 = img1.getRGB(0, 0);
		    		               int pixel2 = img1.getRGB(w1-1,0);
		    		               int pixel3 = img1.getRGB(w1-1,h1-1);
		    		               int pixel4 = img1.getRGB(0,h1-1);
		    		               
		    		               
		    		               
		    		               Color color1 = new Color(pixel1, true);
		    		               Color color2 = new Color(pixel2, true);
		    		               Color color3 = new Color(pixel3, true);
		    		               Color color4 = new Color(pixel4, true);

		    		               
		    		               
		    		               int r1 = color1.getRed();
		    		               int g1 = color1.getGreen();
		    		               int b1 = color1.getBlue();
		    		               
		    		               int r2 = color2.getRed();
		    		               int g2 = color2.getGreen();
		    		               int b2 = color2.getBlue();
		    		               
		    		               int r3 = color3.getRed();
		    		               int g3 = color3.getGreen();
		    		               int b3 = color3.getBlue();
		    		               
		    		               int r4 = color4.getRed();
		    		               int g4 = color4.getGreen();
		    		               int b4 = color4.getBlue();
				    		    
				    		     
				    		         return r3+g3+b3;
				                	
				    		   
				                
				                
						   		}

			   		public static Integer GetRedTotal(String FileName1) throws IOException {
						
						
					       
					   		
		          		  BufferedImage img1 = ImageIO.read(new File(FileName1));
			
					    		   
					    		
					    		    
				                
				    		      int w1 = img1.getWidth();
				    		      int h1 = img1.getHeight();
				    		      int data = 0;
				    		     
				    		         int diff = 0;
				    		         for (int j = 0 ; j < h1 / 2; j++) {
				    		            for (int z = 0; z < w1; z++) {
				    		               //Getting the RGB values of a pixel
				    		               int pixel1 = img1.getRGB(z, j);
				    		               Color color1 = new Color(pixel1, true);
				    		               int r1 = color1.getRed();
				    		               int g1 = color1.getGreen();
				    		               int b1 = color1.getBlue();
				    		        
				    		               //sum of differences of RGB values of the two images
				    		               data = data + r1;
				    		            }
				    		         }
				    		     
				    		         return data;
				                	
				    		   
				                
				                
						   		}
			   		
			   		
			   		
			   		static int mode(int a[]) {
			   	      int maxValue = 0, maxCount = 0, i, j;

			   	      for (i = 0; i < a.length; ++i) {
			   	         int count = 0;
			   	         for (j = 0; j < a.length; ++j) {
			   	            if (a[j] == a[i])
			   	            ++count;
			   	         }

			   	         if (count > maxCount) {
			   	            maxCount = count;
			   	            maxValue = a[i];
			   	         }
			   	      }
			   	      return maxValue;
			   	   }
		public void recaptureCards(int numcards) {
			
			
			//cards start at 50x

//			ArrayList<Integer> distance = new ArrayList<>();
//			ArrayList<Integer> distanceY = new ArrayList<>();
//
//			
//			
//			File cardsFolder = new File("TestWrite");
            int min = 1000;
//
//             for (final File cards : cardsFolder.listFiles()) {   
//            	 
//            	 //Capture X
//            	 int index = cards.getName().indexOf(".");
//            	 int d = Integer.parseInt(cards.getName().substring(0,index));
//            	  distance.add(d);
//            	  
//            	  
//            	  //Capture Y
//            		 int index2 = cards.getName().indexOf("+");
//            		 int index3 = cards.getName().indexOf("_");
//
//                	 int d2 = Integer.parseInt(cards.getName().substring(index2 + 1,index3 - 2));
//                	 distanceY.add(d2);
//            	  
//            	  //Record Min
//            	  if(d < min) {
//            		  
//            		  min  = d;
//            	  }
//            	  
//            	  
//	           }
//
//             
//             Collections.sort(distance);
//             Collections.sort(distanceY);
//
//             int[] difference = new int[distance.size()];
//             int[] difference2 = new int[distanceY.size()];
//
//             for(int i =0; i < difference.length - 1; i++) {
//            	 
//            	 
//            	 
//            	 difference[i] =   (int) (Math.ceil( (distance.get(i + 1) - distance.get(i)) / 3) * 3);
//            	 difference2[i] =   (int) (Math.ceil(distanceY.get(i) / 1) * 1);
//
//             }
//             
//             
//             int actualDistance = mode(difference);
//             int actualDistanceY = mode(difference2);

             //
            int actualDistance = 0;
            int actualDistanceY = 0;

             
             int numberOfCards = numcards;
	       
             File file = new File ("Process");
         
             
             double space = (8 - numberOfCards) / 2;
             if(numberOfCards == 8) {
                 min = 50;
            	 space = 0.5;
                 actualDistance = 72;
                 actualDistanceY = 13;
                 min = 20;

             } else if(numberOfCards == 6) {
                 min = 20;
            	 space = 0.75;
                 actualDistance = 99;
                 actualDistanceY = 15;

             }else if(numberOfCards == 4) {
            	   min = 20;
              	 space = 0.75;
                   actualDistance = 99;
                   actualDistanceY = 15;

             }
             
             for(int i = 0; i < numberOfCards; i ++) {
             
		       	Rect rectCrop = new Rect( (int) (min + actualDistance * i + space * i) , actualDistanceY, 18,39 );
	  	        Mat image_output= img.submat(rectCrop);
	  	        
	  	        
	  	      int redSum = sumRed(image_output, "" + min + actualDistance * i );
	  	      
	  	      int loc = (int) (min + actualDistance * i + space * i) ;
		       
	  	      
		        if(redSum > 70000) {
	  		        Imgcodecs.imwrite("Process\\" + i + "_Red" + ".png", image_output);

		        } else if(redSum > 64500) {
		        	
		       
	  		        Imgcodecs.imwrite("Process\\" + i + "_Neutral" + ".png", image_output);

		        	
		        } else {
		        	
		        	
	  		        Imgcodecs.imwrite("Process\\" + i  + "_Black" + ".png", image_output);

		        	
		        }
		        
	  	        
	  	        
	  	        
	  	        
    
             }
	  	            
	  		        
			
			
		}
		
	    private void createJFrame() {
	        String title = "Source image; Control; Result image";
	        JFrame frame = new JFrame(title);
	        frame.setLayout(new GridLayout(2, 2));
	        frame.add(imgDisplay);
	        int min = 0, max = 5;
	        JSlider slider = new JSlider(JSlider.VERTICAL, min, max, match_method);
	        slider.setPaintTicks(true);
	        slider.setPaintLabels(true);
	        // Set the spacing for the minor tick mark
	        slider.setMinorTickSpacing(1);
	        // Customizing the labels
	        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
	        labelTable.put(new Integer(0), new JLabel("0 - SQDIFF"));
	        labelTable.put(new Integer(1), new JLabel("1 - SQDIFF NORMED"));
	        labelTable.put(new Integer(2), new JLabel("2 - TM CCORR"));
	        labelTable.put(new Integer(3), new JLabel("3 - TM CCORR NORMED"));
	        labelTable.put(new Integer(4), new JLabel("4 - TM COEFF"));
	        labelTable.put(new Integer(5), new JLabel("5 - TM COEFF NORMED : (Method)"));
	        slider.setLabelTable(labelTable);
	        slider.addChangeListener(this);
	        frame.add(slider);
	        frame.add(resultDisplay);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.pack();
	        frame.setVisible(true);
	    }
	}

	

