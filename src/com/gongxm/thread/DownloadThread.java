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
	 * �̵߳�����
	 */
	public static int threadCount = 3;
	
	/**
	 * �������е��̵߳ĸ���
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
			//�鿴�ļ������Ƿ��¼�е�ǰ�̵߳����ؿ�ʼλ�á�
			File infofile = new File(temp.getParent(),threadCount+"temp.zip"+threadid+".txt");
			if(infofile.exists()&&infofile.length()>0){
				FileInputStream fis = new FileInputStream(infofile);
				BufferedReader br = new BufferedReader(new InputStreamReader(fis));
				String newstartindex = br.readLine();
				conn.setRequestProperty("Range", "bytes="+newstartindex+"-"+endindex);
				startindex = Integer.parseInt(newstartindex);
				fis.close();//�ǵ��ͷ��ļ�������
			}else{
			//�������������ļ������������ļ���һ���֡� ���߷����� ���ص���Դ����һ����
			//HttpURLConnection.setRequestProperty("Range", "bytes=2097152-4194303");
				conn.setRequestProperty("Range", "bytes="+startindex+"-"+endindex);
			}
			int code = conn.getResponseCode();//200 OK  206 ���󲿷����ݳɹ�
			if(code == 206){
				//��ǰ���߳� ��Ӧ��������һ���ֵ����ݡ�
				InputStream is = conn.getInputStream();
				RandomAccessFile raf = new RandomAccessFile(temp, "rw");
				raf.seek(startindex);//ע�⣺��ͬ�̴߳��ļ��Ŀ�ʼλ���ǲ���ͬ�ģ�Ҫ���Լ���Ӧ��λ�ô��ļ�
				byte[] buffer = new byte[1024*4];
				int len = -1;
				int total = 0;//����ǰ�߳����ص��ܴ�С
				while(( len = is.read(buffer))!=-1){
					raf.write(buffer, 0, len);
					total+=len;
					int currentposition = startindex+total;//��ǰ�߳����ص�λ�á�
					File file = new File(temp.getParent(),threadCount+"temp.zip"+threadid+".txt");
					//ȷ����ÿ��ѭ������ѽ���д���ײ�洢�豸���档
					RandomAccessFile rafos = new RandomAccessFile(file, "rwd");
					//�ѵ�ǰ�̵߳�λ����Ϣд�뵽һ���ļ�����
					rafos.write(String.valueOf(currentposition).getBytes());
					rafos.close();//���ݲ�����ֱ�ӱ��浽�ײ�Ĵ洢�豸���棬���浽���棬����ռ����ˣ����ݻ�ͬ�����ײ��豸��
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
