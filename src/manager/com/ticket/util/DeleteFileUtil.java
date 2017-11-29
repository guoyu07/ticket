package com.ticket.util;

import java.io.File;

/**
 *  删除文件或目录 的类
 * @author zzf
 *
 */
public class DeleteFileUtil {

	  /**   
     * 删除文件，可以是单个文件或文件夹   
     * @param   filePath    待删除的文件名  (文件存在的路径) 
     * @return 文件删除成功返回true,否则返回false   
     */    
    public static boolean delete(String filePath){     
        File file = new File(filePath);     
        if(!file.exists()){     
            System.out.println("删除文件失败："+filePath+"文件不存在");     
            return false;     
        }else{     
            if(file.isFile()){     
                     
                return deleteFile(filePath);     
            }else{     
                return deleteDirectory(filePath);     
            }     
        }     
    }     
         
    /**   
     * 删除单个文件   
     * @param   filePath    被删除文件的文件名   (文件存在的路径) 
     * @return 单个文件删除成功返回true,否则返回false   
     */    
    public static boolean deleteFile(String filePath){     
        File file = new File(filePath);     
        if(file.isFile() && file.exists()){     
            file.delete();     
            System.out.println("删除单个文件"+filePath+"成功！");     
            return true;     
        }else{     
            System.out.println("删除单个文件"+filePath+"失败！");     
            return false;     
        }     
    }     
         
    /**   
     * 删除目录（文件夹）以及目录下的文件   
     * @param   dir 被删除目录的文件路径   
     * @return  目录删除成功返回true,否则返回false   
     */    
    public static boolean deleteDirectory(String dir){     
        //如果dir不以文件分隔符结尾，自动添加文件分隔符     
        if(!dir.endsWith(File.separator)){     
            dir = dir+File.separator;     
        }     
        File dirFile = new File(dir);     
        //如果dir对应的文件不存在，或者不是一个目录，则退出     
        if(!dirFile.exists() || !dirFile.isDirectory()){     
            System.out.println("删除目录失败"+dir+"目录不存在！");     
            return false;     
        }     
        boolean flag = true;     
        //删除文件夹下的所有文件(包括子目录)     
        File[] files = dirFile.listFiles();     
        for(int i=0;i<files.length;i++){     
            //删除子文件     
            if(files[i].isFile()){     
                flag = deleteFile(files[i].getAbsolutePath());     
                if(!flag){     
                    break;     
                }     
            }     
            //删除子目录     
            else{     
                flag = deleteDirectory(files[i].getAbsolutePath());     
                if(!flag){     
                    break;     
                }     
            }     
        }     
             
        if(!flag){     
            System.out.println("删除目录失败");     
            return false;     
        }     
             
        //删除当前目录     
        if(dirFile.delete()){     
            System.out.println("删除目录"+dir+"成功！");     
            return true;     
        }else{     
            System.out.println("删除目录"+dir+"失败！");     
            return false;     
        }     
    }   
    
    /**
     * 判断文件是否存在
     * @param filePath
     * @return
     */
    public static boolean fileIsExists(String filePath){
    	
    	boolean flag = false;
    	File file = new File(filePath);     
    	if(file.exists()){
    		
    		flag = true;
    	}else{
    		
    		flag = false;
    	}
    	
    	return flag;
    }
    
}
