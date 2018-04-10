
import java.awt.*;  

import javax.swing.*;

import java.util.*;

/*
@author:Vishal Singh
*/
@SuppressWarnings("serial")
class MyClock extends JFrame implements Runnable
{
	Graphics g;
	int i=0;
	int flag=0;
	JFrame f;
	int hours=0, minutes=0, seconds=0,milliseconds=0; 
	int totsec=0,totsec1=0;
	int date,month,year;
//	String day;
	
	String time;
	
	double sangle,mangle,hangle,a,a1;
	int newsx,newsy,newmx,newmy,newhx,newhy,x,y,x1,y1;					// a,x and y are for showing location of numbers
																		// a1,x1 and y1 are showing locations of dots
	
	Thread th=new Thread(this);
	public void run()
	{
		i=1;
		while(true)
		{
			Calendar cal = Calendar.getInstance();  
	        hours = cal.get( Calendar.HOUR_OF_DAY );  
	        if ( hours > 12 ) hours -= 12;  
	        minutes = cal.get( Calendar.MINUTE );  
	        seconds = cal.get( Calendar.SECOND );
	        milliseconds = cal.get( Calendar.MILLISECOND);
	        date = cal.get(Calendar.DATE);
	        month = cal.get(Calendar.MONTH);
	        year = cal.get(Calendar.YEAR);
//	        day = cal.get(Calendar.DAY_OF_WEEK);
	
	        time=""+hours+":"+minutes+":"+seconds+"     "+date+"/"+month+"/"+year;//+"     "+day;
	        
	        totsec=seconds + (minutes * 60) + (hours * 3600);			// In 12 hrs
	        totsec1=seconds + (minutes * 60);							// In 1 hour
	        										// for second needle
	        sangle=2*Math.PI * seconds / 60;							// second hand jumps every dots
//	        sangle=2*Math.PI * ((seconds*1000)+milliseconds) / 60000;	// second hand runs continues but make thread sleep(1000) as comment
	        sangle -= 0.5 * Math.PI;
	        newsx=(int) (130*Math.cos(sangle));
	        newsy=(int) (130*Math.sin(sangle));	
	        System.out.println(newsx);
	        										// for minute needle
//	        mangle=2*Math.PI * minutes / 60;							// jumps only on numeric
//	        mangle=(2*Math.PI * minutes / 60) + (2*Math.PI * (int)(seconds/15)/240);	// Update minute hand in every 15 sec
	        mangle=(2*Math.PI * totsec1 / 3600 );						// Gives accurate position to minute hand
	        mangle -= 0.5 * Math.PI;
	        newmx=(int) (90*Math.cos(mangle));
	        newmy=(int) (90*Math.sin(mangle));	
	        										// for hour needle
	//        hangle=2*Math.PI * hours / 12;							// jumps only on numeric
	//        hangle=(2*Math.PI * hours / 12) + (2*Math.PI * (int)(minutes/12)/60) ;		// update hour hand in every 12 minutes
	        hangle=(2*Math.PI * totsec / 43200);						// gives accurate position to hours hand
	        hangle -= 0.5 * Math.PI;
	        newhx=(int) (60*Math.cos(hangle));
	        newhy=(int) (60*Math.sin(hangle));
	        repaint();
	        
			try 
			{
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
		
		}
	}
	
	MyClock()
	{
		th.start();
	}
	public static void main(String s[])
	{
		MyClock f= new MyClock();
		f.setSize(500, 500);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.getContentPane().setBackground(Color.yellow);
		
	}
	
	public void drawDots(Graphics g)
	{
		for (int i=1;i<60;i++)
		{
			a1=2*Math.PI * i / 60;
			a1 -= 0.5 * Math.PI;
			x1=(int) (133*Math.cos(a1));
			y1=(int) (133*Math.sin(a1));
			
			g.drawString(".",250 + x1, 250 + y1);
			
		}
	}
	public void drawNumbers(Graphics g)
	{
		for (int i=1;i<13;i++)
		{
			a=2*Math.PI * i / 12;
			a -= 0.5 * Math.PI;
			x=(int) (130*Math.cos(a));
			y=(int) (130*Math.sin(a));
			if(i!=3 && i!=6 && i!=9 && i!=12)
			{
				g.drawString(""+i,250 + x, 250 + y);
			}
		}
		
	}
	public void paint(Graphics g)
	{
		super.paintComponents(g);
		if(i==1)
		{
			
		g.setColor(Color.green);
		g.drawOval(110, 110, 280, 280);
		g.setColor(Color.blue);
		g.drawString("Digital and Analog Clock", 30, 60);
		g.drawString("12", 246, 120);
		g.drawString("6 ", 249, 382);
		g.drawString("3", 380, 254);
		g.drawString("9", 118, 254);
		drawNumbers(g);
		drawDots(g);
		
		g.setColor(Color.red);
		g.drawLine(250, 250, 250+newsx, 250+newsy);
		g.setColor(Color.black);
		g.drawLine(250, 250, 250+newmx, 250+newmy);
		g.setColor(Color.magenta);
		g.drawLine(250, 250, 250+newhx, 250+newhy);
		g.setColor(Color.DARK_GRAY);
		g.drawString(time, 200, 450);
		
		}
		
		
	}
}

