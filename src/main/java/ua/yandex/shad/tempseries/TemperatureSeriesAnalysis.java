package ua.yandex.shad.tempseries;
import java.util.Arrays;

public class TemperatureSeriesAnalysis {    
	private final int initialSize = 10;
	private final int minTemperature = -273; 
	private int filledSize;
	private double[] temperatureSeries;
	 
    public TemperatureSeriesAnalysis() {
        temperatureSeries = new double[initialSize];
		filledSize = 0;
    }
    
    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
		this.checkBoundsForTemps(temperatureSeries);
		if(temperatureSeries.length > initialSize)	{
			this.temperatureSeries = temperatureSeries;			
		}
		else	{
			this.temperatureSeries = Arrays.copyOf(temperatureSeries, initialSize);			
		}
		filledSize = temperatureSeries.length;
    }
    
	private double sum()	{
		double sum = 0;
		for (int i=0; i<filledSize; i++)	{
			sum += temperatureSeries[i]; 
		}
		return sum;
	}
	
	private void throwExceptionIfSeriesIsEmpty()	{
		if (filledSize == 0)	{
			throw new IllegalArgumentException();
		}
	}
	
    public final double average(){  
		this.throwExceptionIfSeriesIsEmpty();	
		return this.sum()/filledSize;		
    }    
    
    public final double deviation(){
		this.throwExceptionIfSeriesIsEmpty();		
		double sumSqueredDeviations = 0;
		double average = this.average();		
		for (int i=0; i<filledSize; i++)	{
			sumSqueredDeviations += (temperatureSeries[i]-average)*(temperatureSeries[i]-average); 
		}		
		return sumSqueredDeviations/filledSize;
    }
    
    public final double min(){
		this.throwExceptionIfSeriesIsEmpty();	
        double min=temperatureSeries[0];
		for (int i=0; i<filledSize; i++)	{
			if (temperatureSeries[i] < min)	{
				min = temperatureSeries[i];
			}
		}	
		return min;
    }
     
    public final double max(){
        this.throwExceptionIfSeriesIsEmpty();	
        double max=temperatureSeries[0];
		for (int i=0; i<filledSize; i++)	{
			if (temperatureSeries[i] > max)	{
				max = temperatureSeries[i];
			}
		}	
		return max;
    }
    
    public double findTempClosestToZero(){        	
		return findTempClosestToValue(0);
    }
    
    public double findTempClosestToValue(double tempValue){
        this.throwExceptionIfSeriesIsEmpty();		
		double closestToValue = temperatureSeries[0];
		for (int i=0; i<filledSize; i++)	{
			if ((Math.abs(temperatureSeries[i] - tempValue) < Math.abs(closestToValue - tempValue)) || 
							(temperatureSeries[i]  - tempValue == Math.abs(closestToValue - tempValue) && 
																	closestToValue - tempValue<0))	{
				closestToValue = temperatureSeries[i];
			}			
		}	
		return closestToValue;
	}
    
	private int countTempsLessThen(double tempValue)	{
		int size = 0;
		for(int i=0; i<filledSize; i++)	{
			if(temperatureSeries[i] < tempValue)	{
				size++;
			}
		}
		return size;
	}
	
	private int countTempsGreaterThen(double tempValue)	{
		int size = 0;
		for(int i=0; i<filledSize; i++)	{
			if(temperatureSeries[i] > tempValue)	{
				size++;
			}
		}
		return size;
	}
	
    public double[] findTempsLessThen(double tempValue){
		this.throwExceptionIfSeriesIsEmpty();		
		double[] tempsLessThenValue = new double[this.countTempsLessThen(tempValue)];
		int index =0;
		for(int i=0; i<filledSize; i++)	{
			if(temperatureSeries[i] < tempValue)	{
				tempsLessThenValue[index] = temperatureSeries[i];
				index++;
			}
		}
		return tempsLessThenValue;
    }
    
    public double[] findTempsGreaterThen(double tempValue){
        this.throwExceptionIfSeriesIsEmpty();		
		double[] tempsGreaterThenValue = new double[this.countTempsGreaterThen(tempValue)];
		int index =0;
		for(int i=0; i<filledSize; i++)	{
			if(temperatureSeries[i] > tempValue)	{
				tempsGreaterThenValue[index] = temperatureSeries[i];
				index++;
			}
		}
		return tempsGreaterThenValue;
    }
    
    public TempSummaryStatistics summaryStatistics(){
        this.throwExceptionIfSeriesIsEmpty();
		TempSummaryStatistics summaryStatistics = 
					new TempSummaryStatistics(this.average(), this.deviation(), this.min(), this.max());
		return summaryStatistics;
    }
    
	private void checkBoundsForTemps(double ... temps)	{
		for(double temp : temps)	{
			if(temp < minTemperature)	{
				throw new IllegalArgumentException();
			}
		}
	}
	
	private void doubleArraySize()	{
		int currentSize = temperatureSeries.length;
		int newSize = currentSize*2;
		double[] newTempSeries = new double[newSize];
		newTempSeries = Arrays.copyOf(temperatureSeries, newSize);		
		temperatureSeries = newTempSeries;
	}
	
    public int addTemps(double ... temps){
		this.checkBoundsForTemps(temps);
		if (temps.length == 0)	{
			return filledSize;
		}
		for(double temp : temps)	{
			if(temperatureSeries.length - filledSize == 0)	{
				this.doubleArraySize();
			}
			temperatureSeries[filledSize] = temp;
			filledSize++;
		}	
        return filledSize;
    }
}
