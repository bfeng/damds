package edu.indiana.soic.spidal.damds;

import edu.indiana.sice.dscspidal.mpicommonio.SparseMatrix;
import edu.indiana.soic.spidal.common.Range;
import edu.indiana.soic.spidal.common.TransformationFunction;
import edu.indiana.soic.spidal.common.WeightsWrap1D;

public class SparseWeightWrap1D extends WeightsWrap1D {
    private SparseMatrix distanceMatrix;
    private double scoreWeight;

    public SparseWeightWrap1D(short[] weights, short[] distances, boolean isSammon, int globalColCount) {
        super(weights, distances, isSammon, globalColCount);
    }

    public SparseWeightWrap1D(double[] simpleWeights, Range rowRange, short[] distances, boolean isSammon, int globalColCount, TransformationFunction function) {
        super(simpleWeights, rowRange, distances, isSammon, globalColCount, function);
    }

    public SparseWeightWrap1D(double scoreWeight, SparseMatrix distanceMatrix, boolean isSammon, int globalColCount) {
        super(null, null, isSammon, globalColCount);
        this.scoreWeight = scoreWeight;
        this.distanceMatrix = distanceMatrix;
    }

    @Override
    public double getWeight(int i, int j) {
        if (distanceMatrix == null) {
            return super.getWeight(i, j);
        } else {
            double w = 0;
            if (i == j) {
                w = scoreWeight;
            } else {
                double distance = distanceMatrix.get(i, j);
                if (distance != 0) {
                    w = scoreWeight;
                }
            }
            return w;
        }
    }

    @Override
    public void setAvgDistForSammon(double avgDist) {
        super.setAvgDistForSammon(avgDist);
    }
}
