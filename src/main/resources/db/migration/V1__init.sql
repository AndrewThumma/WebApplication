drop table if exists [403b].ActiveStaff;
drop table if exists [403b].importedData;
drop table if exists [403b].ReportableTerminations;
drop table if exists [403b].ReportedTerminations;
drop table if exists [403b].UploadFile;

CREATE TABLE [403b].[ActiveStaff](
	[StaffID] [nvarchar](255) NOT NULL,
	[SSN] [nvarchar](255) NULL,
	[lastName] [nvarchar](255) NULL,
	[firstName] [nvarchar](255) NULL,
	[middleInitial] [nvarchar](255) NULL,
	[birthDate] [nvarchar](255) NULL,
	[hireDate] [nvarchar](255) NULL,
	[terminationDate] [nvarchar](255) NULL,
	[email] [nvarchar](255) NULL,
	[phoneNumber] [nvarchar](255) NULL,
	[address1] [nvarchar](255) NULL,
	[address2] [nvarchar](255) NULL,
	[city] [nvarchar](255) NULL,
	[state] [nvarchar](255) NULL,
	[zip] [nvarchar](255) NULL,
	[reportTermination] [bit] NULL
)

CREATE TABLE [403b].[ImportedData](
	[StaffID] [nvarchar](255) NOT NULL,
	[SSN] [nvarchar](255) NULL,
	[lastName] [nvarchar](255) NULL,
	[firstName] [nvarchar](255) NULL,
	[middleInitial] [nvarchar](255) NULL,
	[birthDate] [nvarchar](255) NULL,
	[hireDate] [nvarchar](255) NULL,
	[terminationDate] [nvarchar](255) NULL,
	[email] [nvarchar](255) NULL,
	[phoneNumber] [nvarchar](255) NULL,
	[address1] [nvarchar](255) NULL,
	[address2] [nvarchar](255) NULL,
	[city] [nvarchar](255) NULL,
	[state] [nvarchar](255) NULL,
	[zip] [nvarchar](255) NULL,
	[reportTermination] [bit] NULL
)

CREATE TABLE [403b].[ReportableTerminations](
	[StaffID] [nvarchar](255) NOT NULL,
	[SSN] [nvarchar](255) NULL,
	[lastName] [nvarchar](255) NULL,
	[firstName] [nvarchar](255) NULL,
	[middleInitial] [nvarchar](255) NULL,
	[birthDate] [nvarchar](255) NULL,
	[hireDate] [nvarchar](255) NULL,
	[terminationDate] [nvarchar](255) NULL,
	[email] [nvarchar](255) NULL,
	[phoneNumber] [nvarchar](255) NULL,
	[address1] [nvarchar](255) NULL,
	[address2] [nvarchar](255) NULL,
	[city] [nvarchar](255) NULL,
	[state] [nvarchar](255) NULL,
	[zip] [nvarchar](255) NULL,
	[reportTermination] [bit] NULL
)

CREATE TABLE [403b].[ReportedTerminations](
	[StaffID] [nvarchar](255) NOT NULL
)

CREATE TABLE [403b].[UploadFile](
	[StaffID] [nvarchar](255) NOT NULL,
	[SSN] [nvarchar](255) NULL,
	[lastName] [nvarchar](255) NULL,
	[firstName] [nvarchar](255) NULL,
	[middleInitial] [nvarchar](255) NULL,
	[birthDate] [nvarchar](255) NULL,
	[hireDate] [nvarchar](255) NULL,
	[terminationDate] [nvarchar](255) NULL,
	[email] [nvarchar](255) NULL,
	[phoneNumber] [nvarchar](255) NULL,
	[address1] [nvarchar](255) NULL,
	[address2] [nvarchar](255) NULL,
	[city] [nvarchar](255) NULL,
	[state] [nvarchar](255) NULL,
	[zip] [nvarchar](255) NULL,
	[reportTermination] [bit] NULL
)

