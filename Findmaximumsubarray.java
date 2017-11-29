package algorithms;

public class Findmaximumsubarray {
	int[] A = {0, 13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
	int lsum, rsum, maxl, maxr, lrsum;
	int clow, chigh, csum, llow, lhigh, rlow, rhigh;
	
	public static void FindMaximumSubarray() {
		Findmaximumsubarray tt = new Findmaximumsubarray();
		
		Findmaximumsubarray t = Findmaximumsubarray.FindMaxmumSubarray(tt.A, 0, tt.A.length-1);
		
		System.out.printf("left-index = %d\nright-index = %d\nsum = %d\n", t.clow, t.chigh, t.csum);
	}
	
	private static Findmaximumsubarray FindMaxCrossingSubarray(int[] A, int low, int mid, int high) {
		Findmaximumsubarray temp = new Findmaximumsubarray();
		temp.lsum = -1048576;
		temp.rsum = -1048576;
		int sum = 0;
		for(int i = mid; i >= low; i--) {
			sum += A[i];
			if(sum > temp.lsum) {
				temp.lsum = sum;
				temp.maxl = i;
			}
		}
		sum = 0;
		for(int j = mid+1; j < high+1; j++) {
			sum += A[j];
			if(sum > temp.rsum) {
				temp.rsum = sum;
				temp.maxr = j;
			}
		}
		temp.lrsum = temp.lsum + temp.rsum;
		return temp;
	}
	
	private static Findmaximumsubarray FindMaxmumSubarray(int[] A, int low, int high) {
		Findmaximumsubarray temp = new Findmaximumsubarray();
		if(low == high) {
			temp.clow = low;
			temp.chigh = high;
			temp.csum = A[low];
		} else {
			int mid = ((low + high) / 2);
			Findmaximumsubarray temp1 = Findmaximumsubarray.FindMaxmumSubarray(A, low, mid);
			temp.llow = temp1.clow;
			temp.lhigh = temp1.chigh;
			temp.lsum = temp1.csum;
			Findmaximumsubarray temp2 = Findmaximumsubarray.FindMaxmumSubarray(A, mid+1, high);
			temp.rlow = temp2.clow;
			temp.rhigh = temp2.chigh;
			temp.rsum = temp2.csum;
			Findmaximumsubarray temp3 = Findmaximumsubarray.FindMaxCrossingSubarray(A, low, mid, high);
			temp.clow = temp3.maxl;
			temp.chigh = temp3.maxr;
			temp.csum = temp3.lrsum;
			if(temp.lsum >= temp.rsum && temp.lsum >= temp.csum) {
				temp.clow = temp.llow;
				temp.chigh = temp.lhigh;
				temp.csum = temp.lsum;
			} else {
				if(temp.rsum >= temp.lsum && temp.rsum >= temp.csum) {
					temp.clow = temp.rlow;
					temp.chigh = temp.rhigh;
					temp.csum = temp.rsum;
				}
			}
		}
		return temp;
	}
}
