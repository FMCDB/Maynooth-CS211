public class Lab8{
	public static void main (String args[])
	{
		System.out.println(distance(53.381289599999995,39.0366364,-6.591849899999943,125.73091870000007));
	}
	public static double haversin (double theta){
		return (1-Math.cos(theta))/2;
	}
	public static double distance(double Lat1,double Lat2,double Long1,double Long2)
	{
		Lat1 = Math.toRadians(Lat1);
		Lat2 = Math.toRadians(Lat2);
		Long1 = Math.toRadians(Long1);
		Long2 = Math.toRadians(Long2);
		double x = haversin(Lat2 - Lat1) + (Math.cos(Lat1)*Math.cos(Lat2)*haversin(Long2-Long1)), r = 6371;
		return 2*r*Math.asin(Math.sqrt(x));
	}
}