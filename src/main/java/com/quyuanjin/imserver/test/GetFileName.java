package com.quyuanjin.imserver.test;

import java.io.File;

public class GetFileName {

    public static String [] getFileName(String path)
    {
        File file = new File(path);
        String [] fileName = file.list();
        return fileName;
    }
    public static void renameFile(String path,String oldname,String newname){
        if(!oldname.equals(newname)){//新的文件名和以前文件名不同时,才有必要进行重命名
            File oldfile=new File(path+"\\"+oldname);
            File newfile=new File(path+"\\"+newname);
            if(!oldfile.exists()){
                return;//重命名文件不存在
            }
            if(newfile.exists())//若在该目录下已经有一个文件和新文件名相同，则不允许重命名
                System.out.println(newname+"已经存在！");
            else{
                oldfile.renameTo(newfile);
            }
        }else{
            System.out.println("新文件名和旧文件名相同...");
        }
    }
    public static void main(String[] args)
    {
        String [] fileName = getFileName("D:\\zuiyoump4.1");//<span style="font-family: Arial, Helvetica, sans-serif;">此处修改为你的本地路径</span>
        for (int i = 0; i < fileName.length; i++) {
            renameFile("D:\\zuiyoump4.1", fileName[i], i+".mp4");//cx修改为你要修改的文件名格式
        }
    }

}
