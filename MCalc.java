public class MCalc implements RemoteInterface {

    @Override
    public double[][] add(double[][] A, double[][] B) throws DimensExcep {
        if (A.length == 0) {
            return new double[0][0];
        }
    
        if (A.length != B.length || A[0].length != B[0].length) {
            throw new DimensExcep("Dimensions do not match subtr");
        }
    
        int numRows = A.length;
        int numCols = A[0].length;
    
        double[][] R = new double[numRows][numCols];
    
        int numThreads = Runtime.getRuntime().availableProcessors();
        Thread[] threads = new Thread[numThreads];
    
        for (int t = 0; t < numThreads; t++) {
            final int threadIndex = t;
            threads[t] = new Thread(() -> {
                int startRow = threadIndex * numRows / numThreads;
                int endRow = (threadIndex + 1) * numRows / numThreads;
    
                for (int i = startRow; i < endRow; i++) {
                    for (int j = 0; j < numCols; j++) {
                        R[i][j] = A[i][j] + B[i][j];
                    }
                }
            });
            threads[t].start();
        }
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    
        return R;
    }

    @Override
    public double[][] sub(double[][] A, double[][] B) throws DimensExcep {
        if (A.length == 0) {
            return new double[0][0];
        }
    
        if (A.length != B.length || A[0].length != B[0].length) {
            throw new DimensExcep("Dimensions do not match subtr");
        }
    
        int numRows = A.length;
        int numCols = A[0].length;
    
        double[][] R = new double[numRows][numCols];
    
        int numThreads = Runtime.getRuntime().availableProcessors();
        Thread[] threads = new Thread[numThreads];
    
        for (int t = 0; t < numThreads; t++) {
            final int threadIndex = t;
            threads[t] = new Thread(() -> {
                int startRow = threadIndex * numRows / numThreads;
                int endRow = (threadIndex + 1) * numRows / numThreads;
    
                for (int i = startRow; i < endRow; i++) {
                    for (int j = 0; j < numCols; j++) {
                        R[i][j] = A[i][j] - B[i][j];
                    }
                }
            });
            threads[t].start();
        }
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    
        return R;
    }

    @Override
    public double[][] multi(double[][] A, double[][] B) throws DimensExcep {
        if (0 == A.length || 0 == B.length) {
            throw new DimensExcep("Dimensions do not match multi");
        }
    
        if (A[0].length != B.length) {
            throw new DimensExcep("Dimensions do not match multi");
        }
    
        int numRowsA = A.length;
        int numColsA = A[0].length;
        int numColsB = B[0].length;
    
        double[][] R = new double[numRowsA][numColsB];
        
        int numThreads = Runtime.getRuntime().availableProcessors();
        Thread[] threads = new Thread[numThreads];
        
        for (int t = 0; t < numThreads; t++) {
            final int threadIndex = t;
            threads[t] = new Thread(() -> {
                int startRow = threadIndex * numRowsA / numThreads;
                int endRow = (threadIndex + 1) * numRowsA / numThreads;
    
                for (int i = startRow; i < endRow; i++) {
                    for (int j = 0; j < numColsB; j++) {
                        R[i][j] = 0;
                        for (int n = 0; n < numColsA; n++) {
                            R[i][j] += A[i][n] * B[n][j];
                        }
                    }
                }
            });
            threads[t].start();
        }
    
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    
        return R;
    }

    @Override
    public double[][] trans(double[][] A) {
        if (A.length == 0) {
            return A;
        }
    
        int numRows = A.length;
        int numCols = A[0].length;
    
        double[][] R = new double[numCols][numRows];
    
        int numThreads = Runtime.getRuntime().availableProcessors();
        Thread[] threads = new Thread[numThreads];
    
        for (int t = 0; t < numThreads; t++) {
            final int threadIndex = t;
            threads[t] = new Thread(() -> {
                int startRow = threadIndex * numRows / numThreads;
                int endRow = (threadIndex + 1) * numRows / numThreads;
    
                for (int i = startRow; i < endRow; i++) {
                    for (int j = 0; j < numCols; j++) {
                        R[j][i] = A[i][j];
                    }
                }
            });
            threads[t].start();
        }
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    
        return R;   
    }
}
