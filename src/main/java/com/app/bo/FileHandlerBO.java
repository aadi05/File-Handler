package com.app.bo;
import java.util.List;

import com.app.exceptions.EmptyListException;
import com.app.exceptions.NoFileException;
import com.app.model.FileHandler;

public interface FileHandlerBO {
	public List<FileHandler> listFilesAsc() throws EmptyListException;
	public boolean createFile(String fileName);
	public boolean removeFile(String fileName) throws NoFileException;
	public FileHandler searchFile(String fileName) throws NoFileException;

}
