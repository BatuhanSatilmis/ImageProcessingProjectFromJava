package finalpartone;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;



public class FinalPartOne extends JFrame{
	private int [][] pixels;
	private int width, height;
	private TabOne tabOne;
	private TabTwo tabTwo;
	private TabThree tabThree;
    private TabFour tabFour;
    private TabFive tabFive;
    private TabSix tabSix;
    private TabSeven tabSeven;
    private TabEight tabEight;
	private BufferedImage img = null;
    private int[][][] rgb_buffer; 
    private byte[] p;
	FinalPartOne(){
		readImage();
		JTabbedPane jtp = new JTabbedPane();
		tabOne = new TabOne();
		jtp.add("Original Image", tabOne);
		tabTwo = new TabTwo();
		jtp.add("GrayScale", tabTwo);
		tabThree = new TabThree();
		jtp.add("Binary", tabThree);
		tabFour = new TabFour();
		jtp.add("XEdge" ,tabFour);
		tabFive = new TabFive();
		jtp.add("YEdge" ,tabFive);	
		tabSix = new TabSix();
		jtp.add("XYEdge" , tabSix);	
		tabSeven = new TabSeven();
		jtp.add("HOG" , tabSeven);
		tabEight = new TabEight();
		jtp.add("Scale" ,tabEight);
		this.add(jtp);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(598,335);
		this.setVisible(true);
	}
	
	
	private void readImage() {
		
// Reading input file 
	
		
		try {
			File f = new File("circle1.jpg");
			
			
			img = ImageIO.read(f);
			width = img.getWidth();
			height = img.getHeight();
			pixels = new int[300][500];
			System.out.printf("Width : %d, height : %d ", width, height);
			Raster raster = img.getData();
			
			DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();

            p = data.getData();
			 
			for(int row = 0; row < height; row++) {
				for(int col = 0; col < width; col++) {	
					//pixels[col][row] = image.getRGB(0, 0);
					pixels[col][row] = raster.getSample(col, row, 0);
	
				}
			
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
			System.out.println("Reading complete.");
			//System.out.println(p.length);
	}
	
	class TabOne extends JPanel{
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			for(int row = 0; row < height; row++)
				for(int col = 0; col < width; col++) {
					g.setColor(new Color(pixels[col][row],
							pixels[col][row],
							pixels[col][row]));
					g.fillRect(col, row, 1, 1);
				}
		}
	}
	class TabTwo extends JPanel{
	
		@Override
		public void paintComponent(Graphics g) {
			 rgb_buffer = new int[3][img.getHeight()][img.getWidth()];
			super.paintComponent(g);
			for(int row = 0; row < height; row++) {
				for(int col = 0; col < width; col++) {
					g.setColor(new Color(pixels[col][row],
							pixels[col][row],
							pixels[col][row]));
				//	g.fillRect(col, row, 1, 1);
					g.drawImage(img, col, row , null);
				
				Color c= new Color(img.getRGB(col, row));
				rgb_buffer[0][row][col]=c.getRed();
				rgb_buffer[1][row][col]=c.getGreen();
				rgb_buffer[2][row][col]=c.getBlue();
			
				}
					
				}
	
					for(int row = 1; row < height-1; row++) {
						for(int col = 1; col < width-1; col++) {
						int r =0,gr=0,b=0;
						r = Math.min(Math.abs((rgb_buffer[0][row][col]-rgb_buffer[0][row+1][col+1])+120),255);
						gr = Math.min(Math.abs((rgb_buffer[1][row][col]-rgb_buffer[0][row+1][col+1])+120),255);		
						b = Math.min(Math.abs((rgb_buffer[2][row][col]-rgb_buffer[0][row+1][col+1])+120),255);		
								
						 Color c= new Color(r,gr,b);
						img.setRGB(col,row,c.getRGB());
						
						}
						
					}
		
				}
	}
	class TabThree extends JPanel{
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			for(int row = 0; row < height; row++)
				for(int col = 0; col <width ; col++){
					
					if(pixels[row][col] == 0)
					{
						g.setColor(new Color(1, 1, 1));
					}
					else
					{
						g.setColor(new Color(255, 255, 255));
					}
					
					g.fillRect(col, row, 1, 1);
					
				}

		}
	}
	class TabFour extends JPanel{
		@Override

		public void paintComponent(Graphics g) {
			 rgb_buffer = new int[3][img.getHeight()][img.getWidth()];
			super.paintComponent(g);
			for(int row = 0; row < height; row++) {
				for(int col = 0; col < width; col++) {
					g.setColor(new Color(pixels[col][row],
							pixels[col][row],
							pixels[col][row]));
		
					//	g.fillRect(col, row, 1, 1);
					g.drawImage(img, col, row, null);
				
				Color c= new Color(img.getRGB(col, row));
				rgb_buffer[0][row][col]=c.getRed();
				rgb_buffer[1][row][col]=c.getGreen();
				rgb_buffer[2][row][col]=c.getBlue();
			
				}
					
				}
	
					for(int row = 1; row < height-1; row++) {
						for(int col = 1; col < width-1; col++) {
						int r =0,gr=0,b=0;
						r = Math.min(Math.abs((rgb_buffer[0][row][col]-rgb_buffer[0][row][col+1])+0),256);
						gr = Math.min(Math.abs((rgb_buffer[1][row][col]-rgb_buffer[0][row][col+1])+0),256);		
						b = Math.min(Math.abs((rgb_buffer[2][row][col]-rgb_buffer[0][row][col+1])+0),256);		
								
						 Color c= new Color(r,gr,b);
						img.setRGB(col,row,c.getRGB());
						
						}
						
					}
		
				}
	}
	class TabFive extends JPanel{
		@Override

		public void paintComponent(Graphics g) {
			 rgb_buffer = new int[3][img.getHeight()][img.getWidth()];
			super.paintComponent(g);
			for(int row = 0; row < height; row++) {
				for(int col = 0; col < width; col++) {
					g.setColor(new Color(pixels[col][row],
							pixels[col][row],
							pixels[col][row]));
		
					//	g.fillRect(col, row, 1, 1);
					g.drawImage(img, col, row, null);
				
				Color c= new Color(img.getRGB(col, row));
				rgb_buffer[0][row][col]=c.getRed();
				rgb_buffer[1][row][col]=c.getGreen();
				rgb_buffer[2][row][col]=c.getBlue();
			
				}
		
				}
	
					for(int row = 1; row < height-1; row++) {
						for(int col = 1; col < width-1; col++) {
						int r =0,gr=0,b=0;
						r = Math.min(Math.abs((rgb_buffer[0][row][col]-rgb_buffer[0][row+1][col])+0),256);
						gr = Math.min(Math.abs((rgb_buffer[1][row][col]-rgb_buffer[0][row+1][col])+0),256);		
						b = Math.min(Math.abs((rgb_buffer[2][row][col]-rgb_buffer[0][row+1][col])+0),256);		
								
						 Color c= new Color(r,gr,b);
						img.setRGB(col,row,c.getRGB());
						
						}
						
					}
		
				}
	}
	class TabSix extends JPanel{
		public void paintComponent(Graphics g) {
			 rgb_buffer = new int[3][img.getHeight()][img.getWidth()];
			super.paintComponent(g);
			for(int row = 0; row < height; row++) {
				for(int col = 0; col < width; col++) {
					g.setColor(new Color(pixels[col][row],
							pixels[col][row],
							pixels[col][row]));
		
					//	g.fillRect(col, row, 1, 1);
					g.drawImage(img, col, row, null);
				
				Color c= new Color(img.getRGB(col, row));
				rgb_buffer[0][row][col]=c.getRed();
				rgb_buffer[1][row][col]=c.getGreen();
				rgb_buffer[2][row][col]=c.getBlue();
			
				}
					
				}
	
					for(int row = 2; row < height-2; row++) {
						for(int col = 2; col < width-2; col++) {
						int r =0,gr=0,b=0;
						r = Math.min(Math.abs((rgb_buffer[0][row][col]-rgb_buffer[0][row-1][col+1])+0),256);
						gr = Math.min(Math.abs((rgb_buffer[0][row][col]-rgb_buffer[0][row][col+1])+0),256);		
						b = Math.min(Math.abs((rgb_buffer[0][row][col]-rgb_buffer[0][row-1][col+1])+0),256);			
								
						 Color c= new Color(r,gr,b);
						img.setRGB(col,row,c.getRGB());
			
						}
						
					}
		
				}
	}
	class TabSeven extends JPanel{
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            int[][] filter1 = {
                    { -1,  0,  1 },
                    { -2,  0,  2 },
                    { -1,  0,  1 }
                };

                int[][] filter2 = {
                    {  1,  2,  1 },
                    {  0,  0,  0 },
                    { -1, -2, -1 }
                };
  
                Integer horizontal[] = new Integer[height];
            
                List<Integer> arrList = new ArrayList<Integer>();
        	
                for (int y = 1; y < height - 1; y++) {
                    for (int x = 1; x < width - 1; x++) {
                        int[][] gray = new int[3][3];
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                gray[i][j] = (int)(img.getRGB(x-1+i, y-1+j));
                                
                            }
                        }
                       
                        int gray1 = 0, gray2 = 0; 
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                          
                                gray1 += gray[i][j] * filter1[i][j];
                                gray2 += gray[i][j] * filter2[i][j];
         			
                            }
                        }
                       arrList.add(gray1);
  	                   arrList.add(gray2);
                       int magnitude = 255 - ((int) Math.sqrt(gray1*gray1 + gray2*gray2));
                     
                	
                       Color c = new Color((int) magnitude);
                       img.setRGB(x, y, c.getRGB());
                       
                       horizontal = arrList.toArray(horizontal);
                       g.drawLine(x, 400, x+10, 400-horizontal[x]);
                      
                    }
                    }
               
            
        }
	}
	
	class TabEight extends JPanel{
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
		         
		
	
			    g.drawImage(img, 0, 0, 125,250 , 0, 0, height , width, null);
	       	    g.dispose();
		 
				}

		}
	
		
		  
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FinalPartOne();
	}

}
