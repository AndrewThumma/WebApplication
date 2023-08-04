DROP PROCEDURE if exists [dbo].[uspCreateUploadTable]
GO

DROP PROCEDURE if exists [dbo].[uspUpdateReportedTerminations]
GO

DROP PROCEDURE if exists [dbo].[uspUpdateWorkingTables]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Andrew Thumma
-- Create date: 8/4/2023
-- Description:	create table with data for upload file
-- =============================================
CREATE PROCEDURE uspCreateUploadTable
	-- Add the parameters for the stored procedure here

AS
BEGIN
SET NOCOUNT ON;

    -- Insert statements for procedure here
	DELETE FROM [403b].UploadFile

	INSERT INTO [403b].UploadFile
	SELECT *
	FROM [403b].ActiveStaff

	INSERT INTO [403b].UploadFile (StaffID, SSN, lastName, firstName, middleInitial, birthDate, hireDate, terminationDate,
	Email, phoneNumber, Address1, Address2, City, [State], zip)
	SELECT  StaffID,
			SSN,
			lastName,
			firstName,
			middleInitial,
			birthDate,
			hireDate,
			IIF(ReportTermination = 1, terminationDate, '') AS "Termination Date",
			Email,
			phoneNumber,
			Address1,
			Address2,
			City,
			[State],
			Zip
	FROM [403b].ReportableTerminations
END
GO

/****** Object:  StoredProcedure [dbo].[uspUpdateWorkingTables]    Script Date: 8/4/2023 7:50:55 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		Andrew Thumma
-- Create date: 8/03/2023
-- Description:	Used for updating the working tables for use in 403(b) demographics project
-- =============================================
CREATE PROCEDURE [dbo].[uspUpdateWorkingTables]
	-- Add the parameters for the stored procedure here

AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	DELETE FROM [403b].ActiveStaff
	DELETE FROM [403b].ReportableTerminations

	INSERT INTO [403b].ActiveStaff
	SELECT * 
	FROM [403b].ImportedData AS T1
	WHERE [terminationDate] = ''
	
	INSERT INTO [403b].ReportableTerminations (StaffID,SSN,lastName,firstName,middleInitial, birthDate, hireDate, terminationDate, Email, phoneNumber, Address1, Address2, City, [State], Zip)
	SELECT [StaffID],
			[SSN],
			[lastName],
			[firstName],
			[middleInitial],
			[birthDate],
			[hireDate],
			terminationDate,
			Email,
			phoneNumber,
			Address1,
			Address2,
			City,
			[State],
			Zip
	FROM [403b].ImportedData AS T1
	WHERE [terminationDate] <> '' AND NOT EXISTS (SELECT * FROM [403b].ReportedTerminations AS S1 where S1.StaffID = T1.StaffID)
END
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Andrew Thumma
-- Create date: 8/4/2023
-- Description:	Used to add new reported terminations to permenant table
-- =============================================
CREATE PROCEDURE uspUpdateReportedTerminations
	-- Add the parameters for the stored procedure here

AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	INSERT INTO [403b].ReportedTerminations
	SELECT StaffID
	FROM [403b].ReportableTerminations
	WHERE ReportTermination = 'True'
END
GO