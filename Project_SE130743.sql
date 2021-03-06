USE [master]
GO
/****** Object:  Database [TransactionProject]    Script Date: 12/28/2019 10:16:27 PM ******/
CREATE DATABASE [TransactionProject]
GO
ALTER DATABASE [TransactionProject] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [TransactionProject].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [TransactionProject] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [TransactionProject] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [TransactionProject] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [TransactionProject] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [TransactionProject] SET ARITHABORT OFF 
GO
ALTER DATABASE [TransactionProject] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [TransactionProject] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [TransactionProject] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [TransactionProject] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [TransactionProject] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [TransactionProject] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [TransactionProject] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [TransactionProject] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [TransactionProject] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [TransactionProject] SET  ENABLE_BROKER 
GO
ALTER DATABASE [TransactionProject] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [TransactionProject] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [TransactionProject] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [TransactionProject] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [TransactionProject] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [TransactionProject] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [TransactionProject] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [TransactionProject] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [TransactionProject] SET  MULTI_USER 
GO
ALTER DATABASE [TransactionProject] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [TransactionProject] SET DB_CHAINING OFF 
GO
ALTER DATABASE [TransactionProject] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [TransactionProject] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [TransactionProject] SET DELAYED_DURABILITY = DISABLED 
GO
USE [TransactionProject]
GO
/****** Object:  Table [dbo].[tbl_account]    Script Date: 12/28/2019 10:16:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_account](
	[accountID] [varchar](20) NOT NULL,
	[pin] [varchar](20) NULL,
	[balance] [float] NULL,
	[benefit] [float] NULL,
	[isExpired] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[accountID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbl_customerInfo]    Script Date: 12/28/2019 10:16:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_customerInfo](
	[custID] [varchar](20) NOT NULL,
	[custName] [varchar](50) NULL,
	[address1] [varchar](250) NULL,
	[address2] [varchar](250) NULL,
	[phone1] [varchar](12) NULL,
	[phone2] [varchar](12) NULL,
	[signature1] [varchar](250) NULL,
	[signature2] [varchar](250) NULL,
PRIMARY KEY CLUSTERED 
(
	[custID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbl_transaction]    Script Date: 12/28/2019 10:16:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_transaction](
	[transID] [int] IDENTITY(1,1) NOT NULL,
	[transDate] [datetime] NULL,
	[type] [int] NULL,
	[amount] [float] NULL,
	[reason] [varchar](250) NULL,
	[accountID] [varchar](20) NULL,
	[status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[transID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[tbl_account] ([accountID], [pin], [balance], [benefit], [isExpired]) VALUES (N'minh438', N'xyz1233', 5132300, 0.07, 0)
INSERT [dbo].[tbl_account] ([accountID], [pin], [balance], [benefit], [isExpired]) VALUES (N'ngoc576', N'basgt23', 3444600, 0.07, 0)
INSERT [dbo].[tbl_account] ([accountID], [pin], [balance], [benefit], [isExpired]) VALUES (N'nhat344', N'qwet453', 9777900, 0.07, 0)
INSERT [dbo].[tbl_account] ([accountID], [pin], [balance], [benefit], [isExpired]) VALUES (N'thanh123', N'abcd788', 2145200, 0.06, 0)
INSERT [dbo].[tbl_account] ([accountID], [pin], [balance], [benefit], [isExpired]) VALUES (N'vuyz34', N'opt9093', 200000, 0.06, 1)
INSERT [dbo].[tbl_customerInfo] ([custID], [custName], [address1], [address2], [phone1], [phone2], [signature1], [signature2]) VALUES (N'minh438', N'Tran Vu Minh', N'67/8a le thi muoi, q9, hcm', N'87b ly thuong kiet, q1, hcm', N'0273649327', N'0729123738', N'vminh', N'vuminh')
INSERT [dbo].[tbl_customerInfo] ([custID], [custName], [address1], [address2], [phone1], [phone2], [signature1], [signature2]) VALUES (N'ngoc576', N'Tran My Ngoc', N'12/5 xa binh hung, binh chanh, hcm', N'34/5 la van ty, phu my, tien giang ', N'0192734534', N'0192734536', N'ngoc', N'myngoc')
INSERT [dbo].[tbl_customerInfo] ([custID], [custName], [address1], [address2], [phone1], [phone2], [signature1], [signature2]) VALUES (N'nhat344', N'Le Van Nhat', N'67/3 au co, q3, hcm', N'34/5 lac long quan, q1, hcm', N'0123487564', N'0734268262', N'mnhut', N'cmnht')
INSERT [dbo].[tbl_customerInfo] ([custID], [custName], [address1], [address2], [phone1], [phone2], [signature1], [signature2]) VALUES (N'thanh123', N'Nguyen Chanh Thanh', N'23/7 tran boi co, q10, hcm', N'23/7 tran boi co, q10, hcm', N'0937296718', N'0904738474', N'thanh', N'cthanh')
INSERT [dbo].[tbl_customerInfo] ([custID], [custName], [address1], [address2], [phone1], [phone2], [signature1], [signature2]) VALUES (N'vuyz34', N'Ha Thai Vu', N'192c tran van on, q1, hcm', N'56c thach thi ly, hau le, ca mau', N'0927373543', N'0283463535', N'hthvu', N'thaivu')
SET IDENTITY_INSERT [dbo].[tbl_transaction] ON 

INSERT [dbo].[tbl_transaction] ([transID], [transDate], [type], [amount], [reason], [accountID], [status]) VALUES (7, CAST(N'2019-07-10 09:15:18.000' AS DateTime), 2, 300, N'ngoc576 needs money', N'thanh123', 1)
INSERT [dbo].[tbl_transaction] ([transID], [transDate], [type], [amount], [reason], [accountID], [status]) VALUES (8, CAST(N'2019-07-10 09:25:28.000' AS DateTime), 2, 200000, N'thanh123 needs money', N'nhat344', 0)
INSERT [dbo].[tbl_transaction] ([transID], [transDate], [type], [amount], [reason], [accountID], [status]) VALUES (9, CAST(N'2019-07-10 09:25:44.000' AS DateTime), 2, 2000, N'thanh123 needs money', N'nhat344', 0)
INSERT [dbo].[tbl_transaction] ([transID], [transDate], [type], [amount], [reason], [accountID], [status]) VALUES (10, CAST(N'2019-07-10 09:25:53.000' AS DateTime), 2, 100, N'minh438 needs money', N'nhat344', 1)
INSERT [dbo].[tbl_transaction] ([transID], [transDate], [type], [amount], [reason], [accountID], [status]) VALUES (11, CAST(N'2019-07-10 09:26:02.000' AS DateTime), 2, 50000, N'thanh123 needs money', N'nhat344', 1)
INSERT [dbo].[tbl_transaction] ([transID], [transDate], [type], [amount], [reason], [accountID], [status]) VALUES (12, CAST(N'2019-07-10 09:26:46.000' AS DateTime), 2, 30000, N'ngoc576 needs money', N'thanh123', 1)
INSERT [dbo].[tbl_transaction] ([transID], [transDate], [type], [amount], [reason], [accountID], [status]) VALUES (13, CAST(N'2019-07-10 07:41:41.000' AS DateTime), 2, 5000, N'nhut344 needs money', N'thanh123', 1)
INSERT [dbo].[tbl_transaction] ([transID], [transDate], [type], [amount], [reason], [accountID], [status]) VALUES (14, CAST(N'2019-07-12 10:39:20.000' AS DateTime), 1, 30000, N'open new account', N'ngoc576', 1)
INSERT [dbo].[tbl_transaction] ([transID], [transDate], [type], [amount], [reason], [accountID], [status]) VALUES (15, CAST(N'2019-07-12 11:27:36.000' AS DateTime), 0, 40000, N'buy mobile car', N'ngoc576', 1)
INSERT [dbo].[tbl_transaction] ([transID], [transDate], [type], [amount], [reason], [accountID], [status]) VALUES (16, CAST(N'2019-07-12 11:40:54.000' AS DateTime), 0, 30000, N'buy food', N'ngoc576', 1)
INSERT [dbo].[tbl_transaction] ([transID], [transDate], [type], [amount], [reason], [accountID], [status]) VALUES (17, CAST(N'2019-07-12 11:50:33.000' AS DateTime), 0, 20000, N'buy gitfs', N'minh438', 1)
INSERT [dbo].[tbl_transaction] ([transID], [transDate], [type], [amount], [reason], [accountID], [status]) VALUES (18, CAST(N'2019-07-12 12:30:45.000' AS DateTime), 0, 50000, N'buy hat', N'minh438', 1)
INSERT [dbo].[tbl_transaction] ([transID], [transDate], [type], [amount], [reason], [accountID], [status]) VALUES (1014, CAST(N'2019-07-12 04:10:30.000' AS DateTime), 2, 20000, N'minh438 needs money', N'thanh123', 1)
INSERT [dbo].[tbl_transaction] ([transID], [transDate], [type], [amount], [reason], [accountID], [status]) VALUES (1015, CAST(N'2019-07-12 04:14:28.000' AS DateTime), 2, 20000, N'thanh123 needs money', N'minh438', 1)
INSERT [dbo].[tbl_transaction] ([transID], [transDate], [type], [amount], [reason], [accountID], [status]) VALUES (1016, CAST(N'2019-07-12 04:16:08.000' AS DateTime), 2, 30000, N'nhat344 needs money', N'ngoc576', 1)
INSERT [dbo].[tbl_transaction] ([transID], [transDate], [type], [amount], [reason], [accountID], [status]) VALUES (1017, CAST(N'2019-07-12 04:16:17.000' AS DateTime), 2, 40000, N'thanh123 needs money', N'ngoc576', 1)
INSERT [dbo].[tbl_transaction] ([transID], [transDate], [type], [amount], [reason], [accountID], [status]) VALUES (1018, CAST(N'2019-07-12 10:04:03.000' AS DateTime), 2, 9000, N'ngoc576 needs money', N'thanh123', 1)
INSERT [dbo].[tbl_transaction] ([transID], [transDate], [type], [amount], [reason], [accountID], [status]) VALUES (1019, CAST(N'2019-11-16 08:21:46.000' AS DateTime), 2, 20000, N'ngoc576 needs money', N'thanh123', 1)
INSERT [dbo].[tbl_transaction] ([transID], [transDate], [type], [amount], [reason], [accountID], [status]) VALUES (1020, CAST(N'2019-11-16 08:21:46.000' AS DateTime), 2, 20000, N'thanh123 transfered 20000.0 to you', N'ngoc576', 1)
SET IDENTITY_INSERT [dbo].[tbl_transaction] OFF
ALTER TABLE [dbo].[tbl_customerInfo]  WITH CHECK ADD  CONSTRAINT [FK_tbl_customerInfo_tbl_account] FOREIGN KEY([custID])
REFERENCES [dbo].[tbl_account] ([accountID])
GO
ALTER TABLE [dbo].[tbl_customerInfo] CHECK CONSTRAINT [FK_tbl_customerInfo_tbl_account]
GO
ALTER TABLE [dbo].[tbl_transaction]  WITH CHECK ADD FOREIGN KEY([accountID])
REFERENCES [dbo].[tbl_account] ([accountID])
GO
USE [master]
GO
ALTER DATABASE [TransactionProject] SET  READ_WRITE 
GO
