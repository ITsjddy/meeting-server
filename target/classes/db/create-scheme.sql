create table T_Book (id bigint not null, bookname varchar(255),version int default 0, primary key (id));
create table T_User (id bigint not null, username varchar(255),version int default 0,primary key (id));
DROP TABLE IF EXISTS csop_account;
CREATE TABLE csop_account (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  STATUS int(11) DEFAULT NULL,
  CREATETIME datetime DEFAULT NULL,
  UPDATETIME datetime DEFAULT NULL,
  DESCRIPTION varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  USER_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_ACCOUNT__USER_ID (USER_ID),
  CONSTRAINT FK_ACCOUNT__USER_ID FOREIGN KEY (USER_ID) REFERENCES csop_user (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_account_book
-- ----------------------------
DROP TABLE IF EXISTS csop_account_book;
CREATE TABLE csop_account_book (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  ACCOUNT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  TYPE int(11) DEFAULT NULL,
  BALANCE decimal(10,2) DEFAULT NULL,
  CREDIT_LINE decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_ACCOUNT_BOOK__ACCOUNT_ID (ACCOUNT_ID),
  CONSTRAINT FK_ACCOUNT_BOOK__ACCOUNT_ID FOREIGN KEY (ACCOUNT_ID) REFERENCES csop_account (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='账本用于记录用户在系统中的余额与信用度';


-- ----------------------------
-- Table structure for csop_account_book_sp
-- ----------------------------
DROP TABLE IF EXISTS csop_account_book_sp;
CREATE TABLE csop_account_book_sp (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  ACCOUNT_BOOK_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  NAME varchar(50) COLLATE utf8_bin DEFAULT NULL,
  DESCRIPTION varchar(200) COLLATE utf8_bin DEFAULT NULL,
  EFF_DATE date DEFAULT NULL,
  EXP_DATE date DEFAULT NULL,
  balance decimal(10,2) NOT NULL,
  SOURCE char(2) COLLATE utf8_bin DEFAULT NULL,
  SOURCE_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  TYPE char(2) COLLATE utf8_bin DEFAULT NULL,
  CREATED_TIME datetime DEFAULT NULL,
  MODIFIED_TIME datetime DEFAULT NULL,
  SN varchar(50) COLLATE utf8_bin DEFAULT NULL,
  FREEZE_BALANCE decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_ACCOUNT_BOOK_SP__ACCOUNT_BOOK_ID (ACCOUNT_BOOK_ID),
  CONSTRAINT FK_ACCOUNT_BOOK_SP__ACCOUNT_BOOK_ID FOREIGN KEY (ACCOUNT_BOOK_ID) REFERENCES csop_account_book (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='记录专款专用账本所对应的专款专用项信息';

-- ----------------------------
-- Table structure for csop_acct_item
-- ----------------------------
DROP TABLE IF EXISTS csop_acct_item;
CREATE TABLE csop_acct_item (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  ACCT_ITEM_TYPE_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  BILLING_CYCLE_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PROD_INST_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  ACCT_ITEM_SOURCE_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  AMOUNT decimal(10,2) DEFAULT NULL,
  CREATED_TIME datetime DEFAULT NULL,
  STATUS char(2) COLLATE utf8_bin DEFAULT NULL COMMENT '0A-未销帐;0B-已销帐;0C-已返销',
  STATUS_TIME datetime DEFAULT NULL,
  PAID_AMOUNT decimal(10,2) DEFAULT NULL,
  PAYABLE_AMOUNT decimal(10,2) DEFAULT NULL,
  SN varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PREPAID char(1) COLLATE utf8_bin DEFAULT NULL,
  PRE_PAID_AMOUNT decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_ACCT_ITEM__ACCT_ITEM_SOURCE_ID (ACCT_ITEM_SOURCE_ID),
  KEY FK_ACCT_ITEM__ACCT_ITEM_TYPE_ID (ACCT_ITEM_TYPE_ID),
  KEY FK_ACCT_ITEM__BILLING_CYCLE_ID (BILLING_CYCLE_ID),
  KEY FK_ACCT_ITEM__PROD_INST_ID (PROD_INST_ID),
  CONSTRAINT FK_ACCT_ITEM__ACCT_ITEM_SOURCE_ID FOREIGN KEY (ACCT_ITEM_SOURCE_ID) REFERENCES csop_acct_item_source (ID),
  CONSTRAINT FK_ACCT_ITEM__ACCT_ITEM_TYPE_ID FOREIGN KEY (ACCT_ITEM_TYPE_ID) REFERENCES csop_acct_item_type (ID),
  CONSTRAINT FK_ACCT_ITEM__BILLING_CYCLE_ID FOREIGN KEY (BILLING_CYCLE_ID) REFERENCES csop_billing_cycle (ID),
  CONSTRAINT FK_ACCT_ITEM__PROD_INST_ID FOREIGN KEY (PROD_INST_ID) REFERENCES csop_product_inst (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_acct_item_source
-- ----------------------------
DROP TABLE IF EXISTS csop_acct_item_source;
CREATE TABLE csop_acct_item_source (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  PRICE_INFO_SNAPSHOT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  TICKET_INFO_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_ACCT_ITEM_SOURCE__PRICE_INFO_SNAPSHOT_ID (PRICE_INFO_SNAPSHOT_ID),
  KEY FK_ACCT_ITEM_SOURCE__TICKET_INFO_ID (TICKET_INFO_ID),
  CONSTRAINT FK_ACCT_ITEM_SOURCE__PRICE_INFO_SNAPSHOT_ID FOREIGN KEY (PRICE_INFO_SNAPSHOT_ID) REFERENCES csop_pricing_info_snapshot (ID),
  CONSTRAINT FK_ACCT_ITEM_SOURCE__TICKET_INFO_ID FOREIGN KEY (TICKET_INFO_ID) REFERENCES csop_ticket_info (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_acct_item_type
-- ----------------------------
DROP TABLE IF EXISTS csop_acct_item_type;
CREATE TABLE csop_acct_item_type (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  NAME varchar(100) COLLATE utf8_bin DEFAULT NULL,
  DESCRIPTION varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  ENABLED char(1) COLLATE utf8_bin DEFAULT NULL,
  DELETED char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_app_auth
-- ----------------------------
DROP TABLE IF EXISTS csop_app_auth;
CREATE TABLE csop_app_auth (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  APP_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  APP_KEY varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_APP_AUTH__APP_ID (APP_ID),
  CONSTRAINT FK_APP_AUTH__APP_ID FOREIGN KEY (APP_ID) REFERENCES csop_application (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_app_category
-- ----------------------------
DROP TABLE IF EXISTS csop_app_category;
CREATE TABLE csop_app_category (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  PARENT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  NAME varchar(20) COLLATE utf8_bin DEFAULT NULL,
  DESCRIPTION varchar(200) COLLATE utf8_bin DEFAULT NULL,
  ENABLED char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '0-启动；1-停止',
  DELETED char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '0-未删除；1-删除',
  PRIMARY KEY (ID),
  KEY FK_APP_CATEGORY__PARENT_ID (PARENT_ID),
  CONSTRAINT FK_APP_CATEGORY__PARENT_ID FOREIGN KEY (PARENT_ID) REFERENCES csop_app_category (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_app_control_info
-- ----------------------------
DROP TABLE IF EXISTS csop_app_control_info;
CREATE TABLE csop_app_control_info (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  APP_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  BUSI_INTER_URL varchar(200) COLLATE utf8_bin DEFAULT NULL,
  BUSI_INTER_TYPE char(2) COLLATE utf8_bin DEFAULT NULL,
  MAX_LIMIT int(11) DEFAULT NULL,
  ENABLED char(1) COLLATE utf8_bin DEFAULT NULL,
  DELETED char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_APP_CONTROL_INFO__APP_ID (APP_ID),
  CONSTRAINT FK_APP_CONTROL_INFO__APP_ID FOREIGN KEY (APP_ID) REFERENCES csop_application (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_app_files
-- ----------------------------
DROP TABLE IF EXISTS csop_app_files;
CREATE TABLE csop_app_files (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  APP_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  FILE_TYPE int(11) DEFAULT NULL,
  FILE_URL varchar(100) COLLATE utf8_bin DEFAULT NULL,
  SIZE bigint(20) DEFAULT NULL,
  UPLOAD_TIME timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  ENABLED char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_APP_FILES__APP_ID (APP_ID),
  CONSTRAINT FK_APP_FILES__APP_ID FOREIGN KEY (APP_ID) REFERENCES csop_application (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_app_histroy
-- ----------------------------
DROP TABLE IF EXISTS csop_app_histroy;
CREATE TABLE csop_app_histroy (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  APP_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  NAME varchar(50) COLLATE utf8_bin DEFAULT NULL,
  INTRO varchar(20) COLLATE utf8_bin DEFAULT NULL,
  VERSION varchar(20) COLLATE utf8_bin DEFAULT NULL,
  STATUS char(2) COLLATE utf8_bin DEFAULT NULL,
  BRING_IN_STATUS char(2) COLLATE utf8_bin DEFAULT NULL,
  TEST_STATUS char(2) COLLATE utf8_bin DEFAULT NULL,
  PUBLISH_STATUS char(2) COLLATE utf8_bin DEFAULT NULL,
  QUIT_STATUS char(2) COLLATE utf8_bin DEFAULT NULL,
  TYPE char(2) COLLATE utf8_bin DEFAULT NULL,
  TAG varchar(200) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_APP_HISTROY__APP_ID (APP_ID),
  CONSTRAINT FK_APP_HISTROY__APP_ID FOREIGN KEY (APP_ID) REFERENCES csop_application (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_app_image
-- ----------------------------
DROP TABLE IF EXISTS csop_app_image;
CREATE TABLE csop_app_image (
  PIC_TYPE varchar(32) COLLATE utf8_bin DEFAULT NULL,
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  APP_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PATH varchar(100) COLLATE utf8_bin DEFAULT NULL,
  WIDTH int(11) DEFAULT NULL,
  HEIGHT int(11) DEFAULT NULL,
  SIZE bigint(20) DEFAULT NULL,
  UPLOAD_TIME datetime DEFAULT NULL,
  ENABLED char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_APP_IMAGE__APP_ID (APP_ID),
  KEY FK_APP_IMAGE__PIC_TYPE (PIC_TYPE),
  CONSTRAINT FK_APP_IMAGE__APP_ID FOREIGN KEY (APP_ID) REFERENCES csop_application (ID),
  CONSTRAINT FK_APP_IMAGE__PIC_TYPE FOREIGN KEY (PIC_TYPE) REFERENCES csop_image_type (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_app_inst_control_info
-- ----------------------------
DROP TABLE IF EXISTS csop_app_inst_control_info;
CREATE TABLE csop_app_inst_control_info (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  APP_INST_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  TYPE int(11) DEFAULT NULL,
  URL varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_APP_INST_CONTROL_INFO__APP_INST_ID (APP_INST_ID),
  CONSTRAINT FK_APP_INST_CONTROL_INFO__APP_INST_ID FOREIGN KEY (APP_INST_ID) REFERENCES csop_app_instance (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_app_inst_member_rls
-- ----------------------------
DROP TABLE IF EXISTS csop_app_inst_member_rls;
CREATE TABLE csop_app_inst_member_rls (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  APP_INST_ID varchar(32) COLLATE utf8_bin NOT NULL,
  EC_ID varchar(32) COLLATE utf8_bin NOT NULL,
  USER_ID varchar(32) COLLATE utf8_bin NOT NULL,
  ISSYNC varchar(2) COLLATE utf8_bin DEFAULT NULL,
  OPTYPE varchar(4) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (ID),
  KEY FK_PROD_INST_CUSTOMER_RLS__USER_ID (USER_ID),
  KEY FK_PROD_INST_CUSTOMER_RLS__APP_INST_ID (APP_INST_ID),
  CONSTRAINT FK_PROD_INST_CUSTOMER_RLS__APP_INST_ID FOREIGN KEY (APP_INST_ID) REFERENCES csop_app_instance (ID),
  CONSTRAINT FK_PROD_INST_CUSTOMER_RLS__USER_ID FOREIGN KEY (USER_ID) REFERENCES csop_user (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_app_inst_quota_value
-- ----------------------------
DROP TABLE IF EXISTS csop_app_inst_quota_value;
CREATE TABLE csop_app_inst_quota_value (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  APP_INST_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  QUOTA_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  VALUE varchar(20) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_APP_INST_QUOTA_VALUE__QUOTA_ID (QUOTA_ID),
  KEY FK_APP_INST_QUOTA_VALUE__APP_INST_ID (APP_INST_ID),
  CONSTRAINT FK_APP_INST_QUOTA_VALUE__APP_INST_ID FOREIGN KEY (APP_INST_ID) REFERENCES csop_app_instance (ID),
  CONSTRAINT FK_APP_INST_QUOTA_VALUE__QUOTA_ID FOREIGN KEY (QUOTA_ID) REFERENCES csop_app_quota (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_app_instance
-- ----------------------------
DROP TABLE IF EXISTS csop_app_instance;
CREATE TABLE csop_app_instance (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  APP_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  SN varchar(32) COLLATE utf8_bin DEFAULT NULL,
  CREATED_TIME datetime DEFAULT NULL,
  STATUS int(11) DEFAULT NULL,
  STATUS_TIME datetime DEFAULT NULL,
  PROD_INST_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  RUN_INFO varchar(2000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_APP_INSTANCE__APP_ID (APP_ID),
  KEY FK_APP_INSTANCE__PROD_INST_ID (PROD_INST_ID),
  CONSTRAINT FK_APP_INSTANCE__APP_ID FOREIGN KEY (APP_ID) REFERENCES csop_application (ID),
  CONSTRAINT FK_APP_INSTANCE__PROD_INST_ID FOREIGN KEY (PROD_INST_ID) REFERENCES csop_product_inst (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_app_provider_update_info
-- ----------------------------
DROP TABLE IF EXISTS csop_app_provider_update_info;
CREATE TABLE csop_app_provider_update_info (
  SI_ID varchar(32) COLLATE utf8_bin NOT NULL,
  UPDATE_INFO longtext COLLATE utf8_bin,
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (ID),
  KEY FK_APP_PROVIDER_UPDATE_INFO__SI_ID (SI_ID),
  CONSTRAINT FK_APP_PROVIDER_UPDATE_INFO__SI_ID FOREIGN KEY (SI_ID) REFERENCES csop_app_providers (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_app_provider_verify_log
-- ----------------------------
DROP TABLE IF EXISTS csop_app_provider_verify_log;
CREATE TABLE csop_app_provider_verify_log (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  SI_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  RESULT int(11) DEFAULT NULL,
  OPINION varchar(200) COLLATE utf8_bin DEFAULT NULL,
  APPROVAL_STATUS char(2) COLLATE utf8_bin DEFAULT NULL,
  VERIFIER varchar(32) COLLATE utf8_bin DEFAULT NULL,
  APPROVAL_TIME datetime DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_APP_PROVIDER_VERIFY_LOG__SI_ID (SI_ID),
  CONSTRAINT FK_APP_PROVIDER_VERIFY_LOG__SI_ID FOREIGN KEY (SI_ID) REFERENCES csop_app_providers (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_app_providers
-- ----------------------------
DROP TABLE IF EXISTS csop_app_providers;
CREATE TABLE csop_app_providers (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  SI_CODE varchar(200) COLLATE utf8_bin DEFAULT NULL,
  SI_NAME varchar(200) COLLATE utf8_bin DEFAULT NULL,
  ADDRESS varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  INDUSTRY_CATEGORY varchar(200) COLLATE utf8_bin DEFAULT NULL,
  SERVICE_AREA varchar(200) COLLATE utf8_bin DEFAULT NULL,
  USERNAME varchar(200) COLLATE utf8_bin DEFAULT NULL,
  PASSWORD varchar(200) COLLATE utf8_bin DEFAULT NULL,
  CONTACT varchar(300) COLLATE utf8_bin DEFAULT NULL,
  CONTACT_NUMBER varchar(100) COLLATE utf8_bin DEFAULT NULL,
  STATUS int(11) DEFAULT NULL,
  EMAIL varchar(100) COLLATE utf8_bin DEFAULT NULL,
  SCALE int(11) DEFAULT NULL,
  CREATEBY varchar(32) COLLATE utf8_bin DEFAULT NULL,
  CREATETIME datetime DEFAULT NULL,
  UPDATEBY varchar(32) COLLATE utf8_bin DEFAULT NULL,
  UPDATETIME datetime DEFAULT NULL,
  APPROVESTATUS int(11) DEFAULT NULL,
  REG_PHONE varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '注册电话',
  REG_EMAIL varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '注册Email',
  TAX_NAME varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '税务登记证',
  BUSINESS_LICENSE varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '营业执照号码',
  REGIST_CAPITAL varchar(8) COLLATE utf8_bin DEFAULT NULL COMMENT '注册资金',
  CORP_REPRESENTATIVE varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '企业法人代表',
  COMPANY_TEL varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '公司联系电话 ',
  COMPANY_ADDRESS varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '公司地址',
  PROVINCE varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '所在省',
  EPARCHY_ID varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '所在地市编码',
  CITY_ID varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '所在县区编码',
  REG_DATE datetime DEFAULT NULL COMMENT '注册时间',
  OLD_ID varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '修改前的ID，用于变更对比',
  IMAGE_CHANGED char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '是否修改图片',
  FILE_CHANGED char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '是否修改文件',
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_app_providers_contract
-- ----------------------------
DROP TABLE IF EXISTS csop_app_providers_contract;
CREATE TABLE csop_app_providers_contract (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  SI_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  CONTRACT_CODE varchar(200) COLLATE utf8_bin DEFAULT NULL,
  TYPE varchar(4) COLLATE utf8_bin DEFAULT NULL,
  DETAILS varchar(5000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_APP_PROVIDERS_CONTRACT__SI_ID (SI_ID),
  CONSTRAINT FK_APP_PROVIDERS_CONTRACT__SI_ID FOREIGN KEY (SI_ID) REFERENCES csop_app_providers (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_app_providers_file
-- ----------------------------
DROP TABLE IF EXISTS csop_app_providers_file;
CREATE TABLE csop_app_providers_file (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  UPLOAD_DATE timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FILE_URL varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '例如：../upload/icon/${filename}  (兰州用)',
  FILE_TYPE varchar(4) COLLATE utf8_bin DEFAULT NULL,
  SI_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  SIZE bigint(20) DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_PROVIDERS_FILE__SI_ID (SI_ID),
  CONSTRAINT FK_PROVIDERS_FILE__SI_ID FOREIGN KEY (SI_ID) REFERENCES csop_app_providers (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_app_providers_image
-- ----------------------------
DROP TABLE IF EXISTS csop_app_providers_image;
CREATE TABLE csop_app_providers_image (
  PIC_TYPE varchar(32) COLLATE utf8_bin DEFAULT NULL,
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  SI_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PATH varchar(100) COLLATE utf8_bin DEFAULT NULL,
  WIDTH int(11) DEFAULT NULL,
  HEIGHT int(11) DEFAULT NULL,
  SIZE bigint(20) DEFAULT NULL,
  UPLOAD_TIME datetime DEFAULT NULL,
  ENABLED char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_APP_PROVIDERS_IMAGE__PIC_TYPE (PIC_TYPE),
  KEY FK_APP_PROVIDERS_IMAGE__SI_ID (SI_ID),
  CONSTRAINT FK_APP_PROVIDERS_IMAGE__PIC_TYPE FOREIGN KEY (PIC_TYPE) REFERENCES csop_image_type (ID),
  CONSTRAINT FK_APP_PROVIDERS_IMAGE__SI_ID FOREIGN KEY (SI_ID) REFERENCES csop_app_providers (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_app_quota
-- ----------------------------
DROP TABLE IF EXISTS csop_app_quota;
CREATE TABLE csop_app_quota (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  APP_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  NAME varchar(50) COLLATE utf8_bin DEFAULT NULL,
  DESCRIPTION varchar(200) COLLATE utf8_bin DEFAULT NULL,
  CODE varchar(20) COLLATE utf8_bin DEFAULT NULL,
  ENABLED char(1) COLLATE utf8_bin DEFAULT NULL,
  DELETED char(1) COLLATE utf8_bin DEFAULT NULL,
  UNIT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  TYPE int(11) DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_APP_QUOTA__APP_ID (APP_ID),
  KEY FK_APP_QUOTA__UNIT_ID (UNIT_ID),
  CONSTRAINT csop_app_quota_ibfk_1 FOREIGN KEY (UNIT_ID) REFERENCES csop_unit (ID),
  CONSTRAINT FK_APP_QUOTA__APP_ID FOREIGN KEY (APP_ID) REFERENCES csop_application (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_app_quota_value
-- ----------------------------
DROP TABLE IF EXISTS csop_app_quota_value;
CREATE TABLE csop_app_quota_value (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  QUOTA_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  VALUE varchar(20) COLLATE utf8_bin DEFAULT NULL,
  PROD_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_APP_QUOTA_VALUE__QUOTA_ID (QUOTA_ID),
  KEY FK_APP_QUOTA_VALUE__PROD_ID (PROD_ID),
  CONSTRAINT FK_APP_QUOTA_VALUE__PROD_ID FOREIGN KEY (PROD_ID) REFERENCES csop_product (ID),
  CONSTRAINT FK_APP_QUOTA_VALUE__QUOTA_ID FOREIGN KEY (QUOTA_ID) REFERENCES csop_app_quota (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_app_res_inst_relation
-- ----------------------------
DROP TABLE IF EXISTS csop_app_res_inst_relation;
CREATE TABLE csop_app_res_inst_relation (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  APP_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  RES_INST_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  REQ_ITEM_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_RES_INST_RELATION__APP_ID (APP_ID),
  KEY FK_RES_INST_RELATION__RES_INST_ID (RES_INST_ID),
  KEY FK_RES_INST_RELATION__REQ_ITEM_ID (REQ_ITEM_ID),
  CONSTRAINT FK_RES_INST_RELATION__APP_ID FOREIGN KEY (APP_ID) REFERENCES csop_application (ID),
  CONSTRAINT FK_RES_INST_RELATION__REQ_ITEM_ID FOREIGN KEY (REQ_ITEM_ID) REFERENCES csop_app_res_req_item (ID),
  CONSTRAINT FK_RES_INST_RELATION__RES_INST_ID FOREIGN KEY (RES_INST_ID) REFERENCES csop_res_instance (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_app_res_req
-- ----------------------------
DROP TABLE IF EXISTS csop_app_res_req;
CREATE TABLE csop_app_res_req (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  APP_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  SN varchar(32) COLLATE utf8_bin DEFAULT NULL,
  INSERT_TIME datetime DEFAULT NULL,
  STATUS int(11) DEFAULT NULL,
  STATUS_TIME datetime DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_APP_RES_REQ__APP_ID (APP_ID),
  CONSTRAINT FK_APP_RES_REQ__APP_ID FOREIGN KEY (APP_ID) REFERENCES csop_application (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_app_res_req_item
-- ----------------------------
DROP TABLE IF EXISTS csop_app_res_req_item;
CREATE TABLE csop_app_res_req_item (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  REQ_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  RES_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  QUANTITY int(11) DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_APP_RES_REQ_ITEM__REQ_ID (REQ_ID),
  KEY FK_APP_RES_REQ_ITEM__RES_ID (RES_ID),
  CONSTRAINT FK_APP_RES_REQ_ITEM__REQ_ID FOREIGN KEY (REQ_ID) REFERENCES csop_app_res_req (ID),
  CONSTRAINT FK_APP_RES_REQ_ITEM__RES_ID FOREIGN KEY (RES_ID) REFERENCES csop_resource (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_app_verify_log
-- ----------------------------
DROP TABLE IF EXISTS csop_app_verify_log;
CREATE TABLE csop_app_verify_log (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  APP_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  RESULT int(11) DEFAULT NULL,
  OPINION varchar(200) COLLATE utf8_bin DEFAULT NULL,
  APP_STATUS char(6) COLLATE utf8_bin DEFAULT NULL,
  VERIFIER varchar(32) COLLATE utf8_bin DEFAULT NULL,
  APPROVAL_TIME datetime DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_VERIFY_LOG__APP_ID (APP_ID),
  CONSTRAINT FK_VERIFY_LOG__APP_ID FOREIGN KEY (APP_ID) REFERENCES csop_application (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_app_work_order
-- ----------------------------
DROP TABLE IF EXISTS csop_app_work_order;
CREATE TABLE csop_app_work_order (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  APP_INST_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  SN varchar(20) COLLATE utf8_bin DEFAULT NULL,
  TYPE char(3) COLLATE utf8_bin DEFAULT NULL,
  STATUS int(11) DEFAULT NULL,
  CREATED_TIME datetime DEFAULT NULL,
  STATUS_TIME datetime DEFAULT NULL,
  DELETED char(1) COLLATE utf8_bin DEFAULT NULL,
  WORKER varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_APP_WORK_ORDER__APP_INST_ID (APP_INST_ID),
  CONSTRAINT FK_APP_WORK_ORDER__APP_INST_ID FOREIGN KEY (APP_INST_ID) REFERENCES csop_app_instance (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_app_work_order_log
-- ----------------------------
DROP TABLE IF EXISTS csop_app_work_order_log;
CREATE TABLE csop_app_work_order_log (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  WORK_ORDER_ID varchar(32) COLLATE utf8_bin NOT NULL,
  CONTENT varchar(200) COLLATE utf8_bin DEFAULT NULL,
  CREATED_BY varchar(32) COLLATE utf8_bin NOT NULL,
  CREATED_TIME datetime DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_APP_WORK_ORDER_LOG__WORK_ORDER_ID (WORK_ORDER_ID),
  CONSTRAINT FK_APP_WORK_ORDER_LOG__WORK_ORDER_ID FOREIGN KEY (WORK_ORDER_ID) REFERENCES csop_app_work_order (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_application
-- ----------------------------
DROP TABLE IF EXISTS csop_application;
CREATE TABLE csop_application (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  APP_CATEGORY_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  NAME varchar(50) COLLATE utf8_bin DEFAULT NULL,
  INTRO varchar(500) COLLATE utf8_bin DEFAULT NULL,
  SN varchar(32) COLLATE utf8_bin DEFAULT NULL,
  VERSION varchar(20) COLLATE utf8_bin DEFAULT NULL,
  STATUS char(2) COLLATE utf8_bin DEFAULT NULL,
  BRING_IN_STATUS char(2) COLLATE utf8_bin DEFAULT NULL,
  TEST_STATUS char(2) COLLATE utf8_bin DEFAULT NULL,
  PUBLISH_STATUS char(2) COLLATE utf8_bin DEFAULT NULL,
  QUIT_STATUS char(2) COLLATE utf8_bin DEFAULT NULL,
  CREATED_TIME datetime DEFAULT NULL,
  STATUS_TIME datetime DEFAULT NULL,
  DELETED char(1) COLLATE utf8_bin DEFAULT NULL,
  QUIT_DESC varchar(200) COLLATE utf8_bin DEFAULT NULL,
  QUIT_TIME datetime DEFAULT NULL,
  TYPE char(2) COLLATE utf8_bin DEFAULT NULL,
  TAG varchar(200) COLLATE utf8_bin DEFAULT NULL,
  SI_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  EXT longtext COLLATE utf8_bin,
  PRIMARY KEY (ID),
  KEY FK_APPLICATION__APP_CATEGORY_ID (APP_CATEGORY_ID),
  KEY FK_APPLICATION__SI_ID (SI_ID),
  CONSTRAINT FK_APPLICATION__APP_CATEGORY_ID FOREIGN KEY (APP_CATEGORY_ID) REFERENCES csop_app_category (ID),
  CONSTRAINT FK_APPLICATION__SI_ID FOREIGN KEY (SI_ID) REFERENCES csop_app_providers (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_attr_enum_category
-- ----------------------------
DROP TABLE IF EXISTS csop_attr_enum_category;
CREATE TABLE csop_attr_enum_category (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  NAME varchar(20) COLLATE utf8_bin DEFAULT NULL,
  DESCRIPTION varchar(200) COLLATE utf8_bin DEFAULT NULL,
  ENABLED char(1) COLLATE utf8_bin DEFAULT NULL,
  DELETED char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_attr_enum_value
-- ----------------------------
DROP TABLE IF EXISTS csop_attr_enum_value;
CREATE TABLE csop_attr_enum_value (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  CATEGORY_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  CONTENT varchar(50) COLLATE utf8_bin DEFAULT NULL,
  VALUE varchar(50) COLLATE utf8_bin DEFAULT NULL,
  CODE varchar(8) COLLATE utf8_bin DEFAULT NULL,
  ENABLED char(1) COLLATE utf8_bin DEFAULT NULL,
  DELETED char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_ATTR_ENUM_VALUE__CATEGORY_ID (CATEGORY_ID),
  CONSTRAINT FK_ATTR_ENUM_VALUE__CATEGORY_ID FOREIGN KEY (CATEGORY_ID) REFERENCES csop_attr_enum_category (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_bill
-- ----------------------------
DROP TABLE IF EXISTS csop_bill;
CREATE TABLE csop_bill (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  BILL_TYPE varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '0A-电子账单；0B-纸质账单',
  AMOUNT decimal(10,2) DEFAULT NULL,
  BILLING_CYCLE_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  DELIVER_STATUS char(2) COLLATE utf8_bin DEFAULT NULL,
  DELIVER_TIME datetime DEFAULT NULL,
  CREATED_TIME datetime DEFAULT NULL,
  USER_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PAID_AMOUNT decimal(10,2) DEFAULT NULL,
  PAYABLE_AMOUNT decimal(10,2) DEFAULT NULL,
  SN varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_BILL__BILLING_CYCLE_ID (BILLING_CYCLE_ID),
  KEY FK_BILL__USER_ID (USER_ID),
  CONSTRAINT FK_BILL__BILLING_CYCLE_ID FOREIGN KEY (BILLING_CYCLE_ID) REFERENCES csop_billing_cycle (ID),
  CONSTRAINT FK_BILL__USER_ID FOREIGN KEY (USER_ID) REFERENCES csop_user (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_bill_cycle_config
-- ----------------------------
DROP TABLE IF EXISTS csop_bill_cycle_config;
CREATE TABLE csop_bill_cycle_config (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  NAME varchar(20) COLLATE utf8_bin DEFAULT NULL,
  DESCRIPTION varchar(200) COLLATE utf8_bin DEFAULT NULL,
  START_DAY int(11) DEFAULT NULL,
  DURATION_DAY int(11) DEFAULT NULL,
  DUE_DURATION_DAYS int(11) DEFAULT NULL,
  DELETED char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_bill_item
-- ----------------------------
DROP TABLE IF EXISTS csop_bill_item;
CREATE TABLE csop_bill_item (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  BILL_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PROD_INST_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  CREATED_TIME datetime DEFAULT NULL,
  PRINT_ORDER int(11) DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_BILL_ITEM__BILL_ID (BILL_ID),
  KEY FK_BILL_ITEM__PROD_INST_ID (PROD_INST_ID),
  CONSTRAINT FK_BILL_ITEM__BILL_ID FOREIGN KEY (BILL_ID) REFERENCES csop_bill (ID),
  CONSTRAINT FK_BILL_ITEM__PROD_INST_ID FOREIGN KEY (PROD_INST_ID) REFERENCES csop_product_inst (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_bill_item_acct_item
-- ----------------------------
DROP TABLE IF EXISTS csop_bill_item_acct_item;
CREATE TABLE csop_bill_item_acct_item (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  BILL_ITEM_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  ACCT_ITEM_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_BILL_ITEM_ACCT_ITEM__ACCT_ITEM_ID (ACCT_ITEM_ID),
  KEY FK_BILL_ITEM_ACCT_ITEM__BILL_ITEM_ID (BILL_ITEM_ID),
  CONSTRAINT FK_BILL_ITEM_ACCT_ITEM__ACCT_ITEM_ID FOREIGN KEY (ACCT_ITEM_ID) REFERENCES csop_acct_item (ID),
  CONSTRAINT FK_BILL_ITEM_ACCT_ITEM__BILL_ITEM_ID FOREIGN KEY (BILL_ITEM_ID) REFERENCES csop_bill_item (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_billing_cycle
-- ----------------------------
DROP TABLE IF EXISTS csop_billing_cycle;
CREATE TABLE csop_billing_cycle (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  NAME varchar(50) COLLATE utf8_bin DEFAULT NULL,
  BEGIN_DATE date DEFAULT NULL,
  END_DATE date DEFAULT NULL,
  BLOCK_DATE date DEFAULT NULL,
  CYCLE_CONFIG_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_BILLING_CYCLE__CYCLE_CONFIG_ID (CYCLE_CONFIG_ID),
  CONSTRAINT FK_BILLING_CYCLE__CYCLE_CONFIG_ID FOREIGN KEY (CYCLE_CONFIG_ID) REFERENCES csop_bill_cycle_config (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_billing_cycle_user
-- ----------------------------
DROP TABLE IF EXISTS csop_billing_cycle_user;
CREATE TABLE csop_billing_cycle_user (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  USER_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  BILLING_CYCLE_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_BILLING_CYCLE_USER__BILLING_CYCLE_ID (BILLING_CYCLE_ID),
  KEY FK_BILLING_CYCLE_USER__USER_ID (USER_ID),
  CONSTRAINT FK_BILLING_CYCLE_USER__BILLING_CYCLE_ID FOREIGN KEY (BILLING_CYCLE_ID) REFERENCES csop_billing_cycle (ID),
  CONSTRAINT FK_BILLING_CYCLE_USER__USER_ID FOREIGN KEY (USER_ID) REFERENCES csop_user (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_billing_list
-- ----------------------------
DROP TABLE IF EXISTS csop_billing_list;
CREATE TABLE csop_billing_list (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  CHARGING_ITEM_DETAIL_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  TICKET_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  ACCT_ITEM_TYPE_ID varchar(32) COLLATE utf8_bin NOT NULL,
  COMMENTS varchar(200) COLLATE utf8_bin DEFAULT NULL,
  AMOUNT decimal(10,2) DEFAULT NULL,
  TYPE char(2) COLLATE utf8_bin DEFAULT NULL COMMENT '0A-预付费周期性费用;0B-后付费周期性费用;0C-预付费一次性费用;0D-后付费一次性费用;0E-使用量费用',
  START_TIME datetime DEFAULT NULL COMMENT '周期性费用时使用',
  END_TIME datetime DEFAULT NULL COMMENT '周期性费用时使用',
  CREATED_TIME datetime DEFAULT NULL,
  ACCT_ITEM_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  USER_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  SN varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PROD_INST_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_BILLING_LIST__ACCT_ITEM_TYPE_ID (ACCT_ITEM_TYPE_ID),
  KEY FK_BILLING_LIST__CHARGING_ITEM_DETAIL_ID (CHARGING_ITEM_DETAIL_ID),
  KEY FK_BILLING_LIST__TICKET_ID (TICKET_ID),
  CONSTRAINT FK_BILLING_LIST__ACCT_ITEM_TYPE_ID FOREIGN KEY (ACCT_ITEM_TYPE_ID) REFERENCES csop_acct_item_type (ID),
  CONSTRAINT FK_BILLING_LIST__CHARGING_ITEM_DETAIL_ID FOREIGN KEY (CHARGING_ITEM_DETAIL_ID) REFERENCES csop_charging_item_detail (ID),
  CONSTRAINT FK_BILLING_LIST__TICKET_ID FOREIGN KEY (TICKET_ID) REFERENCES csop_ticket_info (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_charge_history
-- ----------------------------
DROP TABLE IF EXISTS csop_charge_history;
CREATE TABLE csop_charge_history (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  ACCOUNT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  BALANCE decimal(20,10) DEFAULT NULL,
  CHARGE decimal(20,10) DEFAULT NULL,
  CREATE_TIME timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  MEMO varchar(200) COLLATE utf8_bin DEFAULT NULL,
  ACCOUNTBOOK_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  CHARGEWAY int(11) DEFAULT NULL,
  CREATEBY varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_CHARGE_HISTORY__ACCOUNT_ID (ACCOUNT_ID),
  KEY FK_CHARGE_HISTORY__ACCOUNTBOOK_ID (ACCOUNTBOOK_ID),
  CONSTRAINT FK_CHARGE_HISTORY__ACCOUNTBOOK_ID FOREIGN KEY (ACCOUNTBOOK_ID) REFERENCES csop_account_book (ID),
  CONSTRAINT FK_CHARGE_HISTORY__ACCOUNT_ID FOREIGN KEY (ACCOUNT_ID) REFERENCES csop_account (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_charge_off_detail
-- ----------------------------
DROP TABLE IF EXISTS csop_charge_off_detail;
CREATE TABLE csop_charge_off_detail (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  SN varchar(50) COLLATE utf8_bin DEFAULT NULL,
  ACCT_ITEM_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  ACCT_BOOK_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  ACCT_BOOK_SP_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  AMOUNT decimal(10,2) DEFAULT NULL,
  STATUS char(2) COLLATE utf8_bin DEFAULT NULL COMMENT '0A-已销赃;0B-已返销',
  STATUS_TIME datetime DEFAULT NULL,
  CREATED_TIME datetime DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_CHARGE_OFF_DETAIL__ACCT_ITEM_ID (ACCT_ITEM_ID),
  KEY FK_CHARGE_OFF_DETAIL__ACCT_BOOK_ID (ACCT_BOOK_ID),
  KEY FK_CHARGE_OFF_DETAIL__ACCT_BOOK_SP_ID (ACCT_BOOK_SP_ID),
  CONSTRAINT FK_CHARGE_OFF_DETAIL__ACCT_BOOK_ID FOREIGN KEY (ACCT_BOOK_ID) REFERENCES csop_account_book (ID),
  CONSTRAINT FK_CHARGE_OFF_DETAIL__ACCT_BOOK_SP_ID FOREIGN KEY (ACCT_BOOK_SP_ID) REFERENCES csop_account_book_sp (ID),
  CONSTRAINT FK_CHARGE_OFF_DETAIL__ACCT_ITEM_ID FOREIGN KEY (ACCT_ITEM_ID) REFERENCES csop_acct_item (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_charging_item
-- ----------------------------
DROP TABLE IF EXISTS csop_charging_item;
CREATE TABLE csop_charging_item (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  PROD_INST_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  USER_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  ACCT_ITEM_TYPE_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRICE_STRATEGY_SNAPSHOT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  STATUS char(2) COLLATE utf8_bin DEFAULT NULL COMMENT '0A-生效;0B-失效',
  STATUS_TIME datetime DEFAULT NULL,
  EFF_DATE date DEFAULT NULL,
  EXP_DATE date DEFAULT NULL COMMENT 'NULL代表一直有效',
  CREATED_TIME datetime DEFAULT NULL,
  LAST_CHARGING_TIME datetime DEFAULT NULL,
  NEXT_CHARGING_TIME datetime DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_CHARGING_ITEM__ACCT_ITEM_TYPE_ID (ACCT_ITEM_TYPE_ID),
  KEY FK_CHARGING_ITEM__USER_ID (USER_ID),
  CONSTRAINT FK_CHARGING_ITEM__ACCT_ITEM_TYPE_ID FOREIGN KEY (ACCT_ITEM_TYPE_ID) REFERENCES csop_acct_item_type (ID),
  CONSTRAINT FK_CHARGING_ITEM__USER_ID FOREIGN KEY (USER_ID) REFERENCES csop_user (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_charging_item_detail
-- ----------------------------
DROP TABLE IF EXISTS csop_charging_item_detail;
CREATE TABLE csop_charging_item_detail (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  CHARGING_ITEM_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRICING_INFO_SNAPSHOT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  STATUS char(2) COLLATE utf8_bin DEFAULT NULL COMMENT '0A-有效；0B-无效',
  STATUS_TIME datetime DEFAULT NULL,
  CREATED_TIME datetime DEFAULT NULL,
  LAST_CHARGING_TIME datetime DEFAULT NULL,
  CHARGING_END_DATE datetime DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_CHARGE_ITEM_DETAIL__CHARGING_ITEM_ID (CHARGING_ITEM_ID),
  KEY FK_CHARGE_ITEM_DETAIL__PRICING_INFO_SNAPSHOT_ID (PRICING_INFO_SNAPSHOT_ID),
  CONSTRAINT FK_CHARGE_ITEM_DETAIL__CHARGING_ITEM_ID FOREIGN KEY (CHARGING_ITEM_ID) REFERENCES csop_charging_item (ID),
  CONSTRAINT FK_CHARGE_ITEM_DETAIL__PRICING_INFO_SNAPSHOT_ID FOREIGN KEY (PRICING_INFO_SNAPSHOT_ID) REFERENCES csop_pricing_info_snapshot (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_complaint
-- ----------------------------
DROP TABLE IF EXISTS csop_complaint;
CREATE TABLE csop_complaint (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  SN varchar(50) COLLATE utf8_bin DEFAULT NULL,
  USER_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRODUCT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  TYPE varchar(32) COLLATE utf8_bin DEFAULT NULL,
  CONTENT varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  CREATED_TIME datetime DEFAULT NULL,
  STATUS char(2) COLLATE utf8_bin DEFAULT NULL COMMENT '0A-未处理；0B-已处理',
  ASSIGNER_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  HANDLE_TIME datetime DEFAULT NULL,
  FEEDBACK varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  EVALUATE char(2) COLLATE utf8_bin DEFAULT NULL COMMENT '0A:满意，0B:不满意',
  EVALUATE_TIME datetime DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_COMPLAINT__USER_ID (USER_ID),
  KEY FK_COMPLAINT__PRODUCT_ID (PRODUCT_ID),
  KEY FK_COMPLAINT__ASSIGNER_ID (ASSIGNER_ID),
  CONSTRAINT FK_COMPLAINT__ASSIGNER_ID FOREIGN KEY (ASSIGNER_ID) REFERENCES csop_sys_user (ID),
  CONSTRAINT FK_COMPLAINT__PRODUCT_ID FOREIGN KEY (PRODUCT_ID) REFERENCES csop_product (ID),
  CONSTRAINT FK_COMPLAINT__USER_ID FOREIGN KEY (USER_ID) REFERENCES csop_user (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_contract
-- ----------------------------
DROP TABLE IF EXISTS csop_contract;
CREATE TABLE csop_contract (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  USER_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  CONTRACT_CODE varchar(500) COLLATE utf8_bin DEFAULT NULL,
  CONTRACT_TYPE varchar(8) COLLATE utf8_bin DEFAULT NULL,
  CONTRACT_CONTENT varchar(2000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_CONTRACT__USER_ID (USER_ID),
  CONSTRAINT FK_CONTRACT__USER_ID FOREIGN KEY (USER_ID) REFERENCES csop_user (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_customer
-- ----------------------------
DROP TABLE IF EXISTS csop_customer;
CREATE TABLE csop_customer (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  CUSTOMER_CODE varchar(200) COLLATE utf8_bin DEFAULT NULL,
  NAME varchar(200) COLLATE utf8_bin DEFAULT NULL,
  TYPE int(11) DEFAULT NULL,
  SEX int(11) DEFAULT NULL,
  PROFESSION varchar(100) COLLATE utf8_bin DEFAULT NULL,
  CREDENTIALS_TYPE varchar(11) COLLATE utf8_bin DEFAULT NULL,
  CREDENTIALS_NUMBER varchar(200) COLLATE utf8_bin DEFAULT NULL,
  EMAIL varchar(100) COLLATE utf8_bin DEFAULT NULL,
  WORK_NATURE varchar(200) COLLATE utf8_bin DEFAULT NULL,
  WORK_UNIT varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  POSITION varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  LP_ORG_NAME varchar(2000) COLLATE utf8_bin DEFAULT NULL,
  CONTACT varchar(300) COLLATE utf8_bin DEFAULT NULL,
  LP_ORG_NATURE varchar(200) COLLATE utf8_bin DEFAULT NULL,
  CREDIBILITY varchar(11) COLLATE utf8_bin DEFAULT NULL,
  LEVEL int(11) DEFAULT NULL,
  STATUS int(11) DEFAULT NULL,
  ADDRESS varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  MOBILEPHONE varchar(100) COLLATE utf8_bin DEFAULT NULL,
  LANDLINEPHONE varchar(100) COLLATE utf8_bin DEFAULT NULL,
  POSTCODE varchar(50) COLLATE utf8_bin DEFAULT NULL,
  CREATEBY varchar(32) COLLATE utf8_bin DEFAULT NULL,
  CREATETIME datetime DEFAULT NULL,
  UPDATEBY varchar(32) COLLATE utf8_bin DEFAULT NULL,
  UPDATETIME datetime DEFAULT NULL,
  BIRTHDATE datetime DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_dhcp_server
-- ----------------------------
DROP TABLE IF EXISTS csop_dhcp_server;
CREATE TABLE csop_dhcp_server (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  NAME varchar(50) COLLATE utf8_bin DEFAULT NULL,
  DESCRIPTION varchar(200) COLLATE utf8_bin DEFAULT NULL,
  HOST varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PORT varchar(10) COLLATE utf8_bin DEFAULT NULL,
  USERNAME varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PASSWORD varchar(100) COLLATE utf8_bin DEFAULT NULL,
  ENABLED char(1) COLLATE utf8_bin DEFAULT NULL,
  DELETED char(1) COLLATE utf8_bin DEFAULT NULL,
  CREATED_TIME datetime DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_email_notification
-- ----------------------------
DROP TABLE IF EXISTS csop_email_notification;
CREATE TABLE csop_email_notification (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  USER_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  TYPE char(2) COLLATE utf8_bin DEFAULT NULL,
  EXTRA varchar(2000) COLLATE utf8_bin DEFAULT NULL,
  CREATED_TIME datetime DEFAULT NULL,
  DELIVERED char(1) COLLATE utf8_bin DEFAULT NULL,
  DELIVERED_TIME datetime DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_fault
-- ----------------------------
DROP TABLE IF EXISTS csop_fault;
CREATE TABLE csop_fault (
  ID varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'ID',
  SN varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '故障编号',
  ORIGINATE varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '发起方',
  FAULT_DATE datetime DEFAULT NULL COMMENT '故障时间',
  SI_ID varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '应用提供商ID',
  APP_ID varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '应用ID',
  TYPE varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '故障类型',
  LEVEL varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '故障级别',
  DESCRIPTION varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '故障描述',
  OPERATOR varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '添加人',
  REPORT_DATE datetime DEFAULT NULL COMMENT '报障时间',
  HANDLE_OPERATOR varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '处理人员',
  FAULT_STATUS varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '处理状态',
  HANDLE_DATE datetime DEFAULT NULL COMMENT '处理时间',
  STATUS varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '逻辑删除标识',
  PRIMARY KEY (ID),
  KEY FK_FAULT__SI_ID (SI_ID),
  KEY FK_FAULT__APP_ID (APP_ID),
  CONSTRAINT FK_FAULT__APP_ID FOREIGN KEY (APP_ID) REFERENCES csop_application (ID),
  CONSTRAINT FK_FAULT__SI_ID FOREIGN KEY (SI_ID) REFERENCES csop_app_providers (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for csop_fault_feedback
-- ----------------------------
DROP TABLE IF EXISTS csop_fault_feedback;
CREATE TABLE csop_fault_feedback (
  ID varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'ID',
  FAULT_ID varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '故障ID',
  FEEDBACK varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '反馈信息',
  FB_DATE datetime DEFAULT NULL COMMENT '反馈时间',
  OPERATOR varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '反馈人',
  PRIMARY KEY (ID),
  KEY FK_FAULT_FEEDBACK__FAULT_ID (FAULT_ID),
  CONSTRAINT FK_FAULT_FEEDBACK__FAULT_ID FOREIGN KEY (FAULT_ID) REFERENCES csop_fault (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for csop_group
-- ----------------------------
DROP TABLE IF EXISTS csop_group;
CREATE TABLE csop_group (
  ID varchar(32) COLLATE utf8_bin NOT NULL COMMENT '组ID',
  NAME varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '组名称',
  DESCRIPTION varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT '组描述',
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for csop_image_type
-- ----------------------------
DROP TABLE IF EXISTS csop_image_type;
CREATE TABLE csop_image_type (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  NAME varchar(20) COLLATE utf8_bin DEFAULT NULL,
  DESCRIPTION varchar(200) COLLATE utf8_bin DEFAULT NULL,
  WIDTH int(11) DEFAULT NULL,
  HEIGHT int(11) DEFAULT NULL,
  MAX_SIZE bigint(20) DEFAULT NULL,
  PATTERN varchar(100) COLLATE utf8_bin DEFAULT NULL,
  UPLOAD_PATH varchar(100) COLLATE utf8_bin DEFAULT NULL,
  DEFAUL_IMG_PATH varchar(200) COLLATE utf8_bin DEFAULT NULL,
  SN varchar(8) COLLATE utf8_bin DEFAULT NULL,
  MAX_COPIES int(11) DEFAULT NULL,
  ENABLED char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '0-启动；1-停止',
  DELETED char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '0-未删除；1-删除',
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_numeric_unit
-- ----------------------------
DROP TABLE IF EXISTS csop_numeric_unit;
CREATE TABLE csop_numeric_unit (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  PARENT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  NAME varchar(100) COLLATE utf8_bin DEFAULT NULL,
  TYPE varchar(100) COLLATE utf8_bin DEFAULT NULL,
  TIMES decimal(20,10) DEFAULT NULL,
  CODE varchar(20) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_NUMERIC_UNIT__PARENT_ID (PARENT_ID),
  CONSTRAINT FK_NUMERIC_UNIT__PARENT_ID FOREIGN KEY (PARENT_ID) REFERENCES csop_numeric_unit (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_orders
-- ----------------------------
DROP TABLE IF EXISTS csop_orders;
CREATE TABLE csop_orders (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  SN varchar(32) COLLATE utf8_bin DEFAULT NULL,
  USER_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PROD_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  CREATED_TIME datetime DEFAULT NULL,
  EFF_DATE datetime DEFAULT NULL,
  EXP_DATE datetime DEFAULT NULL,
  TYPE varchar(2) COLLATE utf8_bin DEFAULT NULL,
  PARENT_ORDER_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRICECOMP_SNAPSHOT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  ORDERS_NUM int(11) DEFAULT NULL,
  ORDERS_STATE varchar(4) COLLATE utf8_bin DEFAULT NULL,
  PAY_TIME datetime DEFAULT NULL,
  PRE_FEE decimal(10,2) DEFAULT NULL,
  TIME_UNIT varchar(32) COLLATE utf8_bin DEFAULT NULL,
  DELETED varchar(1) COLLATE utf8_bin DEFAULT NULL,
  STATUS_TIME datetime DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_ORDERS__USER_ID (USER_ID),
  KEY FK_ORDERS__PARENT_ORDER_ID (PARENT_ORDER_ID),
  KEY FK_ORDERS__PROD_ID (PROD_ID),
  KEY FK_ORDERS__TIME_UNIT (TIME_UNIT),
  CONSTRAINT FK_ORDERS__PARENT_ORDER_ID FOREIGN KEY (PARENT_ORDER_ID) REFERENCES csop_orders (ID),
  CONSTRAINT FK_ORDERS__PROD_ID FOREIGN KEY (PROD_ID) REFERENCES csop_product (ID),
  CONSTRAINT FK_ORDERS__TIME_UNIT FOREIGN KEY (TIME_UNIT) REFERENCES csop_time_unit (ID),
  CONSTRAINT FK_ORDERS__USER_ID FOREIGN KEY (USER_ID) REFERENCES csop_user (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_org
-- ----------------------------
DROP TABLE IF EXISTS csop_org;
CREATE TABLE csop_org (
  ID varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '组织ID',
  PARENTID varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '父类组织ID',
  NAME varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '组织名称',
  USERID varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '所属企业用户ID',
  PRIMARY KEY (ID),
  KEY CSOP_ORG__USERID (USERID),
  KEY CSOP_ORG__PARENTID (PARENTID),
  CONSTRAINT CSOP_ORG__PARENTID FOREIGN KEY (PARENTID) REFERENCES csop_org (ID),
  CONSTRAINT CSOP_ORG__USERID FOREIGN KEY (USERID) REFERENCES csop_user (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for csop_pay_receipt
-- ----------------------------
DROP TABLE IF EXISTS csop_pay_receipt;
CREATE TABLE csop_pay_receipt (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  ORDER_ID varchar(32) COLLATE utf8_bin NOT NULL,
  PAY_AMOUNT decimal(10,2) DEFAULT NULL,
  CREATED_TIME datetime DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_pay_receipt_detail
-- ----------------------------
DROP TABLE IF EXISTS csop_pay_receipt_detail;
CREATE TABLE csop_pay_receipt_detail (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  PAY_RECEIPT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  ACCT_BOOK_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  SP_ACCT_BOOK_ITEM_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PAY_AMOUNT decimal(10,2) DEFAULT NULL,
  CREATED_TIME datetime DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_PAY_RECEIPT_DETAIL__PAY_RECEIPT_ID (PAY_RECEIPT_ID),
  CONSTRAINT FK_PAY_RECEIPT_DETAIL__PAY_RECEIPT_ID FOREIGN KEY (PAY_RECEIPT_ID) REFERENCES csop_pay_receipt (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_portal_log
-- ----------------------------
DROP TABLE IF EXISTS csop_portal_log;
CREATE TABLE csop_portal_log (
  id varchar(32) COLLATE utf8_bin NOT NULL,
  content varchar(128) COLLATE utf8_bin NOT NULL,
  created_by varchar(16) COLLATE utf8_bin NOT NULL,
  creation_date datetime NOT NULL,
  log_level varchar(8) COLLATE utf8_bin DEFAULT NULL,
  log_type varchar(4) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY id (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_pricing_combine
-- ----------------------------
DROP TABLE IF EXISTS csop_pricing_combine;
CREATE TABLE csop_pricing_combine (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  PRICING_PLAN_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRICING_STRATEGY_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  EFF_DATE date DEFAULT NULL,
  EXP_DATE date DEFAULT NULL,
  PRIORITY int(11) DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_PRICING_COMBINE__PRICING_PLAN_ID (PRICING_PLAN_ID),
  KEY FK_PRICING_COMBINE__PRICING_STRATEGY_ID (PRICING_STRATEGY_ID),
  CONSTRAINT FK_PRICING_COMBINE__PRICING_PLAN_ID FOREIGN KEY (PRICING_PLAN_ID) REFERENCES csop_pricing_plan (ID),
  CONSTRAINT FK_PRICING_COMBINE__PRICING_STRATEGY_ID FOREIGN KEY (PRICING_STRATEGY_ID) REFERENCES csop_pricing_strategy (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_pricing_info
-- ----------------------------
DROP TABLE IF EXISTS csop_pricing_info;
CREATE TABLE csop_pricing_info (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  NAME varchar(100) COLLATE utf8_bin DEFAULT NULL,
  DESCRIPTION varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  TARIFF_TYPE char(2) COLLATE utf8_bin DEFAULT NULL COMMENT '0A-周期性费用；0B-一次性费用；0C-使用量费用',
  CALC_METHOD char(2) COLLATE utf8_bin DEFAULT NULL COMMENT '0A-直接计算；0B-离散型计算；0C-阶梯型计算；',
  PRICING_META_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRICING_OBJ_TYPE char(2) COLLATE utf8_bin DEFAULT NULL COMMENT '0A-产品；0B-定价元数据',
  DELETED char(1) COLLATE utf8_bin DEFAULT NULL,
  CHARGE_MODE char(2) COLLATE utf8_bin DEFAULT NULL,
  ACCT_ITEM_TYPE_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_PRICING_INFO__PRICING_META_ID (PRICING_META_ID),
  KEY FK_PRICING_INFO__ACCT_ITEM_TYPE_ID (ACCT_ITEM_TYPE_ID),
  CONSTRAINT FK_PRICING_INFO__ACCT_ITEM_TYPE_ID FOREIGN KEY (ACCT_ITEM_TYPE_ID) REFERENCES csop_acct_item_type (ID),
  CONSTRAINT FK_PRICING_INFO__PRICING_META_ID FOREIGN KEY (PRICING_META_ID) REFERENCES csop_pricing_meta (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_pricing_info_combine
-- ----------------------------
DROP TABLE IF EXISTS csop_pricing_info_combine;
CREATE TABLE csop_pricing_info_combine (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  PRICING_INFO_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRICING_STRATEGY_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_PRICING_INFO_COMBINE__PRICING_STRATEGY_ID (PRICING_STRATEGY_ID),
  KEY FK_PRICING_INFO_COMBINE__PRICING_INFO_ID (PRICING_INFO_ID),
  CONSTRAINT FK_PRICING_INFO_COMBINE__PRICING_INFO_ID FOREIGN KEY (PRICING_INFO_ID) REFERENCES csop_pricing_info (ID),
  CONSTRAINT FK_PRICING_INFO_COMBINE__PRICING_STRATEGY_ID FOREIGN KEY (PRICING_STRATEGY_ID) REFERENCES csop_pricing_strategy (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_pricing_info_combine_snapshot
-- ----------------------------
DROP TABLE IF EXISTS csop_pricing_info_combine_snapshot;
CREATE TABLE csop_pricing_info_combine_snapshot (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  PRICING_INFO_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRICING_STRATEGY_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_PINFOCOMB_ST__PRICING_STRATEGY_ID (PRICING_STRATEGY_ID),
  KEY FK_PINFOCOMB_ST__PRICING_INFO_ID (PRICING_INFO_ID),
  CONSTRAINT FK_PINFOCOMB_ST__PRICING_INFO_ID FOREIGN KEY (PRICING_INFO_ID) REFERENCES csop_pricing_info_snapshot (ID),
  CONSTRAINT FK_PINFOCOMB_ST__PRICING_STRATEGY_ID FOREIGN KEY (PRICING_STRATEGY_ID) REFERENCES csop_pricing_strategy_snapshot (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_pricing_info_snapshot
-- ----------------------------
DROP TABLE IF EXISTS csop_pricing_info_snapshot;
CREATE TABLE csop_pricing_info_snapshot (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  NAME varchar(100) COLLATE utf8_bin DEFAULT NULL,
  DESCRIPTION varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  TARIFF_TYPE char(2) COLLATE utf8_bin DEFAULT NULL COMMENT '0A-周期性费用；0B-一次性费用；0C-使用量费用',
  CALC_METHOD char(2) COLLATE utf8_bin DEFAULT NULL COMMENT '0A-直接计算；0B-离散型计算；0C-阶梯型计算；',
  PRICING_META_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRICING_OBJ_TYPE char(2) COLLATE utf8_bin DEFAULT NULL COMMENT '0A-产品；0B-定价元数据',
  CHARGE_MODE char(2) COLLATE utf8_bin DEFAULT NULL,
  ACCT_ITEM_TYPE_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_PRICING_INFO_SNAPSHOT__ACCT_ITEM_TYPE_ID (ACCT_ITEM_TYPE_ID),
  CONSTRAINT FK_PRICING_INFO_SNAPSHOT__ACCT_ITEM_TYPE_ID FOREIGN KEY (ACCT_ITEM_TYPE_ID) REFERENCES csop_acct_item_type (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_pricing_meta
-- ----------------------------
DROP TABLE IF EXISTS csop_pricing_meta;
CREATE TABLE csop_pricing_meta (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  NAME varchar(100) COLLATE utf8_bin DEFAULT NULL,
  DESCRIPTION varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  ENABLED char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_pricing_plan
-- ----------------------------
DROP TABLE IF EXISTS csop_pricing_plan;
CREATE TABLE csop_pricing_plan (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  NAME varchar(100) COLLATE utf8_bin DEFAULT NULL,
  DESCRIPTION varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  ENABLED char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '0-停用；1-启用',
  DELETED char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '0-未删除；1-删除',
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_pricing_plan_combine
-- ----------------------------
DROP TABLE IF EXISTS csop_pricing_plan_combine;
CREATE TABLE csop_pricing_plan_combine (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  PRICING_PLAN_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PROD_ID varchar(32) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (ID),
  KEY FK_PRICING_PLAN_CB__PRICING_PLAN_ID (PRICING_PLAN_ID),
  KEY FK_PRICING_PLAN__PROD_ID (PROD_ID),
  CONSTRAINT FK_PRICING_PLAN_CB__PRICING_PLAN_ID FOREIGN KEY (PRICING_PLAN_ID) REFERENCES csop_pricing_plan (ID),
  CONSTRAINT FK_PRICING_PLAN__PROD_ID FOREIGN KEY (PROD_ID) REFERENCES csop_product (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_pricing_strategy
-- ----------------------------
DROP TABLE IF EXISTS csop_pricing_strategy;
CREATE TABLE csop_pricing_strategy (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  NAME varchar(100) COLLATE utf8_bin DEFAULT NULL,
  DESCRIPTION varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  PRICING_INFO_TAG varchar(128) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_pricing_strategy_snapshot
-- ----------------------------
DROP TABLE IF EXISTS csop_pricing_strategy_snapshot;
CREATE TABLE csop_pricing_strategy_snapshot (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  NAME varchar(100) COLLATE utf8_bin DEFAULT NULL,
  DESCRIPTION varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  PRICING_INFO_TAG varchar(128) COLLATE utf8_bin DEFAULT NULL,
  ORDER_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_PRICING_STRATEGY_SNAP__ORDER_ID (ORDER_ID),
  CONSTRAINT FK_PRICING_STRATEGY_SNAP__ORDER_ID FOREIGN KEY (ORDER_ID) REFERENCES csop_orders (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_prod_billing_item
-- ----------------------------
DROP TABLE IF EXISTS csop_prod_billing_item;
CREATE TABLE csop_prod_billing_item (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  PROD_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRICING_META_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  APP_QUOTA_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_PROD_BILL_ITEM__PRICING_META_ID (PRICING_META_ID),
  KEY FK_BILLING_ITEM__PROD_ID (PROD_ID),
  KEY FK_PROD_BILL_ITEM__APP_QUOTA_ID (APP_QUOTA_ID),
  CONSTRAINT FK_BILLING_ITEM__PROD_ID FOREIGN KEY (PROD_ID) REFERENCES csop_product (ID),
  CONSTRAINT FK_PROD_BILL_ITEM__APP_QUOTA_ID FOREIGN KEY (APP_QUOTA_ID) REFERENCES csop_app_quota (ID),
  CONSTRAINT FK_PROD_BILL_ITEM__PRICING_META_ID FOREIGN KEY (PRICING_META_ID) REFERENCES csop_pricing_meta (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_prod_file
-- ----------------------------
DROP TABLE IF EXISTS csop_prod_file;
CREATE TABLE csop_prod_file (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  UPLOAD_DATE timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FILE_URL varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '例如：../upload/icon/${filename}  (兰州用)',
  FILE_TYPE varchar(4) COLLATE utf8_bin DEFAULT NULL,
  PROD_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  SIZE bigint(20) DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_PROD_FILE__PROD_ID (PROD_ID),
  CONSTRAINT FK_PROD_FILE__PROD_ID FOREIGN KEY (PROD_ID) REFERENCES csop_product (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_prod_image
-- ----------------------------
DROP TABLE IF EXISTS csop_prod_image;
CREATE TABLE csop_prod_image (
  PIC_TYPE varchar(32) COLLATE utf8_bin DEFAULT NULL,
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  PROD_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PATH varchar(100) COLLATE utf8_bin DEFAULT NULL,
  WIDTH int(11) DEFAULT NULL,
  HEIGHT int(11) DEFAULT NULL,
  SIZE bigint(20) DEFAULT NULL,
  UPLOAD_TIME datetime DEFAULT NULL,
  ENABLED char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_PROD_IMAGE__PIC_TYPE (PIC_TYPE),
  KEY FK_PROD_IMAGE__PROD_ID (PROD_ID),
  CONSTRAINT FK_PROD_IMAGE__PIC_TYPE FOREIGN KEY (PIC_TYPE) REFERENCES csop_image_type (ID),
  CONSTRAINT FK_PROD_IMAGE__PROD_ID FOREIGN KEY (PROD_ID) REFERENCES csop_product (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_product
-- ----------------------------
DROP TABLE IF EXISTS csop_product;
CREATE TABLE csop_product (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  NAME varchar(50) COLLATE utf8_bin DEFAULT NULL,
  CATEGORY_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  APP_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  STATUS char(1) COLLATE utf8_bin DEFAULT NULL,
  CREATE_TIME datetime DEFAULT NULL,
  PRICE_PLAN_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  KEYWORDS varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT ' ',
  ACTIVE_DATE timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  INTRO varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  DESCRIPTION varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  SN varchar(32) COLLATE utf8_bin DEFAULT NULL,
  DELETED char(1) COLLATE utf8_bin DEFAULT '1',
  PACKAGED char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_PRODUCT__APP_ID (APP_ID),
  KEY FK_PRODUCT__PRICE_PLAN_ID (PRICE_PLAN_ID),
  KEY FK_PRODUCT__CATEGORY_ID (CATEGORY_ID),
  CONSTRAINT FK_PRODUCT__APP_ID FOREIGN KEY (APP_ID) REFERENCES csop_application (ID),
  CONSTRAINT FK_PRODUCT__CATEGORY_ID FOREIGN KEY (CATEGORY_ID) REFERENCES csop_product_category (ID),
  CONSTRAINT FK_PRODUCT__PRICE_PLAN_ID FOREIGN KEY (PRICE_PLAN_ID) REFERENCES csop_pricing_plan (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_product_category
-- ----------------------------
DROP TABLE IF EXISTS csop_product_category;
CREATE TABLE csop_product_category (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  NAME varchar(200) COLLATE utf8_bin DEFAULT NULL,
  DES varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  PARENT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  DELETED char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_PROD_CATEGORY__PARENT_ID (PARENT_ID),
  CONSTRAINT FK_PROD_CATEGORY__PARENT_ID FOREIGN KEY (PARENT_ID) REFERENCES csop_product_category (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_product_inst
-- ----------------------------
DROP TABLE IF EXISTS csop_product_inst;
CREATE TABLE csop_product_inst (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  USER_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PROD_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  CREAET_TIME timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CREATOR varchar(32) COLLATE utf8_bin DEFAULT NULL,
  STATUS char(1) COLLATE utf8_bin DEFAULT NULL,
  START_DATE datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  END_DATE datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  SN varchar(32) COLLATE utf8_bin DEFAULT NULL,
  IS_TRY_USE varchar(1) COLLATE utf8_bin DEFAULT NULL,
  PRICING_STRATEGY_SNAP_SHOT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  ORDER_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_PRODUCT_INST__USER_ID (USER_ID),
  KEY FK_PROD_INST__PROD_ID (PROD_ID),
  KEY FK_PROD_INST__PRICING_STRATEGY_SNAP_SHOT_ID (PRICING_STRATEGY_SNAP_SHOT_ID),
  KEY FK_PROD_INST__ORDER_ID (ORDER_ID),
  CONSTRAINT FK_PRODUCT_INST__USER_ID FOREIGN KEY (USER_ID) REFERENCES csop_user (ID),
  CONSTRAINT FK_PROD_INST__ORDER_ID FOREIGN KEY (ORDER_ID) REFERENCES csop_orders (ID),
  CONSTRAINT FK_PROD_INST__PRICING_STRATEGY_SNAP_SHOT_ID FOREIGN KEY (PRICING_STRATEGY_SNAP_SHOT_ID) REFERENCES csop_pricing_strategy_snapshot (ID),
  CONSTRAINT FK_PROD_INST__PROD_ID FOREIGN KEY (PROD_ID) REFERENCES csop_product (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_receipt_disburse_statement
-- ----------------------------
DROP TABLE IF EXISTS csop_receipt_disburse_statement;
CREATE TABLE csop_receipt_disburse_statement (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  ACCOUNT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  ACCOUNTBOOK_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  TYPE varchar(2) COLLATE utf8_bin DEFAULT NULL,
  RECEIPT decimal(20,2) DEFAULT NULL,
  DISBURSE decimal(20,2) DEFAULT NULL,
  BALANCE decimal(20,2) DEFAULT NULL,
  MEMO varchar(200) COLLATE utf8_bin DEFAULT NULL,
  CREATE_TIME timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CREATEBY varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_RDST__ACCOUNTBOOK_ID (ACCOUNTBOOK_ID),
  KEY FK_RDST__ACCOUNT_ID (ACCOUNT_ID),
  CONSTRAINT FK_RDST__ACCOUNTBOOK_ID FOREIGN KEY (ACCOUNTBOOK_ID) REFERENCES csop_account_book (ID),
  CONSTRAINT FK_RDST__ACCOUNT_ID FOREIGN KEY (ACCOUNT_ID) REFERENCES csop_account (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_res_attr_value
-- ----------------------------
DROP TABLE IF EXISTS csop_res_attr_value;
CREATE TABLE csop_res_attr_value (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  RES_ATTR_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  RES_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  VALUE varchar(50) COLLATE utf8_bin DEFAULT NULL,
  ATTR_ENUM_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_RES_ATTR_VALUE__ATTR_ENUM_ID (ATTR_ENUM_ID),
  KEY FK_RES_ATTR_VALUE__RES_ID (RES_ID),
  KEY FK_RES_ATTR_VALUE__RES_ATTR_ID (RES_ATTR_ID),
  CONSTRAINT FK_RES_ATTR_VALUE__ATTR_ENUM_ID FOREIGN KEY (ATTR_ENUM_ID) REFERENCES csop_attr_enum_value (ID),
  CONSTRAINT FK_RES_ATTR_VALUE__RES_ATTR_ID FOREIGN KEY (RES_ATTR_ID) REFERENCES csop_res_attribute (ID),
  CONSTRAINT FK_RES_ATTR_VALUE__RES_ID FOREIGN KEY (RES_ID) REFERENCES csop_resource (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_res_attribute
-- ----------------------------
DROP TABLE IF EXISTS csop_res_attribute;
CREATE TABLE csop_res_attribute (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  RES_TYPE_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  NAME varchar(20) COLLATE utf8_bin DEFAULT NULL,
  DESCRIPTION varchar(200) COLLATE utf8_bin DEFAULT NULL,
  DISPLAY_TYPE int(11) DEFAULT NULL,
  TYPE int(11) DEFAULT NULL,
  CODE varchar(8) COLLATE utf8_bin DEFAULT NULL,
  ENUM_CATEGORY_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  SEQUENCED int(11) DEFAULT NULL,
  ENABLED char(1) COLLATE utf8_bin DEFAULT NULL,
  DELETED char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_RES_ATTRIBUTE__ENUM_CATEGORY_ID (ENUM_CATEGORY_ID),
  KEY FK_RES_ATTRIBUTE__RES_TYPE_ID (RES_TYPE_ID),
  CONSTRAINT FK_RES_ATTRIBUTE__ENUM_CATEGORY_ID FOREIGN KEY (ENUM_CATEGORY_ID) REFERENCES csop_attr_enum_category (ID),
  CONSTRAINT FK_RES_ATTRIBUTE__RES_TYPE_ID FOREIGN KEY (RES_TYPE_ID) REFERENCES csop_res_type (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_res_instance
-- ----------------------------
DROP TABLE IF EXISTS csop_res_instance;
CREATE TABLE csop_res_instance (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  RES_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  SN varchar(32) COLLATE utf8_bin DEFAULT NULL,
  STATUS int(11) DEFAULT NULL,
  CREATED_TIME datetime DEFAULT NULL,
  STATUS_TIME datetime DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_RES_INSTANCE__RES_ID (RES_ID),
  CONSTRAINT FK_RES_INSTANCE__RES_ID FOREIGN KEY (RES_ID) REFERENCES csop_resource (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_res_instance_vm
-- ----------------------------
DROP TABLE IF EXISTS csop_res_instance_vm;
CREATE TABLE csop_res_instance_vm (
  PARENT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PUBLIC_IP varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIVATE_IP varchar(32) COLLATE utf8_bin DEFAULT NULL,
  OPERATING_STATE int(11) DEFAULT NULL,
  POOL_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  uuid varchar(50) COLLATE utf8_bin DEFAULT NULL,
  VM_NAME varchar(32) COLLATE utf8_bin DEFAULT NULL,
  LOGIN_NAME varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PASSWORD varchar(100) COLLATE utf8_bin DEFAULT NULL,
  MAC_ADDRESS varchar(50) COLLATE utf8_bin DEFAULT NULL,
  KEY FK_RES_INSTANCE_VM__POOL_ID (POOL_ID),
  KEY FK_RES_INSTANCE_VM__PARENT_ID (PARENT_ID),
  CONSTRAINT FK_RES_INSTANCE_VM__PARENT_ID FOREIGN KEY (PARENT_ID) REFERENCES csop_res_instance (ID),
  CONSTRAINT FK_RES_INSTANCE_VM__POOL_ID FOREIGN KEY (POOL_ID) REFERENCES csop_res_vm_pool (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_res_type
-- ----------------------------
DROP TABLE IF EXISTS csop_res_type;
CREATE TABLE csop_res_type (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  NAME varchar(20) COLLATE utf8_bin DEFAULT NULL,
  DESCRIPTION varchar(200) COLLATE utf8_bin DEFAULT NULL,
  CODE varchar(8) COLLATE utf8_bin DEFAULT NULL,
  DELETED char(1) COLLATE utf8_bin DEFAULT NULL,
  ENABLED char(1) COLLATE utf8_bin DEFAULT NULL,
  STATUS_TIME datetime DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_res_vm_pool
-- ----------------------------
DROP TABLE IF EXISTS csop_res_vm_pool;
CREATE TABLE csop_res_vm_pool (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  NAME varchar(20) COLLATE utf8_bin DEFAULT NULL,
  DESCRIPTION varchar(200) COLLATE utf8_bin DEFAULT NULL,
  MASTER_IP varchar(32) COLLATE utf8_bin DEFAULT NULL,
  LOGIN_NAME varchar(20) COLLATE utf8_bin DEFAULT NULL,
  PASSWORD varchar(100) COLLATE utf8_bin DEFAULT NULL,
  ENABLED char(1) COLLATE utf8_bin DEFAULT NULL,
  DELETED char(1) COLLATE utf8_bin DEFAULT NULL,
  DHCP_SERVER_ID varchar(32) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (ID),
  KEY FK_VM_POOL__DHCP_SERVER_ID (DHCP_SERVER_ID),
  CONSTRAINT FK_VM_POOL__DHCP_SERVER_ID FOREIGN KEY (DHCP_SERVER_ID) REFERENCES csop_dhcp_server (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_res_work_order
-- ----------------------------
DROP TABLE IF EXISTS csop_res_work_order;
CREATE TABLE csop_res_work_order (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  RES_INST_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  SN varchar(32) COLLATE utf8_bin DEFAULT NULL,
  TYPE char(2) COLLATE utf8_bin DEFAULT NULL,
  STATUS int(11) DEFAULT NULL,
  CREATED_TIME datetime DEFAULT NULL,
  STATUS_TIME datetime DEFAULT NULL,
  DELETED char(1) COLLATE utf8_bin DEFAULT NULL,
  WORKER varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_RES_WORK_ORDER__RES_INST_ID (RES_INST_ID),
  CONSTRAINT FK_RES_WORK_ORDER__RES_INST_ID FOREIGN KEY (RES_INST_ID) REFERENCES csop_res_instance (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_res_work_order_log
-- ----------------------------
DROP TABLE IF EXISTS csop_res_work_order_log;
CREATE TABLE csop_res_work_order_log (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  WORK_ORDER_ID varchar(32) COLLATE utf8_bin NOT NULL,
  CONTENT varchar(200) COLLATE utf8_bin DEFAULT NULL,
  CREATED_BY varchar(32) COLLATE utf8_bin NOT NULL,
  CREATED_TIME datetime DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_RES_ORDER_LOG__WORK_ORDER_ID (WORK_ORDER_ID),
  CONSTRAINT FK_RES_ORDER_LOG__WORK_ORDER_ID FOREIGN KEY (WORK_ORDER_ID) REFERENCES csop_res_work_order (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_resource
-- ----------------------------
DROP TABLE IF EXISTS csop_resource;
CREATE TABLE csop_resource (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  RES_TYPE_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  NAME varchar(20) COLLATE utf8_bin DEFAULT NULL,
  DESCRIPTION varchar(200) COLLATE utf8_bin DEFAULT NULL,
  ENABLED char(1) COLLATE utf8_bin DEFAULT NULL,
  DELETED char(1) COLLATE utf8_bin DEFAULT NULL,
  CREATED_TIME datetime DEFAULT NULL,
  STATUS_TIME datetime DEFAULT NULL,
  SN varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PURPOSE char(2) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_RESOURCE__RES_TYPE_ID (RES_TYPE_ID),
  CONSTRAINT FK_RESOURCE__RES_TYPE_ID FOREIGN KEY (RES_TYPE_ID) REFERENCES csop_res_type (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_sys_bizz_proc
-- ----------------------------
DROP TABLE IF EXISTS csop_sys_bizz_proc;
CREATE TABLE csop_sys_bizz_proc (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  SN varchar(32) COLLATE utf8_bin NOT NULL,
  PORC_NAME varchar(64) COLLATE utf8_bin NOT NULL,
  PROC_DESC varchar(128) COLLATE utf8_bin DEFAULT NULL,
  ENABLE int(11) NOT NULL,
  CREATED_BY varchar(16) COLLATE utf8_bin DEFAULT NULL,
  CREATION_DATE datetime DEFAULT NULL,
  MODIFIED_BY varchar(16) COLLATE utf8_bin DEFAULT NULL,
  MODIFY_DATE datetime DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_sys_bizz_proc_dtl
-- ----------------------------
DROP TABLE IF EXISTS csop_sys_bizz_proc_dtl;
CREATE TABLE csop_sys_bizz_proc_dtl (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  PROC_ID varchar(32) COLLATE utf8_bin NOT NULL,
  SN varchar(32) COLLATE utf8_bin NOT NULL,
  WORKFLOW_NAME varchar(64) COLLATE utf8_bin NOT NULL,
  WORKFLOW_DESC varchar(128) COLLATE utf8_bin DEFAULT NULL,
  VERSION varchar(16) COLLATE utf8_bin NOT NULL,
  ADDRESS varchar(1024) COLLATE utf8_bin NOT NULL,
  ENABLE int(11) NOT NULL,
  CREATED_BY varchar(16) COLLATE utf8_bin DEFAULT NULL,
  CREATION_DATE datetime DEFAULT NULL,
  MODIFIED_BY varchar(16) COLLATE utf8_bin DEFAULT NULL,
  MODIFY_DATE datetime DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_SYS_BIZZ_PROC_DTL__PROC_ID (PROC_ID),
  CONSTRAINT FK_SYS_BIZZ_PROC_DTL__PROC_ID FOREIGN KEY (PROC_ID) REFERENCES csop_sys_bizz_proc (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_sys_btn
-- ----------------------------
DROP TABLE IF EXISTS csop_sys_btn;
CREATE TABLE csop_sys_btn (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  BTN_NAME varchar(64) COLLATE utf8_bin DEFAULT NULL,
  BTN_DESC varchar(64) COLLATE utf8_bin DEFAULT NULL,
  BTN_ORDER int(11) DEFAULT NULL,
  PAGE_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  CREATED_BY varchar(16) COLLATE utf8_bin DEFAULT NULL,
  CREATION_DATE datetime DEFAULT NULL,
  MODIFIED_BY varchar(16) COLLATE utf8_bin DEFAULT NULL,
  MODIFY_DATE datetime DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_SYS_BTN__PAGE_ID (PAGE_ID),
  CONSTRAINT FK_SYS_BTN__PAGE_ID FOREIGN KEY (PAGE_ID) REFERENCES csop_sys_page_info (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='按钮信息';

-- ----------------------------
-- Table structure for csop_sys_btn_role
-- ----------------------------
DROP TABLE IF EXISTS csop_sys_btn_role;
CREATE TABLE csop_sys_btn_role (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  PAGE_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  ROLE_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  BTN_MARK int(11) DEFAULT NULL,
  CREATED_BY varchar(16) COLLATE utf8_bin DEFAULT NULL,
  CREATION_DATE datetime DEFAULT NULL,
  MODIFIED_BY varchar(16) COLLATE utf8_bin DEFAULT NULL,
  MODIFY_DATE datetime DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_SYS_BTN_ROLE__PAGE_ID (PAGE_ID),
  KEY FK_SYS_BTN_ROLE__ROLE_ID (ROLE_ID),
  CONSTRAINT FK_SYS_BTN_ROLE__PAGE_ID FOREIGN KEY (PAGE_ID) REFERENCES csop_sys_page_info (ID),
  CONSTRAINT FK_SYS_BTN_ROLE__ROLE_ID FOREIGN KEY (ROLE_ID) REFERENCES csop_sys_role (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='页面按钮角色关系表';

-- ----------------------------
-- Table structure for csop_sys_cooper_terms
-- ----------------------------
DROP TABLE IF EXISTS csop_sys_cooper_terms;
CREATE TABLE csop_sys_cooper_terms (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  CONTENT varchar(4096) COLLATE utf8_bin DEFAULT NULL,
  CREATED_BY varchar(16) COLLATE utf8_bin DEFAULT NULL,
  CREATION_DATE datetime DEFAULT NULL,
  MODIFIED_BY varchar(16) COLLATE utf8_bin DEFAULT NULL,
  MODIFIED_DATE datetime DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='合作条款信息';

-- ----------------------------
-- Table structure for csop_sys_image
-- ----------------------------
DROP TABLE IF EXISTS csop_sys_image;
CREATE TABLE csop_sys_image (
  PIC_TYPE varchar(32) COLLATE utf8_bin DEFAULT NULL,
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  PATH varchar(100) COLLATE utf8_bin DEFAULT NULL,
  WIDTH int(11) DEFAULT NULL,
  HEIGHT int(11) DEFAULT NULL,
  SIZE bigint(20) DEFAULT NULL,
  UPLOAD_TIME datetime DEFAULT NULL,
  ENABLED char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_SYS_IMAGE__PIC_TYPE (PIC_TYPE),
  CONSTRAINT FK_SYS_IMAGE__PIC_TYPE FOREIGN KEY (PIC_TYPE) REFERENCES csop_image_type (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_sys_log
-- ----------------------------
DROP TABLE IF EXISTS csop_sys_log;
CREATE TABLE csop_sys_log (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  LOG_TYPE varchar(4) COLLATE utf8_bin NOT NULL,
  LOG_LEVEL varchar(8) COLLATE utf8_bin DEFAULT NULL,
  CONTENT varchar(128) COLLATE utf8_bin NOT NULL,
  CREATED_BY varchar(16) COLLATE utf8_bin NOT NULL,
  CREATION_DATE datetime NOT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='系统日志信息';

-- ----------------------------
-- Table structure for csop_sys_mail_template
-- ----------------------------
DROP TABLE IF EXISTS csop_sys_mail_template;
CREATE TABLE csop_sys_mail_template (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  NAME varchar(64) COLLATE utf8_bin NOT NULL,
  SN varchar(32) COLLATE utf8_bin NOT NULL,
  DESCRIPTION varchar(128) COLLATE utf8_bin DEFAULT NULL,
  SUBJECT varchar(64) COLLATE utf8_bin DEFAULT NULL,
  CONTENT longtext COLLATE utf8_bin,
  ENABLE int(11) NOT NULL,
  CREATED_BY varchar(16) COLLATE utf8_bin DEFAULT NULL,
  CREATION_DATE datetime DEFAULT NULL,
  MODIFIED_BY varchar(16) COLLATE utf8_bin DEFAULT NULL,
  MODIFY_DATE datetime DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='邮件模板信息';

-- ----------------------------
-- Table structure for csop_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS csop_sys_menu;
CREATE TABLE csop_sys_menu (
  ID varchar(32) COLLATE utf8_bin NOT NULL COMMENT '菜单编号',
  MENU_NAME varchar(50) COLLATE utf8_bin NOT NULL COMMENT '菜单名称',
  MENU_PATH varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '菜单url',
  PARENT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '父目录id',
  IS_LEAF_MENU int(11) NOT NULL,
  MENU_ORDER int(11) NOT NULL COMMENT '菜单目录顺序号',
  ENABLE int(11) NOT NULL,
  SYTLE varchar(50) COLLATE utf8_bin DEFAULT NULL,
  LEVEL_NUMBER int(11) NOT NULL COMMENT '菜单目录级数',
  CREATED_BY varchar(16) COLLATE utf8_bin DEFAULT NULL,
  CREATION_DATE datetime DEFAULT NULL,
  MODIFIED_BY varchar(16) COLLATE utf8_bin DEFAULT NULL,
  MODIFY_DATE datetime DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_SYS_MENU__PARENT_ID (PARENT_ID),
  CONSTRAINT FK_SYS_MENU__PARENT_ID FOREIGN KEY (PARENT_ID) REFERENCES csop_sys_menu (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='系统菜单信息';

-- ----------------------------
-- Table structure for csop_sys_news
-- ----------------------------
DROP TABLE IF EXISTS csop_sys_news;
CREATE TABLE csop_sys_news (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  CATEGORY_ID varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '所属栏目，在数据字典中获取',
  TITLE varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '标题',
  ORIGIN varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '来源',
  PUBLISHER varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '发布人',
  PUBLISH_DATE datetime DEFAULT NULL COMMENT '发布时间',
  CONTENT longtext CHARACTER SET utf8 COMMENT '新闻内容',
  CREATE_BY varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建者',
  CREATE_DATE datetime DEFAULT NULL COMMENT '创建时间',
  MODIFY_BY varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '修改者',
  MODIFY_DATE datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (ID),
  KEY FK_SYS_NEWS__CATEGORY_ID (CATEGORY_ID),
  CONSTRAINT FK_SYS_NEWS__CATEGORY_ID FOREIGN KEY (CATEGORY_ID) REFERENCES csop_sys_param_info (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_sys_notice
-- ----------------------------
DROP TABLE IF EXISTS csop_sys_notice;
CREATE TABLE csop_sys_notice (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  TITLE varchar(100) COLLATE utf8_bin DEFAULT NULL,
  CONTENT varchar(255) COLLATE utf8_bin DEFAULT NULL,
  DATE datetime DEFAULT NULL,
  END_DATE datetime DEFAULT NULL,
  RECEIVE_LIST varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'ALL表示全部，多个接收人时以“,”隔开',
  CREATED_BY varchar(16) COLLATE utf8_bin DEFAULT NULL,
  CREATION_DATE datetime DEFAULT NULL,
  MODIFIED_BY varchar(16) COLLATE utf8_bin DEFAULT NULL,
  MODIFY_DATE datetime DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='公告信息';

-- ----------------------------
-- Table structure for csop_sys_page_info
-- ----------------------------
DROP TABLE IF EXISTS csop_sys_page_info;
CREATE TABLE csop_sys_page_info (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  PAGE_NAME varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PAGE_PATH varchar(256) COLLATE utf8_bin DEFAULT NULL,
  PAGE_DESC varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PAGE_TYPE int(11) DEFAULT NULL,
  MENU_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  CREATED_BY varchar(16) COLLATE utf8_bin DEFAULT NULL,
  CREATION_DATE datetime DEFAULT NULL,
  MODIFIED_BY varchar(16) COLLATE utf8_bin DEFAULT NULL,
  MODIFY_DATE datetime DEFAULT NULL,
  sys_menu_id varchar(255) COLLATE utf8_bin DEFAULT NULL,
  sys_menu_name varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_SYS_PAGE_INFO__MENU_ID (MENU_ID),
  CONSTRAINT FK_SYS_PAGE_INFO__MENU_ID FOREIGN KEY (MENU_ID) REFERENCES csop_sys_menu (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='页面信息';

-- ----------------------------
-- Table structure for csop_sys_param_info
-- ----------------------------
DROP TABLE IF EXISTS csop_sys_param_info;
CREATE TABLE csop_sys_param_info (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  PARANAME varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PARA_TYPE varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PARAVAL varchar(64) COLLATE utf8_bin DEFAULT NULL,
  DES varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  ENABLE int(11) NOT NULL,
  ISSYSDEFPARA char(1) COLLATE utf8_bin NOT NULL COMMENT '是否系统默认参数：0－否，1－是',
  CREATED_BY varchar(16) COLLATE utf8_bin DEFAULT NULL,
  CREATION_DATE datetime DEFAULT NULL,
  MODIFIED_BY varchar(16) COLLATE utf8_bin DEFAULT NULL,
  MODIFY_DATE datetime DEFAULT NULL,
  DELETED int(1) DEFAULT NULL,
  PARAM_CODE varchar(8) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_SYS_PARAM_INFO__PARAM_TYPE (PARA_TYPE),
  CONSTRAINT FK_SYS_PARAM_INFO__PARAM_TYPE FOREIGN KEY (PARA_TYPE) REFERENCES csop_sys_param_type (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='系统全局属性和参数';

-- ----------------------------
-- Table structure for csop_sys_param_type
-- ----------------------------
DROP TABLE IF EXISTS csop_sys_param_type;
CREATE TABLE csop_sys_param_type (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  TYPE_NAME varchar(32) COLLATE utf8_bin DEFAULT NULL,
  TYEP_DESC varchar(64) COLLATE utf8_bin DEFAULT NULL,
  CREATED_BY varchar(16) COLLATE utf8_bin DEFAULT NULL,
  CREATION_DATE datetime DEFAULT NULL,
  MODIFIED_BY varchar(16) COLLATE utf8_bin DEFAULT NULL,
  MODIFY_DATE datetime DEFAULT NULL,
  DELETED int(1) DEFAULT NULL,
  TYPE_CODE varchar(8) COLLATE utf8_bin DEFAULT NULL,
  ENABLED int(1) DEFAULT NULL,
  ISSYSDEFPARA char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='参数类型信息';

-- ----------------------------
-- Table structure for csop_sys_proc_log
-- ----------------------------
DROP TABLE IF EXISTS csop_sys_proc_log;
CREATE TABLE csop_sys_proc_log (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  PROC_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  WORKFLOW_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  TASK_NAME varchar(64) COLLATE utf8_bin DEFAULT NULL,
  START_TIME datetime DEFAULT NULL,
  END_TIME datetime DEFAULT NULL,
  RESULT_INFO varchar(256) COLLATE utf8_bin DEFAULT NULL,
  OPERATOR varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_SYS_PROC_LOG_WORKFLOW_ID (WORKFLOW_ID),
  KEY FK_SYS_PROC_LOG_PROC_ID (PROC_ID),
  CONSTRAINT FK_SYS_PROC_LOG_PROC_ID FOREIGN KEY (PROC_ID) REFERENCES csop_sys_bizz_proc (ID),
  CONSTRAINT FK_SYS_PROC_LOG_WORKFLOW_ID FOREIGN KEY (WORKFLOW_ID) REFERENCES csop_sys_bizz_proc_dtl (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='业务流程日志信息';

-- ----------------------------
-- Table structure for csop_sys_role
-- ----------------------------
DROP TABLE IF EXISTS csop_sys_role;
CREATE TABLE csop_sys_role (
  ID varchar(32) COLLATE utf8_bin NOT NULL COMMENT 'id',
  NAME varchar(20) COLLATE utf8_bin NOT NULL COMMENT '职责名称',
  DESCRIPTION varchar(400) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  ENABLE int(11) NOT NULL COMMENT '状态标识',
  DATEBGN date DEFAULT NULL COMMENT '时间格式YYYY-MM-DD',
  DATEEND date DEFAULT NULL COMMENT '时间格式YYYY-MM-DD',
  ISSYSDEFROLE char(1) COLLATE utf8_bin NOT NULL COMMENT '是否系统默认角色1-是，0-否',
  CREATED_BY varchar(16) COLLATE utf8_bin DEFAULT NULL,
  CREATION_DATE datetime DEFAULT NULL,
  MODIFIED_BY varchar(16) COLLATE utf8_bin DEFAULT NULL,
  MODIFY_DATE datetime DEFAULT NULL,
  CODE varchar(8) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='系统角色信息';

-- ----------------------------
-- Table structure for csop_sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS csop_sys_role_menu;
CREATE TABLE csop_sys_role_menu (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  MENU_ID varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '菜单编号',
  ROLE_ID varchar(32) COLLATE utf8_bin NOT NULL COMMENT 'id',
  CREATED_BY varchar(16) COLLATE utf8_bin DEFAULT NULL,
  CREATION_DATE datetime DEFAULT NULL,
  MODIFIED_BY varchar(16) COLLATE utf8_bin DEFAULT NULL,
  MODIFY_DATE datetime DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_SYS_ROLE_MENU__MENU_ID (MENU_ID),
  KEY FK_SYS_ROLE_MENU__ROLE_ID (ROLE_ID),
  CONSTRAINT FK_SYS_ROLE_MENU__MENU_ID FOREIGN KEY (MENU_ID) REFERENCES csop_sys_menu (ID),
  CONSTRAINT FK_SYS_ROLE_MENU__ROLE_ID FOREIGN KEY (ROLE_ID) REFERENCES csop_sys_role (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色菜单关系信息';

-- ----------------------------
-- Table structure for csop_sys_style_icons
-- ----------------------------
DROP TABLE IF EXISTS csop_sys_style_icons;
CREATE TABLE csop_sys_style_icons (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  ICON_NAME varchar(32) COLLATE utf8_bin DEFAULT NULL,
  ICON_STYLE varchar(32) COLLATE utf8_bin DEFAULT NULL,
  ADDRESS varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '图片服务器路径',
  ENABLE int(11) NOT NULL,
  CREATED_BY varchar(16) COLLATE utf8_bin DEFAULT NULL,
  CREATION_DATE datetime DEFAULT NULL,
  MODIFIED_BY varchar(16) COLLATE utf8_bin DEFAULT NULL,
  MODIFY_DATE datetime DEFAULT NULL,
  DELETED int(1) DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_sys_task_info
-- ----------------------------
DROP TABLE IF EXISTS csop_sys_task_info;
CREATE TABLE csop_sys_task_info (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  TASK_TYPE varchar(32) COLLATE utf8_bin DEFAULT NULL,
  TASK_NAME varchar(64) COLLATE utf8_bin NOT NULL,
  PRIORITY varchar(4) COLLATE utf8_bin DEFAULT NULL,
  TASK_STAUTS int(11) NOT NULL,
  COMMENT varchar(128) COLLATE utf8_bin DEFAULT NULL,
  START_TIME datetime DEFAULT NULL,
  RESULT_INFO varchar(256) COLLATE utf8_bin DEFAULT NULL,
  END_TIME datetime DEFAULT NULL,
  CREATED_BY varchar(16) COLLATE utf8_bin DEFAULT NULL,
  CREATION_DATE datetime DEFAULT NULL,
  MODIFIED_BY varchar(16) COLLATE utf8_bin DEFAULT NULL,
  MODIFY_DATE datetime DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_SYS_TASK_INFO__TASK_TYPE (TASK_TYPE),
  CONSTRAINT FK_SYS_TASK_INFO__TASK_TYPE FOREIGN KEY (TASK_TYPE) REFERENCES csop_sys_task_type (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_sys_task_log
-- ----------------------------
DROP TABLE IF EXISTS csop_sys_task_log;
CREATE TABLE csop_sys_task_log (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  TASK_ID varchar(32) COLLATE utf8_bin NOT NULL,
  START_TIME datetime DEFAULT NULL,
  END_TIME datetime DEFAULT NULL,
  RESULT varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_SYS_TASK_LOG__TASK_ID (TASK_ID),
  CONSTRAINT FK_SYS_TASK_LOG__TASK_ID FOREIGN KEY (TASK_ID) REFERENCES csop_sys_task_info (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='任务执行日志';

-- ----------------------------
-- Table structure for csop_sys_task_type
-- ----------------------------
DROP TABLE IF EXISTS csop_sys_task_type;
CREATE TABLE csop_sys_task_type (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  TYPE_NAME varchar(32) COLLATE utf8_bin NOT NULL,
  TYPE_DESC varchar(64) COLLATE utf8_bin DEFAULT NULL,
  CREATED_BY varchar(16) COLLATE utf8_bin DEFAULT NULL,
  CREATION_DATE datetime DEFAULT NULL,
  MODIFIED_BY varchar(16) COLLATE utf8_bin DEFAULT NULL,
  MODIFY_DATE datetime DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='任务类型信息';

-- ----------------------------
-- Table structure for csop_sys_user
-- ----------------------------
DROP TABLE IF EXISTS csop_sys_user;
CREATE TABLE csop_sys_user (
  ID varchar(32) COLLATE utf8_bin NOT NULL COMMENT '用户代号',
  USER_NAME varchar(100) COLLATE utf8_bin NOT NULL COMMENT '用户名称',
  PASSWORD varchar(64) COLLATE utf8_bin NOT NULL COMMENT '密码',
  FLAG char(1) COLLATE utf8_bin NOT NULL COMMENT '状态标识',
  EMAIL varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT 'email地址',
  CELLPHONE varchar(11) COLLATE utf8_bin DEFAULT NULL COMMENT '手机号码',
  OFFICEPHONE varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '办公室电话',
  FAMILLYPHONE varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '家庭电话',
  FAX varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '传真',
  USERADDR varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '用户地址',
  REALNAME varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '真实名',
  ISFRSTLOGIN char(1) COLLATE utf8_bin NOT NULL COMMENT '是否第一次登录',
  REALNUM varchar(32) COLLATE utf8_bin NOT NULL COMMENT '实际密码错误的登录次数',
  STARTTIME datetime DEFAULT NULL COMMENT '有效的开始时间',
  ENDTIME datetime DEFAULT NULL COMMENT '有效的结束时间',
  ENABLE int(11) NOT NULL COMMENT '是否删除：0 是；1 否',
  DESCRIPTION varchar(400) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  ISADMINISTRATOR char(1) COLLATE utf8_bin NOT NULL COMMENT '是否为管理员;1,系统管理员;2,实施管理员;4,集成管理员;5,DEMO管理员',
  ISINNUSER char(1) COLLATE utf8_bin NOT NULL COMMENT '是否内建用户',
  PWDMODTIME datetime DEFAULT NULL COMMENT '密码修改时间',
  MSN varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'MSN',
  CREATED_BY varchar(16) COLLATE utf8_bin DEFAULT NULL,
  CREATION_DATE datetime DEFAULT NULL,
  MODIFIED_BY varchar(16) COLLATE utf8_bin DEFAULT NULL,
  MODIFY_DATE datetime DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户表';

-- ----------------------------
-- Table structure for csop_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS csop_sys_user_role;
CREATE TABLE csop_sys_user_role (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  ROLE_ID varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'id',
  USER_ID varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '用户代号',
  CREATED_BY varchar(16) COLLATE utf8_bin DEFAULT NULL,
  CREATION_DATE datetime DEFAULT NULL,
  MODIFIED_BY varchar(16) COLLATE utf8_bin DEFAULT NULL,
  MODIFY_DATE datetime DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_SYS_USER_ROLE__ROLE_ID (ROLE_ID),
  KEY FK_SYS_USER_ROLE__USER_ID (USER_ID),
  CONSTRAINT FK_SYS_USER_ROLE__ROLE_ID FOREIGN KEY (ROLE_ID) REFERENCES csop_sys_role (ID),
  CONSTRAINT FK_SYS_USER_ROLE__USER_ID FOREIGN KEY (USER_ID) REFERENCES csop_sys_user (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户角色关系信息';

-- ----------------------------
-- Table structure for csop_sys_user_sets
-- ----------------------------
DROP TABLE IF EXISTS csop_sys_user_sets;
CREATE TABLE csop_sys_user_sets (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  USER_ID varchar(32) COLLATE utf8_bin NOT NULL,
  ITEM varchar(32) COLLATE utf8_bin NOT NULL,
  ITEM_VALUE varchar(1024) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_SYS_USER_SETS_R_SYS_USER (USER_ID),
  CONSTRAINT FK_SYS_USER_SETS_R_SYS_USER FOREIGN KEY (USER_ID) REFERENCES csop_sys_user (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_sys_work_flow_conf
-- ----------------------------
DROP TABLE IF EXISTS csop_sys_work_flow_conf;
CREATE TABLE csop_sys_work_flow_conf (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  PROC_ID varchar(32) COLLATE utf8_bin NOT NULL,
  WORKFLOW_ID varchar(32) COLLATE utf8_bin NOT NULL,
  ELEMENT_DESC varchar(64) COLLATE utf8_bin NOT NULL,
  ELEMENT_NAME varchar(32) COLLATE utf8_bin NOT NULL,
  ROLE varchar(32) COLLATE utf8_bin DEFAULT NULL,
  JUMPABLE int(2) NOT NULL,
  PRIMARY KEY (ID),
  KEY FK_SYS_WORK_FLOW_CONF__PROC_ID (PROC_ID),
  KEY FK_SYS_WORK_FLOW_CONF__WORKFLOW_ID (WORKFLOW_ID),
  CONSTRAINT FK_SYS_WORK_FLOW_CONF__PROC_ID FOREIGN KEY (PROC_ID) REFERENCES csop_sys_bizz_proc (ID),
  CONSTRAINT FK_SYS_WORK_FLOW_CONF__WORKFLOW_ID FOREIGN KEY (WORKFLOW_ID) REFERENCES csop_sys_bizz_proc_dtl (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_tariff
-- ----------------------------
DROP TABLE IF EXISTS csop_tariff;
CREATE TABLE csop_tariff (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  PRICING_INFO_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  TIME_UNIT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  TIME_RATE_UNIT float DEFAULT NULL,
  MEASURE_UNIT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  MEASURE_RATE_UNIT float DEFAULT NULL,
  RATE_START float DEFAULT NULL,
  RATE_END float DEFAULT NULL,
  SE_UNIT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  FIXED_PRICE decimal(10,2) DEFAULT NULL,
  CALC_METHOD char(2) COLLATE utf8_bin DEFAULT NULL COMMENT '0A-直接计算；0B-计量计时组合计算；',
  PRIMARY KEY (ID),
  KEY FK_TARIFF__PRICING_INFO_ID (PRICING_INFO_ID),
  KEY FK_TARIFF__MEASURE_UNIT_ID (MEASURE_UNIT_ID),
  KEY FK_TARIFF__TIME_UNIT_ID (TIME_UNIT_ID),
  KEY FK_TARIFF__SE_UNIT_ID (SE_UNIT_ID),
  CONSTRAINT FK_TARIFF__MEASURE_UNIT_ID FOREIGN KEY (MEASURE_UNIT_ID) REFERENCES csop_unit (ID),
  CONSTRAINT FK_TARIFF__PRICING_INFO_ID FOREIGN KEY (PRICING_INFO_ID) REFERENCES csop_pricing_info (ID),
  CONSTRAINT FK_TARIFF__SE_UNIT_ID FOREIGN KEY (SE_UNIT_ID) REFERENCES csop_unit (ID),
  CONSTRAINT FK_TARIFF__TIME_UNIT_ID FOREIGN KEY (TIME_UNIT_ID) REFERENCES csop_unit (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_tariff_snapshot
-- ----------------------------
DROP TABLE IF EXISTS csop_tariff_snapshot;
CREATE TABLE csop_tariff_snapshot (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  PRICING_INFO_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  TIME_UNIT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  TIME_RATE_UNIT float DEFAULT NULL,
  MEASURE_UNIT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  MEASURE_RATE_UNIT float DEFAULT NULL,
  RATE_START float DEFAULT NULL,
  RATE_END float DEFAULT NULL,
  SE_UNIT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  FIXED_PRICE decimal(10,2) DEFAULT NULL,
  CALC_METHOD char(2) COLLATE utf8_bin DEFAULT NULL COMMENT '0A-直接计算；0B-计量计时组合计算；',
  PRIMARY KEY (ID),
  KEY FK_TARIFF_SNAPSHOT__PRICING_INFO_ID (PRICING_INFO_ID),
  KEY FK_TARIFF_SNAPSHOT__MEASURE_UNIT_ID (MEASURE_UNIT_ID),
  KEY FK_TARIFF_SNAPSHOT__TIME_UNIT_ID (TIME_UNIT_ID),
  KEY FK_TARIFF_SNAPSHOT__SE_UNIT_ID (SE_UNIT_ID),
  CONSTRAINT FK_TARIFF_SNAPSHOT__MEASURE_UNIT_ID FOREIGN KEY (MEASURE_UNIT_ID) REFERENCES csop_unit (ID),
  CONSTRAINT FK_TARIFF_SNAPSHOT__PRICING_INFO_ID FOREIGN KEY (PRICING_INFO_ID) REFERENCES csop_pricing_info_snapshot (ID),
  CONSTRAINT FK_TARIFF_SNAPSHOT__SE_UNIT_ID FOREIGN KEY (SE_UNIT_ID) REFERENCES csop_unit (ID),
  CONSTRAINT FK_TARIFF_SNAPSHOT__TIME_UNIT_ID FOREIGN KEY (TIME_UNIT_ID) REFERENCES csop_unit (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_ticket_info
-- ----------------------------
DROP TABLE IF EXISTS csop_ticket_info;
CREATE TABLE csop_ticket_info (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  SN varchar(50) COLLATE utf8_bin DEFAULT NULL,
  APP_INST_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  APP_QUOTA_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  BEGIN_TIME datetime DEFAULT NULL,
  END_TIME datetime DEFAULT NULL,
  AMOUNT float DEFAULT NULL,
  CREATED_TIME datetime DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_TICKET_INFO__APP_INST_ID (APP_INST_ID),
  KEY FK_TICKET_INFO__APP_QUOTA_ID (APP_QUOTA_ID),
  CONSTRAINT FK_TICKET_INFO__APP_INST_ID FOREIGN KEY (APP_INST_ID) REFERENCES csop_product_inst (ID),
  CONSTRAINT FK_TICKET_INFO__APP_QUOTA_ID FOREIGN KEY (APP_QUOTA_ID) REFERENCES csop_app_quota (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_time_unit
-- ----------------------------
DROP TABLE IF EXISTS csop_time_unit;
CREATE TABLE csop_time_unit (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  PRENT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  NAME varchar(20) COLLATE utf8_bin DEFAULT NULL,
  TIMES decimal(20,10) DEFAULT NULL,
  CODE varchar(10) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_TIME_UNIT__PARENT_ID (PRENT_ID),
  CONSTRAINT FK_TIME_UNIT__PARENT_ID FOREIGN KEY (PRENT_ID) REFERENCES csop_time_unit (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_unit
-- ----------------------------
DROP TABLE IF EXISTS csop_unit;
CREATE TABLE csop_unit (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  NAME varchar(20) COLLATE utf8_bin DEFAULT NULL,
  DESCRIPTION varchar(200) COLLATE utf8_bin DEFAULT NULL,
  SYMBLE varchar(20) COLLATE utf8_bin DEFAULT NULL,
  CODE varchar(8) COLLATE utf8_bin DEFAULT NULL,
  ENABLED char(1) COLLATE utf8_bin DEFAULT NULL,
  DELETED char(1) COLLATE utf8_bin DEFAULT NULL,
  CATEGORY_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  CONVERSION_RATE bigint(20) DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_UNIT__CATEGORY_ID (CATEGORY_ID),
  CONSTRAINT FK_UNIT__CATEGORY_ID FOREIGN KEY (CATEGORY_ID) REFERENCES csop_unit_category (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_unit_category
-- ----------------------------
DROP TABLE IF EXISTS csop_unit_category;
CREATE TABLE csop_unit_category (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  NAME varchar(20) COLLATE utf8_bin DEFAULT NULL,
  CODE varchar(20) COLLATE utf8_bin DEFAULT NULL,
  DESCRIPTION varchar(200) COLLATE utf8_bin DEFAULT NULL,
  ENABLED char(1) COLLATE utf8_bin DEFAULT NULL,
  DELETED char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_user
-- ----------------------------
DROP TABLE IF EXISTS csop_user;
CREATE TABLE csop_user (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  CUSTOMER_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  TYPE int(11) DEFAULT NULL,
  USERNAME varchar(200) COLLATE utf8_bin DEFAULT NULL,
  PASSWORD varchar(200) COLLATE utf8_bin DEFAULT NULL,
  STATUS int(11) DEFAULT NULL,
  LEVEL int(11) DEFAULT NULL,
  CREATEBY varchar(32) COLLATE utf8_bin DEFAULT NULL,
  CREATETIME datetime DEFAULT NULL,
  UPDATEBY varchar(32) COLLATE utf8_bin DEFAULT NULL,
  UPDATETIME datetime DEFAULT NULL,
  PARENTID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_USER__CUSTOMER_ID (CUSTOMER_ID),
  CONSTRAINT FK_USER__CUSTOMER_ID FOREIGN KEY (CUSTOMER_ID) REFERENCES csop_customer (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for csop_user_group_relation
-- ----------------------------
DROP TABLE IF EXISTS csop_user_group_relation;
CREATE TABLE csop_user_group_relation (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  USERID varchar(32) COLLATE utf8_bin NOT NULL,
  GROUPID varchar(32) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (ID),
  KEY FK_USER_GROUP_RELATION__GROUPID (GROUPID),
  KEY FK_USER_GROUP_RELATION__USERID (USERID),
  CONSTRAINT FK_USER_GROUP_RELATION__GROUPID FOREIGN KEY (GROUPID) REFERENCES csop_group (ID),
  CONSTRAINT FK_USER_GROUP_RELATION__USERID FOREIGN KEY (USERID) REFERENCES csop_user (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for csop_user_org_relation
-- ----------------------------
DROP TABLE IF EXISTS csop_user_org_relation;
CREATE TABLE csop_user_org_relation (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  USERID varchar(32) COLLATE utf8_bin NOT NULL COMMENT '企业成员用户ID',
  ORGID varchar(32) COLLATE utf8_bin NOT NULL COMMENT '组织ID',
  PRIMARY KEY (ID),
  KEY FK_USER_ORG_RELATION__USERID (USERID),
  KEY FK_USER_ORG_RELATION__ORGID (ORGID),
  CONSTRAINT FK_USER_ORG_RELATION__ORGID FOREIGN KEY (ORGID) REFERENCES csop_org (ID),
  CONSTRAINT FK_USER_ORG_RELATION__USERID FOREIGN KEY (USERID) REFERENCES csop_user (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for csop_vm_template
-- ----------------------------
DROP TABLE IF EXISTS csop_vm_template;
CREATE TABLE csop_vm_template (
  ID varchar(32) COLLATE utf8_bin NOT NULL,
  NAME varchar(100) COLLATE utf8_bin DEFAULT NULL,
  DESCRIPTION varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  POOL_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  VCPU int(11) DEFAULT NULL,
  MEMORY int(11) DEFAULT NULL,
  STORAGE bigint(20) DEFAULT NULL,
  OS varchar(100) COLLATE utf8_bin DEFAULT NULL,
  UUID varchar(50) COLLATE utf8_bin DEFAULT NULL,
  ENABLED char(1) COLLATE utf8_bin DEFAULT NULL,
  DELETED char(1) COLLATE utf8_bin DEFAULT NULL,
  CREATED_TIME datetime DEFAULT NULL,
  STATUS_TIME datetime DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY FK_VM_TEMPLATE__POOL_ID (POOL_ID),
  CONSTRAINT FK_VM_TEMPLATE__POOL_ID FOREIGN KEY (POOL_ID) REFERENCES csop_res_vm_pool (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for jbpm4_deployment
-- ----------------------------
DROP TABLE IF EXISTS jbpm4_deployment;
CREATE TABLE jbpm4_deployment (
  dbid_ bigint(20) NOT NULL,
  name_ longtext COLLATE utf8_bin,
  timestamp_ bigint(20) DEFAULT NULL,
  state_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (dbid_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for jbpm4_deployprop
-- ----------------------------
DROP TABLE IF EXISTS jbpm4_deployprop;
CREATE TABLE jbpm4_deployprop (
  dbid_ bigint(20) NOT NULL,
  deployment_ bigint(20) DEFAULT NULL,
  objname_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  key_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  stringval_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  longval_ bigint(20) DEFAULT NULL,
  PRIMARY KEY (dbid_),
  KEY FK_DEPLPROP_DEPL (deployment_),
  KEY IDX_DEPLPROP_DEPL (deployment_),
  CONSTRAINT FK_DEPLPROP_DEPL FOREIGN KEY (deployment_) REFERENCES jbpm4_deployment (dbid_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for jbpm4_execution
-- ----------------------------
DROP TABLE IF EXISTS jbpm4_execution;
CREATE TABLE jbpm4_execution (
  dbid_ bigint(20) NOT NULL,
  class_ varchar(255) COLLATE utf8_bin NOT NULL,
  dbversion_ int(11) NOT NULL,
  activityname_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  procdefid_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  hasvars_ bit(1) DEFAULT NULL,
  name_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  key_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  id_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  state_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  susphiststate_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  priority_ int(11) DEFAULT NULL,
  hisactinst_ bigint(20) DEFAULT NULL,
  parent_ bigint(20) DEFAULT NULL,
  instance_ bigint(20) DEFAULT NULL,
  superexec_ bigint(20) DEFAULT NULL,
  subprocinst_ bigint(20) DEFAULT NULL,
  parent_idx_ int(11) DEFAULT NULL,
  PRIMARY KEY (dbid_),
  UNIQUE KEY id_ (id_),
  KEY FK_EXEC_SUBPI (subprocinst_),
  KEY FK_EXEC_INSTANCE (instance_),
  KEY FK_EXEC_SUPEREXEC (superexec_),
  KEY FK_EXEC_PARENT (parent_),
  KEY IDX_EXEC_SUBPI (subprocinst_),
  KEY IDX_EXEC_PARENT (parent_),
  KEY IDX_EXEC_SUPEREXEC (superexec_),
  KEY IDX_EXEC_INSTANCE (instance_),
  CONSTRAINT FK_EXEC_INSTANCE FOREIGN KEY (instance_) REFERENCES jbpm4_execution (dbid_),
  CONSTRAINT FK_EXEC_PARENT FOREIGN KEY (parent_) REFERENCES jbpm4_execution (dbid_),
  CONSTRAINT FK_EXEC_SUBPI FOREIGN KEY (subprocinst_) REFERENCES jbpm4_execution (dbid_),
  CONSTRAINT FK_EXEC_SUPEREXEC FOREIGN KEY (superexec_) REFERENCES jbpm4_execution (dbid_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for jbpm4_hist_actinst
-- ----------------------------
DROP TABLE IF EXISTS jbpm4_hist_actinst;
CREATE TABLE jbpm4_hist_actinst (
  dbid_ bigint(20) NOT NULL,
  class_ varchar(255) COLLATE utf8_bin NOT NULL,
  dbversion_ int(11) NOT NULL,
  hproci_ bigint(20) DEFAULT NULL,
  type_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  execution_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  activity_name_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  start_ datetime DEFAULT NULL,
  end_ datetime DEFAULT NULL,
  duration_ bigint(20) DEFAULT NULL,
  transition_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  nextidx_ int(11) DEFAULT NULL,
  htask_ bigint(20) DEFAULT NULL,
  PRIMARY KEY (dbid_),
  KEY FK_HACTI_HPROCI (hproci_),
  KEY FK_HTI_HTASK (htask_),
  KEY IDX_HTI_HTASK (htask_),
  KEY IDX_HACTI_HPROCI (hproci_),
  CONSTRAINT FK_HACTI_HPROCI FOREIGN KEY (hproci_) REFERENCES jbpm4_hist_procinst (dbid_),
  CONSTRAINT FK_HTI_HTASK FOREIGN KEY (htask_) REFERENCES jbpm4_hist_task (dbid_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for jbpm4_hist_detail
-- ----------------------------
DROP TABLE IF EXISTS jbpm4_hist_detail;
CREATE TABLE jbpm4_hist_detail (
  dbid_ bigint(20) NOT NULL,
  class_ varchar(255) COLLATE utf8_bin NOT NULL,
  dbversion_ int(11) NOT NULL,
  userid_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  time_ datetime DEFAULT NULL,
  hproci_ bigint(20) DEFAULT NULL,
  hprociidx_ int(11) DEFAULT NULL,
  hacti_ bigint(20) DEFAULT NULL,
  hactiidx_ int(11) DEFAULT NULL,
  htask_ bigint(20) DEFAULT NULL,
  htaskidx_ int(11) DEFAULT NULL,
  hvar_ bigint(20) DEFAULT NULL,
  hvaridx_ int(11) DEFAULT NULL,
  message_ longtext COLLATE utf8_bin,
  old_str_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  new_str_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  old_int_ int(11) DEFAULT NULL,
  new_int_ int(11) DEFAULT NULL,
  old_time_ datetime DEFAULT NULL,
  new_time_ datetime DEFAULT NULL,
  parent_ bigint(20) DEFAULT NULL,
  parent_idx_ int(11) DEFAULT NULL,
  PRIMARY KEY (dbid_),
  KEY FK_HDETAIL_HVAR (hvar_),
  KEY FK_HDETAIL_HPROCI (hproci_),
  KEY FK_HDETAIL_HTASK (htask_),
  KEY FK_HDETAIL_HACTI (hacti_),
  KEY IDX_HDET_HVAR (hvar_),
  KEY IDX_HDET_HACTI (hacti_),
  KEY IDX_HDET_HTASK (htask_),
  KEY IDX_HDET_HPROCI (hproci_),
  CONSTRAINT FK_HDETAIL_HACTI FOREIGN KEY (hacti_) REFERENCES jbpm4_hist_actinst (dbid_),
  CONSTRAINT FK_HDETAIL_HPROCI FOREIGN KEY (hproci_) REFERENCES jbpm4_hist_procinst (dbid_),
  CONSTRAINT FK_HDETAIL_HTASK FOREIGN KEY (htask_) REFERENCES jbpm4_hist_task (dbid_),
  CONSTRAINT FK_HDETAIL_HVAR FOREIGN KEY (hvar_) REFERENCES jbpm4_hist_var (dbid_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for jbpm4_hist_procinst
-- ----------------------------
DROP TABLE IF EXISTS jbpm4_hist_procinst;
CREATE TABLE jbpm4_hist_procinst (
  dbid_ bigint(20) NOT NULL,
  dbversion_ int(11) NOT NULL,
  id_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  procdefid_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  key_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  start_ datetime DEFAULT NULL,
  end_ datetime DEFAULT NULL,
  duration_ bigint(20) DEFAULT NULL,
  state_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  endactivity_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  nextidx_ int(11) DEFAULT NULL,
  PRIMARY KEY (dbid_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for jbpm4_hist_task
-- ----------------------------
DROP TABLE IF EXISTS jbpm4_hist_task;
CREATE TABLE jbpm4_hist_task (
  dbid_ bigint(20) NOT NULL,
  dbversion_ int(11) NOT NULL,
  execution_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  outcome_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  assignee_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  priority_ int(11) DEFAULT NULL,
  state_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  create_ datetime DEFAULT NULL,
  end_ datetime DEFAULT NULL,
  duration_ bigint(20) DEFAULT NULL,
  nextidx_ int(11) DEFAULT NULL,
  supertask_ bigint(20) DEFAULT NULL,
  PRIMARY KEY (dbid_),
  KEY FK_HSUPERT_SUB (supertask_),
  KEY IDX_HSUPERT_SUB (supertask_),
  CONSTRAINT FK_HSUPERT_SUB FOREIGN KEY (supertask_) REFERENCES jbpm4_hist_task (dbid_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for jbpm4_hist_var
-- ----------------------------
DROP TABLE IF EXISTS jbpm4_hist_var;
CREATE TABLE jbpm4_hist_var (
  dbid_ bigint(20) NOT NULL,
  dbversion_ int(11) NOT NULL,
  procinstid_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  executionid_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  varname_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  value_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  hproci_ bigint(20) DEFAULT NULL,
  htask_ bigint(20) DEFAULT NULL,
  PRIMARY KEY (dbid_),
  KEY FK_HVAR_HPROCI (hproci_),
  KEY FK_HVAR_HTASK (htask_),
  KEY IDX_HVAR_HTASK (htask_),
  KEY IDX_HVAR_HPROCI (hproci_),
  CONSTRAINT FK_HVAR_HPROCI FOREIGN KEY (hproci_) REFERENCES jbpm4_hist_procinst (dbid_),
  CONSTRAINT FK_HVAR_HTASK FOREIGN KEY (htask_) REFERENCES jbpm4_hist_task (dbid_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for jbpm4_id_group
-- ----------------------------
DROP TABLE IF EXISTS jbpm4_id_group;
CREATE TABLE jbpm4_id_group (
  dbid_ bigint(20) NOT NULL,
  dbversion_ int(11) NOT NULL,
  id_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  name_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  type_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  parent_ bigint(20) DEFAULT NULL,
  PRIMARY KEY (dbid_),
  KEY FK_GROUP_PARENT (parent_),
  KEY IDX_GROUP_PARENT (parent_),
  CONSTRAINT FK_GROUP_PARENT FOREIGN KEY (parent_) REFERENCES jbpm4_id_group (dbid_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for jbpm4_id_membership
-- ----------------------------
DROP TABLE IF EXISTS jbpm4_id_membership;
CREATE TABLE jbpm4_id_membership (
  dbid_ bigint(20) NOT NULL,
  dbversion_ int(11) NOT NULL,
  user_ bigint(20) DEFAULT NULL,
  group_ bigint(20) DEFAULT NULL,
  name_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (dbid_),
  KEY FK_MEM_GROUP (group_),
  KEY FK_MEM_USER (user_),
  KEY IDX_MEM_GROUP (group_),
  KEY IDX_MEM_USER (user_),
  CONSTRAINT FK_MEM_GROUP FOREIGN KEY (group_) REFERENCES jbpm4_id_group (dbid_),
  CONSTRAINT FK_MEM_USER FOREIGN KEY (user_) REFERENCES jbpm4_id_user (dbid_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for jbpm4_id_user
-- ----------------------------
DROP TABLE IF EXISTS jbpm4_id_user;
CREATE TABLE jbpm4_id_user (
  dbid_ bigint(20) NOT NULL,
  dbversion_ int(11) NOT NULL,
  id_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  password_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  givenname_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  familyname_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  businessemail_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (dbid_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for jbpm4_job
-- ----------------------------
DROP TABLE IF EXISTS jbpm4_job;
CREATE TABLE jbpm4_job (
  dbid_ bigint(20) NOT NULL,
  class_ varchar(255) COLLATE utf8_bin NOT NULL,
  dbversion_ int(11) NOT NULL,
  duedate_ datetime DEFAULT NULL,
  state_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  isexclusive_ bit(1) DEFAULT NULL,
  lockowner_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  lockexptime_ datetime DEFAULT NULL,
  exception_ longtext COLLATE utf8_bin,
  retries_ int(11) DEFAULT NULL,
  processinstance_ bigint(20) DEFAULT NULL,
  execution_ bigint(20) DEFAULT NULL,
  cfg_ bigint(20) DEFAULT NULL,
  signal_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  event_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  repeat_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (dbid_),
  KEY FK_JOB_CFG (cfg_),
  KEY IDX_JOBRETRIES (retries_),
  KEY IDX_JOBDUEDATE (duedate_),
  KEY IDX_JOBLOCKEXP (lockexptime_),
  KEY IDX_JOB_CFG (cfg_),
  KEY IDX_JOB_EXE (execution_),
  KEY IDX_JOB_PRINST (processinstance_),
  CONSTRAINT FK_JOB_CFG FOREIGN KEY (cfg_) REFERENCES jbpm4_lob (dbid_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for jbpm4_lob
-- ----------------------------
DROP TABLE IF EXISTS jbpm4_lob;
CREATE TABLE jbpm4_lob (
  dbid_ bigint(20) NOT NULL,
  dbversion_ int(11) NOT NULL,
  blob_value_ longblob,
  deployment_ bigint(20) DEFAULT NULL,
  name_ longtext COLLATE utf8_bin,
  PRIMARY KEY (dbid_),
  KEY FK_LOB_DEPLOYMENT (deployment_),
  KEY IDX_LOB_DEPLOYMENT (deployment_),
  CONSTRAINT FK_LOB_DEPLOYMENT FOREIGN KEY (deployment_) REFERENCES jbpm4_deployment (dbid_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for jbpm4_participation
-- ----------------------------
DROP TABLE IF EXISTS jbpm4_participation;
CREATE TABLE jbpm4_participation (
  dbid_ bigint(20) NOT NULL,
  dbversion_ int(11) NOT NULL,
  groupid_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  userid_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  type_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  task_ bigint(20) DEFAULT NULL,
  swimlane_ bigint(20) DEFAULT NULL,
  PRIMARY KEY (dbid_),
  KEY FK_PART_SWIMLANE (swimlane_),
  KEY FK_PART_TASK (task_),
  KEY IDX_PART_TASK (task_),
  CONSTRAINT FK_PART_SWIMLANE FOREIGN KEY (swimlane_) REFERENCES jbpm4_swimlane (dbid_),
  CONSTRAINT FK_PART_TASK FOREIGN KEY (task_) REFERENCES jbpm4_task (dbid_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for jbpm4_property
-- ----------------------------
DROP TABLE IF EXISTS jbpm4_property;
CREATE TABLE jbpm4_property (
  key_ varchar(255) COLLATE utf8_bin NOT NULL,
  version_ int(11) NOT NULL,
  value_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (key_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for jbpm4_swimlane
-- ----------------------------
DROP TABLE IF EXISTS jbpm4_swimlane;
CREATE TABLE jbpm4_swimlane (
  dbid_ bigint(20) NOT NULL,
  dbversion_ int(11) NOT NULL,
  name_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  assignee_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  execution_ bigint(20) DEFAULT NULL,
  PRIMARY KEY (dbid_),
  KEY FK_SWIMLANE_EXEC (execution_),
  KEY IDX_SWIMLANE_EXEC (execution_),
  CONSTRAINT FK_SWIMLANE_EXEC FOREIGN KEY (execution_) REFERENCES jbpm4_execution (dbid_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for jbpm4_task
-- ----------------------------
DROP TABLE IF EXISTS jbpm4_task;
CREATE TABLE jbpm4_task (
  dbid_ bigint(20) NOT NULL,
  class_ char(1) COLLATE utf8_bin NOT NULL,
  dbversion_ int(11) NOT NULL,
  name_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  descr_ longtext COLLATE utf8_bin,
  state_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  susphiststate_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  assignee_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  form_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  priority_ int(11) DEFAULT NULL,
  create_ datetime DEFAULT NULL,
  duedate_ datetime DEFAULT NULL,
  progress_ int(11) DEFAULT NULL,
  signalling_ bit(1) DEFAULT NULL,
  execution_id_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  activity_name_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  hasvars_ bit(1) DEFAULT NULL,
  supertask_ bigint(20) DEFAULT NULL,
  execution_ bigint(20) DEFAULT NULL,
  procinst_ bigint(20) DEFAULT NULL,
  swimlane_ bigint(20) DEFAULT NULL,
  taskdefname_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (dbid_),
  KEY FK_TASK_SWIML (swimlane_),
  KEY FK_TASK_SUPERTASK (supertask_),
  KEY IDX_TASK_SUPERTASK (supertask_),
  CONSTRAINT FK_TASK_SUPERTASK FOREIGN KEY (supertask_) REFERENCES jbpm4_task (dbid_),
  CONSTRAINT FK_TASK_SWIML FOREIGN KEY (swimlane_) REFERENCES jbpm4_swimlane (dbid_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for jbpm4_variable
-- ----------------------------
DROP TABLE IF EXISTS jbpm4_variable;
CREATE TABLE jbpm4_variable (
  dbid_ bigint(20) NOT NULL,
  class_ varchar(255) COLLATE utf8_bin NOT NULL,
  dbversion_ int(11) NOT NULL,
  key_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  converter_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  hist_ bit(1) DEFAULT NULL,
  execution_ bigint(20) DEFAULT NULL,
  task_ bigint(20) DEFAULT NULL,
  lob_ bigint(20) DEFAULT NULL,
  date_value_ datetime DEFAULT NULL,
  double_value_ double DEFAULT NULL,
  classname_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  long_value_ bigint(20) DEFAULT NULL,
  string_value_ varchar(255) COLLATE utf8_bin DEFAULT NULL,
  text_value_ longtext COLLATE utf8_bin,
  exesys_ bigint(20) DEFAULT NULL,
  PRIMARY KEY (dbid_),
  KEY FK_VAR_EXESYS (exesys_),
  KEY FK_VAR_LOB (lob_),
  KEY FK_VAR_TASK (task_),
  KEY FK_VAR_EXECUTION (execution_),
  KEY IDX_VAR_EXESYS (exesys_),
  KEY IDX_VAR_TASK (task_),
  KEY IDX_VAR_EXECUTION (execution_),
  KEY IDX_VAR_LOB (lob_),
  CONSTRAINT FK_VAR_EXECUTION FOREIGN KEY (execution_) REFERENCES jbpm4_execution (dbid_),
  CONSTRAINT FK_VAR_EXESYS FOREIGN KEY (exesys_) REFERENCES jbpm4_execution (dbid_),
  CONSTRAINT FK_VAR_LOB FOREIGN KEY (lob_) REFERENCES jbpm4_lob (dbid_),
  CONSTRAINT FK_VAR_TASK FOREIGN KEY (task_) REFERENCES jbpm4_task (dbid_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*修改csop_sys_style_icons 中name 和style长度*/
alter table csop_sys_style_icons modify column ICON_NAME varchar(256) ;
alter table csop_sys_style_icons modify column ICON_STYLE varchar(256) ;

DROP TABLE IF EXISTS csop_portal_log;
CREATE TABLE csop_portal_log (
  id varchar(32) COLLATE utf8_bin NOT NULL,
  content varchar(128) COLLATE utf8_bin NOT NULL,
  created_by varchar(16) COLLATE utf8_bin NOT NULL,
  creation_date datetime NOT NULL,
  log_level varchar(8) COLLATE utf8_bin DEFAULT NULL,
  log_type varchar(4) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY id (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/** product表中添加extra字段*/
alter table csop_product add extra longtext();
/** Customer表中添加省、市县字段*/
/** product表中添加extra字段*/
alter table csop_customer add PROVINCE varchar(20);
alter table csop_customer add CITY_ID varchar(20);
alter table csop_customer add EPARCHY_ID varchar(20);