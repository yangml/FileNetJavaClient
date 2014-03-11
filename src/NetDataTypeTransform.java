import java.io.UnsupportedEncodingException;

/*
 *@author: ZhengHaibo
 *web:     blog.csdn.net/nuptboyzhb
 *mail:    zhb931706659@126.com
 *2012-9-25  Nanjing njupt
 */
public class NetDataTypeTransform {
	public NetDataTypeTransform(){
		
	}
	/**
	 * ��intתΪ���ֽ���ǰ�����ֽ��ں��byte����
	 */
	public byte[] IntToByteArray(int n) {
		byte[] b = new byte[4];
		b[0] = (byte) (n & 0xff);
		b[1] = (byte) (n >> 8 & 0xff);
		b[2] = (byte) (n >> 16 & 0xff);
		b[3] = (byte) (n >> 24 & 0xff);
		return b;
	}
	/**
	 * byte����ת��Ϊint
	 * �����ֽ���ǰתΪint�����ֽ��ں��byte����
	 */
	public int ByteArrayToInt(byte[] bArr) {
		int n = 0;
		for(int i=0;i<bArr.length&&i<4;i++){
			int left = i*8;
			n+= (bArr[i] << left);
		}
		return n;
	}
	/**
	 * ��byte����ת����String
	 */
	public String ByteArraytoString(byte[] valArr,int maxLen) {
		String result=null;
		int index = 0;
		while(index < valArr.length && index < maxLen) {
			if(valArr[index] == 0) {
				break;
			}
			index++;
		}
		byte[] temp = new byte[index];
		System.arraycopy(valArr, 0, temp, 0, index);
		try {
			result= new String(temp,"GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	public byte[] StringToByteArray(String str){
		byte[] temp = null;
	    try {
			temp = str.getBytes("GBK");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}
}
