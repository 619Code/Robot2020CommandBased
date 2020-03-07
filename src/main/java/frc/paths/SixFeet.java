package frc.paths;

import com.team319.trajectory.Path;

public class SixFeet extends Path {
   // dt,x,y,left.pos,left.vel,left.acc,left.jerk,center.pos,center.vel,center.acc,center.jerk,right.pos,right.vel,right.acc,right.jerk,heading
	private static final double[][] points = {
				{0.0200,0.0040,0.0000,0.0040,0.2000,10.0000,0.0000,0.0040,0.2000,10.0000,0.0000,0.0040,0.2000,10.0000,0.0000,0.0000},
				{0.0200,0.0120,0.0000,0.0120,0.4000,10.0000,-0.0000,0.0120,0.4000,10.0000,0.0000,0.0120,0.4000,10.0000,-0.0000,0.0000},
				{0.0200,0.0240,0.0000,0.0240,0.6000,10.0000,0.0000,0.0240,0.6000,10.0000,0.0000,0.0240,0.6000,10.0000,0.0000,0.0000},
				{0.0200,0.0400,0.0000,0.0400,0.8000,10.0000,0.0000,0.0400,0.8000,10.0000,0.0000,0.0400,0.8000,10.0000,0.0000,0.0000},
				{0.0200,0.0600,0.0000,0.0600,1.0000,10.0000,-0.0000,0.0600,1.0000,10.0000,0.0000,0.0600,1.0000,10.0000,-0.0000,0.0000},
				{0.0200,0.0840,0.0000,0.0840,1.2000,10.0000,-0.0000,0.0840,1.2000,10.0000,0.0000,0.0840,1.2000,10.0000,-0.0000,0.0000},
				{0.0200,0.1120,0.0000,0.1120,1.4000,10.0000,0.0000,0.1120,1.4000,10.0000,0.0000,0.1120,1.4000,10.0000,0.0000,0.0000},
				{0.0200,0.1440,0.0000,0.1440,1.6000,10.0000,0.0000,0.1440,1.6000,10.0000,0.0000,0.1440,1.6000,10.0000,0.0000,0.0000},
				{0.0200,0.1800,0.0000,0.1800,1.8000,10.0000,-0.0000,0.1800,1.8000,10.0000,0.0000,0.1800,1.8000,10.0000,-0.0000,0.0000},
				{0.0200,0.2200,0.0000,0.2200,2.0000,10.0000,-0.0000,0.2200,2.0000,10.0000,0.0000,0.2200,2.0000,10.0000,-0.0000,0.0000},
				{0.0200,0.2640,0.0000,0.2640,2.2000,10.0000,0.0000,0.2640,2.2000,10.0000,0.0000,0.2640,2.2000,10.0000,0.0000,0.0000},
				{0.0200,0.3120,0.0000,0.3120,2.4000,10.0000,0.0000,0.3120,2.4000,10.0000,0.0000,0.3120,2.4000,10.0000,0.0000,0.0000},
				{0.0200,0.3640,0.0000,0.3640,2.6000,10.0000,0.0000,0.3640,2.6000,10.0000,0.0000,0.3640,2.6000,10.0000,0.0000,0.0000},
				{0.0200,0.4200,0.0000,0.4200,2.8000,10.0000,0.0000,0.4200,2.8000,10.0000,0.0000,0.4200,2.8000,10.0000,0.0000,0.0000},
				{0.0200,0.4800,0.0000,0.4800,3.0000,10.0000,-0.0000,0.4800,3.0000,10.0000,0.0000,0.4800,3.0000,10.0000,-0.0000,0.0000},
				{0.0200,0.5440,0.0000,0.5440,3.2000,10.0000,-0.0000,0.5440,3.2000,10.0000,0.0000,0.5440,3.2000,10.0000,-0.0000,0.0000},
				{0.0200,0.6120,0.0000,0.6120,3.4000,10.0000,0.0000,0.6120,3.4000,10.0000,0.0000,0.6120,3.4000,10.0000,0.0000,0.0000},
				{0.0200,0.6840,0.0000,0.6840,3.6000,10.0000,0.0000,0.6840,3.6000,10.0000,0.0000,0.6840,3.6000,10.0000,0.0000,0.0000},
				{0.0200,0.7600,0.0000,0.7600,3.8000,10.0000,-0.0000,0.7600,3.8000,10.0000,0.0000,0.7600,3.8000,10.0000,-0.0000,0.0000},
				{0.0200,0.8400,0.0000,0.8400,4.0000,10.0000,-0.0000,0.8400,4.0000,10.0000,0.0000,0.8400,4.0000,10.0000,-0.0000,0.0000},
				{0.0200,0.9200,0.0000,0.9200,4.0000,0.0000,-500.0000,0.9200,4.0000,10.0000,0.0000,0.9200,4.0000,0.0000,-500.0000,0.0000},
				{0.0200,1.0000,0.0000,1.0000,4.0000,-0.0000,-0.0000,1.0000,4.0000,10.0000,0.0000,1.0000,4.0000,-0.0000,-0.0000,0.0000},
				{0.0200,1.0800,0.0000,1.0800,4.0000,0.0000,0.0000,1.0800,4.0000,10.0000,0.0000,1.0800,4.0000,0.0000,0.0000,0.0000},
				{0.0200,1.1600,0.0000,1.1600,4.0000,0.0000,-0.0000,1.1600,4.0000,10.0000,0.0000,1.1600,4.0000,0.0000,-0.0000,0.0000},
				{0.0200,1.2400,0.0000,1.2400,4.0000,-0.0000,-0.0000,1.2400,4.0000,10.0000,0.0000,1.2400,4.0000,-0.0000,-0.0000,0.0000},
				{0.0200,1.3200,0.0000,1.3200,4.0000,0.0000,0.0000,1.3200,4.0000,10.0000,0.0000,1.3200,4.0000,0.0000,0.0000,0.0000},
				{0.0200,1.4000,0.0000,1.4000,4.0000,-0.0000,-0.0000,1.4000,4.0000,10.0000,0.0000,1.4000,4.0000,-0.0000,-0.0000,0.0000},
				{0.0200,1.4800,0.0000,1.4800,4.0000,0.0000,0.0000,1.4800,4.0000,10.0000,0.0000,1.4800,4.0000,0.0000,0.0000,0.0000},
				{0.0200,1.5600,0.0000,1.5600,4.0000,0.0000,-0.0000,1.5600,4.0000,10.0000,0.0000,1.5600,4.0000,0.0000,-0.0000,0.0000},
				{0.0200,1.6400,0.0000,1.6400,4.0000,0.0000,0.0000,1.6400,4.0000,10.0000,0.0000,1.6400,4.0000,0.0000,0.0000,0.0000},
				{0.0200,1.7200,0.0000,1.7200,4.0000,-0.0000,-0.0000,1.7200,4.0000,10.0000,0.0000,1.7200,4.0000,-0.0000,-0.0000,0.0000},
				{0.0200,1.8000,0.0000,1.8000,4.0000,0.0000,0.0000,1.8000,4.0000,10.0000,0.0000,1.8000,4.0000,0.0000,0.0000,0.0000},
				{0.0200,1.8800,0.0000,1.8800,4.0000,0.0000,0.0000,1.8800,4.0000,10.0000,0.0000,1.8800,4.0000,0.0000,0.0000,0.0000},
				{0.0200,1.9600,0.0000,1.9600,4.0000,0.0000,0.0000,1.9600,4.0000,10.0000,0.0000,1.9600,4.0000,0.0000,0.0000,0.0000},
				{0.0200,2.0400,0.0000,2.0400,4.0000,-0.0000,-0.0000,2.0400,4.0000,10.0000,0.0000,2.0400,4.0000,-0.0000,-0.0000,0.0000},
				{0.0200,2.1200,0.0000,2.1200,4.0000,0.0000,0.0000,2.1200,4.0000,10.0000,0.0000,2.1200,4.0000,0.0000,0.0000,0.0000},
				{0.0200,2.2000,0.0000,2.2000,4.0000,0.0000,-0.0000,2.2000,4.0000,10.0000,0.0000,2.2000,4.0000,0.0000,-0.0000,0.0000},
				{0.0200,2.2800,0.0000,2.2800,4.0000,-0.0000,-0.0000,2.2800,4.0000,10.0000,0.0000,2.2800,4.0000,-0.0000,-0.0000,0.0000},
				{0.0200,2.3600,0.0000,2.3600,4.0000,0.0000,0.0000,2.3600,4.0000,10.0000,0.0000,2.3600,4.0000,0.0000,0.0000,0.0000},
				{0.0200,2.4400,0.0000,2.4400,4.0000,-0.0000,-0.0000,2.4400,4.0000,10.0000,0.0000,2.4400,4.0000,-0.0000,-0.0000,0.0000},
				{0.0200,2.5200,0.0000,2.5200,4.0000,0.0000,0.0000,2.5200,4.0000,10.0000,0.0000,2.5200,4.0000,0.0000,0.0000,0.0000},
				{0.0200,2.6000,0.0000,2.6000,4.0000,0.0000,0.0000,2.6000,4.0000,10.0000,0.0000,2.6000,4.0000,0.0000,0.0000,0.0000},
				{0.0200,2.6800,0.0000,2.6800,4.0000,-0.0000,-0.0000,2.6800,4.0000,10.0000,0.0000,2.6800,4.0000,-0.0000,-0.0000,0.0000},
				{0.0200,2.7600,0.0000,2.7600,4.0000,-0.0000,0.0000,2.7600,4.0000,10.0000,0.0000,2.7600,4.0000,-0.0000,0.0000,0.0000},
				{0.0200,2.8400,0.0000,2.8400,4.0000,0.0000,0.0000,2.8400,4.0000,10.0000,0.0000,2.8400,4.0000,0.0000,0.0000,0.0000},
				{0.0200,2.9200,0.0000,2.9200,4.0000,0.0000,-0.0000,2.9200,4.0000,10.0000,0.0000,2.9200,4.0000,0.0000,-0.0000,0.0000},
				{0.0200,3.0000,0.0000,3.0000,4.0000,0.0000,0.0000,3.0000,4.0000,10.0000,0.0000,3.0000,4.0000,0.0000,0.0000,0.0000},
				{0.0200,3.0800,0.0000,3.0800,4.0000,0.0000,0.0000,3.0800,4.0000,10.0000,0.0000,3.0800,4.0000,0.0000,0.0000,0.0000},
				{0.0200,3.1600,0.0000,3.1600,4.0000,-0.0000,-0.0000,3.1600,4.0000,10.0000,0.0000,3.1600,4.0000,-0.0000,-0.0000,0.0000},
				{0.0200,3.2400,0.0000,3.2400,4.0000,0.0000,0.0000,3.2400,4.0000,10.0000,0.0000,3.2400,4.0000,0.0000,0.0000,0.0000},
				{0.0200,3.3200,0.0000,3.3200,4.0000,-0.0000,-0.0000,3.3200,4.0000,10.0000,0.0000,3.3200,4.0000,-0.0000,-0.0000,0.0000},
				{0.0200,3.4000,0.0000,3.4000,4.0000,-0.0000,0.0000,3.4000,4.0000,10.0000,0.0000,3.4000,4.0000,-0.0000,0.0000,0.0000},
				{0.0200,3.4800,0.0000,3.4800,4.0000,0.0000,0.0000,3.4800,4.0000,10.0000,0.0000,3.4800,4.0000,0.0000,0.0000,0.0000},
				{0.0200,3.5600,0.0000,3.5600,4.0000,-0.0000,-0.0000,3.5600,4.0000,10.0000,0.0000,3.5600,4.0000,-0.0000,-0.0000,0.0000},
				{0.0200,3.6400,0.0000,3.6400,4.0000,0.0000,0.0000,3.6400,4.0000,10.0000,0.0000,3.6400,4.0000,0.0000,0.0000,0.0000},
				{0.0200,3.7200,0.0000,3.7200,4.0000,0.0000,-0.0000,3.7200,4.0000,10.0000,0.0000,3.7200,4.0000,0.0000,-0.0000,0.0000},
				{0.0200,3.8000,0.0000,3.8000,4.0000,0.0000,0.0000,3.8000,4.0000,10.0000,0.0000,3.8000,4.0000,0.0000,0.0000,0.0000},
				{0.0200,3.8800,0.0000,3.8800,4.0000,-0.0000,-0.0000,3.8800,4.0000,10.0000,0.0000,3.8800,4.0000,-0.0000,-0.0000,0.0000},
				{0.0200,3.9600,0.0000,3.9600,4.0000,0.0000,0.0000,3.9600,4.0000,10.0000,0.0000,3.9600,4.0000,0.0000,0.0000,0.0000},
				{0.0200,4.0400,0.0000,4.0400,4.0000,-0.0000,-0.0000,4.0400,4.0000,10.0000,0.0000,4.0400,4.0000,-0.0000,-0.0000,0.0000},
				{0.0200,4.1200,0.0000,4.1200,4.0000,0.0000,0.0000,4.1200,4.0000,10.0000,0.0000,4.1200,4.0000,0.0000,0.0000,0.0000},
				{0.0200,4.2000,0.0000,4.2000,4.0000,0.0000,-0.0000,4.2000,4.0000,10.0000,0.0000,4.2000,4.0000,0.0000,-0.0000,0.0000},
				{0.0200,4.2800,0.0000,4.2800,4.0000,0.0000,0.0000,4.2800,4.0000,10.0000,0.0000,4.2800,4.0000,0.0000,0.0000,0.0000},
				{0.0200,4.3600,0.0000,4.3600,4.0000,0.0000,0.0000,4.3600,4.0000,10.0000,0.0000,4.3600,4.0000,0.0000,0.0000,0.0000},
				{0.0200,4.4400,0.0000,4.4400,4.0000,0.0000,0.0000,4.4400,4.0000,10.0000,0.0000,4.4400,4.0000,0.0000,0.0000,0.0000},
				{0.0200,4.5200,0.0000,4.5200,4.0000,0.0000,0.0000,4.5200,4.0000,10.0000,0.0000,4.5200,4.0000,0.0000,0.0000,0.0000},
				{0.0200,4.6000,0.0000,4.6000,4.0000,-0.0000,-0.0000,4.6000,4.0000,10.0000,0.0000,4.6000,4.0000,-0.0000,-0.0000,0.0000},
				{0.0200,4.6800,0.0000,4.6800,4.0000,0.0000,0.0000,4.6800,4.0000,10.0000,0.0000,4.6800,4.0000,0.0000,0.0000,0.0000},
				{0.0200,4.7600,0.0000,4.7600,4.0000,-0.0000,-0.0000,4.7600,4.0000,10.0000,0.0000,4.7600,4.0000,-0.0000,-0.0000,0.0000},
				{0.0200,4.8400,0.0000,4.8400,4.0000,-0.0000,0.0000,4.8400,4.0000,10.0000,0.0000,4.8400,4.0000,-0.0000,0.0000,0.0000},
				{0.0200,4.9200,0.0000,4.9200,4.0000,0.0000,0.0000,4.9200,4.0000,10.0000,0.0000,4.9200,4.0000,0.0000,0.0000,0.0000},
				{0.0200,5.0000,0.0000,5.0000,4.0000,-0.0000,-0.0000,5.0000,4.0000,10.0000,0.0000,5.0000,4.0000,-0.0000,-0.0000,0.0000},
				{0.0200,5.0800,0.0000,5.0800,4.0000,0.0000,0.0000,5.0800,4.0000,10.0000,0.0000,5.0800,4.0000,0.0000,0.0000,0.0000},
				{0.0200,5.1600,0.0000,5.1600,4.0000,0.0000,0.0000,5.1600,4.0000,10.0000,0.0000,5.1600,4.0000,0.0000,0.0000,0.0000},
				{0.0200,5.2400,0.0000,5.2400,4.0000,-0.0000,-0.0000,5.2400,4.0000,10.0000,0.0000,5.2400,4.0000,-0.0000,-0.0000,0.0000},
				{0.0200,5.3160,0.0000,5.3160,3.8000,-10.0000,-500.0000,5.3160,3.8000,-10.0000,0.0000,5.3160,3.8000,-10.0000,-500.0000,0.0000},
				{0.0200,5.3880,0.0000,5.3880,3.6000,-10.0000,0.0000,5.3880,3.6000,-10.0000,0.0000,5.3880,3.6000,-10.0000,0.0000,0.0000},
				{0.0200,5.4560,0.0000,5.4560,3.4000,-10.0000,-0.0000,5.4560,3.4000,-10.0000,0.0000,5.4560,3.4000,-10.0000,-0.0000,0.0000},
				{0.0200,5.5200,0.0000,5.5200,3.2000,-10.0000,0.0000,5.5200,3.2000,-10.0000,0.0000,5.5200,3.2000,-10.0000,0.0000,0.0000},
				{0.0200,5.5800,0.0000,5.5800,3.0000,-10.0000,-0.0000,5.5800,3.0000,-10.0000,0.0000,5.5800,3.0000,-10.0000,-0.0000,0.0000},
				{0.0200,5.6360,0.0000,5.6360,2.8000,-10.0000,0.0000,5.6360,2.8000,-10.0000,0.0000,5.6360,2.8000,-10.0000,0.0000,0.0000},
				{0.0200,5.6880,0.0000,5.6880,2.6000,-10.0000,-0.0000,5.6880,2.6000,-10.0000,0.0000,5.6880,2.6000,-10.0000,-0.0000,0.0000},
				{0.0200,5.7360,0.0000,5.7360,2.4000,-10.0000,0.0000,5.7360,2.4000,-10.0000,0.0000,5.7360,2.4000,-10.0000,0.0000,0.0000},
				{0.0200,5.7800,0.0000,5.7800,2.2000,-10.0000,-0.0000,5.7800,2.2000,-10.0000,0.0000,5.7800,2.2000,-10.0000,-0.0000,0.0000},
				{0.0200,5.8200,0.0000,5.8200,2.0000,-10.0000,0.0000,5.8200,2.0000,-10.0000,0.0000,5.8200,2.0000,-10.0000,0.0000,0.0000},
				{0.0200,5.8560,0.0000,5.8560,1.8000,-10.0000,-0.0000,5.8560,1.8000,-10.0000,0.0000,5.8560,1.8000,-10.0000,-0.0000,0.0000},
				{0.0200,5.8880,0.0000,5.8880,1.6000,-10.0000,-0.0000,5.8880,1.6000,-10.0000,0.0000,5.8880,1.6000,-10.0000,-0.0000,0.0000},
				{0.0200,5.9160,0.0000,5.9160,1.4000,-10.0000,0.0000,5.9160,1.4000,-10.0000,0.0000,5.9160,1.4000,-10.0000,0.0000,0.0000},
				{0.0200,5.9400,0.0000,5.9400,1.2000,-10.0000,-0.0000,5.9400,1.2000,-10.0000,0.0000,5.9400,1.2000,-10.0000,-0.0000,0.0000},
				{0.0200,5.9600,0.0000,5.9600,1.0000,-10.0000,-0.0000,5.9600,1.0000,-10.0000,0.0000,5.9600,1.0000,-10.0000,-0.0000,0.0000},
				{0.0200,5.9760,0.0000,5.9760,0.8000,-10.0000,0.0000,5.9760,0.8000,-10.0000,0.0000,5.9760,0.8000,-10.0000,0.0000,0.0000},
				{0.0200,5.9880,0.0000,5.9880,0.6000,-10.0000,-0.0000,5.9880,0.6000,-10.0000,0.0000,5.9880,0.6000,-10.0000,-0.0000,0.0000},
				{0.0200,5.9960,0.0000,5.9960,0.4000,-10.0000,0.0000,5.9960,0.4000,-10.0000,0.0000,5.9960,0.4000,-10.0000,0.0000,0.0000},
				{0.0200,6.0000,0.0000,6.0000,0.2000,-10.0000,-0.0000,6.0000,0.2000,-10.0000,0.0000,6.0000,0.2000,-10.0000,-0.0000,0.0000},
				{0.0200,6.0000,0.0000,6.0000,0.0000,-10.0000,0.0000,6.0000,-0.0000,-10.0000,0.0000,6.0000,0.0000,-10.0000,0.0000,0.0000},

	    };

	@Override
	public double[][] getPath() {
	    return points;
	}
}