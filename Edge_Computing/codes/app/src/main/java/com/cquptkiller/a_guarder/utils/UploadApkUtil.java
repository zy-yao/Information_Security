package com.cquptkiller.a_guarder.utils;

import android.content.Context;
import android.os.Environment;
import android.os.Message;
import android.widget.Toast;

import java.io.File;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.io.RandomAccessFile;
import java.net.Socket;

/**
 * Created by yao on 2018/12/22.
 */
public class UploadApkUtil {
    Context context;
    private UploadLogService logService;
    private boolean start=true;


    public UploadApkUtil(Context context,String apkDir,String ip){
        this.context=context;
        logService = new UploadLogService(context);

        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){

            File uploadFile=new File(apkDir);
            if(uploadFile.exists()){
                uploadFile(uploadFile,ip);
            }else{
                Toast.makeText(context, "文件不存在", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(context, "内存卡错误", Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * 上传文件
     * @param uploadFile
     */
    private void uploadFile(final File uploadFile, final String IP) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                   String souceid = logService.getBindId(uploadFile);
                    String head = "Content-Length="+ uploadFile.length() + ";filename="+ uploadFile.getName() + ";sourceid="+
                            (souceid==null? "" : souceid)+"\r\n";

                    Socket socket = new Socket(IP,3000);

                    OutputStream outStream = socket.getOutputStream();
                    outStream.write(head.getBytes());

                    PushbackInputStream inStream = new PushbackInputStream(socket.getInputStream());
                    String response = StreamTool.readLine(inStream);
                    String[] items = response.split(";");
                    String responseid = items[0].substring(items[0].indexOf("=")+1);
                    String position = items[1].substring(items[1].indexOf("=")+1);
                    if(souceid==null){//代表原来没有上传过此文件，往数据库添加一条绑定记录
                        logService.save(responseid, uploadFile);
                    }
                    RandomAccessFile fileOutStream = new RandomAccessFile(uploadFile, "r");
                    fileOutStream.seek(Integer.valueOf(position));
                    byte[] buffer = new byte[1024];
                    int len = -1;
                    int length = Integer.valueOf(position);
                    while(start&&(len = fileOutStream.read(buffer)) != -1){
                        outStream.write(buffer, 0, len);
                        length += len;

                    }
                    fileOutStream.close();
                    outStream.close();
                    inStream.close();
                    socket.close();
                    if(length==uploadFile.length()) logService.delete(uploadFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
