create database ajay;
use ajay;

CREATE TABLE Admin(
emailID VARCHAR(255),
password VARCHAR(20)
);
INSERT INTO Admin (emailID, password) VALUES ('admin@gmail.com', 'password1');

SET SQL_SAFE_UPDATES = 0;
-- Hotel
CREATE TABLE Hotel (
    HotelID int PRIMARY KEY,
    HotelName VARCHAR(255),
    City VARCHAR(100),
    State VARCHAR(100),
    PIN VARCHAR(10),
    Rating DECIMAL(2,1)
    
);


-- HotelEmail
CREATE TABLE HotelEmail (
    Hotelno int,
    Email VARCHAR(255),
    PRIMARY KEY (Hotelno, Email),
    FOREIGN KEY (Hotelno) REFERENCES Hotel(HotelID) ON DELETE CASCADE ON UPDATE CASCADE
);

-- HotelContact
CREATE TABLE HotelContact (
    Hotelno int,
    Phone VARCHAR(15),
    PRIMARY KEY (Hotelno, Phone),
    FOREIGN KEY (Hotelno) REFERENCES Hotel(HotelID) ON DELETE CASCADE ON UPDATE CASCADE
);
-- User
CREATE TABLE User (
    UserID varchar(36) primary key ,
    Fname VARCHAR(100),
    Mname VARCHAR(100),
    Lname VARCHAR(100),
    FlatNo VARCHAR(100),
    Sex Varchar(10),
    DOB DATE,
    City VARCHAR(100),
    State VARCHAR(100),
    PIN VARCHAR(10),
    PhoneNo VARCHAR(15),
    Score INT,
     pEmail VARCHAR(255) ,
    password VARCHAR(255)
    
);
INSERT INTO User (UserID, Fname, Mname, Lname, FlatNo, Sex, DOB, City, State, PIN, PhoneNo, Score, pEmail, password)
VALUES (uuid(), 'John', 'A.', 'Doe', '101', 'M', '1990-01-01', 'New York', 'NY', '10001', '1234567890', 85, 'john.doe@example.com', 'password123');
-- Department
CREATE TABLE Department (
    DeptID varchar(36) PRIMARY KEY,
    DeptName VARCHAR(100),
    Description TEXT
);
CREATE TABLE HotelDepartment(
SNO int primary key,
Hotelno int,
Departmentno varchar(36),
FOREIGN KEY (Departmentno) REFERENCES Department(DeptID) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (Hotelno) REFERENCES Hotel(HotelID) ON DELETE SET NULL ON UPDATE CASCADE 
);
select * from User;

-- Staff
CREATE TABLE Staff (
    StaffID varchar(36) PRIMARY KEY,  
    Belongsto int,
    FirstName VARCHAR(100),
    LastName VARCHAR(100),
    Salary DECIMAL(10,2),
    Position VARCHAR(100),
    Sex CHAR(1),
    City VARCHAR(100),
    State VARCHAR(100),
    PIN VARCHAR(10),
    aadhaarNo varchar(12),
    role varchar(255),
	accountNo varchar(20),
    IFSCCode varchar(255),
    bankName varchar(255),
    pEmail varchar(255),
    password VARCHAR(255),
     FOREIGN KEY (Belongsto) REFERENCES HotelDepartment(SNo) ON DELETE CASCADE ON UPDATE CASCADE
);
create table head(
     manager varchar(36),
     belongs int,
     primary key(manager,belongs),
	FOREIGN KEY (belongs) REFERENCES HotelDepartment(SNo) ON DELETE CASCADE ON UPDATE CASCADE,
      FOREIGN KEY (manager) REFERENCES Staff(StaffId) ON DELETE CASCADE ON UPDATE CASCADE);
-- RoomType
CREATE TABLE RoomType (
    TypeID varchar(36) PRIMARY KEY,
    TypeName VARCHAR(100),
    Description TEXT
);
CREATE TABLE Room (
    RoomID varchar(36) PRIMARY KEY,
    ContainedIn int,
    FloorNo INT,
    Cost DECIMAL(10,2),
    Occupancy INT,
    AvailabilityStatus VARCHAR(3) CHECK (AvailabilityStatus IN ('Yes', 'No')),
    TypeID varchar(36),
    FOREIGN KEY (TypeID) REFERENCES RoomType(TypeID) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (ContainedIn) REFERENCES Hotel(HotelID) ON DELETE SET NULL ON UPDATE CASCADE
);

-- RoomRequest
CREATE TABLE RoomRequest (
    RoomRequestID varchar(36) PRIMARY KEY,
    RoomNo varchar(36),
    CheckIn TIME,
    CheckOut TIME,
    Status VARCHAR(3) CHECK (Status IN ('Yes', 'No')),
    UserNo varchar(36),
    FOREIGN KEY (RoomNo) REFERENCES Room(RoomID) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (UserNo) REFERENCES User(UserID) ON DELETE CASCADE ON UPDATE CASCADE
);



-- Service
CREATE TABLE Service (
    ServiceID varchar(36) PRIMARY KEY,
      Belongsto int,
    ServiceName VARCHAR(100),
    Rating DECIMAL(2,1),
    Description TEXT,
    Price DECIMAL(10,2),
    AvailabilityStatus VARCHAR(3) CHECK (AvailabilityStatus IN ('Yes', 'No')),
     FOREIGN KEY (Belongsto) REFERENCES HotelDepartment(SNo) ON DELETE CASCADE ON UPDATE CASCADE 
);
-- ServiceRequest
CREATE TABLE ServiceRequest (
    RequestServiceID varchar(36) PRIMARY KEY,
    ServiceNo varchar(36),
    Date DATE,
    Time TIME,
    Status VARCHAR(3) CHECK (Status IN ('Yes', 'No')),
    ServiceRequestedBy varchar(36),
    FOREIGN KEY (ServiceNo) REFERENCES Service(ServiceID) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (ServiceRequestedBy) REFERENCES User(UserID) ON DELETE CASCADE ON UPDATE CASCADE
);
-- Discount
CREATE TABLE Discount (
    DiscountID varchar(36) PRIMARY KEY,
    Value DECIMAL(10,2),
    Status VARCHAR(3) CHECK (Status IN ('Yes', 'No')),
    ScoreRequired INT
);

-- Booking
CREATE TABLE Booking (
    BookingID varchar(36) PRIMARY KEY,
    RoomReqNo varchar(36),
    BookingTime TIME,
    Date DATE,
    Status VARCHAR(3) CHECK (Status IN ('Yes', 'No')),
    DiscountNo varchar(36),
    ServiceReqNo varchar(36),
    FOREIGN KEY (RoomReqNo) REFERENCES RoomRequest(RoomRequestID) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (DiscountNo) REFERENCES Discount(DiscountID) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (ServiceReqNo) REFERENCES ServiceRequest(RequestServiceID) ON DELETE SET NULL ON UPDATE CASCADE
);
-- Payment
CREATE TABLE Payment (
    PaymentID varchar(36) PRIMARY KEY,
    SettlementID varchar(36),
    PaymentMode VARCHAR(10) CHECK (PaymentMode IN ('Cash', 'Card', 'Online')),
    Amount DECIMAL(10,2),
    Date DATE,
    Time TIME,
    PaymentStatus VARCHAR(3) CHECK (PaymentStatus IN ('Yes', 'No')),
    TransactionID VARCHAR(255),
    FOREIGN KEY (SettlementID) REFERENCES Booking(BookingID) ON DELETE SET NULL ON UPDATE CASCADE
);

-- Cancellation
CREATE TABLE Cancellation (
    CancellationID varchar(36) PRIMARY KEY,
    BookingNo varchar(36),
    Date DATE,
    Time TIME,
    RefundTime TIME,
    Status VARCHAR(3) CHECK (Status IN ('Yes', 'No')),
    Reason TEXT,
    FOREIGN KEY (BookingNo) REFERENCES Booking(BookingID) ON DELETE SET NULL ON UPDATE CASCADE
);

-- LostAndFound
CREATE TABLE LostAndFound (
    SNo varchar(36) PRIMARY KEY,
    HandledBy varchar(36),
    ItemType TEXT,
    FoundLocation VARCHAR(255),
    Status VARCHAR(3) CHECK (Status IN ('Yes', 'No')),
    Date DATE,
    Time TIME,
    Description TEXT,
    StaffID INT,
    hotelno int,
    FOREIGN KEY (HandledBy) REFERENCES Staff(StaffID) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (hotelno) REFERENCES Hotel(HotelId) ON DELETE SET NULL ON UPDATE CASCADE
);

-- LostAndFoundImage
CREATE TABLE LostAndFoundImage (
    SNo varchar(36),
    Image VARCHAR(255),
    PRIMARY KEY (SNo, Image),
    FOREIGN KEY (SNo) REFERENCES LostAndFound(SNo) ON DELETE CASCADE ON UPDATE CASCADE
);


-- Feedback
CREATE TABLE Feedback (
    ReviewID varchar(36) PRIMARY KEY,
    Ratings INT,
    UserNo varchar(36),
    Comments TEXT,
    Date DATE,
    Time TIME,
    FOREIGN KEY (UserNo) REFERENCES User(UserID) ON DELETE CASCADE ON UPDATE CASCADE
);

-- ReviewImages
CREATE TABLE ReviewImages (
    ReviewNo varchar(36),
    ReviewImage varchar(255),
    PRIMARY KEY (ReviewNo, ReviewImage),
    FOREIGN KEY (ReviewNo) REFERENCES Feedback(ReviewID) ON DELETE CASCADE ON UPDATE CASCADE
);
  -- drop database ajay;
INSERT INTO Hotel (HotelID, HotelName, City, State, PIN, Rating)
VALUES (1, 'Hotel Vivekananda', 'Aurangabad', 'Maharashtra', '431001', 4.5);
-- Insert Departments
-- Insert Departments using UUIDs
INSERT INTO Department (DeptID, DeptName, Description)
VALUES 
(UUID(), 'Housekeeping', 'Responsible for cleaning and maintaining rooms'),
(UUID(), 'Reception', 'Manages guest check-ins and check-outs'),
(UUID(), 'Restaurant', 'Handles food and beverage services'),
(UUID(), 'Security', 'Ensures the safety and security of guests and staff'),
(UUID(), 'Maintenance', 'Handles repairs and ensures the proper functioning of hotel facilities');

-- Insert HotelDepartment relationships using UUIDs
-- Replace 'UUID-1', 'UUID-2', etc., with actual UUIDs from the Department table after insertion
-- Insert data into HotelDepartment table
select * from Department;
INSERT INTO HotelDepartment (SNO, Hotelno, Departmentno)
VALUES
    (1, 1, '94adc280-8571-11ef-aa6a-00ffef917db7'),
    (2, 1, '94adc726-8571-11ef-aa6a-00ffef917db7'),
    (3, 1, '94adc89a-8571-11ef-aa6a-00ffef917db7'),
    (4, 1, '94adc8ec-8571-11ef-aa6a-00ffef917db7'),
    (5, 1, '94adc938-8571-11ef-aa6a-00ffef917db7');
INSERT INTO Staff (StaffID, Belongsto, FirstName, LastName, Salary, Position, Sex, City, State, PIN, aadhaarNo, role, accountNo, IFSCCode, bankName, pEmail,password)
VALUES
(UUID(), 1, 'Rahul', 'Sharma', 45000.00, 'Housekeeping Manager', 'M', 'Aurangabad', 'Maharashtra', '431001', '123456789012', 'Manager', '1234567890123456', 'SBIN0001234','State Bank of India', 'rahul.sharma@gmail.com','Anu@1168');

ALTER TABLE Staff
ADD password VARCHAR(255);

select * from Staff;
-- delete from Staff where StaffID = uuid()