USE NorthwindSampleDb
GO

--Drop all table constraints
DECLARE @sql NVARCHAR(MAX) = N'';

SELECT @sql += N'
ALTER TABLE ' + QUOTENAME(OBJECT_SCHEMA_NAME(parent_object_id))
    + '.' + QUOTENAME(OBJECT_NAME(parent_object_id)) + 
    ' DROP CONSTRAINT ' + QUOTENAME(name) + ';'
FROM sys.foreign_keys;

PRINT @sql;
EXEC sp_executesql @sql;
--Drop table
IF OBJECT_ID('dbo.Customers','U') IS NOT NULL
    DROP TABLE dbo.[Customers];

CREATE TABLE [Customers] (
	[CustomerID] [nchar] (5) PRIMARY KEY  NOT NULL,
	[CompanyName] [varchar](50) NULL,
	[ContactName] [nvarchar](30) NULL,
	[ContactTitle] [nvarchar](30) NULL,
	[Address] [nvarchar](60) NULL,
	[City] [nvarchar](15) NULL,
	[Region] [nvarchar](15) NULL,
	[PostalCode] [nvarchar](12) NULL,
	[Country] [nvarchar](15) NULL,
	[Phone] [nvarchar](24) NULL,
	[Fax] [nvarchar](24) NULL
);

--Drop table
IF OBJECT_ID('dbo.Employees','U') IS NOT NULL
    DROP TABLE dbo.[Employees];

CREATE TABLE [Employees] (
	[EmployeeID] [int] PRIMARY KEY IDENTITY(1,1) NOT NULL,
	[LastName] [varchar](50) NULL,
	[FirstName] [varchar](50) NULL,
	[Title] [nvarchar](30) NULL,
	[TitleOfCourtesy] [nvarchar](25) NULL,
	[BirthDate] [date] NULL,
	[HireDate] [date] NULL,
	[Address] [nvarchar](60) NULL,
	[City] [nvarchar](15) NULL,
	[Region] [nvarchar](15) NULL,
	[PostalCode] [nvarchar](10) NULL,
	[Country] [nvarchar](15) NULL,
	[HomePhone] [nvarchar](24) NULL,
	[Extension] [nvarchar](4) NULL,
	[Photo] [nvarchar](255) NULL,
	[Notes] [nvarchar](2000) NULL,
	[ReportsTo] [int] NULL,
	[PhotoPath] [nvarchar](255) NULL
);

--Drop table
IF OBJECT_ID('dbo.Order','U') IS NOT NULL
    DROP TABLE dbo.[Order];

CREATE TABLE [Order] (
	[OrderId] [int] PRIMARY KEY IDENTITY(1,1) NOT NULL,
	[CustomerID] [nchar](5) NOT NULL,
	[EmployeeID] [int] NOT NULL,
	[OrderDate] [datetime2] NOT NULL,
	[RequiredDate] [date] NULL,
	[ShippedDate] [date] NULL,
	[ShipName] [nvarchar](40) NULL,
	[ShipVia] [int] NULL,
	[Freight] [decimal] NULL,
	[ShipAddress] [nvarchar](60) NULL,
	[ShipCity] [nvarchar](15) NULL,
	[ShipRegion] [nvarchar](15) NULL,
	[ShipPostalCode] [nvarchar](10) NULL,
	[ShipCountry] [nvarchar](15) NULL
);

--Drop table
IF OBJECT_ID('dbo.Shippers','U') IS NOT NULL
    DROP TABLE dbo.[Shippers];

CREATE TABLE [Shippers] (
	[ShipperId] [int] PRIMARY KEY IDENTITY(1,1) NOT NULL,
	[CompanyName] [nvarchar](40) NOT NULL,
	[Phone] [nvarchar](24) NULL
);

--Drop table
IF OBJECT_ID('dbo.EmployeesTerritories','U') IS NOT NULL
    DROP TABLE dbo.[EmployeesTerritories];

CREATE TABLE [EmployeesTerritories] (
	[EmpleymontId] [int] NOT NULL,
	[TerritoryID] [nvarchar] (20) NOT NULL,
CONSTRAINT PK_EmployeesTerritories PRIMARY KEY (EmpleymontId,TerritoryID)
);

--Drop table
IF OBJECT_ID('dbo.Region','U') IS NOT NULL
    DROP TABLE dbo.[Region];

CREATE TABLE [Region] (
	[RegionID] [int] PRIMARY KEY  NOT NULL,
	[RegionDescription] [nchar](50) NOT NULL
);

--Drop table
IF OBJECT_ID('dbo.Territories','U') IS NOT NULL
    DROP TABLE dbo.[Territories];

CREATE TABLE [Territories] (
	[TerritoryID] [nvarchar] (20) PRIMARY KEY  NOT NULL,
	[TerritoryDescription] [nchar](50) NOT NULL,
	[RegionID] [int] NOT NULL
);

--Drop table
IF OBJECT_ID('dbo.CustomersCustomersDemo','U') IS NOT NULL
    DROP TABLE dbo.[CustomersCustomersDemo];

CREATE TABLE [CustomersCustomersDemo] (
	[CustomerID] [nchar] (5) NOT NULL,
	[CustomersTypeId] [nchar] (10) NOT NULL,
CONSTRAINT PK_CustomersCustomersDemo PRIMARY KEY (CustomerID,CustomersTypeId)
);

--Drop table
IF OBJECT_ID('dbo.CustomersDemographics','U') IS NOT NULL
    DROP TABLE dbo.[CustomersDemographics];

CREATE TABLE [CustomersDemographics] (
	[CustomersTypeId] [nchar] (10) PRIMARY KEY  NOT NULL,
	[CustomersDesc] [nvarchar](2000) NULL
);

--Drop table
IF OBJECT_ID('dbo.OrderDetails','U') IS NOT NULL
    DROP TABLE dbo.[OrderDetails];

CREATE TABLE [OrderDetails] (
	[OrderId] [int] NOT NULL,
	[ProductId] [int] NOT NULL,
	[UnitPrice] [float] NOT NULL,
	[Quantity] [smallint] NOT NULL,
	[Discount] [float] NOT NULL,
CONSTRAINT PK_OrderDetails PRIMARY KEY (OrderId,ProductId)
);

--Drop table
IF OBJECT_ID('dbo.Products','U') IS NOT NULL
    DROP TABLE dbo.[Products];

CREATE TABLE [Products] (
	[ProductId] [int] PRIMARY KEY IDENTITY (1, 1) NOT NULL,
	[ProductName] [nvarchar](40) NOT NULL,
	[SupplierID] [int] NULL,
	[CategoryID] [int] NULL,
	[QuantityPerUnit] [nvarchar](20) NULL,
	[UnitPrice] [float] NULL,
	[UnitsInStock] [smallint] NULL,
	[UnitsOnOrder] [smallint] NULL,
	[ReorderLevel] [smallint] NULL,
	[Discontinued] [bit] NOT NULL
);

--Drop table
IF OBJECT_ID('dbo.Categories','U') IS NOT NULL
    DROP TABLE dbo.[Categories];

CREATE TABLE [Categories] (
	[CategoryID] [int] PRIMARY KEY IDENTITY (1, 1) NOT NULL,
	[CategoryName] [nvarchar](15) NOT NULL,
	[Description] [nvarchar](2000) NULL,
	[Picture] [nvarchar](255) NULL
);

--Drop table
IF OBJECT_ID('dbo.Suppliers','U') IS NOT NULL
    DROP TABLE dbo.[Suppliers];

CREATE TABLE [Suppliers] (
	[SupplierID] [int] PRIMARY KEY IDENTITY (1, 1) NOT NULL,
	[CompanyName] [nvarchar](40) NOT NULL,
	[ContactName] [nvarchar](30) NULL,
	[ContactTitle] [nvarchar](30) NULL,
	[Address] [nvarchar](60) NULL,
	[City] [nvarchar](15) NULL,
	[Region] [nvarchar](15) NULL,
	[PostalCode] [nvarchar](10) NULL,
	[Country] [nvarchar](15) NULL,
	[Phone] [nvarchar](24) NULL,
	[Fax] [nvarchar](24) NULL,
	[HomePage] [nvarchar](2000) NULL
);
IF NOT EXISTS(SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_Order_Customers]') AND parent_object_id = OBJECT_ID(N'[dbo].[Order]'))
ALTER TABLE [Order] ADD CONSTRAINT FK_Order_Customers FOREIGN KEY ([CustomerID]) REFERENCES [Customers] ([CustomerID])
	ON DELETE CASCADE
	ON UPDATE CASCADE
GO
IF OBJECT_ID('sp_Customers_Order_List', 'P') IS NOT NULL
	DROP PROCEDURE sp_Customers_Order_List;
GO
CREATE PROCEDURE sp_Customers_Order_List(
@CustomerID nchar(256)
)
AS
	SELECT * FROM [Order] WHERE [CustomerID] = @CustomerID 
RETURN
GO
IF NOT EXISTS(SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_CustomersCustomersDemo_Customers]') AND parent_object_id = OBJECT_ID(N'[dbo].[CustomersCustomersDemo]'))
ALTER TABLE [CustomersCustomersDemo] ADD CONSTRAINT FK_CustomersCustomersDemo_Customers FOREIGN KEY ([CustomerID]) REFERENCES [Customers] ([CustomerID])
	ON DELETE CASCADE
	ON UPDATE CASCADE
GO
IF OBJECT_ID('sp_Customers_CustomersCustomersDemo_List', 'P') IS NOT NULL
	DROP PROCEDURE sp_Customers_CustomersCustomersDemo_List;
GO
CREATE PROCEDURE sp_Customers_CustomersCustomersDemo_List(
@CustomerID nchar(256)
)
AS
	SELECT * FROM [CustomersCustomersDemo] WHERE [CustomerID] = @CustomerID 
RETURN
GO



IF OBJECT_ID ('sp_Customers_Insert', 'P' ) IS NOT NULL   
    DROP PROCEDURE sp_Customers_Insert;  
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE sp_Customers_Insert 
	@CustomerID nchar(5),
	@CompanyName varchar(50),
	@ContactName nvarchar(30),
	@ContactTitle nvarchar(30),
	@Address nvarchar(60),
	@City nvarchar(15),
	@Region nvarchar(15),
	@PostalCode nvarchar(12),
	@Country nvarchar(15),
	@Phone nvarchar(24),
	@Fax nvarchar(24)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
    INSERT INTO [Customers]
		([CustomerID], 
		[CompanyName], 
		[ContactName], 
		[ContactTitle], 
		[Address], 
		[City], 
		[Region], 
		[PostalCode], 
		[Country], 
		[Phone], 
		[Fax])
	VALUES (@CustomerID, 
		@CompanyName, 
		@ContactName, 
		@ContactTitle, 
		@Address, 
		@City, 
		@Region, 
		@PostalCode, 
		@Country, 
		@Phone, 
		@Fax)
END
GO

IF OBJECT_ID ('sp_Customers_Update', 'P' ) IS NOT NULL   
    DROP PROCEDURE sp_Customers_Update;  
GO

CREATE PROCEDURE sp_Customers_Update
		@CustomerID nchar(5),
	@CompanyName varchar(50),
	@ContactName nvarchar(30),
	@ContactTitle nvarchar(30),
	@Address nvarchar(60),
	@City nvarchar(15),
	@Region nvarchar(15),
	@PostalCode nvarchar(12),
	@Country nvarchar(15),
	@Phone nvarchar(24),
	@Fax nvarchar(24)
AS
BEGIN
	SET NOCOUNT ON;
	UPDATE [Customers] SET 
		[CompanyName] = @CompanyName,
		[ContactName] = @ContactName,
		[ContactTitle] = @ContactTitle,
		[Address] = @Address,
		[City] = @City,
		[Region] = @Region,
		[PostalCode] = @PostalCode,
		[Country] = @Country,
		[Phone] = @Phone,
		[Fax] = @Fax
	WHERE CustomerID = @CustomerID
END
GO

IF OBJECT_ID ('sp_Customers_Delete', 'P' ) IS NOT NULL   
    DROP PROCEDURE sp_Customers_Delete;  
GO

CREATE PROCEDURE sp_Customers_Delete
	@CustomerID nchar (5)
AS
BEGIN
    -- SET NOCOUNT ON added to prevent extra result sets from
    -- interfering with SELECT statements.
    SET NOCOUNT ON;

    DELETE [Customers] WHERE CustomerID = @CustomerID
END
GO


IF OBJECT_ID('sp_Customers_Select', 'P') IS NOT NULL  
   DROP PROCEDURE sp_Customers_Select;  
GO  

CREATE PROCEDURE sp_Customers_Select(
	@CustomerID nchar  (5)
)
AS    
   SELECT * FROM [Customers] WHERE CustomerID = @CustomerID
RETURN  
GO  

IF OBJECT_ID('sp_Customers_List', 'P') IS NOT NULL  
   DROP PROCEDURE sp_Customers_List;  
GO  

CREATE PROCEDURE sp_Customers_List(
	@pageNumber int = 0, 
	@pageSize int = 100
)
AS    
   SELECT * FROM [Customers]
	 ORDER BY CustomerID DESC
	 OFFSET @pageNumber ROWS 
		FETCH NEXT @pageSize ROWS ONLY;
RETURN  
GO  
IF NOT EXISTS(SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_Order_Employees]') AND parent_object_id = OBJECT_ID(N'[dbo].[Order]'))
ALTER TABLE [Order] ADD CONSTRAINT FK_Order_Employees FOREIGN KEY ([EmployeeID]) REFERENCES [Employees] ([EmployeeID])
	ON DELETE CASCADE
	ON UPDATE CASCADE
GO
IF OBJECT_ID('sp_Employees_Order_List', 'P') IS NOT NULL
	DROP PROCEDURE sp_Employees_Order_List;
GO
CREATE PROCEDURE sp_Employees_Order_List(
@EmployeeID int
)
AS
	SELECT * FROM [Order] WHERE [EmployeeID] = @EmployeeID 
RETURN
GO
IF NOT EXISTS(SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_EmployeesTerritories_Employees]') AND parent_object_id = OBJECT_ID(N'[dbo].[EmployeesTerritories]'))
ALTER TABLE [EmployeesTerritories] ADD CONSTRAINT FK_EmployeesTerritories_Employees FOREIGN KEY ([EmpleymontId]) REFERENCES [Employees] ([EmployeeID])
	ON DELETE CASCADE
	ON UPDATE CASCADE
GO
IF OBJECT_ID('sp_Employees_EmployeesTerritories_List', 'P') IS NOT NULL
	DROP PROCEDURE sp_Employees_EmployeesTerritories_List;
GO
CREATE PROCEDURE sp_Employees_EmployeesTerritories_List(
@EmpleymontId int
)
AS
	SELECT * FROM [EmployeesTerritories] WHERE [EmpleymontId] = @EmpleymontId 
RETURN
GO



IF OBJECT_ID ('sp_Employees_Insert', 'P' ) IS NOT NULL   
    DROP PROCEDURE sp_Employees_Insert;  
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE sp_Employees_Insert 
	@EmployeeID int,
	@LastName varchar(50),
	@FirstName varchar(50),
	@Title nvarchar(30),
	@TitleOfCourtesy nvarchar(25),
	@BirthDate date,
	@HireDate date,
	@Address nvarchar(60),
	@City nvarchar(15),
	@Region nvarchar(15),
	@PostalCode nvarchar(10),
	@Country nvarchar(15),
	@HomePhone nvarchar(24),
	@Extension nvarchar(4),
	@Photo nvarchar(255),
	@Notes nvarchar(2000),
	@ReportsTo int,
	@PhotoPath nvarchar(255)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
    INSERT INTO [Employees]
		([LastName], 
		[FirstName], 
		[Title], 
		[TitleOfCourtesy], 
		[BirthDate], 
		[HireDate], 
		[Address], 
		[City], 
		[Region], 
		[PostalCode], 
		[Country], 
		[HomePhone], 
		[Extension], 
		[Photo], 
		[Notes], 
		[ReportsTo], 
		[PhotoPath])
	VALUES (@LastName, 
		@FirstName, 
		@Title, 
		@TitleOfCourtesy, 
		@BirthDate, 
		@HireDate, 
		@Address, 
		@City, 
		@Region, 
		@PostalCode, 
		@Country, 
		@HomePhone, 
		@Extension, 
		@Photo, 
		@Notes, 
		@ReportsTo, 
		@PhotoPath)
END
GO

IF OBJECT_ID ('sp_Employees_Update', 'P' ) IS NOT NULL   
    DROP PROCEDURE sp_Employees_Update;  
GO

CREATE PROCEDURE sp_Employees_Update
		@EmployeeID int,
	@LastName varchar(50),
	@FirstName varchar(50),
	@Title nvarchar(30),
	@TitleOfCourtesy nvarchar(25),
	@BirthDate date,
	@HireDate date,
	@Address nvarchar(60),
	@City nvarchar(15),
	@Region nvarchar(15),
	@PostalCode nvarchar(10),
	@Country nvarchar(15),
	@HomePhone nvarchar(24),
	@Extension nvarchar(4),
	@Photo nvarchar(255),
	@Notes nvarchar(2000),
	@ReportsTo int,
	@PhotoPath nvarchar(255)
AS
BEGIN
	SET NOCOUNT ON;
	UPDATE [Employees] SET 
		[LastName] = @LastName,
		[FirstName] = @FirstName,
		[Title] = @Title,
		[TitleOfCourtesy] = @TitleOfCourtesy,
		[BirthDate] = @BirthDate,
		[HireDate] = @HireDate,
		[Address] = @Address,
		[City] = @City,
		[Region] = @Region,
		[PostalCode] = @PostalCode,
		[Country] = @Country,
		[HomePhone] = @HomePhone,
		[Extension] = @Extension,
		[Photo] = @Photo,
		[Notes] = @Notes,
		[ReportsTo] = @ReportsTo,
		[PhotoPath] = @PhotoPath
	WHERE EmployeeID = @EmployeeID
END
GO

IF OBJECT_ID ('sp_Employees_Delete', 'P' ) IS NOT NULL   
    DROP PROCEDURE sp_Employees_Delete;  
GO

CREATE PROCEDURE sp_Employees_Delete
	@EmployeeID int
AS
BEGIN
    -- SET NOCOUNT ON added to prevent extra result sets from
    -- interfering with SELECT statements.
    SET NOCOUNT ON;

    DELETE [Employees] WHERE EmployeeID = @EmployeeID
END
GO


IF OBJECT_ID('sp_Employees_Select', 'P') IS NOT NULL  
   DROP PROCEDURE sp_Employees_Select;  
GO  

CREATE PROCEDURE sp_Employees_Select(
	@EmployeeID int
)
AS    
   SELECT * FROM [Employees] WHERE EmployeeID = @EmployeeID
RETURN  
GO  

IF OBJECT_ID('sp_Employees_List', 'P') IS NOT NULL  
   DROP PROCEDURE sp_Employees_List;  
GO  

CREATE PROCEDURE sp_Employees_List(
	@pageNumber int = 0, 
	@pageSize int = 100
)
AS    
   SELECT * FROM [Employees]
	 ORDER BY EmployeeID DESC
	 OFFSET @pageNumber ROWS 
		FETCH NEXT @pageSize ROWS ONLY;
RETURN  
GO  
IF NOT EXISTS(SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_Order_Customers]') AND parent_object_id = OBJECT_ID(N'[dbo].[Order]'))
ALTER TABLE [Order] ADD CONSTRAINT FK_Order_Customers FOREIGN KEY ([CustomerID]) REFERENCES [Customers] ([CustomerID])
	ON DELETE CASCADE
	ON UPDATE CASCADE
GO
IF OBJECT_ID('sp_Customers_Order_List', 'P') IS NOT NULL
	DROP PROCEDURE sp_Customers_Order_List;
GO
CREATE PROCEDURE sp_Customers_Order_List(
@CustomerID nchar(256)
)
AS
	SELECT * FROM [Order] WHERE [CustomerID] = @CustomerID 
RETURN
GO
IF NOT EXISTS(SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_Order_Employees]') AND parent_object_id = OBJECT_ID(N'[dbo].[Order]'))
ALTER TABLE [Order] ADD CONSTRAINT FK_Order_Employees FOREIGN KEY ([EmployeeID]) REFERENCES [Employees] ([EmployeeID])
	ON DELETE CASCADE
	ON UPDATE CASCADE
GO
IF OBJECT_ID('sp_Employees_Order_List', 'P') IS NOT NULL
	DROP PROCEDURE sp_Employees_Order_List;
GO
CREATE PROCEDURE sp_Employees_Order_List(
@EmployeeID int
)
AS
	SELECT * FROM [Order] WHERE [EmployeeID] = @EmployeeID 
RETURN
GO
IF NOT EXISTS(SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_Order_Shippers]') AND parent_object_id = OBJECT_ID(N'[dbo].[Order]'))
ALTER TABLE [Order] ADD CONSTRAINT FK_Order_Shippers FOREIGN KEY ([ShipVia]) REFERENCES [Shippers] ([ShipperId])
	ON DELETE CASCADE
	ON UPDATE CASCADE
GO
IF OBJECT_ID('sp_Shippers_Order_List', 'P') IS NOT NULL
	DROP PROCEDURE sp_Shippers_Order_List;
GO
CREATE PROCEDURE sp_Shippers_Order_List(
@ShipVia int
)
AS
	SELECT * FROM [Order] WHERE [ShipVia] = @ShipVia 
RETURN
GO
IF NOT EXISTS(SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_OrderDetails_Order]') AND parent_object_id = OBJECT_ID(N'[dbo].[OrderDetails]'))
ALTER TABLE [OrderDetails] ADD CONSTRAINT FK_OrderDetails_Order FOREIGN KEY ([OrderId]) REFERENCES [Order] ([OrderId])
	ON DELETE CASCADE
	ON UPDATE CASCADE
GO
IF OBJECT_ID('sp_Order_OrderDetails_List', 'P') IS NOT NULL
	DROP PROCEDURE sp_Order_OrderDetails_List;
GO
CREATE PROCEDURE sp_Order_OrderDetails_List(
@OrderId int
)
AS
	SELECT * FROM [OrderDetails] WHERE [OrderId] = @OrderId 
RETURN
GO



IF OBJECT_ID ('sp_Order_Insert', 'P' ) IS NOT NULL   
    DROP PROCEDURE sp_Order_Insert;  
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE sp_Order_Insert 
	@OrderId int,
	@CustomerID nchar(5),
	@EmployeeID int,
	@OrderDate datetime2,
	@RequiredDate date,
	@ShippedDate date,
	@ShipName nvarchar(40),
	@ShipVia int,
	@Freight decimal,
	@ShipAddress nvarchar(60),
	@ShipCity nvarchar(15),
	@ShipRegion nvarchar(15),
	@ShipPostalCode nvarchar(10),
	@ShipCountry nvarchar(15)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
    INSERT INTO [Order]
		([CustomerID], 
		[EmployeeID], 
		[OrderDate], 
		[RequiredDate], 
		[ShippedDate], 
		[ShipName], 
		[ShipVia], 
		[Freight], 
		[ShipAddress], 
		[ShipCity], 
		[ShipRegion], 
		[ShipPostalCode], 
		[ShipCountry])
	VALUES (@CustomerID, 
		@EmployeeID, 
		@OrderDate, 
		@RequiredDate, 
		@ShippedDate, 
		@ShipName, 
		@ShipVia, 
		@Freight, 
		@ShipAddress, 
		@ShipCity, 
		@ShipRegion, 
		@ShipPostalCode, 
		@ShipCountry)
END
GO

IF OBJECT_ID ('sp_Order_Update', 'P' ) IS NOT NULL   
    DROP PROCEDURE sp_Order_Update;  
GO

CREATE PROCEDURE sp_Order_Update
		@OrderId int,
	@CustomerID nchar(5),
	@EmployeeID int,
	@OrderDate datetime2,
	@RequiredDate date,
	@ShippedDate date,
	@ShipName nvarchar(40),
	@ShipVia int,
	@Freight decimal,
	@ShipAddress nvarchar(60),
	@ShipCity nvarchar(15),
	@ShipRegion nvarchar(15),
	@ShipPostalCode nvarchar(10),
	@ShipCountry nvarchar(15)
AS
BEGIN
	SET NOCOUNT ON;
	UPDATE [Order] SET 
		[CustomerID] = @CustomerID,
		[EmployeeID] = @EmployeeID,
		[OrderDate] = @OrderDate,
		[RequiredDate] = @RequiredDate,
		[ShippedDate] = @ShippedDate,
		[ShipName] = @ShipName,
		[ShipVia] = @ShipVia,
		[Freight] = @Freight,
		[ShipAddress] = @ShipAddress,
		[ShipCity] = @ShipCity,
		[ShipRegion] = @ShipRegion,
		[ShipPostalCode] = @ShipPostalCode,
		[ShipCountry] = @ShipCountry
	WHERE OrderId = @OrderId
END
GO

IF OBJECT_ID ('sp_Order_Delete', 'P' ) IS NOT NULL   
    DROP PROCEDURE sp_Order_Delete;  
GO

CREATE PROCEDURE sp_Order_Delete
	@OrderId int
AS
BEGIN
    -- SET NOCOUNT ON added to prevent extra result sets from
    -- interfering with SELECT statements.
    SET NOCOUNT ON;

    DELETE [Order] WHERE OrderId = @OrderId
END
GO


IF OBJECT_ID('sp_Order_Select', 'P') IS NOT NULL  
   DROP PROCEDURE sp_Order_Select;  
GO  

CREATE PROCEDURE sp_Order_Select(
	@OrderId int
)
AS    
   SELECT * FROM [Order] WHERE OrderId = @OrderId
RETURN  
GO  

IF OBJECT_ID('sp_Order_List', 'P') IS NOT NULL  
   DROP PROCEDURE sp_Order_List;  
GO  

CREATE PROCEDURE sp_Order_List(
	@pageNumber int = 0, 
	@pageSize int = 100
)
AS    
   SELECT * FROM [Order]
	 ORDER BY OrderId DESC
	 OFFSET @pageNumber ROWS 
		FETCH NEXT @pageSize ROWS ONLY;
RETURN  
GO  
IF NOT EXISTS(SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_Order_Shippers]') AND parent_object_id = OBJECT_ID(N'[dbo].[Order]'))
ALTER TABLE [Order] ADD CONSTRAINT FK_Order_Shippers FOREIGN KEY ([ShipVia]) REFERENCES [Shippers] ([ShipperId])
	ON DELETE CASCADE
	ON UPDATE CASCADE
GO
IF OBJECT_ID('sp_Shippers_Order_List', 'P') IS NOT NULL
	DROP PROCEDURE sp_Shippers_Order_List;
GO
CREATE PROCEDURE sp_Shippers_Order_List(
@ShipVia int
)
AS
	SELECT * FROM [Order] WHERE [ShipVia] = @ShipVia 
RETURN
GO



IF OBJECT_ID ('sp_Shippers_Insert', 'P' ) IS NOT NULL   
    DROP PROCEDURE sp_Shippers_Insert;  
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE sp_Shippers_Insert 
	@ShipperId int,
	@CompanyName nvarchar(40),
	@Phone nvarchar(24)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
    INSERT INTO [Shippers]
		([CompanyName], 
		[Phone])
	VALUES (@CompanyName, 
		@Phone)
END
GO

IF OBJECT_ID ('sp_Shippers_Update', 'P' ) IS NOT NULL   
    DROP PROCEDURE sp_Shippers_Update;  
GO

CREATE PROCEDURE sp_Shippers_Update
		@ShipperId int,
	@CompanyName nvarchar(40),
	@Phone nvarchar(24)
AS
BEGIN
	SET NOCOUNT ON;
	UPDATE [Shippers] SET 
		[CompanyName] = @CompanyName,
		[Phone] = @Phone
	WHERE ShipperId = @ShipperId
END
GO

IF OBJECT_ID ('sp_Shippers_Delete', 'P' ) IS NOT NULL   
    DROP PROCEDURE sp_Shippers_Delete;  
GO

CREATE PROCEDURE sp_Shippers_Delete
	@ShipperId int
AS
BEGIN
    -- SET NOCOUNT ON added to prevent extra result sets from
    -- interfering with SELECT statements.
    SET NOCOUNT ON;

    DELETE [Shippers] WHERE ShipperId = @ShipperId
END
GO


IF OBJECT_ID('sp_Shippers_Select', 'P') IS NOT NULL  
   DROP PROCEDURE sp_Shippers_Select;  
GO  

CREATE PROCEDURE sp_Shippers_Select(
	@ShipperId int
)
AS    
   SELECT * FROM [Shippers] WHERE ShipperId = @ShipperId
RETURN  
GO  

IF OBJECT_ID('sp_Shippers_List', 'P') IS NOT NULL  
   DROP PROCEDURE sp_Shippers_List;  
GO  

CREATE PROCEDURE sp_Shippers_List(
	@pageNumber int = 0, 
	@pageSize int = 100
)
AS    
   SELECT * FROM [Shippers]
	 ORDER BY ShipperId DESC
	 OFFSET @pageNumber ROWS 
		FETCH NEXT @pageSize ROWS ONLY;
RETURN  
GO  
IF NOT EXISTS(SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_EmployeesTerritories_Employees]') AND parent_object_id = OBJECT_ID(N'[dbo].[EmployeesTerritories]'))
ALTER TABLE [EmployeesTerritories] ADD CONSTRAINT FK_EmployeesTerritories_Employees FOREIGN KEY ([EmpleymontId]) REFERENCES [Employees] ([EmployeeID])
	ON DELETE CASCADE
	ON UPDATE CASCADE
GO
IF OBJECT_ID('sp_Employees_EmployeesTerritories_List', 'P') IS NOT NULL
	DROP PROCEDURE sp_Employees_EmployeesTerritories_List;
GO
CREATE PROCEDURE sp_Employees_EmployeesTerritories_List(
@EmpleymontId int
)
AS
	SELECT * FROM [EmployeesTerritories] WHERE [EmpleymontId] = @EmpleymontId 
RETURN
GO
IF NOT EXISTS(SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_EmployeesTerritories_Territories]') AND parent_object_id = OBJECT_ID(N'[dbo].[EmployeesTerritories]'))
ALTER TABLE [EmployeesTerritories] ADD CONSTRAINT FK_EmployeesTerritories_Territories FOREIGN KEY ([TerritoryID]) REFERENCES [Territories] ([TerritoryID])
	ON DELETE CASCADE
	ON UPDATE CASCADE
GO
IF OBJECT_ID('sp_Territories_EmployeesTerritories_List', 'P') IS NOT NULL
	DROP PROCEDURE sp_Territories_EmployeesTerritories_List;
GO
CREATE PROCEDURE sp_Territories_EmployeesTerritories_List(
@TerritoryID int
)
AS
	SELECT * FROM [EmployeesTerritories] WHERE [TerritoryID] = @TerritoryID 
RETURN
GO



IF OBJECT_ID ('sp_EmployeesTerritories_Insert', 'P' ) IS NOT NULL   
    DROP PROCEDURE sp_EmployeesTerritories_Insert;  
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE sp_EmployeesTerritories_Insert 
	@EmpleymontId int,
	@TerritoryID nvarchar(20)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
    INSERT INTO [EmployeesTerritories]
		([EmpleymontId], 
		[TerritoryID])
	VALUES (@EmpleymontId, 
		@TerritoryID)
END
GO

IF OBJECT_ID ('sp_EmployeesTerritories_Delete', 'P' ) IS NOT NULL   
    DROP PROCEDURE sp_EmployeesTerritories_Delete;  
GO

CREATE PROCEDURE sp_EmployeesTerritories_Delete
	@EmpleymontId int,
	@TerritoryID nvarchar (20)
AS
BEGIN
    -- SET NOCOUNT ON added to prevent extra result sets from
    -- interfering with SELECT statements.
    SET NOCOUNT ON;

    DELETE [EmployeesTerritories] WHERE EmpleymontId = @EmpleymontId and TerritoryID = @TerritoryID
END
GO


IF OBJECT_ID('sp_EmployeesTerritories_Select', 'P') IS NOT NULL  
   DROP PROCEDURE sp_EmployeesTerritories_Select;  
GO  

CREATE PROCEDURE sp_EmployeesTerritories_Select(
	@EmpleymontId int ,
 @TerritoryID nvarchar  (20)
)
AS    
   SELECT * FROM [EmployeesTerritories] WHERE EmpleymontId = @EmpleymontId and TerritoryID = @TerritoryID
RETURN  
GO  

IF OBJECT_ID('sp_EmployeesTerritories_List', 'P') IS NOT NULL  
   DROP PROCEDURE sp_EmployeesTerritories_List;  
GO  

CREATE PROCEDURE sp_EmployeesTerritories_List(
	@pageNumber int = 0, 
	@pageSize int = 100
)
AS    
   SELECT * FROM [EmployeesTerritories]
	 ORDER BY EmpleymontId, TerritoryID DESC
	 OFFSET @pageNumber ROWS 
		FETCH NEXT @pageSize ROWS ONLY;
RETURN  
GO  
IF NOT EXISTS(SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_Territories_Region]') AND parent_object_id = OBJECT_ID(N'[dbo].[Territories]'))
ALTER TABLE [Territories] ADD CONSTRAINT FK_Territories_Region FOREIGN KEY ([RegionID]) REFERENCES [Region] ([RegionID])
	ON DELETE CASCADE
	ON UPDATE CASCADE
GO
IF OBJECT_ID('sp_Region_Territories_List', 'P') IS NOT NULL
	DROP PROCEDURE sp_Region_Territories_List;
GO
CREATE PROCEDURE sp_Region_Territories_List(
@RegionID int
)
AS
	SELECT * FROM [Territories] WHERE [RegionID] = @RegionID 
RETURN
GO



IF OBJECT_ID ('sp_Region_Insert', 'P' ) IS NOT NULL   
    DROP PROCEDURE sp_Region_Insert;  
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE sp_Region_Insert 
	@RegionID int,
	@RegionDescription nchar(50)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
    INSERT INTO [Region]
		([RegionID], 
		[RegionDescription])
	VALUES (@RegionID, 
		@RegionDescription)
END
GO

IF OBJECT_ID ('sp_Region_Update', 'P' ) IS NOT NULL   
    DROP PROCEDURE sp_Region_Update;  
GO

CREATE PROCEDURE sp_Region_Update
		@RegionID int,
	@RegionDescription nchar(50)
AS
BEGIN
	SET NOCOUNT ON;
	UPDATE [Region] SET 
		[RegionDescription] = @RegionDescription
	WHERE RegionID = @RegionID
END
GO

IF OBJECT_ID ('sp_Region_Delete', 'P' ) IS NOT NULL   
    DROP PROCEDURE sp_Region_Delete;  
GO

CREATE PROCEDURE sp_Region_Delete
	@RegionID int
AS
BEGIN
    -- SET NOCOUNT ON added to prevent extra result sets from
    -- interfering with SELECT statements.
    SET NOCOUNT ON;

    DELETE [Region] WHERE RegionID = @RegionID
END
GO


IF OBJECT_ID('sp_Region_Select', 'P') IS NOT NULL  
   DROP PROCEDURE sp_Region_Select;  
GO  

CREATE PROCEDURE sp_Region_Select(
	@RegionID int
)
AS    
   SELECT * FROM [Region] WHERE RegionID = @RegionID
RETURN  
GO  

IF OBJECT_ID('sp_Region_List', 'P') IS NOT NULL  
   DROP PROCEDURE sp_Region_List;  
GO  

CREATE PROCEDURE sp_Region_List(
	@pageNumber int = 0, 
	@pageSize int = 100
)
AS    
   SELECT * FROM [Region]
	 ORDER BY RegionID DESC
	 OFFSET @pageNumber ROWS 
		FETCH NEXT @pageSize ROWS ONLY;
RETURN  
GO  
IF NOT EXISTS(SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_EmployeesTerritories_Territories]') AND parent_object_id = OBJECT_ID(N'[dbo].[EmployeesTerritories]'))
ALTER TABLE [EmployeesTerritories] ADD CONSTRAINT FK_EmployeesTerritories_Territories FOREIGN KEY ([TerritoryID]) REFERENCES [Territories] ([TerritoryID])
	ON DELETE CASCADE
	ON UPDATE CASCADE
GO
IF OBJECT_ID('sp_Territories_EmployeesTerritories_List', 'P') IS NOT NULL
	DROP PROCEDURE sp_Territories_EmployeesTerritories_List;
GO
CREATE PROCEDURE sp_Territories_EmployeesTerritories_List(
@TerritoryID int
)
AS
	SELECT * FROM [EmployeesTerritories] WHERE [TerritoryID] = @TerritoryID 
RETURN
GO
IF NOT EXISTS(SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_Territories_Region]') AND parent_object_id = OBJECT_ID(N'[dbo].[Territories]'))
ALTER TABLE [Territories] ADD CONSTRAINT FK_Territories_Region FOREIGN KEY ([RegionID]) REFERENCES [Region] ([RegionID])
	ON DELETE CASCADE
	ON UPDATE CASCADE
GO
IF OBJECT_ID('sp_Region_Territories_List', 'P') IS NOT NULL
	DROP PROCEDURE sp_Region_Territories_List;
GO
CREATE PROCEDURE sp_Region_Territories_List(
@RegionID int
)
AS
	SELECT * FROM [Territories] WHERE [RegionID] = @RegionID 
RETURN
GO



IF OBJECT_ID ('sp_Territories_Insert', 'P' ) IS NOT NULL   
    DROP PROCEDURE sp_Territories_Insert;  
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE sp_Territories_Insert 
	@TerritoryID nvarchar(20),
	@TerritoryDescription nchar(50),
	@RegionID int
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
    INSERT INTO [Territories]
		([TerritoryID], 
		[TerritoryDescription], 
		[RegionID])
	VALUES (@TerritoryID, 
		@TerritoryDescription, 
		@RegionID)
END
GO

IF OBJECT_ID ('sp_Territories_Update', 'P' ) IS NOT NULL   
    DROP PROCEDURE sp_Territories_Update;  
GO

CREATE PROCEDURE sp_Territories_Update
		@TerritoryID nvarchar(20),
	@TerritoryDescription nchar(50),
	@RegionID int
AS
BEGIN
	SET NOCOUNT ON;
	UPDATE [Territories] SET 
		[TerritoryDescription] = @TerritoryDescription,
		[RegionID] = @RegionID
	WHERE TerritoryID = @TerritoryID
END
GO

IF OBJECT_ID ('sp_Territories_Delete', 'P' ) IS NOT NULL   
    DROP PROCEDURE sp_Territories_Delete;  
GO

CREATE PROCEDURE sp_Territories_Delete
	@TerritoryID nvarchar (20)
AS
BEGIN
    -- SET NOCOUNT ON added to prevent extra result sets from
    -- interfering with SELECT statements.
    SET NOCOUNT ON;

    DELETE [Territories] WHERE TerritoryID = @TerritoryID
END
GO


IF OBJECT_ID('sp_Territories_Select', 'P') IS NOT NULL  
   DROP PROCEDURE sp_Territories_Select;  
GO  

CREATE PROCEDURE sp_Territories_Select(
	@TerritoryID nvarchar  (20)
)
AS    
   SELECT * FROM [Territories] WHERE TerritoryID = @TerritoryID
RETURN  
GO  

IF OBJECT_ID('sp_Territories_List', 'P') IS NOT NULL  
   DROP PROCEDURE sp_Territories_List;  
GO  

CREATE PROCEDURE sp_Territories_List(
	@pageNumber int = 0, 
	@pageSize int = 100
)
AS    
   SELECT * FROM [Territories]
	 ORDER BY TerritoryID DESC
	 OFFSET @pageNumber ROWS 
		FETCH NEXT @pageSize ROWS ONLY;
RETURN  
GO  
IF NOT EXISTS(SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_CustomersCustomersDemo_Customers]') AND parent_object_id = OBJECT_ID(N'[dbo].[CustomersCustomersDemo]'))
ALTER TABLE [CustomersCustomersDemo] ADD CONSTRAINT FK_CustomersCustomersDemo_Customers FOREIGN KEY ([CustomerID]) REFERENCES [Customers] ([CustomerID])
	ON DELETE CASCADE
	ON UPDATE CASCADE
GO
IF OBJECT_ID('sp_Customers_CustomersCustomersDemo_List', 'P') IS NOT NULL
	DROP PROCEDURE sp_Customers_CustomersCustomersDemo_List;
GO
CREATE PROCEDURE sp_Customers_CustomersCustomersDemo_List(
@CustomerID nchar(256)
)
AS
	SELECT * FROM [CustomersCustomersDemo] WHERE [CustomerID] = @CustomerID 
RETURN
GO
IF NOT EXISTS(SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_CustomersCustomersDemo_CustomersDemographics]') AND parent_object_id = OBJECT_ID(N'[dbo].[CustomersCustomersDemo]'))
ALTER TABLE [CustomersCustomersDemo] ADD CONSTRAINT FK_CustomersCustomersDemo_CustomersDemographics FOREIGN KEY ([CustomersTypeId]) REFERENCES [CustomersDemographics] ([CustomersTypeId])
	ON DELETE CASCADE
	ON UPDATE CASCADE
GO
IF OBJECT_ID('sp_CustomersDemographics_CustomersCustomersDemo_List', 'P') IS NOT NULL
	DROP PROCEDURE sp_CustomersDemographics_CustomersCustomersDemo_List;
GO
CREATE PROCEDURE sp_CustomersDemographics_CustomersCustomersDemo_List(
@CustomersTypeId nchar(256)
)
AS
	SELECT * FROM [CustomersCustomersDemo] WHERE [CustomersTypeId] = @CustomersTypeId 
RETURN
GO



IF OBJECT_ID ('sp_CustomersCustomersDemo_Insert', 'P' ) IS NOT NULL   
    DROP PROCEDURE sp_CustomersCustomersDemo_Insert;  
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE sp_CustomersCustomersDemo_Insert 
	@CustomerID nchar(5),
	@CustomersTypeId nchar(10)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
    INSERT INTO [CustomersCustomersDemo]
		([CustomerID], 
		[CustomersTypeId])
	VALUES (@CustomerID, 
		@CustomersTypeId)
END
GO

IF OBJECT_ID ('sp_CustomersCustomersDemo_Delete', 'P' ) IS NOT NULL   
    DROP PROCEDURE sp_CustomersCustomersDemo_Delete;  
GO

CREATE PROCEDURE sp_CustomersCustomersDemo_Delete
	@CustomerID nchar (5),
	@CustomersTypeId nchar (10)
AS
BEGIN
    -- SET NOCOUNT ON added to prevent extra result sets from
    -- interfering with SELECT statements.
    SET NOCOUNT ON;

    DELETE [CustomersCustomersDemo] WHERE CustomerID = @CustomerID and CustomersTypeId = @CustomersTypeId
END
GO


IF OBJECT_ID('sp_CustomersCustomersDemo_Select', 'P') IS NOT NULL  
   DROP PROCEDURE sp_CustomersCustomersDemo_Select;  
GO  

CREATE PROCEDURE sp_CustomersCustomersDemo_Select(
	@CustomerID nchar  (5),
 @CustomersTypeId nchar  (10)
)
AS    
   SELECT * FROM [CustomersCustomersDemo] WHERE CustomerID = @CustomerID and CustomersTypeId = @CustomersTypeId
RETURN  
GO  

IF OBJECT_ID('sp_CustomersCustomersDemo_List', 'P') IS NOT NULL  
   DROP PROCEDURE sp_CustomersCustomersDemo_List;  
GO  

CREATE PROCEDURE sp_CustomersCustomersDemo_List(
	@pageNumber int = 0, 
	@pageSize int = 100
)
AS    
   SELECT * FROM [CustomersCustomersDemo]
	 ORDER BY CustomerID, CustomersTypeId DESC
	 OFFSET @pageNumber ROWS 
		FETCH NEXT @pageSize ROWS ONLY;
RETURN  
GO  
IF NOT EXISTS(SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_CustomersCustomersDemo_CustomersDemographics]') AND parent_object_id = OBJECT_ID(N'[dbo].[CustomersCustomersDemo]'))
ALTER TABLE [CustomersCustomersDemo] ADD CONSTRAINT FK_CustomersCustomersDemo_CustomersDemographics FOREIGN KEY ([CustomersTypeId]) REFERENCES [CustomersDemographics] ([CustomersTypeId])
	ON DELETE CASCADE
	ON UPDATE CASCADE
GO
IF OBJECT_ID('sp_CustomersDemographics_CustomersCustomersDemo_List', 'P') IS NOT NULL
	DROP PROCEDURE sp_CustomersDemographics_CustomersCustomersDemo_List;
GO
CREATE PROCEDURE sp_CustomersDemographics_CustomersCustomersDemo_List(
@CustomersTypeId nchar(256)
)
AS
	SELECT * FROM [CustomersCustomersDemo] WHERE [CustomersTypeId] = @CustomersTypeId 
RETURN
GO



IF OBJECT_ID ('sp_CustomersDemographics_Insert', 'P' ) IS NOT NULL   
    DROP PROCEDURE sp_CustomersDemographics_Insert;  
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE sp_CustomersDemographics_Insert 
	@CustomersTypeId nchar(10),
	@CustomersDesc nvarchar(2000)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
    INSERT INTO [CustomersDemographics]
		([CustomersTypeId], 
		[CustomersDesc])
	VALUES (@CustomersTypeId, 
		@CustomersDesc)
END
GO

IF OBJECT_ID ('sp_CustomersDemographics_Update', 'P' ) IS NOT NULL   
    DROP PROCEDURE sp_CustomersDemographics_Update;  
GO

CREATE PROCEDURE sp_CustomersDemographics_Update
		@CustomersTypeId nchar(10),
	@CustomersDesc nvarchar(2000)
AS
BEGIN
	SET NOCOUNT ON;
	UPDATE [CustomersDemographics] SET 
		[CustomersDesc] = @CustomersDesc
	WHERE CustomersTypeId = @CustomersTypeId
END
GO

IF OBJECT_ID ('sp_CustomersDemographics_Delete', 'P' ) IS NOT NULL   
    DROP PROCEDURE sp_CustomersDemographics_Delete;  
GO

CREATE PROCEDURE sp_CustomersDemographics_Delete
	@CustomersTypeId nchar (10)
AS
BEGIN
    -- SET NOCOUNT ON added to prevent extra result sets from
    -- interfering with SELECT statements.
    SET NOCOUNT ON;

    DELETE [CustomersDemographics] WHERE CustomersTypeId = @CustomersTypeId
END
GO


IF OBJECT_ID('sp_CustomersDemographics_Select', 'P') IS NOT NULL  
   DROP PROCEDURE sp_CustomersDemographics_Select;  
GO  

CREATE PROCEDURE sp_CustomersDemographics_Select(
	@CustomersTypeId nchar  (10)
)
AS    
   SELECT * FROM [CustomersDemographics] WHERE CustomersTypeId = @CustomersTypeId
RETURN  
GO  

IF OBJECT_ID('sp_CustomersDemographics_List', 'P') IS NOT NULL  
   DROP PROCEDURE sp_CustomersDemographics_List;  
GO  

CREATE PROCEDURE sp_CustomersDemographics_List(
	@pageNumber int = 0, 
	@pageSize int = 100
)
AS    
   SELECT * FROM [CustomersDemographics]
	 ORDER BY CustomersTypeId DESC
	 OFFSET @pageNumber ROWS 
		FETCH NEXT @pageSize ROWS ONLY;
RETURN  
GO  
IF NOT EXISTS(SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_OrderDetails_Order]') AND parent_object_id = OBJECT_ID(N'[dbo].[OrderDetails]'))
ALTER TABLE [OrderDetails] ADD CONSTRAINT FK_OrderDetails_Order FOREIGN KEY ([OrderId]) REFERENCES [Order] ([OrderId])
	ON DELETE CASCADE
	ON UPDATE CASCADE
GO
IF OBJECT_ID('sp_Order_OrderDetails_List', 'P') IS NOT NULL
	DROP PROCEDURE sp_Order_OrderDetails_List;
GO
CREATE PROCEDURE sp_Order_OrderDetails_List(
@OrderId int
)
AS
	SELECT * FROM [OrderDetails] WHERE [OrderId] = @OrderId 
RETURN
GO
IF NOT EXISTS(SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_OrderDetails_Products]') AND parent_object_id = OBJECT_ID(N'[dbo].[OrderDetails]'))
ALTER TABLE [OrderDetails] ADD CONSTRAINT FK_OrderDetails_Products FOREIGN KEY ([ProductId]) REFERENCES [Products] ([ProductId])
	ON DELETE CASCADE
	ON UPDATE CASCADE
GO
IF OBJECT_ID('sp_Products_OrderDetails_List', 'P') IS NOT NULL
	DROP PROCEDURE sp_Products_OrderDetails_List;
GO
CREATE PROCEDURE sp_Products_OrderDetails_List(
@ProductId int
)
AS
	SELECT * FROM [OrderDetails] WHERE [ProductId] = @ProductId 
RETURN
GO



IF OBJECT_ID ('sp_OrderDetails_Insert', 'P' ) IS NOT NULL   
    DROP PROCEDURE sp_OrderDetails_Insert;  
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE sp_OrderDetails_Insert 
	@OrderId int,
	@ProductId int,
	@UnitPrice float,
	@Quantity smallint,
	@Discount float
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
    INSERT INTO [OrderDetails]
		([OrderId], 
		[ProductId], 
		[UnitPrice], 
		[Quantity], 
		[Discount])
	VALUES (@OrderId, 
		@ProductId, 
		@UnitPrice, 
		@Quantity, 
		@Discount)
END
GO

IF OBJECT_ID ('sp_OrderDetails_Update', 'P' ) IS NOT NULL   
    DROP PROCEDURE sp_OrderDetails_Update;  
GO

CREATE PROCEDURE sp_OrderDetails_Update
		@OrderId int,
	@ProductId int,
	@UnitPrice float,
	@Quantity smallint,
	@Discount float
AS
BEGIN
	SET NOCOUNT ON;
	UPDATE [OrderDetails] SET 
		[UnitPrice] = @UnitPrice,
		[Quantity] = @Quantity,
		[Discount] = @Discount
	WHERE OrderId = @OrderId and ProductId = @ProductId
END
GO

IF OBJECT_ID ('sp_OrderDetails_Delete', 'P' ) IS NOT NULL   
    DROP PROCEDURE sp_OrderDetails_Delete;  
GO

CREATE PROCEDURE sp_OrderDetails_Delete
	@OrderId int,
	@ProductId int
AS
BEGIN
    -- SET NOCOUNT ON added to prevent extra result sets from
    -- interfering with SELECT statements.
    SET NOCOUNT ON;

    DELETE [OrderDetails] WHERE OrderId = @OrderId and ProductId = @ProductId
END
GO


IF OBJECT_ID('sp_OrderDetails_Select', 'P') IS NOT NULL  
   DROP PROCEDURE sp_OrderDetails_Select;  
GO  

CREATE PROCEDURE sp_OrderDetails_Select(
	@OrderId int ,
 @ProductId int
)
AS    
   SELECT * FROM [OrderDetails] WHERE OrderId = @OrderId and ProductId = @ProductId
RETURN  
GO  

IF OBJECT_ID('sp_OrderDetails_List', 'P') IS NOT NULL  
   DROP PROCEDURE sp_OrderDetails_List;  
GO  

CREATE PROCEDURE sp_OrderDetails_List(
	@pageNumber int = 0, 
	@pageSize int = 100
)
AS    
   SELECT * FROM [OrderDetails]
	 ORDER BY OrderId, ProductId DESC
	 OFFSET @pageNumber ROWS 
		FETCH NEXT @pageSize ROWS ONLY;
RETURN  
GO  
IF NOT EXISTS(SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_OrderDetails_Products]') AND parent_object_id = OBJECT_ID(N'[dbo].[OrderDetails]'))
ALTER TABLE [OrderDetails] ADD CONSTRAINT FK_OrderDetails_Products FOREIGN KEY ([ProductId]) REFERENCES [Products] ([ProductId])
	ON DELETE CASCADE
	ON UPDATE CASCADE
GO
IF OBJECT_ID('sp_Products_OrderDetails_List', 'P') IS NOT NULL
	DROP PROCEDURE sp_Products_OrderDetails_List;
GO
CREATE PROCEDURE sp_Products_OrderDetails_List(
@ProductId int
)
AS
	SELECT * FROM [OrderDetails] WHERE [ProductId] = @ProductId 
RETURN
GO
IF NOT EXISTS(SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_Products_Categories]') AND parent_object_id = OBJECT_ID(N'[dbo].[Products]'))
ALTER TABLE [Products] ADD CONSTRAINT FK_Products_Categories FOREIGN KEY ([CategoryID]) REFERENCES [Categories] ([CategoryID])
	ON DELETE CASCADE
	ON UPDATE CASCADE
GO
IF OBJECT_ID('sp_Categories_Products_List', 'P') IS NOT NULL
	DROP PROCEDURE sp_Categories_Products_List;
GO
CREATE PROCEDURE sp_Categories_Products_List(
@CategoryID int
)
AS
	SELECT * FROM [Products] WHERE [CategoryID] = @CategoryID 
RETURN
GO
IF NOT EXISTS(SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_Products_Suppliers]') AND parent_object_id = OBJECT_ID(N'[dbo].[Products]'))
ALTER TABLE [Products] ADD CONSTRAINT FK_Products_Suppliers FOREIGN KEY ([SupplierID]) REFERENCES [Suppliers] ([SupplierID])
	ON DELETE CASCADE
	ON UPDATE CASCADE
GO
IF OBJECT_ID('sp_Suppliers_Products_List', 'P') IS NOT NULL
	DROP PROCEDURE sp_Suppliers_Products_List;
GO
CREATE PROCEDURE sp_Suppliers_Products_List(
@SupplierID int
)
AS
	SELECT * FROM [Products] WHERE [SupplierID] = @SupplierID 
RETURN
GO



IF OBJECT_ID ('sp_Products_Insert', 'P' ) IS NOT NULL   
    DROP PROCEDURE sp_Products_Insert;  
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE sp_Products_Insert 
	@ProductId int,
	@ProductName nvarchar(40),
	@SupplierID int,
	@CategoryID int,
	@QuantityPerUnit nvarchar(20),
	@UnitPrice float,
	@UnitsInStock smallint,
	@UnitsOnOrder smallint,
	@ReorderLevel smallint,
	@Discontinued bit
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
    INSERT INTO [Products]
		([ProductName], 
		[SupplierID], 
		[CategoryID], 
		[QuantityPerUnit], 
		[UnitPrice], 
		[UnitsInStock], 
		[UnitsOnOrder], 
		[ReorderLevel], 
		[Discontinued])
	VALUES (@ProductName, 
		@SupplierID, 
		@CategoryID, 
		@QuantityPerUnit, 
		@UnitPrice, 
		@UnitsInStock, 
		@UnitsOnOrder, 
		@ReorderLevel, 
		@Discontinued)
END
GO

IF OBJECT_ID ('sp_Products_Update', 'P' ) IS NOT NULL   
    DROP PROCEDURE sp_Products_Update;  
GO

CREATE PROCEDURE sp_Products_Update
		@ProductId int,
	@ProductName nvarchar(40),
	@SupplierID int,
	@CategoryID int,
	@QuantityPerUnit nvarchar(20),
	@UnitPrice float,
	@UnitsInStock smallint,
	@UnitsOnOrder smallint,
	@ReorderLevel smallint,
	@Discontinued bit
AS
BEGIN
	SET NOCOUNT ON;
	UPDATE [Products] SET 
		[ProductName] = @ProductName,
		[SupplierID] = @SupplierID,
		[CategoryID] = @CategoryID,
		[QuantityPerUnit] = @QuantityPerUnit,
		[UnitPrice] = @UnitPrice,
		[UnitsInStock] = @UnitsInStock,
		[UnitsOnOrder] = @UnitsOnOrder,
		[ReorderLevel] = @ReorderLevel,
		[Discontinued] = @Discontinued
	WHERE ProductId = @ProductId
END
GO

IF OBJECT_ID ('sp_Products_Delete', 'P' ) IS NOT NULL   
    DROP PROCEDURE sp_Products_Delete;  
GO

CREATE PROCEDURE sp_Products_Delete
	@ProductId int
AS
BEGIN
    -- SET NOCOUNT ON added to prevent extra result sets from
    -- interfering with SELECT statements.
    SET NOCOUNT ON;

    DELETE [Products] WHERE ProductId = @ProductId
END
GO


IF OBJECT_ID('sp_Products_Select', 'P') IS NOT NULL  
   DROP PROCEDURE sp_Products_Select;  
GO  

CREATE PROCEDURE sp_Products_Select(
	@ProductId int
)
AS    
   SELECT * FROM [Products] WHERE ProductId = @ProductId
RETURN  
GO  

IF OBJECT_ID('sp_Products_List', 'P') IS NOT NULL  
   DROP PROCEDURE sp_Products_List;  
GO  

CREATE PROCEDURE sp_Products_List(
	@pageNumber int = 0, 
	@pageSize int = 100
)
AS    
   SELECT * FROM [Products]
	 ORDER BY ProductId DESC
	 OFFSET @pageNumber ROWS 
		FETCH NEXT @pageSize ROWS ONLY;
RETURN  
GO  
IF NOT EXISTS(SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_Products_Categories]') AND parent_object_id = OBJECT_ID(N'[dbo].[Products]'))
ALTER TABLE [Products] ADD CONSTRAINT FK_Products_Categories FOREIGN KEY ([CategoryID]) REFERENCES [Categories] ([CategoryID])
	ON DELETE CASCADE
	ON UPDATE CASCADE
GO
IF OBJECT_ID('sp_Categories_Products_List', 'P') IS NOT NULL
	DROP PROCEDURE sp_Categories_Products_List;
GO
CREATE PROCEDURE sp_Categories_Products_List(
@CategoryID int
)
AS
	SELECT * FROM [Products] WHERE [CategoryID] = @CategoryID 
RETURN
GO



IF OBJECT_ID ('sp_Categories_Insert', 'P' ) IS NOT NULL   
    DROP PROCEDURE sp_Categories_Insert;  
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE sp_Categories_Insert 
	@CategoryID int,
	@CategoryName nvarchar(15),
	@Description nvarchar(2000),
	@Picture nvarchar(255)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
    INSERT INTO [Categories]
		([CategoryName], 
		[Description], 
		[Picture])
	VALUES (@CategoryName, 
		@Description, 
		@Picture)
END
GO

IF OBJECT_ID ('sp_Categories_Update', 'P' ) IS NOT NULL   
    DROP PROCEDURE sp_Categories_Update;  
GO

CREATE PROCEDURE sp_Categories_Update
		@CategoryID int,
	@CategoryName nvarchar(15),
	@Description nvarchar(2000),
	@Picture nvarchar(255)
AS
BEGIN
	SET NOCOUNT ON;
	UPDATE [Categories] SET 
		[CategoryName] = @CategoryName,
		[Description] = @Description,
		[Picture] = @Picture
	WHERE CategoryID = @CategoryID
END
GO

IF OBJECT_ID ('sp_Categories_Delete', 'P' ) IS NOT NULL   
    DROP PROCEDURE sp_Categories_Delete;  
GO

CREATE PROCEDURE sp_Categories_Delete
	@CategoryID int
AS
BEGIN
    -- SET NOCOUNT ON added to prevent extra result sets from
    -- interfering with SELECT statements.
    SET NOCOUNT ON;

    DELETE [Categories] WHERE CategoryID = @CategoryID
END
GO


IF OBJECT_ID('sp_Categories_Select', 'P') IS NOT NULL  
   DROP PROCEDURE sp_Categories_Select;  
GO  

CREATE PROCEDURE sp_Categories_Select(
	@CategoryID int
)
AS    
   SELECT * FROM [Categories] WHERE CategoryID = @CategoryID
RETURN  
GO  

IF OBJECT_ID('sp_Categories_List', 'P') IS NOT NULL  
   DROP PROCEDURE sp_Categories_List;  
GO  

CREATE PROCEDURE sp_Categories_List(
	@pageNumber int = 0, 
	@pageSize int = 100
)
AS    
   SELECT * FROM [Categories]
	 ORDER BY CategoryID DESC
	 OFFSET @pageNumber ROWS 
		FETCH NEXT @pageSize ROWS ONLY;
RETURN  
GO  
IF NOT EXISTS(SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_Products_Suppliers]') AND parent_object_id = OBJECT_ID(N'[dbo].[Products]'))
ALTER TABLE [Products] ADD CONSTRAINT FK_Products_Suppliers FOREIGN KEY ([SupplierID]) REFERENCES [Suppliers] ([SupplierID])
	ON DELETE CASCADE
	ON UPDATE CASCADE
GO
IF OBJECT_ID('sp_Suppliers_Products_List', 'P') IS NOT NULL
	DROP PROCEDURE sp_Suppliers_Products_List;
GO
CREATE PROCEDURE sp_Suppliers_Products_List(
@SupplierID int
)
AS
	SELECT * FROM [Products] WHERE [SupplierID] = @SupplierID 
RETURN
GO



IF OBJECT_ID ('sp_Suppliers_Insert', 'P' ) IS NOT NULL   
    DROP PROCEDURE sp_Suppliers_Insert;  
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE sp_Suppliers_Insert 
	@SupplierID int,
	@CompanyName nvarchar(40),
	@ContactName nvarchar(30),
	@ContactTitle nvarchar(30),
	@Address nvarchar(60),
	@City nvarchar(15),
	@Region nvarchar(15),
	@PostalCode nvarchar(10),
	@Country nvarchar(15),
	@Phone nvarchar(24),
	@Fax nvarchar(24),
	@HomePage nvarchar(2000)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
    INSERT INTO [Suppliers]
		([CompanyName], 
		[ContactName], 
		[ContactTitle], 
		[Address], 
		[City], 
		[Region], 
		[PostalCode], 
		[Country], 
		[Phone], 
		[Fax], 
		[HomePage])
	VALUES (@CompanyName, 
		@ContactName, 
		@ContactTitle, 
		@Address, 
		@City, 
		@Region, 
		@PostalCode, 
		@Country, 
		@Phone, 
		@Fax, 
		@HomePage)
END
GO

IF OBJECT_ID ('sp_Suppliers_Update', 'P' ) IS NOT NULL   
    DROP PROCEDURE sp_Suppliers_Update;  
GO

CREATE PROCEDURE sp_Suppliers_Update
		@SupplierID int,
	@CompanyName nvarchar(40),
	@ContactName nvarchar(30),
	@ContactTitle nvarchar(30),
	@Address nvarchar(60),
	@City nvarchar(15),
	@Region nvarchar(15),
	@PostalCode nvarchar(10),
	@Country nvarchar(15),
	@Phone nvarchar(24),
	@Fax nvarchar(24),
	@HomePage nvarchar(2000)
AS
BEGIN
	SET NOCOUNT ON;
	UPDATE [Suppliers] SET 
		[CompanyName] = @CompanyName,
		[ContactName] = @ContactName,
		[ContactTitle] = @ContactTitle,
		[Address] = @Address,
		[City] = @City,
		[Region] = @Region,
		[PostalCode] = @PostalCode,
		[Country] = @Country,
		[Phone] = @Phone,
		[Fax] = @Fax,
		[HomePage] = @HomePage
	WHERE SupplierID = @SupplierID
END
GO

IF OBJECT_ID ('sp_Suppliers_Delete', 'P' ) IS NOT NULL   
    DROP PROCEDURE sp_Suppliers_Delete;  
GO

CREATE PROCEDURE sp_Suppliers_Delete
	@SupplierID int
AS
BEGIN
    -- SET NOCOUNT ON added to prevent extra result sets from
    -- interfering with SELECT statements.
    SET NOCOUNT ON;

    DELETE [Suppliers] WHERE SupplierID = @SupplierID
END
GO


IF OBJECT_ID('sp_Suppliers_Select', 'P') IS NOT NULL  
   DROP PROCEDURE sp_Suppliers_Select;  
GO  

CREATE PROCEDURE sp_Suppliers_Select(
	@SupplierID int
)
AS    
   SELECT * FROM [Suppliers] WHERE SupplierID = @SupplierID
RETURN  
GO  

IF OBJECT_ID('sp_Suppliers_List', 'P') IS NOT NULL  
   DROP PROCEDURE sp_Suppliers_List;  
GO  

CREATE PROCEDURE sp_Suppliers_List(
	@pageNumber int = 0, 
	@pageSize int = 100
)
AS    
   SELECT * FROM [Suppliers]
	 ORDER BY SupplierID DESC
	 OFFSET @pageNumber ROWS 
		FETCH NEXT @pageSize ROWS ONLY;
RETURN  
GO  

/****** Object:  Table [dbo].[AspNetRoleClaims]    Script Date: 6/4/2018 10:18:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[AspNetRoleClaims]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[AspNetRoleClaims](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[ClaimType] [nvarchar](max) NULL,
	[ClaimValue] [nvarchar](max) NULL,
	[RoleId] [nvarchar](450) NOT NULL,
 CONSTRAINT [PK_AspNetRoleClaims] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
END
GO
/****** Object:  Table [dbo].[AspNetRoles]    Script Date: 6/4/2018 10:18:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[AspNetRoles]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[AspNetRoles](
	[Id] [nvarchar](450) NOT NULL,
	[ConcurrencyStamp] [nvarchar](max) NULL,
	[Name] [nvarchar](256) NULL,
	[NormalizedName] [nvarchar](256) NULL,
 CONSTRAINT [PK_AspNetRoles] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
END
GO
/****** Object:  Table [dbo].[AspNetUserClaims]    Script Date: 6/4/2018 10:18:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[AspNetUserClaims]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[AspNetUserClaims](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[ClaimType] [nvarchar](max) NULL,
	[ClaimValue] [nvarchar](max) NULL,
	[UserId] [nvarchar](450) NOT NULL,
 CONSTRAINT [PK_AspNetUserClaims] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
END
GO
/****** Object:  Table [dbo].[AspNetUserLogins]    Script Date: 6/4/2018 10:18:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[AspNetUserLogins]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[AspNetUserLogins](
	[LoginProvider] [nvarchar](450) NOT NULL,
	[ProviderKey] [nvarchar](450) NOT NULL,
	[ProviderDisplayName] [nvarchar](max) NULL,
	[UserId] [nvarchar](450) NOT NULL,
 CONSTRAINT [PK_AspNetUserLogins] PRIMARY KEY CLUSTERED 
(
	[LoginProvider] ASC,
	[ProviderKey] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
END
GO
/****** Object:  Table [dbo].[AspNetUserRoles]    Script Date: 6/4/2018 10:18:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[AspNetUserRoles]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[AspNetUserRoles](
	[UserId] [nvarchar](450) NOT NULL,
	[RoleId] [nvarchar](450) NOT NULL,
 CONSTRAINT [PK_AspNetUserRoles] PRIMARY KEY CLUSTERED 
(
	[UserId] ASC,
	[RoleId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
END
GO
/****** Object:  Table [dbo].[AspNetUsers]    Script Date: 6/4/2018 10:18:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[AspNetUsers]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[AspNetUsers](
	[Id] [nvarchar](450) NOT NULL,
	[AccessFailedCount] [int] NOT NULL,
	[ConcurrencyStamp] [nvarchar](max) NULL,
	[Email] [nvarchar](256) NULL,
	[EmailConfirmed] [bit] NOT NULL,
	[LockoutEnabled] [bit] NOT NULL,
	[LockoutEnd] [datetimeoffset](7) NULL,
	[NormalizedEmail] [nvarchar](256) NULL,
	[NormalizedUserName] [nvarchar](256) NULL,
	[PasswordHash] [nvarchar](max) NULL,
	[PhoneNumber] [nvarchar](max) NULL,
	[PhoneNumberConfirmed] [bit] NOT NULL,
	[SecurityStamp] [nvarchar](max) NULL,
	[TwoFactorEnabled] [bit] NOT NULL,
	[UserName] [nvarchar](256) NULL,
 CONSTRAINT [PK_AspNetUsers] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
END
GO
/****** Object:  Table [dbo].[AspNetUserTokens]    Script Date: 6/4/2018 10:18:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[AspNetUserTokens]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[AspNetUserTokens](
	[UserId] [nvarchar](450) NOT NULL,
	[LoginProvider] [nvarchar](450) NOT NULL,
	[Name] [nvarchar](450) NOT NULL,
	[Value] [nvarchar](max) NULL,
 CONSTRAINT [PK_AspNetUserTokens] PRIMARY KEY CLUSTERED 
(
	[UserId] ASC,
	[LoginProvider] ASC,
	[Name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
END
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_AspNetRoleClaims_AspNetRoles_RoleId]') AND parent_object_id = OBJECT_ID(N'[dbo].[AspNetRoleClaims]'))
ALTER TABLE [dbo].[AspNetRoleClaims]  WITH CHECK ADD  CONSTRAINT [FK_AspNetRoleClaims_AspNetRoles_RoleId] FOREIGN KEY([RoleId])
REFERENCES [dbo].[AspNetRoles] ([Id])
ON DELETE CASCADE
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_AspNetRoleClaims_AspNetRoles_RoleId]') AND parent_object_id = OBJECT_ID(N'[dbo].[AspNetRoleClaims]'))
ALTER TABLE [dbo].[AspNetRoleClaims] CHECK CONSTRAINT [FK_AspNetRoleClaims_AspNetRoles_RoleId]
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_AspNetUserClaims_AspNetUsers_UserId]') AND parent_object_id = OBJECT_ID(N'[dbo].[AspNetUserClaims]'))
ALTER TABLE [dbo].[AspNetUserClaims]  WITH CHECK ADD  CONSTRAINT [FK_AspNetUserClaims_AspNetUsers_UserId] FOREIGN KEY([UserId])
REFERENCES [dbo].[AspNetUsers] ([Id])
ON DELETE CASCADE
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_AspNetUserClaims_AspNetUsers_UserId]') AND parent_object_id = OBJECT_ID(N'[dbo].[AspNetUserClaims]'))
ALTER TABLE [dbo].[AspNetUserClaims] CHECK CONSTRAINT [FK_AspNetUserClaims_AspNetUsers_UserId]
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_AspNetUserLogins_AspNetUsers_UserId]') AND parent_object_id = OBJECT_ID(N'[dbo].[AspNetUserLogins]'))
ALTER TABLE [dbo].[AspNetUserLogins]  WITH CHECK ADD  CONSTRAINT [FK_AspNetUserLogins_AspNetUsers_UserId] FOREIGN KEY([UserId])
REFERENCES [dbo].[AspNetUsers] ([Id])
ON DELETE CASCADE
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_AspNetUserLogins_AspNetUsers_UserId]') AND parent_object_id = OBJECT_ID(N'[dbo].[AspNetUserLogins]'))
ALTER TABLE [dbo].[AspNetUserLogins] CHECK CONSTRAINT [FK_AspNetUserLogins_AspNetUsers_UserId]
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_AspNetUserRoles_AspNetRoles_RoleId]') AND parent_object_id = OBJECT_ID(N'[dbo].[AspNetUserRoles]'))
ALTER TABLE [dbo].[AspNetUserRoles]  WITH CHECK ADD  CONSTRAINT [FK_AspNetUserRoles_AspNetRoles_RoleId] FOREIGN KEY([RoleId])
REFERENCES [dbo].[AspNetRoles] ([Id])
ON DELETE CASCADE
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_AspNetUserRoles_AspNetRoles_RoleId]') AND parent_object_id = OBJECT_ID(N'[dbo].[AspNetUserRoles]'))
ALTER TABLE [dbo].[AspNetUserRoles] CHECK CONSTRAINT [FK_AspNetUserRoles_AspNetRoles_RoleId]
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_AspNetUserRoles_AspNetUsers_UserId]') AND parent_object_id = OBJECT_ID(N'[dbo].[AspNetUserRoles]'))
ALTER TABLE [dbo].[AspNetUserRoles]  WITH CHECK ADD  CONSTRAINT [FK_AspNetUserRoles_AspNetUsers_UserId] FOREIGN KEY([UserId])
REFERENCES [dbo].[AspNetUsers] ([Id])
ON DELETE CASCADE
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_AspNetUserRoles_AspNetUsers_UserId]') AND parent_object_id = OBJECT_ID(N'[dbo].[AspNetUserRoles]'))
ALTER TABLE [dbo].[AspNetUserRoles] CHECK CONSTRAINT [FK_AspNetUserRoles_AspNetUsers_UserId]
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_AspNetUserTokens_AspNetUsers_UserId]') AND parent_object_id = OBJECT_ID(N'[dbo].[AspNetUserTokens]'))
ALTER TABLE [dbo].[AspNetUserTokens]  WITH CHECK ADD  CONSTRAINT [FK_AspNetUserTokens_AspNetUsers_UserId] FOREIGN KEY([UserId])
REFERENCES [dbo].[AspNetUsers] ([Id])
ON DELETE CASCADE
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_AspNetUserTokens_AspNetUsers_UserId]') AND parent_object_id = OBJECT_ID(N'[dbo].[AspNetUserTokens]'))
ALTER TABLE [dbo].[AspNetUserTokens] CHECK CONSTRAINT [FK_AspNetUserTokens_AspNetUsers_UserId]
GO
IF EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[AspNetRoles]') AND type in (N'U'))
BEGIN
INSERT INTO [dbo].[AspNetRoles] ([Id], [Name], [NormalizedName], [ConcurrencyStamp]) VALUES (NewId(), 'Admin', 'ADMİN', NewId())
INSERT INTO [dbo].[AspNetRoles] ([Id], [Name], [NormalizedName], [ConcurrencyStamp]) VALUES (NewId(), 'Manager', 'MANAGER', NewId())
INSERT INTO [dbo].[AspNetRoles] ([Id], [Name], [NormalizedName], [ConcurrencyStamp]) VALUES (NewId(), 'Editor', 'EDİTOR', NewId())
INSERT INTO [dbo].[AspNetRoles] ([Id], [Name], [NormalizedName], [ConcurrencyStamp]) VALUES (NewId(), 'WebUser', 'WEBUSER', NewId())
INSERT INTO [dbo].[AspNetRoles] ([Id], [Name], [NormalizedName], [ConcurrencyStamp]) VALUES (NewId(), 'WebApiUser', 'WEBAPİUSER', NewId())
END
GO
