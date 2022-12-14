package Server;
public class calculate {
	private static int p=3543233, q=7, e=97, d=219169, N=p*q;
	public static int size;
	private static int pow(int i, int j, int k) {
		int l, temp, p=1;
		for(temp=0; temp<j; temp++) {
			p=(p*i);
			l=p/k;
			p=p-(l*k);
		}
		return (int)p; 
	}
	
	public static String encode(String pass, int _size) {
		String s=new String();
		size=_size;
		int[] arr=new int[20];
		char[] ch=new char[20];
		ch=pass.toCharArray();
		for(int i=0; i<size; i++) {
			arr[i]=(int)ch[i];
		}
		arr=encryption(arr, e, N);
		for(int i=0; i<size; i++) {
			s+=arr[i]+"/";
		}
		return s;
	}
	public static String decode(String arr) {
		String s;
		s=decryption(arr, d, N);
		return s;
	}
	
	private static String decryption(String arr, int d, int N) {
		String s=new String();
		String ar[]=arr.split("/");
		for(int i=0; i<size; i++) {
			int temp=Integer.parseInt(ar[i]);
			int caltemp=pow(temp, d, N);
			s +=(char)caltemp;
		}
		return s;
	}
	
	
	private static int[] encryption(int[] arr, int e, int N) {
		for(int i=0; i<size; i++) {
			int temp=pow(arr[i], e, N);
			arr[i]=temp;
		}
		return arr;
	}
}

