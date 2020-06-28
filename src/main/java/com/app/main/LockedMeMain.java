package com.app.main;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.app.bo.FileHandlerBO;
import com.app.bo.Implementation.FileHandlerImplementation;
import com.app.model.FileHandler;

public class LockedMeMain {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("\tLOCKEDME.COM");
		System.out.println("DEVELOPED BY: ADITYA BHOGATE\n");
		System.out.println("LOCKEDME MENU(1-3)");
		FileHandlerBO handler = new FileHandlerImplementation();
		int choice = 0;
		do {
			System.out.println("1. List file names in Ascending order");
			System.out.println("2. Manipulate Files");
			System.out.println("3. Exit");
			choice = Integer.parseInt(sc.nextLine());
			switch (choice) {
			case 1:
				List<FileHandler> fileList = handler.listFilesAsc();
				if(!fileList.isEmpty()) {
					Collections.sort(fileList, (f1,f2) -> {
						return f1.getFileName().compareTo(f2.getFileName());
					});
					for(FileHandler file:fileList) {
						System.out.println(file.getFileName()+"    "+file.getLastAccessed());
					}
				}
				else {
					System.out.println("Please add files first!");
				}
				System.out.println();
				break;
			case 2:
				int choice2 = 0;
				do {
					System.out.println("1. Add File");
					System.out.println("2. Remove File");
					System.out.println("3 Search File");
					System.out.println("4.Go Back");
					choice2 = Integer.parseInt(sc.nextLine());
					switch (choice2) {
					case 1:
						System.out.println("Enter File Name");
						String fileName = sc.nextLine().trim();
						boolean status = handler.createFile(fileName);
						if(status) {
							System.out.println("File "+fileName+" created succesfully.");
						}
						else {
							System.out.println("File already exists!");
						}
						break;
					case 2:
						System.out.println("Enter File Name");
						String fileName2 = sc.nextLine().trim();
						boolean status2 = handler.removeFile(fileName2);
						if(status2) {
							System.out.println("File "+fileName2+" deleted succesfully.");
						}
						else {
							System.out.println("File "+fileName2+" not found!");
						}
						break;
					case 3:
						System.out.println("Enter File Name");
						String fileName3 = sc.nextLine().trim();
						FileHandler returnedFile = handler.searchFile(fileName3);
						if(returnedFile != null) {
							LocalDateTime dateObj = returnedFile.getLastAccessed();
							DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
						    String formattedDate = dateObj.format(myFormatObj);
							System.out.println("File found succesfully.");
							System.out.println(returnedFile.getFileName()+"    "+formattedDate);
						}
						else {
							System.out.println("File "+fileName3+" not found!");
						}
						break;
					case 4:
						break;

					default:
						System.out.println("Invalid option!");
						break;
					}
				}while(choice2!=4);
				break;
			case 3:
				break;
				
			default:
				System.out.println("Invalid option!");
				break;
			}
			
			
		}while(choice!=3);

	}

}
