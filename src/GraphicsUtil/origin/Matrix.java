package GraphicsUtil.origin;


public class Matrix {
	private double[][] array; 		//矩阵是一个二维数组
	private int size_r,size_c;	//矩阵的行数，列数
	/**
	 * 默认的构造方法
	 */
	public  Matrix(){
		this.array = null;
		this.size_r = 0;
		this.size_c = 0;
	}
	/**
	 * 将二维数组构造为矩阵
	 * @param arg 二维数组
	 */
	public  Matrix(double [][]arg){
		this.array = arg;
		this.size_r = arg.length;
		this.size_c = arg[0].length;
	}
	/**
	 * 构造一个已知行列数的矩阵
	 * @param r 行数
	 * @param c 列数
	 */
	public  Matrix(int r,int c){	//本题中是供内部返回结果矩阵使用的
		this.array =new double[r][c];
		this.size_r = r;
		this.size_c = c;
	}
	/**
	 * 矩阵加法
	 * @param arg1
	 * @param arg2
	 * @return
	 */
	public static Matrix MatrixAdd(Matrix arg1,Matrix arg2){
		if((arg1.size_r==arg2.size_r)&&(arg1.size_c==arg2.size_c)){//矩阵行列相等的时候才可以相加
			int i,j;
			Matrix result = new Matrix(arg1.size_r,arg1.size_c);
			for(i=0;i<arg1.size_r;i++)
				for(j=0;j<arg1.size_c;j++){
					result.array[i][j] = arg1.array[i][j] + arg2.array[i][j];
				}
			return result;	//返回矩阵加法的结果
		}
		else return null;	//如果矩阵维数不相等，不能相加，则返回为空
	}

	/**
	 * 矩阵乘法
	 * @param arg1
	 * @param arg2
	 * @return
	 */
	public static Matrix MatrixMul(Matrix arg1,Matrix arg2){
		if(arg1.size_c==arg2.size_r){//只有当第一个矩阵的列数和第二个矩阵的行数相等时才可以进行矩阵乘法
			int i,j,k;
			double temp;
			Matrix result = new Matrix(arg1.size_r,arg2.size_c);
			for(i=0;i<arg1.size_r;i++){
				for(j=0;j<arg2.size_c;j++){
					temp = 0;
					for(k=0;k<arg1.size_c;k++){
						temp+=arg1.array[i][k]*arg2.array[k][j];
					}
					result.array[i][j] = temp;
				}
			}
			return result;
		}
		else return null;//如果两个矩阵不能相乘，返回空
	}

	public double[][] getArray() {
		return array;
	}

	public void setArray(double[][] array) {
		this.array = array;
	}

	//以下为本题需要得到的最后一个一行一列的矩阵的值,因为B样条的矩阵中要乘以1/6，所以直接在这里面乘了
	public int getResult() {
		return (int)(1.0/6 * array[0][0]);
	}





}
