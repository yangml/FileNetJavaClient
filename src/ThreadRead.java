/*
 *@author: ZhengHaibo  
 *web:     blog.csdn.net/nuptboyzhb
 *mail:    zhb931706659@126.com
 *2012-9-26  Nanjing njupt
 */
import java.io.IOException;
import java.lang.Thread;
import java.net.Socket;
public class ThreadRead extends Thread{
	public Socket mSocket;
	public Client mClient;
	private static final int GetDriver=0x01;
	private static final int GetDirInfo=0x02;
	private static final int ExecFile=0x03;
	private static final int GetFile=0x04;
	private static final int PutFile=0x05;
	private static final int DelFile=0x06;
	private static final int DelDir =0x07;
	private static final int CreateDir=0x08;
	private static final int FileInfo=0x09;
	private static final int GetScreen=0x10;
	private static final int CommandLen=2052;
	private static int TryTimes=5;
	private byte []byteArrayData=new byte[CommandLen];
	public ThreadRead(Socket lpSocket,Client mClient) {
		// TODO Auto-generated constructor stub
		this.mSocket=lpSocket;
		this.mClient=mClient;
	}
	
	public void run() {
		while (TryTimes>0) {
			while (true) {
				try {
					mSocket.getInputStream().read(byteArrayData);
					NetDataCommand mCommand = new NetDataCommand(byteArrayData);
					switch (mCommand.getID()){
					case GetDriver:
						mClient.GetDriverPro(mSocket, mCommand);
						break;
					case GetDirInfo:
						mClient.GetDirInfoPro(mSocket, mCommand);
						break;
					case ExecFile:
						mClient.ExecFilePro(mSocket, mCommand);
						break;
					case DelFile:
						mClient.DelFilePro(mSocket, mCommand);
						break;
					case FileInfo:
						mClient.FileInfoPro(mSocket, mCommand);
						break;
					case CreateDir:
						mClient.CreateDirPro(mSocket, mCommand);
						break;
					case DelDir:
						mClient.DelFilePro(mSocket, mCommand);
						break;
					case GetFile:
						mClient.GetFilePro(mSocket, mCommand);
						break;
					case PutFile:
						mClient.PutFilePro(mSocket, mCommand);
						break;
					case GetScreen:
						mClient.GetScreenPro(mSocket, mCommand);
						break;
					default:
						System.out.println("----------wrong!!!--------------");
						break;
					}

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					TryTimes--;
					break;
				}
			}
		}
	}
}
