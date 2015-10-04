package ua.yandex.shad.tempseries;

import static org.junit.Assert.*;
import org.junit.Test;

public class TemperatureSeriesAnalysisTest {

    @Test(expected = IllegalArgumentException.class)
    public void testAverage_FailOnEmptyList() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        seriesAnalysis.average();
    }

    @Test
    public void testAverage_TemperatureSeriesWithSingleElement() {
        double[] temperatureSeries = {36.8};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 36.8;
        double actualResult = seriesAnalysis.average();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testAverage_TemperatureSeriesWithZeroAverage() {
        double[] temperatureSeries = {10.5, -5.5, -4.5, 0.0, -0.5};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 0.0;
        double actualResult = seriesAnalysis.average();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testAverage_TemperatureSeriesWithNotZeroAverage() {
        double[] temperatureSeries = {1.8, -7.5, -0.5, 0.0, 1.2};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;
        double actualResult = seriesAnalysis.average();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeviation_FailOnEmptyList() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        seriesAnalysis.deviation();
    }

    @Test
    public void testDeviation_TemperatureSeriesWithSingleElement() {
        double[] temperatureSeries = {-34.67};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 0;
        double actualResult = seriesAnalysis.deviation();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testDeviation_TemperatureSeriesWithZeroAverage() {
        double[] temperatureSeries = {-0.3, 0.0, 0.3};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 0.06;
        double actualResult = seriesAnalysis.deviation();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testDeviation_TemperatureSeriesWithNotZeroAverage() {
        double[] temperatureSeries = {-1, 0, 1, 4};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 3.5;
        double actualResult = seriesAnalysis.deviation();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMin_FailOnEmptyList() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        seriesAnalysis.min();
    }

    @Test
    public void testMin_TemperatureSeriesWithSingleElement() {
        double[] temperatureSeries = {23.7};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 23.7;
        double actualResult = seriesAnalysis.min();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testMin_TemperatureSeriesWithMinTemperatureOnTheLastPlace() {
        double[] temperatureSeries = {35.5, 1.0, -22.0, 0.0, 0.0, 45.89, -23.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -23.0;
        double actualResult = seriesAnalysis.min();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testMin_TemperatureSeriesWithMinTemperatureOnTheFirstPlace() {
        double[] temperatureSeries = {-33.8, 0.23, -22.5, 23.1, 23.8, -33.7};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -33.8;
        double actualResult = seriesAnalysis.min();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testMin_TemperatureSeriesWithMinTemperatureSomewhereInTheMiddle() {
        double[] temperatureSeries = {0.8, 23.0, 0.1, 12.987};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 0.1;
        double actualResult = seriesAnalysis.min();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMax_FailOnEmptyList() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        seriesAnalysis.max();
    }

    @Test
    public void testMax_TemperatureSeriesWithSingleElement() {
        double[] temperatureSeries = {-18.9};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -18.9;
        double actualResult = seriesAnalysis.max();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testMax_TemperatureSeriesWithMaxTemperatureOnTheLastPlace() {
        double[] temperatureSeries = {-15.2, 8.9, 1.8, 18.9, 18.92};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 18.92;
        double actualResult = seriesAnalysis.max();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testMax_TemperatureSeriesWithMaxTemperatureOnTheFirstPlace() {
        double[] temperatureSeries = {-1.5, -1.9, -14.4};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.5;
        double actualResult = seriesAnalysis.max();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testMax_TemperatureSeriesWithMaxTemperatureSomewhereInTheMiddle() {
        double[] temperatureSeries = {-23.0, 41.3, 41.2, -41.5, 41.3, 0.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 41.3;
        double actualResult = seriesAnalysis.max();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempClosestToZero_FailOnEmptyList() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        seriesAnalysis.findTempClosestToZero();
    }

    @Test
    public void testFindTempClosestToZero_TemperatureSeriesWithSingleElement() {
        double[] temperatureSeries = {54.78};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 54.78;
        double actualResult = seriesAnalysis.findTempClosestToZero();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToZero_TemperatureSeriesWithPositiveNumbers() {
        double[] temperatureSeries = {12.3, 0.02, 0.2, 0.08};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 0.02;
        double actualResult = seriesAnalysis.findTempClosestToZero();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToZero_TemperatureSeriesWithNegativeNumbers() {
        double[] temperatureSeries = {-18.9, -23.9, -5.675, -5.67};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -5.67;
        double actualResult = seriesAnalysis.findTempClosestToZero();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToZero_TemperatureSeriesWithZero() {
        double[] temperatureSeries = {1.3, -0.99, 0.0, 24.0, 0.0, -0.1};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 0.0;
        double actualResult = seriesAnalysis.findTempClosestToZero();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToZero_AmongPositiveAndNegativeClosestToZeroNumberReturnsPositive() {
        double[] temperatureSeries = {-0.2, 0.2, 0.25, -0.2};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 0.2;
        double actualResult = seriesAnalysis.findTempClosestToZero();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempClosestToValue_FailOnEmptyList() {
        double[] temperatureSeries = {};
        double value = 13.8;
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        seriesAnalysis.findTempClosestToValue(value);
    }

    @Test
    public void testFindTempClosestToValue_TemperatureSeriesWithSingleElement() {
        double[] temperatureSeries = {54.78};
        double value = -10.6;
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 54.78;
        double actualResult = seriesAnalysis.findTempClosestToValue(value);

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToValue_TemperatureSeriesWithAllNumbersMoreThanValue() {
        double[] temperatureSeries = {12.4, 10.2, 18.2, 10.1};
        double value = 10;
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 10.1;
        double actualResult = seriesAnalysis.findTempClosestToValue(value);

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToValue_TemperatureSeriesWithAllNumbersLessThanValue() {
        double[] temperatureSeries = {-5.3, 0.0, 1.5, -2.2};
        double value = 2.2;
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1.5;
        double actualResult = seriesAnalysis.findTempClosestToValue(value);

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToValue_TemperatureSeriesWithNumberIsEqualeToValue() {
        double[] temperatureSeries = {0.1, -0.1, -0.11, 0.2, -0.2};
        double value = -0.1;
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -0.1;
        double actualResult = seriesAnalysis.findTempClosestToValue(value);

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToValue_AmongNumbersWithSameDistanceToValueReturnBigger() {
        double[] temperatureSeries = {-3.5, -2.5, 3, -3.5, 3.5};
        double value = -3;
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -2.5;
        double actualResult = seriesAnalysis.findTempClosestToValue(value);

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToValue_ResultValueOnFirstPlace() {
        double[] temperatureSeries = {9.8, 10.25, -10, 0};
        double value = 10;
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 9.8;
        double actualResult = seriesAnalysis.findTempClosestToValue(value);

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempsLessThen_FailOnEmptyList() {
        double[] temperatureSeries = {};
        double value = 13.8;
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        seriesAnalysis.findTempsLessThen(value);
    }

    @Test
    public void testFindTempsLessThen_TemperatureSeriesWithAllTempsLessThenValue() {
        double[] temperatureSeries = {16.2, 16.23, -16, 8, 0.0};
        double value = 16.3;
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = {16.2, 16.23, -16, 8, 0.0};
        double[] actualResult = seriesAnalysis.findTempsLessThen(value);

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempsLessThen_TemperatureSeriesWithAllTempsMoreThenValue() {
        double[] temperatureSeries = {2.33, 2.7, 12.5, 2.31};
        double value = 2.3;
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = {};
        double[] actualResult = seriesAnalysis.findTempsLessThen(value);

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempsLessThen_TempSeriesWithSomeTempsEqualToValue() {
        double[] temperatureSeries = {1.2, -1.6, -3.9, -1.6};
        double value = -1.6;
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = {-3.9};
        double[] actualResult = seriesAnalysis.findTempsLessThen(value);

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempsGreaterThen_FailOnEmptyList() {
        double[] temperatureSeries = {};
        double value = -20.3;
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        seriesAnalysis.findTempsLessThen(value);
    }

    @Test
    public void testFindTempsGreaterThen_TemperatureSeriesWithAllTempsGreaterThenValue() {
        double[] temperatureSeries = {89.7, 34.4, 5.7};
        double value = 5.6;
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = {89.7, 34.4, 5.7};
        double[] actualResult = seriesAnalysis.findTempsGreaterThen(value);

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempsGreaterThen_TemperatureSeriesWithAllTempsLessThenValue() {
        double[] temperatureSeries = {-13.2, 0.0, -5.6, 5.5};
        double value = 5.6;
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = {};
        double[] actualResult = seriesAnalysis.findTempsGreaterThen(value);

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempsGreaterThen_TempSeriesWithSomeTempsEqualToValue() {
        double[] temperatureSeries = {1.2, -1.6, -3.9, -1.6};
        double value = -1.6;
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = {1.2};
        double[] actualResult = seriesAnalysis.findTempsGreaterThen(value);

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSummaryStatistics_FailOnEmptyList() {
        double[] temperatureSeries = {};
        double value = -9.12;
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        seriesAnalysis.summaryStatistics();
    }

    @Test
    public void testSummaryStatistics() {
        double[] temperatureSeries = {1.0, 0.0, -1.0, 0.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        TempSummaryStatistics sumStatistics = seriesAnalysis.summaryStatistics();

        assertEquals(0, sumStatistics.getAvgTemp(), 0.00001);
        assertEquals(0.5, sumStatistics.getDevTemp(), 0.00001);
        assertEquals(-1, sumStatistics.getMinTemp(), 0.00001);
        assertEquals(1, sumStatistics.getMaxTemp(), 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddTemps_ThrowExceptionOnTempLessThenMinTemp() {
        double[] temperatureSeries = {24, -8.7};
        double[] temps = {67, -200, -274};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        seriesAnalysis.addTemps(temps);
    }

    @Test
    public void testAddTemps_IfTempsListIsEmptyReturnTempSeriesSize() {
        double[] temperatureSeries = {17.5, 22.5, -20};
        double[] temps = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        int expResult = 3;
        int actualResult = seriesAnalysis.addTemps(temps);

        assertEquals(expResult, actualResult);
    }

    @Test
    public void testAddTemps_IfTempSeriesWasEmptyAndNewTempsAddedReturnTempsSize() {
        double[] temperatureSeries = {};
        double[] temps = {89.2, -123.3, 0, 9.9};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        int expResult = 4;
        int actualResult = seriesAnalysis.addTemps(temps);

        assertEquals(expResult, actualResult);
    }

    @Test
    public void testAddTemps_IfTempSeriesIsEmptyAndTempsIsEmptyReturnZero() {
        double[] temperatureSeries = {};
        double[] temps = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        int expResult = 0;
        int actualResult = seriesAnalysis.addTemps(temps);

        assertEquals(expResult, actualResult);
    }

    @Test
    public void testAddTemps_NewTempsMuchMoreThanLengthOfTempSeries() {
        double[] temperatureSeries = {-100.1};
        double[] temps = {0.0, 23.3, 12.0, 0.0, 0.0, 12.0, 0.0, -12.0, 13.5, 0.0, 0.0, 0.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        int expResult = 13;
        int actualResult = seriesAnalysis.addTemps(temps);

        assertEquals(expResult, actualResult);
    }
}