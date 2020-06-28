package com.app.bo.Implementation;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.app.bo.FileHandlerBO;
import com.app.exceptions.EmptyListException;
import com.app.exceptions.NoFileException;
import com.app.model.FileHandler;

public class FileHandlerImplementation implements FileHandlerBO {
	private static Map<String,FileHandler> fileMap = new HashMap<>();
	private static int fileId;

	@Override
	public List<FileHandler> listFilesAsc() throws EmptyListException {
		List<FileHandler> fileList = new ArrayList<>(fileMap.values());
		if(fileList.isEmpty()) {
			//throw new EmptyListException("Please add files first!");
		}
		return fileList;
	}

	@Override
	public boolean createFile(String fileName) {
		File fileObj = new File(fileName);
		boolean creationStatus = false;
		try {
			creationStatus = fileObj.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(creationStatus) {
			LocalDateTime currTime = LocalDateTime.now();
			FileHandler newFile = new FileHandler(++fileId,fileName,currTime);
			fileMap.put(fileName, newFile);
		}
		return creationStatus;
	}

	@Override
	public boolean removeFile(String fileName) throws NoFileException {
		File fileObj = new File(fileName);
		boolean deletionStatus = false;
		if(fileObj.delete()) {
			deletionStatus = true;
			fileMap.remove(fileName);
		}
		else {
			//throw new NoFileException("File "+fileName+" doesn't exist!");
		}
		return deletionStatus;
		
	}

	@Override
	public FileHandler searchFile(String fileName) {
		FileHandler currFile = null;
		if(fileMap.containsKey(fileName)) {
			currFile = fileMap.get(fileName);
			LocalDateTime currTime = LocalDateTime.now();
			currFile.setLastAccessed(currTime);
		}
		else {
			//throw new NoFileException("File "+fileName+" doesn't exist!");
		}
		return currFile;
	}

}
