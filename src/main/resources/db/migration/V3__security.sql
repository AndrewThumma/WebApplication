DROP table IF EXISTS [Users]

DROP table IF EXISTS [Roles]

Drop table if Exists [users_roles]

CREATE TABLE [Users](
    [id][INT] identity NOT NULL,
    [name] [nvarchar](255) NOT NULL,
    [email] [nvarchar](255) NOT NULL,
    [password] [nvarchar](255) NOT NULL
)

CREATE TABLE [Roles](
    [id] [INT] identity NOT NULL,
    [name][nvarchar](255)NOT NULL
)

Create Table [users_roles](
    [user_id] [INT] NOT NULL,
    [role_id] [INT] NOT NULL
)