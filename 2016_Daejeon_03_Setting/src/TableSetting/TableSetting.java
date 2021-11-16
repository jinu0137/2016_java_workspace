package TableSetting;

import java.sql.ResultSet;

import Setting_DBInterface.DBInterface;

public class TableSetting {
	public static void CreateDB() throws Exception {
		ResultSet rs = DBInterface.Stmt.executeQuery("show databases like 'project000'");
		if(rs.next()) {
			DBInterface.Stmt.execute("drop database `project000`");
		}
		DBInterface.Stmt.execute("create database `project000`");
	}
	
	public static void CreateGrants() throws Exception {
		DBInterface.Stmt.execute("use Mysql");
		ResultSet rs = DBInterface.Stmt.executeQuery("select * from user where user='user'");
		if(rs.next()) {
			DBInterface.Stmt.execute("drop user `user`");
		}
		DBInterface.Stmt.execute("create user `user` identified by '1234'");
		DBInterface.Stmt.execute("grant select,insert,delete,update on `project000`.* to user");
	}
	
	public static void CreateTables() throws Exception {
		DBInterface.Stmt.execute("CREATE TABLE `project000`.`member` (  `id` VARCHAR(11) NOT NULL,  `pw` VARCHAR(6) NULL,  `name` VARCHAR(45) NULL,  `phone_num` VARCHAR(13) NULL,  `address` VARCHAR(45) NULL,  `point` INT(11) NULL,  PRIMARY KEY (`id`));");
		DBInterface.Stmt.execute("CREATE TABLE `project000`.`product` (  `id` INT(11) NOT NULL AUTO_INCREMENT,  `name` VARCHAR(45) NULL,  `type` VARCHAR(2) NULL,  `price` INT(11) NULL,  `size` VARCHAR(45) NULL,  `amount` INT(11) NULL,  PRIMARY KEY (`id`));");
		DBInterface.Stmt.execute("CREATE TABLE `project000`.`purchase` (  `id` INT(11) NOT NULL AUTO_INCREMENT,  `recipient` VARCHAR(45) NULL,  `size` VARCHAR(45) NULL,  `amount` INT(11) NULL,  `cost` INT(11) NULL,  `address` VARCHAR(45) NULL,  `order_date` DATE NULL,  `delivery` TINYINT(1) NULL,  `product_id` INT(11) NULL,  `member_id` VARCHAR(45) NULL,  PRIMARY KEY (`id`));");
		DBInterface.Stmt.execute("CREATE TABLE `project000`.`log` (  `id` VARCHAR(11) NULL);");
	}
	
	public static void CreateData() throws Exception {
		DBInterface.Stmt.execute("use `Project000`");
		
		String path = System.getProperty("user.dir")+"\\res\\datafiles\\member.txt";
		path = path.replace('\\', '/');
		DBInterface.Stmt.execute("load data local infile '"+path+"' into table member ignore 1 lines");
		
		path = System.getProperty("user.dir")+"\\res\\datafiles\\product.txt";
		path = path.replace('\\', '/');
		DBInterface.Stmt.execute("load data local infile '"+path+"' into table product ignore 1 lines (name, type, price, size, amount)");
		
		path = System.getProperty("user.dir")+"\\res\\datafiles\\purchase.txt";
		path = path.replace('\\', '/');
		DBInterface.Stmt.execute("load data local infile '"+path+"' into table purchase ignore 1 lines (recipient, size, amount, cost, address, order_date, delivery, product_id, member_id)");
	}
}
