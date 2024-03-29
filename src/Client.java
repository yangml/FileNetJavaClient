/*
 *@author: ZhengHaibo  
 *web:     blog.csdn.net/nuptboyzhb
 *mail:    zhb931706659@126.com
 *2012-9-23  Nanjing njupt
 */
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
public class Client {
    NetDataTypeTransform mNetDataTypeTransform=new NetDataTypeTransform();
	private static final String IP="127.0.0.1";
	private static final int NetPort=17329;
	private Socket sock;
	public Client(){
		try {
			onCreate();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void onCreate() throws IOException{
		InetSocketAddress addr = new InetSocketAddress(IP,NetPort); //创建socket
		sock = new Socket();
		sock.connect(addr);//连接服务器
		byte []receive=new byte[9];
		sock.getInputStream().read(receive);
		String tempString=mNetDataTypeTransform.ByteArraytoString(receive, receive.length);
		System.out.println("  Server said:send your "+tempString);
		if(tempString.equals("Password")){
			System.out.println("I can send password 123\0!");
		}
		String password="123\0";//注意，别忘了‘\0’
		sock.getOutputStream().write(mNetDataTypeTransform.StringToByteArray(password));
		byte []isOk=new byte[3];
		sock.getInputStream().read(isOk);
		String okString=mNetDataTypeTransform.ByteArraytoString(isOk,isOk.length);
		System.out.println("  ----- is ok?--"+okString);
		if(okString.equals("OK")){
			System.out.println("new Thread begin...");
			NetDataCommand commd=new NetDataCommand(1,"E:\0");
			sock.getOutputStream().write(commd.getByteArrayData());
			ThreadRead mThreadRead=new ThreadRead(sock,this);
			mThreadRead.start();//启动监听线程。
		}
		//////////////////////////////////////////////////////////////
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    public static void main(String [] args) throws InterruptedException, IOException
	{
    	new Client();
	}
    public void GetDriverPro(Socket mSocket,NetDataCommand mCommand){
    	System.out.println("command ID="+mCommand.getID()+"--"+"command Lparam="+mCommand.getLparam());
    	NetDataCommand commd=new NetDataCommand(2,mCommand.getLparam()+"\\\0");
    	try {
			mSocket.getOutputStream().write(commd.getByteArrayData());
		} catch (IOException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void GetDirInfoPro(Socket mSocket,NetDataCommand mCommand){
    	System.out.println("command ID="+mCommand.getID()+"command Lparam="+mCommand.getLparam());
    }
    public void ExecFilePro(Socket mSocket,NetDataCommand mCommand){
    	System.out.println("command ID="+mCommand.getID()+"command Lparam="+mCommand.getLparam());
    }
    public void DelFilePro(Socket mSocket,NetDataCommand mCommand){
    	System.out.println("command ID="+mCommand.getID()+"command Lparam="+mCommand.getLparam());
    	
    }
    public void FileInfoPro(Socket mSocket,NetDataCommand mCommand){
    	System.out.println("command ID="+mCommand.getID()+"command Lparam="+mCommand.getLparam());
    }
    public void CreateDirPro(Socket mSocket,NetDataCommand mCommand){
    	System.out.println("command ID="+mCommand.getID()+"command Lparam="+mCommand.getLparam());
    }
    public void GetFilePro(Socket mSocket,NetDataCommand mCommand){
    	System.out.println("command ID="+mCommand.getID()+"command Lparam="+mCommand.getLparam());
    }
    public void PutFilePro(Socket mSocket,NetDataCommand mCommand){
    	System.out.println("command ID="+mCommand.getID()+"command Lparam="+mCommand.getLparam());
    }
    public void GetScreenPro(Socket mSocket,NetDataCommand mCommand){
    	System.out.println("command ID="+mCommand.getID()+"command Lparam="+mCommand.getLparam());
    }
}
