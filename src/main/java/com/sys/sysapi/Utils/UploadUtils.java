package com.sys.sysapi.Utils;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class UploadUtils {
    /** 上传文件
     * 写Bytes数据到文件
     * @param bytes
     * @param fileName
     */
    public static boolean writeBytesToFile(byte bytes[], String filePath,
                                           String fileName) {
        boolean rtn = false;
        FileOutputStream fos = null;
        java.io.File myFilePath = null;
        try {
            myFilePath = new java.io.File(filePath);
            if (!myFilePath.exists()) {
                myFilePath.mkdir();
            }
            fos = new FileOutputStream(filePath + fileName);
            fos.write(bytes);
            fos.flush();
            rtn = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            myFilePath = null;
            try {
                fos.close();
            } catch (IOException iex) {
                iex.printStackTrace();
            }
        }
        return rtn;
    }
    /**
     * 获得指定文件的byte数组
     */
    public static byte[] getBytes(String filePath){
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    /**
     *
     * @param f 上传的文件
     * @param outpath 上传路径
     * @param outname 文件名称
     */
    public static void upload(File f,String outpath,String outname) {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            InputStream datas=new FileInputStream(f);
            //获取客户端传递的InputStream
            bis = new BufferedInputStream(datas);
            //创建文件输出流
            bos = new BufferedOutputStream(new FileOutputStream(outpath+outname));
            byte[] buffer = new byte[8192];
            int r = bis.read(buffer, 0, buffer.length);
            while (r > 0) {
                bos.write(buffer, 0, r);
                r = bis.read(buffer, 0, buffer.length);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    /**、
     *
     * @param path 上传路径
     * @param name 上传名称
     * @param datas 上传类容
     */
    public void upload(String path,String name,String datas){
        try {
            FileOutputStream fos = new FileOutputStream(path+name);
            InputStream is = new FileInputStream(datas);
            byte[] buffer = new byte[8192];
            int count = 0;
            while ((count = is.read(buffer)) > 0) {
                fos.write(buffer, 0, count);
            }
            fos.close();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static  void uploadFileTest(MultipartFile zipFile,String targetFilePath,String fileName) {
        File targetFile = new File(targetFilePath + File.separator + fileName);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(targetFile);
            IOUtils.copy(zipFile.getInputStream(), fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
            }
        }
    }


}