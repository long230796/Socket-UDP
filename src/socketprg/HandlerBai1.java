package socketprg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class HandlerBai1 {
	private String src;
	private String dst;
	private String file;
	File fileSource;
	File fileDestination;
	File folderSource;
	File folderDestination;
	
	public HandlerBai1(String src, String dst, String file) {
		this.src = src.trim();
		this.dst = dst.trim();
		this.file = file.trim();
		
		this.fileSource = new File(this.src + this.file);
		System.out.println(fileSource);
		System.out.println(this.dst + this.file);
		this.fileDestination = new File(this.dst + this.file);
		
		this.folderSource = new File(this.src);
		this.folderDestination = new File(this.dst);
	}
	
	public String copyFile()  {

        try (var fis = new FileInputStream(fileSource);
             var fos = new FileOutputStream(fileDestination)) {

            byte[] buffer = new byte[1024];
            int length;

            while ((length = fis.read(buffer)) > 0) {

                fos.write(buffer, 0, length);
            }
        } catch (Exception e) {
			return "Khong the copy file";
		}
        
        return "Copy file thanh cong!";
	}
	
	public boolean checkFolderExist() {
		if (folderSource.isDirectory() && folderDestination.isDirectory()) {
           return true;
        }
		
		return false;
		
	}
	
	public boolean checkFileExist() {
		if (fileSource.exists() && !fileSource.isDirectory()) {
			return true;
		}
		return false;
	}

}
