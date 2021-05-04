CREATE Database Library_DB;

USE Library_DB;

CREATE TABLE Publisher
(
    Publishder_ID  INT         NOT NULL,
    Publisher_Name VARCHAR(50) NOT NULL,
    PRIMARY KEY (Publishder_ID)
);
CREATE TABLE Author
(
    Author_ID        INT          NOT NULL,
    Author_Full_Name VARCHAR(20)  NOT NULL,
    Author_Biography VARCHAR(100) NOT NULL,
    PRIMARY KEY (Author_ID)
);
CREATE TABLE Book
(
    Book_ID              INT         NOT NULL,
    Book_ISBN            VARCHAR(30) NOT NULL,
    Book_Title           VARCHAR(50) NOT NULL,
    Book_Number_of_Pages INT         NOT NULL,
    Book_Category        VARCHAR(50) NOT NULL,
    Book_Language        VARCHAR(20) NOT NULL,
    Publication_Year     INT        NOT NULL,
    Author_ID            INT         NOT NULL,
    Publisher_ID         INT         NOT NULL,
    PRIMARY KEY (Book_ID),
    FOREIGN KEY (Publisher_ID) REFERENCES Publisher (Publishder_ID),
    FOREIGN KEY (Author_ID) REFERENCES Author (Author_ID)
);