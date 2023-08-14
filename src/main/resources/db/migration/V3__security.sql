DROP IF EXISTS [User]

DROP IF EXISTS [Role]

CREATE TABLE [User](
    [id][INT] NOT NULL,
    [name] [nvarchar](255) NOT NULL,
    [email] [nvarchar](255) NOT NULL,
    [password] [nvarchar](25) NOT NULL
)



CREATE TABLE [Role](
    [id] [INT] NOT NULL,
    [name][nvarchar](255)NOT NULL
)