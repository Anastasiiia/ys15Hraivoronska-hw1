package ua.yandex.shad.tempseries;

import java.util.Arrays;

public class TemperatureSeriesAnalysis {

    private static final int INITIAL_SIZE = 10;
    private static final int MIN_TEMPERATURE = -273;
    private int filledSize;
    private double[] temperatureSeries;

    public TemperatureSeriesAnalysis() {
        temperatureSeries = new double[INITIAL_SIZE];
        filledSize = 0;
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        this.checkBoundsForTemps(temperatureSeries);
        int size = Math.max(INITIAL_SIZE, temperatureSeries.length);
        this.temperatureSeries = Arrays.copyOf(temperatureSeries, size);
        filledSize = temperatureSeries.length;
    }

    private double sum() {
        double sum = 0;
        for (int i = 0; i < filledSize; i++) {
            sum += temperatureSeries[i];
        }
        return sum;
    }

    private void throwExceptionIfSeriesIsEmpty() {
        if (filledSize == 0) {
            throw new IllegalArgumentException();
        }
    }

    public final double average() {
        this.throwExceptionIfSeriesIsEmpty();
        return this.sum() / filledSize;
    }

    public final double deviation() {
        this.throwExceptionIfSeriesIsEmpty();
        double sumSqueredDeviations = 0;
        double average = this.average();
        double deviation = 0;
        for (int i = 0; i < filledSize; i++) {
            deviation = temperatureSeries[i] - average;
            sumSqueredDeviations += deviation * deviation;
        }
        return sumSqueredDeviations / filledSize;
    }

    public final double min() {
        this.throwExceptionIfSeriesIsEmpty();
        double min = temperatureSeries[0];
        for (int i = 0; i < filledSize; i++) {
            if (temperatureSeries[i] < min) {
                min = temperatureSeries[i];
            }
        }
        return min;
    }

    public final double max() {
        this.throwExceptionIfSeriesIsEmpty();
        double max = temperatureSeries[0];
        for (int i = 0; i < filledSize; i++) {
            if (temperatureSeries[i] > max) {
                max = temperatureSeries[i];
            }
        }
        return max;
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        this.throwExceptionIfSeriesIsEmpty();
        double closestToValue = temperatureSeries[0];
        double minDeviation = Math.abs(closestToValue - tempValue);
        double deviation = 0;
        for (int i = 0; i < filledSize; i++) {
            deviation = Math.abs(temperatureSeries[i] - tempValue);
            if (deviation < minDeviation
                    || (Math.abs(deviation - minDeviation) < 0.00001
                    && closestToValue - tempValue < 0)) {
                closestToValue = temperatureSeries[i];
                minDeviation = deviation;
            }
        }
        return closestToValue;
    }

    private int countTempsLessThen(double tempValue) {
        int size = 0;
        for (int i = 0; i < filledSize; i++) {
            if (temperatureSeries[i] < tempValue) {
                size++;
            }
        }
        return size;
    }

    private int countTempsGreaterThen(double tempValue) {
        int size = 0;
        for (int i = 0; i < filledSize; i++) {
            if (temperatureSeries[i] > tempValue) {
                size++;
            }
        }
        return size;
    }

    public double[] findTempsLessThen(double tempValue) {
        this.throwExceptionIfSeriesIsEmpty();
        int number = this.countTempsLessThen(tempValue);
        double[] tempsLessThenValue = new double[number];
        int index = 0;
        for (int i = 0; i < filledSize; i++) {
            if (temperatureSeries[i] < tempValue) {
                tempsLessThenValue[index] = temperatureSeries[i];
                index++;
            }
        }
        return tempsLessThenValue;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        this.throwExceptionIfSeriesIsEmpty();
        int number = this.countTempsGreaterThen(tempValue);
        double[] tempsGreaterThenValue = new double[number];
        int index = 0;
        for (int i = 0; i < filledSize; i++) {
            if (temperatureSeries[i] > tempValue) {
                tempsGreaterThenValue[index] = temperatureSeries[i];
                index++;
            }
        }
        return tempsGreaterThenValue;
    }

    public TempSummaryStatistics summaryStatistics() {
        this.throwExceptionIfSeriesIsEmpty();
        TempSummaryStatistics summaryStatistics =
                new TempSummaryStatistics(this.average(), this.deviation(),
                this.min(), this.max());
        return summaryStatistics;
    }

    private void checkBoundsForTemps(double... temps) {
        for (double temp : temps) {
            if (temp < MIN_TEMPERATURE) {
                throw new IllegalArgumentException();
            }
        }
    }

    private void doubleArraySize() {
        int currentSize = temperatureSeries.length;
        int newSize = currentSize * 2;
        double[] newTempSeries = Arrays.copyOf(temperatureSeries, newSize);
        temperatureSeries = newTempSeries;
    }

    public int addTemps(double... temps) {
        this.checkBoundsForTemps(temps);
        if (temps.length == 0) {
            return filledSize;
        }
        for (double temp : temps) {
            if (temperatureSeries.length - filledSize == 0) {
                this.doubleArraySize();
            }
            temperatureSeries[filledSize] = temp;
            filledSize++;
        }
        return filledSize;
    }
}
