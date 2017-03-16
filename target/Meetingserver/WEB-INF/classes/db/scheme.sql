create table T_Book (id bigint not null, bookname varchar(255),version int default 0, primary key (id));
create table T_User (id bigint not null, username varchar(255),version int default 0,primary key (id));
create table csop_app_category (
	ID varchar (96),
	PARENT_ID varchar (96),
	NAME varchar (60),
	DESCRIPTION varchar (600),
	ENABLED varchar (3),
	DELETED varchar (3)
);
CREATE TABLE csop_application (
	ID varchar (96),
	APP_CATEGORY_ID varchar (96),
	NAME varchar (150),
	INTRO blob ,
	DESCRIPTION blob ,
	SN varchar (96),
	VERSION varchar (60),
	STATUS varchar (6),
	BRING_IN_STATUS varchar (6),
	TEST_STATUS varchar (6),
	PUBLISH_STATUS varchar (6),
	QUIT_STATUS varchar (6),
	CREATED_TIME datetime ,
	STATUS_TIME datetime ,
	DELETED varchar (3),
	QUIT_DESC varchar (600),
	QUIT_TIME datetime ,
	TYPE varchar (6),
	TAG varchar (600),
	SI_ID varchar (96),
	EXT blob,
	PRIMARY KEY (ID),
	KEY FK_APPLICATION__APP_CATEGORY_ID (APP_CATEGORY_ID),
	KEY FK_APPLICATION__SI_ID (SI_ID),
	CONSTRAINT FK_APPLICATION__APP_CATEGORY_ID FOREIGN KEY (APP_CATEGORY_ID) REFERENCES csop_app_category (ID)
)
