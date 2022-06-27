package zoom;

public class Tester {

	public static void main(String[] args) {

		int [][] tbtImage = {{255,0,0},{255,255,0},{255,255,255}};
		
		int [][] xdir = {{-1,0,1},{-2,0,2},{-1,0,1}};
		int [][] ydir = {{-1,-2,-1},{0,0,0},{1,2,1}};
		
		int Gx = xdir[0][0]*tbtImage[0][0]+xdir[1][0]*tbtImage[1][0]
				+xdir[2][0]*tbtImage[2][2]+xdir[0][2]*tbtImage[0][2]
				+xdir[1][2]*tbtImage[1][2]+xdir[2][2]*tbtImage[2][2];
		
		int Gy = ydir[0][0]*tbtImage[0][0]+ydir[0][1]*tbtImage[0][1]
				+ydir[0][2]*tbtImage[0][1]+ydir[2][0]*tbtImage[2][0]
				+ydir[2][1]*tbtImage[2][1]+ydir[2][2]*tbtImage[2][2];
		
		System.out.println("Gx : "+Gx);
		System.out.println("Gy : "+Gy);
		
		System.out.println("Magnitude :" +Math.sqrt(Gx*Gx+Gy*Gy) );
		System.out.println("Orientation : " + Math.toDegrees(Math.atan(-Gy/Gx)));
		
		

	}

}
