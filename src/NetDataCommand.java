/*
 *@author: ZhengHaibo  
 *web:     blog.csdn.net/nuptboyzhb
 *mail:    zhb931706659@126.com
 *2012-9-26  Nanjing njupt
 */
public class NetDataCommand {
	private static final int IDLen=4;
	private static final int LparamLen=2048;
	private static final int CommandLen=2052;
	public byte []byteArrayData=new byte[CommandLen];
	private int ID;
	private String lparam;
	private NetDataTypeTransform mDataTypeTransform=new NetDataTypeTransform();;
	public byte[] getByteArrayData(){
		return byteArrayData;
	}
	public NetDataCommand(){
		
	}
	public NetDataCommand(int ID,String lparam) {
		// TODO Auto-generated constructor stub
		
		this.ID=ID;
		this.lparam=lparam;
		byte[] IDbyte = mDataTypeTransform.IntToByteArray(ID);
		System.arraycopy(IDbyte,0, byteArrayData, 0, IDbyte.length);
		byte[] Strbyte = mDataTypeTransform.StringToByteArray(lparam);
		System.arraycopy(Strbyte,0,byteArrayData,IDbyte.length,Strbyte.length);
	}
	public NetDataCommand(byte[] dataArray){
		int id=1;
	    String lpString="";
		System.arraycopy(dataArray,0, byteArrayData,0,CommandLen);
		byte[] forIntID = new byte[IDLen];
		System.arraycopy(dataArray,0,forIntID,0,forIntID.length);
		id=mDataTypeTransform.ByteArrayToInt(forIntID);
		byte[] StrTemp=new byte[LparamLen];
		System.arraycopy(dataArray,IDLen,StrTemp,0,StrTemp.length);
		lpString=mDataTypeTransform.ByteArraytoString(StrTemp, StrTemp.length);
		//lpString=StrTemp.toString();
		ID=id;
		lparam=lpString;
	}
	public int getID(){
		return ID;
	}
	public String getLparam(){
		return lparam;
	}
	public void setID(int id) {
		this.ID=id;
	}
	public void setLparam(String str){
		this.lparam=str;
	}
	
}
