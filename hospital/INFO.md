# Hospital Database

## Users and Credentials

- **Admin**
  - Username: `admin`
  - Password: `hospital.admin`
  - Privileges: Full access to all tables and operations.

- **Staff**
  - Username: `staff`
  - Password: `hospital.staff`
  - Privileges: Can INSERT, SELECT, and UPDATE data from `patient`, `icu`, and `ot` tables.

- **Reception**
  - Username: `reception`
  - Password: `hospital.reception`
  - Privileges: Can INSERT, SELECT, and UPDATE data from `appointment` table.

## Database Tables and Schema

### `department`
- **Columns:**
  - `dep_id` (INT): Department ID, primary key.
  - `dep_name` (VARCHAR): Name of the department.

### `employee`
- **Columns:**
  - `emp_id` (INT): Employee ID, primary key.
  - `dep_id` (INT): Department ID, foreign key referencing `department(dep_id)`.
  - `f_name` (VARCHAR): First name of the employee.
  - `l_name` (VARCHAR): Last name of the employee.
  - `emp_mail` (VARCHAR): Email address of the employee.
  - `address` (VARCHAR): Address of the employee.

### `icu`
- **Columns:**
  - `icu_id` (INT): ICU ID, primary key.
  - `type` (VARCHAR): Type of ICU.
  - `floor` (INT): Floor where the ICU is located.

### `ot`
- **Columns:**
  - `opt_id` (INT): OT ID, primary key.
  - `patient_id` (INT): Patient ID, foreign key referencing `patient(patient_id)`.
  - `surgery_id` (INT): Surgery ID, foreign key referencing `surgery(surgery_id)`.
  - `emp_id` (INT): Employee ID, foreign key referencing `employee(emp_id)`.
  - `med_id` (INT): Medication ID, foreign key referencing `pharmacy(med_id)`.
  - `date` (DATE): Date of the operation.

### `patient`
- **Columns:**
  - `patient_id` (INT): Patient ID, primary key.
  - `f_name` (VARCHAR): First name of the patient.
  - `l_name` (VARCHAR): Last name of the patient.
  - `address` (VARCHAR): Address of the patient.
  - `age` (INT): Age of the patient.
  - `icu_id` (INT): ICU ID, foreign key referencing `icu(icu_id)`.
  - `bloodgroup` (VARCHAR): Blood group of the patient.

### `pharmacy`
- **Columns:**
  - `med_id` (INT): Medication ID, primary key.
  - `med_name` (VARCHAR): Name of the medication.
  - `med_manufacturer` (VARCHAR): Manufacturer of the medication.
  - `abbreviated_usage` (VARCHAR): Abbreviated usage description of the medication.

### `surgery`
- **Columns:**
  - `surgery_id` (INT): Surgery ID, primary key.
  - `surgery_name` (VARCHAR): Name of the surgery.
  - `emp_id` (INT): Employee ID, foreign key referencing `employee(emp_id)`.
  - `med_id` (INT): Medication ID, foreign key referencing `pharmacy(med_id)`.

### `appointment`
- **Columns:**
  - `f_name` (VARCHAR): First name of the patient.
  - `l_name` (VARCHAR): Last name of the patient.
  - `address` (VARCHAR): Address of the patient.
  - `age` (INT): Age of the patient.
  - `bloodgroup` (VARCHAR): Blood group of the patient.

## How Everything Functions

### Admin Operations
- **Full Access:** Admin users (`admin`) have full access to all tables and operations within the hospital database management system. They can perform INSERT, SELECT, UPDATE, and DELETE operations on all tables.

### Staff Operations
- **Limited Access:** Staff users (`staff`) can perform INSERT, SELECT, and UPDATE operations on specific tables (`patient`, `icu`, `ot`). They do not have DELETE privileges.

### Reception Operations
- **Appointment Management:** Reception users (`reception`) can manage appointments. They have INSERT, SELECT, and UPDATE privileges specifically on the `appointment` table.

### Functionality Overview
- **Data Insertion:** All users can insert new records into their respective tables (`patient`, `icu`, `ot`, `appointment`) using dedicated insertion methods.
- **Data Retrieval:** Users can retrieve data from their permitted tables using SELECT queries.
- **Data Update:** Users can update existing records within their privileges using UPDATE statements.
- **Data Deletion:** Only admin users (`admin`) can delete records across all tables.