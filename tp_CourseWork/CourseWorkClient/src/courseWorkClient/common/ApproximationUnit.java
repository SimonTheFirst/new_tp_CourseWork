package courseWorkClient.common;

public class ApproximationUnit {

    private double[][] matrix;
    private int[] mask;
 
    private  double[][] makeSystem(double[][] xyTable, int basis, int n){
        double[][] matrix = new double[basis][basis+1];
        for(int i=0; i<basis; i++){
            for(int j=0; j<basis; j++){
                matrix[i][j] = 0;
            }
        }
        for(int i=0; i<basis; i++){
            for(int j=0; j<basis; j++){
                double sumA=0, sumB = 0;
                for (int k = 0; k < n; k++)
                {
                    sumA += Math.pow(xyTable[0][k], i) * Math.pow(xyTable[0][k], j);
                    sumB += xyTable[1][k] * Math.pow(xyTable[0][k], i);
                }
                matrix[i][j] = sumA;
                matrix[i][basis] = sumB;
            }
        }
        return matrix;
    }
 
    private double[] Gauss(int rowCount, int colCount){
        int i;
        mask = new int[colCount - 1];
        for (i = 0; i < colCount - 1; i++)
            mask[i] = i;
        if (GaussDirectPass(rowCount, colCount))
        {
            double[] answer = GaussReversePass(colCount, rowCount);
            return answer;
        }
        else return null;
    }
 
    private boolean GaussDirectPass(int rowCount, int colCount){
        int i, j, k, maxId, tmpInt;
        double maxVal, tmpDouble;
        for (i = 0; i < rowCount; i++)
        {
            maxId = i;
            maxVal = matrix[i][i];
            for (j = i + 1; j < colCount - 1; j++)
                if(Math.abs(maxVal)<Math.abs(matrix[i][j]))
            {
                maxVal = matrix[i][j];
                maxId = j;
            }
            if (maxVal == 0) return false;
            if (i != maxId)
            {
                for (j = 0; j < rowCount; j++)
                {
                    tmpDouble = matrix[j][i];
                    matrix[j][i] = matrix[j][maxId];
                    matrix[j][maxId] = tmpDouble;
                }
                tmpInt = mask[i];
                mask[i] = mask[maxId];
                mask[maxId] = tmpInt;
            }
            for (j = 0; j < colCount; j++) matrix[i][j] /= maxVal;
            for (j = i + 1; j < rowCount; j++)
            {
                double tempMn = matrix[j][i];
                for (k = 0; k < colCount; k++)
                    matrix[j][k] -= matrix[i][k] * tempMn;
            }
        }
        return true;
    }
 
    private double[] GaussReversePass(int colCount, int rowCount){
        int i,j,k;
        for (i = rowCount - 1; i >= 0; i--)
            for (j = i - 1; j >= 0; j--)
            {
                double tempMn = matrix[j][i];
                for (k = 0; k < colCount; k++)
                    matrix[j][k] -= matrix[i][k] * tempMn;
            }
        double[] answer = new double[rowCount];
        for (i = 0; i < rowCount; i++) answer[mask[i]] = matrix[i][colCount - 1];
        return answer;
    }
 
    public double[] LeastSquares(double[][] xyTable, int basis, int n){
        this.matrix = makeSystem(xyTable, basis, n);
        double[] result = Gauss(basis, basis + 1);
        return result;
    }
}
