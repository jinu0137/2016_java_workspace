package Setting_TableSetting;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.ResultSet;

import Setting_DBInterface.DBInterface;

public class TableSetting {
	public static void CreateDB() throws Exception {
		ResultSet rs = DBInterface.Stmt.executeQuery("show databases like 'TrainDB'");
		if(rs.next()) {
			DBInterface.Stmt.execute("drop database `TrainDB`");
		}
		DBInterface.Stmt.execute("create database `TrainDB`");
		DBInterface.Stmt.execute("use `TrainDB`");
		
		System.out.println("A");
	}
	
	public static void CreateGrants() throws Exception {
		DBInterface.Stmt.execute("use Mysql");
		ResultSet rs = DBInterface.Stmt.executeQuery("select * from user where user='user'");
		if(rs.next()) {
			DBInterface.Stmt.execute("drop user `user`");
		}
		DBInterface.Stmt.execute("create user `user` identified by '1234'");
		DBInterface.Stmt.execute("grant select,insert,delete,update on `TrainDB`.* to user");
		
		System.out.println("B");
	}
	
	public static void CreateTables() throws Exception {
		DBInterface.Stmt.execute("CREATE TABLE `traindb`.`user` (  `id` INT(11) NOT NULL,  `uID` VARCHAR(10) NULL,  `uPW` VARCHAR(10) NULL,  `name` VARCHAR(10) NULL,  PRIMARY KEY (`id`));");
		DBInterface.Stmt.execute("CREATE TABLE `traindb`.`reservation` (  `id` INT(11) NOT NULL,  `User_num` INT(11) NULL,  `Train_service_num` INT(11) NULL,  `Seat_num` INT(11) NULL,  `Reservation_date` DATE NULL,  PRIMARY KEY (`id`));");
		DBInterface.Stmt.execute("CREATE TABLE `traindb`.`seat` (  `seat` INT(11) NOT NULL,  `Stateroom_num` INT(11) NULL,  `name` VARCHAR(10) NULL,  PRIMARY KEY (`seat`));");
		DBInterface.Stmt.execute("CREATE TABLE `traindb`.`stateroom` (  `id` INT(11) NOT NULL,  `name` VARCHAR(10) NULL,  PRIMARY KEY (`id`));");
		DBInterface.Stmt.execute("CREATE TABLE `traindb`.`train_service` (  `id` INT(11) NOT NULL,  `Schedule_num` INT(11) NULL,  `Departure_time` DATETIME NULL,  PRIMARY KEY (`id`));");
		DBInterface.Stmt.execute("CREATE TABLE `traindb`.`schedule` (  `id` INT(11) NOT NULL,  `Departure_station` INT(11) NULL,  `Arrival_station` INT(11) NULL,  `Train_num` INT(11) NULL,  `Lead_time` TIME NULL,  `Price` INT(11) NULL,  PRIMARY KEY (`id`));");
		DBInterface.Stmt.execute("CREATE TABLE `traindb`.`train` (  `id` INT(11) NOT NULL,  `name` VARCHAR(10) NULL,  PRIMARY KEY (`id`));");
		DBInterface.Stmt.execute("CREATE TABLE `traindb`.`station` (  `id` INT(11) NOT NULL,  `name` VARCHAR(10) NULL,  PRIMARY KEY (`id`));");
		
		System.out.println("C");
	}
	
	public void CreateData() throws Exception {
		ClassLoader cl = getClass().getClassLoader();
		InputStream st1 = cl.getResourceAsStream("DataFiles/User.txt");
		InputStream st2 = cl.getResourceAsStream("DataFiles/Reservation.txt");
		InputStream st3 = cl.getResourceAsStream("DataFiles/Seat.txt");
		InputStream st4 = cl.getResourceAsStream("DataFiles/Stateroom.txt");
		InputStream st5 = cl.getResourceAsStream("DataFiles/Train_service.txt");
		InputStream st6 = cl.getResourceAsStream("DataFiles/Schedule.txt");
		InputStream st7 = cl.getResourceAsStream("DataFiles/Train.txt");
		InputStream st8 = cl.getResourceAsStream("DataFiles/Station.txt");
		
		String s;
		int cnt=1;
		
		BufferedReader in = new BufferedReader(new InputStreamReader(st1, "UTF8"));
		
		while((s=in.readLine())!=null) {
			if(cnt!=1) {
				String[] sp = s.split("\t");
				DBInterface.Stmt.execute("INSERT INTO `traindb`.`user` (`id`, `uID`, `uPW`, `name`) "
						+ "VALUES ('"+sp[0]+"', '"+sp[1]+"', '"+sp[2]+"', '"+sp[3]+"');");
			}
			cnt+=1;
		}
		in.close();
		
		cnt=1;
		
		in = new BufferedReader(new InputStreamReader(st2, "UTF8"));
		
		while((s=in.readLine())!=null) {
			if(cnt!=1) {
				String[] sp = s.split("\t");
				DBInterface.Stmt.execute("INSERT INTO `traindb`.`reservation` (`id`, `User_num`, `Train_service_num`, `Seat_num`, `Reservation_date`) "
						+ "VALUES ('"+sp[0]+"', '"+sp[1]+"', '"+sp[2]+"', '"+sp[3]+"', '"+sp[4]+"');");
			}
			cnt+=1;
		}
		in.close();
		
		cnt=1;
		
		in = new BufferedReader(new InputStreamReader(st3, "UTF8"));
		
		while((s=in.readLine())!=null) {
			if(cnt!=1) {
				String[] sp = s.split("\t");
				DBInterface.Stmt.execute("INSERT INTO `traindb`.`seat` (`seat`, `Stateroom_num`, `name`) "
						+ "VALUES ('"+sp[0]+"', '"+sp[1]+"', '"+sp[2]+"');");
			}
			cnt+=1;
		}
		in.close();
		
		cnt=1;
		
		in = new BufferedReader(new InputStreamReader(st4, "UTF8"));
		
		while((s=in.readLine())!=null) {
			if(cnt!=1) {
				String[] sp = s.split("\t");
				DBInterface.Stmt.execute("INSERT INTO `traindb`.`stateroom` (`id`, `name`) "
						+ "VALUES ('"+sp[0]+"', '"+sp[1]+"');");
			}
			cnt+=1;
		}
		in.close();
		
		cnt=1;
		
		in = new BufferedReader(new InputStreamReader(st5, "UTF8"));
		
		while((s=in.readLine())!=null) {
			if(cnt!=1) {
				String[] sp = s.split("\t");
				DBInterface.Stmt.execute("INSERT INTO `traindb`.`train_service` (`id`, `Schedule_num`, `Departure_time`) "
						+ "VALUES ('"+sp[0]+"', '"+sp[1]+"', '"+sp[2]+"');");
			}
			cnt+=1;
		}
		in.close();
		
		cnt=1;
		
		in = new BufferedReader(new InputStreamReader(st6, "UTF8"));
		
		while((s=in.readLine())!=null) {
			if(cnt!=1) {
				String[] sp = s.split("\t");
				DBInterface.Stmt.execute("INSERT INTO `traindb`.`schedule` (`id`, `Departure_station`, `Arrival_station`, `Train_num`, `Lead_time`, `Price`) "
						+ "VALUES ('"+sp[0]+"', '"+sp[1]+"', '"+sp[2]+"', '"+sp[3]+"', '"+sp[4]+"', '"+sp[5]+"');");
			}
			cnt+=1;
		}
		in.close();
		
		cnt=1;
		
		in = new BufferedReader(new InputStreamReader(st7, "UTF8"));
		
		while((s=in.readLine())!=null) {
			if(cnt!=1) {
				String[] sp = s.split("\t");
				DBInterface.Stmt.execute("INSERT INTO `traindb`.`train` (`id`, `name`) "
						+ "VALUES ('"+sp[0]+"', '"+sp[1]+"');");
			}
			cnt+=1;
		}
		in.close();
		
		cnt=1;
		
		in = new BufferedReader(new InputStreamReader(st8, "UTF8"));
		
		while((s=in.readLine())!=null) {
			if(cnt!=1) {
				String[] sp = s.split("\t");
				DBInterface.Stmt.execute("INSERT INTO `traindb`.`station` (`id`, `name`) "
						+ "VALUES ('"+sp[0]+"', '"+sp[1]+"');");
			}
			cnt+=1;
		}
		in.close();
		
		
		System.out.println("D");
	}
}
