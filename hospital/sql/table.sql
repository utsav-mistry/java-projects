--Create database `hospital`
CREATE DATABASE hospital;

-- Create table `department`
CREATE TABLE department (
    dp_id INT PRIMARY KEY AUTO_INCREMENT,
    dept_name VARCHAR(50) NOT NULL
);

-- Create table `employee`
CREATE TABLE employee (
    emp_id INT PRIMARY KEY AUTO_INCREMENT,
    dep_id INT,
    f_name VARCHAR(50),
    l_name VARCHAR(50),
    emp_mail VARCHAR(100),
    address VARCHAR(100),
    FOREIGN KEY (dep_id) REFERENCES department(dp_id)
);

-- Create table `icu`
CREATE TABLE icu (
    icu_id INT PRIMARY KEY AUTO_INCREMENT,
    type VARCHAR(50),
    floor INT
);

-- Create table `patient`
CREATE TABLE patient (
    patient_id INT PRIMARY KEY AUTO_INCREMENT,
    f_name VARCHAR(50),
    l_name VARCHAR(50),
    address VARCHAR(100),
    age INT,
    bloodgroup VARCHAR(10),
    icu_id INT,
    FOREIGN KEY (icu_id) REFERENCES icu(icu_id)
);

-- Create table `ot`
CREATE TABLE ot (
    opt_id INT PRIMARY KEY AUTO_INCREMENT,
    patient_id INT,
    surgery_id INT,
    emp_id INT,
    med_id INT,
    icu_id INT,
    date DATE,
    FOREIGN KEY (patient_id) REFERENCES patient(patient_id),
    FOREIGN KEY (surgery_id) REFERENCES surgery(surgery_id),
    FOREIGN KEY (emp_id) REFERENCES employee(emp_id),
    FOREIGN KEY (med_id) REFERENCES pharmacy(med_id),
);

-- Create table `pharmacy`
CREATE TABLE pharmacy (
    med_id INT PRIMARY KEY AUTO_INCREMENT,
    med_name VARCHAR(100),
    med_manufacturer VARCHAR(100),
    abbreviated_usage VARCHAR(255)
);

-- Create table `surgery`
CREATE TABLE surgery (
    surgery_id INT PRIMARY KEY AUTO_INCREMENT,
    surgery_name VARCHAR(100),
    emp_id INT,
    med_id INT,
    FOREIGN KEY (emp_id) REFERENCES employee(emp_id),
    FOREIGN KEY (med_id) REFERENCES pharmacy(med_id)
);

-- Create table `appointment`
CREATE TABLE `appointment` (
  `a_id` int(11) NOT NULL AUTO_INCREMENT,
  `f_name` varchar(20) NOT NULL,
  `l_name` varchar(30) NOT NULL,
  `address` varchar(30) NOT NULL,
  `age` int(11) NOT NULL,
  `bloodgroup` varchar(3) NOT NULL,
  PRIMARY KEY (`a_id`)
);