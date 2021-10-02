import java.io.File;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
public class MakeCards {
	

	 
	 public static void main( String[] args )
	    {
	        try{
	            System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
	            //´óˆD(Ä¸ˆD)
	            
	            final File cardsFolder = new File("cards");
	            final File lettersFolder = new File("letters");
	            final File suiteFolder = new File("suite");

	            
	                for (final File card : cardsFolder.listFiles()) {

	                    if (cardsFolder.isDirectory()) {
	                    	
	                    	for (final File suite : suiteFolder .listFiles()) {

	                    	for (final File letter : lettersFolder .listFiles()) {
	                    			                    		
	                    		
	                    		int cardtypeIndex = card.getName().indexOf("_");

                				int cardNumberIndex = letter.getName().indexOf("_");

                				int cardNameIndex = card.getName().indexOf("_", cardtypeIndex + 1);
                				int fileTypeIndex = card.getName().indexOf(".", cardNameIndex + 1);
                				int cardSuiteIndex = suite.getName().indexOf(".");


                				String cardSuite = suite.getName().substring(0 , cardSuiteIndex);
                				

                				String cardtype = card.getName().substring(cardtypeIndex + 1 , cardNameIndex);

                				String cardName = card.getName().substring(cardNameIndex + 1 , fileTypeIndex);
                				
                				
                				int cardNumber = Integer.parseInt(letter.getName().substring(0,cardNumberIndex));
                				
                				Card c = new Card(cardName, cardNumber, cardSuite, cardtype);
	                    		
	                    		if(suite.getName().equals("spade.png") ||suite.getName().equals("club.png") ) {
	                    			
	                    			if(letter.getName().contains("Black")) {		
	                    				System.out.println("Making " + c.cardName+ " " + c.cardNumber + " " + c.cardSuite + " " + c.cardType );
		                    		makecards(card.getName(),suite.getName() ,letter.getName(),c);
	                    			}

	                    		} else {

                    				System.out.println("Making " + c.cardName+ " " + c.cardNumber + " " + c.cardSuite + " " + c.cardType );
			                    		makecards(card.getName(),suite.getName() ,letter.getName(),c);

	                    		}

	                    		
	                    	}
	                    	
	                    	}
	                    } 
	                }
	         
	         
	        
	        }catch (Exception e) {
	            System.out.println("error: " + e.getMessage());
	        }
	    }
	 
	 
	 public static void testMakingCards(){
		 
		 
	 }
	 
	 public static void makecards(String cardName, String suitName, String numName, Card c) {
		 
	       
         Mat source = Imgcodecs.imread("cards\\" + cardName ,Imgcodecs.IMREAD_COLOR);
         
         //Ð¡ˆD(×ÓˆD)
          Mat cardNumberFile = Imgcodecs.imread("letters\\" + numName,Imgcodecs.IMREAD_COLOR);	         
          Mat cardSuiteFile = Imgcodecs.imread("suite\\" + suitName ,Imgcodecs.IMREAD_COLOR);
          Mat cardsuiteTemp = new Mat(cardSuiteFile.rows(), cardSuiteFile.cols(), CvType.CV_8UC4);
          Mat cardNumberTmp = new Mat(cardNumberFile.rows(), cardNumberFile.cols(), CvType.CV_8UC4);
          Mat cardResult = new Mat(source.rows(), source.cols(), CvType.CV_8UC4);

	  //Make card number transparent by adding it to a rgba mat
	
	      for(int row =0; row < cardNumberTmp.rows(); row++) {
		           for(int col =0; col < cardNumberTmp.cols(); col++) {
		           cardNumberTmp.row(row).col(col).setTo(new Scalar(cardNumberFile.get(row,col)[0],cardNumberFile.get(row,col)[1],cardNumberFile.get(row,col)[2],255));
	
		         }
		            	
		  }


     for(int row =0; row < cardNumberTmp.rows(); row++) {
	            	for(int col =0; col < cardNumberTmp.cols(); col++) {
	            		if(! (cardNumberTmp.get(row,col)[0] < 90)) {
	            			cardNumberTmp.row(row).col(col).setTo(new Scalar(255,255,255,0));
	            		}
	            	}  
     }

         
   for(int row =0; row < cardsuiteTemp.rows(); row++) {
   	
   	for(int col =0; col < cardsuiteTemp.cols(); col++) {
   		
   			cardsuiteTemp.row(row).col(col).setTo(new Scalar(cardSuiteFile.get(row,col)[0],cardSuiteFile.get(row,col)[1],cardSuiteFile.get(row,col)[2],255));

   	}
   	
   }                    



	for(int row =0; row < cardsuiteTemp.rows(); row++) {
	      	
	      	for(int col =0; col < cardsuiteTemp.cols(); col++) {
	      		
	      		if(! (cardsuiteTemp.get(row,col)[0] < 60)) {
	      			
	      			cardsuiteTemp.row(row).col(col).setTo(new Scalar(255,255,255,0));
	      		
	      		}
	
	      	}
	      	
	      }


	//Make the card itself transparent too
	
	
	for(int row =0; row < cardResult.rows(); row++) {
		
		for(int col =0; col < cardResult.cols(); col++) {
			
			cardResult.row(row).col(col).setTo(new Scalar(source.get(row,col)[0],source.get(row,col)[1],source.get(row,col)[2],255));
	
		}
		
	}
	
		//Merge the two, only adding pixels that are not transparent
		for(int row =0; row < cardNumberTmp.rows(); row++) {
			
			for(int col =0; col < cardNumberTmp.cols(); col++) {
				
				if(cardNumberTmp.get(row,col)[3]!=0)
				cardResult.row(row + 7).col(col + 10).setTo(new Scalar(cardNumberTmp.get(row,col)[0],cardNumberTmp.get(row,col)[1],cardNumberTmp.get(row,col)[2],255));
			}
			
		}
	
	
	
		for(int row =0; row < cardsuiteTemp.rows(); row++) {
		  	
		  	for(int col =0; col < cardsuiteTemp.cols(); col++) {
		  		
		  		if(cardsuiteTemp.get(row,col)[3]!=0)
		  			cardResult.row(row + 26).col(col + 9).setTo(new Scalar(cardsuiteTemp.get(row,col)[0],cardsuiteTemp.get(row,col)[1],cardsuiteTemp.get(row,col)[2],255));
		  		
		  	}
		  }


        Mat cardID = new Mat(cardsuiteTemp.rows() + cardNumberTmp.rows() , Math.max(cardsuiteTemp.cols(), cardNumberTmp.cols()), CvType.CV_8UC4, new Scalar(255,255,255,255));
		int padding = (cardID.cols() - cardNumberTmp.cols()) / 2;

	for(int row =0; row < cardsuiteTemp.rows(); row++) {
		  	
		  	for(int col =0; col < cardsuiteTemp.cols(); col++) {
		  		
		  			cardID.row(row).col(col).setTo(new Scalar(cardsuiteTemp.get(row,col)[0],cardsuiteTemp.get(row,col)[1],cardsuiteTemp.get(row,col)[2],cardsuiteTemp.get(row,col)[3]));
		  		
		  	}
		  }

	for(int row =0; row < cardNumberTmp.rows(); row++) {

		for(int col =0; col < cardNumberTmp.cols(); col++) {
			
			cardID.row(row + cardsuiteTemp.rows()).col(col + padding).setTo(new Scalar(cardNumberTmp.get(row,col)[0],cardNumberTmp.get(row,col)[1],cardNumberTmp.get(row,col)[2],cardNumberTmp.get(row,col)[3]));
		}
		
	}
		
			Imgcodecs.imwrite("cardID\\" + c.cardSuite + c.cardNumber + ".png", cardID);
//			Imgcodecs.imwrite("cardID\\" + c.cardNumber + ".png", cardNumberTmp);

			
			//Imgcodecs.imwrite("output\\" + c.cardName + "_" + c.cardNumber + "_" + c.cardSuite + ".png" , cardResult);       
         
		 
	 }

}
