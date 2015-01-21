package com.gongxm.thread;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import com.gongxm.web.controller.UrlDownload;

public class DownloadThread extends Thread {
	/**
	 * 线程的数量
	 */
	public static int threadCount = 3;
	
	/**
	 * 正在运行的线程的个数
	 */
	public static int runningThreadCount ;
	
	private int threadid;
	private int startindex;
	private int endindex;
	private String path;
	private File temp;
	public DownloadThread(int threadid, int startindex, int endindex,
			String path,File file) {
		this.threadid = threadid;
		this.startindex = startindex;
		this.endindex = endindex;
		this.path = path;
		this.temp=file;
	}
	@Override
	public void run() {
		try {
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5000);
			//查看文件里面是否记录有当前线程的下载开始位置。
			File infofile = new File(temp.getParent(),threadCount+"temp.zip"+threadid+".txt");
			if(infofile.exists()&&infofile.length()>0){
				FileInputStream fis = new FileInputStream(infofile);
				BufferedReader br = new BufferedReader(new InputStreamReader(fis));
				String newstartindex = br.readLine();
				conn.setRequestProperty("Range", "bytes="+newstartindex+"-"+endindex);
				startindex = Integer.parseInt(newstartindex);
				fis.close();//记得释放文件的引用
			}else{
			//不是下载整个文件，而是下载文件的一部分。 告诉服务器 下载的资源就是一部分
			//HttpURLConnection.setRequestProperty("Range", "bytes=2097152-4194303");
				conn.setRequestProperty("Range", "bytes="+startindex+"-"+endindex);
			}
			int code = conn.getResponseCode();//200 OK  206 请求部分数据成功
			if(code == 206){
				//当前的线程 就应用下载这一部分的数据。
				InputStream is = conn.getInputStream();
				RandomAccessFile raf = new RandomAccessFile(temp, "rw");
				raf.seek(startindex);//注意：不同线程存文件的开始位置是不相同的，要从自己对应的位置存文件
				byte[] buffer = new byte[1024*4];
				int len = -1;
				int total = 0;//代表当前线程下载的总大小
				while(( len = is.read(buffer))!=-1){
					raf.write(buffer, 0, len);
					total+=len;
					int currentposition = startindex+total;//当前线程下载的位置。
					File file = new File(temp.getParent(),threadCount+"temp.zip"+threadid+".txt");
					//确保了每次循环都会把进度写到底层存储设备里面。
					RandomAccessFile rafos = new RandomAccessFile(file, "rwd");
					//把当前线程的位置信息写入到一个文件里面
					rafos.write(String.valueOf(currentposition).getBytes());
					rafos.close();//数据并不是直接保存到底层的存储设备里面，保存到缓存，缓存空间满了，数据会同步到底层设备。
				}
				is.close();
				raf.close();
				synchronized (DownloadThread.class) {
					runningThreadCount--;
					if(runningThreadCount<=0){
						for(int i=0;i<threadCount;i++){
							File f = new File(temp.getParent(),threadCount+"temp.zip"+i+".txt");
							f.delete();
						}
						UrlDownload.isBusy=false;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
