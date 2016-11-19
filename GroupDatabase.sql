DROP DATABASE if exists UserAndPollDatabase;

CREATE DATABASE UserAndPollDatabase;

USE UserAndPollDatabase;

CREATE TABLE Users (
  UserID int(11) primary key not null auto_increment,
  Email varchar(50) not null,
  Pword varchar(50) not null,
  City varchar(50) not null,
  AgeRange int(11) not null,
  Gender int(11) not null,
  Race int(11) not null,
  Education int(11) not null,
  Points int(11) not null,
  Username varchar(50) 

);

#INSERT INTO Users (Email, pword, City, AgeRange, Gender, Race, Education) VALUES ('me@gmail.com', 'mypassword', 'Los Angeles', 1, 0, 1, 0);

CREATE TABLE Followers (
	UserID int(11) not null,
    FollowID int(11) not null
);

CREATE TABLE UserPolls (
	UserID int(11) not null,
    PollID int(11) not null,
    Answer int(11) not null
);

CREATE TABLE CreatedPolls (
	UserID int(11) not null,
    PollID int(11) not null
);



CREATE TABLE Polls (
	PollID int(11) primary key not null auto_increment,
    Question varchar(100) not null,
    Category varchar(50) not null,
    Answer1 varchar(50) not null,
    Answer2 varchar(50) not null,
    Answer3 varchar(50),
    Answer4 varchar(50),
    TotalCount int(11) not null,
    Count1 int(11) not null,
    Count2 int(11) not null,
    Count3 int(11),
    Count4 int(11)
);

CREATE TABLE Analytics (
	AnalyticsID int(11) primary key not null auto_increment,
    TotalCount int(11) not null
);

CREATE TABLE Words (
	what int(11)
);


