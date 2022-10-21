package ie.gmit.dip;

public enum Kernel {

	IDENTITY(new double[][] { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } }),

	EDGE_DETECTION_1(new double[][] { { -1, -1, -1 }, { -1, 8, -1 }, { -1, -1, -1 } }),

	EDGE_DETECTION_2(new double[][] { { 1, 0, -1 }, { 0, 0, 0 }, { -1, 0, 1 } }),
	
	EMBOSS(new double[][] { { -2, -1, 0 }, { -1, 1, 1 }, { 0, 1, 2 } }),
	
	LAPLACIAN(new double[][] { { 0, -1, 0 }, { -1, 4, -1 }, { 0, -1, 0 } }),

	SHARPEN(new double[][] { { 0, -1, 0 }, { -1, 5, -1 }, { 0, -1, 0 } }),

	VERTICAL_LINES(new double[][] { { -1, 2, -1 }, { -1, 2, -1 }, { -1, 2, -1 } }),

	HORIZONTAL_LINES(new double[][] { { -1, -1, -1 }, { 2, 2, 2 }, { -1, -1, -1 } }),

	DIAGONAL_45_LINES(new double[][] { { -1, -1, 2 }, { -1, 2, -1 }, { 2, -1, -1 } }),

	BLUR(new double[][] { { 0.0625, 0.125, 0.0625 }, { 0.125, 0.25, 0.125 }, { 0.0625, 0.125, 0.0625 } }),

	BOX_BLUR(new double[][] { { 0.111, 0.111, 0.111 }, { 0.111, 0.111, 0.111 }, { 0.111, 0.111, 0.111 } }),

	SOBEL_HORIZONTAL(new double[][] { { -1, -2, -1 }, { 0, 0, 0 }, { 1, 2, 1 } }),

	SOBEL_VERTICAL(new double[][] { { -1, 0, 1 }, { -2, 0, 2 }, { -1, 0, 1 } }),

	APPLY_ALL_FILTERS(new double[][] { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } });

	private final double[][] kernel;

	Kernel(double[][] kernel) {
		this.kernel = kernel;
	}

	public double[][] kernel() {
		return this.kernel;
	}

	public double GetValue(int row, int col) {
		return kernel[row][col];
	}

}
