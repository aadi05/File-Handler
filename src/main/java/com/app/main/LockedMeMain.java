package com.app.main;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.app.bo.FileHandlerBO;
import com.app.bo.Implementation.FileHandlerImplementation;
import com.app.exceptions.EmptyListException;
import com.app.exceptions.NoFileException;
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
			try {
				choice = Integer.parseInt(sc.nextLine());
			}
			catch(Exception e) {
				continue;
			}finally {
				System.out.println("Please enter a number!\n");
			}
			switch (choice) {
			case 1:
				try {
					List<FileHandler> fileList = handler.listFilesAsc();
					if(!fileList.isEmpty()) {
						Collections.sort(fileList, (f1,f2) -> {
							return f1.getFileName().compareTo(f2.getFileName());
						});
						System.out.println("File name\t\t\tLast Accessed");
						DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");
						for(FileHandler file:fileList) {
							LocalDateTime dateObj = file.getLastAccessed();
							String formattedDateTime = dateObj.format(format1); 
							System.out.println(file.getFileName()+"\t\t\t\t"+formattedDateTime);
						}
					}
				}catch(EmptyListException e){
					System.out.println(e.getMessage());
				}finally {
					System.out.println();
				}
				break;
			case 2:
				int choice2 = 0;
				do {
					System.out.println("1. Add File");
					System.out.println("2. Remove File");
					System.out.println("3 Search File");
					System.out.println("4.Go Back");
					try {
						choice2 = Integer.parseInt(sc.nextLine());
					}catch(Exception e) {
						System.out.println("Please enter a number!\n");
						continue;
					}
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
						System.out.println();
						break;
					case 2:
						try {
							System.out.println("Enter File Name");
							String fileName2 = sc.nextLine().trim();
							boolean status2 = handler.removeFile(fileName2);
							if(status2) {
								System.out.println("File "+fileName2+" deleted succesfully.");
							}
						}catch(NoFileException e){
							System.out.println(e.getMessage());
						}finally {
							System.out.println();
						}
						break;
					case 3:
						try {
							System.out.println("Enter File Name");
							String fileName3 = sc.nextLine().trim();
							FileHandler returnedFile = handler.searchFile(fileName3);
							if(returnedFile != null) {
								LocalDateTime dateObj = returnedFile.getLastAccessed();
								DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");
							    String formattedDateTime = dateObj.format(format1); 
								System.out.println("File found succesfully.");
								System.out.println(returnedFile.getFileName()+"\t\t\t\t"+formattedDateTime);
							}
						}catch(NoFileException e) {
							System.out.println(e.getMessage());
						}finally {
							System.out.println();
						}
						break;
					case 4:
						System.out.println();
						break;

					default:
						System.out.println("Invalid option!\n");
						break;
					}
				}while(choice2!=4);
				break;
			case 3:
				break;
				
			default:
				System.out.println("Invalid option!\n");
				break;
			}
			
			
		}while(choice!=3);

	}

}
